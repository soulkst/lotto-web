package dev.kirin.toy.lottoweb.util;

import lombok.extern.slf4j.Slf4j;

import java.util.Map;

@Slf4j
public class MapUtils {
    private static final String PATH_REGEX = "\\.";

    public static <T> T findByPath(Map<String, Object> map, String path) {
        String[] paths = path.split(PATH_REGEX);
        Object result = map;
        for(String pathItem : paths) {
            Map<String, Object> resultMap = (Map<String, Object>) result;
            if(resultMap.containsKey(pathItem)) {
                result = resultMap.get(pathItem);
            } else {
                log.warn("Cannot found item '{}'. path = {}", pathItem, path);
                return null;
            }
        }
        return (T) result;
    }
}
