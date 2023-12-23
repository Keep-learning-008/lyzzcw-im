package lyzzcw.work.im.server.application.netty.tcp.codec;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ReplayingDecoder;
import lyzzcw.work.im.common.domain.constants.IMConstants;
import lyzzcw.work.im.common.domain.model.IMSendInfo;

import java.nio.charset.StandardCharsets;
import java.util.List;


/**
 * TCP套接字消息协议解码器
 *
 * ReplayingDecoder解码器相关解释
 * https://blog.csdn.net/panghuangang/article/details/132215799
 *
 * @author lzy
 * @date 2023/12/20
 */
public class TcpSocketMessageProtocolDecoder extends ReplayingDecoder {
    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        if (byteBuf.readableBytes() < IMConstants.MIN_READABLE_BYTES){
            return;
        }
        //获取数据包长度
        int length = byteBuf.readInt();
        ByteBuf contentBuf = byteBuf.readBytes(length);
        String content = contentBuf.toString(StandardCharsets.UTF_8);
        ObjectMapper objectMapper = new ObjectMapper();
        IMSendInfo imSendInfo = objectMapper.readValue(content, IMSendInfo.class);
        list.add(imSendInfo);
    }
}
