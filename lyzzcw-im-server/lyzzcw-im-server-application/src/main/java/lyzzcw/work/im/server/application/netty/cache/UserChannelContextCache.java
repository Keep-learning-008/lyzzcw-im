package lyzzcw.work.im.server.application.netty.cache;

import io.netty.channel.ChannelHandlerContext;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * 用户通道上下文缓存
 *
 * @author lzy
 * @date 2023/12/20
 */
public class UserChannelContextCache {

    /**
     * 缓存userId和ChannelHandlerContext的关系
     * 主要格式：Map<userId, Map<terminal, ctx>>
     */
    private static Map<Long, Map<Integer, ChannelHandlerContext>> channelMap = new ConcurrentHashMap<>();

    public static void addChannelCtx(Long userId, Integer channel, ChannelHandlerContext ctx){
        channelMap.computeIfAbsent(userId, key -> new ConcurrentHashMap<>()).put(channel, ctx);
    }

    public static void removeChannelCtx(Long userId, Integer terminal){
        if (userId != null && terminal != null && channelMap.containsKey(userId)){
            Map<Integer, ChannelHandlerContext> userChannelMap = channelMap.get(userId);
            if (userChannelMap.containsKey(terminal)){
                userChannelMap.remove(terminal);
            }
        }
    }

    public static ChannelHandlerContext getChannelCtx(Long userId, Integer terminal){
        if (userId != null && terminal != null && channelMap.containsKey(userId)){
            Map<Integer, ChannelHandlerContext> userChannelMap = channelMap.get(userId);
            if (userChannelMap.containsKey(terminal)){
                return userChannelMap.get(terminal);
            }
        }
        return null;
    }

    public static Map<Integer, ChannelHandlerContext> getChannelCtx(Long userId){
        if (userId == null){
            return null;
        }
        return channelMap.get(userId);
    }

}
