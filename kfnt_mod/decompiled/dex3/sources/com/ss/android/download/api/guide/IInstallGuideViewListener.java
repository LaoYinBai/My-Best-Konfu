package com.ss.android.download.api.guide;

import android.app.Activity;
import android.graphics.drawable.Drawable;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public interface IInstallGuideViewListener {
    void dismissDialog();

    void showGuide(Activity activity, int i, String str, Drawable drawable, String str2, long j, IInstallGuideEndCallback iInstallGuideEndCallback);
}
