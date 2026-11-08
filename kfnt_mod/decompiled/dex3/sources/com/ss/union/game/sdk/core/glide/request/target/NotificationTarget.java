package com.ss.union.game.sdk.core.glide.request.target;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.widget.RemoteViews;
import com.ss.union.game.sdk.core.glide.request.transition.Transition;
import com.ss.union.game.sdk.core.glide.util.Preconditions;
import com.umeng.message.entity.UMessage;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class NotificationTarget extends SimpleTarget<Bitmap> {
    private final RemoteViews a;
    private final Context b;
    private final int c;
    private final String d;
    private final Notification e;
    private final int f;

    @Override // com.ss.union.game.sdk.core.glide.request.target.Target
    public /* bridge */ /* synthetic */ void onResourceReady(Object obj, Transition transition) {
        onResourceReady((Bitmap) obj, (Transition<? super Bitmap>) transition);
    }

    public NotificationTarget(Context context, int i, RemoteViews remoteViews, Notification notification, int i2) {
        this(context, i, remoteViews, notification, i2, null);
    }

    public NotificationTarget(Context context, int i, RemoteViews remoteViews, Notification notification, int i2, String str) {
        this(context, Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL, i, remoteViews, notification, i2, str);
    }

    public NotificationTarget(Context context, int i, int i2, int i3, RemoteViews remoteViews, Notification notification, int i4, String str) {
        super(i, i2);
        this.b = (Context) Preconditions.checkNotNull(context, "Context must not be null!");
        this.e = (Notification) Preconditions.checkNotNull(notification, "Notification object can not be null!");
        this.a = (RemoteViews) Preconditions.checkNotNull(remoteViews, "RemoteViews object can not be null!");
        this.f = i3;
        this.c = i4;
        this.d = str;
    }

    private void a() {
        ((NotificationManager) Preconditions.checkNotNull((NotificationManager) this.b.getSystemService(UMessage.DISPLAY_TYPE_NOTIFICATION))).notify(this.d, this.c, this.e);
    }

    public void onResourceReady(Bitmap bitmap, Transition<? super Bitmap> transition) {
        this.a.setImageViewBitmap(this.f, bitmap);
        a();
    }
}
