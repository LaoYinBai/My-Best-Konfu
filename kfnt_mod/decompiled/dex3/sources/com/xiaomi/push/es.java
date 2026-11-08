package com.xiaomi.push;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.qq.e.comm.constants.ErrorCode;
import com.xiaomi.clientreport.data.EventClientReport;
import com.xiaomi.clientreport.data.PerfClientReport;
import com.xiaomi.clientreport.manager.ClientReportClient;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class es {
    private static volatile es a;

    /* renamed from: a, reason: collision with other field name */
    private Context f329a;

    private es(Context context) {
        this.f329a = context;
    }

    public static es a(Context context) {
        if (a == null) {
            synchronized (es.class) {
                if (a == null) {
                    a = new es(context);
                }
            }
        }
        return a;
    }

    private void a(com.xiaomi.clientreport.data.a aVar) {
        if (aVar instanceof PerfClientReport) {
            ClientReportClient.reportPerf(this.f329a, (PerfClientReport) aVar);
        } else if (aVar instanceof EventClientReport) {
            ClientReportClient.reportEvent(this.f329a, (EventClientReport) aVar);
        }
    }

    public void a(String str, int i, long j, long j2) {
        if (i < 0 || j2 < 0 || j <= 0) {
            return;
        }
        PerfClientReport perfClientReportA = er.a(this.f329a, i, j, j2);
        perfClientReportA.setAppPackageName(str);
        perfClientReportA.setSdkVersion("3_8_2");
        a(perfClientReportA);
    }

    public void a(String str, Intent intent, int i, String str2) {
        if (intent == null) {
            return;
        }
        a(str, er.m292a(intent.getIntExtra("eventMessageType", -1)), intent.getStringExtra("messageId"), i, System.currentTimeMillis(), str2);
    }

    public void a(String str, Intent intent, String str2) {
        if (intent == null) {
            return;
        }
        a(str, er.m292a(intent.getIntExtra("eventMessageType", -1)), intent.getStringExtra("messageId"), ErrorCode.SERVER_JSON_PARSE_ERROR, System.currentTimeMillis(), str2);
    }

    public void a(String str, String str2, String str3, int i, long j, String str4) {
        if (TextUtils.isEmpty(str2) || TextUtils.isEmpty(str3)) {
            return;
        }
        EventClientReport eventClientReportA = er.a(this.f329a, str2, str3, i, j, str4);
        eventClientReportA.setAppPackageName(str);
        eventClientReportA.setSdkVersion("3_8_2");
        a(eventClientReportA);
    }

    public void a(String str, String str2, String str3, int i, String str4) {
        a(str, str2, str3, i, System.currentTimeMillis(), str4);
    }

    public void a(String str, String str2, String str3, String str4) {
        a(str, str2, str3, ErrorCode.VIDEO_DOWNLOAD_FAIL, System.currentTimeMillis(), str4);
    }

    public void b(String str, String str2, String str3, String str4) {
        a(str, str2, str3, ErrorCode.SERVER_JSON_PARSE_ERROR, System.currentTimeMillis(), str4);
    }

    public void c(String str, String str2, String str3, String str4) {
        a(str, str2, str3, ErrorCode.MANIFEST_ERROR, System.currentTimeMillis(), str4);
    }
}
