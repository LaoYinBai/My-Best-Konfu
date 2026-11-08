package com.ss.android.token;

import android.text.TextUtils;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class TokenUtils {
    public static String getTopDomain(String str) {
        try {
            Matcher matcher = Pattern.compile("[^\\.]+(\\.com\\.cn|\\.net\\.cn|\\.org\\.cn|\\.gov\\.cn|\\.com|\\.net|\\.cn|\\.org|\\.cc|\\.me|\\.tel|\\.mobi|\\.asia|\\.biz|\\.info|\\.name|\\.tv|\\.hk|\\.公司|\\.中国|\\.网络|\\.ly)").matcher(new URL(str).getHost().toLowerCase());
            if (matcher.find()) {
                return matcher.group();
            }
            return null;
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static boolean isInDomainList(String str, Set<String> set) {
        if (TextUtils.isEmpty(str) || set == null || set.size() == 0) {
            return false;
        }
        String topDomain = getTopDomain(str);
        if (TextUtils.isEmpty(topDomain)) {
            return false;
        }
        return set.contains(topDomain);
    }
}
