package lyzzcw.work.im.platform.common.domain.exception;


import lyzzcw.work.im.platform.common.domain.enums.HttpCode;

/**
 * IM 异常
 *
 * @author lzy
 * @date 2023/12/29
 */
public class IMException extends RuntimeException{

    private static final long serialVersionUID = -2571805513813090624L;

    private Integer code;
    private String message;

    public IMException(Integer code, String message){
        this.code = code;
        this.message = message;
    }

    public IMException(HttpCode httpCode, String message){
        this.code = httpCode.getCode();
        this.message = message;
    }

    public IMException(HttpCode httpCode){
        this.code = httpCode.getCode();
        this.message = httpCode.getMsg();
    }

    public IMException(String message){
        this.code = HttpCode.PROGRAM_ERROR.getCode();
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
