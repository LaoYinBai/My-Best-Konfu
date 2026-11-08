package com.alipay.security.mobile.module.d;

import com.bytedance.sdk.account.platform.api.IWeiboService;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public final class a {

    /* renamed from: a, reason: collision with root package name */
    private String f2714a;

    /* renamed from: b, reason: collision with root package name */
    private String f2715b;

    /* renamed from: c, reason: collision with root package name */
    private String f2716c;
    private String d;
    private String e;
    private String f;
    private String g;

    public a(String str, String str2, String str3, String str4, String str5, String str6, String str7) {
        this.f2714a = str;
        this.f2715b = str2;
        this.f2716c = str3;
        this.d = str4;
        this.e = str5;
        this.f = str6;
        this.g = str7;
    }

    public final String toString() {
        StringBuilder sb;
        String strSubstring;
        StringBuilder sb2;
        String strSubstring2;
        StringBuilder sb3;
        String strSubstring3;
        StringBuffer stringBuffer = new StringBuffer(new SimpleDateFormat("yyyyMMddHHmmssSSS").format(Calendar.getInstance().getTime()));
        stringBuffer.append(IWeiboService.Scope.EMPTY_SCOPE + this.f2714a);
        stringBuffer.append(IWeiboService.Scope.EMPTY_SCOPE + this.f2715b);
        stringBuffer.append(IWeiboService.Scope.EMPTY_SCOPE + this.f2716c);
        stringBuffer.append(IWeiboService.Scope.EMPTY_SCOPE + this.d);
        if (com.alipay.security.mobile.module.a.a.a(this.e) || this.e.length() < 20) {
            sb = new StringBuilder(IWeiboService.Scope.EMPTY_SCOPE);
            strSubstring = this.e;
        } else {
            sb = new StringBuilder(IWeiboService.Scope.EMPTY_SCOPE);
            strSubstring = this.e.substring(0, 20);
        }
        sb.append(strSubstring);
        stringBuffer.append(sb.toString());
        if (com.alipay.security.mobile.module.a.a.a(this.f) || this.f.length() < 20) {
            sb2 = new StringBuilder(IWeiboService.Scope.EMPTY_SCOPE);
            strSubstring2 = this.f;
        } else {
            sb2 = new StringBuilder(IWeiboService.Scope.EMPTY_SCOPE);
            strSubstring2 = this.f.substring(0, 20);
        }
        sb2.append(strSubstring2);
        stringBuffer.append(sb2.toString());
        if (com.alipay.security.mobile.module.a.a.a(this.g) || this.g.length() < 20) {
            sb3 = new StringBuilder(IWeiboService.Scope.EMPTY_SCOPE);
            strSubstring3 = this.g;
        } else {
            sb3 = new StringBuilder(IWeiboService.Scope.EMPTY_SCOPE);
            strSubstring3 = this.g.substring(0, 20);
        }
        sb3.append(strSubstring3);
        stringBuffer.append(sb3.toString());
        return stringBuffer.toString();
    }
}
