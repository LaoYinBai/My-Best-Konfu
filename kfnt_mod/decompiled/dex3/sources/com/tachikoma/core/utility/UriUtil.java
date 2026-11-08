package com.tachikoma.core.utility;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class UriUtil {
    public static final String ASSET_PREFIX = "asset://";
    public static final String BUNDLE_PREFIX = "bundle://";
    public static final String FILE_PREFIX = "file://";
    public static final String HTTPS_PREFIX = "https://";
    public static final String HTTP_PREFIX = "http://";

    public static String getMappedPath(String str, String str2) {
        if (str == null || str.length() <= 0 || !str.startsWith(str2)) {
            return str;
        }
        return str.substring(str2 != null ? str2.length() : 0);
    }

    public static String getResName(String str) {
        int iLastIndexOf;
        String mappedPath = getMappedPath(str, ASSET_PREFIX);
        return (mappedPath == null || mappedPath.length() <= 0 || -1 == (iLastIndexOf = mappedPath.lastIndexOf("."))) ? mappedPath : mappedPath.substring(0, iLastIndexOf);
    }
}
