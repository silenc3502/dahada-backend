package com.dahada.backend.application.auth.utils;

import org.springframework.lang.Nullable;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Objects;

public final class CookieUtil {

    public static void invalidate(HttpServletRequest request, HttpServletResponse response, List<String> names) {
        names.forEach(name -> invalidate(request, response, name));
    }

    public static void invalidate(HttpServletRequest request, HttpServletResponse response, String name) {
        Objects.requireNonNull(request, "request should be not null");
        Objects.requireNonNull(response, "response should be not null");
        Objects.requireNonNull(name, "name should be not null");
        final Cookie cookie = getCookie(request, name);
        invalidate(response, cookie);
    }

    public static void invalidate(HttpServletResponse response, Cookie cookie) {
        if (cookie != null) {
            cookie.setMaxAge(0);
            response.addCookie(cookie);
        }
    }

    public static boolean has(HttpServletRequest request, String name) {
        return getCookie(request, name) != null;
    }

    public static boolean hasNot(HttpServletRequest request, String name) {
        return !has(request, name);
    }

    @Nullable
    public static Cookie getCookie(HttpServletRequest request, String name) {
        Objects.requireNonNull(request, "request should be not null");
        Objects.requireNonNull(name, "name should be not null");
        final Cookie[] cookies = request.getCookies();
        if (cookies == null) {
            return null;
        }

        for (Cookie cookie : cookies) {
            if (name.equals(cookie.getName())) {
                return cookie;
            }
        }
        return null;
    }
}
