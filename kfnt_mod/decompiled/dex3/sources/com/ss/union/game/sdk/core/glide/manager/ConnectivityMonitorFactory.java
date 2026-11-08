package com.ss.union.game.sdk.core.glide.manager;

import android.content.Context;
import com.ss.union.game.sdk.core.glide.manager.ConnectivityMonitor;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public interface ConnectivityMonitorFactory {
    ConnectivityMonitor build(Context context, ConnectivityMonitor.ConnectivityListener connectivityListener);
}
