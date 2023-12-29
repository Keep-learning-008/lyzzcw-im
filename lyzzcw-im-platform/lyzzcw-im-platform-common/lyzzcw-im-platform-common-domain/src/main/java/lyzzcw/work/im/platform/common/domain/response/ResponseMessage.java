package lyzzcw.work.im.platform.common.domain.response;


/**
 * 响应消息
 *
 * @author lzy
 * @date 2023/12/29
 */
public class ResponseMessage<T> {

    private Integer code;
    private String message;
    private T data;

    public ResponseMessage() {
    }

    public ResponseMessage(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public ResponseMessage(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
