package com.bytedance.push.c;

import android.app.Notification;
import android.content.Context;
import android.graphics.Bitmap;
import com.bytedance.push.PushBody;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public interface m {
    Notification a(Context context, int i, PushBody pushBody, Bitmap bitmap, Notification notification);

    boolean a(Context context, int i, PushBody pushBody);
}
