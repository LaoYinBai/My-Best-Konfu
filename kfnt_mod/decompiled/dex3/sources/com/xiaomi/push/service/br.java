package com.xiaomi.push.service;

import android.content.Context;
import com.xiaomi.push.Cif;
import com.xiaomi.push.hj;
import com.xiaomi.push.hz;
import com.xiaomi.push.ia;
import com.xiaomi.push.ie;
import com.xiaomi.push.ii;
import com.xiaomi.push.ik;
import com.xiaomi.push.il;
import com.xiaomi.push.im;
import com.xiaomi.push.io;
import com.xiaomi.push.iq;
import com.xiaomi.push.is;
import com.xiaomi.push.it;
import com.xiaomi.push.iu;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class br {
    public static iu a(Context context, Cif cif) {
        if (cif.m469b()) {
            return null;
        }
        byte[] bArrM467a = cif.m467a();
        iu iuVarA = a(cif.a(), cif.f639b);
        if (iuVarA != null) {
            it.a(iuVarA, bArrM467a);
        }
        return iuVarA;
    }

    private static iu a(hj hjVar, boolean z) {
        switch (hjVar) {
            case Registration:
                return new ik();
            case UnRegistration:
                return new iq();
            case Subscription:
                return new io();
            case UnSubscription:
                return new is();
            case SendMessage:
                return new im();
            case AckMessage:
                return new hz();
            case SetConfig:
                return new ie();
            case ReportFeedback:
                return new il();
            case Notification:
                if (z) {
                    return new ii();
                }
                ia iaVar = new ia();
                iaVar.a(true);
                return iaVar;
            case Command:
                return new ie();
            default:
                return null;
        }
    }
}
