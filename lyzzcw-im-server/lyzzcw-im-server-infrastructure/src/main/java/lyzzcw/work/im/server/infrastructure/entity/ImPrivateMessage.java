package lyzzcw.work.im.server.infrastructure.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 *
 * create by lzy
 * im_private_message
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImPrivateMessage {
    /**
     * id
     */
    private Long id;

    /**
     * 发送用户id
     */
    private Long sendId;

    /**
     * 接收用户id
     */
    private Long recvId;

    /**
     * 消息类型 0:文字 1:图片 2:文件 3:语音 10:系统提示
     */
    private Boolean type;

    /**
     * 状态 0:未读 1:已读 2:撤回
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