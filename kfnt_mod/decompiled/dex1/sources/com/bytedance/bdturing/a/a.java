package com.bytedance.bdturing.a;

import com.bytedance.bdturing.e;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONException;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class a {
    private static int d = 5000;

    /* renamed from: a, reason: collision with root package name */
    private String f3750a;

    /* renamed from: b, reason: collision with root package name */
    private InterfaceC0107a f3751b;

    /* renamed from: c, reason: collision with root package name */
    private String f3752c = "GET";
    private HttpURLConnection e = null;

    /* renamed from: com.bytedance.bdturing.a.a$a, reason: collision with other inner class name */
    public interface InterfaceC0107a {
        void a(a aVar, int i, String str);
    }

    a(String str, InterfaceC0107a interfaceC0107a) {
        this.f3751b = interfaceC0107a;
        this.f3750a = str;
    }

    private String a(InputStream inputStream, int i) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String line = bufferedReader.readLine();
        bufferedReader.close();
        return line;
    }

    public void a() throws JSONException {
        String strA;
        int i;
        long jCurrentTimeMillis = System.currentTimeMillis();
        int i2 = 0;
        try {
            try {
                this.e = (HttpURLConnection) new URL(this.f3750a).openConnection();
                this.e.setConnectTimeout(d);
                this.e.setReadTimeout(d);
                this.e.setRequestMethod(this.f3752c);
                this.e.connect();
                int responseCode = this.e.getResponseCode();
                if (responseCode == 200) {
                    try {
                        int contentLength = this.e.getContentLength();
                        InputStream inputStream = this.e.getInputStream();
                        strA = a(inputStream, contentLength);
                        try {
                            inputStream.close();
                        } catch (Exception e) {
                            e = e;
                            i = responseCode;
                            e.printStackTrace();
                            e.a(System.currentTimeMillis() - jCurrentTimeMillis, i2);
                            this.f3751b.a(this, i, strA);
                        }
                    } catch (Exception e2) {
                        strA = "";
                        i = responseCode;
                        e = e2;
                    }
                } else {
                    strA = "";
                    i2 = responseCode;
                }
                if (this.e != null) {
                    this.e.disconnect();
                    this.e = null;
                }
                i = responseCode;
            } catch (Exception e3) {
                e = e3;
                strA = "";
                i = -1;
            }
            e.a(System.currentTimeMillis() - jCurrentTimeMillis, i2);
            this.f3751b.a(this, i, strA);
        } finally {
            if (this.e != null) {
                this.e.disconnect();
                this.e = null;
            }
        }
    }
}
