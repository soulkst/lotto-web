package dev.kirin.toy.lottoweb.util;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileUtils {
    public static String readAsString(File file) {
        return readAsString(file, StandardCharsets.UTF_8);
    }

    public static String readAsString(String filePath) {
        return readAsString(filePath, StandardCharsets.UTF_8);
    }

    public static String readAsString(File file, Charset charset) {
        return new String(read(file), charset);
    }

    public static String readAsString(String filePath, Charset charset) {
        return new String(read(filePath), charset);
    }

    public static byte[] read(String filePath) {
        return read(Paths.get(filePath));
    }

    public static byte[] read(File file) {
        return read(Paths.get(file.getAbsolutePath()));
    }

    public static byte[] read(Path path) {
        try {
            return Files.readAllBytes(path);
        } catch (IOException e) {
            throw new FileUtilException(e);
        }
    }

    public static class FileUtilException extends RuntimeException {
        public FileUtilException(Throwable cause) {
            super(cause);
        }
    }
}
