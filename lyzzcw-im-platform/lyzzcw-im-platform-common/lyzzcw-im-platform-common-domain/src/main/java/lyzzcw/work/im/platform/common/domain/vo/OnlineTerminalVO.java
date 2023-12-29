package lyzzcw.work.im.platform.common.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;


/**
 * 在线终端VO
 *
 * @author lzy
 * @date 2023/12/29
 */
@ApiModel("在线终端VO")
public class OnlineTerminalVO {

    @ApiModelProperty(value = "用户id")
    private Long userId;

    @ApiModelProperty(value = "在线终端类型")
    private List<Integer> terminals;

    public OnlineTerminalVO() {
    }

    public OnlineTerminalVO(Long userId, List<Integer> terminals) {
        this.userId = userId;
        this.terminals = terminals;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<Integer> getTerminals() {
        return terminals;
    }

    public void setTerminals(List<Integer> terminals) {
        this.terminals = terminals;
    }
}
