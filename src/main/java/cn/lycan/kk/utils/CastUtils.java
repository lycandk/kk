package cn.lycan.kk.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Makkapakka
 * @date 2022-6-14
 * @package_name cn.lycan.kk.utils
 * @description 从缓存拿回来的是 Object ，我们需要编写一个方法把它转换为 List
 */
public class CastUtils {
    public static <T> List<T> objectConvertToList(Object obj, Class<T> clazz) {
        List<T> result = new ArrayList<T>();
        if (obj instanceof List<?>) {
            for (Object o : (List<?>) obj) {
                result.add(clazz.cast(o));
            }
            return result;
        }
        return null;
    }
}
