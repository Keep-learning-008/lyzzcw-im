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
 * im_friend
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImFriend {
    /**
     * id
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    /**
     * 用户id
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long userId;

    /**
     * 好友id
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long friendId;

    /**
     * 好友昵称
     */
    private String friendNickName;

    /**
     * 好友头像
     */
    private String friendHeadImage;

    /**
     * 创建时间
     */
    private LocalDateTime createdTime;
}