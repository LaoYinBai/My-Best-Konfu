package com.ss.union.game.sdk.core.vapp;

import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.view.Window;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;
import com.ss.android.socialbase.downloader.utils.DownloadExpSwitchCode;
import com.ss.union.game.sdk.common.util.ResourceUtils;
import com.ss.union.game.sdk.core.glide.request.target.Target;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
class a extends FragmentActivity {
    a() {
    }

    protected void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        Window window = getWindow();
        window.clearFlags(DownloadExpSwitchCode.BACK_BUGFIX_SIGBUS);
        if (Build.VERSION.SDK_INT >= 21) {
            window.addFlags(Target.SIZE_ORIGINAL);
            window.setStatusBarColor(Color.parseColor("#00000000"));
        }
        getWindow().addFlags(com.ss.union.game.sdk.feedback.picture.select.d.d);
        window.getDecorView().setSystemUiVisibility(5894);
        overridePendingTransition(ResourceUtils.getAnimIdByName("lg_anim_slide_in_right"), ResourceUtils.getAnimIdByName("lg_anim_slide_out_left"));
    }

    protected void onResume() {
        try {
            getWindow().addFlags(com.ss.union.game.sdk.feedback.picture.select.d.d);
            getWindow().getDecorView().setSystemUiVisibility(5894);
        } catch (Exception unused) {
        }
        super.onResume();
    }

    protected void onPause() {
        super.onPause();
    }

    public void onConfigurationChanged(Configuration configuration) {
        final View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(decorView.getSystemUiVisibility() | 2);
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() { // from class: com.ss.union.game.sdk.core.vapp.a.1
            @Override // java.lang.Runnable
            public void run() {
                decorView.setSystemUiVisibility(decorView.getSystemUiVisibility() | 2);
            }
        }, 300L);
        super.onConfigurationChanged(configuration);
    }

    protected void onDestroy() {
        super.onDestroy();
    }

    public void finish() {
        super.finish();
        overridePendingTransition(0, ResourceUtils.getAnimIdByName("lg_anim_slide_out_right"));
    }
}
