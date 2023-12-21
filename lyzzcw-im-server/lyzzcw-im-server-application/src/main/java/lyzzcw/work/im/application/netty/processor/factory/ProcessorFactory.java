package lyzzcw.work.im.application.netty.processor.factory;


import lyzzcw.work.im.application.netty.processor.MessageProcessor;
import lyzzcw.work.im.application.netty.processor.impl.GroupMessageProcessor;
import lyzzcw.work.im.application.netty.processor.impl.HeartbeatProcessor;
import lyzzcw.work.im.application.netty.processor.impl.LoginProcessor;
import lyzzcw.work.im.application.netty.processor.impl.PrivateMessageProcessor;
import lyzzcw.work.im.common.domain.enums.IMCmdType;
import lyzzcw.work.im.domain.utils.SpringContextHolder;

/**
 * 处理器工厂
 *
 * @author lzy
 * @date 2023/12/20
 */
public class ProcessorFactory {

    public static MessageProcessor<?> getProcessor(IMCmdType cmd){
        switch (cmd){
            //登录
            case LOGIN:
                return SpringContextHolder.getApplicationContext().getBean(LoginProcessor.class);
            //心跳
            case HEART_BEAT:
                return SpringContextHolder.getApplicationContext().getBean(HeartbeatProcessor.class);
            //单聊消息
            case PRIVATE_MESSAGE:
                return SpringContextHolder.getApplicationContext().getBean(PrivateMessageProcessor.class);
            //群聊消息
            case GROUP_MESSAGE:
                return SpringContextHolder.getApplicationContext().getBean(GroupMessageProcessor.class);
            default:
                return null;

        }
    }
}
