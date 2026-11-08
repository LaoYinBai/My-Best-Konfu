package com.sigmob.sdk.base.common;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.text.TextUtils;
import com.sigmob.sdk.common.Constants;
import com.sigmob.sdk.common.utils.m;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public abstract class BaseBroadcastReceiver extends BroadcastReceiver {
    private Context a;
    private String b;

    public BaseBroadcastReceiver(String str) {
        this.b = str;
    }

    public static void a(Context context, String str, String str2) {
        a(context, str, null, str2, 0);
    }

    public static void a(Context context, String str, String str2, int i) {
        a(context, str, null, str2, i);
    }

    public static void a(Context context, String str, Map<String, Object> map, String str2) {
        a(context, str, map, str2, 0);
    }

    public static void a(Context context, String str, Map<String, Object> map, String str2, int i) {
        m.a.a(context);
        m.a.a(str2);
        Intent intent = new Intent(str2);
        intent.putExtra(Constants.BROADCAST_IDENTIFIER_KEY, str);
        if (map != null) {
            for (String str3 : map.keySet()) {
                try {
                    intent.putExtra(str3, (String) map.get(str3));
                } catch (Throwable unused) {
                }
            }
        }
        w wVarA = w.a(context);
        if (wVarA != null) {
            try {
                wVarA.a(intent, i);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    protected abstract IntentFilter a();

    public void a(BroadcastReceiver broadcastReceiver) {
        if (this.a == null || broadcastReceiver == null) {
            return;
        }
        w wVarA = w.a(this.a);
        if (wVarA != null) {
            try {
                wVarA.a(broadcastReceiver);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        this.a = null;
    }

    public void a(BroadcastReceiver broadcastReceiver, Context context) {
        this.a = context.getApplicationContext();
        w wVarA = w.a(this.a);
        if (wVarA != null) {
            try {
                wVarA.a(broadcastReceiver, a());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    protected boolean a(Intent intent) {
        m.a.a(intent);
        String stringExtra = intent.getStringExtra(Constants.BROADCAST_IDENTIFIER_KEY);
        return !TextUtils.isEmpty(stringExtra) && this.b.equalsIgnoreCase(stringExtra);
    }
}
