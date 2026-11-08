package com.ss.union.game.sdk.core.glide.request.target;

import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.graphics.Bitmap;
import android.widget.RemoteViews;
import com.ss.union.game.sdk.core.glide.request.transition.Transition;
import com.ss.union.game.sdk.core.glide.util.Preconditions;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class AppWidgetTarget extends SimpleTarget<Bitmap> {
    private final int[] a;
    private final ComponentName b;
    private final RemoteViews c;
    private final Context d;
    private final int e;

    @Override // com.ss.union.game.sdk.core.glide.request.target.Target
    public /* bridge */ /* synthetic */ void onResourceReady(Object obj, Transition transition) {
        onResourceReady((Bitmap) obj, (Transition<? super Bitmap>) transition);
    }

    public AppWidgetTarget(Context context, int i, int i2, int i3, RemoteViews remoteViews, int... iArr) {
        super(i, i2);
        if (iArr.length == 0) {
            throw new IllegalArgumentException("WidgetIds must have length > 0");
        }
        this.d = (Context) Preconditions.checkNotNull(context, "Context can not be null!");
        this.c = (RemoteViews) Preconditions.checkNotNull(remoteViews, "RemoteViews object can not be null!");
        this.a = (int[]) Preconditions.checkNotNull(iArr, "WidgetIds can not be null!");
        this.e = i3;
        this.b = null;
    }

    public AppWidgetTarget(Context context, int i, RemoteViews remoteViews, int... iArr) {
        this(context, Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL, i, remoteViews, iArr);
    }

    public AppWidgetTarget(Context context, int i, int i2, int i3, RemoteViews remoteViews, ComponentName componentName) {
        super(i, i2);
        this.d = (Context) Preconditions.checkNotNull(context, "Context can not be null!");
        this.c = (RemoteViews) Preconditions.checkNotNull(remoteViews, "RemoteViews object can not be null!");
        this.b = (ComponentName) Preconditions.checkNotNull(componentName, "ComponentName can not be null!");
        this.e = i3;
        this.a = null;
    }

    public AppWidgetTarget(Context context, int i, RemoteViews remoteViews, ComponentName componentName) {
        this(context, Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL, i, remoteViews, componentName);
    }

    private void a() {
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(this.d);
        if (this.b != null) {
            appWidgetManager.updateAppWidget(this.b, this.c);
        } else {
            appWidgetManager.updateAppWidget(this.a, this.c);
        }
    }

    public void onResourceReady(Bitmap bitmap, Transition<? super Bitmap> transition) {
        this.c.setImageViewBitmap(this.e, bitmap);
        a();
    }
}
