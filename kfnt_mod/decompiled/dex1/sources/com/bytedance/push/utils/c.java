package com.bytedance.push.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Process;
import android.text.TextUtils;
import android.util.Log;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class c {
    public static void a(Context context) {
        Context applicationContext = context.getApplicationContext();
        d<Long> dVarB = b(applicationContext);
        long jCurrentTimeMillis = System.currentTimeMillis();
        dVarB.a((d<Long>) Long.valueOf(jCurrentTimeMillis));
        if (dVarB.a() < 5 || jCurrentTimeMillis - dVarB.a(0).longValue() > 7000) {
            e.a("KillProcess", "killProcess: real kill " + Log.getStackTraceString(new Throwable()));
            a(applicationContext, dVarB);
            Process.killProcess(Process.myPid());
        }
    }

    private static d<Long> b(Context context) {
        SharedPreferences sharedPreferences = context.getApplicationContext().getSharedPreferences("kill_process_sp", 0);
        d<Long> dVar = new d<>(5);
        String string = sharedPreferences.getString("last_kill_times", "");
        if (TextUtils.isEmpty(string)) {
            return dVar;
        }
        for (String str : string.split("\\|")) {
            try {
                dVar.a((d<Long>) Long.valueOf(Long.parseLong(str)));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return dVar;
    }

    @SuppressLint({"ApplySharedPref"})
    private static void a(Context context, d<Long> dVar) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < dVar.a(); i++) {
            sb.append(dVar.a(i));
            sb.append("|");
        }
        SharedPreferences sharedPreferences = context.getApplicationContext().getSharedPreferences("kill_process_sp", 0);
        if (com.bytedance.common.push.utility.a.b()) {
            com.bytedance.common.push.utility.a.b("KillProcess", "killTimesStr = " + ((Object) sb));
        }
        sharedPreferences.edit().putString("last_kill_times", sb.toString()).commit();
    }
}
