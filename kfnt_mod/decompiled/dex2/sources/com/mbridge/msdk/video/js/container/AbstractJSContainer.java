package com.mbridge.msdk.video.js.container;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Build;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Base64;
import android.widget.FrameLayout;
import com.heytap.mcssdk.mode.CommandMessage;
import com.mbridge.msdk.foundation.entity.CampaignEx;
import com.mbridge.msdk.foundation.tools.s;
import com.mbridge.msdk.mbjscommon.windvane.WindVaneWebView;
import com.mbridge.msdk.video.js.a.j;
import com.mbridge.msdk.video.js.b;
import com.mbridge.msdk.video.js.e;
import com.mbridge.msdk.video.js.f;
import com.mbridge.msdk.video.js.factory.IJSFactory;
import com.mbridge.msdk.video.js.factory.a;
import com.mbridge.msdk.video.js.h;
import com.mbridge.msdk.video.js.i;
import com.mbridge.msdk.videocommon.a;
import com.mbridge.msdk.videocommon.b.d;
import com.mbridge.msdk.videocommon.d.c;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public abstract class AbstractJSContainer extends FrameLayout implements IJSFactory {
    private int a;
    private int b;
    protected Activity j;
    protected String k;
    protected String l;
    protected c m;
    protected String n;
    protected d o;
    protected String p;
    protected int q;
    protected boolean r;
    protected boolean s;
    protected int t;
    protected int u;
    protected int v;
    protected boolean w;
    protected IJSFactory x;

    public AbstractJSContainer(Context context) {
        super(context);
        this.a = 0;
        this.b = 1;
        this.q = 2;
        this.r = false;
        this.s = false;
        this.w = false;
        this.x = new a();
    }

    public AbstractJSContainer(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.a = 0;
        this.b = 1;
        this.q = 2;
        this.r = false;
        this.s = false;
        this.w = false;
        this.x = new a();
    }

    public void registerJsFactory(IJSFactory iJSFactory) {
        this.x = iJSFactory;
    }

    public void onResume() {
        if (getJSCommon().c()) {
            getActivityProxy().b();
        }
        getActivityProxy().a(0);
    }

    public void onPause() {
        if (getJSCommon().c()) {
            getActivityProxy().a();
        }
        getActivityProxy().a(1);
    }

    public void onStop() {
        if (getJSCommon().c()) {
            getActivityProxy().d();
        }
        getActivityProxy().a(3);
    }

    public void onRestart() {
        if (getJSCommon().c()) {
            getActivityProxy().f();
        }
        getActivityProxy().a(4);
    }

    public void onStart() {
        if (getJSCommon().c()) {
            getActivityProxy().e();
        }
        getActivityProxy().a(2);
    }

    public void onDestroy() {
        if (getJSCommon().c()) {
            getActivityProxy().c();
        }
    }

    @Override // android.view.View
    public void onConfigurationChanged(Configuration configuration) {
        if (getJSCommon().c()) {
            getActivityProxy().a(configuration);
        }
    }

    @Override // com.mbridge.msdk.video.js.factory.IJSFactory
    public com.mbridge.msdk.video.js.a getActivityProxy() {
        return this.x.getActivityProxy();
    }

    @Override // com.mbridge.msdk.video.js.factory.IJSFactory
    public com.mbridge.msdk.video.js.c getJSCommon() {
        return this.x.getJSCommon();
    }

    @Override // com.mbridge.msdk.video.js.factory.IJSFactory
    public i getJSVideoModule() {
        return this.x.getJSVideoModule();
    }

    @Override // com.mbridge.msdk.video.js.factory.IJSFactory
    public f getJSNotifyProxy() {
        return this.x.getJSNotifyProxy();
    }

    @Override // com.mbridge.msdk.video.js.factory.IJSFactory
    public e getJSContainerModule() {
        return this.x.getJSContainerModule();
    }

    @Override // com.mbridge.msdk.video.js.factory.IJSFactory
    public h getIJSRewardVideoV1() {
        return this.x.getIJSRewardVideoV1();
    }

    @Override // com.mbridge.msdk.video.js.factory.IJSFactory
    public b getJSBTModule() {
        return this.x.getJSBTModule();
    }

    protected final void a(Object obj) {
        com.mbridge.msdk.mbjscommon.windvane.h.a().a(obj, a(this.a));
    }

    protected final void a(Object obj, String str) {
        com.mbridge.msdk.mbjscommon.windvane.h.a().b(obj, Base64.encodeToString(str.getBytes(), 2));
    }

    private String a(int i) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(CommandMessage.CODE, i);
            String string = jSONObject.toString();
            return !TextUtils.isEmpty(string) ? Base64.encodeToString(string.getBytes(), 2) : "";
        } catch (Throwable unused) {
            s.d("AbstractJSContainer", "code to string is error");
            return "";
        }
    }

    public void setRewardId(String str) {
        this.p = str;
    }

    public void setUserId(String str) {
        this.n = str;
    }

    public void setUnitId(String str) {
        this.k = str;
    }

    public String getUnitId() {
        return this.k;
    }

    public String getPlacementId() {
        return this.l;
    }

    public void setPlacementId(String str) {
        this.l = str;
    }

    public void setActivity(Activity activity) {
        this.j = activity;
    }

    public void setReward(d dVar) {
        this.o = dVar;
    }

    public void setMute(int i) {
        this.q = i;
    }

    public void setIV(boolean z) {
        this.r = z;
    }

    public void setBidCampaign(boolean z) {
        this.s = z;
    }

    public void setIVRewardEnable(int i, int i2, int i3) {
        this.t = i;
        this.u = i2;
        this.v = i3;
    }

    public void setBigOffer(boolean z) {
        this.w = z;
    }

    public void setRewardUnitSetting(c cVar) {
        this.m = cVar;
    }

    protected void a(String str) {
        s.d("AbstractJSContainer", str);
        if (this.j != null) {
            this.j.finish();
        }
    }

    protected final j a(CampaignEx campaignEx) {
        if (campaignEx == null) {
            return null;
        }
        a.C0146a c0146aA = com.mbridge.msdk.videocommon.a.a(this.r ? 287 : 94, campaignEx);
        if (c0146aA != null && c0146aA.c()) {
            WindVaneWebView windVaneWebViewA = c0146aA.a();
            if (windVaneWebViewA.getObject() instanceof j) {
                s.d("AbstractJSContainer", "JSCommon 进来");
                return (j) windVaneWebViewA.getObject();
            }
        }
        return null;
    }

    protected final int b(CampaignEx campaignEx) {
        j jVarA = a(campaignEx);
        if (jVarA != null) {
            return jVarA.m();
        }
        return 0;
    }

    protected final void a(c cVar, CampaignEx campaignEx) {
        CampaignEx.c rewardTemplateMode;
        if (b(campaignEx) == 1) {
            return;
        }
        boolean zB = false;
        if (campaignEx != null && (rewardTemplateMode = campaignEx.getRewardTemplateMode()) != null) {
            zB = b(rewardTemplateMode.b());
        }
        if (zB || cVar == null) {
            return;
        }
        b(this.m.e());
    }

    private boolean b(int i) {
        boolean z = false;
        try {
            switch (i) {
                case 1:
                    if (Build.VERSION.SDK_INT >= 18) {
                        this.j.setRequestedOrientation(12);
                    } else {
                        this.j.setRequestedOrientation(1);
                    }
                    z = true;
                    return true;
                case 2:
                    if (Build.VERSION.SDK_INT >= 18) {
                        this.j.setRequestedOrientation(11);
                    } else {
                        this.j.setRequestedOrientation(0);
                    }
                    z = true;
                    return true;
                default:
                    return false;
            }
        } catch (Throwable th) {
            s.b("AbstractJSContainer", th.getMessage(), th);
            return z;
        }
    }

    protected final String b() {
        if (TextUtils.isEmpty(this.l) && this.m != null && !TextUtils.isEmpty(this.m.D())) {
            return this.m.D();
        }
        return this.l;
    }
}
