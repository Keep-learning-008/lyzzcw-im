package lyzzcw.work.im.sdk.interfaces.client.impl;


import lombok.RequiredArgsConstructor;
import lyzzcw.work.im.common.domain.model.IMGroupMessage;
import lyzzcw.work.im.common.domain.model.IMPrivateMessage;
import lyzzcw.work.im.sdk.interfaces.client.IMClient;
import lyzzcw.work.im.sdk.interfaces.sender.IMSender;
import org.springframework.stereotype.Service;

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
}
