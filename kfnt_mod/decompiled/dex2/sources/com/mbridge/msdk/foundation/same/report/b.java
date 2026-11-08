package com.mbridge.msdk.foundation.same.report;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import com.mbridge.msdk.foundation.entity.CampaignEx;
import com.mbridge.msdk.foundation.entity.q;
import com.mbridge.msdk.foundation.tools.o;
import com.meizu.cloud.pushsdk.constants.PushConstants;

/* compiled from: PlayableReportUtils.java */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public final class b {
    private static Handler a = new Handler(Looper.getMainLooper());

    public static void a(CampaignEx campaignEx, String str, String str2, String str3) {
        if (campaignEx == null || !campaignEx.isMraid()) {
            return;
        }
        q qVar = new q("2000043", TextUtils.isEmpty(campaignEx.getMraid()) ^ true ? 1 : 3, PushConstants.PUSH_TYPE_NOTIFY, "", campaignEx.getId(), str2, str, String.valueOf(campaignEx.getKeyIaRst()));
        qVar.n(campaignEx.getId());
        qVar.k(campaignEx.getRequestId());
        qVar.l(campaignEx.getRequestIdNotice());
        qVar.h(str3);
        qVar.b(o.C(com.mbridge.msdk.foundation.controller.a.f().j()));
        qVar.a(campaignEx.isMraid() ? q.a : q.b);
        a(qVar, str2);
    }

    public static void a(q qVar, String str) {
        if (qVar != null) {
            qVar.b(o.A());
            String strD = q.d(qVar);
            if (a.a().c()) {
                a.a().a(strD);
            } else {
                a(strD, com.mbridge.msdk.foundation.controller.a.f().j(), str);
            }
        }
    }

    public static void a(q qVar, Context context, String str) {
        if (qVar != null) {
            qVar.o("2000060");
            qVar.m(str);
            qVar.b(o.C(context));
            String strB = q.b(qVar);
            if (a.a().c()) {
                a.a().a(strB);
            } else {
                a(strB, context, str);
            }
        }
    }

    public static void b(q qVar, String str) {
        if (qVar != null) {
            qVar.o("2000059");
            qVar.m(str);
            qVar.b(o.C(com.mbridge.msdk.foundation.controller.a.f().j()));
            qVar.b(o.A());
            String strC = q.c(qVar);
            if (a.a().c()) {
                a.a().a(strC);
            } else {
                a(strC, com.mbridge.msdk.foundation.controller.a.f().j(), str);
            }
        }
    }

    public static void a(String str, Context context, String str2) {
        if (context == null || TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return;
        }
        a(new com.mbridge.msdk.foundation.same.f.a(str, str2));
    }

    public static void b(q qVar, Context context, String str) {
        String strG = q.g(qVar);
        if (a.a().c()) {
            a.a().a(strG);
        } else {
            a(strG, context, str);
        }
    }

    public static void c(q qVar, Context context, String str) {
        String strH = q.h(qVar);
        if (a.a().c()) {
            a.a().a(strH);
        } else {
            a(strH, context, str);
        }
    }

    public static void d(q qVar, Context context, String str) {
        if (qVar != null) {
            qVar.o("2000063");
            qVar.m(str);
            qVar.b(o.C(context));
            String strA = q.a(qVar);
            if (a.a().c()) {
                a.a().a(strA);
            } else {
                a(strA, context, str);
            }
        }
    }

    private static void a(Runnable runnable) {
        if (a != null) {
            a.post(runnable);
        }
    }
}
