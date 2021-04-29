package com.dahada.backend.api.page;

import com.dahada.backend.application.aspect.RequestTracking;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestTracking
@RequestMapping("/")
@RequiredArgsConstructor
public class MainFormController {
}
