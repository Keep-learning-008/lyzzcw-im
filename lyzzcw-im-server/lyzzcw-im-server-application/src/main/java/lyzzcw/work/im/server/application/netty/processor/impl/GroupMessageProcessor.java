package lyzzcw.work.im.server.application.netty.processor.impl;


import io.netty.channel.ChannelHandlerContext;
import lombok.extern.slf4j.Slf4j;
import lyzzcw.work.component.mq.service.MessageSenderService;
import lyzzcw.work.im.server.application.netty.cache.UserChannelContextCache;
import lyzzcw.work.im.server.application.netty.processor.MessageProcessor;
import lyzzcw.work.im.common.domain.constants.IMConstants;
import lyzzcw.work.im.common.domain.enums.IMCmdType;
import lyzzcw.work.im.common.domain.enums.IMSendCode;
import lyzzcw.work.im.common.domain.model.IMReceiveInfo;
import lyzzcw.work.im.common.domain.model.IMSendInfo;
import lyzzcw.work.im.common.domain.model.IMSendResult;
import lyzzcw.work.im.common.domain.model.IMUserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.List;


/**
 * 群消息处理器
 *
 * @author lzy
 * @date 2023/12/21
 */
@Component
@Slf4j
public class GroupMessageProcessor implements MessageProcessor<IMReceiveInfo> {

    @Autowired
    private MessageSenderService messageSenderService;

    @Async
    @Override
    public void process(IMReceiveInfo receiveInfo) {
        IMUserInfo sender = receiveInfo.getSender();
        List<IMUserInfo> receivers = receiveInfo.getReceivers();
        log.info("GroupMessageProcessor.process|接收到群消息,发送消息用户:{}，接收消息用户数量:{}，消息内容:{}", sender.getUserId(), receivers.size(), receiveInfo.getData());
        receivers.forEach((receiver) -> {
            try{
                ChannelHandlerContext channelHandlerCtx = UserChannelContextCache.getChannelCtx(receiver.getUserId(), receiver.getTerminal());
                if (channelHandlerCtx != null){
                    //向用户推送消息
                    IMSendInfo<?> imSendInfo = new IMSendInfo<>(IMCmdType.GROUP_MESSAGE.code(), receiveInfo.getData());
                    channelHandlerCtx.writeAndFlush(imSendInfo);
                    //发送确认消息
                    sendResult(receiveInfo, receiver, IMSendCode.SUCCESS);
                }else{
                    //未找到用户的连接信息
                    sendResult(receiveInfo, receiver, IMSendCode.NOT_FIND_CHANNEL);
                    log.error("GroupMessageProcessor.process|未找到Channel,发送者:{}, 接收者:{}, 消息内容:{}", sender.getUserId(), receiver.getUserId(), receiveInfo.getData());
                }
            }catch (Exception e){
                sendResult(receiveInfo, receiver, IMSendCode.UNKONW_ERROR);
                log.error("GroupMessageProcessor.process|发送消息异常,发送者:{}, 接收者:{}, 消息内容:{}, 异常信息:{}", sender.getUserId(), receiver.getUserId(), receiveInfo.getData(), e.getMessage());
            }
        });
    }

    /**
     * 发送结果数据
     */
    private void sendResult(IMReceiveInfo imReceiveInfo, IMUserInfo imUserInfo, IMSendCode imSendCode){
        if (imReceiveInfo.getSendResult()){
            IMSendResult<?> imSendResult = new IMSendResult<>(imReceiveInfo.getSender(), imUserInfo, imSendCode.code(), imReceiveInfo.getData());
            imSendResult.setDestination(IMConstants.IM_RESULT_GROUP_QUEUE);
            messageSenderService.send(imSendResult);
        }
    }
}
