package com.ss.union.game.sdk.core.splashEffect.activity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;
import com.ss.union.game.sdk.common.util.ActivityUtils;
import com.ss.union.game.sdk.common.util.ResourceUtils;
import com.ss.union.game.sdk.core.age_tips.AgeTipsImageView;
import com.ss.union.game.sdk.core.splashEffect.a;
import com.ss.union.game.sdk.core.splashEffect.view.a.b;
import com.ss.union.game.sdk.core.splashEffect.view.a.c;
import com.ss.union.game.sdk.feedback.picture.select.d;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class SplashEffectBaseActivity extends FragmentActivity {
    private boolean a = false;
    private b b;
    private FrameLayout c;
    private TextView d;

    public void onBackPressed() {
    }

    protected void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        overridePendingTransition(0, 0);
        a();
        b();
    }

    private void a() {
        Window window = getWindow();
        getWindow().addFlags(d.d);
        window.getDecorView().setSystemUiVisibility(5894);
        if (Build.VERSION.SDK_INT >= 28) {
            WindowManager.LayoutParams attributes = window.getAttributes();
            attributes.layoutInDisplayCutoutMode = 1;
            window.setAttributes(attributes);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void b() {
        setContentView(ResourceUtils.getLayoutIdByName("lg_splash_effect"));
        this.c = (FrameLayout) findViewById(ResourceUtils.getIdByName("lg_splash_effect_video_container"));
        this.d = (TextView) findViewById(ResourceUtils.getIdByName("lg_splash_effect_game_tips"));
        ((AgeTipsImageView) findViewById(ResourceUtils.getIdByName("lg_splash_effect_age_tips_image"))).init(this, 1);
        if (com.ss.union.game.sdk.core.age_tips.b.a(this).a()) {
            this.d.setVisibility(0);
            if (ActivityUtils.isPortrait(this)) {
                this.d.setText(ResourceUtils.getString("lg_age_tips_portrait_text"));
            } else {
                this.d.setText(ResourceUtils.getString("lg_age_tips_landscape_text"));
            }
        }
        this.b = a.a(this.c);
        this.b.setMediaPlayerListener(new c() { // from class: com.ss.union.game.sdk.core.splashEffect.activity.SplashEffectBaseActivity.1
            @Override // com.ss.union.game.sdk.core.splashEffect.view.a.c, com.ss.union.game.sdk.core.splashEffect.view.a.a
            public boolean a(MediaPlayer mediaPlayer, int i, int i2) {
                if (!SplashEffectBaseActivity.this.a) {
                    SplashEffectBaseActivity.this.finish();
                }
                return super.a(mediaPlayer, i, i2);
            }

            @Override // com.ss.union.game.sdk.core.splashEffect.view.a.c, com.ss.union.game.sdk.core.splashEffect.view.a.a
            public void a(MediaPlayer mediaPlayer) {
                if (!SplashEffectBaseActivity.this.a) {
                    SplashEffectBaseActivity.this.finish();
                }
                super.a(mediaPlayer);
            }
        });
    }

    public void finish() {
        super.finish();
        overridePendingTransition(0, 0);
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() { // from class: com.ss.union.game.sdk.core.splashEffect.activity.SplashEffectBaseActivity.2
            @Override // java.lang.Runnable
            public void run() {
                a.a();
            }
        }, 20L);
    }

    protected void onPause() {
        super.onPause();
        this.a = true;
        if (this.b != null) {
            try {
                this.b.c();
            } catch (Exception e) {
                com.ss.union.game.sdk.core.splashEffect.a.a.b("视频暂停失败" + Log.getStackTraceString(e));
            }
        }
    }

    protected void onResume() {
        super.onResume();
        if (this.a) {
            finish();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private boolean c() {
        try {
            String string = getPackageManager().getActivityInfo(getComponentName(), 128).metaData.getString("LG_GAME_ACTIVITY");
            if (TextUtils.isEmpty(string)) {
                return false;
            }
            Intent intent = new Intent();
            if (getIntent() != null) {
                intent.setData(getIntent().getData());
                intent.setAction(getIntent().getAction());
                intent.setClipData(getIntent().getClipData());
            }
            intent.setComponent(new ComponentName((Context) this, string));
            startActivity(intent);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        if (keyEvent.getKeyCode() == 4 && keyEvent.getAction() == 0) {
            return true;
        }
        return super.dispatchKeyEvent(keyEvent);
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i == 4) {
            return true;
        }
        return super.onKeyDown(i, keyEvent);
    }
}
