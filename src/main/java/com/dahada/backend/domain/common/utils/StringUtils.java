package com.dahada.backend.domain.common.utils;

public final class StringUtils {
    public static boolean hasText(CharSequence sequence) {
        return sequence != null && sequence.length() != 0;
    }

    public static boolean hasText(CharSequence sequence, EmptyLambda function) {
        final boolean ret = hasText(sequence);
        if (!ret) {
            function.apply();
        }
        return ret;
    }
}
