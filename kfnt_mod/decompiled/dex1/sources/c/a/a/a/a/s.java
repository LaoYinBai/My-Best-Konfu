package c.a.a.a.a;

import com.bytedance.sdk.b.d.a;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
class s {

    /* renamed from: a, reason: collision with root package name */
    private String f2115a;

    /* renamed from: b, reason: collision with root package name */
    private String f2116b;

    /* renamed from: c, reason: collision with root package name */
    private byte[] f2117c;
    private String d;
    private Map<String, String> e;
    private v f;
    private HttpURLConnection g;

    s() {
    }

    private byte[] a(int i) throws Exception {
        HttpURLConnection httpURLConnection = this.g;
        if (httpURLConnection == null) {
            throw new Exception("no connection");
        }
        InputStream inputStream = (i == 200 || i == 201 || i == 202) ? this.g.getInputStream() : httpURLConnection.getErrorStream();
        if (inputStream == null) {
            throw new Exception("error get input stream");
        }
        BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr = new byte[1024];
        while (true) {
            int i2 = bufferedInputStream.read(bArr);
            if (i2 <= 0) {
                bufferedInputStream.close();
                return byteArrayOutputStream.toByteArray();
            }
            byteArrayOutputStream.write(bArr, 0, i2);
        }
    }

    private void c() {
        if (this.d.equals("GET")) {
            com.bytedance.gameprotect.o.a(this.f2116b, this.e);
        } else {
            if (!this.d.equals("POST")) {
                throw new RuntimeException("Unsupported http method");
            }
            com.bytedance.gameprotect.o.a(this.f2116b, this.e, this.f2117c);
        }
    }

    private void d() throws Exception {
        if (this.g != null) {
            throw new Exception("already connected");
        }
        HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(this.f2116b).openConnection();
        this.g = httpURLConnection;
        httpURLConnection.setRequestMethod(this.d);
        this.g.setConnectTimeout(y.a());
        this.g.setReadTimeout(y.b());
        this.g.setInstanceFollowRedirects(false);
        this.g.setUseCaches(false);
        Map<String, String> map = this.e;
        if (map != null && map.size() > 0) {
            for (Map.Entry<String, String> entry : this.e.entrySet()) {
                this.g.setRequestProperty(entry.getKey(), entry.getValue());
            }
        }
        if (this.d.equals("POST")) {
            this.g.setDoOutput(true);
            this.g.setDoInput(true);
            if (this.f2117c != null) {
                this.g.setRequestProperty("Content-Type", a.f.f5151a);
                this.g.setFixedLengthStreamingMode(this.f2117c.length);
                OutputStream outputStream = this.g.getOutputStream();
                outputStream.write(this.f2117c);
                outputStream.flush();
                outputStream.close();
            }
        }
    }

    private void e() throws Exception {
        HttpURLConnection httpURLConnection = this.g;
        if (httpURLConnection == null) {
            throw new Exception("no connection");
        }
        int responseCode = httpURLConnection.getResponseCode();
        this.f = new v(responseCode, a(responseCode), g());
    }

    private void f() throws Exception {
        String str = this.d;
        if (str == null) {
            throw new Exception("No method");
        }
        if (!str.equals("GET") && !this.d.equals("POST")) {
            throw new Exception("Unknown http method");
        }
    }

    private HashMap<String, String> g() throws Exception {
        if (this.g == null) {
            throw new Exception("no connection");
        }
        HashMap<String, String> map = new HashMap<>();
        for (Map.Entry<String, List<String>> entry : this.g.getHeaderFields().entrySet()) {
            map.put(entry.getKey(), this.g.getHeaderField(entry.getKey()));
        }
        return map;
    }

    public v a() {
        return this.f;
    }

    void a(String str, String str2, Map<String, Object> map, Map<String, Object> map2, Map<String, Object> map3) {
        this.f2115a = str2;
        this.d = str;
        this.e = ab.b(map3);
        f();
        this.f2116b = ab.a(this.f2115a, map);
        this.f2117c = ab.a(map2);
    }

    void a(String str, byte[] bArr, Map<String, Object> map, Map<String, Object> map2) {
        this.f2115a = str;
        this.d = "POST";
        this.f2117c = bArr;
        this.e = ab.b(map2);
        this.f2116b = ab.a(this.f2115a, map);
    }

    public void b() {
        c();
        d();
        this.g.connect();
        e();
    }
}
