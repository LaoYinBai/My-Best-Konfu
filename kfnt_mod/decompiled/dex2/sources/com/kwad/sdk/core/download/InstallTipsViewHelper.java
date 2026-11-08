package com.kwad.sdk.core.download;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.core.view.ViewCompat;
import androidx.core.view.animation.PathInterpolatorCompat;
import com.kwad.sdk.KsAdSDKImpl;
import com.kwad.sdk.R;
import com.kwad.sdk.api.loader.Wrapper;
import com.kwad.sdk.core.imageloader.KSImageLoader;
import com.kwad.sdk.core.response.model.AdInfo;
import com.kwad.sdk.core.response.model.AdTemplate;
import com.kwad.sdk.utils.ag;
import java.io.Serializable;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class InstallTipsViewHelper implements View.OnClickListener {
    private AdInfo a;
    private AdTemplate b;
    private InstallTipsData c;
    private View d;
    private View e;
    private ImageView f;
    private TextView g;
    private Button h;
    private int i = -1;
    private long j = 10000;
    private Context k = Wrapper.wrapContextIfNeed(KsAdSDKImpl.get().getContext());

    static class InstallTipsData implements Serializable {
        public static final int ACTION_INSTALL = 1;
        public static final int ACTION_LAUNCH_APP = 2;
        private static int MAX_APP_NAME_LENGTH = 8;
        private static final long serialVersionUID = 5637398311569371631L;
        private int clickAction;
        private String displayContent;
        private String downloadId;
        private String iconUrl;

        private InstallTipsData(Context context, int i, AdTemplate adTemplate, int i2) {
            this.clickAction = 1;
            this.clickAction = i;
            this.iconUrl = com.kwad.sdk.core.response.a.d.o(adTemplate);
            this.downloadId = com.kwad.sdk.core.response.a.d.j(adTemplate).downloadId;
            String strT = com.kwad.sdk.core.response.a.a.t(com.kwad.sdk.core.response.a.d.j(adTemplate));
            if (strT.length() >= MAX_APP_NAME_LENGTH) {
                strT = strT.substring(0, MAX_APP_NAME_LENGTH - 1) + "...";
            }
            this.displayContent = context.getString(i2, strT);
        }

        public static InstallTipsData newInstallInstance(Context context, AdTemplate adTemplate) {
            return new InstallTipsData(context, 1, adTemplate, R.string.ksad_install_tips);
        }

        public static InstallTipsData newLaunchInstance(Context context, AdTemplate adTemplate) {
            return new InstallTipsData(context, 2, adTemplate, R.string.ksad_launch_tips);
        }

        public int getClickAction() {
            return this.clickAction;
        }

        public String getClickButtonText() {
            return this.clickAction == 2 ? "打开" : "安装";
        }

        public String getDisplayContent() {
            return this.displayContent;
        }

        public String getDownloadId() {
            return this.downloadId;
        }

        public String getIconUrl() {
            return this.iconUrl;
        }
    }

    public InstallTipsViewHelper(boolean z, AdInfo adInfo, AdTemplate adTemplate) {
        if (this.k == null) {
            return;
        }
        this.a = adInfo;
        this.b = adTemplate;
        this.c = z ? InstallTipsData.newInstallInstance(this.k, adTemplate) : InstallTipsData.newLaunchInstance(this.k, adTemplate);
        this.d = LayoutInflater.from(this.k).inflate(R.layout.ksad_install_tips, (ViewGroup) null, false);
        a(this.k, this.d);
        if (this.c != null) {
            a(this.k, this.c);
        }
    }

    @Nullable
    private Animator a(View view) {
        if (this.i < 0) {
            return null;
        }
        ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(view, "translationY", this.i, 0.0f);
        objectAnimatorOfFloat.setInterpolator(PathInterpolatorCompat.create(0.0f, 0.42f, 0.85f, 0.64f));
        objectAnimatorOfFloat.setDuration(260L);
        return objectAnimatorOfFloat;
    }

    private Animator a(View view, int i) {
        ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(view, "translationY", 0.0f, i);
        objectAnimatorOfFloat.setInterpolator(PathInterpolatorCompat.create(0.25f, 0.1f, 0.27f, 0.87f));
        objectAnimatorOfFloat.setDuration(300L);
        return objectAnimatorOfFloat;
    }

    private void a(Context context, View view) {
        String clickButtonText;
        ViewCompat.setElevation(view, context.getResources().getDimension(R.dimen.ksad_install_tips_card_elevation));
        this.e = view.findViewById(R.id.ksad_install_tips_close);
        this.f = (ImageView) view.findViewById(R.id.ksad_install_tips_icon);
        this.g = (TextView) view.findViewById(R.id.ksad_install_tips_content);
        this.h = (Button) view.findViewById(R.id.ksad_install_tips_install);
        if (this.c != null && (clickButtonText = this.c.getClickButtonText()) != null) {
            this.h.setText(clickButtonText);
        }
        this.e.setOnClickListener(this);
        this.h.setOnClickListener(this);
    }

    private void a(Context context, InstallTipsData installTipsData) {
        if (installTipsData.getIconUrl() != null) {
            KSImageLoader.loadAppIcon(this.f, com.kwad.sdk.core.response.a.a.au(this.a), this.b, 8);
        }
        this.g.setText(installTipsData.getDisplayContent());
    }

    private void d() {
        String str = this.a.downloadFilePath;
        Context context = KsAdSDKImpl.get().getContext();
        if (context != null && !TextUtils.isEmpty(str)) {
            com.kwad.sdk.core.report.a.h(this.b);
            KsAdSDKImpl.get().getProxyForAdInstall().installApp(context, str);
            return;
        }
        com.kwad.sdk.core.d.a.e("InstallTipsViewHelper", "openApp fail appContext:" + context + "--filePath:" + str);
    }

    public View a() {
        return this.d;
    }

    public void a(FrameLayout frameLayout) {
        View viewA = a();
        if (viewA == null || this.k == null || viewA.getParent() != null) {
            return;
        }
        int dimensionPixelSize = this.k.getResources().getDimensionPixelSize(R.dimen.ksad_install_tips_card_height);
        int dimensionPixelSize2 = this.k.getResources().getDimensionPixelSize(R.dimen.ksad_install_tips_card_margin);
        int i = dimensionPixelSize + dimensionPixelSize2;
        int i2 = -i;
        this.i = i + dimensionPixelSize2;
        ViewGroup.LayoutParams layoutParams = viewA.getLayoutParams();
        FrameLayout.LayoutParams layoutParams2 = layoutParams instanceof FrameLayout.LayoutParams ? (FrameLayout.LayoutParams) layoutParams : new FrameLayout.LayoutParams(-1, dimensionPixelSize);
        layoutParams2.gravity = 48;
        layoutParams2.leftMargin = dimensionPixelSize2;
        layoutParams2.rightMargin = dimensionPixelSize2;
        layoutParams2.topMargin = i2;
        frameLayout.addView(viewA, layoutParams2);
        a(viewA, this.i).start();
        viewA.postDelayed(new Runnable() { // from class: com.kwad.sdk.core.download.InstallTipsViewHelper.1
            @Override // java.lang.Runnable
            public void run() {
                InstallTipsViewHelper.this.b();
            }
        }, this.j);
    }

    public void b() {
        if (a() == null || a().getParent() == null) {
            return;
        }
        final View viewA = a();
        final ViewGroup viewGroup = (ViewGroup) viewA.getParent();
        final Animator animatorA = a(viewA);
        if (animatorA == null) {
            viewGroup.removeView(viewA);
        } else {
            animatorA.start();
            animatorA.addListener(new Animator.AnimatorListener() { // from class: com.kwad.sdk.core.download.InstallTipsViewHelper.2
                @Override // android.animation.Animator.AnimatorListener
                public void onAnimationCancel(Animator animator) {
                }

                @Override // android.animation.Animator.AnimatorListener
                public void onAnimationEnd(Animator animator) {
                    viewGroup.removeView(viewA);
                    animatorA.removeListener(this);
                    e.a().a(InstallTipsViewHelper.this);
                }

                @Override // android.animation.Animator.AnimatorListener
                public void onAnimationRepeat(Animator animator) {
                }

                @Override // android.animation.Animator.AnimatorListener
                public void onAnimationStart(Animator animator) {
                }
            });
        }
    }

    public void c() {
        this.d = null;
        this.e = null;
        this.f = null;
        this.h = null;
        this.i = -1;
        this.b = null;
        this.c = null;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        AdTemplate adTemplate;
        int i;
        int clickAction = this.c != null ? this.c.getClickAction() : 1;
        int id = view.getId();
        if (id == R.id.ksad_install_tips_install) {
            b();
            switch (clickAction) {
                case 1:
                    d();
                    com.kwad.sdk.core.report.a.e(this.b, 45);
                    break;
                case 2:
                    break;
                default:
                    return;
            }
            if (ag.c(this.k, com.kwad.sdk.core.response.a.a.v(this.a))) {
                com.kwad.sdk.core.report.a.e(this.b);
            }
            adTemplate = this.b;
            i = 47;
        } else {
            if (id != R.id.ksad_install_tips_close) {
                return;
            }
            b();
            switch (clickAction) {
                case 1:
                    com.kwad.sdk.core.report.a.e(this.b, 46);
                    break;
                case 2:
                    break;
                default:
                    return;
            }
            adTemplate = this.b;
            i = 48;
        }
        com.kwad.sdk.core.report.a.d(adTemplate, i);
    }
}
