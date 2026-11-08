package com.alipay.android.phone.mrpc.core;

import android.text.format.Time;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public final class k {

    /* renamed from: a, reason: collision with root package name */
    private static final Pattern f2456a = Pattern.compile("([0-9]{1,2})[- ]([A-Za-z]{3,9})[- ]([0-9]{2,4})[ ]([0-9]{1,2}:[0-9][0-9]:[0-9][0-9])");

    /* renamed from: b, reason: collision with root package name */
    private static final Pattern f2457b = Pattern.compile("[ ]([A-Za-z]{3,9})[ ]+([0-9]{1,2})[ ]([0-9]{1,2}:[0-9][0-9]:[0-9][0-9])[ ]([0-9]{2,4})");

    private static class a {

        /* renamed from: a, reason: collision with root package name */
        int f2458a;

        /* renamed from: b, reason: collision with root package name */
        int f2459b;

        /* renamed from: c, reason: collision with root package name */
        int f2460c;

        a(int i, int i2, int i3) {
            this.f2458a = i;
            this.f2459b = i2;
            this.f2460c = i3;
        }
    }

    public static long a(String str) {
        int iD;
        int i;
        int i2;
        a aVarE;
        int i3;
        int i4;
        int i5;
        Matcher matcher = f2456a.matcher(str);
        if (matcher.find()) {
            int iB = b(matcher.group(1));
            int iC = c(matcher.group(2));
            int iD2 = d(matcher.group(3));
            aVarE = e(matcher.group(4));
            i = iC;
            i2 = iB;
            iD = iD2;
        } else {
            Matcher matcher2 = f2457b.matcher(str);
            if (!matcher2.find()) {
                throw new IllegalArgumentException();
            }
            int iC2 = c(matcher2.group(1));
            int iB2 = b(matcher2.group(2));
            a aVarE2 = e(matcher2.group(3));
            iD = d(matcher2.group(4));
            i = iC2;
            i2 = iB2;
            aVarE = aVarE2;
        }
        if (iD >= 2038) {
            i4 = 1;
            i5 = 0;
            i3 = 2038;
        } else {
            i3 = iD;
            i4 = i2;
            i5 = i;
        }
        Time time = new Time("UTC");
        time.set(aVarE.f2460c, aVarE.f2459b, aVarE.f2458a, i4, i5, i3);
        return time.toMillis(false);
    }

    private static int b(String str) {
        return str.length() == 2 ? ((str.charAt(0) - '0') * 10) + (str.charAt(1) - '0') : str.charAt(0) - '0';
    }

    private static int c(String str) {
        int lowerCase = ((Character.toLowerCase(str.charAt(0)) + Character.toLowerCase(str.charAt(1))) + Character.toLowerCase(str.charAt(2))) - 291;
        if (lowerCase == 22) {
            return 0;
        }
        if (lowerCase == 26) {
            return 7;
        }
        if (lowerCase == 29) {
            return 2;
        }
        if (lowerCase == 32) {
            return 3;
        }
        if (lowerCase == 40) {
            return 6;
        }
        if (lowerCase == 42) {
            return 5;
        }
        if (lowerCase == 48) {
            return 10;
        }
        switch (lowerCase) {
            case 9:
                return 11;
            case 10:
                return 1;
            default:
                switch (lowerCase) {
                    case 35:
                        return 9;
                    case 36:
                        return 4;
                    case 37:
                        return 8;
                    default:
                        throw new IllegalArgumentException();
                }
        }
    }

    private static int d(String str) {
        if (str.length() == 2) {
            int iCharAt = ((str.charAt(0) - '0') * 10) + (str.charAt(1) - '0');
            return iCharAt >= 70 ? iCharAt + 1900 : iCharAt + 2000;
        }
        if (str.length() == 3) {
            return ((str.charAt(0) - '0') * 100) + ((str.charAt(1) - '0') * 10) + (str.charAt(2) - '0') + 1900;
        }
        if (str.length() == 4) {
            return ((str.charAt(0) - '0') * 1000) + ((str.charAt(1) - '0') * 100) + ((str.charAt(2) - '0') * 10) + (str.charAt(3) - '0');
        }
        return 1970;
    }

    private static a e(String str) {
        int i;
        int iCharAt = str.charAt(0) - '0';
        if (str.charAt(1) != ':') {
            i = 2;
            iCharAt = (iCharAt * 10) + (str.charAt(1) - '0');
        } else {
            i = 1;
        }
        int i2 = i + 1 + 1 + 1 + 1;
        return new a(iCharAt, ((str.charAt(r2) - '0') * 10) + (str.charAt(r3) - '0'), ((str.charAt(i2) - '0') * 10) + (str.charAt(i2 + 1) - '0'));
    }
}
