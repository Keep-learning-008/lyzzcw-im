/**
 * Copyright 2022-9999 the original author or authors.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package lyzzcw.work.im.sdk.interfaces.sender.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.BooleanUtil;
import cn.hutool.core.util.StrUtil;
import lombok.RequiredArgsConstructor;
import lyzzcw.work.component.mq.service.MessageSenderService;
import lyzzcw.work.component.redis.cache.distribute.DistributeCacheService;
import lyzzcw.work.im.common.domain.constants.IMConstants;
import lyzzcw.work.im.common.domain.enums.IMCmdType;
import lyzzcw.work.im.common.domain.enums.IMListenerType;
import lyzzcw.work.im.common.domain.enums.IMSendCode;
import lyzzcw.work.im.common.domain.model.IMPrivateMessage;
import lyzzcw.work.im.common.domain.model.IMReceiveInfo;
import lyzzcw.work.im.common.domain.model.IMSendResult;
import lyzzcw.work.im.common.domain.model.IMUserInfo;
import lyzzcw.work.im.sdk.infrastructure.multicaster.MessageListenerMulticaster;
import lyzzcw.work.im.sdk.interfaces.sender.IMSender;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * 默认 imsender
 *
 * @author lzy
 * @date 2023/12/26
 */
@Service
@RequiredArgsConstructor
public class DefaultIMSender implements IMSender {

    final DistributeCacheService distributedCacheService;

    final MessageSenderService messageSenderService;

    final MessageListenerMulticaster messageListenerMulticaster;


    @Override
    public <T> void sendPrivateMessage(IMPrivateMessage<T> message) {
        if (message == null){
            return;
        }
        //向用户的终端发送数据
        List<Integer> receiveTerminals = message.getReceiveTerminals();
        //终端不为空
        if (!CollectionUtil.isEmpty(receiveTerminals)){
            //向目标用户发送私聊消息
            this.sendPrivateMessageToTargetUser(message, receiveTerminals);
            //向自己的其他终端发送私聊消息
            this.sendPrivateMessageToSelf(message, receiveTerminals);
        }
    }

    /**
     * 向自己的其他终端发送消息
     */
    private <T> void sendPrivateMessageToSelf(IMPrivateMessage<T> message, List<Integer> receiveTerminals) {
        //向自己的其他终端发送消息
        if (BooleanUtil.isTrue(message.getSendToSelf())){
            receiveTerminals.forEach((receiveTerminal) -> {
                //向自己的其他终端发送消息
                if (!message.getSender().getTerminal().equals(receiveTerminal)){
                    String redisKey = String.join(IMConstants.REDIS_KEY_SPLIT, IMConstants.IM_USER_SERVER_ID, message.getSender().getUserId().toString(), receiveTerminal.toString());
                    String serverId = distributedCacheService.get(redisKey);
                    if (!StrUtil.isEmpty(serverId)){
                        String sendKey = String.join(IMConstants.MESSAGE_KEY_SPLIT,IMConstants.IM_MESSAGE_PRIVATE_QUEUE, serverId);
                        IMReceiveInfo imReceivenfo = new IMReceiveInfo(IMCmdType.PRIVATE_MESSAGE.code(), message.getSender(), Collections.singletonList(new IMUserInfo(message.getSender().getUserId(), receiveTerminal)), false, message.getData());
                        imReceivenfo.setDestination(sendKey);
                        messageSenderService.send(imReceivenfo);
                    }
                }
            });
        }
    }

    /**
     * 向其他用户发送私聊消息
     */
    private <T> void sendPrivateMessageToTargetUser(IMPrivateMessage<T> message, List<Integer> receiveTerminals) {
        receiveTerminals.forEach((receiveTerminal) -> {
            //获取接收消息的用户的channelId
            String redisKey = String.join(IMConstants.REDIS_KEY_SPLIT, IMConstants.IM_USER_SERVER_ID, message.getReceiveId().toString(), receiveTerminal.toString());
            String serverId = distributedCacheService.get(redisKey);
            //用户在线，将消息推送到RocketMQ
            if (!StrUtil.isEmpty(serverId)){
                String sendKey = String.join(IMConstants.MESSAGE_KEY_SPLIT, IMConstants.IM_MESSAGE_PRIVATE_QUEUE, serverId);
                IMReceiveInfo imReceiveInfo = new IMReceiveInfo(IMCmdType.PRIVATE_MESSAGE.code(), message.getSender(), Collections.singletonList(new IMUserInfo(message.getReceiveId(), receiveTerminal)), message.getSendResult(), message.getData());
                //设置发送的主题
                imReceiveInfo.setDestination(sendKey);
                //发送消息
                messageSenderService.send(imReceiveInfo);
            }else if (BooleanUtil.isTrue(message.getSendResult())){
                //回复消息的状态
                IMSendResult<T> result = new IMSendResult<>(message.getSender(), new IMUserInfo(message.getReceiveId(), receiveTerminal), IMSendCode.NOT_ONLINE.code(), message.getData());
                messageListenerMulticaster.multicast(IMListenerType.PRIVATE_MESSAGE, result);
            }
        });
    }
}
