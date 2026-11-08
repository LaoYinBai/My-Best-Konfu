package com.kwad.sdk.c.b;

import android.text.TextUtils;
import com.kwad.sdk.c.kwai.d;
import com.kwad.sdk.core.report.ReportAction;
import com.kwad.sdk.core.report.f;
import java.util.Random;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class b {
    public static void a(d dVar, int i) {
        if (a()) {
            ReportAction reportAction = new ReportAction(230L);
            reportAction.bz = dVar.a;
            reportAction.bA = dVar.e;
            reportAction.bB = dVar.g;
            reportAction.bC = i;
            reportAction.bD = System.currentTimeMillis();
            f.a(reportAction);
        }
    }

    public static void a(d dVar, int i, String str) {
        if (a()) {
            ReportAction reportAction = new ReportAction(230L);
            reportAction.bz = dVar.a;
            reportAction.bA = dVar.e;
            reportAction.bB = dVar.g;
            reportAction.bC = 0;
            reportAction.bE = i;
            reportAction.bD = System.currentTimeMillis();
            reportAction.bF = str;
            f.a(reportAction);
        }
    }

    private static boolean a() {
        String strU = com.kwad.sdk.core.config.b.U();
        if (TextUtils.isEmpty(strU)) {
            return false;
        }
        String[] strArrSplit = strU.split(":");
        if (strArrSplit.length == 2) {
            return new Random().nextInt(Integer.parseInt(strArrSplit[0])) <= Integer.parseInt(strArrSplit[1]);
        }
        return false;
    }
}
