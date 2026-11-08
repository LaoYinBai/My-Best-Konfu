package com.unicom.xiaowo.login;

import android.content.Context;
import android.text.TextUtils;
import com.unicom.xiaowo.login.b.a;
import com.unicom.xiaowo.login.b.d;
import com.unicom.xiaowo.login.d.f;
import com.unicom.xiaowo.login.d.h;
import org.json.JSONException;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class UniAuthHelper {
    private static volatile UniAuthHelper s_instance;
    private a mAccountOpen;
    private Context mContext;

    private UniAuthHelper(Context context) {
        this.mContext = context.getApplicationContext();
        this.mAccountOpen = a.a(this.mContext);
    }

    private boolean envCheck(String str, String str2, ResultListener resultListener) throws JSONException {
        if (this.mContext == null || resultListener == null) {
            f.c("parameter error !");
            return false;
        }
        d.a().a(resultListener);
        if (!h.a(this.mContext)) {
            d.a().a(this.mContext, "网络未连接");
            return false;
        }
        if (TextUtils.isEmpty(str)) {
            d.a().a(this.mContext, "appId不能为空");
            return false;
        }
        if (!TextUtils.isEmpty(str2)) {
            return true;
        }
        d.a().a(this.mContext, "appSecret不能为空");
        return false;
    }

    public static UniAuthHelper getInstance(Context context) {
        if (s_instance == null) {
            synchronized (UniAuthHelper.class) {
                if (s_instance == null) {
                    s_instance = new UniAuthHelper(context);
                }
            }
        }
        return s_instance;
    }

    public void getAccesscode(String str, String str2, ResultListener resultListener) throws JSONException {
        if (envCheck(str, str2, resultListener)) {
            try {
                this.mAccountOpen.b(this.mContext, str, str2);
            } catch (Exception unused) {
                d.a().a(this.mContext, "getAccesscode异常");
            }
        }
    }

    public void getToken(String str, String str2, String str3, ResultListener resultListener) throws JSONException {
        d dVarA;
        Context context;
        String str4;
        if (envCheck(str, str2, resultListener)) {
            if (TextUtils.isEmpty(str3)) {
                dVarA = d.a();
                context = this.mContext;
                str4 = "accessCode不能为空";
            } else {
                try {
                    this.mAccountOpen.a(this.mContext, str, str2, str3);
                    return;
                } catch (Exception unused) {
                    dVarA = d.a();
                    context = this.mContext;
                    str4 = "getToken异常";
                }
            }
            dVarA.a(context, str4);
        }
    }

    public void login(String str, String str2, ResultListener resultListener) throws JSONException {
        if (envCheck(str, str2, resultListener)) {
            try {
                this.mAccountOpen.a(this.mContext, str, str2);
            } catch (Exception unused) {
                d.a().a(this.mContext, "sdk异常");
            }
        }
    }
}
