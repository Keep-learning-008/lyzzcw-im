package lyzzcw.work.im.platform.common.domain.risk.rule.impl;

import cn.hutool.core.util.BooleanUtil;
import lyzzcw.work.im.platform.common.domain.enums.HttpCode;
import lyzzcw.work.im.platform.common.domain.risk.enums.RuleEnum;
import lyzzcw.work.im.platform.common.domain.risk.rule.RuleChainService;
import lyzzcw.work.im.platform.common.domain.risk.rule.base.BaseRuleChainService;
import lyzzcw.work.im.platform.common.domain.risk.window.SlidingWindowLimitService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;


/**
 * IPRuil链服务
 *
 * @author lzy
 * @date 2024/01/03
 */
@Component
public class IPRuleChainService extends BaseRuleChainService implements RuleChainService {
    private final Logger logger = LoggerFactory.getLogger(XssRuleChainService.class);

    @Value("${bh.im.rule.ipRule.enabled}")
    private Boolean ipRuleEnabled;

    @Value("${bh.im.rule.ipRule.order}")
    private Integer ipRuleOrder;
    /**
     * 滑动窗口大小
     */
    @Value("${bh.im.rule.ipRule.windowsSize}")
    private Integer windowsSize;
    /**
     * 限流窗口的周期
     */
    @Value("${bh.im.rule.ipRule.windowPeriod}")
    private Long windowPeriod;

    @Autowired
    private SlidingWindowLimitService slidingWindowLimitService;

    @Override
    public HttpCode execute(HttpServletRequest request, Object handler) {
        if (BooleanUtil.isFalse(ipRuleEnabled)){
            return HttpCode.SUCCESS;
        }
        try{
            windowsSize = windowsSize == null ? DEFAULT_WINDOWS_SIZE : windowsSize;
            windowPeriod = windowPeriod == null ? DEFAULT_WINDOWS_PERIOD : windowPeriod;
            String ip = this.getIp(request);
            boolean result = slidingWindowLimitService.passThough(ip, windowPeriod, windowsSize);
            return result ? HttpCode.SUCCESS : HttpCode.PROGRAM_ERROR;
        }catch (Exception e){
            logger.error("IPRuleChainService|IP限制异常|{}", e.getMessage());
            return HttpCode.PROGRAM_ERROR;
        }
    }

    @Override
    public int getOrder() {
        return ipRuleOrder == null ? RuleEnum.IP.getCode() : ipRuleOrder;
    }

    @Override
    public String getServiceName() {
        return RuleEnum.IP.getMesaage();
    }
}
