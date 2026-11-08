package com.ss.android.socialbase.appdownloader.depend;

import android.widget.Button;
import androidx.annotation.Nullable;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public interface IDownloadAlertDialog {
    void dismiss();

    @Nullable
    Button getButton(int i);

    boolean isShowing();

    void show();
}
