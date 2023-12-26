package lyzzcw.work.im.sdk.interfaces.client;


import lyzzcw.work.im.common.domain.model.IMGroupMessage;
import lyzzcw.work.im.common.domain.model.IMPrivateMessage;

/**
 * imclient客户端
 *
 * @author lzy
 * @date 2023/12/26
 */
public interface IMClient {
    /**
     * 发送私聊消息
     */
    <T> void sendPrivateMessage(IMPrivateMessage<T> message);

    /**
     * 发送群消息
     */
    <T> void sendGroupMessage(IMGroupMessage<T> message);
}
