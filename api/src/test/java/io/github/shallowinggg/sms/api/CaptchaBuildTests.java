package io.github.shallowinggg.sms.api;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

/**
 * @author ding shimin
 */
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
@Sql(statements = "DROP TABLE verification_configs")
@Sql("classpath:db/schema.sql")
@Sql("init_verification_configs.sql")
public class CaptchaBuildTests {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    StringRedisTemplate redisTemplate;

    @Test
    public void shouldBuildCaptcha() throws Exception {

        mockMvc.perform(post("/api/captcha")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("configId", "1"))
                .andExpect(jsonPath("$.token").isNotEmpty())
                .andExpect(jsonPath("$.captcha.format").value("image"));

        String key = "";
        Assertions.assertNotNull(redisTemplate.opsForValue().get(key));
    }

    @Test
    public void shouldNotBuildCaptchaWhenConfigNotOnline() throws Exception {
        mockMvc.perform(post("/api/captcha")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("configId", "2"))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }
}
