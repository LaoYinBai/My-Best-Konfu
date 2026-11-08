package com.mbridge.msdk.mbdownload;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.os.Build;
import android.widget.RemoteViews;
import androidx.core.app.NotificationCompat;
import com.mbridge.msdk.foundation.tools.m;
import com.mbridge.msdk.playercommon.exoplayer2.text.ttml.TtmlNode;
import java.lang.reflect.Field;

/* compiled from: NotificationBuilder.java */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class h {
    protected Context e;
    protected Notification f;
    protected Notification.Builder g;
    protected NotificationCompat.Builder h;
    protected PendingIntent i;
    protected RemoteViews j;

    public h(Context context) {
        this.e = context.getApplicationContext();
        this.j = new RemoteViews(this.e.getPackageName(), m.a(this.e, "mbridge_download_notify_layout", TtmlNode.TAG_LAYOUT));
        if ((context != null ? this.e.getApplicationInfo().targetSdkVersion : 0) >= 26 && Build.VERSION.SDK_INT >= 26) {
            this.h = new NotificationCompat.Builder(this.e, "download");
            this.h.setCustomContentView(this.j);
        } else if (Build.VERSION.SDK_INT >= 16) {
            this.g = new Notification.Builder(context);
            this.g.setContent(this.j);
        } else {
            this.f = new Notification();
        }
    }

    public final void e() throws IllegalAccessException, NoSuchFieldException, IllegalArgumentException {
        try {
            if ((this.e != null ? this.e.getApplicationInfo().targetSdkVersion : 0) >= 26 && Build.VERSION.SDK_INT >= 26) {
                Field declaredField = NotificationCompat.Builder.class.getDeclaredField("mActions");
                declaredField.setAccessible(true);
                declaredField.set(this.h, declaredField.get(this.h).getClass().newInstance());
            } else {
                if (Build.VERSION.SDK_INT < 16) {
                    return;
                }
                Field declaredField2 = Notification.Builder.class.getDeclaredField("mActions");
                declaredField2.setAccessible(true);
                declaredField2.set(this.g, declaredField2.get(this.g).getClass().newInstance());
            }
        } catch (Exception unused) {
        }
    }

    public final h a(boolean z) {
        if ((this.e != null ? this.e.getApplicationInfo().targetSdkVersion : 0) >= 26 && Build.VERSION.SDK_INT >= 26) {
            this.h.setOngoing(z);
        } else if (Build.VERSION.SDK_INT >= 16) {
            this.g.setOngoing(z);
        } else if (z) {
            this.f.flags |= 2;
        } else {
            this.f.flags &= -3;
        }
        return this;
    }

    public final h b(boolean z) {
        if ((this.e != null ? this.e.getApplicationInfo().targetSdkVersion : 0) >= 26 && Build.VERSION.SDK_INT >= 26) {
            this.h.setAutoCancel(z);
        } else if (Build.VERSION.SDK_INT >= 16) {
            this.g.setAutoCancel(z);
        } else if (z) {
            this.f.flags |= 16;
        } else {
            this.f.flags &= -17;
        }
        return this;
    }

    public final h a(int i) {
        if ((this.e != null ? this.e.getApplicationInfo().targetSdkVersion : 0) >= 26 && Build.VERSION.SDK_INT >= 26) {
            this.h.setSmallIcon(i);
        } else if (Build.VERSION.SDK_INT >= 16) {
            this.g.setSmallIcon(i);
        } else {
            this.f.icon = i;
        }
        return this;
    }

    public final h a(long j) {
        if ((this.e != null ? this.e.getApplicationInfo().targetSdkVersion : 0) >= 26 && Build.VERSION.SDK_INT >= 26) {
            this.h.setWhen(j);
        } else if (Build.VERSION.SDK_INT >= 16) {
            this.g.setWhen(j);
        } else {
            this.f.when = j;
        }
        return this;
    }
}
