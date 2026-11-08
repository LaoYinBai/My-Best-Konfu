package com.umeng.commonsdk.statistics.internal;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.TextUtils;
import com.umeng.commonsdk.debug.UMLog;
import com.umeng.commonsdk.debug.UMRTLog;
import com.umeng.commonsdk.framework.UMEnvelopeBuild;
import com.umeng.commonsdk.internal.crash.UMCrashManager;
import com.umeng.commonsdk.statistics.AnalyticsConstants;
import com.umeng.commonsdk.statistics.SdkVersion;
import com.umeng.commonsdk.statistics.UMServerURL;
import com.umeng.commonsdk.statistics.common.DataHelper;
import com.umeng.commonsdk.statistics.common.DeviceConfig;
import com.umeng.commonsdk.statistics.common.HelperUtils;
import com.umeng.commonsdk.statistics.common.MLog;
import com.umeng.message.MsgConstant;
import com.umeng.message.util.HttpRequest;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.net.UnknownHostException;
import java.security.SecureRandom;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLHandshakeException;
import org.apache.http.conn.ssl.SSLSocketFactory;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class c {
    private static boolean e;
    private String a = "10.0.0.172";
    private int b = 80;
    private Context c;
    private b d;

    public c(Context context) {
        this.c = context;
    }

    public void a(b bVar) {
        this.d = bVar;
    }

    private void a() {
        String strImprintProperty = UMEnvelopeBuild.imprintProperty(this.c, "domain_p", "");
        String strImprintProperty2 = UMEnvelopeBuild.imprintProperty(this.c, "domain_s", "");
        if (!TextUtils.isEmpty(strImprintProperty)) {
            UMServerURL.DEFAULT_URL = DataHelper.assembleURL(strImprintProperty);
        }
        if (!TextUtils.isEmpty(strImprintProperty2)) {
            UMServerURL.SECONDARY_URL = DataHelper.assembleURL(strImprintProperty2);
        }
        AnalyticsConstants.APPLOG_URL_LIST = new String[]{UMServerURL.DEFAULT_URL, UMServerURL.SECONDARY_URL};
    }

    private void b() {
        String strImprintProperty = UMEnvelopeBuild.imprintProperty(this.c, "domain_p", "");
        String strImprintProperty2 = UMEnvelopeBuild.imprintProperty(this.c, "domain_s", "");
        if (!TextUtils.isEmpty(strImprintProperty)) {
            UMServerURL.DEFAULT_URL = DataHelper.assembleURL(strImprintProperty);
        }
        if (!TextUtils.isEmpty(strImprintProperty2)) {
            UMServerURL.SECONDARY_URL = DataHelper.assembleURL(strImprintProperty2);
        }
        String strImprintProperty3 = UMEnvelopeBuild.imprintProperty(this.c, "oversea_domain_p", "");
        String strImprintProperty4 = UMEnvelopeBuild.imprintProperty(this.c, "oversea_domain_s", "");
        if (!TextUtils.isEmpty(strImprintProperty3)) {
            UMServerURL.OVERSEA_DEFAULT_URL = DataHelper.assembleURL(strImprintProperty3);
        }
        if (!TextUtils.isEmpty(strImprintProperty4)) {
            UMServerURL.OVERSEA_SECONDARY_URL = DataHelper.assembleURL(strImprintProperty4);
        }
        AnalyticsConstants.APPLOG_URL_LIST = new String[]{UMServerURL.OVERSEA_DEFAULT_URL, UMServerURL.OVERSEA_SECONDARY_URL};
        if (TextUtils.isEmpty(com.umeng.commonsdk.statistics.b.b)) {
            return;
        }
        if (com.umeng.commonsdk.statistics.b.b.startsWith("460") || com.umeng.commonsdk.statistics.b.b.startsWith("461")) {
            AnalyticsConstants.APPLOG_URL_LIST = new String[]{UMServerURL.DEFAULT_URL, UMServerURL.SECONDARY_URL};
        }
    }

    public byte[] a(byte[] bArr, boolean z) throws Throwable {
        if (SdkVersion.SDK_TYPE == 0) {
            a();
        } else {
            UMServerURL.DEFAULT_URL = UMServerURL.OVERSEA_DEFAULT_URL;
            UMServerURL.SECONDARY_URL = UMServerURL.OVERSEA_SECONDARY_URL;
            b();
        }
        int i = 0;
        byte[] bArrB = null;
        while (true) {
            if (i >= AnalyticsConstants.APPLOG_URL_LIST.length) {
                break;
            }
            bArrB = b(bArr, AnalyticsConstants.APPLOG_URL_LIST[i]);
            if (bArrB != null) {
                if (this.d != null) {
                    this.d.onRequestSucceed(z);
                }
            } else {
                if (this.d != null) {
                    this.d.onRequestFailed();
                }
                i++;
            }
        }
        return bArrB;
    }

    private boolean c() {
        NetworkInfo activeNetworkInfo;
        String extraInfo;
        if (this.c.getPackageManager().checkPermission(MsgConstant.PERMISSION_ACCESS_NETWORK_STATE, this.c.getPackageName()) != 0) {
            return false;
        }
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) this.c.getSystemService("connectivity");
            if (DeviceConfig.checkPermission(this.c, MsgConstant.PERMISSION_ACCESS_NETWORK_STATE) && connectivityManager != null && (activeNetworkInfo = connectivityManager.getActiveNetworkInfo()) != null && activeNetworkInfo.getType() != 1 && (extraInfo = activeNetworkInfo.getExtraInfo()) != null) {
                if (!extraInfo.equals("cmwap") && !extraInfo.equals("3gwap")) {
                    if (extraInfo.equals("uniwap")) {
                    }
                }
                return true;
            }
        } catch (Throwable th) {
            UMCrashManager.reportCrash(this.c, th);
        }
        return false;
    }

    /* JADX WARN: Removed duplicated region for block: B:52:0x0132  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public byte[] a(byte[] r7, java.lang.String r8) throws java.lang.Throwable {
        /*
            Method dump skipped, instructions count: 310
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.commonsdk.statistics.internal.c.a(byte[], java.lang.String):byte[]");
    }

    /* JADX WARN: Not initialized variable reg: 5, insn: 0x01de: MOVE (r1 I:??[OBJECT, ARRAY]) = (r5 I:??[OBJECT, ARRAY]), block:B:92:0x01de */
    public byte[] b(byte[] bArr, String str) throws Throwable {
        HttpsURLConnection httpsURLConnection;
        OutputStream outputStream;
        OutputStream outputStream2;
        OutputStream outputStream3 = null;
        try {
            try {
                if (this.d != null) {
                    this.d.onRequestStart();
                }
                if (c()) {
                    httpsURLConnection = (HttpsURLConnection) new URL(str).openConnection(new Proxy(Proxy.Type.HTTP, new InetSocketAddress(this.a, this.b)));
                } else {
                    httpsURLConnection = (HttpsURLConnection) new URL(str).openConnection();
                }
            } catch (Throwable th) {
                th = th;
                outputStream3 = outputStream2;
            }
            try {
                if (!e) {
                    httpsURLConnection.setHostnameVerifier(SSLSocketFactory.STRICT_HOSTNAME_VERIFIER);
                    SSLContext sSLContext = SSLContext.getInstance("TLS");
                    sSLContext.init(null, null, new SecureRandom());
                    httpsURLConnection.setSSLSocketFactory(sSLContext.getSocketFactory());
                    e = true;
                }
                httpsURLConnection.setRequestProperty("X-Umeng-UTC", String.valueOf(System.currentTimeMillis()));
                httpsURLConnection.setRequestProperty("X-Umeng-Sdk", a.a(this.c).b());
                httpsURLConnection.setRequestProperty("Content-Type", a.a(this.c).a());
                httpsURLConnection.setRequestProperty("Msg-Type", "envelope/json");
                httpsURLConnection.setRequestProperty("X-Umeng-Pro-Ver", "1.0.0");
                httpsURLConnection.setConnectTimeout(30000);
                httpsURLConnection.setReadTimeout(30000);
                httpsURLConnection.setRequestMethod("POST");
                httpsURLConnection.setDoOutput(true);
                httpsURLConnection.setDoInput(true);
                boolean z = false;
                httpsURLConnection.setUseCaches(false);
                outputStream = httpsURLConnection.getOutputStream();
                try {
                    outputStream.write(bArr);
                    outputStream.flush();
                    httpsURLConnection.connect();
                    if (this.d != null) {
                        this.d.onRequestEnd();
                    }
                    int responseCode = httpsURLConnection.getResponseCode();
                    String headerField = httpsURLConnection.getHeaderField("Content-Type");
                    if (!TextUtils.isEmpty(headerField) && headerField.equalsIgnoreCase(HttpRequest.CONTENT_TYPE_THRIFT)) {
                        z = true;
                    }
                    if (AnalyticsConstants.UM_DEBUG) {
                        MLog.i("status code : " + responseCode + "; isThrifit:" + z);
                    }
                    if (responseCode != 200 || !z) {
                        if (outputStream != null) {
                            try {
                                outputStream.close();
                            } catch (Exception e2) {
                                UMCrashManager.reportCrash(this.c, e2);
                            }
                        }
                        if (httpsURLConnection != null) {
                            try {
                                httpsURLConnection.getInputStream().close();
                            } catch (IOException unused) {
                            }
                            httpsURLConnection.disconnect();
                        }
                        return null;
                    }
                    MLog.i("Send message to server. status code is: " + responseCode);
                    UMRTLog.i(UMRTLog.RTLOG_TAG, "Send message to server. status code is: " + responseCode + "; url = " + str);
                    InputStream inputStream = httpsURLConnection.getInputStream();
                    try {
                        byte[] streamToByteArray = HelperUtils.readStreamToByteArray(inputStream);
                        if (outputStream != null) {
                            try {
                                outputStream.close();
                            } catch (Exception e3) {
                                UMCrashManager.reportCrash(this.c, e3);
                            }
                        }
                        if (httpsURLConnection != null) {
                            try {
                                httpsURLConnection.getInputStream().close();
                            } catch (IOException unused2) {
                            }
                            httpsURLConnection.disconnect();
                        }
                        return streamToByteArray;
                    } finally {
                        HelperUtils.safeClose(inputStream);
                    }
                } catch (UnknownHostException unused3) {
                    UMLog.aq("A_10200", 2, "\\|");
                    if (outputStream != null) {
                        try {
                            outputStream.close();
                        } catch (Exception e4) {
                            UMCrashManager.reportCrash(this.c, e4);
                        }
                    }
                    if (httpsURLConnection != null) {
                        try {
                            httpsURLConnection.getInputStream().close();
                        } catch (IOException unused4) {
                        }
                        httpsURLConnection.disconnect();
                    }
                    return null;
                } catch (SSLHandshakeException unused5) {
                    UMLog.aq("A_10201", 2, "\\|");
                    if (outputStream != null) {
                        try {
                            outputStream.close();
                        } catch (Exception e5) {
                            UMCrashManager.reportCrash(this.c, e5);
                        }
                    }
                    if (httpsURLConnection != null) {
                        try {
                            httpsURLConnection.getInputStream().close();
                        } catch (IOException unused6) {
                        }
                        httpsURLConnection.disconnect();
                    }
                    return null;
                } catch (Throwable unused7) {
                    if (outputStream != null) {
                        try {
                            outputStream.close();
                        } catch (Exception e6) {
                            UMCrashManager.reportCrash(this.c, e6);
                        }
                    }
                    if (httpsURLConnection != null) {
                        try {
                            httpsURLConnection.getInputStream().close();
                        } catch (IOException unused8) {
                        }
                        httpsURLConnection.disconnect();
                    }
                    return null;
                }
            } catch (UnknownHostException unused9) {
                outputStream = null;
            } catch (SSLHandshakeException unused10) {
                outputStream = null;
            } catch (Throwable unused11) {
                outputStream = null;
            }
        } catch (UnknownHostException unused12) {
            httpsURLConnection = null;
            outputStream = null;
        } catch (SSLHandshakeException unused13) {
            httpsURLConnection = null;
            outputStream = null;
        } catch (Throwable unused14) {
            httpsURLConnection = null;
            outputStream = null;
        }
    }
}
