package com.mbridge.msdk.video.module;

import android.content.Context;
import android.content.res.Configuration;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.mbridge.msdk.video.js.factory.b;
import com.mbridge.msdk.video.module.a.a.d;
import com.mbridge.msdk.widget.MBDownloadProgressBar;
import com.mbridge.msdk.widget.a;
import java.lang.reflect.InvocationTargetException;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class MBridgeClickCTAView extends MBridgeBaseView {
    private ViewGroup i;
    private ImageView j;
    private TextView k;
    private MBDownloadProgressBar l;
    private String m;
    private float n;
    private float o;
    private int p;

    public void setUnitId(String str) {
        this.m = str;
    }

    public MBridgeClickCTAView(Context context) {
        super(context);
    }

    public MBridgeClickCTAView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override // com.mbridge.msdk.video.module.MBridgeBaseView
    public void init(Context context) {
        int iFindLayout = findLayout("mbridge_reward_clickable_cta");
        if (iFindLayout >= 0) {
            this.c.inflate(iFindLayout, this);
            this.i = (ViewGroup) findViewById(findID("mbridge_viewgroup_ctaroot"));
            this.j = (ImageView) findViewById(findID("mbridge_iv_appicon"));
            this.k = (TextView) findViewById(findID("mbridge_tv_desc"));
            this.l = (MBDownloadProgressBar) findViewById(findID("mbridge_tv_install"));
            this.f = isNotNULL(this.i, this.j, this.k, this.l);
            c();
            setWrapContent();
        }
    }

    @Override // com.mbridge.msdk.video.module.MBridgeBaseView
    protected final void c() {
        super.c();
        if (this.f) {
            this.l.setOnClickListener(new a() { // from class: com.mbridge.msdk.video.module.MBridgeClickCTAView.1
                @Override // com.mbridge.msdk.widget.a
                protected final void a(View view) throws JSONException {
                    JSONObject jSONObject;
                    JSONException e;
                    try {
                        jSONObject = new JSONObject();
                        try {
                            jSONObject.put(com.mbridge.msdk.foundation.same.a.o, MBridgeClickCTAView.this.a(0));
                        } catch (JSONException e2) {
                            e = e2;
                            e.printStackTrace();
                            MBridgeClickCTAView.this.e.a(105, jSONObject);
                        }
                    } catch (JSONException e3) {
                        jSONObject = null;
                        e = e3;
                    }
                    MBridgeClickCTAView.this.e.a(105, jSONObject);
                }
            });
        }
    }

    public void preLoadData(b bVar) throws IllegalAccessException, ClassNotFoundException, IllegalArgumentException, InvocationTargetException {
        if (!this.f || this.b == null) {
            return;
        }
        this.l.setText(this.b.getAdCall());
        this.l.setUniqueKey(this.b.getAkdlui());
        this.l.setLinkType(this.b.getLinkType());
        if (this.b.getRewardTemplateMode() != null) {
            this.l.setCtaldtypeUrl(this.b.getRewardTemplateMode().d());
        }
        if (!TextUtils.isEmpty(this.b.getIconUrl())) {
            this.k.setText(this.b.getAppName());
            com.mbridge.msdk.foundation.same.c.b.a(this.a.getApplicationContext()).a(this.b.getIconUrl(), new d(this.j, this.b, this.m) { // from class: com.mbridge.msdk.video.module.MBridgeClickCTAView.2
                @Override // com.mbridge.msdk.video.module.a.a.d, com.mbridge.msdk.foundation.same.c.c
                public final void onFailedLoad(String str, String str2) {
                    super.onFailedLoad(str, str2);
                    MBridgeClickCTAView.this.a();
                }
            });
            return;
        }
        a();
    }

    @Override // com.mbridge.msdk.video.module.MBridgeBaseView
    public void onSelfConfigurationChanged(Configuration configuration) {
        super.onSelfConfigurationChanged(configuration);
        this.p = configuration.orientation;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a() {
        this.i.setBackgroundColor(0);
        this.j.setVisibility(8);
        this.k.setVisibility(8);
    }

    @Override // com.mbridge.msdk.video.module.MBridgeBaseView, android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        this.n = motionEvent.getRawX();
        this.o = motionEvent.getRawY();
        return super.onInterceptTouchEvent(motionEvent);
    }
}
