package com.ss.union.game.sdk.account.f;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.ss.union.game.sdk.common.util.ResourceUtils;
import com.ss.union.game.sdk.common.util.UIUtils;
import com.ss.union.game.sdk.core.base.config.ConfigManager;
import com.ss.union.game.sdk.core.base.event.PageStater;
import pl.droidsonroids.gif.GifDrawable;
import pl.droidsonroids.gif.GifImageView;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class c implements Handler.Callback {
    private static final int a = 1;
    private WindowManager.LayoutParams b;
    private WindowManager c;
    private View d;
    private Handler e;

    private c(Activity activity) {
        try {
            PageStater.V1.onEvent(com.ss.union.game.sdk.account.a.a.ag, com.ss.union.game.sdk.account.a.a.ah);
            this.c = activity.getWindowManager();
            this.d = LayoutInflater.from(activity).inflate(ResourceUtils.getLayoutIdByName("lg_splash_toast"), (ViewGroup) null);
            LinearLayout linearLayout = (LinearLayout) this.d.findViewById(ResourceUtils.getIdByName("lg_ll_toast"));
            GifImageView gifImageViewFindViewById = this.d.findViewById(ResourceUtils.getIdByName("gif_view"));
            ImageView imageView = (ImageView) this.d.findViewById(ResourceUtils.getIdByName("common_logo"));
            TextView textView = (TextView) this.d.findViewById(ResourceUtils.getIdByName("welcome_slogan"));
            if (ConfigManager.AppConfig.appIsOhayooProduct()) {
                gifImageViewFindViewById.setVisibility(0);
                imageView.setVisibility(8);
                gifImageViewFindViewById.setImageDrawable(new GifDrawable(activity.getResources(), ResourceUtils.getDrawableIdByName("lg_splash_logo")));
                textView.setText("欢迎来到Ohayoo");
            } else {
                gifImageViewFindViewById.setVisibility(8);
                imageView.setVisibility(0);
                textView.setText("欢迎进入游戏世界");
            }
            linearLayout.setLayoutParams(new RelativeLayout.LayoutParams(UIUtils.dip2Px(294.0f), UIUtils.dip2Px(48.0f)));
            b(activity);
            this.e = new Handler(this);
            this.c.addView(this.d, this.b);
            this.e.sendEmptyMessageDelayed(1, 3000L);
        } catch (Exception e) {
            com.ss.union.game.sdk.c.a.a("splash occur problem--" + Log.getStackTraceString(e));
            a();
        }
    }

    public static void a(Activity activity) {
        new c(activity);
    }

    private void b(Activity activity) {
        this.b = new WindowManager.LayoutParams();
        this.b.height = -2;
        this.b.width = -2;
        this.b.format = -3;
        this.b.windowAnimations = ResourceUtils.getStyleIdByName("LGSplashToast_Animation");
        this.b.flags = 152;
        this.b.gravity = 49;
        this.b.y = UIUtils.dip2Px(48.0f);
    }

    private void a() {
        this.c = null;
        this.d = null;
        this.b = null;
    }

    @Override // android.os.Handler.Callback
    public boolean handleMessage(Message message) {
        if (message.what == 1) {
            try {
                if (this.c != null && this.d != null) {
                    this.c.removeViewImmediate(this.d);
                    a();
                } else {
                    com.ss.union.game.sdk.c.a.a("mWdm is null or mToastView is null");
                }
            } catch (Exception e) {
                com.ss.union.game.sdk.c.a.a("remove splash toast exception--" + Log.getStackTraceString(e));
                a();
            }
        }
        return true;
    }
}
