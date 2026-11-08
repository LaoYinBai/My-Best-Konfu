package com.bytedance.common.utility;

import android.text.TextUtils;
import android.util.Base64;
import com.bytedance.sdk.account.platform.api.IWeiboService;
import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.lang.Character;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.zip.GZIPOutputStream;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public final class StringUtils {
    private static final int CHAR_12288 = 12288;
    private static final int CHAR_127 = 127;
    private static final int CHAR_32 = 32;
    private static final int CHAR_65248 = 65248;
    private static final int CHAR_65280 = 65280;
    private static final int CHAR_65375 = 65375;
    private static final int CHAR_MAX = 31;
    public static final String EMPTY = "";
    private static final long G = 1073741824;
    private static final long K = 1024;
    private static final long M = 1048576;
    private static final int STRING_MAX_NUM = 255;
    private static final long T = 1099511627776L;
    private static final int XOR_MASK = 5;

    private StringUtils() {
    }

    public static boolean isEmpty(String str) {
        return str == null || str.length() == 0;
    }

    public static boolean equal(String str, String str2) {
        return (isEmpty(str) && isEmpty(str2)) || (str != null && str.equals(str2));
    }

    public static String ellipsize(String str, int i) {
        if (str == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        int i2 = 0;
        int i3 = 0;
        while (true) {
            if (i2 >= str.length()) {
                break;
            }
            char cCharAt = str.charAt(i2);
            i3 = (cCharAt <= 0 || cCharAt > 255) ? i3 + 2 : i3 + 1;
            if (i3 > i) {
                sb.append("...");
                break;
            }
            sb.append(cCharAt);
            i2++;
        }
        return sb.toString();
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
                    sb.append('\\');
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
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) >= '0' && str.charAt(i) <= '9') {
                stringBuffer.append(str.charAt(i));
            }
        }
        return stringBuffer.toString();
    }

    public static HashMap<String, String> parseResponse(String str) {
        String[] strArrSplit = str.substring(str.indexOf(123) + 1, str.lastIndexOf(125)).split(IWeiboService.Scope.EMPTY_SCOPE);
        for (int i = 0; i < strArrSplit.length; i++) {
            strArrSplit[i] = strArrSplit[i].trim();
        }
        HashMap<String, String> map = new HashMap<>();
        for (String str2 : strArrSplit) {
            String[] strArrSplit2 = str2.split(":", 2);
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
            } else if (charArray[i] < CHAR_127 && charArray[i] > ' ') {
                charArray[i] = (char) (charArray[i] + CHAR_65248);
            }
        }
        return new String(charArray);
    }

    public static String toDBC(String str) {
        char[] charArray = str.toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            if (isChinese(charArray[i])) {
                if (charArray[i] == CHAR_12288) {
                    charArray[i] = ' ';
                } else if (charArray[i] > 65280 && charArray[i] < CHAR_65375) {
                    charArray[i] = (char) (charArray[i] - CHAR_65248);
                }
            }
        }
        return new String(charArray);
    }

    public static boolean isChinese(char c2) {
        Character.UnicodeBlock unicodeBlockOf = Character.UnicodeBlock.of(c2);
        return unicodeBlockOf == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS || unicodeBlockOf == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS || unicodeBlockOf == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A || unicodeBlockOf == Character.UnicodeBlock.GENERAL_PUNCTUATION || unicodeBlockOf == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION || unicodeBlockOf == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS;
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

    private static String format(long j, long j2, String str) {
        return new DecimalFormat("#.##").format(j2 > 1 ? j / j2 : j) + " " + str;
    }

    public static String bytesToHuman(long j) {
        long[] jArr = {T, G, 1048576, 1024, 1};
        String[] strArr = {"TB", "GB", "MB", "KB", "B"};
        if (j < 1) {
            return "0 " + strArr[strArr.length - 1];
        }
        for (int i = 0; i < jArr.length; i++) {
            long j2 = jArr[i];
            if (j >= j2) {
                return format(j, j2, strArr[i]);
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

    public static String encryptWithXor(String str) throws UnsupportedEncodingException {
        try {
            if (TextUtils.isEmpty(str)) {
                return null;
            }
            byte[] bytes = str.getBytes("UTF-8");
            for (int i = 0; i < bytes.length; i++) {
                bytes[i] = (byte) (bytes[i] ^ 5);
            }
            return DigestUtils.toHexString(bytes, 0, bytes.length);
        } catch (Exception unused) {
            return str;
        }
    }

    public static String decryptWithXor(String str, Boolean... boolArr) {
        byte[] bArrHexStringToBytes;
        try {
            if (TextUtils.isEmpty(str)) {
                return null;
            }
            if (boolArr != null && boolArr.length > 0 && boolArr[0].booleanValue()) {
                bArrHexStringToBytes = Base64.decode(str, 0);
            } else {
                bArrHexStringToBytes = DigestUtils.hexStringToBytes(str);
            }
            for (int i = 0; i < bArrHexStringToBytes.length; i++) {
                bArrHexStringToBytes[i] = (byte) (bArrHexStringToBytes[i] ^ 5);
            }
            return new String(bArrHexStringToBytes, 0, bArrHexStringToBytes.length, "UTF-8");
        } catch (Exception unused) {
            return str;
        }
    }

    public static String trimString(String str) {
        return (str == null || str.length() == 0) ? str : str.trim();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v0, types: [boolean] */
    /* JADX WARN: Type inference failed for: r0v1, types: [java.io.ByteArrayOutputStream] */
    /* JADX WARN: Type inference failed for: r0v2 */
    /* JADX WARN: Type inference failed for: r0v3 */
    /* JADX WARN: Type inference failed for: r0v4, types: [java.io.ByteArrayOutputStream] */
    /* JADX WARN: Type inference failed for: r0v5, types: [java.io.ByteArrayOutputStream, java.io.OutputStream] */
    public static String compressWithGzip(String str) throws Throwable {
        GZIPOutputStream gZIPOutputStream;
        GZIPOutputStream gZIPOutputStream2;
        ?? IsEmpty = isEmpty(str);
        try {
            if (IsEmpty != 0) {
                return null;
            }
            try {
                IsEmpty = new ByteArrayOutputStream(str.length());
            } catch (Throwable th) {
                th = th;
                IsEmpty = 0;
                gZIPOutputStream = null;
            }
            try {
                gZIPOutputStream2 = new GZIPOutputStream(IsEmpty);
                try {
                    gZIPOutputStream2.write(str.getBytes());
                    gZIPOutputStream2.close();
                    byte[] byteArray = IsEmpty.toByteArray();
                    String hexString = DigestUtils.toHexString(byteArray, 0, byteArray.length);
                    try {
                        gZIPOutputStream2.close();
                        IsEmpty.close();
                    } catch (Throwable unused) {
                    }
                    return hexString;
                } catch (Throwable th2) {
                    th = th2;
                    Logger.d("Thread", th.getMessage());
                    if (gZIPOutputStream2 != null) {
                        try {
                            gZIPOutputStream2.close();
                        } catch (Throwable unused2) {
                            return null;
                        }
                    }
                    if (IsEmpty != 0) {
                        IsEmpty.close();
                    }
                    return null;
                }
            } catch (Throwable th3) {
                th = th3;
                gZIPOutputStream2 = null;
            }
        } catch (Throwable th4) {
            th = th4;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:42:0x007b A[Catch: Throwable -> 0x0083, TryCatch #6 {Throwable -> 0x0083, blocks: (B:40:0x0076, B:42:0x007b, B:44:0x0080), top: B:54:0x0076 }] */
    /* JADX WARN: Removed duplicated region for block: B:44:0x0080 A[Catch: Throwable -> 0x0083, TRY_LEAVE, TryCatch #6 {Throwable -> 0x0083, blocks: (B:40:0x0076, B:42:0x007b, B:44:0x0080), top: B:54:0x0076 }] */
    /* JADX WARN: Removed duplicated region for block: B:54:0x0076 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r0v0, types: [boolean] */
    /* JADX WARN: Type inference failed for: r0v1, types: [java.io.ByteArrayInputStream] */
    /* JADX WARN: Type inference failed for: r0v2 */
    /* JADX WARN: Type inference failed for: r0v3 */
    /* JADX WARN: Type inference failed for: r0v4 */
    /* JADX WARN: Type inference failed for: r0v5, types: [java.io.ByteArrayInputStream] */
    /* JADX WARN: Type inference failed for: r0v6, types: [java.io.ByteArrayInputStream, java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r0v7 */
    /* JADX WARN: Type inference failed for: r6v0, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r6v1, types: [java.util.zip.GZIPInputStream] */
    /* JADX WARN: Type inference failed for: r6v10, types: [java.io.InputStream, java.util.zip.GZIPInputStream] */
    /* JADX WARN: Type inference failed for: r6v2 */
    /* JADX WARN: Type inference failed for: r6v5 */
    /* JADX WARN: Type inference failed for: r6v6, types: [java.util.zip.GZIPInputStream] */
    /* JADX WARN: Type inference failed for: r6v9 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.lang.String decompressWithGzip(java.lang.String r6) throws java.lang.Throwable {
        /*
            boolean r0 = isEmpty(r6)
            r1 = 0
            if (r0 == 0) goto L8
            return r1
        L8:
            byte[] r6 = com.bytedance.common.utility.DigestUtils.hexStringToBytes(r6)     // Catch: java.lang.Throwable -> L50 java.lang.Throwable -> L56
            java.io.ByteArrayInputStream r0 = new java.io.ByteArrayInputStream     // Catch: java.lang.Throwable -> L50 java.lang.Throwable -> L56
            r0.<init>(r6)     // Catch: java.lang.Throwable -> L50 java.lang.Throwable -> L56
            java.util.zip.GZIPInputStream r6 = new java.util.zip.GZIPInputStream     // Catch: java.lang.Throwable -> L49 java.lang.Throwable -> L4c
            r6.<init>(r0)     // Catch: java.lang.Throwable -> L49 java.lang.Throwable -> L4c
            java.io.BufferedReader r2 = new java.io.BufferedReader     // Catch: java.lang.Throwable -> L41 java.lang.Throwable -> L46
            java.io.InputStreamReader r3 = new java.io.InputStreamReader     // Catch: java.lang.Throwable -> L41 java.lang.Throwable -> L46
            java.lang.String r4 = "UTF-8"
            r3.<init>(r6, r4)     // Catch: java.lang.Throwable -> L41 java.lang.Throwable -> L46
            r2.<init>(r3)     // Catch: java.lang.Throwable -> L41 java.lang.Throwable -> L46
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L3f java.lang.Throwable -> L73
            r3.<init>()     // Catch: java.lang.Throwable -> L3f java.lang.Throwable -> L73
        L27:
            java.lang.String r4 = r2.readLine()     // Catch: java.lang.Throwable -> L3f java.lang.Throwable -> L73
            if (r4 == 0) goto L31
            r3.append(r4)     // Catch: java.lang.Throwable -> L3f java.lang.Throwable -> L73
            goto L27
        L31:
            java.lang.String r3 = r3.toString()     // Catch: java.lang.Throwable -> L3f java.lang.Throwable -> L73
            r2.close()     // Catch: java.lang.Throwable -> L3e
            r6.close()     // Catch: java.lang.Throwable -> L3e
            r0.close()     // Catch: java.lang.Throwable -> L3e
        L3e:
            return r3
        L3f:
            r3 = move-exception
            goto L5a
        L41:
            r2 = move-exception
            r5 = r2
            r2 = r1
            r1 = r5
            goto L74
        L46:
            r3 = move-exception
            r2 = r1
            goto L5a
        L49:
            r6 = move-exception
            r2 = r1
            goto L53
        L4c:
            r3 = move-exception
            r6 = r1
            r2 = r6
            goto L5a
        L50:
            r6 = move-exception
            r0 = r1
            r2 = r0
        L53:
            r1 = r6
            r6 = r2
            goto L74
        L56:
            r3 = move-exception
            r6 = r1
            r0 = r6
            r2 = r0
        L5a:
            java.lang.String r4 = "Thread"
            java.lang.String r3 = r3.getMessage()     // Catch: java.lang.Throwable -> L73
            com.bytedance.common.utility.Logger.d(r4, r3)     // Catch: java.lang.Throwable -> L73
            if (r2 == 0) goto L68
            r2.close()     // Catch: java.lang.Throwable -> L72
        L68:
            if (r6 == 0) goto L6d
            r6.close()     // Catch: java.lang.Throwable -> L72
        L6d:
            if (r0 == 0) goto L72
            r0.close()     // Catch: java.lang.Throwable -> L72
        L72:
            return r1
        L73:
            r1 = move-exception
        L74:
            if (r2 == 0) goto L79
            r2.close()     // Catch: java.lang.Throwable -> L83
        L79:
            if (r6 == 0) goto L7e
            r6.close()     // Catch: java.lang.Throwable -> L83
        L7e:
            if (r0 == 0) goto L83
            r0.close()     // Catch: java.lang.Throwable -> L83
        L83:
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bytedance.common.utility.StringUtils.decompressWithGzip(java.lang.String):java.lang.String");
    }
}
