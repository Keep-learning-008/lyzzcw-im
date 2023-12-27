package lyzzcw.work.im.sdk.domain.annotation;

import lyzzcw.work.im.common.domain.enums.IMListenerType;
import org.springframework.stereotype.Component;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * 监听消息注解
 *
 * @author lzy
 * @date 2023/12/27
 */
@Target({ElementType.TYPE,ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Component
public @interface IMListener {

    IMListenerType listenerType();
}
