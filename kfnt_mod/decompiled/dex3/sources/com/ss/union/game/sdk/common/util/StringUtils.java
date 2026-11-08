package com.ss.union.game.sdk.common.util;

import android.text.TextUtils;
import com.tachikoma.core.utility.FileUtil;
import com.xiaomi.mipush.sdk.Constants;
import java.io.UnsupportedEncodingException;
import java.lang.Character;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class StringUtils {
    private static final long a = 1024;
    private static final long b = 1048576;
    private static final long c = 1073741824;
    private static final long d = 1099511627776L;

    public static boolean isEmpty(String str) {
        return str == null || str.length() == 0;
    }

    public static boolean equal(String str, String str2) {
        return (isEmpty(str) && isEmpty(str2)) || (str != null && str.equals(str2));
    }

    public static void escapeEcmaScript(StringBuilder sb, String str, boolean z) {
        if (z) {
            sb.append("\"");
        }
        int length = str.length();
        for (int i = 0; i < length; i++) {
            char cCharAt = str.charAt(i);
            switch (cCharAt) {
                case '\b':
                    sb.append("\\b");
                    break;
                case '\t':
                    sb.append("\\t");
                    break;
                case '\n':
                    sb.append("\\n");
                    break;
                case '\f':
                    sb.append("\\f");
                    break;
                case '\r':
                    sb.append("\\r");
                    break;
                case '\"':
                case '\\':
                    sb.append(FileUtil.WINDOWS_SEPARATOR);
                    sb.append(cCharAt);
                    break;
                case 8232:
                case 8233:
                    sb.append(String.format("\\u%04x", Integer.valueOf(cCharAt)));
                    break;
                default:
                    if (cCharAt <= 31) {
                        sb.append(String.format("\\u%04x", Integer.valueOf(cCharAt)));
                        break;
                    } else {
                        sb.append(cCharAt);
                        break;
                    }
            }
        }
        if (z) {
            sb.append("\"");
        }
    }

    public static String removeBlank(String str) {
        if (isEmpty(str)) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) >= '0' && str.charAt(i) <= '9') {
                sb.append(str.charAt(i));
            }
        }
        return sb.toString();
    }

    public static HashMap<String, String> parseResponse(String str) {
        String[] strArrSplit = str.substring(str.indexOf(123) + 1, str.lastIndexOf(125)).split(Constants.ACCEPT_TIME_SEPARATOR_SP);
        for (int i = 0; i < strArrSplit.length; i++) {
            strArrSplit[i] = strArrSplit[i].trim();
        }
        HashMap<String, String> map = new HashMap<>();
        for (String str2 : strArrSplit) {
            String[] strArrSplit2 = str2.split(Constants.COLON_SEPARATOR, 2);
            map.put(clean(strArrSplit2[0]), clean(strArrSplit2[1]));
        }
        return map;
    }

    public static String clean(String str) {
        int iIndexOf = str.indexOf(34);
        int iLastIndexOf = str.lastIndexOf(34);
        return (iIndexOf < 0 || iLastIndexOf <= iIndexOf) ? str : str.substring(iIndexOf + 1, iLastIndexOf).trim();
    }

    public static String getShortDateTime(String str) {
        int iIndexOf = str.indexOf(45) + 1;
        int iLastIndexOf = str.lastIndexOf(58);
        return (iIndexOf <= 0 || iLastIndexOf <= 0) ? str : str.substring(iIndexOf, iLastIndexOf);
    }

    public static String toSBC(String str) {
        char[] charArray = str.toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            if (charArray[i] == ' ') {
                charArray[i] = 12288;
            } else if (charArray[i] < 127 && charArray[i] > ' ') {
                charArray[i] = (char) (charArray[i] + 65248);
            }
        }
        return new String(charArray);
    }

    public static String toDBC(String str) {
        char[] charArray = str.toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            if (a(charArray[i])) {
                if (charArray[i] == 12288) {
                    charArray[i] = ' ';
                } else if (charArray[i] > 65280 && charArray[i] < 65375) {
                    charArray[i] = (char) (charArray[i] - 65248);
                }
            }
        }
        return new String(charArray);
    }

    private static boolean a(char c2) {
        Character.UnicodeBlock unicodeBlockOf = Character.UnicodeBlock.of(c2);
        return unicodeBlockOf == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS || unicodeBlockOf == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS || unicodeBlockOf == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A || unicodeBlockOf == Character.UnicodeBlock.GENERAL_PUNCTUATION || unicodeBlockOf == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION || unicodeBlockOf == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS;
    }

    public static int getStrCharactersLength(String str) {
        if (TextUtils.isEmpty(str)) {
            return -1;
        }
        int length = str.length();
        int i = length;
        for (int i2 = 0; i2 < length; i2++) {
            if (a(str.charAt(i2))) {
                i++;
            }
        }
        return i;
    }

    public static int getIndexByMaxCharLimit(String str, int i) {
        if (TextUtils.isEmpty(str)) {
            return -1;
        }
        int length = str.length();
        int i2 = 0;
        for (int i3 = 0; i3 < length; i3++) {
            i2 = a(str.charAt(i3)) ? i2 + 2 : i2 + 1;
            if (i2 > i) {
                return i3;
            }
        }
        return length - 1;
    }

    public static boolean containChinese(String str) {
        if (isEmpty(str)) {
            return false;
        }
        for (int i = 0; i < str.length(); i++) {
            if (a(str.charAt(i))) {
                return true;
            }
        }
        return false;
    }

    public static String handleAdClickTrackUrl(String str) {
        String strReplace;
        if (isEmpty(str)) {
            return str;
        }
        try {
            strReplace = str.replace("[ss_random]", String.valueOf(new Random().nextLong()));
        } catch (Exception e) {
            e = e;
        }
        try {
            return strReplace.replace("[ss_timestamp]", String.valueOf(System.currentTimeMillis()));
        } catch (Exception e2) {
            e = e2;
            str = strReplace;
            e.printStackTrace();
            return str;
        }
    }

    private static String a(long j, long j2, String str) {
        return new DecimalFormat("#.##").format(j2 > 1 ? j / j2 : j) + " " + str;
    }

    public static String bytesToHuman(long j) {
        long[] jArr = {1099511627776L, 1073741824, 1048576, 1024, 1};
        String[] strArr = {"TB", "GB", "MB", "KB", "B"};
        if (j < 1) {
            return "0 " + strArr[strArr.length - 1];
        }
        for (int i = 0; i < jArr.length; i++) {
            long j2 = jArr[i];
            if (j >= j2) {
                return a(j, j2, strArr[i]);
            }
        }
        return null;
    }

    public static String mapToString(Map<String, String> map) throws JSONException {
        if (map == null) {
            return null;
        }
        try {
            JSONObject jSONObject = new JSONObject();
            for (String str : map.keySet()) {
                String str2 = map.get(str);
                if (!isEmpty(str) && !isEmpty(str2)) {
                    jSONObject.put(str, str2);
                }
            }
            return jSONObject.toString();
        } catch (Exception unused) {
            return null;
        }
    }

    public static Map<String, String> stringToMap(String str, Map<String, String> map) throws JSONException {
        if (isEmpty(str) || map == null) {
            return null;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            Iterator<String> itKeys = jSONObject.keys();
            while (itKeys.hasNext()) {
                String next = itKeys.next();
                String string = jSONObject.getString(next);
                if (!isEmpty(next) && !isEmpty(string)) {
                    map.put(next, string);
                }
            }
        } catch (Exception unused) {
        }
        return map;
    }

    public static String strEncode(String str) {
        if (!isEmpty(str)) {
            try {
                return URLEncoder.encode(str, "UTF-8");
            } catch (UnsupportedEncodingException unused) {
            }
        }
        return str;
    }
}
