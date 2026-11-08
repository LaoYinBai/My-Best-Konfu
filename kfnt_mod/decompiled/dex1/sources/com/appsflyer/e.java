package com.appsflyer;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import androidx.annotation.NonNull;
import anet.channel.strategy.dispatch.DispatchConstants;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
final class e {

    /* renamed from: ˎ, reason: contains not printable characters */
    private IntentFilter f176 = new IntentFilter("android.intent.action.BATTERY_CHANGED");

    static final class b {

        /* renamed from: ˊ, reason: contains not printable characters */
        static final e f177 = new e();
    }

    e() {
    }

    @NonNull
    /* renamed from: ˋ, reason: contains not printable characters */
    final d m148(Context context) {
        String str;
        String str2 = null;
        float f = 0.0f;
        try {
            Intent intentRegisterReceiver = context.registerReceiver(null, this.f176);
            if (intentRegisterReceiver != null) {
                if (2 == intentRegisterReceiver.getIntExtra("status", -1)) {
                    int intExtra = intentRegisterReceiver.getIntExtra("plugged", -1);
                    if (intExtra != 4) {
                        switch (intExtra) {
                            case 1:
                                str = "ac";
                                break;
                            case 2:
                                str = "usb";
                                break;
                            default:
                                str = DispatchConstants.OTHER;
                                break;
                        }
                    } else {
                        str = "wireless";
                    }
                } else {
                    str = "no";
                }
                str2 = str;
                int intExtra2 = intentRegisterReceiver.getIntExtra("level", -1);
                int intExtra3 = intentRegisterReceiver.getIntExtra("scale", -1);
                if (-1 != intExtra2 && -1 != intExtra3) {
                    f = (intExtra2 * 100.0f) / intExtra3;
                }
            }
        } catch (Throwable unused) {
        }
        return new d(f, str2);
    }

    static final class d {

        /* renamed from: ˋ, reason: contains not printable characters */
        private final String f178;

        /* renamed from: ॱ, reason: contains not printable characters */
        private final float f179;

        d(float f, String str) {
            this.f179 = f;
            this.f178 = str;
        }

        /* renamed from: ˎ, reason: contains not printable characters */
        final float m150() {
            return this.f179;
        }

        /* renamed from: ˋ, reason: contains not printable characters */
        final String m149() {
            return this.f178;
        }

        d() {
        }
    }
}
