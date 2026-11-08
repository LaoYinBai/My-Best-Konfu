package com.bytedance.sdk.account.platform.onekey;

import android.text.TextUtils;
import android.util.Log;
import cn.com.chinatelecom.account.api.TraceLogger;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class CTLogger implements TraceLogger {
    private String mTag;

    CTLogger(String str) {
        this.mTag = str;
    }

    @Override // cn.com.chinatelecom.account.api.TraceLogger
    public void debug(String str, String str2) {
        Log.d(getTag(str), str2 + "");
    }

    @Override // cn.com.chinatelecom.account.api.TraceLogger
    public void info(String str, String str2) {
        Log.i(getTag(str), str2 + "");
    }

    @Override // cn.com.chinatelecom.account.api.TraceLogger
    public void warn(String str, String str2, Throwable th) {
        Log.w(getTag(str), str2);
    }

    private String getTag(String str) {
        if (TextUtils.isEmpty(this.mTag)) {
            return str + "";
        }
        return this.mTag;
    }
}
