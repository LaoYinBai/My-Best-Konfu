package com.alipay.sdk.tid;

import android.content.Context;
import android.os.Looper;
import android.text.TextUtils;
import com.alipay.sdk.data.b;
import com.alipay.sdk.util.c;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class TidHelper {
    public static Tid loadTID(Context context) {
        a(context);
        Tid tidA = a(context, a.a(context));
        if (tidA == null) {
            c.a(com.alipay.sdk.cons.a.x, "load_tid null");
        }
        return tidA;
    }

    public static synchronized Tid loadOrCreateTID(Context context) {
        Tid tidB;
        c.a(com.alipay.sdk.cons.a.x, "load_create_tid");
        a(context);
        Tid tidLoadTID = loadTID(context);
        if (!Tid.isEmpty(tidLoadTID)) {
            tidB = tidLoadTID;
        } else {
            if (Looper.myLooper() == Looper.getMainLooper()) {
                return null;
            }
            try {
                tidB = b(context);
            } catch (Throwable unused) {
            }
        }
        return tidB;
    }

    public static synchronized String getTIDValue(Context context) {
        Tid tidLoadOrCreateTID;
        tidLoadOrCreateTID = loadOrCreateTID(context);
        return Tid.isEmpty(tidLoadOrCreateTID) ? "" : tidLoadOrCreateTID.getTid();
    }

    public static boolean resetTID(Context context) throws Exception {
        Tid tidB;
        c.a(com.alipay.sdk.cons.a.x, "reset_tid");
        if (Looper.myLooper() == Looper.getMainLooper()) {
            throw new Exception("Must be called on worker thread");
        }
        a(context);
        clearTID(context);
        try {
            tidB = b(context);
        } catch (Throwable unused) {
            tidB = null;
        }
        return !Tid.isEmpty(tidB);
    }

    public static void clearTID(Context context) {
        a.a(context).g();
    }

    public static String getIMEI(Context context) {
        a(context);
        return com.alipay.sdk.util.a.a(context).b();
    }

    public static String getIMSI(Context context) {
        a(context);
        return com.alipay.sdk.util.a.a(context).a();
    }

    public static String getVirtualImei(Context context) {
        a(context);
        b.b();
        return b.c();
    }

    public static String getVirtualImsi(Context context) {
        a(context);
        b.b();
        return b.d();
    }

    private static void a(Context context) {
        if (context == null) {
            return;
        }
        com.alipay.sdk.sys.b.a().a(context);
    }

    private static Tid b(Context context) throws Exception {
        try {
            com.alipay.sdk.packet.b bVarA = new com.alipay.sdk.packet.impl.c().a(com.alipay.sdk.sys.a.a(), context);
            if (bVarA == null) {
                return null;
            }
            JSONObject jSONObject = new JSONObject(bVarA.b());
            a aVarA = a.a(context);
            String strOptString = jSONObject.optString("tid");
            String string = jSONObject.getString("client_key");
            if (!TextUtils.isEmpty(strOptString) && !TextUtils.isEmpty(string)) {
                aVarA.a(strOptString, string);
            }
            return a(context, aVarA);
        } catch (Throwable unused) {
            return null;
        }
    }

    private static Tid a(Context context, a aVar) {
        if (aVar == null || aVar.e()) {
            return null;
        }
        return new Tid(aVar.a(), aVar.b(), aVar.i().longValue());
    }

    public static Tid loadLocalTid(Context context) {
        a aVarA = a.a(context);
        if (aVarA.h()) {
            return null;
        }
        return new Tid(aVarA.a(), aVarA.b(), aVarA.i().longValue());
    }
}
