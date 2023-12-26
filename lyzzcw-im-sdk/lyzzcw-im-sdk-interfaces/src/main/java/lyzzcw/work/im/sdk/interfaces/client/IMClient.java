package lyzzcw.work.im.sdk.interfaces.client;


import lyzzcw.work.im.common.domain.enums.IMTerminalType;
import lyzzcw.work.im.common.domain.model.IMGroupMessage;
import lyzzcw.work.im.common.domain.model.IMPrivateMessage;

import java.util.List;
import java.util.Map;

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


    /**
     * 判断用户是否在线
     */
    Boolean isOnline(Long userId);

    /**
     * 筛选出在线的用户
     */
    List<Long> getOnlineUserList(List<Long> userIds);

    /**
     * 获取用户与其在线的终端列表
     */
    Map<Long,List<IMTerminalType>> getOnlineTerminal(List<Long> userIds);
}
