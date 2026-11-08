package anet.channel.session;

import android.os.Build;
import android.util.Pair;
import anet.channel.RequestCb;
import anet.channel.appmonitor.AppMonitor;
import anet.channel.bytes.ByteArray;
import anet.channel.bytes.a;
import anet.channel.request.Request;
import anet.channel.statist.ExceptionStatistic;
import anet.channel.status.NetworkStatusHelper;
import anet.channel.util.ALog;
import anet.channel.util.ErrorConstant;
import anet.channel.util.HttpConstant;
import anet.channel.util.StringUtils;
import com.bytedance.sdk.account.monitor.AccountMonitorConstants;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CancellationException;
import java.util.zip.GZIPInputStream;
import javax.net.ssl.HttpsURLConnection;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class b {

    public static class a {

        /* renamed from: a, reason: collision with root package name */
        public int f494a;

        /* renamed from: b, reason: collision with root package name */
        public byte[] f495b;

        /* renamed from: c, reason: collision with root package name */
        public Map<String, List<String>> f496c;
        public int d;
        public boolean e;
    }

    private b() {
    }

    /* JADX WARN: Removed duplicated region for block: B:105:0x0294 A[Catch: all -> 0x0268, TryCatch #5 {all -> 0x0268, blocks: (B:13:0x002c, B:15:0x0032, B:16:0x007f, B:18:0x00f9, B:20:0x0103, B:22:0x0109, B:23:0x0147, B:103:0x028e, B:105:0x0294, B:107:0x029b, B:109:0x02a3, B:111:0x02b7, B:110:0x02b2, B:119:0x02d1, B:127:0x02fd, B:137:0x0325, B:145:0x0355, B:153:0x0387, B:161:0x03a7, B:169:0x03c6, B:177:0x03e5, B:48:0x0182, B:49:0x0195, B:51:0x01b9, B:53:0x01bf, B:55:0x01c5, B:57:0x01cb, B:60:0x01d2, B:62:0x01f1, B:64:0x0201, B:65:0x0208, B:69:0x0225, B:71:0x023e, B:73:0x0258, B:67:0x0216, B:68:0x021d), top: B:190:0x002c }] */
    /* JADX WARN: Removed duplicated region for block: B:106:0x0299  */
    /* JADX WARN: Removed duplicated region for block: B:109:0x02a3 A[Catch: all -> 0x0268, TryCatch #5 {all -> 0x0268, blocks: (B:13:0x002c, B:15:0x0032, B:16:0x007f, B:18:0x00f9, B:20:0x0103, B:22:0x0109, B:23:0x0147, B:103:0x028e, B:105:0x0294, B:107:0x029b, B:109:0x02a3, B:111:0x02b7, B:110:0x02b2, B:119:0x02d1, B:127:0x02fd, B:137:0x0325, B:145:0x0355, B:153:0x0387, B:161:0x03a7, B:169:0x03c6, B:177:0x03e5, B:48:0x0182, B:49:0x0195, B:51:0x01b9, B:53:0x01bf, B:55:0x01c5, B:57:0x01cb, B:60:0x01d2, B:62:0x01f1, B:64:0x0201, B:65:0x0208, B:69:0x0225, B:71:0x023e, B:73:0x0258, B:67:0x0216, B:68:0x021d), top: B:190:0x002c }] */
    /* JADX WARN: Removed duplicated region for block: B:110:0x02b2 A[Catch: all -> 0x0268, TryCatch #5 {all -> 0x0268, blocks: (B:13:0x002c, B:15:0x0032, B:16:0x007f, B:18:0x00f9, B:20:0x0103, B:22:0x0109, B:23:0x0147, B:103:0x028e, B:105:0x0294, B:107:0x029b, B:109:0x02a3, B:111:0x02b7, B:110:0x02b2, B:119:0x02d1, B:127:0x02fd, B:137:0x0325, B:145:0x0355, B:153:0x0387, B:161:0x03a7, B:169:0x03c6, B:177:0x03e5, B:48:0x0182, B:49:0x0195, B:51:0x01b9, B:53:0x01bf, B:55:0x01c5, B:57:0x01cb, B:60:0x01d2, B:62:0x01f1, B:64:0x0201, B:65:0x0208, B:69:0x0225, B:71:0x023e, B:73:0x0258, B:67:0x0216, B:68:0x021d), top: B:190:0x002c }] */
    /* JADX WARN: Removed duplicated region for block: B:113:0x02c6 A[Catch: Exception -> 0x0314, TRY_ENTER, TRY_LEAVE, TryCatch #23 {Exception -> 0x0314, blocks: (B:113:0x02c6, B:121:0x02f2, B:129:0x030f, B:139:0x034c, B:147:0x037c, B:155:0x039c, B:163:0x03bc, B:171:0x03db, B:179:0x0406, B:75:0x0263), top: B:196:0x0028 }] */
    /* JADX WARN: Removed duplicated region for block: B:121:0x02f2 A[Catch: Exception -> 0x0314, TRY_ENTER, TRY_LEAVE, TryCatch #23 {Exception -> 0x0314, blocks: (B:113:0x02c6, B:121:0x02f2, B:129:0x030f, B:139:0x034c, B:147:0x037c, B:155:0x039c, B:163:0x03bc, B:171:0x03db, B:179:0x0406, B:75:0x0263), top: B:196:0x0028 }] */
    /* JADX WARN: Removed duplicated region for block: B:129:0x030f A[Catch: Exception -> 0x0314, TRY_ENTER, TRY_LEAVE, TryCatch #23 {Exception -> 0x0314, blocks: (B:113:0x02c6, B:121:0x02f2, B:129:0x030f, B:139:0x034c, B:147:0x037c, B:155:0x039c, B:163:0x03bc, B:171:0x03db, B:179:0x0406, B:75:0x0263), top: B:196:0x0028 }] */
    /* JADX WARN: Removed duplicated region for block: B:139:0x034c A[Catch: Exception -> 0x0314, TRY_ENTER, TRY_LEAVE, TryCatch #23 {Exception -> 0x0314, blocks: (B:113:0x02c6, B:121:0x02f2, B:129:0x030f, B:139:0x034c, B:147:0x037c, B:155:0x039c, B:163:0x03bc, B:171:0x03db, B:179:0x0406, B:75:0x0263), top: B:196:0x0028 }] */
    /* JADX WARN: Removed duplicated region for block: B:147:0x037c A[Catch: Exception -> 0x0314, TRY_ENTER, TRY_LEAVE, TryCatch #23 {Exception -> 0x0314, blocks: (B:113:0x02c6, B:121:0x02f2, B:129:0x030f, B:139:0x034c, B:147:0x037c, B:155:0x039c, B:163:0x03bc, B:171:0x03db, B:179:0x0406, B:75:0x0263), top: B:196:0x0028 }] */
    /* JADX WARN: Removed duplicated region for block: B:155:0x039c A[Catch: Exception -> 0x0314, TRY_ENTER, TRY_LEAVE, TryCatch #23 {Exception -> 0x0314, blocks: (B:113:0x02c6, B:121:0x02f2, B:129:0x030f, B:139:0x034c, B:147:0x037c, B:155:0x039c, B:163:0x03bc, B:171:0x03db, B:179:0x0406, B:75:0x0263), top: B:196:0x0028 }] */
    /* JADX WARN: Removed duplicated region for block: B:163:0x03bc A[Catch: Exception -> 0x0314, TRY_ENTER, TRY_LEAVE, TryCatch #23 {Exception -> 0x0314, blocks: (B:113:0x02c6, B:121:0x02f2, B:129:0x030f, B:139:0x034c, B:147:0x037c, B:155:0x039c, B:163:0x03bc, B:171:0x03db, B:179:0x0406, B:75:0x0263), top: B:196:0x0028 }] */
    /* JADX WARN: Removed duplicated region for block: B:171:0x03db A[Catch: Exception -> 0x0314, TRY_ENTER, TRY_LEAVE, TryCatch #23 {Exception -> 0x0314, blocks: (B:113:0x02c6, B:121:0x02f2, B:129:0x030f, B:139:0x034c, B:147:0x037c, B:155:0x039c, B:163:0x03bc, B:171:0x03db, B:179:0x0406, B:75:0x0263), top: B:196:0x0028 }] */
    /* JADX WARN: Removed duplicated region for block: B:179:0x0406 A[Catch: Exception -> 0x0314, TRY_ENTER, TRY_LEAVE, TryCatch #23 {Exception -> 0x0314, blocks: (B:113:0x02c6, B:121:0x02f2, B:129:0x030f, B:139:0x034c, B:147:0x037c, B:155:0x039c, B:163:0x03bc, B:171:0x03db, B:179:0x0406, B:75:0x0263), top: B:196:0x0028 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static anet.channel.session.b.a a(anet.channel.request.Request r17, anet.channel.RequestCb r18) throws java.lang.Throwable {
        /*
            Method dump skipped, instructions count: 1069
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: anet.channel.session.b.a(anet.channel.request.Request, anet.channel.RequestCb):anet.channel.session.b$a");
    }

    private static void a(Request request, a aVar, RequestCb requestCb, int i, Throwable th) {
        String errMsg = ErrorConstant.getErrMsg(i);
        ALog.e("awcn.HttpConnector", "onException", request.getSeq(), AccountMonitorConstants.CommonParameter.ERROR, Integer.valueOf(i), "errMsg", errMsg, "url", request.getUrlString(), com.alipay.sdk.cons.c.f, request.getHost());
        if (aVar != null) {
            aVar.f494a = i;
        }
        if (!request.f475a.isDone.get()) {
            request.f475a.statusCode = i;
            request.f475a.msg = errMsg;
            request.f475a.rspEnd = System.currentTimeMillis();
            if (i != -204) {
                AppMonitor.getInstance().commitStat(new ExceptionStatistic(i, errMsg, request.f475a, th));
            }
        }
        if (requestCb != null) {
            requestCb.onFinish(i, errMsg, request.f475a);
        }
    }

    private static HttpURLConnection a(Request request) throws IOException {
        HttpURLConnection httpURLConnection;
        Pair<String, Integer> wifiProxy = NetworkStatusHelper.getWifiProxy();
        Proxy proxy = wifiProxy != null ? new Proxy(Proxy.Type.HTTP, new InetSocketAddress((String) wifiProxy.first, ((Integer) wifiProxy.second).intValue())) : null;
        anet.channel.util.g gVarA = anet.channel.util.g.a();
        if (NetworkStatusHelper.getStatus().isMobile() && gVarA != null) {
            proxy = gVarA.b();
        }
        URL url = request.getUrl();
        if (proxy != null) {
            httpURLConnection = (HttpURLConnection) url.openConnection(proxy);
        } else {
            httpURLConnection = (HttpURLConnection) url.openConnection();
        }
        httpURLConnection.setConnectTimeout(request.getConnectTimeout());
        httpURLConnection.setReadTimeout(request.getReadTimeout());
        httpURLConnection.setRequestMethod(request.getMethod());
        if (request.containsBody()) {
            httpURLConnection.setDoOutput(true);
        }
        Map<String, String> headers = request.getHeaders();
        for (Map.Entry<String, String> entry : headers.entrySet()) {
            httpURLConnection.addRequestProperty(entry.getKey(), entry.getValue());
        }
        String host = headers.get(HttpConstant.HOST);
        if (host == null) {
            host = request.getHost();
        }
        String strConcatString = request.getHttpUrl().containsNonDefaultPort() ? StringUtils.concatString(host, ":", String.valueOf(request.getHttpUrl().getPort())) : host;
        httpURLConnection.setRequestProperty(HttpConstant.HOST, strConcatString);
        if (NetworkStatusHelper.getApn().equals("cmwap")) {
            httpURLConnection.setRequestProperty(HttpConstant.X_ONLINE_HOST, strConcatString);
        }
        if (!headers.containsKey(HttpConstant.ACCEPT_ENCODING)) {
            httpURLConnection.setRequestProperty(HttpConstant.ACCEPT_ENCODING, HttpConstant.GZIP);
        }
        if (gVarA != null) {
            httpURLConnection.setRequestProperty(HttpConstant.AUTHORIZATION, gVarA.c());
        }
        if (url.getProtocol().equalsIgnoreCase("https")) {
            a(httpURLConnection, request, host);
        }
        httpURLConnection.setInstanceFollowRedirects(false);
        return httpURLConnection;
    }

    private static void a(HttpURLConnection httpURLConnection, Request request, String str) {
        if (Integer.parseInt(Build.VERSION.SDK) < 8) {
            ALog.e("awcn.HttpConnector", "supportHttps", "[supportHttps]Froyo 以下版本不支持https", new Object[0]);
            return;
        }
        HttpsURLConnection httpsURLConnection = (HttpsURLConnection) httpURLConnection;
        if (request.getSslSocketFactory() != null) {
            httpsURLConnection.setSSLSocketFactory(request.getSslSocketFactory());
        } else if (anet.channel.util.b.a() != null) {
            httpsURLConnection.setSSLSocketFactory(anet.channel.util.b.a());
        }
        if (request.getHostnameVerifier() != null) {
            httpsURLConnection.setHostnameVerifier(request.getHostnameVerifier());
        } else if (anet.channel.util.b.b() != null) {
            httpsURLConnection.setHostnameVerifier(anet.channel.util.b.b());
        } else {
            httpsURLConnection.setHostnameVerifier(new c(str));
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v12, types: [int] */
    /* JADX WARN: Type inference failed for: r0v6, types: [java.lang.String] */
    private static int a(HttpURLConnection httpURLConnection, Request request) throws Throwable {
        OutputStream outputStream;
        int i = 0;
        if (request.containsBody()) {
            OutputStream outputStream2 = null;
            OutputStream outputStream3 = null;
            try {
                try {
                    outputStream = httpURLConnection.getOutputStream();
                } catch (Throwable th) {
                    th = th;
                }
            } catch (Exception e) {
                e = e;
            }
            try {
                ?? PostBody = request.postBody(outputStream);
                if (outputStream != null) {
                    try {
                        outputStream.flush();
                        outputStream.close();
                    } catch (IOException e2) {
                        ALog.e("awcn.HttpConnector", "postData", request.getSeq(), e2, new Object[0]);
                    }
                }
                i = PostBody;
                outputStream2 = PostBody;
            } catch (Exception e3) {
                outputStream3 = outputStream;
                e = e3;
                ALog.e("awcn.HttpConnector", "postData error", request.getSeq(), e, new Object[0]);
                outputStream2 = outputStream3;
                if (outputStream3 != null) {
                    try {
                        outputStream3.flush();
                        outputStream3.close();
                        outputStream2 = outputStream3;
                    } catch (IOException e4) {
                        ALog.e("awcn.HttpConnector", "postData", request.getSeq(), e4, new Object[0]);
                        outputStream2 = "awcn.HttpConnector";
                    }
                }
                long j = i;
                request.f475a.reqBodyInflateSize = j;
                request.f475a.reqBodyDeflateSize = j;
                request.f475a.sendDataSize = j;
                return i;
            } catch (Throwable th2) {
                outputStream2 = outputStream;
                th = th2;
                if (outputStream2 != null) {
                    try {
                        outputStream2.flush();
                        outputStream2.close();
                    } catch (IOException e5) {
                        ALog.e("awcn.HttpConnector", "postData", request.getSeq(), e5, new Object[i]);
                    }
                }
                throw th;
            }
            long j2 = i;
            request.f475a.reqBodyInflateSize = j2;
            request.f475a.reqBodyDeflateSize = j2;
            request.f475a.sendDataSize = j2;
        }
        return i;
    }

    private static void a(HttpURLConnection httpURLConnection, Request request, a aVar, RequestCb requestCb) throws Throwable {
        InputStream errorStream;
        ByteArrayOutputStream byteArrayOutputStream;
        anet.channel.util.a aVar2;
        httpURLConnection.getURL().toString();
        try {
            errorStream = httpURLConnection.getInputStream();
        } catch (IOException e) {
            if (e instanceof FileNotFoundException) {
                ALog.w("awcn.HttpConnector", "File not found", request.getSeq(), "url", request.getUrlString());
            }
            try {
                errorStream = httpURLConnection.getErrorStream();
            } catch (Exception e2) {
                ALog.e("awcn.HttpConnector", "get error stream failed.", request.getSeq(), e2, new Object[0]);
                errorStream = null;
            }
        }
        if (errorStream == null) {
            a(request, aVar, requestCb, ErrorConstant.ERROR_IO_EXCEPTION, null);
            return;
        }
        if (requestCb == null) {
            byteArrayOutputStream = new ByteArrayOutputStream(aVar.d <= 0 ? 1024 : aVar.e ? aVar.d * 2 : aVar.d);
        } else {
            byteArrayOutputStream = null;
        }
        try {
            aVar2 = new anet.channel.util.a(errorStream);
        } catch (Throwable th) {
            th = th;
            aVar2 = null;
        }
        try {
            InputStream gZIPInputStream = aVar.e ? new GZIPInputStream(aVar2) : aVar2;
            ByteArray byteArrayA = null;
            while (!Thread.currentThread().isInterrupted()) {
                if (byteArrayA == null) {
                    byteArrayA = a.C0007a.f408a.a(2048);
                }
                int from = byteArrayA.readFrom(gZIPInputStream);
                if (from != -1) {
                    if (byteArrayOutputStream != null) {
                        byteArrayA.writeTo(byteArrayOutputStream);
                    } else {
                        requestCb.onDataReceive(byteArrayA, false);
                        byteArrayA = null;
                    }
                    long j = from;
                    request.f475a.recDataSize += j;
                    request.f475a.rspBodyInflateSize += j;
                } else {
                    if (byteArrayOutputStream != null) {
                        byteArrayA.recycle();
                    } else {
                        requestCb.onDataReceive(byteArrayA, true);
                    }
                    if (byteArrayOutputStream != null) {
                        aVar.f495b = byteArrayOutputStream.toByteArray();
                    }
                    request.f475a.recDataTime = System.currentTimeMillis() - request.f475a.rspStart;
                    request.f475a.rspBodyDeflateSize = aVar2.a();
                    try {
                        gZIPInputStream.close();
                        return;
                    } catch (IOException unused) {
                        return;
                    }
                }
            }
            throw new CancellationException("task cancelled");
        } catch (Throwable th2) {
            th = th2;
            request.f475a.recDataTime = System.currentTimeMillis() - request.f475a.rspStart;
            request.f475a.rspBodyDeflateSize = aVar2.a();
            if (errorStream != null) {
                try {
                    errorStream.close();
                } catch (IOException unused2) {
                }
            }
            throw th;
        }
    }
}
