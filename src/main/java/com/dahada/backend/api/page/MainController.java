package com.dahada.backend.api.page;

import com.dahada.backend.application.aspect.RequestTracking;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author hyeyoom
 */
@Slf4j
@Controller
@RequestMapping("/")
@RequestTracking
public class MainController {

    @GetMapping
    public String index() {
        return "index";
    }
}
