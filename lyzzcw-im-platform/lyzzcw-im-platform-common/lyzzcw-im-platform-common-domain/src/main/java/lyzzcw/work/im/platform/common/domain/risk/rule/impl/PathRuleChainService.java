package lyzzcw.work.im.platform.common.domain.risk.rule.impl;

import cn.hutool.core.util.BooleanUtil;
import lyzzcw.work.im.platform.common.domain.enums.HttpCode;
import lyzzcw.work.im.platform.common.domain.risk.enums.RuleEnum;
import lyzzcw.work.im.platform.common.domain.risk.rule.RuleChainService;
import lyzzcw.work.im.platform.common.domain.risk.rule.base.BaseRuleChainService;
import lyzzcw.work.im.platform.common.domain.risk.window.SlidingWindowLimitService;
import lyzzcw.work.im.platform.common.domain.session.UserSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;


/**
 * 路径规则链服务
 *
 * @author lzy
 * @date 2024/01/03
 */
@Component
public class PathRuleChainService extends BaseRuleChainService implements RuleChainService {

    private final Logger logger = LoggerFactory.getLogger(PathRuleChainService.class);

    @Value("${bh.im.rule.pathRule.enabled}")
    private Boolean pathRuleEnabled;

    @Value("${bh.im.rule.pathRule.order}")
    private Integer pathRuleOrder;
    /**
     * 滑动窗口大小
     */
    @Value("${bh.im.rule.pathRule.windowsSize}")
    private Integer windowsSize;
    /**
     * 限流窗口的周期
     */
    @Value("${bh.im.rule.pathRule.windowPeriod}")
    private Long windowPeriod;

    @Autowired
    private SlidingWindowLimitService slidingWindowLimitService;

    @Override
    public HttpCode execute(HttpServletRequest request, Object handler) {
        if (BooleanUtil.isFalse(pathRuleEnabled)){
            return HttpCode.SUCCESS;
        }
        try{
            UserSession userSession = this.getUserSessionWithoutException(request);
            if (userSession == null){
                return HttpCode.SUCCESS;
            }
            windowsSize = windowsSize == null ? DEFAULT_WINDOWS_SIZE : windowsSize;
            windowPeriod = windowPeriod == null ? DEFAULT_WINDOWS_PERIOD : windowPeriod;
            String userPath = request.getRequestURI().concat(String.valueOf(userSession.getUserId())).concat(String.valueOf(userSession.getTerminal()));
            boolean result = slidingWindowLimitService.passThough(userPath, windowPeriod, windowsSize);
            return result ? HttpCode.SUCCESS : HttpCode.PROGRAM_ERROR;
        }catch (Exception e){
            logger.error("PathRuleChainService|资源限制异常|{}", e.getMessage());
            return HttpCode.PROGRAM_ERROR;
        }
    }

    @Override
    public int getOrder() {
        return pathRuleOrder == null ? RuleEnum.PATH.getCode() : pathRuleOrder;
    }

    @Override
    public String getServiceName() {
        return RuleEnum.PATH.getMesaage();
    }
}
