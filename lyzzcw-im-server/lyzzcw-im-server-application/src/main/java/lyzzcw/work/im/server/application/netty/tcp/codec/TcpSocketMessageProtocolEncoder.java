package lyzzcw.work.im.server.application.netty.tcp.codec;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import lyzzcw.work.im.common.domain.model.IMSendInfo;

import java.nio.charset.StandardCharsets;


/**
 * TCP 套接字消息协议编码器
 *
 * @author lzy
 * @date 2023/12/20
 */
public class TcpSocketMessageProtocolEncoder extends MessageToByteEncoder<IMSendInfo<?>> {
    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, IMSendInfo<?> imSendInfo, ByteBuf byteBuf) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        String content = objectMapper.writeValueAsString(imSendInfo);
        byte[] bytes = content.getBytes(StandardCharsets.UTF_8);
        byteBuf.writeInt(bytes.length);
        byteBuf.writeBytes(bytes);

    }
}
