package com.umeng.message.util;

import com.sigmob.sdk.common.Constants;
import com.ss.android.download.api.constant.BaseConstants;
import com.ss.android.socialbase.downloader.utils.DownloadUtils;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.Flushable;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.Proxy;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.zip.GZIPInputStream;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class HttpRequest {
    private static final String BOUNDARY = "00content0boundary00";
    public static final String CHARSET_UTF8 = "UTF-8";
    public static final String CONTENT_TYPE_FORM = "application/x-www-form-urlencoded";
    public static final String CONTENT_TYPE_JSON = "application/json";
    private static final String CONTENT_TYPE_MULTIPART = "multipart/form-data; boundary=00content0boundary00";
    public static final String CONTENT_TYPE_THRIFT = "application/thrift";
    private static final String CRLF = "\r\n";
    public static final String ENCODING_GZIP = "gzip";
    public static final String HEADER_ACCEPT = "Accept";
    public static final String HEADER_ACCEPT_CHARSET = "Accept-Charset";
    public static final String HEADER_ACCEPT_ENCODING = "Accept-Encoding";
    public static final String HEADER_AUTHORIZATION = "Authorization";
    public static final String HEADER_CACHE_CONTROL = "Cache-Control";
    public static final String HEADER_CONTENT_ENCODING = "Content-Encoding";
    public static final String HEADER_CONTENT_LENGTH = "Content-Length";
    public static final String HEADER_CONTENT_TYPE = "Content-Type";
    public static final String HEADER_DATE = "Date";
    public static final String HEADER_ETAG = "ETag";
    public static final String HEADER_EXPIRES = "Expires";
    public static final String HEADER_IF_NONE_MATCH = "If-None-Match";
    public static final String HEADER_LAST_MODIFIED = "Last-Modified";
    public static final String HEADER_LOCATION = "Location";
    public static final String HEADER_PROXY_AUTHORIZATION = "Proxy-Authorization";
    public static final String HEADER_REFERER = "Referer";
    public static final String HEADER_SERVER = "Server";
    public static final String HEADER_USER_AGENT = "User-Agent";
    public static final String METHOD_DELETE = "DELETE";
    public static final String METHOD_GET = "GET";
    public static final String METHOD_HEAD = "HEAD";
    public static final String METHOD_OPTIONS = "OPTIONS";
    public static final String METHOD_POST = "POST";
    public static final String METHOD_PUT = "PUT";
    public static final String METHOD_TRACE = "TRACE";
    public static final String PARAM_CHARSET = "charset";
    private static SSLSocketFactory TRUSTED_FACTORY;
    private static HostnameVerifier TRUSTED_VERIFIER;
    private boolean form;
    private String httpProxyHost;
    private int httpProxyPort;
    private boolean multipart;
    private e output;
    private final String requestMethod;
    private final URL url;
    private static final String[] EMPTY_STRINGS = new String[0];
    private static int readTimeOut = BaseConstants.Time.MINUTE;
    private static int connectTimeout = BaseConstants.Time.MINUTE;
    private static ConnectionFactory CONNECTION_FACTORY = ConnectionFactory.DEFAULT;
    private HttpURLConnection connection = null;
    private boolean ignoreCloseExceptions = true;
    private boolean uncompress = false;
    private int bufferSize = 8192;

    public interface ConnectionFactory {
        public static final ConnectionFactory DEFAULT = new ConnectionFactory() { // from class: com.umeng.message.util.HttpRequest.ConnectionFactory.1
            @Override // com.umeng.message.util.HttpRequest.ConnectionFactory
            public HttpURLConnection create(URL url) throws IOException {
                return (HttpURLConnection) url.openConnection();
            }

            @Override // com.umeng.message.util.HttpRequest.ConnectionFactory
            public HttpURLConnection create(URL url, Proxy proxy) throws IOException {
                return (HttpURLConnection) url.openConnection(proxy);
            }
        };

        HttpURLConnection create(URL url) throws IOException;

        HttpURLConnection create(URL url, Proxy proxy) throws IOException;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String getValidCharset(String str) {
        return (str == null || str.length() <= 0) ? "UTF-8" : str;
    }

    private static HostnameVerifier getTrustedVerifier() {
        if (TRUSTED_VERIFIER == null) {
            TRUSTED_VERIFIER = new HostnameVerifier() { // from class: com.umeng.message.util.HttpRequest.1
                @Override // javax.net.ssl.HostnameVerifier
                public boolean verify(String str, SSLSession sSLSession) {
                    return true;
                }
            };
        }
        return TRUSTED_VERIFIER;
    }

    private static HostnameVerifier getTrustedVerifier(final HttpsURLConnection httpsURLConnection) {
        if (TRUSTED_VERIFIER == null) {
            TRUSTED_VERIFIER = new HostnameVerifier() { // from class: com.umeng.message.util.HttpRequest.3
                @Override // javax.net.ssl.HostnameVerifier
                public boolean verify(String str, SSLSession sSLSession) {
                    String requestProperty = httpsURLConnection.getRequestProperty(Constants.HOST);
                    if (requestProperty == null) {
                        requestProperty = httpsURLConnection.getURL().getHost();
                    }
                    return HttpsURLConnection.getDefaultHostnameVerifier().verify(requestProperty, sSLSession);
                }
            };
        }
        return TRUSTED_VERIFIER;
    }

    private static StringBuilder addPathSeparator(String str, StringBuilder sb) {
        if (str.indexOf(58) + 2 == str.lastIndexOf(47)) {
            sb.append('/');
        }
        return sb;
    }

    private static StringBuilder addParamPrefix(String str, StringBuilder sb) {
        int iIndexOf = str.indexOf(63);
        int length = sb.length() - 1;
        if (iIndexOf == -1) {
            sb.append('?');
        } else if (iIndexOf < length && str.charAt(length) != '&') {
            sb.append('&');
        }
        return sb;
    }

    public static void setConnectionFactory(ConnectionFactory connectionFactory) {
        if (connectionFactory == null) {
            CONNECTION_FACTORY = ConnectionFactory.DEFAULT;
        } else {
            CONNECTION_FACTORY = connectionFactory;
        }
    }

    public static class a {
        private static final byte a = 61;
        private static final String b = "US-ASCII";
        private static final byte[] c = {65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 43, 47};

        private a() {
        }

        private static byte[] a(byte[] bArr, int i, int i2, byte[] bArr2, int i3) {
            byte[] bArr3 = c;
            int i4 = (i2 > 0 ? (bArr[i] << 24) >>> 8 : 0) | (i2 > 1 ? (bArr[i + 1] << 24) >>> 16 : 0) | (i2 > 2 ? (bArr[i + 2] << 24) >>> 24 : 0);
            switch (i2) {
                case 1:
                    bArr2[i3] = bArr3[i4 >>> 18];
                    bArr2[i3 + 1] = bArr3[(i4 >>> 12) & 63];
                    bArr2[i3 + 2] = a;
                    bArr2[i3 + 3] = a;
                    break;
                case 2:
                    bArr2[i3] = bArr3[i4 >>> 18];
                    bArr2[i3 + 1] = bArr3[(i4 >>> 12) & 63];
                    bArr2[i3 + 2] = bArr3[(i4 >>> 6) & 63];
                    bArr2[i3 + 3] = a;
                    break;
                case 3:
                    bArr2[i3] = bArr3[i4 >>> 18];
                    bArr2[i3 + 1] = bArr3[(i4 >>> 12) & 63];
                    bArr2[i3 + 2] = bArr3[(i4 >>> 6) & 63];
                    bArr2[i3 + 3] = bArr3[i4 & 63];
                    break;
            }
            return bArr2;
        }

        public static String a(String str) throws UnsupportedEncodingException {
            byte[] bytes;
            try {
                bytes = str.getBytes("US-ASCII");
            } catch (UnsupportedEncodingException unused) {
                bytes = str.getBytes();
            }
            return a(bytes);
        }

        public static String a(byte[] bArr) {
            return a(bArr, 0, bArr.length);
        }

        public static String a(byte[] bArr, int i, int i2) {
            byte[] bArrB = b(bArr, i, i2);
            try {
                return new String(bArrB, "US-ASCII");
            } catch (UnsupportedEncodingException unused) {
                return new String(bArrB);
            }
        }

        public static byte[] b(byte[] bArr, int i, int i2) {
            if (bArr == null) {
                throw new NullPointerException("Cannot serialize a null array.");
            }
            if (i < 0) {
                throw new IllegalArgumentException("Cannot have negative offset: " + i);
            }
            if (i2 < 0) {
                throw new IllegalArgumentException("Cannot have length offset: " + i2);
            }
            if (i + i2 > bArr.length) {
                throw new IllegalArgumentException(String.format("Cannot have offset of %d and length of %d with array of length %d", Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(bArr.length)));
            }
            byte[] bArr2 = new byte[((i2 / 3) * 4) + (i2 % 3 <= 0 ? 0 : 4)];
            int i3 = i2 - 2;
            int i4 = 0;
            int i5 = 0;
            while (i4 < i3) {
                a(bArr, i4 + i, 3, bArr2, i5);
                i4 += 3;
                i5 += 4;
            }
            if (i4 < i2) {
                a(bArr, i + i4, i2 - i4, bArr2, i5);
                i5 += 4;
            }
            if (i5 > bArr2.length - 1) {
                return bArr2;
            }
            byte[] bArr3 = new byte[i5];
            System.arraycopy(bArr2, 0, bArr3, 0, i5);
            return bArr3;
        }
    }

    public static class HttpRequestException extends RuntimeException {
        private static final long serialVersionUID = -1170466989781746231L;

        protected HttpRequestException(IOException iOException) {
            super(iOException);
        }

        @Override // java.lang.Throwable
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public IOException getCause() {
            return (IOException) super.getCause();
        }
    }

    protected static abstract class d<V> implements Callable<V> {
        protected abstract V b() throws HttpRequestException, IOException;

        protected abstract void c() throws IOException;

        protected d() {
        }

        @Override // java.util.concurrent.Callable
        public V call() throws Throwable {
            Throwable th;
            boolean z = true;
            try {
                try {
                    V vB = b();
                    try {
                        c();
                        return vB;
                    } catch (IOException e) {
                        throw new HttpRequestException(e);
                    }
                } catch (Throwable th2) {
                    th = th2;
                    try {
                        c();
                    } catch (IOException e2) {
                        if (!z) {
                            throw new HttpRequestException(e2);
                        }
                    }
                    throw th;
                }
            } catch (HttpRequestException e3) {
                throw e3;
            } catch (IOException e4) {
                throw new HttpRequestException(e4);
            } catch (Throwable th3) {
                th = th3;
                z = false;
                c();
                throw th;
            }
        }
    }

    protected static abstract class b<V> extends d<V> {
        private final Closeable a;
        private final boolean b;

        protected b(Closeable closeable, boolean z) {
            this.a = closeable;
            this.b = z;
        }

        @Override // com.umeng.message.util.HttpRequest.d
        protected void c() throws IOException {
            if (this.a instanceof Flushable) {
                ((Flushable) this.a).flush();
            }
            if (this.b) {
                try {
                    this.a.close();
                } catch (IOException unused) {
                }
            } else {
                this.a.close();
            }
        }
    }

    protected static abstract class c<V> extends d<V> {
        private final Flushable a;

        protected c(Flushable flushable) {
            this.a = flushable;
        }

        @Override // com.umeng.message.util.HttpRequest.d
        protected void c() throws IOException {
            this.a.flush();
        }
    }

    public static class e extends BufferedOutputStream {
        private final CharsetEncoder a;

        public e(OutputStream outputStream, String str, int i) {
            super(outputStream, i);
            this.a = Charset.forName(HttpRequest.getValidCharset(str)).newEncoder();
        }

        public e a(String str) throws IOException {
            ByteBuffer byteBufferEncode = this.a.encode(CharBuffer.wrap(str));
            super.write(byteBufferEncode.array(), 0, byteBufferEncode.limit());
            return this;
        }
    }

    public static String encode(CharSequence charSequence) throws HttpRequestException {
        int i;
        try {
            URL url = new URL(charSequence.toString());
            String host = url.getHost();
            int port = url.getPort();
            if (port != -1) {
                host = host + ':' + Integer.toString(port);
            }
            try {
                String aSCIIString = new URI(url.getProtocol(), host, url.getPath(), url.getQuery(), null).toASCIIString();
                int iIndexOf = aSCIIString.indexOf(63);
                if (iIndexOf <= 0 || (i = iIndexOf + 1) >= aSCIIString.length()) {
                    return aSCIIString;
                }
                return aSCIIString.substring(0, i) + aSCIIString.substring(i).replace("+", "%2B");
            } catch (URISyntaxException e2) {
                IOException iOException = new IOException("Parsing URI failed");
                iOException.initCause(e2);
                throw new HttpRequestException(iOException);
            }
        } catch (IOException e3) {
            throw new HttpRequestException(e3);
        }
    }

    public static String append(CharSequence charSequence, Map<?, ?> map) {
        String string = charSequence.toString();
        if (map == null || map.isEmpty()) {
            return string;
        }
        StringBuilder sb = new StringBuilder(string);
        addPathSeparator(string, sb);
        addParamPrefix(string, sb);
        Iterator<Map.Entry<?, ?>> it = map.entrySet().iterator();
        Map.Entry<?, ?> next = it.next();
        sb.append(next.getKey().toString());
        sb.append('=');
        Object value = next.getValue();
        if (value != null) {
            sb.append(value);
        }
        while (it.hasNext()) {
            sb.append('&');
            Map.Entry<?, ?> next2 = it.next();
            sb.append(next2.getKey().toString());
            sb.append('=');
            Object value2 = next2.getValue();
            if (value2 != null) {
                sb.append(value2);
            }
        }
        return sb.toString();
    }

    public static String append(CharSequence charSequence, Object... objArr) {
        String string = charSequence.toString();
        if (objArr == null || objArr.length == 0) {
            return string;
        }
        if (objArr.length % 2 != 0) {
            throw new IllegalArgumentException("Must specify an even number of parameter names/values");
        }
        StringBuilder sb = new StringBuilder(string);
        addPathSeparator(string, sb);
        addParamPrefix(string, sb);
        sb.append(objArr[0]);
        sb.append('=');
        Object obj = objArr[1];
        if (obj != null) {
            sb.append(obj);
        }
        for (int i = 2; i < objArr.length; i += 2) {
            sb.append('&');
            sb.append(objArr[i]);
            sb.append('=');
            Object obj2 = objArr[i + 1];
            if (obj2 != null) {
                sb.append(obj2);
            }
        }
        return sb.toString();
    }

    public static HttpRequest get(CharSequence charSequence) throws HttpRequestException {
        return new HttpRequest(charSequence, "GET");
    }

    public static HttpRequest get(URL url) throws HttpRequestException {
        return new HttpRequest(url, "GET");
    }

    public static HttpRequest get(CharSequence charSequence, Map<?, ?> map, boolean z) throws HttpRequestException {
        String strAppend = append(charSequence, map);
        if (z) {
            strAppend = encode(strAppend);
        }
        return get(strAppend);
    }

    public static HttpRequest get(CharSequence charSequence, boolean z, Object... objArr) throws HttpRequestException {
        String strAppend = append(charSequence, objArr);
        if (z) {
            strAppend = encode(strAppend);
        }
        return get(strAppend);
    }

    public static HttpRequest post(CharSequence charSequence) throws HttpRequestException {
        HttpRequest httpRequest = new HttpRequest(charSequence, "POST");
        httpRequest.getConnection().setConnectTimeout(connectTimeout);
        httpRequest.getConnection().setReadTimeout(readTimeOut);
        return httpRequest;
    }

    public static HttpRequest post(URL url) throws HttpRequestException {
        HttpRequest httpRequest = new HttpRequest(url, "POST");
        httpRequest.getConnection().setConnectTimeout(connectTimeout);
        httpRequest.getConnection().setReadTimeout(readTimeOut);
        return httpRequest;
    }

    public static HttpRequest post(CharSequence charSequence, Map<?, ?> map, boolean z) throws HttpRequestException {
        String strAppend = append(charSequence, map);
        if (z) {
            strAppend = encode(strAppend);
        }
        return post(strAppend);
    }

    public static HttpRequest post(CharSequence charSequence, boolean z, Object... objArr) throws HttpRequestException {
        String strAppend = append(charSequence, objArr);
        if (z) {
            strAppend = encode(strAppend);
        }
        return post(strAppend);
    }

    public static HttpRequest put(CharSequence charSequence) throws HttpRequestException {
        return new HttpRequest(charSequence, METHOD_PUT);
    }

    public static HttpRequest put(URL url) throws HttpRequestException {
        return new HttpRequest(url, METHOD_PUT);
    }

    public static HttpRequest put(CharSequence charSequence, Map<?, ?> map, boolean z) throws HttpRequestException {
        String strAppend = append(charSequence, map);
        if (z) {
            strAppend = encode(strAppend);
        }
        return put(strAppend);
    }

    public static HttpRequest put(CharSequence charSequence, boolean z, Object... objArr) throws HttpRequestException {
        String strAppend = append(charSequence, objArr);
        if (z) {
            strAppend = encode(strAppend);
        }
        return put(strAppend);
    }

    public static HttpRequest delete(CharSequence charSequence) throws HttpRequestException {
        return new HttpRequest(charSequence, METHOD_DELETE);
    }

    public static HttpRequest delete(URL url) throws HttpRequestException {
        return new HttpRequest(url, METHOD_DELETE);
    }

    public static HttpRequest delete(CharSequence charSequence, Map<?, ?> map, boolean z) throws HttpRequestException {
        String strAppend = append(charSequence, map);
        if (z) {
            strAppend = encode(strAppend);
        }
        return delete(strAppend);
    }

    public static HttpRequest delete(CharSequence charSequence, boolean z, Object... objArr) throws HttpRequestException {
        String strAppend = append(charSequence, objArr);
        if (z) {
            strAppend = encode(strAppend);
        }
        return delete(strAppend);
    }

    public static HttpRequest head(CharSequence charSequence) throws HttpRequestException {
        return new HttpRequest(charSequence, METHOD_HEAD);
    }

    public static HttpRequest head(URL url) throws HttpRequestException {
        return new HttpRequest(url, METHOD_HEAD);
    }

    public static HttpRequest head(CharSequence charSequence, Map<?, ?> map, boolean z) throws HttpRequestException {
        String strAppend = append(charSequence, map);
        if (z) {
            strAppend = encode(strAppend);
        }
        return head(strAppend);
    }

    public static HttpRequest head(CharSequence charSequence, boolean z, Object... objArr) throws HttpRequestException {
        String strAppend = append(charSequence, objArr);
        if (z) {
            strAppend = encode(strAppend);
        }
        return head(strAppend);
    }

    public static HttpRequest options(CharSequence charSequence) throws HttpRequestException {
        return new HttpRequest(charSequence, METHOD_OPTIONS);
    }

    public static HttpRequest options(URL url) throws HttpRequestException {
        return new HttpRequest(url, METHOD_OPTIONS);
    }

    public static HttpRequest trace(CharSequence charSequence) throws HttpRequestException {
        return new HttpRequest(charSequence, METHOD_TRACE);
    }

    public static HttpRequest trace(URL url) throws HttpRequestException {
        return new HttpRequest(url, METHOD_TRACE);
    }

    public static void keepAlive(boolean z) {
        setProperty("http.keepAlive", Boolean.toString(z));
    }

    public static void proxyHost(String str) {
        setProperty("http.proxyHost", str);
        setProperty("https.proxyHost", str);
    }

    public static void proxyPort(int i) {
        String string = Integer.toString(i);
        setProperty("http.proxyPort", string);
        setProperty("https.proxyPort", string);
    }

    public static void nonProxyHosts(String... strArr) {
        if (strArr != null && strArr.length > 0) {
            StringBuilder sb = new StringBuilder();
            int length = strArr.length - 1;
            for (int i = 0; i < length; i++) {
                sb.append(strArr[i]);
                sb.append('|');
            }
            sb.append(strArr[length]);
            setProperty("http.nonProxyHosts", sb.toString());
            return;
        }
        setProperty("http.nonProxyHosts", null);
    }

    private static String setProperty(final String str, final String str2) {
        PrivilegedAction<String> privilegedAction;
        if (str2 != null) {
            privilegedAction = new PrivilegedAction<String>() { // from class: com.umeng.message.util.HttpRequest.4
                @Override // java.security.PrivilegedAction
                /* renamed from: a, reason: merged with bridge method [inline-methods] */
                public String run() {
                    return System.setProperty(str, str2);
                }
            };
        } else {
            privilegedAction = new PrivilegedAction<String>() { // from class: com.umeng.message.util.HttpRequest.5
                @Override // java.security.PrivilegedAction
                /* renamed from: a, reason: merged with bridge method [inline-methods] */
                public String run() {
                    return System.clearProperty(str);
                }
            };
        }
        return (String) AccessController.doPrivileged(privilegedAction);
    }

    public HttpRequest(CharSequence charSequence, String str) throws HttpRequestException {
        try {
            this.url = new URL(charSequence.toString());
            this.requestMethod = str;
        } catch (MalformedURLException e2) {
            throw new HttpRequestException(e2);
        }
    }

    public HttpRequest(URL url, String str) throws HttpRequestException {
        this.url = url;
        this.requestMethod = str;
    }

    private Proxy createProxy() {
        return new Proxy(Proxy.Type.HTTP, new InetSocketAddress(this.httpProxyHost, this.httpProxyPort));
    }

    private HttpURLConnection createConnection() throws ProtocolException {
        HttpURLConnection httpURLConnectionCreate;
        try {
            if (this.httpProxyHost != null) {
                httpURLConnectionCreate = CONNECTION_FACTORY.create(this.url, createProxy());
            } else {
                httpURLConnectionCreate = CONNECTION_FACTORY.create(this.url);
            }
            httpURLConnectionCreate.setRequestMethod(this.requestMethod);
            return httpURLConnectionCreate;
        } catch (IOException e2) {
            throw new HttpRequestException(e2);
        }
    }

    public String toString() {
        return method() + ' ' + url();
    }

    public HttpURLConnection getConnection() {
        if (this.connection == null) {
            this.connection = createConnection();
        }
        return this.connection;
    }

    public HttpRequest ignoreCloseExceptions(boolean z) {
        this.ignoreCloseExceptions = z;
        return this;
    }

    public boolean ignoreCloseExceptions() {
        return this.ignoreCloseExceptions;
    }

    public int code() throws HttpRequestException {
        try {
            closeOutput();
            return getConnection().getResponseCode();
        } catch (IOException e2) {
            throw new HttpRequestException(e2);
        }
    }

    public HttpRequest code(AtomicInteger atomicInteger) throws HttpRequestException {
        atomicInteger.set(code());
        return this;
    }

    public boolean ok() throws HttpRequestException {
        return 200 == code();
    }

    public boolean created() throws HttpRequestException {
        return 201 == code();
    }

    public boolean serverError() throws HttpRequestException {
        return 500 == code();
    }

    public boolean badRequest() throws HttpRequestException {
        return 400 == code();
    }

    public boolean notFound() throws HttpRequestException {
        return 404 == code();
    }

    public boolean notModified() throws HttpRequestException {
        return 304 == code();
    }

    public String message() throws HttpRequestException {
        try {
            closeOutput();
            return getConnection().getResponseMessage();
        } catch (IOException e2) {
            throw new HttpRequestException(e2);
        }
    }

    public HttpRequest disconnect() {
        getConnection().disconnect();
        return this;
    }

    public HttpRequest chunk(int i) {
        getConnection().setChunkedStreamingMode(i);
        return this;
    }

    public HttpRequest bufferSize(int i) {
        if (i < 1) {
            throw new IllegalArgumentException("Size must be greater than zero");
        }
        this.bufferSize = i;
        return this;
    }

    public int bufferSize() {
        return this.bufferSize;
    }

    public HttpRequest uncompress(boolean z) {
        this.uncompress = z;
        return this;
    }

    protected ByteArrayOutputStream byteStream() {
        int iContentLength = contentLength();
        if (iContentLength > 0) {
            return new ByteArrayOutputStream(iContentLength);
        }
        return new ByteArrayOutputStream();
    }

    public String body(String str) throws HttpRequestException {
        ByteArrayOutputStream byteArrayOutputStreamByteStream = byteStream();
        try {
            copy(buffer(), byteArrayOutputStreamByteStream);
            return byteArrayOutputStreamByteStream.toString(getValidCharset(str));
        } catch (IOException e2) {
            throw new HttpRequestException(e2);
        }
    }

    public String body() throws HttpRequestException {
        return body(charset());
    }

    public HttpRequest body(AtomicReference<String> atomicReference) throws HttpRequestException {
        atomicReference.set(body());
        return this;
    }

    public HttpRequest body(AtomicReference<String> atomicReference, String str) throws HttpRequestException {
        atomicReference.set(body(str));
        return this;
    }

    public boolean isBodyEmpty() throws HttpRequestException {
        return contentLength() == 0;
    }

    public byte[] bytes() throws HttpRequestException {
        ByteArrayOutputStream byteArrayOutputStreamByteStream = byteStream();
        try {
            copy(buffer(), byteArrayOutputStreamByteStream);
            return byteArrayOutputStreamByteStream.toByteArray();
        } catch (IOException e2) {
            throw new HttpRequestException(e2);
        }
    }

    public BufferedInputStream buffer() throws HttpRequestException {
        return new BufferedInputStream(stream(), this.bufferSize);
    }

    public InputStream stream() throws HttpRequestException {
        InputStream inputStream;
        if (code() < 400) {
            try {
                inputStream = getConnection().getInputStream();
            } catch (IOException e2) {
                throw new HttpRequestException(e2);
            }
        } else {
            inputStream = getConnection().getErrorStream();
            if (inputStream == null) {
                try {
                    inputStream = getConnection().getInputStream();
                } catch (IOException e3) {
                    throw new HttpRequestException(e3);
                }
            }
        }
        if (!this.uncompress || !ENCODING_GZIP.equals(contentEncoding())) {
            return inputStream;
        }
        try {
            return new GZIPInputStream(inputStream);
        } catch (IOException e4) {
            throw new HttpRequestException(e4);
        }
    }

    public InputStreamReader reader(String str) throws HttpRequestException {
        try {
            return new InputStreamReader(stream(), getValidCharset(str));
        } catch (UnsupportedEncodingException e2) {
            throw new HttpRequestException(e2);
        }
    }

    public InputStreamReader reader() throws HttpRequestException {
        return reader(charset());
    }

    public BufferedReader bufferedReader(String str) throws HttpRequestException {
        return new BufferedReader(reader(str), this.bufferSize);
    }

    public BufferedReader bufferedReader() throws HttpRequestException {
        return bufferedReader(charset());
    }

    public HttpRequest receive(File file) throws HttpRequestException {
        try {
            final BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file), this.bufferSize);
            return new b<HttpRequest>(bufferedOutputStream, this.ignoreCloseExceptions) { // from class: com.umeng.message.util.HttpRequest.6
                /* JADX INFO: Access modifiers changed from: protected */
                @Override // com.umeng.message.util.HttpRequest.d
                /* renamed from: a, reason: merged with bridge method [inline-methods] */
                public HttpRequest b() throws HttpRequestException, IOException {
                    return HttpRequest.this.receive(bufferedOutputStream);
                }
            }.call();
        } catch (FileNotFoundException e2) {
            throw new HttpRequestException(e2);
        }
    }

    public HttpRequest receive(OutputStream outputStream) throws HttpRequestException {
        try {
            return copy(buffer(), outputStream);
        } catch (IOException e2) {
            throw new HttpRequestException(e2);
        }
    }

    public HttpRequest receive(PrintStream printStream) throws HttpRequestException {
        return receive((OutputStream) printStream);
    }

    public HttpRequest receive(final Appendable appendable) throws HttpRequestException {
        final BufferedReader bufferedReader = bufferedReader();
        return new b<HttpRequest>(bufferedReader, this.ignoreCloseExceptions) { // from class: com.umeng.message.util.HttpRequest.7
            @Override // com.umeng.message.util.HttpRequest.d
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public HttpRequest b() throws IOException {
                CharBuffer charBufferAllocate = CharBuffer.allocate(HttpRequest.this.bufferSize);
                while (true) {
                    int i = bufferedReader.read(charBufferAllocate);
                    if (i != -1) {
                        charBufferAllocate.rewind();
                        appendable.append(charBufferAllocate, 0, i);
                        charBufferAllocate.rewind();
                    } else {
                        return HttpRequest.this;
                    }
                }
            }
        }.call();
    }

    public HttpRequest receive(final Writer writer) throws HttpRequestException {
        final BufferedReader bufferedReader = bufferedReader();
        return new b<HttpRequest>(bufferedReader, this.ignoreCloseExceptions) { // from class: com.umeng.message.util.HttpRequest.8
            @Override // com.umeng.message.util.HttpRequest.d
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public HttpRequest b() throws IOException {
                return HttpRequest.this.copy(bufferedReader, writer);
            }
        }.call();
    }

    public HttpRequest readTimeout(int i) {
        getConnection().setReadTimeout(i);
        return this;
    }

    public HttpRequest connectTimeout(int i) {
        getConnection().setConnectTimeout(i);
        return this;
    }

    public HttpRequest header(String str, String str2) {
        getConnection().setRequestProperty(str, str2);
        return this;
    }

    public HttpRequest header(String str, Number number) {
        return header(str, number != null ? number.toString() : null);
    }

    public HttpRequest headers(Map<String, String> map) {
        if (!map.isEmpty()) {
            Iterator<Map.Entry<String, String>> it = map.entrySet().iterator();
            while (it.hasNext()) {
                header(it.next());
            }
        }
        return this;
    }

    public HttpRequest header(Map.Entry<String, String> entry) {
        return header(entry.getKey(), entry.getValue());
    }

    public String header(String str) throws HttpRequestException {
        closeOutputQuietly();
        return getConnection().getHeaderField(str);
    }

    public Map<String, List<String>> headers() throws HttpRequestException {
        closeOutputQuietly();
        return getConnection().getHeaderFields();
    }

    public long dateHeader(String str) throws HttpRequestException {
        return dateHeader(str, -1L);
    }

    public long dateHeader(String str, long j) throws HttpRequestException {
        closeOutputQuietly();
        return getConnection().getHeaderFieldDate(str, j);
    }

    public int intHeader(String str) throws HttpRequestException {
        return intHeader(str, -1);
    }

    public int intHeader(String str, int i) throws HttpRequestException {
        closeOutputQuietly();
        return getConnection().getHeaderFieldInt(str, i);
    }

    public String[] headers(String str) throws HttpRequestException {
        Map<String, List<String>> mapHeaders = headers();
        if (mapHeaders == null || mapHeaders.isEmpty()) {
            return EMPTY_STRINGS;
        }
        List<String> list = mapHeaders.get(str);
        if (list != null && !list.isEmpty()) {
            return (String[]) list.toArray(new String[list.size()]);
        }
        return EMPTY_STRINGS;
    }

    public String parameter(String str, String str2) {
        return getParam(header(str), str2);
    }

    public Map<String, String> parameters(String str) {
        return getParams(header(str));
    }

    /* JADX WARN: Removed duplicated region for block: B:29:0x006e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    protected java.util.Map<java.lang.String, java.lang.String> getParams(java.lang.String r12) {
        /*
            r11 = this;
            if (r12 == 0) goto L81
            int r0 = r12.length()
            if (r0 != 0) goto La
            goto L81
        La:
            int r0 = r12.length()
            r1 = 59
            int r2 = r12.indexOf(r1)
            r3 = 1
            int r2 = r2 + r3
            if (r2 == 0) goto L7c
            if (r2 != r0) goto L1b
            goto L7c
        L1b:
            int r4 = r12.indexOf(r1, r2)
            r5 = -1
            if (r4 != r5) goto L23
            r4 = r0
        L23:
            java.util.LinkedHashMap r6 = new java.util.LinkedHashMap
            r6.<init>()
        L28:
            if (r2 >= r4) goto L7b
            r7 = 61
            int r7 = r12.indexOf(r7, r2)
            if (r7 == r5) goto L71
            if (r7 >= r4) goto L71
            java.lang.String r2 = r12.substring(r2, r7)
            java.lang.String r2 = r2.trim()
            int r8 = r2.length()
            if (r8 <= 0) goto L71
            int r7 = r7 + 1
            java.lang.String r7 = r12.substring(r7, r4)
            java.lang.String r7 = r7.trim()
            int r8 = r7.length()
            if (r8 == 0) goto L71
            r9 = 2
            if (r8 <= r9) goto L6e
            r9 = 0
            char r9 = r7.charAt(r9)
            r10 = 34
            if (r10 != r9) goto L6e
            int r8 = r8 + (-1)
            char r9 = r7.charAt(r8)
            if (r10 != r9) goto L6e
            java.lang.String r7 = r7.substring(r3, r8)
            r6.put(r2, r7)
            goto L71
        L6e:
            r6.put(r2, r7)
        L71:
            int r2 = r4 + 1
            int r4 = r12.indexOf(r1, r2)
            if (r4 != r5) goto L28
            r4 = r0
            goto L28
        L7b:
            return r6
        L7c:
            java.util.Map r12 = java.util.Collections.emptyMap()
            return r12
        L81:
            java.util.Map r12 = java.util.Collections.emptyMap()
            return r12
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.message.util.HttpRequest.getParams(java.lang.String):java.util.Map");
    }

    protected String getParam(String str, String str2) {
        String strTrim;
        int length;
        if (str == null || str.length() == 0) {
            return null;
        }
        int length2 = str.length();
        int iIndexOf = str.indexOf(59) + 1;
        if (iIndexOf == 0 || iIndexOf == length2) {
            return null;
        }
        int iIndexOf2 = str.indexOf(59, iIndexOf);
        if (iIndexOf2 == -1) {
            iIndexOf2 = length2;
        }
        while (iIndexOf < iIndexOf2) {
            int iIndexOf3 = str.indexOf(61, iIndexOf);
            if (iIndexOf3 != -1 && iIndexOf3 < iIndexOf2 && str2.equals(str.substring(iIndexOf, iIndexOf3).trim()) && (length = (strTrim = str.substring(iIndexOf3 + 1, iIndexOf2).trim()).length()) != 0) {
                if (length > 2 && '\"' == strTrim.charAt(0)) {
                    int i = length - 1;
                    if ('\"' == strTrim.charAt(i)) {
                        return strTrim.substring(1, i);
                    }
                }
                return strTrim;
            }
            iIndexOf = iIndexOf2 + 1;
            iIndexOf2 = str.indexOf(59, iIndexOf);
            if (iIndexOf2 == -1) {
                iIndexOf2 = length2;
            }
        }
        return null;
    }

    public String charset() {
        return parameter("Content-Type", PARAM_CHARSET);
    }

    public HttpRequest userAgent(String str) {
        return header("User-Agent", str);
    }

    public HttpRequest referer(String str) {
        return header(HEADER_REFERER, str);
    }

    public HttpRequest useCaches(boolean z) {
        getConnection().setUseCaches(z);
        return this;
    }

    public HttpRequest acceptEncoding(String str) {
        return header(HEADER_ACCEPT_ENCODING, str);
    }

    public HttpRequest acceptGzipEncoding() {
        return acceptEncoding(ENCODING_GZIP);
    }

    public HttpRequest acceptCharset(String str) {
        return header(HEADER_ACCEPT_CHARSET, str);
    }

    public String contentEncoding() {
        return header(HEADER_CONTENT_ENCODING);
    }

    public String server() {
        return header(HEADER_SERVER);
    }

    public long date() {
        return dateHeader(HEADER_DATE);
    }

    public String cacheControl() {
        return header("Cache-Control");
    }

    public String eTag() {
        return header(HEADER_ETAG);
    }

    public long expires() {
        return dateHeader(HEADER_EXPIRES);
    }

    public long lastModified() {
        return dateHeader("Last-Modified");
    }

    public String location() {
        return header(HEADER_LOCATION);
    }

    public HttpRequest authorization(String str) {
        return header(HEADER_AUTHORIZATION, str);
    }

    public HttpRequest proxyAuthorization(String str) {
        return header(HEADER_PROXY_AUTHORIZATION, str);
    }

    public HttpRequest basic(String str, String str2) {
        return authorization("Basic " + a.a(str + ':' + str2));
    }

    public HttpRequest proxyBasic(String str, String str2) {
        return proxyAuthorization("Basic " + a.a(str + ':' + str2));
    }

    public HttpRequest ifModifiedSince(long j) {
        getConnection().setIfModifiedSince(j);
        return this;
    }

    public HttpRequest ifNoneMatch(String str) {
        return header(HEADER_IF_NONE_MATCH, str);
    }

    public HttpRequest contentType(String str) {
        return contentType(str, null);
    }

    public HttpRequest contentType(String str, String str2) {
        if (str2 != null && str2.length() > 0) {
            return header("Content-Type", str + "; charset=" + str2);
        }
        return header("Content-Type", str);
    }

    public String contentType() {
        return header("Content-Type");
    }

    public int contentLength() {
        return intHeader("Content-Length");
    }

    public HttpRequest contentLength(String str) {
        return contentLength(Integer.parseInt(str));
    }

    public HttpRequest contentLength(int i) {
        getConnection().setFixedLengthStreamingMode(i);
        return this;
    }

    public HttpRequest accept(String str) {
        return header(HEADER_ACCEPT, str);
    }

    public HttpRequest acceptJson() {
        return accept(CONTENT_TYPE_JSON);
    }

    protected HttpRequest copy(final InputStream inputStream, final OutputStream outputStream) throws IOException {
        return new b<HttpRequest>(inputStream, this.ignoreCloseExceptions) { // from class: com.umeng.message.util.HttpRequest.9
            @Override // com.umeng.message.util.HttpRequest.d
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public HttpRequest b() throws IOException {
                byte[] bArr = new byte[HttpRequest.this.bufferSize];
                while (true) {
                    int i = inputStream.read(bArr);
                    if (i != -1) {
                        outputStream.write(bArr, 0, i);
                    } else {
                        return HttpRequest.this;
                    }
                }
            }
        }.call();
    }

    protected HttpRequest copy(final Reader reader, final Writer writer) throws IOException {
        return new b<HttpRequest>(reader, this.ignoreCloseExceptions) { // from class: com.umeng.message.util.HttpRequest.10
            @Override // com.umeng.message.util.HttpRequest.d
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public HttpRequest b() throws IOException {
                char[] cArr = new char[HttpRequest.this.bufferSize];
                while (true) {
                    int i = reader.read(cArr);
                    if (i != -1) {
                        writer.write(cArr, 0, i);
                    } else {
                        return HttpRequest.this;
                    }
                }
            }
        }.call();
    }

    protected HttpRequest closeOutput() throws IOException {
        if (this.output == null) {
            return this;
        }
        if (this.multipart) {
            this.output.a("\r\n--00content0boundary00--\r\n");
        }
        if (this.ignoreCloseExceptions) {
            try {
                this.output.close();
            } catch (IOException unused) {
            }
        } else {
            this.output.close();
        }
        this.output = null;
        return this;
    }

    protected HttpRequest closeOutputQuietly() throws HttpRequestException {
        try {
            return closeOutput();
        } catch (IOException e2) {
            throw new HttpRequestException(e2);
        }
    }

    protected HttpRequest openOutput() throws IOException {
        if (this.output != null) {
            return this;
        }
        getConnection().setDoOutput(true);
        this.output = new e(getConnection().getOutputStream(), getParam(getConnection().getRequestProperty("Content-Type"), PARAM_CHARSET), this.bufferSize);
        return this;
    }

    protected HttpRequest startPart() throws IOException {
        if (!this.multipart) {
            this.multipart = true;
            contentType(CONTENT_TYPE_MULTIPART).openOutput();
            this.output.a("--00content0boundary00\r\n");
        } else {
            this.output.a("\r\n--00content0boundary00\r\n");
        }
        return this;
    }

    protected HttpRequest writePartHeader(String str, String str2) throws IOException {
        return writePartHeader(str, str2, null);
    }

    protected HttpRequest writePartHeader(String str, String str2, String str3) throws HttpRequestException, IOException {
        StringBuilder sb = new StringBuilder();
        sb.append("form-data; name=\"");
        sb.append(str);
        if (str2 != null) {
            sb.append("\"; filename=\"");
            sb.append(str2);
        }
        sb.append('\"');
        partHeader(DownloadUtils.CONTENT_DISPOSITION, sb.toString());
        if (str3 != null) {
            partHeader("Content-Type", str3);
        }
        return send("\r\n");
    }

    public HttpRequest part(String str, String str2) {
        return part(str, (String) null, str2);
    }

    public HttpRequest part(String str, String str2, String str3) throws HttpRequestException {
        return part(str, str2, (String) null, str3);
    }

    public HttpRequest part(String str, String str2, String str3, String str4) throws HttpRequestException {
        try {
            startPart();
            writePartHeader(str, str2, str3);
            this.output.a(str4);
            return this;
        } catch (IOException e2) {
            throw new HttpRequestException(e2);
        }
    }

    public HttpRequest part(String str, Number number) throws HttpRequestException {
        return part(str, (String) null, number);
    }

    public HttpRequest part(String str, String str2, Number number) throws HttpRequestException {
        return part(str, str2, number != null ? number.toString() : null);
    }

    public HttpRequest part(String str, File file) throws HttpRequestException {
        return part(str, (String) null, file);
    }

    public HttpRequest part(String str, String str2, File file) throws HttpRequestException {
        return part(str, str2, (String) null, file);
    }

    public HttpRequest part(String str, String str2, String str3, File file) throws HttpRequestException {
        try {
            return part(str, str2, str3, new BufferedInputStream(new FileInputStream(file)));
        } catch (IOException e2) {
            throw new HttpRequestException(e2);
        }
    }

    public HttpRequest part(String str, InputStream inputStream) throws HttpRequestException {
        return part(str, (String) null, (String) null, inputStream);
    }

    public HttpRequest part(String str, String str2, String str3, InputStream inputStream) throws HttpRequestException {
        try {
            startPart();
            writePartHeader(str, str2, str3);
            copy(inputStream, this.output);
            return this;
        } catch (IOException e2) {
            throw new HttpRequestException(e2);
        }
    }

    public HttpRequest partHeader(String str, String str2) throws HttpRequestException {
        return send(str).send(": ").send(str2).send("\r\n");
    }

    public HttpRequest send(File file) throws HttpRequestException {
        try {
            return send(new BufferedInputStream(new FileInputStream(file)));
        } catch (FileNotFoundException e2) {
            throw new HttpRequestException(e2);
        }
    }

    public HttpRequest send(byte[] bArr) throws HttpRequestException {
        return send(new ByteArrayInputStream(bArr));
    }

    public HttpRequest send(InputStream inputStream) throws HttpRequestException {
        try {
            openOutput();
            copy(inputStream, this.output);
            return this;
        } catch (IOException e2) {
            throw new HttpRequestException(e2);
        }
    }

    public HttpRequest send(final Reader reader) throws HttpRequestException {
        try {
            openOutput();
            final OutputStreamWriter outputStreamWriter = new OutputStreamWriter(this.output, this.output.a.charset());
            return new c<HttpRequest>(outputStreamWriter) { // from class: com.umeng.message.util.HttpRequest.2
                /* JADX INFO: Access modifiers changed from: protected */
                @Override // com.umeng.message.util.HttpRequest.d
                /* renamed from: a, reason: merged with bridge method [inline-methods] */
                public HttpRequest b() throws IOException {
                    return HttpRequest.this.copy(reader, outputStreamWriter);
                }
            }.call();
        } catch (IOException e2) {
            throw new HttpRequestException(e2);
        }
    }

    public HttpRequest send(CharSequence charSequence) throws HttpRequestException {
        try {
            openOutput();
            this.output.a(charSequence.toString());
            return this;
        } catch (IOException e2) {
            throw new HttpRequestException(e2);
        }
    }

    public OutputStreamWriter writer() throws HttpRequestException {
        try {
            openOutput();
            return new OutputStreamWriter(this.output, this.output.a.charset());
        } catch (IOException e2) {
            throw new HttpRequestException(e2);
        }
    }

    public HttpRequest form(Map<?, ?> map) throws HttpRequestException {
        return form(map, "UTF-8");
    }

    public HttpRequest form(Map.Entry<?, ?> entry) throws HttpRequestException {
        return form(entry, "UTF-8");
    }

    public HttpRequest form(Map.Entry<?, ?> entry, String str) throws HttpRequestException {
        return form(entry.getKey(), entry.getValue(), str);
    }

    public HttpRequest form(Object obj, Object obj2) throws HttpRequestException {
        return form(obj, obj2, "UTF-8");
    }

    public HttpRequest form(Object obj, Object obj2, String str) throws HttpRequestException {
        boolean z = !this.form;
        if (z) {
            contentType(CONTENT_TYPE_FORM, str);
            this.form = true;
        }
        String validCharset = getValidCharset(str);
        try {
            openOutput();
            if (!z) {
                this.output.write(38);
            }
            this.output.a(URLEncoder.encode(obj.toString(), validCharset));
            this.output.write(61);
            if (obj2 != null) {
                this.output.a(URLEncoder.encode(obj2.toString(), validCharset));
            }
            return this;
        } catch (IOException e2) {
            throw new HttpRequestException(e2);
        }
    }

    public HttpRequest form(Map<?, ?> map, String str) throws HttpRequestException {
        if (!map.isEmpty()) {
            Iterator<Map.Entry<?, ?>> it = map.entrySet().iterator();
            while (it.hasNext()) {
                form(it.next(), str);
            }
        }
        return this;
    }

    public HttpRequest trustAllHosts() {
        HttpURLConnection connection = getConnection();
        if (connection instanceof HttpsURLConnection) {
            ((HttpsURLConnection) connection).setHostnameVerifier(getTrustedVerifier());
        }
        return this;
    }

    public HttpRequest trustHosts() {
        HttpURLConnection connection = getConnection();
        if (connection instanceof HttpsURLConnection) {
            HttpsURLConnection httpsURLConnection = (HttpsURLConnection) connection;
            httpsURLConnection.setHostnameVerifier(getTrustedVerifier(httpsURLConnection));
        }
        return this;
    }

    public URL url() {
        return getConnection().getURL();
    }

    public String method() {
        return getConnection().getRequestMethod();
    }

    public HttpRequest useProxy(String str, int i) {
        if (this.connection != null) {
            throw new IllegalStateException("The connection has already been created. This method must be called before reading or writing to the request.");
        }
        this.httpProxyHost = str;
        this.httpProxyPort = i;
        return this;
    }

    public HttpRequest followRedirects(boolean z) {
        getConnection();
        HttpURLConnection.setFollowRedirects(z);
        return this;
    }
}
