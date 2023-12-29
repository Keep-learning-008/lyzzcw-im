package lyzzcw.work.im.platform.common.domain.exception;


import lyzzcw.work.im.platform.common.domain.enums.HttpCode;
import lyzzcw.work.im.platform.common.domain.response.ResponseMessage;
import lyzzcw.work.im.platform.common.domain.response.ResponseMessageFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * imexception 处理程序
 *
 * @author lzy
 * @date 2023/12/29
 */
@ControllerAdvice
public class IMExceptionHandler {

    @ResponseBody
    @ExceptionHandler(IMException.class)
    public ResponseMessage<String> handleIMException(IMException e) {
        return ResponseMessageFactory.getErrorResponseMessage(e.getCode(), e.getMessage());
    }

    @ResponseBody
    @ExceptionHandler(Exception.class)
    public ResponseMessage<String> handleException(Exception e) {
        return ResponseMessageFactory.getErrorResponseMessage(HttpCode.PROGRAM_ERROR);
    }
}
