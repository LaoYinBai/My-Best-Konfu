package com.mbridge.msdk.foundation.same.f;

import com.mbridge.msdk.foundation.same.net.g.d;
import com.mbridge.msdk.foundation.tools.s;

/* compiled from: ReportTask.java */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public final class a implements Runnable {
    private String a;
    private String b;

    public a(String str, String str2) {
        this.a = str;
        this.b = str2;
    }

    @Override // java.lang.Runnable
    public final void run() {
        try {
            s.d("ReportTask", "start report");
            new com.mbridge.msdk.foundation.same.report.d.a(com.mbridge.msdk.foundation.controller.a.f().j()).c(0, d.c().a, com.mbridge.msdk.foundation.same.report.d.a(this.a, com.mbridge.msdk.foundation.controller.a.f().j(), this.b), new com.mbridge.msdk.foundation.same.report.d.b() { // from class: com.mbridge.msdk.foundation.same.f.a.1
                @Override // com.mbridge.msdk.foundation.same.report.d.b
                public final void a(String str) {
                    s.d("ReportTask", str);
                }

                @Override // com.mbridge.msdk.foundation.same.report.d.b
                public final void b(String str) {
                    s.d("ReportTask", str);
                }
            });
        } catch (Throwable th) {
            s.d("ReportTask", th.getMessage());
        }
    }
}
