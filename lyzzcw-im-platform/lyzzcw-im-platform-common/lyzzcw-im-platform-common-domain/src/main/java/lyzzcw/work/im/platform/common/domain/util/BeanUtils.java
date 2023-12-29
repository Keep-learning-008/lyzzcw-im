package lyzzcw.work.im.platform.common.domain.util;

import org.springframework.util.ReflectionUtils;


/**
 * Bean 实用程序
 *
 * @author lzy
 * @date 2023/12/29
 */
public class BeanUtils {

    private static void handleReflectionException(Exception e) {
        ReflectionUtils.handleReflectionException(e);
    }

    /**
     * 属性拷贝
     * @param orig  源对象
     * @param destClass 目标
     * @return T
     */
    public static <T> T copyProperties(Object orig, Class<T> destClass) {
        try {
            if(orig == null) {
                return null;
            }
            Object target = destClass.newInstance();
            copyProperties(orig, target);
            return (T) target;
        }catch(Exception e) {
            handleReflectionException(e);
            return null;
        }
    }


    public static void copyProperties(Object orig, Object dest) {
        try {
            org.springframework.beans.BeanUtils.copyProperties(orig, dest);
        } catch (Exception e) {
            handleReflectionException(e);
        }
    }
}
