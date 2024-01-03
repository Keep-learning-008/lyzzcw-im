package lyzzcw.work.im.platform.common.domain.interceptor;


import lyzzcw.work.im.platform.common.domain.enums.HttpCode;
import lyzzcw.work.im.platform.common.domain.exception.IMException;
import lyzzcw.work.im.platform.common.domain.interceptor.base.BaseInterceptor;
import lyzzcw.work.im.platform.common.domain.risk.rule.RuleChainService;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


/**
 * 拦截器
 *
 * @author lzy
 * @date 2024/01/03
 */
@Component
public class IMInterceptor extends BaseInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //获取排好序的规则链
        List<RuleChainService> ruleChainServices = this.getRuleChainServices();
        //遍历规则链
        for (RuleChainService ruleChainService : ruleChainServices){
            HttpCode httpCode = ruleChainService.execute(request, handler);
            if (!HttpCode.SUCCESS.getCode().equals(httpCode.getCode())){
                 throw new IMException(httpCode);
            }
        }
        return true;
    }
}
