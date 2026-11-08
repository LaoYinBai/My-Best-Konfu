package com.tachikoma.core.utility;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class StringUtil {
    public static String uppercaseFirstChar(String str) {
        if (str.length() <= 0 || Character.isUpperCase(str.charAt(0))) {
            return str;
        }
        char[] charArray = str.toCharArray();
        charArray[0] = (char) (charArray[0] - ' ');
        return String.valueOf(charArray);
    }
}
