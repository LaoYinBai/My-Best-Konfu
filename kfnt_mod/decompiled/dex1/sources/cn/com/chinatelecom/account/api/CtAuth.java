package cn.com.chinatelecom.account.api;

import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import cn.com.chinatelecom.account.api.a.d;
import cn.com.chinatelecom.account.api.b.b;
import cn.com.chinatelecom.account.api.d.c;
import cn.com.chinatelecom.account.api.e.f;
import cn.com.chinatelecom.account.api.e.g;
import cn.com.chinatelecom.account.api.e.j;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class CtAuth {
    private static final String TAG = "CtAuth";
    private static volatile CtAuth instance = null;
    public static boolean isInit = false;
    public static String mAppId = "";
    public static String mAppSecret = "";
    public static Context mContext;
    public static Handler mHandler = new Handler(Looper.getMainLooper());
    public static TraceLogger mTraceLogger;

    public static CtAuth getInstance() {
        if (instance == null) {
            synchronized (CtAuth.class) {
                if (instance == null) {
                    instance = new CtAuth();
                }
            }
        }
        return instance;
    }

    public static void info(String str, String str2) {
        if (mTraceLogger != null) {
            mTraceLogger.info("CT_" + str, str2);
        }
    }

    public static void postResultOnMainThread(final String str, final JSONObject jSONObject, final ResultListener resultListener) {
        mHandler.post(new Runnable() { // from class: cn.com.chinatelecom.account.api.CtAuth.1
            @Override // java.lang.Runnable
            public void run() {
                if (resultListener != null) {
                    try {
                        if (str != null) {
                            jSONObject.put("reqId", str);
                        }
                        resultListener.onResult(jSONObject.toString());
                    } catch (Throwable th) {
                        th.printStackTrace();
                    }
                    f.c(str);
                }
            }
        });
    }

    public static void warn(String str, String str2, Throwable th) {
        if (mTraceLogger != null) {
            mTraceLogger.warn("CT_" + str, str2, th);
        }
    }

    public Context getContext() {
        return mContext;
    }

    public String getOperatorType() {
        if (mContext != null) {
            return g.a(mContext, false);
        }
        throw new IllegalArgumentException("Please call the init method");
    }

    public void getPreCodeParamsByJs(String str, cn.com.chinatelecom.account.api.b.a aVar) {
        info(TAG, "called getPreCodeParamsByJs()");
        if (aVar == null) {
            return;
        }
        if (mContext == null || TextUtils.isEmpty(mAppId) || TextUtils.isEmpty(mAppSecret)) {
            aVar.callbackPreCodeParams(j.e().toString());
        } else {
            new b().a(str, aVar);
        }
    }

    public void init(Context context, String str, String str2, TraceLogger traceLogger) {
        if (context == null) {
            throw new IllegalArgumentException("context must not be null!");
        }
        if (str == null) {
            throw new IllegalArgumentException("appId must not be null!");
        }
        if (str2 == null) {
            throw new IllegalArgumentException("appSecret must not be null!");
        }
        if (!(context instanceof Application)) {
            context = context.getApplicationContext();
        }
        mContext = context;
        c.a(mContext);
        mAppId = str;
        mAppSecret = str2;
        mTraceLogger = traceLogger;
    }

    public boolean isMobileDataEnabled() {
        if (mContext != null) {
            return g.d(mContext);
        }
        throw new IllegalArgumentException("Please call the init method");
    }

    @Deprecated
    public void requestPreCode(CtSetting ctSetting, ResultListener resultListener) {
        requestPreLogin(ctSetting, resultListener);
    }

    /* JADX WARN: Removed duplicated region for block: B:25:0x0048  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0054  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void requestPreCodeByJs(java.lang.String r4, cn.com.chinatelecom.account.api.b.a r5) {
        /*
            r3 = this;
            java.lang.String r0 = cn.com.chinatelecom.account.api.CtAuth.TAG
            java.lang.String r1 = "called requestPreCodeByJs()"
            info(r0, r1)
            if (r5 != 0) goto La
            return
        La:
            android.content.Context r0 = cn.com.chinatelecom.account.api.CtAuth.mContext
            if (r0 == 0) goto L82
            java.lang.String r0 = cn.com.chinatelecom.account.api.CtAuth.mAppId
            boolean r0 = android.text.TextUtils.isEmpty(r0)
            if (r0 != 0) goto L82
            java.lang.String r0 = cn.com.chinatelecom.account.api.CtAuth.mAppSecret
            boolean r0 = android.text.TextUtils.isEmpty(r0)
            if (r0 == 0) goto L1f
            goto L82
        L1f:
            boolean r0 = android.text.TextUtils.isEmpty(r4)
            r1 = 0
            if (r0 != 0) goto L41
            org.json.JSONObject r0 = new org.json.JSONObject     // Catch: java.lang.Exception -> L3b
            r0.<init>(r4)     // Catch: java.lang.Exception -> L3b
            java.lang.String r4 = "url"
            java.lang.String r4 = r0.optString(r4)     // Catch: java.lang.Exception -> L3b
            java.lang.String r2 = "taskId"
            java.lang.String r0 = r0.optString(r2)     // Catch: java.lang.Exception -> L39
            r1 = r0
            goto L42
        L39:
            r0 = move-exception
            goto L3d
        L3b:
            r0 = move-exception
            r4 = r1
        L3d:
            r0.printStackTrace()
            goto L42
        L41:
            r4 = r1
        L42:
            boolean r0 = android.text.TextUtils.isEmpty(r4)
            if (r0 == 0) goto L54
            org.json.JSONObject r4 = cn.com.chinatelecom.account.api.e.j.f()
        L4c:
            java.lang.String r4 = r4.toString()
            r5.callbackPreCode(r4)
            return
        L54:
            android.content.Context r0 = cn.com.chinatelecom.account.api.CtAuth.mContext
            boolean r0 = cn.com.chinatelecom.account.api.e.g.c(r0)
            if (r0 == 0) goto L65
            cn.com.chinatelecom.account.api.b.b r0 = new cn.com.chinatelecom.account.api.b.b
            r0.<init>()
            r0.a(r1, r4, r5)
            goto L81
        L65:
            android.content.Context r0 = cn.com.chinatelecom.account.api.CtAuth.mContext
            boolean r0 = cn.com.chinatelecom.account.api.e.g.d(r0)
            if (r0 == 0) goto L76
            cn.com.chinatelecom.account.api.b.b r0 = new cn.com.chinatelecom.account.api.b.b
            r0.<init>()
            r0.b(r1, r4, r5)
            goto L81
        L76:
            org.json.JSONObject r4 = cn.com.chinatelecom.account.api.e.j.d()
            java.lang.String r4 = r4.toString()
            r5.callbackPreCode(r4)
        L81:
            return
        L82:
            org.json.JSONObject r4 = cn.com.chinatelecom.account.api.e.j.e()
            goto L4c
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.com.chinatelecom.account.api.CtAuth.requestPreCodeByJs(java.lang.String, cn.com.chinatelecom.account.api.b.a):void");
    }

    public void requestPreLogin(CtSetting ctSetting, int i, ResultListener resultListener) {
        JSONObject jSONObjectE;
        info(TAG, "called requestPreLogin()");
        if (resultListener == null) {
            return;
        }
        if (mContext == null || TextUtils.isEmpty(mAppId) || TextUtils.isEmpty(mAppSecret)) {
            jSONObjectE = j.e();
        } else {
            if (g.b(mContext)) {
                if (g.c(mContext)) {
                    new cn.com.chinatelecom.account.api.c.a(mContext, mAppId, mAppSecret).a(d.a(cn.com.chinatelecom.account.api.e.b.e), ctSetting, i, resultListener);
                    return;
                } else if (g.d(mContext)) {
                    new cn.com.chinatelecom.account.api.c.a(mContext, mAppId, mAppSecret).b(d.a(cn.com.chinatelecom.account.api.e.b.e), ctSetting, i, resultListener);
                    return;
                } else {
                    postResultOnMainThread(null, j.d(), resultListener);
                    return;
                }
            }
            jSONObjectE = j.a();
        }
        postResultOnMainThread(null, jSONObjectE, resultListener);
    }

    public void requestPreLogin(CtSetting ctSetting, ResultListener resultListener) {
        requestPreLogin(ctSetting, a.d, resultListener);
    }

    public void setDomainName(String str, String str2, String str3) {
        g.f2297a = str;
        g.f2298b = str2;
        g.f2299c = str3;
    }
}
