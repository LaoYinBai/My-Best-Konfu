package com.ss.android.message;

import android.content.Context;
import android.content.Intent;
import com.bytedance.push.alive.ForegroundService;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
final class c {
    public static Intent a(Context context) {
        return new Intent(context.getApplicationContext(), a());
    }

    public static Intent b(Context context) {
        Intent intentA = a(context);
        intentA.setAction("pull_do_schedule");
        intentA.putExtra("from_schedule", true);
        return intentA;
    }

    public static void c(Context context) {
        try {
            Intent intentA = a(context);
            intentA.putExtra("do_schedule_start", true);
            intentA.putExtra("do_schedule_start_type", 1);
            context.startService(intentA);
        } catch (Exception unused) {
        }
    }

    public static Class<?> a() {
        return ForegroundService.class;
    }
}
