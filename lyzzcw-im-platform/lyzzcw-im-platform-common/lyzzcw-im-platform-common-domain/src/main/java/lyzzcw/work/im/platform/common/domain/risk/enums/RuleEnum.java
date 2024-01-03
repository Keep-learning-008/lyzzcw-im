package lyzzcw.work.im.platform.common.domain.risk.enums;


/**
 * 规则枚举
 *
 * @author lzy
 * @date 2024/01/03
 */
public enum RuleEnum {

    XSS(0, "XSS安全服务"),
    IP(1, "IP安全服务"),
    PATH(2, "资源安全服务"),
    AUTH(10, "账号安全服务");

    private final Integer code;
    private final String mesaage;

    RuleEnum(Integer code, String mesaage) {
        this.code = code;
        this.mesaage = mesaage;
    }

    public Integer getCode() {
        return code;
    }

    public String getMesaage() {
        return mesaage;
    }
}
