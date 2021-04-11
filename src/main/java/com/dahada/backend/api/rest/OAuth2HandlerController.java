package com.dahada.backend.api.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequestMapping("/test")
@RestController
public class OAuth2HandlerController {

    @GetMapping
    public String test() {
        log.info("info: {}", SecurityContextHolder.getContext());
        return "abc";
    }
}
