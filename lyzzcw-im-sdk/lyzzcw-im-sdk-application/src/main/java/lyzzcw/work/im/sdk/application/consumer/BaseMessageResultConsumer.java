package lyzzcw.work.im.sdk.application.consumer;

import com.alibaba.fastjson.JSONObject;
import lyzzcw.work.im.common.domain.constants.IMConstants;
import lyzzcw.work.im.common.domain.model.IMSendResult;


/**
 * 基本消息结果使用者
 *
 * @author lzy
 * @date 2023/12/27
 */
public class BaseMessageResultConsumer {

    /**
     * 解析数据
     */
    protected IMSendResult<?> getResultMessage(String msg){
        JSONObject jsonObject = JSONObject.parseObject(msg);
        String eventStr = jsonObject.getString(IMConstants.MSG_KEY);
        return JSONObject.parseObject(eventStr, IMSendResult.class);
    }
}
