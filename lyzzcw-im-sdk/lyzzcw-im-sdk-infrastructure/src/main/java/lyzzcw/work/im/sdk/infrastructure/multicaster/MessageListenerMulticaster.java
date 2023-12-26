package lyzzcw.work.im.sdk.infrastructure.multicaster;


import lyzzcw.work.im.common.domain.enums.IMListenerType;
import lyzzcw.work.im.common.domain.model.IMSendResult;

/**
 * 消息侦听器多播器
 *
 * @author lzy
 * @date 2023/12/26
 */
public interface MessageListenerMulticaster {

    /**
     * 广播消息
     * @param listenerType 监听的类型
     * @param result 发送消息的结果
     * @param <T> 泛型类型
     */
    <T> void multicast(IMListenerType listenerType, IMSendResult<T> result);
}
