package com.sigmob.sdk.base.common;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import com.sigmob.logger.SigmobLog;
import com.sigmob.sdk.base.models.BaseAdUnit;
import com.sigmob.sdk.base.models.IntentActions;
import com.sigmob.sdk.common.Constants;
import com.sigmob.sdk.videoAd.BaseAdActivity;
import java.util.HashMap;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class AdActivity extends BaseAdActivity implements h {
    private g a;
    private String b;

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0044  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private com.sigmob.sdk.base.common.g a(com.sigmob.sdk.base.models.BaseAdUnit r9, android.os.Bundle r10) {
        /*
            r8 = this;
            android.content.Intent r0 = r8.getIntent()
            java.lang.String r1 = "ad_view_class_name"
            java.lang.String r0 = r0.getStringExtra(r1)
            int r1 = r0.hashCode()
            switch(r1) {
                case -1961669118: goto L3a;
                case -1552818374: goto L30;
                case -959624923: goto L26;
                case -934326481: goto L1c;
                case 104156535: goto L12;
                default: goto L11;
            }
        L11:
            goto L44
        L12:
            java.lang.String r1 = "mraid"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L44
            r0 = 2
            goto L45
        L1c:
            java.lang.String r1 = "reward"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L44
            r0 = 0
            goto L45
        L26:
            java.lang.String r1 = "DisLike"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L44
            r0 = 4
            goto L45
        L30:
            java.lang.String r1 = "LandPage"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L44
            r0 = 1
            goto L45
        L3a:
            java.lang.String r1 = "LandNative"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L44
            r0 = 3
            goto L45
        L44:
            r0 = -1
        L45:
            switch(r0) {
                case 0: goto L9e;
                case 1: goto L89;
                case 2: goto L74;
                case 3: goto L5f;
                case 4: goto L4a;
                default: goto L48;
            }
        L48:
            r0 = 0
            return r0
        L4a:
            com.sigmob.sdk.nativead.l r7 = new com.sigmob.sdk.nativead.l
            android.content.Intent r0 = r8.getIntent()
            android.os.Bundle r3 = r0.getExtras()
            java.lang.String r5 = r8.b
            r0 = r7
            r1 = r8
            r2 = r9
            r4 = r10
            r6 = r8
            r0.<init>(r1, r2, r3, r4, r5, r6)
            return r7
        L5f:
            com.sigmob.sdk.nativead.o r7 = new com.sigmob.sdk.nativead.o
            android.content.Intent r0 = r8.getIntent()
            android.os.Bundle r3 = r0.getExtras()
            java.lang.String r5 = r8.b
            r0 = r7
            r1 = r8
            r2 = r9
            r4 = r10
            r6 = r8
            r0.<init>(r1, r2, r3, r4, r5, r6)
            return r7
        L74:
            com.sigmob.sdk.mraid.i r7 = new com.sigmob.sdk.mraid.i
            android.content.Intent r0 = r8.getIntent()
            android.os.Bundle r3 = r0.getExtras()
            java.lang.String r5 = r8.b
            r0 = r7
            r1 = r8
            r2 = r9
            r4 = r10
            r6 = r8
            r0.<init>(r1, r2, r3, r4, r5, r6)
            return r7
        L89:
            com.sigmob.sdk.base.common.q r7 = new com.sigmob.sdk.base.common.q
            android.content.Intent r0 = r8.getIntent()
            android.os.Bundle r3 = r0.getExtras()
            java.lang.String r5 = r8.b
            r0 = r7
            r1 = r8
            r2 = r9
            r4 = r10
            r6 = r8
            r0.<init>(r1, r2, r3, r4, r5, r6)
            return r7
        L9e:
            com.sigmob.sdk.videoAd.h r7 = new com.sigmob.sdk.videoAd.h
            android.content.Intent r0 = r8.getIntent()
            android.os.Bundle r3 = r0.getExtras()
            java.lang.String r5 = r8.b
            r0 = r7
            r1 = r8
            r2 = r9
            r4 = r10
            r6 = r8
            r0.<init>(r1, r2, r3, r4, r5, r6)
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sigmob.sdk.base.common.AdActivity.a(com.sigmob.sdk.base.models.BaseAdUnit, android.os.Bundle):com.sigmob.sdk.base.common.g");
    }

    private static String a(Intent intent) {
        return intent.getStringExtra(Constants.BROADCAST_IDENTIFIER_KEY);
    }

    @Override // com.sigmob.sdk.base.common.h
    public void a() {
        finish();
        overridePendingTransition(0, 0);
    }

    @Override // com.sigmob.sdk.base.common.h
    public void a(int i) {
        setRequestedOrientation(i);
    }

    @Override // android.app.Activity
    protected void onActivityResult(int i, int i2, Intent intent) {
        if (this.a != null) {
            this.a.a(i, i2, intent);
        }
    }

    @Override // android.app.Activity, com.sigmob.sdk.base.common.h
    public void onBackPressed() {
        if (this.a == null || !this.a.g()) {
            return;
        }
        super.onBackPressed();
        this.a.f();
    }

    @Override // android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        if (this.a != null) {
            this.a.a(configuration);
        }
    }

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        SigmobLog.i("TaskId :" + getTaskId());
        this.b = a(getIntent());
        String stringExtra = getIntent().getStringExtra("adUnit_requestId_key");
        try {
            if (com.sigmob.sdk.common.a.n() != null && !TextUtils.isEmpty(stringExtra)) {
                BaseAdUnit baseAdUnitA = d.a(stringExtra);
                if (!this.b.equals("dislike_broadcastIdentifier") && baseAdUnitA == null) {
                    finish();
                    return;
                }
                this.a = a(baseAdUnitA, bundle);
                if (this.a != null) {
                    this.a.b();
                    return;
                }
                return;
            }
            finish();
        } catch (Throwable th) {
            SigmobLog.e("AdActivity onCreate Throwable:" + th.getMessage());
            HashMap map = new HashMap();
            map.put("error", th.getMessage());
            BaseBroadcastReceiver.a(this, this.b, map, IntentActions.ACTION_REWARDED_VIDEO_PLAYFAIL);
            finish();
        }
    }

    @Override // com.sigmob.sdk.videoAd.BaseAdActivity, android.app.Activity
    protected void onDestroy() {
        SigmobLog.d("RewardVideoAdPlayerActivity onDestroy() called");
        if (this.a != null) {
            this.a.e();
        }
        this.a = null;
        super.onDestroy();
    }

    @Override // android.app.Activity
    protected void onPause() {
        try {
            super.onPause();
            if (this.a != null) {
                this.a.c();
            }
        } catch (Throwable th) {
            SigmobLog.e(th.getMessage());
            HashMap map = new HashMap();
            map.put("error", th.getMessage());
            BaseBroadcastReceiver.a(this, this.b, map, IntentActions.ACTION_REWARDED_VIDEO_PLAYFAIL);
            finish();
        }
    }

    @Override // android.app.Activity
    protected void onResume() {
        try {
            super.onResume();
            if (this.a != null) {
                this.a.d();
            }
        } catch (Throwable th) {
            HashMap map = new HashMap();
            map.put("error", th.getMessage());
            BaseBroadcastReceiver.a(this, this.b, map, IntentActions.ACTION_REWARDED_VIDEO_PLAYFAIL);
            finish();
        }
    }

    @Override // android.app.Activity
    protected void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        if (this.a != null) {
            this.a.a(bundle);
        }
    }

    @Override // com.sigmob.sdk.base.common.h
    public void onSetContentView(View view) {
        setContentView(view);
    }

    @Override // android.app.Activity
    public void setRequestedOrientation(int i) {
        try {
            super.setRequestedOrientation(i);
        } catch (Exception e) {
            SigmobLog.e("setRequestedOrientation: " + e.getMessage());
        }
    }
}
