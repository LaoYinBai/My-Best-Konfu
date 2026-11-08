package com.xiaomi.push;

import android.content.Context;
import com.xiaomi.clientreport.processor.IEventProcessor;
import com.xiaomi.clientreport.processor.IPerfProcessor;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class bp implements Runnable {
    private Context a;

    /* renamed from: a, reason: collision with other field name */
    private com.xiaomi.clientreport.processor.c f158a;

    public void a(Context context) {
        this.a = context;
    }

    public void a(com.xiaomi.clientreport.processor.c cVar) {
        this.f158a = cVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        bt btVarA;
        String str;
        String str2;
        long jCurrentTimeMillis;
        try {
            if (this.f158a != null) {
                this.f158a.a();
            }
            com.xiaomi.channel.commonutils.logger.b.c("begin read and send perf / event");
            if (this.f158a instanceof IEventProcessor) {
                btVarA = bt.a(this.a);
                str = "sp_client_report_status";
                str2 = "event_last_upload_time";
                jCurrentTimeMillis = System.currentTimeMillis();
            } else {
                if (!(this.f158a instanceof IPerfProcessor)) {
                    return;
                }
                btVarA = bt.a(this.a);
                str = "sp_client_report_status";
                str2 = "perf_last_upload_time";
                jCurrentTimeMillis = System.currentTimeMillis();
            }
            btVarA.m140a(str, str2, jCurrentTimeMillis);
        } catch (Exception e) {
            com.xiaomi.channel.commonutils.logger.b.a(e);
        }
    }
}
