package com.kwad.sdk.d.kwai;

import android.app.Dialog;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory;
import com.kwad.sdk.R;
import com.kwad.sdk.api.KsAdVideoPlayConfig;
import com.kwad.sdk.api.KsInterstitialAd;
import com.kwad.sdk.core.download.a.a;
import com.kwad.sdk.core.imageloader.KSImageLoader;
import com.kwad.sdk.core.imageloader.core.assist.FailReason;
import com.kwad.sdk.core.imageloader.core.decode.DecodedResult;
import com.kwad.sdk.core.imageloader.core.listener.ImageLoadingListener;
import com.kwad.sdk.core.imageloader.utils.BlurUtils;
import com.kwad.sdk.core.response.model.AdInfo;
import com.kwad.sdk.core.response.model.AdTemplate;
import com.kwad.sdk.core.video.videoview.a;
import com.kwad.sdk.utils.af;
import com.kwad.sdk.utils.at;
import com.kwad.sdk.utils.bb;
import com.kwad.sdk.utils.m;
import com.kwad.sdk.utils.z;
import java.io.InputStream;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class c extends com.kwad.sdk.core.e.b {
    public AdTemplate a;
    public KsInterstitialAd.AdInteractionListener b;
    public Dialog c;
    public com.kwad.sdk.core.download.a.b d;
    public com.kwad.sdk.d.a.b e;
    public boolean f;

    @NonNull
    public KsAdVideoPlayConfig g;
    public com.kwad.sdk.core.video.videoview.b i;
    private a k;
    private b l;
    private Handler m = new Handler(Looper.getMainLooper());
    public volatile boolean j = false;
    public List<a.b> h = new CopyOnWriteArrayList();

    interface a {
        void a();
    }

    interface b {
        void a();
    }

    private boolean a(View view) {
        return view.getId() == R.id.ksad_interstitial_play_end;
    }

    @Override // com.kwad.sdk.core.e.b
    public void a() {
        this.m.removeCallbacksAndMessages(null);
        this.h.clear();
    }

    public void a(final Context context, AdInfo adInfo, AdTemplate adTemplate, @Nullable final View view) {
        if (view == null) {
            return;
        }
        String strA = com.kwad.sdk.core.response.a.a.aa(adInfo).a();
        if (at.a(strA)) {
            return;
        }
        KSImageLoader.loadImage(strA, adTemplate, KSImageLoader.IMGOPTION_NORMAL, new ImageLoadingListener() { // from class: com.kwad.sdk.d.kwai.c.3
            @Override // com.kwad.sdk.core.imageloader.core.listener.ImageLoadingListener
            public boolean onDecode(String str, InputStream inputStream, DecodedResult decodedResult) {
                return false;
            }

            @Override // com.kwad.sdk.core.imageloader.core.listener.ImageLoadingListener
            public void onLoadingCancelled(String str, View view2) {
            }

            @Override // com.kwad.sdk.core.imageloader.core.listener.ImageLoadingListener
            public void onLoadingComplete(String str, View view2, DecodedResult decodedResult) throws Resources.NotFoundException {
                Bitmap bitmap = decodedResult.mBitmap;
                com.kwad.sdk.core.d.a.a("InterstitialCallerContext", "onLoadingComplete before blur");
                Bitmap bitmapStackBlur = BlurUtils.stackBlur(bitmap, 50, false);
                com.kwad.sdk.core.d.a.a("InterstitialCallerContext", "onLoadingComplete after blur");
                float dimension = context.getResources().getDimension(R.dimen.ksad_interstitial_icon_radius);
                Drawable drawableCreate = RoundedBitmapDrawableFactory.create(context.getResources(), bitmapStackBlur);
                drawableCreate.setCornerRadius(dimension);
                view.setBackground(drawableCreate);
            }

            @Override // com.kwad.sdk.core.imageloader.core.listener.ImageLoadingListener
            public void onLoadingFailed(String str, View view2, FailReason failReason) {
            }

            @Override // com.kwad.sdk.core.imageloader.core.listener.ImageLoadingListener
            public void onLoadingStarted(String str, View view2) {
            }
        });
    }

    public void a(Context context, AdTemplate adTemplate) {
        if (this.j) {
            return;
        }
        com.kwad.sdk.core.page.a.a(context, adTemplate);
        this.j = true;
    }

    void a(View view, boolean z, int i, final boolean z2) {
        AdInfo adInfoJ = com.kwad.sdk.core.response.a.d.j(this.a);
        if (!z && com.kwad.sdk.core.response.a.a.P(adInfoJ) && this.i != null && this.i.d() && !a(view)) {
            m.b(this.a);
            this.i.setKsPlayLogParam(com.kwad.sdk.contentalliance.detail.video.b.a(this.a));
            this.i.a();
            return;
        }
        if ((com.kwad.sdk.core.config.b.ak() || z) && com.kwad.sdk.core.download.a.a.a(new a.C0035a(bb.a(view)).a(this.a).a(this.d).a(z).a(i).a(new a.b() { // from class: com.kwad.sdk.d.kwai.c.1
            @Override // com.kwad.sdk.core.download.a.a.b
            public void a() {
                c.this.a(z2);
            }
        })) == 0 && this.c != null && com.kwad.sdk.core.config.b.aC()) {
            this.m.postDelayed(new Runnable() { // from class: com.kwad.sdk.d.kwai.c.2
                @Override // java.lang.Runnable
                public void run() {
                    c.this.c.dismiss();
                }
            }, 500L);
        }
    }

    public void a(a.b bVar) {
        if (this.h.contains(bVar)) {
            return;
        }
        this.h.add(bVar);
    }

    public void a(a aVar) {
        this.k = aVar;
    }

    public void a(b bVar) {
        this.l = bVar;
    }

    public void a(boolean z) {
        if (z) {
            com.kwad.sdk.core.report.a.a(this.a, (z.a) null);
        } else {
            com.kwad.sdk.core.report.a.a(this.a, 153, (z.a) null);
        }
        if (this.b != null) {
            this.b.onAdClicked();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0025  */
    /* JADX WARN: Removed duplicated region for block: B:11:0x002a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void a(boolean r12, @androidx.annotation.Nullable com.kwad.sdk.core.video.videoview.b r13) {
        /*
            r11 = this;
            r0 = -1
            if (r13 == 0) goto L1f
            long r1 = r13.getDuration()
            long r3 = r13.getCurrentPosition()
            r5 = 0
            int r13 = (r1 > r5 ? 1 : (r1 == r5 ? 0 : -1))
            if (r13 == 0) goto L21
            float r13 = (float) r3
            float r0 = (float) r1
            float r13 = r13 / r0
            r0 = 1120403456(0x42c80000, float:100.0)
            float r13 = r13 * r0
            int r0 = java.lang.Math.round(r13)
            r9 = r0
            r7 = r3
            goto L23
        L1f:
            r3 = -1
        L21:
            r7 = r3
            r9 = -1
        L23:
            if (r12 == 0) goto L2a
            r12 = 14
            r6 = 14
            goto L2c
        L2a:
            r12 = 1
            r6 = 1
        L2c:
            com.kwad.sdk.core.response.model.AdTemplate r5 = r11.a
            r10 = 0
            com.kwad.sdk.core.report.a.a(r5, r6, r7, r9, r10)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.kwad.sdk.d.kwai.c.a(boolean, com.kwad.sdk.core.video.videoview.b):void");
    }

    public boolean a(Context context) {
        if (this.a != null && context != null) {
            return com.kwad.sdk.core.response.a.a.an(com.kwad.sdk.core.response.a.d.j(this.a));
        }
        com.kwad.sdk.core.d.a.d("InterstitialCallerContext", "isPlayable illegal params: " + this.a + ", context: " + context);
        return false;
    }

    public boolean a(Context context, AdInfo adInfo) {
        return com.kwad.sdk.core.response.a.a.K(adInfo) && !af.e(context);
    }

    public void b() {
        if (this.k != null) {
            this.k.a();
        }
    }

    public void b(a.b bVar) {
        this.h.remove(bVar);
    }

    public void c() {
        if (this.l != null) {
            this.l.a();
        }
    }

    public boolean d() {
        boolean z = this.e == null || this.e.getParent() == null;
        com.kwad.sdk.core.d.a.a("InterstitialCallerContext", "isH5Interstitial :" + z);
        return z;
    }
}
