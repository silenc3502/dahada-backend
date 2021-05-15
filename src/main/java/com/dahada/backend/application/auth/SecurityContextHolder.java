package com.dahada.backend.application.auth;

public final class SecurityContextHolder {
    private static final ThreadLocal<SecurityContext> contextHolder = new ThreadLocal<>();
    private static final SecurityContext EMPTY_CONTEXT = new SecurityContext(null);

    public static void clear() {
        contextHolder.remove();
    }

    public static void setContext(SecurityContext context) {
        contextHolder.set(context);
    }

    public static SecurityContext getContext() {
        final SecurityContext ctx = contextHolder.get();
        if (ctx == null) {
            setContext(EMPTY_CONTEXT);
            return EMPTY_CONTEXT;
        }
        return ctx;
    }
}
