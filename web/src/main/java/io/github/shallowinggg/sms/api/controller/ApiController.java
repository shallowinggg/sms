package io.github.shallowinggg.sms.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ding shimin
 */
@RequestMapping("/api")
@RestController
public class ApiController {

    @GetMapping("/heartbeat")
    public String heartbeat() {
        return "ok";
    }
}
