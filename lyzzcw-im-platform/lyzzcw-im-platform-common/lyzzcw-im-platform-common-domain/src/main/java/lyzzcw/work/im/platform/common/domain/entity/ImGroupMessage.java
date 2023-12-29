package lyzzcw.work.im.platform.common.domain.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * create by lzy
 * im_group_message
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImGroupMessage {
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
     * 发送用户id
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long sendId;

    /**
     * 发送用户昵称
     */
    private String sendNickName;

    /**
     * 被@的用户id列表，逗号分隔
     */
    private String atUserIds;

    /**
     * 消息类型 0:文字 1:图片 2:文件 3:语音 10:系统提示
     */
    private Boolean type;

    /**
     * 状态 0:正常  2:撤回
     */
    private Boolean status;

    /**
     * 发送时间
     */
    private LocalDateTime sendTime;

    /**
     * 发送内容
     */
    private String content;
}