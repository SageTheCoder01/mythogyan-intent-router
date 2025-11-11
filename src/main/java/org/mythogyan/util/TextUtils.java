package org.mythogyan.util;

import org.apache.commons.lang3.StringUtils;

public class TextUtils {
    public static String normalize(String text, String lang) {
        if (text == null) return "";
        String t = text.trim().toLowerCase();
        t = t.replaceAll("[^\\p{L}\\p{N}\\s]", " ");
        return StringUtils.normalizeSpace(t);
    }
}
