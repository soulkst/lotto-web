package dev.kirin.toy.lottoweb.util;

public class ObjectUtils {
    public static <T> T copyObject(T src) {
        return (T) JsonUtils.convert(src, src.getClass());
    }
}
