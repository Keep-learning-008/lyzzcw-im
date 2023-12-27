package lyzzcw.work.im.platform.domain.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * create by lzy
 * im_group_member
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImGroupMember {
    /**
     * id
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    /**
     * 群id
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long groupId;

    /**
     * 用户id
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long userId;

    /**
     * 组内显示名称
     */
    private String aliasName;

    /**
     * 用户头像
     */
    private String headImage;

    /**
     * 备注
     */
    private String remark;

    /**
     * 是否已退出
     */
    private Boolean quit;

    /**
     * 创建时间
     */
    private LocalDateTime createdTime;
}