package com.alipay.security.mobile.module.http;

import android.content.Context;
import com.alipay.android.phone.mrpc.core.aa;
import com.alipay.android.phone.mrpc.core.h;
import com.alipay.android.phone.mrpc.core.w;
import com.alipay.tscenter.biz.rpc.deviceFp.BugTrackMessageService;
import com.alipay.tscenter.biz.rpc.report.general.DataReportService;
import com.alipay.tscenter.biz.rpc.report.general.model.DataReportRequest;
import com.alipay.tscenter.biz.rpc.report.general.model.DataReportResult;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class b implements a {
    private static b d;
    private static DataReportResult e;

    /* renamed from: a, reason: collision with root package name */
    private w f2723a;

    /* renamed from: b, reason: collision with root package name */
    private BugTrackMessageService f2724b;

    /* renamed from: c, reason: collision with root package name */
    private DataReportService f2725c;

    private b(Context context, String str) {
        this.f2723a = null;
        this.f2724b = null;
        this.f2725c = null;
        aa aaVar = new aa();
        aaVar.a(str);
        this.f2723a = new h(context);
        this.f2724b = (BugTrackMessageService) this.f2723a.a(BugTrackMessageService.class, aaVar);
        this.f2725c = (DataReportService) this.f2723a.a(DataReportService.class, aaVar);
    }

    public static synchronized b a(Context context, String str) {
        if (d == null) {
            d = new b(context, str);
        }
        return d;
    }

    @Override // com.alipay.security.mobile.module.http.a
    public DataReportResult a(DataReportRequest dataReportRequest) throws InterruptedException {
        if (dataReportRequest == null) {
            return null;
        }
        if (this.f2725c != null) {
            e = null;
            new Thread(new c(this, dataReportRequest)).start();
            for (int i = com.alipay.security.mobile.module.http.constant.a.f2728a; e == null && i >= 0; i -= 50) {
                Thread.sleep(50L);
            }
        }
        return e;
    }

    @Override // com.alipay.security.mobile.module.http.a
    public boolean a(String str) {
        String strLogCollect;
        if (com.alipay.security.mobile.module.a.a.a(str) || this.f2724b == null) {
            return false;
        }
        try {
            strLogCollect = this.f2724b.logCollect(com.alipay.security.mobile.module.a.a.f(str));
        } catch (Throwable unused) {
            strLogCollect = null;
        }
        if (com.alipay.security.mobile.module.a.a.a(strLogCollect)) {
            return false;
        }
        return ((Boolean) new JSONObject(strLogCollect).get("success")).booleanValue();
    }
}
