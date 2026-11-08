package com.xiaomi.push;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.xiaomi.mipush.sdk.Constants;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
final class dn implements ds {
    dn() {
    }

    private void a(Context context, String str, String str2) {
        if (TextUtils.isEmpty(str2) || TextUtils.isEmpty(str)) {
            return;
        }
        try {
            if (dq.a(context, "12", 1L)) {
                return;
            }
            hr hrVar = new hr();
            hrVar.a(str + Constants.COLON_SEPARATOR + str2);
            hrVar.a(System.currentTimeMillis());
            hrVar.a(hl.BroadcastAction);
            dx.a(context, hrVar);
        } catch (Throwable unused) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(Context context, Intent intent) {
        int iA;
        try {
            String dataString = intent.getDataString();
            if (TextUtils.isEmpty(dataString)) {
                return;
            }
            String[] strArrSplit = dataString.split(Constants.COLON_SEPARATOR);
            if (strArrSplit.length >= 2 && !TextUtils.isEmpty(strArrSplit[1])) {
                String str = strArrSplit[1];
                long jCurrentTimeMillis = System.currentTimeMillis();
                boolean zA = com.xiaomi.push.service.aq.a(context).a(ho.BroadcastActionCollectionSwitch.a(), true);
                if (TextUtils.equals("android.intent.action.PACKAGE_RESTARTED", intent.getAction())) {
                    if (!dq.a(context, "12", 1L) && zA) {
                        if (TextUtils.isEmpty(dw.a)) {
                            dw.a += dr.f247a + Constants.COLON_SEPARATOR;
                        }
                        dw.a += str + com.umeng.message.proguard.l.s + jCurrentTimeMillis + com.umeng.message.proguard.l.t + Constants.ACCEPT_TIME_SEPARATOR_SP;
                        return;
                    }
                    return;
                }
                if (TextUtils.equals("android.intent.action.PACKAGE_CHANGED", intent.getAction())) {
                    if (!dq.a(context, "12", 1L) && zA) {
                        if (TextUtils.isEmpty(dw.b)) {
                            dw.b += dr.b + Constants.COLON_SEPARATOR;
                        }
                        dw.b += str + com.umeng.message.proguard.l.s + jCurrentTimeMillis + com.umeng.message.proguard.l.t + Constants.ACCEPT_TIME_SEPARATOR_SP;
                        return;
                    }
                    return;
                }
                if (TextUtils.equals("android.intent.action.PACKAGE_ADDED", intent.getAction())) {
                    if (intent.getExtras().getBoolean("android.intent.extra.REPLACING") || !zA) {
                        return;
                    } else {
                        iA = hl.BroadcastActionAdded.a();
                    }
                } else if (TextUtils.equals("android.intent.action.PACKAGE_REMOVED", intent.getAction())) {
                    if (intent.getExtras().getBoolean("android.intent.extra.REPLACING") || !zA) {
                        return;
                    } else {
                        iA = hl.BroadcastActionRemoved.a();
                    }
                } else if (TextUtils.equals("android.intent.action.PACKAGE_REPLACED", intent.getAction())) {
                    if (!zA) {
                        return;
                    } else {
                        iA = hl.BroadcastActionReplaced.a();
                    }
                } else if (!TextUtils.equals("android.intent.action.PACKAGE_DATA_CLEARED", intent.getAction()) || !zA) {
                    return;
                } else {
                    iA = hl.BroadcastActionDataCleared.a();
                }
                a(context, String.valueOf(iA), str);
            }
        } catch (Throwable unused) {
        }
    }

    @Override // com.xiaomi.push.ds
    public void a(Context context, Intent intent) {
        if (intent == null) {
            return;
        }
        dm.b().post(new Cdo(this, context, intent));
    }
}
