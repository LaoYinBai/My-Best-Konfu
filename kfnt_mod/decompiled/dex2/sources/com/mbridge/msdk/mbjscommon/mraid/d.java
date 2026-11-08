package com.mbridge.msdk.mbjscommon.mraid;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioManager;
import com.mbridge.msdk.playercommon.exoplayer2.util.MimeTypes;
import java.lang.ref.WeakReference;

/* compiled from: MraidVolumeChangeReceiver.java */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public final class d {
    public static double a = -1.0d;
    private Context b;
    private AudioManager c;
    private boolean d = false;
    private b e;
    private a f;

    /* compiled from: MraidVolumeChangeReceiver.java */
    public interface b {
        void a(double d);
    }

    public d(Context context) {
        this.b = context;
        this.c = (AudioManager) context.getApplicationContext().getSystemService(MimeTypes.BASE_TYPE_AUDIO);
    }

    public final double a() {
        double streamVolume = ((this.c != null ? this.c.getStreamVolume(3) : -1) * 100.0d) / (this.c != null ? this.c.getStreamMaxVolume(3) : -1);
        a = streamVolume;
        return streamVolume;
    }

    public final b b() {
        return this.e;
    }

    public final void a(b bVar) {
        this.e = bVar;
    }

    public final void c() {
        if (this.b != null) {
            this.f = new a(this);
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.media.VOLUME_CHANGED_ACTION");
            this.b.registerReceiver(this.f, intentFilter);
            this.d = true;
        }
    }

    public final void d() {
        if (!this.d || this.b == null) {
            return;
        }
        try {
            this.b.unregisterReceiver(this.f);
            this.e = null;
            this.d = false;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* compiled from: MraidVolumeChangeReceiver.java */
    private static class a extends BroadcastReceiver {
        private WeakReference<d> a;

        public a(d dVar) {
            this.a = new WeakReference<>(dVar);
        }

        @Override // android.content.BroadcastReceiver
        public final void onReceive(Context context, Intent intent) {
            d dVar;
            b bVarB;
            if (!"android.media.VOLUME_CHANGED_ACTION".equals(intent.getAction()) || intent.getIntExtra("android.media.EXTRA_VOLUME_STREAM_TYPE", -1) != 3 || (dVar = this.a.get()) == null || (bVarB = dVar.b()) == null) {
                return;
            }
            double dA = dVar.a();
            if (dA >= 0.0d) {
                bVarB.a(dA);
            }
        }
    }
}
