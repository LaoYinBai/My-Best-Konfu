package com.bytedance.sdk.adtnc.d;

import android.text.TextUtils;
import android.util.Pair;
import anet.channel.util.HttpConstant;
import com.appsflyer.share.Constants;
import com.bytedance.sdk.b.d.a;
import java.io.Closeable;
import java.io.Externalizable;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Locale;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class a implements com.bytedance.sdk.adtnc.sdk.b.b {

    /* renamed from: com.bytedance.sdk.adtnc.d.a$a, reason: collision with other inner class name */
    private static class C0154a implements Externalizable {

        /* renamed from: a, reason: collision with root package name */
        private String f5098a;

        /* renamed from: b, reason: collision with root package name */
        private String f5099b;

        /* renamed from: c, reason: collision with root package name */
        private b f5100c;

        public C0154a() {
            this.f5098a = "application";
            this.f5099b = "*";
            this.f5100c = new b();
        }

        public C0154a(String str) throws c {
            b(str);
        }

        private static boolean a(char c2) {
            return c2 > ' ' && c2 < 127 && "()<>@,;:/[]?=\\\"".indexOf(c2) < 0;
        }

        private void b(String str) throws c {
            int iIndexOf = str.indexOf(47);
            int iIndexOf2 = str.indexOf(59);
            if (iIndexOf < 0 && iIndexOf2 < 0) {
                throw new c("Unable to find a sub type.");
            }
            if (iIndexOf < 0 && iIndexOf2 >= 0) {
                throw new c("Unable to find a sub type.");
            }
            if (iIndexOf >= 0 && iIndexOf2 < 0) {
                this.f5098a = str.substring(0, iIndexOf).trim().toLowerCase(Locale.ENGLISH);
                this.f5099b = str.substring(iIndexOf + 1).trim().toLowerCase(Locale.ENGLISH);
                this.f5100c = new b();
            } else {
                if (iIndexOf >= iIndexOf2) {
                    throw new c("Unable to find a sub type.");
                }
                this.f5098a = str.substring(0, iIndexOf).trim().toLowerCase(Locale.ENGLISH);
                this.f5099b = str.substring(iIndexOf + 1, iIndexOf2).trim().toLowerCase(Locale.ENGLISH);
                this.f5100c = new b(str.substring(iIndexOf2));
            }
            if (!c(this.f5098a)) {
                throw new c("Primary type is invalid.");
            }
            if (!c(this.f5099b)) {
                throw new c("Sub type is invalid.");
            }
        }

        private boolean c(String str) {
            int length = str.length();
            if (length <= 0) {
                return false;
            }
            for (int i = 0; i < length; i++) {
                if (!a(str.charAt(i))) {
                    return false;
                }
            }
            return true;
        }

        public String a() {
            return this.f5098a + Constants.URL_PATH_DELIMITER + this.f5099b;
        }

        public String a(String str) {
            return this.f5100c.b(str);
        }

        @Override // java.io.Externalizable
        public void readExternal(ObjectInput objectInput) throws IOException {
            try {
                b(objectInput.readUTF());
            } catch (c e) {
                throw new IOException(e.toString());
            }
        }

        public String toString() {
            return a() + this.f5100c.toString();
        }

        @Override // java.io.Externalizable
        public void writeExternal(ObjectOutput objectOutput) throws IOException {
            objectOutput.writeUTF(toString());
            objectOutput.flush();
        }
    }

    private static class b {

        /* renamed from: a, reason: collision with root package name */
        private Hashtable f5101a = new Hashtable();

        public b() {
        }

        public b(String str) throws c {
            a(str);
        }

        private static int a(String str, int i) {
            int length = str.length();
            while (i < length && Character.isWhitespace(str.charAt(i))) {
                i++;
            }
            return i;
        }

        private static boolean a(char c2) {
            return c2 > ' ' && c2 < 127 && "()<>@,;:/[]?=\\\"".indexOf(c2) < 0;
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

        protected void a(String str) throws c {
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
                        throw new c("Couldn't find the '=' that separates a parameter name from its value.");
                    }
                    int iA4 = a(str, iA3 + 1);
                    if (iA4 >= length) {
                        throw new c("Couldn't find a value for parameter named " + lowerCase);
                    }
                    char cCharAt = str.charAt(iA4);
                    if (cCharAt == '\"') {
                        int i3 = iA4 + 1;
                        if (i3 >= length) {
                            throw new c("Encountered unterminated quoted parameter value.");
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
                            throw new c("Encountered unterminated quoted parameter value.");
                        }
                        strSubstring = d(str.substring(i3, i4));
                        i = i4 + 1;
                    } else {
                        if (!a(cCharAt)) {
                            throw new c("Unexpected character encountered at index " + iA4);
                        }
                        i = iA4;
                        while (i < length && a(str.charAt(i))) {
                            i++;
                        }
                        strSubstring = str.substring(iA4, i);
                    }
                    this.f5101a.put(lowerCase, strSubstring);
                    iA = a(str, i);
                }
                if (iA < length) {
                    throw new c("More characters encountered in input than expected.");
                }
            }
        }

        public String b(String str) {
            return (String) this.f5101a.get(str.trim().toLowerCase(Locale.ENGLISH));
        }

        public String toString() {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.ensureCapacity(this.f5101a.size() * 16);
            Enumeration enumerationKeys = this.f5101a.keys();
            while (enumerationKeys.hasMoreElements()) {
                String str = (String) enumerationKeys.nextElement();
                stringBuffer.append("; ");
                stringBuffer.append(str);
                stringBuffer.append('=');
                stringBuffer.append(c((String) this.f5101a.get(str)));
            }
            return stringBuffer.toString();
        }
    }

    private static class c extends Exception {
        public c() {
        }

        public c(String str) {
            super(str);
        }
    }

    private interface d {
        void a();
    }

    private static class e {
        public static Pair<String, String> a(String str) {
            String strA;
            String strA2 = null;
            if (str == null) {
                return null;
            }
            try {
                C0154a c0154a = new C0154a(str);
                strA = c0154a.a();
                try {
                    strA2 = c0154a.a("charset");
                } catch (Throwable th) {
                    th = th;
                    th.printStackTrace();
                    return new Pair<>(strA, strA2);
                }
            } catch (Throwable th2) {
                th = th2;
                strA = null;
            }
            return new Pair<>(strA, strA2);
        }
    }

    private static class f {
        public static void a(Closeable closeable) throws IOException {
            a(closeable, (String) null);
        }

        private static void a(Closeable closeable, String str) throws IOException {
            if (closeable != null) {
                try {
                    closeable.close();
                } catch (Exception unused) {
                }
            }
        }

        public static void a(byte[] bArr, int i) {
            if (bArr == null || i <= 0) {
                return;
            }
            byte[] bArr2 = {-99, -114, 127, 90};
            for (int i2 = 0; i2 < i; i2++) {
                bArr[i2] = (byte) (bArr[i2] ^ bArr2[i2 % 4]);
            }
        }

        public static boolean a(String str) {
            if (str == null) {
                return false;
            }
            int iIndexOf = str.indexOf(a.f.f5151a);
            if (iIndexOf >= 0) {
                iIndexOf = str.indexOf("ssmix=", iIndexOf + a.f.f5151a.length());
            }
            return iIndexOf > 0;
        }

        /* JADX WARN: Code restructure failed: missing block: B:40:0x005f, code lost:
        
            if (r1 <= 0) goto L44;
         */
        /* JADX WARN: Code restructure failed: missing block: B:41:0x0061, code lost:
        
            r10[0] = r1;
         */
        /* JADX WARN: Code restructure failed: missing block: B:42:0x0063, code lost:
        
            a(r9);
         */
        /* JADX WARN: Code restructure failed: missing block: B:43:0x0066, code lost:
        
            return r4;
         */
        /* JADX WARN: Code restructure failed: missing block: B:44:0x0067, code lost:
        
            a(r9);
         */
        /* JADX WARN: Code restructure failed: missing block: B:45:0x006a, code lost:
        
            return null;
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public static byte[] a(boolean r7, int r8, java.io.InputStream r9, int[] r10) throws java.io.IOException {
            /*
                if (r8 > 0) goto L4
                r8 = 5242880(0x500000, float:7.34684E-39)
            L4:
                r0 = 1048576(0x100000, float:1.469368E-39)
                if (r8 >= r0) goto La
                r8 = 1048576(0x100000, float:1.469368E-39)
            La:
                r0 = 0
                if (r9 != 0) goto Le
                return r0
            Le:
                if (r7 == 0) goto L19
                java.util.zip.GZIPInputStream r1 = new java.util.zip.GZIPInputStream     // Catch: java.lang.Throwable -> L17
                r1.<init>(r9)     // Catch: java.lang.Throwable -> L17
                r9 = r1
                goto L19
            L17:
                r7 = move-exception
                goto L6c
            L19:
                r1 = 8192(0x2000, float:1.148E-41)
                byte[] r1 = new byte[r1]     // Catch: java.lang.Throwable -> L17
                r2 = 4096(0x1000, float:5.74E-42)
                r3 = 0
                r4 = r1
                r1 = 0
            L22:
                int r5 = r1 + 4096
                int r6 = r4.length     // Catch: java.lang.Throwable -> L17 java.io.IOException -> L3f java.io.EOFException -> L5a
                if (r5 <= r6) goto L30
                int r5 = r4.length     // Catch: java.lang.Throwable -> L17 java.io.IOException -> L3f java.io.EOFException -> L5a
                int r5 = r5 * 2
                byte[] r5 = new byte[r5]     // Catch: java.lang.Throwable -> L17 java.io.IOException -> L3f java.io.EOFException -> L5a
                java.lang.System.arraycopy(r4, r3, r5, r3, r1)     // Catch: java.lang.Throwable -> L17 java.io.IOException -> L3f java.io.EOFException -> L5a
                r4 = r5
            L30:
                int r5 = r9.read(r4, r1, r2)     // Catch: java.lang.Throwable -> L17 java.io.IOException -> L3f java.io.EOFException -> L5a
                if (r5 <= 0) goto L5f
                int r1 = r1 + r5
                if (r8 <= 0) goto L22
                if (r1 <= r8) goto L22
                a(r9)
                return r0
            L3f:
                r8 = move-exception
                java.lang.String r2 = r8.getMessage()     // Catch: java.lang.Throwable -> L17
                if (r7 == 0) goto L59
                if (r1 <= 0) goto L59
                java.lang.String r7 = "CRC mismatch"
                boolean r7 = r7.equals(r2)     // Catch: java.lang.Throwable -> L17
                if (r7 != 0) goto L5f
                java.lang.String r7 = "Size mismatch"
                boolean r7 = r7.equals(r2)     // Catch: java.lang.Throwable -> L17
                if (r7 == 0) goto L59
                goto L5f
            L59:
                throw r8     // Catch: java.lang.Throwable -> L17
            L5a:
                r8 = move-exception
                if (r7 == 0) goto L6b
                if (r1 <= 0) goto L6b
            L5f:
                if (r1 <= 0) goto L67
                r10[r3] = r1     // Catch: java.lang.Throwable -> L17
                a(r9)
                return r4
            L67:
                a(r9)
                return r0
            L6b:
                throw r8     // Catch: java.lang.Throwable -> L17
            L6c:
                a(r9)
                throw r7
            */
            throw new UnsupportedOperationException("Method not decompiled: com.bytedance.sdk.adtnc.d.a.f.a(boolean, int, java.io.InputStream, int[]):byte[]");
        }

        public static byte[] a(boolean z, int i, InputStream inputStream, int[] iArr, d dVar) throws Exception {
            if (inputStream == null) {
                return null;
            }
            try {
                byte[] bArrA = a(z, i, inputStream, iArr);
                if (bArrA == null || iArr[0] <= 0) {
                    return null;
                }
                return bArrA;
            } catch (Exception e) {
                if (dVar != null) {
                    try {
                        dVar.a();
                    } catch (Throwable unused) {
                    }
                }
                throw e;
            }
        }
    }

    @Override // com.bytedance.sdk.adtnc.sdk.b.b
    public String a(com.bytedance.sdk.adtnc.sdk.b.a aVar, final String str, Map<String, String> map) throws Throwable {
        final HttpURLConnection httpURLConnection;
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        if (aVar != null && !com.bytedance.sdk.adtnc.g.d.c(aVar.a())) {
            return null;
        }
        try {
            httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
            try {
                try {
                    httpURLConnection.setRequestMethod("GET");
                    httpURLConnection.setConnectTimeout(15000);
                    httpURLConnection.setReadTimeout(15000);
                    httpURLConnection.setInstanceFollowRedirects(true);
                    httpURLConnection.setRequestProperty(HttpConstant.ACCEPT_ENCODING, HttpConstant.GZIP);
                    httpURLConnection.setRequestProperty("User-Agent", "ADNetTncAgent/0" + (str.startsWith("https") ? " HttpsURLConnection" : " HttpURLConnection"));
                    if (map != null && map.size() > 0) {
                        for (Map.Entry<String, String> entry : map.entrySet()) {
                            httpURLConnection.setRequestProperty(entry.getKey(), entry.getValue());
                        }
                    }
                    int responseCode = httpURLConnection.getResponseCode();
                    if (responseCode != 200) {
                        throw new IOException("err status = " + responseCode);
                    }
                    InputStream inputStream = httpURLConnection.getInputStream();
                    boolean zEquals = HttpConstant.GZIP.equals(httpURLConnection.getHeaderField("Content-Encoding"));
                    String headerField = httpURLConnection.getHeaderField("Content-Type");
                    boolean zA = f.a(headerField);
                    Pair<String, String> pairA = e.a(headerField);
                    String str2 = (pairA == null || pairA.second == null || !Charset.isSupported((String) pairA.second)) ? null : (String) pairA.second;
                    if (str2 == null) {
                        str2 = "UTF-8";
                    }
                    int[] iArr = {0};
                    byte[] bArrA = f.a(zEquals, -1, inputStream, iArr, new d() { // from class: com.bytedance.sdk.adtnc.d.a.1
                        @Override // com.bytedance.sdk.adtnc.d.a.d
                        public void a() {
                            try {
                                if (httpURLConnection != null) {
                                    httpURLConnection.disconnect();
                                }
                            } catch (Throwable unused) {
                            }
                        }
                    });
                    if (bArrA != null && iArr[0] > 0 && iArr[0] <= bArrA.length) {
                        if (zA) {
                            f.a(bArrA, iArr[0]);
                        }
                        String str3 = new String(bArrA, 0, iArr[0], str2);
                        if (httpURLConnection != null) {
                            try {
                                httpURLConnection.disconnect();
                            } catch (Exception unused) {
                            }
                        }
                        return str3;
                    }
                    if (httpURLConnection != null) {
                        try {
                            httpURLConnection.disconnect();
                        } catch (Exception unused2) {
                        }
                    }
                    return null;
                } catch (Throwable th) {
                    th = th;
                    if (httpURLConnection != null) {
                        try {
                            httpURLConnection.disconnect();
                        } catch (Exception unused3) {
                        }
                    }
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
                th.printStackTrace();
                if (httpURLConnection != null) {
                    try {
                        httpURLConnection.disconnect();
                    } catch (Exception unused4) {
                    }
                }
                return null;
            }
        } catch (Throwable th3) {
            th = th3;
            httpURLConnection = null;
        }
    }
}
