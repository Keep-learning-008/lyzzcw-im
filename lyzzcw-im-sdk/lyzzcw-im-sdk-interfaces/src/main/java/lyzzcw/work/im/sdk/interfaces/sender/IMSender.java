package lyzzcw.work.im.sdk.interfaces.sender;


import lyzzcw.work.im.common.domain.model.IMGroupMessage;
import lyzzcw.work.im.common.domain.model.IMPrivateMessage;

/**
 * 发送者
 *
 * @author lzy
 * @date 2023/12/26
 */
public interface IMSender {

    /**
     * 发送私聊消息
     */
    <T> void sendPrivateMessage(IMPrivateMessage<T> message);


    /**
     * 发送群聊消息
     */
    <T> void sendGroupMessage(IMGroupMessage<T> message);

}
