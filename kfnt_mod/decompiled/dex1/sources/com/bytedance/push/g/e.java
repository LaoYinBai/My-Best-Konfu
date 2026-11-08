package com.bytedance.push.g;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Locale;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class e {

    /* renamed from: a, reason: collision with root package name */
    private Hashtable f4823a = new Hashtable();

    public e() {
    }

    public e(String str) throws f {
        a(str);
    }

    protected void a(String str) throws f {
        int length;
        int i;
        String strSubstring;
        if (str != null && (length = str.length()) > 0) {
            int iA = a(str, 0);
            while (iA < length && str.charAt(iA) == ';') {
                int iA2 = a(str, iA + 1);
                if (iA2 >= length) {
                    return;
                }
                int i2 = iA2;
                while (i2 < length && a(str.charAt(i2))) {
                    i2++;
                }
                String lowerCase = str.substring(iA2, i2).toLowerCase(Locale.ENGLISH);
                int iA3 = a(str, i2);
                if (iA3 >= length || str.charAt(iA3) != '=') {
                    throw new f("Couldn't find the '=' that separates a parameter name from its value.");
                }
                int iA4 = a(str, iA3 + 1);
                if (iA4 >= length) {
                    throw new f("Couldn't find a value for parameter named " + lowerCase);
                }
                char cCharAt = str.charAt(iA4);
                if (cCharAt == '\"') {
                    int i3 = iA4 + 1;
                    if (i3 >= length) {
                        throw new f("Encountered unterminated quoted parameter value.");
                    }
                    char cCharAt2 = cCharAt;
                    int i4 = i3;
                    while (i4 < length) {
                        cCharAt2 = str.charAt(i4);
                        if (cCharAt2 == '\"') {
                            break;
                        }
                        if (cCharAt2 == '\\') {
                            i4++;
                        }
                        i4++;
                    }
                    if (cCharAt2 != '\"') {
                        throw new f("Encountered unterminated quoted parameter value.");
                    }
                    strSubstring = d(str.substring(i3, i4));
                    i = i4 + 1;
                } else if (a(cCharAt)) {
                    i = iA4;
                    while (i < length && a(str.charAt(i))) {
                        i++;
                    }
                    strSubstring = str.substring(iA4, i);
                } else {
                    throw new f("Unexpected character encountered at index " + iA4);
                }
                this.f4823a.put(lowerCase, strSubstring);
                iA = a(str, i);
            }
            if (iA < length) {
                throw new f("More characters encountered in input than expected.");
            }
        }
    }

    public String b(String str) {
        return (String) this.f4823a.get(str.trim().toLowerCase(Locale.ENGLISH));
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.ensureCapacity(this.f4823a.size() * 16);
        Enumeration enumerationKeys = this.f4823a.keys();
        while (enumerationKeys.hasMoreElements()) {
            String str = (String) enumerationKeys.nextElement();
            stringBuffer.append("; ");
            stringBuffer.append(str);
            stringBuffer.append('=');
            stringBuffer.append(c((String) this.f4823a.get(str)));
        }
        return stringBuffer.toString();
    }

    private static boolean a(char c2) {
        return c2 > ' ' && c2 < 127 && "()<>@,;:/[]?=\\\"".indexOf(c2) < 0;
    }

    private static int a(String str, int i) {
        int length = str.length();
        while (i < length && Character.isWhitespace(str.charAt(i))) {
            i++;
        }
        return i;
    }

    private static String c(String str) {
        int length = str.length();
        boolean z = false;
        for (int i = 0; i < length && !z; i++) {
            z = !a(str.charAt(i));
        }
        if (!z) {
            return str;
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.ensureCapacity((int) (length * 1.5d));
        stringBuffer.append('\"');
        for (int i2 = 0; i2 < length; i2++) {
            char cCharAt = str.charAt(i2);
            if (cCharAt == '\\' || cCharAt == '\"') {
                stringBuffer.append('\\');
            }
            stringBuffer.append(cCharAt);
        }
        stringBuffer.append('\"');
        return stringBuffer.toString();
    }

    private static String d(String str) {
        int length = str.length();
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.ensureCapacity(length);
        boolean z = false;
        for (int i = 0; i < length; i++) {
            char cCharAt = str.charAt(i);
            if (!z && cCharAt != '\\') {
                stringBuffer.append(cCharAt);
            } else if (z) {
                stringBuffer.append(cCharAt);
                z = false;
            } else {
                z = true;
            }
        }
        return stringBuffer.toString();
    }
}
