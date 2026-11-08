package com.bytedance.sdk.b.d;

import android.text.TextUtils;
import android.util.Pair;
import anet.channel.util.HttpConstant;
import com.appsflyer.share.Constants;
import java.io.Closeable;
import java.io.Externalizable;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Locale;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class a implements com.bytedance.sdk.b.e.a.b {

    private interface d {
        URI a();

        void b();
    }

    @Override // com.bytedance.sdk.b.e.a.b
    public String a(com.bytedance.sdk.b.e.a.a aVar, final String str, Map<String, String> map) throws Throwable {
        final HttpURLConnection httpURLConnection;
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        if (aVar != null && !com.bytedance.sdk.b.f.c.c(aVar.a())) {
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
                    httpURLConnection.setRequestProperty("User-Agent", "ADNetDNSAgent/0" + (str.startsWith("https") ? " HttpsURLConnection" : " HttpURLConnection"));
                    if (map != null && map.size() > 0) {
                        for (Map.Entry<String, String> entry : map.entrySet()) {
                            httpURLConnection.setRequestProperty(entry.getKey(), entry.getValue());
                        }
                    }
                    int responseCode = httpURLConnection.getResponseCode();
                    if (responseCode == 200) {
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
                        byte[] bArrA = f.a(zEquals, -1, inputStream, iArr, new d() { // from class: com.bytedance.sdk.b.d.a.1
                            @Override // com.bytedance.sdk.b.d.a.d
                            public URI a() {
                                try {
                                    return g.a(str);
                                } catch (RuntimeException unused) {
                                    return null;
                                }
                            }

                            @Override // com.bytedance.sdk.b.d.a.d
                            public void b() {
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
                    }
                    throw new IOException("err status = " + responseCode);
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

    private static class f {

        /* renamed from: a, reason: collision with root package name */
        public static final String f5151a = "application/octet-stream";

        /* renamed from: b, reason: collision with root package name */
        private static final int f5152b = 5242880;

        private f() {
        }

        public static boolean a(String str) {
            if (str == null) {
                return false;
            }
            int iIndexOf = str.indexOf(f5151a);
            if (iIndexOf >= 0) {
                iIndexOf = str.indexOf("ssmix=", iIndexOf + f5151a.length());
            }
            return iIndexOf > 0;
        }

        public static void a(byte[] bArr, int i) throws IOException {
            if (bArr == null || i <= 0) {
                return;
            }
            byte[] bArr2 = {-99, -114, 127, 90};
            for (int i2 = 0; i2 < i; i2++) {
                bArr[i2] = (byte) (bArr[i2] ^ bArr2[i2 % 4]);
            }
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
                        dVar.b();
                    } catch (Throwable unused) {
                    }
                }
                throw e;
            }
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
            throw new UnsupportedOperationException("Method not decompiled: com.bytedance.sdk.b.d.a.f.a(boolean, int, java.io.InputStream, int[]):byte[]");
        }

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
    }

    private static class g {
        private g() {
        }

        public static URI a(String str) throws RuntimeException {
            if (TextUtils.isEmpty(str)) {
                return null;
            }
            try {
                try {
                    return new URI(str);
                } catch (Exception unused) {
                    return b(str);
                }
            } catch (URISyntaxException unused2) {
                return URI.create(str.replaceAll("[\\u0000-\\u001F\\u007F-\\u009F\\p{javaWhitespace}]", ""));
            }
        }

        public static URI b(String str) throws RuntimeException {
            if (TextUtils.isEmpty(str)) {
                return null;
            }
            try {
                int iIndexOf = str.indexOf("?");
                if (iIndexOf > 0) {
                    str = str.substring(0, iIndexOf);
                }
                return URI.create(str);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    private static class e {
        private e() {
        }

        public static Pair<String, String> a(String str) {
            String strD;
            String strC = null;
            if (str == null) {
                return null;
            }
            try {
                C0156a c0156a = new C0156a(str);
                strD = c0156a.d();
                try {
                    strC = c0156a.c("charset");
                } catch (Throwable th) {
                    th = th;
                    th.printStackTrace();
                    return new Pair<>(strD, strC);
                }
            } catch (Throwable th2) {
                th = th2;
                strD = null;
            }
            return new Pair<>(strD, strC);
        }
    }

    /* renamed from: com.bytedance.sdk.b.d.a$a, reason: collision with other inner class name */
    private static class C0156a implements Externalizable {
        private static final String d = "()<>@,;:/[]?=\\\"";

        /* renamed from: a, reason: collision with root package name */
        private String f5146a;

        /* renamed from: b, reason: collision with root package name */
        private String f5147b;

        /* renamed from: c, reason: collision with root package name */
        private b f5148c;

        public C0156a() {
            this.f5146a = "application";
            this.f5147b = "*";
            this.f5148c = new b();
        }

        public C0156a(String str) throws c {
            f(str);
        }

        public C0156a(String str, String str2) throws c {
            if (g(str)) {
                this.f5146a = str.toLowerCase(Locale.ENGLISH);
                if (g(str2)) {
                    this.f5147b = str2.toLowerCase(Locale.ENGLISH);
                    this.f5148c = new b();
                    return;
                }
                throw new c("Sub type is invalid.");
            }
            throw new c("Primary type is invalid.");
        }

        private void f(String str) throws c {
            int iIndexOf = str.indexOf(47);
            int iIndexOf2 = str.indexOf(59);
            if (iIndexOf < 0 && iIndexOf2 < 0) {
                throw new c("Unable to find a sub type.");
            }
            if (iIndexOf < 0 && iIndexOf2 >= 0) {
                throw new c("Unable to find a sub type.");
            }
            if (iIndexOf >= 0 && iIndexOf2 < 0) {
                this.f5146a = str.substring(0, iIndexOf).trim().toLowerCase(Locale.ENGLISH);
                this.f5147b = str.substring(iIndexOf + 1).trim().toLowerCase(Locale.ENGLISH);
                this.f5148c = new b();
            } else if (iIndexOf < iIndexOf2) {
                this.f5146a = str.substring(0, iIndexOf).trim().toLowerCase(Locale.ENGLISH);
                this.f5147b = str.substring(iIndexOf + 1, iIndexOf2).trim().toLowerCase(Locale.ENGLISH);
                this.f5148c = new b(str.substring(iIndexOf2));
            } else {
                throw new c("Unable to find a sub type.");
            }
            if (!g(this.f5146a)) {
                throw new c("Primary type is invalid.");
            }
            if (!g(this.f5147b)) {
                throw new c("Sub type is invalid.");
            }
        }

        public String a() {
            return this.f5146a;
        }

        public void a(String str) throws c {
            if (!g(this.f5146a)) {
                throw new c("Primary type is invalid.");
            }
            this.f5146a = str.toLowerCase(Locale.ENGLISH);
        }

        public String b() {
            return this.f5147b;
        }

        public void b(String str) throws c {
            if (!g(this.f5147b)) {
                throw new c("Sub type is invalid.");
            }
            this.f5147b = str.toLowerCase(Locale.ENGLISH);
        }

        public b c() {
            return this.f5148c;
        }

        public String c(String str) {
            return this.f5148c.b(str);
        }

        public void a(String str, String str2) {
            this.f5148c.a(str, str2);
        }

        public void d(String str) {
            this.f5148c.c(str);
        }

        public String toString() {
            return d() + this.f5148c.toString();
        }

        public String d() {
            return this.f5146a + Constants.URL_PATH_DELIMITER + this.f5147b;
        }

        public boolean a(C0156a c0156a) {
            return this.f5146a.equals(c0156a.a()) && (this.f5147b.equals("*") || c0156a.b().equals("*") || this.f5147b.equals(c0156a.b()));
        }

        public boolean e(String str) throws c {
            return a(new C0156a(str));
        }

        @Override // java.io.Externalizable
        public void writeExternal(ObjectOutput objectOutput) throws IOException {
            objectOutput.writeUTF(toString());
            objectOutput.flush();
        }

        @Override // java.io.Externalizable
        public void readExternal(ObjectInput objectInput) throws IOException, ClassNotFoundException {
            try {
                f(objectInput.readUTF());
            } catch (c e) {
                throw new IOException(e.toString());
            }
        }

        private static boolean a(char c2) {
            return c2 > ' ' && c2 < 127 && d.indexOf(c2) < 0;
        }

        private boolean g(String str) {
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
    }

    private static class b {

        /* renamed from: b, reason: collision with root package name */
        private static final String f5149b = "()<>@,;:/[]?=\\\"";

        /* renamed from: a, reason: collision with root package name */
        private Hashtable f5150a = new Hashtable();

        public b() {
        }

        public b(String str) throws c {
            a(str);
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
                        strSubstring = e(str.substring(i3, i4));
                        i = i4 + 1;
                    } else if (a(cCharAt)) {
                        i = iA4;
                        while (i < length && a(str.charAt(i))) {
                            i++;
                        }
                        strSubstring = str.substring(iA4, i);
                    } else {
                        throw new c("Unexpected character encountered at index " + iA4);
                    }
                    this.f5150a.put(lowerCase, strSubstring);
                    iA = a(str, i);
                }
                if (iA < length) {
                    throw new c("More characters encountered in input than expected.");
                }
            }
        }

        public int a() {
            return this.f5150a.size();
        }

        public boolean b() {
            return this.f5150a.isEmpty();
        }

        public String b(String str) {
            return (String) this.f5150a.get(str.trim().toLowerCase(Locale.ENGLISH));
        }

        public void a(String str, String str2) {
            this.f5150a.put(str.trim().toLowerCase(Locale.ENGLISH), str2);
        }

        public void c(String str) {
            this.f5150a.remove(str.trim().toLowerCase(Locale.ENGLISH));
        }

        public Enumeration c() {
            return this.f5150a.keys();
        }

        public String toString() {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.ensureCapacity(this.f5150a.size() * 16);
            Enumeration enumerationKeys = this.f5150a.keys();
            while (enumerationKeys.hasMoreElements()) {
                String str = (String) enumerationKeys.nextElement();
                stringBuffer.append("; ");
                stringBuffer.append(str);
                stringBuffer.append('=');
                stringBuffer.append(d((String) this.f5150a.get(str)));
            }
            return stringBuffer.toString();
        }

        private static boolean a(char c2) {
            return c2 > ' ' && c2 < 127 && f5149b.indexOf(c2) < 0;
        }

        private static int a(String str, int i) {
            int length = str.length();
            while (i < length && Character.isWhitespace(str.charAt(i))) {
                i++;
            }
            return i;
        }

        private static String d(String str) {
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

        private static String e(String str) {
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

    private static class c extends Exception {
        public c() {
        }

        public c(String str) {
            super(str);
        }
    }
}
