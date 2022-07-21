package dev.kirin.toy.lottoweb.util;

import java.io.*;

public class IOUtils {
    public static void close(InputStream in) {
        try {
            in.close();
        } catch (IOException ignored) {}
    }

    public static void close(OutputStream out) {
        try {
            out.close();
        } catch (IOException ignored) {}
    }

    public static void close(Reader reader) {
        try {
            reader.close();
        } catch (IOException ignored) {}
    }

    public static void close(Writer writer) {
        try {
            writer.close();
        } catch (IOException ignored) {}
    }
}
