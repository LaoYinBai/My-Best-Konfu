package com.ss.union.game.sdk.account.f;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import com.bytedance.sdk.account.api.response.LoginByTicketResponse;
import com.bytedance.sdk.account.platform.OnekeyLoginAdapter;
import com.bytedance.sdk.account.platform.api.IOnekeyLoginService;
import com.bytedance.sdk.account.platform.base.AuthorizeCallback;
import com.bytedance.sdk.account.platform.base.AuthorizeErrorResponse;
import com.bytedance.sdk.account.platform.base.AuthorizeFramework;
import com.ss.union.game.sdk.account.b;
import com.ss.union.game.sdk.account.callback.IAuthorizeCallBack;
import com.ss.union.game.sdk.account.callback.IOneKeyLoginCallBack;
import com.ss.union.game.sdk.common.util.GlobalApplicationUtils;
import com.ss.union.game.sdk.common.util.ResourceUtils;
import com.ss.union.game.sdk.common.util.ToastUtils;
import com.umeng.message.proguard.l;
import java.lang.ref.WeakReference;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class b {
    private static volatile b a = null;
    private static final String d = "10002";
    private static final int e = 3000;
    private volatile boolean b = false;
    private IOnekeyLoginService c = AuthorizeFramework.getService(IOnekeyLoginService.class);
    private String f;
    private String g;

    private b() {
    }

    public static b a() {
        if (a == null) {
            synchronized (b.class) {
                if (a == null) {
                    a = new b();
                }
            }
        }
        return a;
    }

    public void a(final IAuthorizeCallBack iAuthorizeCallBack) {
        this.b = false;
        final Handler handler = new Handler(Looper.getMainLooper());
        if (this.c == null) {
            iAuthorizeCallBack.onFail(d, "一键登录service没有成功初始化");
            return;
        }
        handler.postDelayed(new Runnable() { // from class: com.ss.union.game.sdk.account.f.b.1
            @Override // java.lang.Runnable
            public void run() {
                b.this.b = true;
                iAuthorizeCallBack.onTimeout();
                try {
                    b.this.c.cancel();
                } catch (Throwable unused) {
                }
            }
        }, 3000L);
        this.c.getPhoneInfo(new AuthorizeCallback() { // from class: com.ss.union.game.sdk.account.f.b.2
            public void onSuccess(Bundle bundle) {
                if (b.this.b) {
                    return;
                }
                handler.removeCallbacksAndMessages(null);
                b.this.f = bundle.getString("security_phone");
                b.this.g = bundle.getString("net_type");
                iAuthorizeCallBack.onSuccess(b.this.f, b.this.g);
            }

            public void onError(AuthorizeErrorResponse authorizeErrorResponse) {
                if (b.this.b) {
                    return;
                }
                handler.removeCallbacksAndMessages(null);
                iAuthorizeCallBack.onFail(authorizeErrorResponse.platformErrorCode, authorizeErrorResponse.platformErrorMsg);
            }
        });
    }

    public void a(Activity activity, final IOneKeyLoginCallBack iOneKeyLoginCallBack) {
        if (this.c == null) {
            iOneKeyLoginCallBack.onFail(d, "一键登录service没有成功初始化");
            return;
        }
        final WeakReference weakReference = new WeakReference(activity);
        this.c.getAuthToken(new OnekeyLoginAdapter(activity) { // from class: com.ss.union.game.sdk.account.f.b.3
            public void onLoginSuccess(LoginByTicketResponse loginByTicketResponse) {
                com.ss.union.game.sdk.c.a.a("一键登录成功: ");
                if (a()) {
                    return;
                }
                iOneKeyLoginCallBack.onSuccess(loginByTicketResponse);
            }

            public void onLoginError(AuthorizeErrorResponse authorizeErrorResponse) {
                b.this.a(authorizeErrorResponse);
                if (a()) {
                    return;
                }
                iOneKeyLoginCallBack.onFail(authorizeErrorResponse.platformErrorCode, authorizeErrorResponse.platformErrorMsg);
            }

            boolean a() {
                return weakReference.get() == null || ((Activity) weakReference.get()).isFinishing();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(AuthorizeErrorResponse authorizeErrorResponse) {
        try {
            com.ss.union.game.sdk.c.a.a("=============一键登录失败=================>");
            com.ss.union.game.sdk.c.a.a("code=" + authorizeErrorResponse.platformErrorCode + ",msg=" + authorizeErrorResponse.platformErrorMsg);
            com.ss.union.game.sdk.c.a.a("<============一键登录失败==================");
        } catch (Throwable unused) {
        }
    }

    public void b() {
        if (this.c != null) {
            this.c.cancel();
            this.c = null;
        }
        a = null;
    }

    public String c() {
        return this.f;
    }

    public String d() {
        return this.g;
    }

    public static int a(String str) {
        try {
            return Integer.parseInt(str);
        } catch (Exception unused) {
            return 0;
        }
    }

    public static String b(String str) {
        return TextUtils.isEmpty(str) ? "" : str;
    }

    public static int a(int i) {
        switch (i) {
            case b.a.h /* -19 */:
                return ResourceUtils.getStringIdByName("ss_error_service_unavailable");
            case b.a.g /* -18 */:
            default:
                return ResourceUtils.getStringIdByName("ss_error_unknown");
            case -17:
                return ResourceUtils.getStringIdByName("ss_error_api_error");
            case -16:
                return ResourceUtils.getStringIdByName("ss_error_server_error");
            case -15:
                return ResourceUtils.getStringIdByName("ss_error_network_error");
            case -14:
                return ResourceUtils.getStringIdByName("ss_error_network_timeout");
            case -13:
                return ResourceUtils.getStringIdByName("ss_error_connect_timeout");
            case -12:
                return ResourceUtils.getStringIdByName("game_sdk_network_error_tips");
        }
    }

    public static String c(String str) {
        try {
            return str.replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2");
        } catch (Exception unused) {
            return str;
        }
    }

    public static void a(int i, String str) {
        if (!TextUtils.isEmpty(str)) {
            ToastUtils.getInstance().toast(str);
            return;
        }
        ToastUtils.getInstance().toast(GlobalApplicationUtils.getContext().getResources().getString(a(i)) + l.s + i + l.t);
    }
}
