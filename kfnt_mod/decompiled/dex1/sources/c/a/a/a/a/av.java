package c.a.a.a.a;

import com.bytedance.sdk.adnet.game.http.cb.NetCallback;
import com.bytedance.sdk.b.d.a;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class av {

    /* renamed from: a, reason: collision with root package name */
    private static HostnameVerifier f2070a = new as();

    public static String a(String str, Map map, byte[] bArr) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry entry : map.entrySet()) {
            if (entry.getValue() != null) {
                try {
                    sb.append((String) entry.getKey());
                    sb.append("=");
                    sb.append(URLEncoder.encode((String) entry.getValue(), "utf-8"));
                    sb.append("&");
                } catch (Throwable unused) {
                }
            }
        }
        if (!map.isEmpty()) {
            sb.deleteCharAt(sb.length() - 1);
        }
        URL url = null;
        try {
            url = new URL(str + "?" + sb.toString());
        } catch (Throwable unused2) {
        }
        return a(url, bArr, a.f.f5151a);
    }

    private static String a(URL url, byte[] bArr, String str) {
        try {
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            if ("https".equals(url.getProtocol().toLowerCase())) {
                ((HttpsURLConnection) httpURLConnection).setHostnameVerifier(f2070a);
            }
            httpURLConnection.setRequestProperty("Content-Type", str);
            httpURLConnection.setConnectTimeout(NetCallback.ERROR_CODE_REQUEST_SUCCESS);
            httpURLConnection.setReadTimeout(5000);
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setRequestMethod("POST");
            OutputStream outputStream = httpURLConnection.getOutputStream();
            outputStream.write(bArr);
            outputStream.flush();
            int responseCode = httpURLConnection.getResponseCode();
            if (responseCode != 200) {
                return String.format("{\"code\":%d}", Integer.valueOf(responseCode));
            }
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "utf-8"));
            StringBuilder sb = new StringBuilder();
            while (true) {
                String line = bufferedReader.readLine();
                if (line == null) {
                    inputStream.close();
                    outputStream.close();
                    return sb.toString();
                }
                sb.append(line);
            }
        } catch (Throwable unused) {
            return "";
        }
    }
}
