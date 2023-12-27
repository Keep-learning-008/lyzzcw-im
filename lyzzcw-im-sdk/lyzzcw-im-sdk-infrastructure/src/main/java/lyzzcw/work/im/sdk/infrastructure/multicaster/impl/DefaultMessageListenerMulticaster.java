package lyzzcw.work.im.sdk.infrastructure.multicaster.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.alibaba.fastjson.JSONObject;
import lyzzcw.work.im.common.domain.enums.IMListenerType;
import lyzzcw.work.im.common.domain.model.IMSendResult;
import lyzzcw.work.im.sdk.domain.annotation.IMListener;
import lyzzcw.work.im.sdk.infrastructure.listener.MessageListener;
import lyzzcw.work.im.sdk.infrastructure.multicaster.MessageListenerMulticaster;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;


/**
 * 默认消息侦听器多播程序
 *
 * @author lzy
 * @date 2023/12/27
 */
@Component
public class DefaultMessageListenerMulticaster implements MessageListenerMulticaster {

    @Autowired(required = false)
    private List<MessageListener> messageListenerList = Collections.emptyList();

    @Override
    public <T> void multicast(IMListenerType listenerType, IMSendResult<T> result) {
        //为空，直接返回
        if (CollectionUtil.isEmpty(messageListenerList)){
            return;
        }
        messageListenerList.forEach((messageListener) -> {
            IMListener imListener = messageListener.getClass().getAnnotation(IMListener.class);
            if (imListener != null && (IMListenerType.ALL.equals(imListener.listenerType()) || imListener.listenerType().equals(listenerType))){
                if (result.getData() instanceof JSONObject){
                    Type superInterface = messageListener.getClass().getGenericInterfaces()[0];
                    Type type = ((ParameterizedType)superInterface).getActualTypeArguments()[0];
                    JSONObject data = (JSONObject) result.getData();
                    result.setData(data.toJavaObject(type));
                }
                messageListener.doProcess(result);
            }
        });
    }
}
