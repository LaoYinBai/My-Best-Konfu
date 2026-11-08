package com.mbridge.msdk.video.module;

import android.R;
import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Base64;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.RelativeLayout;
import com.mbridge.msdk.foundation.controller.a;
import com.mbridge.msdk.foundation.download.download.H5DownLoadManager;
import com.mbridge.msdk.foundation.entity.CampaignEx;
import com.mbridge.msdk.foundation.tools.s;
import com.mbridge.msdk.foundation.tools.w;
import com.mbridge.msdk.mbjscommon.windvane.h;
import com.mbridge.msdk.video.js.factory.b;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class MBridgeClickMiniCardView extends MBridgeH5EndCardView {
    private boolean t;

    public MBridgeClickMiniCardView(Context context) {
        super(context);
        this.t = false;
    }

    public MBridgeClickMiniCardView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.t = false;
    }

    @Override // com.mbridge.msdk.video.module.MBridgeH5EndCardView, com.mbridge.msdk.video.module.MBridgeBaseView
    public void onSelfConfigurationChanged(Configuration configuration) throws JSONException {
        if (this.f) {
            a(this.i);
        }
        super.onSelfConfigurationChanged(configuration);
    }

    @Override // com.mbridge.msdk.video.module.MBridgeH5EndCardView
    protected final RelativeLayout.LayoutParams b() {
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams.addRule(13, -1);
        return layoutParams;
    }

    @Override // com.mbridge.msdk.video.module.MBridgeH5EndCardView
    protected final void e() {
        super.e();
        if (this.f) {
            setBackgroundResource(findColor("mbridge_reward_minicard_bg"));
            a(this.i);
            setClickable(true);
        }
    }

    @Override // com.mbridge.msdk.video.module.MBridgeH5EndCardView
    protected final String a() {
        if (this.b == null) {
            return null;
        }
        CampaignEx.c rewardTemplateMode = this.b.getRewardTemplateMode();
        String strC = rewardTemplateMode != null ? rewardTemplateMode.c() : null;
        if (TextUtils.isEmpty(strC) || !strC.contains(".zip")) {
            return strC;
        }
        String h5ResAddress = H5DownLoadManager.getInstance().getH5ResAddress(strC);
        return !TextUtils.isEmpty(h5ResAddress) ? h5ResAddress : strC;
    }

    @Override // com.mbridge.msdk.video.module.MBridgeH5EndCardView
    public void preLoadData(b bVar) {
        super.preLoadData(bVar);
        setCloseVisible(0);
    }

    @Override // com.mbridge.msdk.video.module.MBridgeH5EndCardView
    public void webviewshow() {
        if (this.l != null) {
            this.l.post(new Runnable() { // from class: com.mbridge.msdk.video.module.MBridgeClickMiniCardView.1
                @Override // java.lang.Runnable
                public final void run() {
                    try {
                        s.a(MBridgeBaseView.TAG, "webviewshow");
                        String string = "";
                        try {
                            int[] iArr = new int[2];
                            MBridgeClickMiniCardView.this.l.getLocationOnScreen(iArr);
                            s.d(MBridgeBaseView.TAG, "coordinate:" + iArr[0] + "--" + iArr[1]);
                            JSONObject jSONObject = new JSONObject();
                            jSONObject.put("startX", w.a(a.f().j(), (float) iArr[0]));
                            jSONObject.put("startY", w.a(a.f().j(), (float) iArr[1]));
                            string = jSONObject.toString();
                        } catch (Throwable th) {
                            s.b(MBridgeBaseView.TAG, th.getMessage(), th);
                        }
                        h.a().a((WebView) MBridgeClickMiniCardView.this.l, "webviewshow", Base64.encodeToString(string.toString().getBytes(), 2));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    private void a(View view) {
        int iH = w.h(this.a);
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.width = (int) ((iH * 0.7f) + 0.5f);
        layoutParams.height = (int) ((w.g(this.a) * 0.7f) + 0.5f);
        view.setLayoutParams(layoutParams);
    }

    public void setMBridgeClickMiniCardViewTransparent() {
        setBackgroundColor(0);
    }

    public void setMBridgeClickMiniCardViewClickable(boolean z) {
        setClickable(z);
    }

    public void setMiniCardLocation(int i, int i2, int i3, int i4) {
        this.t = true;
        resizeMiniCard(i3, i4);
    }

    public void resizeMiniCard(int i, int i2) {
        if (Build.VERSION.SDK_INT < 11) {
            return;
        }
        View viewFindViewById = ((Activity) this.a).getWindow().findViewById(R.id.content);
        int width = viewFindViewById.getWidth();
        int height = viewFindViewById.getHeight();
        if (i <= 0 || i2 <= 0 || i > width || i2 > height) {
            return;
        }
        ViewGroup.LayoutParams layoutParams = this.i.getLayoutParams();
        layoutParams.width = i;
        layoutParams.height = i2;
        this.i.setLayoutParams(layoutParams);
    }

    public void setRadius(int i) {
        if (i > 0) {
            GradientDrawable gradientDrawable = new GradientDrawable();
            gradientDrawable.setCornerRadius(w.b(getContext(), i));
            gradientDrawable.setColor(-1);
            if (Build.VERSION.SDK_INT >= 16) {
                this.l.setBackground(gradientDrawable);
            } else {
                this.l.setBackgroundDrawable(gradientDrawable);
            }
            if (Build.VERSION.SDK_INT >= 21) {
                this.l.setClipToOutline(true);
            }
        }
    }
}
