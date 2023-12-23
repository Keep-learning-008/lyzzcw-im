package lyzzcw.work.im.server.application.netty.ws.codec;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import lyzzcw.work.im.common.domain.model.IMSendInfo;

import java.util.List;


/**
 * Web 套接字消息协议解码器
 *
 * @author lzy
 * @date 2023/12/20
 */
public class WebSocketMessageProtocolDecoder extends MessageToMessageDecoder<TextWebSocketFrame> {
    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, TextWebSocketFrame textWebSocketFrame, List<Object> list) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        IMSendInfo imSendInfo = objectMapper.readValue(textWebSocketFrame.text(), IMSendInfo.class);
        list.add(imSendInfo);
    }
}
