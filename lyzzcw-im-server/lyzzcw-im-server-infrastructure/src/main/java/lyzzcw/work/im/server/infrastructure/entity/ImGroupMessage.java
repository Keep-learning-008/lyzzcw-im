package lyzzcw.work.im.server.infrastructure.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

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
    private Long id;

    /**
     * 群id
     */
    private Long groupId;

    /**
     * 发送用户id
     */
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