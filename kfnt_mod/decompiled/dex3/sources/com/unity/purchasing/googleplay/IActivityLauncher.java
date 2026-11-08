package com.unity.purchasing.googleplay;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public interface IActivityLauncher {
    void startActivity(Context context, Intent intent);

    void startIntentSenderForResult(Activity activity, PendingIntent pendingIntent, int i, Intent intent, String str) throws IntentSender.SendIntentException;
}
