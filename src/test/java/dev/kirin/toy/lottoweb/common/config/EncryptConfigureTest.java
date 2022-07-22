package dev.kirin.toy.lottoweb.common.config;

import org.jasypt.encryption.StringEncryptor;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.ResourceLoader;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.spy;

@DisplayName("Jasypt configure Test")
public class EncryptConfigureTest {
    private static StringEncryptor encryptor;

    @BeforeAll
    static void beforeAll() throws IOException {
        AppProperties appProperties = new AppProperties();
        appProperties.setKeyFile("classpath:test.key");

        ResourceLoader resourceLoader = new DefaultResourceLoader();
        encryptor = spy(EncryptConfigure.class)
                .stringEncryptor(resourceLoader, appProperties);
    }

    @DisplayName("기본 패스워드 암/복호화 테스트")
    @Test
    void passwordEncrypt_AndMatched() {
        String password = "test-password";
        String encrypted = encryptor.encrypt(password);
        System.out.println(encrypted);
        assertEquals(password, encryptor.decrypt(encrypted));
    }
}
