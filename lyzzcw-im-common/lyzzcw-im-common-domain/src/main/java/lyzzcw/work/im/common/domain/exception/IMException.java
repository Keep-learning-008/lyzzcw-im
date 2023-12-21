package lyzzcw.work.im.common.domain.exception;


/**
 * IM 自定义异常
 *
 * @author lzy
 * @date 2023/12/21
 */
public class IMException extends RuntimeException{

    private Integer code;

    public IMException(String message) {
        super(message);
    }

    public IMException(Integer code, String messgae){
        super(messgae);
        this.code = code;
    }


    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
