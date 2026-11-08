package com.alipay.security.mobile.module.http;

import com.alipay.tscenter.biz.rpc.report.general.model.DataReportRequest;
import com.alipay.tscenter.biz.rpc.report.general.model.DataReportResult;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
class c implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    final /* synthetic */ DataReportRequest f2726a;

    /* renamed from: b, reason: collision with root package name */
    final /* synthetic */ b f2727b;

    c(b bVar, DataReportRequest dataReportRequest) {
        this.f2727b = bVar;
        this.f2726a = dataReportRequest;
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            DataReportResult unused = b.e = this.f2727b.f2725c.reportData(this.f2726a);
        } catch (Throwable th) {
            DataReportResult unused2 = b.e = new DataReportResult();
            b.e.success = false;
            b.e.resultCode = "static data rpc upload error, " + com.alipay.security.mobile.module.a.a.a(th);
            new StringBuilder("rpc failed:").append(com.alipay.security.mobile.module.a.a.a(th));
        }
    }
}
