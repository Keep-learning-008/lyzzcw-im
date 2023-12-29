package lyzzcw.work.im.platform.common.domain.session;


import lyzzcw.work.im.platform.common.domain.constant.IMPlatformConstants;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;


/**
 * 会话上下文
 *
 * @author lzy
 * @date 2023/12/29
 */
public class SessionContext {

    public static UserSession getSession(){
        // 从请求上下文里获取Request对象
        ServletRequestAttributes requestAttributes = ServletRequestAttributes.class.
                cast(RequestContextHolder.getRequestAttributes());
        HttpServletRequest request = requestAttributes.getRequest();
        return  (UserSession) request.getAttribute(IMPlatformConstants.SESSION);
    }
}
