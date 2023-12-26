package lyzzcw.work.im.sdk.interfaces.client.impl;


import lombok.RequiredArgsConstructor;
import lyzzcw.work.im.common.domain.enums.IMTerminalType;
import lyzzcw.work.im.common.domain.model.IMGroupMessage;
import lyzzcw.work.im.common.domain.model.IMPrivateMessage;
import lyzzcw.work.im.sdk.interfaces.client.IMClient;
import lyzzcw.work.im.sdk.interfaces.sender.IMSender;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 默认 imclient
 *
 * @author lzy
 * @date 2023/12/26
 */
@Service
@RequiredArgsConstructor
public class DefaultIMClient implements IMClient {
    final IMSender imSender;

    @Override
    public <T> void sendPrivateMessage(IMPrivateMessage<T> message) {
        imSender.sendPrivateMessage(message);
    }

    @Override
    public <T> void sendGroupMessage(IMGroupMessage<T> message) {
        imSender.sendGroupMessage(message);
    }

    @Override
    public Boolean isOnline(Long userId) {
        return imSender.isOnline(userId);
    }

    @Override
    public List<Long> getOnlineUserList(List<Long> userIds) {
        return imSender.getOnlineUser(userIds);
    }

    @Override
    public Map<Long, List<IMTerminalType>> getOnlineTerminal(List<Long> userIds) {
        return imSender.getOnlineTerminal(userIds);
    }
}
