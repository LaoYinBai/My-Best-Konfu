package com.ss.union.game.sdk.core.splashEffect.util;

import android.app.Activity;
import android.media.MediaPlayer;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.FrameLayout;
import com.ss.union.game.sdk.core.splashEffect.view.a.b;
import com.ss.union.game.sdk.core.splashEffect.view.a.c;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class a {
    public static void a(Activity activity) {
        FrameLayout frameLayoutA = a(activity.getWindow().getDecorView());
        if (frameLayoutA == null) {
            com.ss.union.game.sdk.core.splashEffect.a.a.b("获取activity的布局失败");
            com.ss.union.game.sdk.core.splashEffect.a.a();
        } else {
            final b bVarA = com.ss.union.game.sdk.core.splashEffect.a.a(frameLayoutA);
            bVarA.setMediaPlayerListener(new c() { // from class: com.ss.union.game.sdk.core.splashEffect.util.a.1
                @Override // com.ss.union.game.sdk.core.splashEffect.view.a.c, com.ss.union.game.sdk.core.splashEffect.view.a.a
                public boolean a(MediaPlayer mediaPlayer, int i, int i2) {
                    a.b(bVarA);
                    return super.a(mediaPlayer, i, i2);
                }

                @Override // com.ss.union.game.sdk.core.splashEffect.view.a.c, com.ss.union.game.sdk.core.splashEffect.view.a.a
                public void a(MediaPlayer mediaPlayer) {
                    a.b(bVarA);
                    super.a(mediaPlayer);
                }
            });
            TimeCollector.effect().end("设置完成");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void b(b bVar) {
        try {
            View view = bVar.getView();
            ViewParent parent = view.getParent();
            if (parent instanceof ViewGroup) {
                view.setVisibility(8);
                ((ViewGroup) parent).removeView(view);
            }
        } catch (Exception e) {
            e.printStackTrace();
            com.ss.union.game.sdk.core.splashEffect.a.a.b("视频结束页面异常: " + e.getMessage());
        }
        com.ss.union.game.sdk.core.splashEffect.a.a();
    }

    private static FrameLayout a(View view) {
        if (view instanceof FrameLayout) {
            return (FrameLayout) view;
        }
        if (!(view instanceof ViewGroup)) {
            return null;
        }
        ViewGroup viewGroup = (ViewGroup) view;
        for (int i = 0; i < viewGroup.getChildCount(); i++) {
            FrameLayout frameLayoutA = a(viewGroup.getChildAt(i));
            if (frameLayoutA != null) {
                return frameLayoutA;
            }
        }
        return null;
    }
}
