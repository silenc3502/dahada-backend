package com.dahada.backend.application.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Aspect
@Component
public class LoggingAspect {

    @Around("@within(RequestTracking) || @annotation(RequestTracking)")
    public Object insertLoggngInfo(ProceedingJoinPoint pjp) throws Throwable {
        try {
            final HttpServletRequest request
                    = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
            final String ip
                    = Optional.ofNullable(request.getHeader("X-FORWARDED-FOR")).orElse(request.getRemoteAddr());
            MDC.put("ipaddr", ip);
            MDC.put("tracking", generateShortUUID());
        } catch (Throwable t) {
            MDC.clear();
        }
        return pjp.proceed();
    }

    private String generateShortUUID() {
        final UUID uuid = UUID.randomUUID();
        final long l = ByteBuffer.wrap(uuid.toString().getBytes(StandardCharsets.UTF_8)).getLong();
        return Long.toString(l, Character.MAX_RADIX);
    }

}
