package lyzzcw.work.im.application.netty.processor.impl;

import io.netty.channel.ChannelHandlerContext;
import lombok.extern.slf4j.Slf4j;
import lyzzcw.work.component.mq.service.MessageSenderService;
import lyzzcw.work.im.application.netty.cache.UserChannelContextCache;
import lyzzcw.work.im.application.netty.processor.MessageProcessor;
import lyzzcw.work.im.common.domain.constants.IMConstants;
import lyzzcw.work.im.common.domain.enums.IMCmdType;
import lyzzcw.work.im.common.domain.enums.IMSendCode;
import lyzzcw.work.im.common.domain.model.IMReceiveInfo;
import lyzzcw.work.im.common.domain.model.IMSendInfo;
import lyzzcw.work.im.common.domain.model.IMSendResult;
import lyzzcw.work.im.common.domain.model.IMUserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 私聊消息处理器
 *
 * @author lzy
 * @date 2023/12/21
 */
@Component
@Slf4j
public class PrivateMessageProcessor implements MessageProcessor<IMReceiveInfo> {

    @Autowired
    private MessageSenderService messageSenderService;

    @Override
    public void process(IMReceiveInfo receiveInfo) {
        IMUserInfo sender = receiveInfo.getSender();
        IMUserInfo receiver = receiveInfo.getReceivers().get(0);
        log.info("PrivateMessageProcessor.process|接收到消息,发送者:{}, 接收者:{}, 内容:{}", sender.getUserId(), receiver.getUserId(), receiveInfo.getData());
        try{
            ChannelHandlerContext channelHandlerContext = UserChannelContextCache.getChannelCtx(receiver.getUserId(), receiver.getTerminal());
            if (channelHandlerContext != null){
                //推送消息
                IMSendInfo<?> imSendInfo = new IMSendInfo<>(IMCmdType.PRIVATE_MESSAGE.code(), receiveInfo.getData());
                channelHandlerContext.writeAndFlush(imSendInfo);
                sendResult(receiveInfo, IMSendCode.SUCCESS);
            }else{
                sendResult(receiveInfo, IMSendCode.NOT_FIND_CHANNEL);
                log.error("PrivateMessageProcessor.process|未找到Channel, 发送者:{}, 接收者:{}, 内容:{}", sender.getUserId(), receiver.getUserId(), receiveInfo.getData());
            }
        }catch (Exception e){
            sendResult(receiveInfo, IMSendCode.UNKONW_ERROR);
            log.error("PrivateMessageProcessor.process|发送异常,发送者:{}, 接收者:{}, 内容:{}, 异常信息:{}", sender.getUserId(), receiver.getUserId(), receiveInfo.getData(), e.getMessage());
        }
    }

    private void sendResult(IMReceiveInfo receiveInfo, IMSendCode sendCode){
        if (receiveInfo.getSendResult()){
            IMSendResult<?> result = new IMSendResult<>(receiveInfo.getSender(), receiveInfo.getReceivers().get(0), sendCode.code(), receiveInfo.getData());
            String sendKey = IMConstants.IM_RESULT_PRIVATE_QUEUE;
            result.setDestination(sendKey);
            messageSenderService.send(result);
        }
    }
}
