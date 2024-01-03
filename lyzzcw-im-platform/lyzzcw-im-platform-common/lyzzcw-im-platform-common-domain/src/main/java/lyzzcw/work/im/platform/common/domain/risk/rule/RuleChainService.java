package lyzzcw.work.im.platform.common.domain.risk.rule;



import lyzzcw.work.im.platform.common.domain.enums.HttpCode;

import javax.servlet.http.HttpServletRequest;


/**
 * 规则链服务
 *
 * @author lzy
 * @date 2024/01/03
 */
public interface RuleChainService {

    /**
     * 执行处理逻辑
     */
    HttpCode execute(HttpServletRequest request, Object handler);

    /**
     * 规则链中的每个规则排序
     */
    int getOrder();
}
