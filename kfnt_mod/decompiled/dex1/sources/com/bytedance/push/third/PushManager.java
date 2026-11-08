package com.bytedance.push.third;

import android.content.Context;
import android.content.pm.PackageManager;
import android.util.Log;
import com.bytedance.push.i;
import java.util.Iterator;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class PushManager implements IPushAdapter {
    private static final String TAG = "PushManager";
    private static volatile PushManager sPushManager;

    @Override // com.bytedance.push.third.IPushAdapter
    public boolean requestNotificationPermission(int i) {
        return false;
    }

    private PushManager() {
    }

    public static PushManager inst() {
        if (sPushManager == null) {
            synchronized (PushManager.class) {
                if (sPushManager == null) {
                    sPushManager = new PushManager();
                }
            }
        }
        return sPushManager;
    }

    @Override // com.bytedance.push.third.IPushAdapter
    public boolean isPushAvailable(Context context, int i) {
        IPushAdapter iPushAdapterA = e.a(context).a(i);
        if (iPushAdapterA == null) {
            return false;
        }
        try {
            return iPushAdapterA.isPushAvailable(context, i);
        } catch (Throwable unused) {
            return false;
        }
    }

    @Override // com.bytedance.push.third.IPushAdapter
    public void registerPush(Context context, int i) {
        IPushAdapter iPushAdapterA = e.a(context).a(i);
        if (iPushAdapterA != null) {
            try {
                i.o().a(i);
                iPushAdapterA.registerPush(context, i);
            } catch (Throwable unused) {
            }
        }
    }

    @Override // com.bytedance.push.third.IPushAdapter
    public void setAlias(Context context, String str, int i) {
        IPushAdapter iPushAdapterA = e.a(context).a(i);
        if (iPushAdapterA != null) {
            try {
                iPushAdapterA.setAlias(context, str, i);
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }

    @Override // com.bytedance.push.third.IPushAdapter
    public void unregisterPush(Context context, int i) {
        IPushAdapter iPushAdapterA = e.a(context).a(i);
        if (iPushAdapterA != null) {
            try {
                iPushAdapterA.unregisterPush(context, i);
            } catch (Throwable unused) {
            }
        }
    }

    @Override // com.bytedance.push.third.IPushAdapter
    public void trackPush(Context context, int i, Object obj) {
        IPushAdapter iPushAdapterA = e.a(context).a(i);
        if (iPushAdapterA != null) {
            try {
                iPushAdapterA.trackPush(context, i, obj);
            } catch (Throwable unused) {
            }
        }
    }

    @Override // com.bytedance.push.third.IPushAdapter
    public boolean checkThirdPushConfig(String str, Context context) {
        Iterator it = e.a(context).b().iterator();
        boolean zCheckThirdPushConfig = true;
        while (it.hasNext()) {
            IPushAdapter iPushAdapterA = e.a(context).a(((Integer) it.next()).intValue());
            if (iPushAdapterA != null) {
                try {
                    zCheckThirdPushConfig &= iPushAdapterA.checkThirdPushConfig(str, context);
                } catch (Throwable th) {
                    th.printStackTrace();
                    com.bytedance.common.push.utility.a.e(str, "check pushType error: " + Log.getStackTraceString(th));
                    zCheckThirdPushConfig = false;
                }
            }
        }
        try {
            return com.bytedance.push.a.a.a(context, str) & zCheckThirdPushConfig & com.bytedance.push.alive.b.a(context).a(str);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            com.bytedance.common.push.utility.a.e(str, "check pushType error: " + Log.getStackTraceString(e));
            return false;
        }
    }
}
