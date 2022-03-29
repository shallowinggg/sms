package io.github.shallowinggg.sms.api;

import io.github.shallowinggg.sms.TestContext;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author ding shimin
 */
@SpringBootTest
@ContextConfiguration(classes = TestContext.class)
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
@Sql(statements = "DROP TABLE verification_configs")
@Sql("classpath:db/schema.sql")
@Sql("init_verification_configs.sql")
public class VerificationConfigQueryTests {

    @Autowired
    MockMvc mockMvc;

    @Test
    public void shouldGetTheGivenVerificationConfig() throws Exception {

        mockMvc.perform(get("/api/verificationConfig/{id}", 1))
                .andExpect(jsonPath("$.name").value("test"))
                .andExpect(jsonPath("$.needCaptcha").value(true));
    }

    @Test
    public void shouldNotFoundWhenConfigIsNotOnline() throws Exception {

        mockMvc.perform(get("/api/verificationConfig/{id}", 2))
                .andExpect(status().isNotFound());
        mockMvc.perform(get("/api/verificationConfig/{id}", 3))
                .andExpect(status().isNotFound());
    }
}
