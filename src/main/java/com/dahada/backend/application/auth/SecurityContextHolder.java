package com.dahada.backend.application.auth;

public final class SecurityContextHolder {
    private static final ThreadLocal<SecurityContext> contextHolder = new ThreadLocal<>();

    public static void clear() {
        contextHolder.remove();
    }

    public static void setContext(SecurityContext context) {
        contextHolder.set(context);
    }

    public static SecurityContext getContext() {
        final SecurityContext ctx = contextHolder.get();
        if (ctx == null) {
            final SecurityContext emptyContext = new SecurityContext(null);
            setContext(emptyContext);
            return emptyContext;
        }
        return ctx;
    }
}
