package com.kwad.sdk.splashscreen;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.kwad.sdk.R;
import com.kwad.sdk.api.KsScene;
import com.kwad.sdk.api.KsSplashScreenAd;
import com.kwad.sdk.api.KsVideoPlayConfig;
import com.kwad.sdk.core.download.a.a;
import com.kwad.sdk.core.report.p;
import com.kwad.sdk.core.response.model.AdInfo;
import com.kwad.sdk.core.response.model.AdTemplate;
import com.kwad.sdk.core.view.AdBaseFrameLayout;
import com.kwad.sdk.internal.api.SceneImpl;
import com.kwad.sdk.plugin.DevelopMangerPlugin;
import com.kwad.sdk.utils.at;
import com.kwad.sdk.utils.bd;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class g extends com.kwad.sdk.core.e.b {
    public KsSplashScreenAd.SplashScreenAdInteractionListener a;
    public boolean b;

    @NonNull
    public KsVideoPlayConfig c;

    @NonNull
    public AdTemplate d;

    @NonNull
    public AdBaseFrameLayout e;

    @Nullable
    public com.kwad.sdk.splashscreen.b.a f;
    public com.kwad.sdk.core.download.a.b g;

    @NonNull
    public KsScene h;
    public com.kwad.sdk.core.j.c i;
    private String j;
    private boolean k;

    g() {
    }

    public static boolean a(Context context, AdInfo adInfo) {
        com.kwad.sdk.splashscreen.kwai.b bVarB = com.kwad.sdk.splashscreen.kwai.a.b(context);
        boolean z = bVarB == null || !bVarB.a();
        boolean zB = com.kwad.sdk.core.response.a.b.b(adInfo);
        com.kwad.sdk.core.d.a.a("SplashScreenCallerContext", "isNeedShowSplashShake: " + z + " confEnableShake: " + zB);
        return z && zB;
    }

    private boolean e() {
        DevelopMangerPlugin.DevelopValue developValueA;
        DevelopMangerPlugin developMangerPlugin = (DevelopMangerPlugin) com.kwad.sdk.plugin.f.a(DevelopMangerPlugin.class);
        if (developMangerPlugin == null || (developValueA = developMangerPlugin.a("KEY_CLOSE_SPLASH_VPLUS")) == null) {
            return false;
        }
        return ((Boolean) developValueA.getValue()).booleanValue();
    }

    @Override // com.kwad.sdk.core.e.b
    public void a() {
        if (this.f != null) {
            if (this.j != null) {
                this.f.i();
            } else {
                this.f.h();
            }
        }
    }

    public void a(Context context, int i, int i2) {
        a(context, i, i2, 0.0d);
    }

    public void a(Context context, final int i, int i2, final double d) {
        final AdInfo adInfoJ = com.kwad.sdk.core.response.a.d.j(this.d);
        com.kwad.sdk.core.download.a.a.a(new a.C0035a(context).a(this.d).a(this.g).a(i2).a(false).a(new a.b() { // from class: com.kwad.sdk.splashscreen.g.2
            @Override // com.kwad.sdk.core.download.a.a.b
            public void a() throws JSONException {
                String strD;
                if (g.this.a != null) {
                    g.this.a.onAdClicked();
                }
                if (!com.kwad.sdk.core.response.a.a.B(adInfoJ) && (strD = g.this.d()) != null) {
                    g.this.b = true;
                    g.this.d.mMiniWindowId = strD;
                }
                JSONObject jSONObject = new JSONObject();
                try {
                    if (g.this.f != null) {
                        jSONObject.put("duration", g.this.f.c());
                    }
                } catch (JSONException e) {
                    com.kwad.sdk.core.d.a.a(e);
                }
                p.a aVar = new p.a();
                aVar.b = i;
                aVar.B = d;
                com.kwad.sdk.core.report.a.a(g.this.d, jSONObject, aVar, g.this.e.getTouchCoords(), (String) null);
            }
        }));
    }

    public boolean b() {
        if (e()) {
            return false;
        }
        AdInfo adInfoJ = com.kwad.sdk.core.response.a.d.j(this.d);
        return com.kwad.sdk.core.response.a.a.P(adInfoJ) && (this.h instanceof SceneImpl ? ((SceneImpl) this.h).needShowMiniWindow : false) && com.kwad.sdk.core.response.a.a.b(adInfoJ) > 5;
    }

    public String c() {
        AdInfo adInfoJ = com.kwad.sdk.core.response.a.d.j(this.d);
        boolean zB = b();
        String strA = com.kwad.sdk.core.response.a.a.a(adInfoJ);
        if (!zB || at.a(strA)) {
            return null;
        }
        return String.valueOf(strA.hashCode());
    }

    public String d() {
        if (this.k) {
            return this.j;
        }
        AdInfo adInfoJ = com.kwad.sdk.core.response.a.d.j(this.d);
        boolean zB = b();
        String strA = com.kwad.sdk.core.response.a.a.a(adInfoJ);
        if (!zB || at.a(strA) || this.f == null) {
            return null;
        }
        String strC = c();
        final ImageView imageView = (ImageView) this.e.findViewById(R.id.ksad_splash_foreground);
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(this.e.getWidth(), this.e.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmapCreateBitmap);
        Bitmap bitmap = this.f.a().getBitmap();
        imageView.setVisibility(0);
        imageView.setImageDrawable(new BitmapDrawable(imageView.getResources(), bitmap));
        this.e.invalidate();
        this.e.draw(canvas);
        this.f.a = bitmapCreateBitmap;
        SplashPlayModuleCache.a().a(strC, this.f);
        this.j = strC;
        this.k = true;
        imageView.post(new bd(new Runnable() { // from class: com.kwad.sdk.splashscreen.g.1
            @Override // java.lang.Runnable
            public void run() {
                imageView.setVisibility(4);
            }
        }));
        return strC;
    }
}
