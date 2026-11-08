package anet.channel.request;

import android.text.TextUtils;
import anet.channel.statist.RequestStatistic;
import anet.channel.util.ALog;
import anet.channel.util.HttpConstant;
import anet.channel.util.HttpUrl;
import com.bytedance.sdk.adnet.game.http.cb.NetCallback;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSocketFactory;
import org.json.JSONException;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class Request {
    public static final String DEFAULT_CHARSET = "UTF-8";

    /* renamed from: a, reason: collision with root package name */
    public final RequestStatistic f475a;

    /* renamed from: b, reason: collision with root package name */
    private HttpUrl f476b;

    /* renamed from: c, reason: collision with root package name */
    private HttpUrl f477c;
    private HttpUrl d;
    private URL e;
    private String f;
    private Map<String, String> g;
    private Map<String, String> h;
    private String i;
    private BodyEntry j;
    private boolean k;
    private String l;
    private String m;
    private int n;
    private int o;
    private int p;
    private HostnameVerifier q;
    private SSLSocketFactory r;

    public static final class Method {
        public static final String DELETE = "DELETE";
        public static final String GET = "GET";
        public static final String HEAD = "HEAD";
        public static final String OPTION = "OPTIONS";
        public static final String POST = "POST";
        public static final String PUT = "PUT";

        static boolean a(String str) {
            return str.equals("POST") || str.equals(PUT);
        }

        static boolean b(String str) {
            return a(str) || str.equals(DELETE) || str.equals(OPTION);
        }
    }

    private Request(Builder builder) {
        this.f = "GET";
        this.k = true;
        this.n = 0;
        this.o = NetCallback.ERROR_CODE_REQUEST_SUCCESS;
        this.p = NetCallback.ERROR_CODE_REQUEST_SUCCESS;
        this.f = builder.f480c;
        this.g = builder.d;
        this.h = builder.e;
        this.j = builder.g;
        this.i = builder.f;
        this.k = builder.h;
        this.n = builder.i;
        this.q = builder.j;
        this.r = builder.k;
        this.l = builder.l;
        this.m = builder.m;
        this.o = builder.n;
        this.p = builder.o;
        this.f476b = builder.f478a;
        this.f477c = builder.f479b;
        if (this.f477c == null) {
            a();
        }
        this.f475a = builder.p != null ? builder.p : new RequestStatistic(getHost(), this.l);
    }

    public Builder newBuilder() {
        Builder builder = new Builder();
        builder.f480c = this.f;
        builder.d = this.g;
        builder.e = this.h;
        builder.g = this.j;
        builder.f = this.i;
        builder.h = this.k;
        builder.i = this.n;
        builder.j = this.q;
        builder.k = this.r;
        builder.f478a = this.f476b;
        builder.f479b = this.f477c;
        builder.l = this.l;
        builder.m = this.m;
        builder.n = this.o;
        builder.o = this.p;
        builder.p = this.f475a;
        return builder;
    }

    public HttpUrl getHttpUrl() {
        return this.f477c;
    }

    public String getUrlString() {
        return this.f477c.urlString();
    }

    public URL getUrl() {
        if (this.e == null) {
            this.e = (this.d != null ? this.d : this.f477c).toURL();
        }
        return this.e;
    }

    public void setDnsOptimize(String str, int i) throws JSONException {
        if (str != null) {
            if (this.d == null) {
                this.d = new HttpUrl(this.f477c);
            }
            this.d.replaceIpAndPort(str, i);
        } else {
            this.d = null;
        }
        this.e = null;
        this.f475a.setIPAndPort(str, i);
    }

    public void setUrlScheme(boolean z) {
        if (this.d == null) {
            this.d = new HttpUrl(this.f477c);
        }
        this.d.setScheme(z ? "https" : HttpConstant.HTTP);
        this.e = null;
    }

    public int getRedirectTimes() {
        return this.n;
    }

    public String getHost() {
        return this.f477c.host();
    }

    public String getMethod() {
        return this.f;
    }

    public Map<String, String> getHeaders() {
        return Collections.unmodifiableMap(this.g);
    }

    public String getContentEncoding() {
        return this.i != null ? this.i : "UTF-8";
    }

    public boolean isRedirectEnable() {
        return this.k;
    }

    public HostnameVerifier getHostnameVerifier() {
        return this.q;
    }

    public SSLSocketFactory getSslSocketFactory() {
        return this.r;
    }

    public int postBody(OutputStream outputStream) throws IOException {
        if (this.j != null) {
            return this.j.writeTo(outputStream);
        }
        return 0;
    }

    public byte[] getBodyBytes() {
        if (this.j == null) {
            return null;
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(128);
        try {
            postBody(byteArrayOutputStream);
        } catch (IOException unused) {
        }
        return byteArrayOutputStream.toByteArray();
    }

    public boolean containsBody() {
        return this.j != null;
    }

    public String getBizId() {
        return this.l;
    }

    public String getSeq() {
        return this.m;
    }

    public int getReadTimeout() {
        return this.p;
    }

    public int getConnectTimeout() {
        return this.o;
    }

    private void a() {
        String strA = anet.channel.strategy.utils.c.a(this.h, getContentEncoding());
        if (!TextUtils.isEmpty(strA)) {
            if (!Method.a(this.f) || this.j != null) {
                String strUrlString = this.f476b.urlString();
                StringBuilder sb = new StringBuilder(strUrlString);
                if (sb.indexOf("?") == -1) {
                    sb.append('?');
                } else if (strUrlString.charAt(strUrlString.length() - 1) != '&') {
                    sb.append('&');
                }
                sb.append(strA);
                HttpUrl httpUrl = HttpUrl.parse(sb.toString());
                if (httpUrl != null) {
                    this.f477c = httpUrl;
                }
            } else {
                try {
                    this.j = new ByteArrayEntry(strA.getBytes(getContentEncoding()));
                    this.g.put("Content-Type", "application/x-www-form-urlencoded; charset=" + getContentEncoding());
                } catch (UnsupportedEncodingException unused) {
                }
            }
        }
        if (this.f477c == null) {
            this.f477c = this.f476b;
        }
    }

    public static class Builder {

        /* renamed from: a, reason: collision with root package name */
        private HttpUrl f478a;

        /* renamed from: b, reason: collision with root package name */
        private HttpUrl f479b;
        private Map<String, String> e;
        private String f;
        private BodyEntry g;
        private HostnameVerifier j;
        private SSLSocketFactory k;
        private String l;
        private String m;

        /* renamed from: c, reason: collision with root package name */
        private String f480c = "GET";
        private Map<String, String> d = new HashMap();
        private boolean h = true;
        private int i = 0;
        private int n = NetCallback.ERROR_CODE_REQUEST_SUCCESS;
        private int o = NetCallback.ERROR_CODE_REQUEST_SUCCESS;
        private RequestStatistic p = null;

        public Builder setUrl(HttpUrl httpUrl) {
            this.f478a = httpUrl;
            this.f479b = null;
            return this;
        }

        public Builder setUrl(String str) {
            this.f478a = HttpUrl.parse(str);
            this.f479b = null;
            if (this.f478a != null) {
                return this;
            }
            throw new IllegalArgumentException("toURL is invalid! toURL = " + str);
        }

        public Builder setMethod(String str) {
            if (TextUtils.isEmpty(str)) {
                throw new IllegalArgumentException("method is null or empty");
            }
            if ("GET".equalsIgnoreCase(str)) {
                this.f480c = "GET";
            } else if ("POST".equalsIgnoreCase(str)) {
                this.f480c = "POST";
            } else if (Method.OPTION.equalsIgnoreCase(str)) {
                this.f480c = Method.OPTION;
            } else if (Method.HEAD.equalsIgnoreCase(str)) {
                this.f480c = Method.HEAD;
            } else if (Method.PUT.equalsIgnoreCase(str)) {
                this.f480c = Method.PUT;
            } else if (Method.DELETE.equalsIgnoreCase(str)) {
                this.f480c = Method.DELETE;
            } else {
                this.f480c = "GET";
            }
            return this;
        }

        public Builder setHeaders(Map<String, String> map) {
            this.d.clear();
            if (map != null) {
                this.d.putAll(map);
            }
            return this;
        }

        public Builder addHeader(String str, String str2) {
            this.d.put(str, str2);
            return this;
        }

        public Builder setParams(Map<String, String> map) {
            this.e = map;
            this.f479b = null;
            return this;
        }

        public Builder addParam(String str, String str2) {
            if (this.e == null) {
                this.e = new HashMap();
            }
            this.e.put(str, str2);
            this.f479b = null;
            return this;
        }

        public Builder setCharset(String str) {
            this.f = str;
            this.f479b = null;
            return this;
        }

        public Builder setBody(BodyEntry bodyEntry) {
            this.g = bodyEntry;
            return this;
        }

        public Builder setRedirectEnable(boolean z) {
            this.h = z;
            return this;
        }

        public Builder setRedirectTimes(int i) {
            this.i = i;
            return this;
        }

        public Builder setHostnameVerifier(HostnameVerifier hostnameVerifier) {
            this.j = hostnameVerifier;
            return this;
        }

        public Builder setSslSocketFactory(SSLSocketFactory sSLSocketFactory) {
            this.k = sSLSocketFactory;
            return this;
        }

        public Builder setBizId(String str) {
            this.l = str;
            return this;
        }

        public Builder setSeq(String str) {
            this.m = str;
            return this;
        }

        public Builder setReadTimeout(int i) {
            if (i > 0) {
                this.o = i;
            }
            return this;
        }

        public Builder setConnectTimeout(int i) {
            if (i > 0) {
                this.n = i;
            }
            return this;
        }

        public Builder setRequestStatistic(RequestStatistic requestStatistic) {
            this.p = requestStatistic;
            return this;
        }

        public Request build() {
            if (this.g == null && this.e == null && Method.a(this.f480c)) {
                ALog.e("awcn.Request", "method " + this.f480c + " must have a request body", null, new Object[0]);
            }
            if (this.g != null && !Method.b(this.f480c)) {
                ALog.e("awcn.Request", "method " + this.f480c + " should not have a request body", null, new Object[0]);
                this.g = null;
            }
            if (this.g != null && this.g.getContentType() != null) {
                addHeader("Content-Type", this.g.getContentType());
            }
            return new Request(this);
        }
    }
}
