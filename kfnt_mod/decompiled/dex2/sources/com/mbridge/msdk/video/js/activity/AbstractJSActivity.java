package com.mbridge.msdk.video.js.activity;

import android.content.res.Configuration;
import com.mbridge.msdk.activity.MBBaseActivity;
import com.mbridge.msdk.foundation.tools.s;
import com.mbridge.msdk.video.js.b;
import com.mbridge.msdk.video.js.c;
import com.mbridge.msdk.video.js.e;
import com.mbridge.msdk.video.js.f;
import com.mbridge.msdk.video.js.factory.IJSFactory;
import com.mbridge.msdk.video.js.factory.a;
import com.mbridge.msdk.video.js.h;
import com.mbridge.msdk.video.js.i;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public abstract class AbstractJSActivity extends MBBaseActivity implements IJSFactory {
    protected static final String TAG = "AbstractJSActivity";
    protected IJSFactory jsFactory = new a();

    public boolean canBackPress() {
        return false;
    }

    public void registerJsFactory(IJSFactory iJSFactory) {
        this.jsFactory = iJSFactory;
    }

    @Override // com.mbridge.msdk.activity.MBBaseActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        if (getJSCommon().c()) {
            getActivityProxy().b();
        }
        getActivityProxy().a(0);
    }

    @Override // android.app.Activity
    public void onPause() {
        super.onPause();
        if (getJSCommon().c()) {
            getActivityProxy().a();
        }
        getActivityProxy().a(1);
    }

    @Override // com.mbridge.msdk.activity.MBBaseActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
    }

    @Override // android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        if (getJSCommon().c()) {
            getActivityProxy().a(configuration);
        }
    }

    @Override // android.app.Activity
    public void onBackPressed() {
        if (getJSCommon().c()) {
            if (getJSContainerModule() == null || !getJSContainerModule().miniCardShowing()) {
                getActivityProxy().g();
                return;
            }
            return;
        }
        if (canBackPress()) {
            super.onBackPressed();
        } else {
            s.a(TAG, "onBackPressed can't excute");
        }
    }

    @Override // com.mbridge.msdk.video.js.factory.IJSFactory
    public com.mbridge.msdk.video.js.a getActivityProxy() {
        return this.jsFactory.getActivityProxy();
    }

    @Override // com.mbridge.msdk.video.js.factory.IJSFactory
    public c getJSCommon() {
        return this.jsFactory.getJSCommon();
    }

    @Override // com.mbridge.msdk.video.js.factory.IJSFactory
    public i getJSVideoModule() {
        return this.jsFactory.getJSVideoModule();
    }

    @Override // com.mbridge.msdk.video.js.factory.IJSFactory
    public f getJSNotifyProxy() {
        return this.jsFactory.getJSNotifyProxy();
    }

    @Override // com.mbridge.msdk.video.js.factory.IJSFactory
    public e getJSContainerModule() {
        return this.jsFactory.getJSContainerModule();
    }

    @Override // com.mbridge.msdk.video.js.factory.IJSFactory
    public h getIJSRewardVideoV1() {
        return this.jsFactory.getIJSRewardVideoV1();
    }

    @Override // com.mbridge.msdk.video.js.factory.IJSFactory
    public b getJSBTModule() {
        return this.jsFactory.getJSBTModule();
    }
}
