package lyzzcw.work.im.platform.common.domain.risk.rule.impl;


import lyzzcw.work.im.platform.common.domain.constant.IMPlatformConstants;
import lyzzcw.work.im.platform.common.domain.enums.HttpCode;
import lyzzcw.work.im.platform.common.domain.exception.IMException;
import lyzzcw.work.im.platform.common.domain.risk.enums.RuleEnum;
import lyzzcw.work.im.platform.common.domain.risk.rule.RuleChainService;
import lyzzcw.work.im.platform.common.domain.risk.rule.base.BaseRuleChainService;
import lyzzcw.work.im.platform.common.domain.session.UserSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;

import javax.servlet.http.HttpServletRequest;


/**
 * 身份验证规则链服务
 *
 * @author lzy
 * @date 2024/01/03
 */
@Component
public class AuthRuleChainService extends BaseRuleChainService implements RuleChainService {
    private final Logger logger = LoggerFactory.getLogger(AuthRuleChainService.class);

    @Value("${bh.im.rule.authRule.order}")
    private Integer authRuleOrder;

    @Override
    public HttpCode execute(HttpServletRequest request, Object handler) {
        //如果不是映射到方法直接通过
        if (!(handler instanceof HandlerMethod)) {
            return HttpCode.SUCCESS;
        }
        UserSession userSession = this.getUserSession(request);
        if (userSession == null){
            logger.error("AuthRuleChainService|未登录，url|{}",request.getRequestURI());
            throw new IMException(HttpCode.NO_LOGIN);
        }
        request.setAttribute(IMPlatformConstants.SESSION, userSession);
        return HttpCode.SUCCESS;
    }

    @Override
    public int getOrder() {
        return authRuleOrder == null ? RuleEnum.AUTH.getCode() : authRuleOrder;
    }

    @Override
    public String getServiceName() {
        return RuleEnum.AUTH.getMesaage();
    }
}
