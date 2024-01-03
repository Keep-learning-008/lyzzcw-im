package lyzzcw.work.im.platform.common.domain.interceptor.base;

import cn.hutool.core.collection.CollectionUtil;
import lyzzcw.work.im.platform.common.domain.risk.rule.RuleChainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


/**
 * 基地拦截器
 *
 * @author lzy
 * @date 2024/01/03
 */
public abstract class BaseInterceptor implements HandlerInterceptor {

    @Autowired
    private List<RuleChainService> ruleChainServices;

    protected List<RuleChainService> getRuleChainServices(){
        if (CollectionUtil.isEmpty(ruleChainServices)){
            return Collections.emptyList();
        }
        return ruleChainServices.stream().sorted(Comparator.comparing(RuleChainService::getOrder)).collect(Collectors.toList());
    }
}
