package lyzzcw.work.im.sdk.infrastructure.listener;


import lyzzcw.work.im.common.domain.model.IMSendResult;

/**
 * 消息侦听器
 *
 * @author lzy
 * @date 2023/12/27
 */
public interface MessageListener<T> {

    /**
     * 处理发送的结果
     */
    void doProcess(IMSendResult<T> result);
}
