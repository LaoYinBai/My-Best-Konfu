package com.ss.union.game.sdk.core.init.b;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import com.ss.android.downloadad.api.download.AdDownloadModel;
import com.ss.union.game.sdk.common.net.CoreNetClient;
import com.ss.union.game.sdk.common.net.http.base.in.ACoreRequestPost;
import com.ss.union.game.sdk.common.net.http.base.in.ICoreNetCallback;
import com.ss.union.game.sdk.common.net.http.base.response.CoreNetResponse;
import com.ss.union.game.sdk.common.util.ActivityUtils;
import com.ss.union.game.sdk.common.util.DeviceUtils;
import com.ss.union.game.sdk.common.util.MainThreadExecutor;
import com.ss.union.game.sdk.common.util.SPUtils;
import com.ss.union.game.sdk.common.util.executeTime.impl.InitTime;
import com.ss.union.game.sdk.common.util.flow.FlowItem;
import com.ss.union.game.sdk.core.base.constant.CoreUrls;
import com.ss.union.game.sdk.core.base.debug.automatic_detection.LGDetectionManager;
import com.ss.union.game.sdk.core.base.debug.automatic_detection.constants.LGDetectionConstant;
import com.ss.union.game.sdk.core.init.fragment.PrivacyPolicyFragment;
import com.ss.union.game.sdk.core.privacy.LGPrivacyPolicyManager;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class k extends FlowItem {
    @Override // com.ss.union.game.sdk.common.util.flow.FlowItem
    public String toString() {
        return "PrivacyPolicyInit";
    }

    @Override // com.ss.union.game.sdk.common.util.flow.FlowItem
    public void doIt() {
        b();
    }

    private void b() {
        LGDetectionManager.getDetectionApi().detection(LGDetectionConstant.DetectionType.PRIVACY, LGDetectionConstant.PrivacyItem.DETECTION_ID_WINDOW_INVOKE, LGDetectionConstant.DetectionState.PASS);
        CoreNetClient.post(CoreUrls.Privacy.URL_PRIVACY_POLICY_CONFIG).param("device_platform", "android").param(com.umeng.message.common.a.u, DeviceUtils.getAppPackageName()).param(AdDownloadModel.JsonKey.VERSION_NAME, DeviceUtils.getVersionName()).param("version_code", DeviceUtils.getVersionCode()).api(new ICoreNetCallback<JSONObject, ACoreRequestPost>() { // from class: com.ss.union.game.sdk.core.init.b.k.1
            @Override // com.ss.union.game.sdk.common.net.http.base.in.ICoreNetCallback
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public void onNetError(ACoreRequestPost aCoreRequestPost, CoreNetResponse<JSONObject, ACoreRequestPost> coreNetResponse) {
                super.onNetError(aCoreRequestPost, coreNetResponse);
                k.this.a(false);
            }

            @Override // com.ss.union.game.sdk.common.net.http.base.in.ICoreNetCallback
            /* renamed from: b, reason: merged with bridge method [inline-methods] */
            public void onNetSuccess(ACoreRequestPost aCoreRequestPost, CoreNetResponse<JSONObject, ACoreRequestPost> coreNetResponse) {
                super.onNetSuccess(aCoreRequestPost, coreNetResponse);
                boolean z = false;
                try {
                    JSONObject jSONObjectOptJSONObject = coreNetResponse.data.optJSONObject("data");
                    if (jSONObjectOptJSONObject != null) {
                        boolean zOptBoolean = jSONObjectOptJSONObject.optBoolean("use_privacy_popup");
                        try {
                            String strOptString = jSONObjectOptJSONObject.optString("user_protocol");
                            if (!TextUtils.isEmpty(strOptString)) {
                                LGPrivacyPolicyManager.saveServerUserProtocolUrl(strOptString);
                            }
                            String strOptString2 = jSONObjectOptJSONObject.optString("privacy_protocol");
                            if (!TextUtils.isEmpty(strOptString2)) {
                                LGPrivacyPolicyManager.saveServerPrivacyProtocolUrl(strOptString2);
                            }
                            String strOptString3 = jSONObjectOptJSONObject.optString("identify_protocol");
                            if (!TextUtils.isEmpty(strOptString3)) {
                                LGPrivacyPolicyManager.saveServerIdentifyProtocolUrl(strOptString3);
                            }
                        } catch (Throwable unused) {
                        }
                        z = zOptBoolean;
                    }
                } catch (Throwable unused2) {
                }
                if (k.this.e()) {
                    k.this.d();
                } else {
                    k.this.a(z);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(boolean z) {
        if (z) {
            a();
        } else {
            d();
        }
    }

    public void a() {
        if (ActivityUtils.getTopActivity() == null) {
            c();
        } else {
            MainThreadExecutor.post(new Runnable() { // from class: com.ss.union.game.sdk.core.init.b.k.2
                @Override // java.lang.Runnable
                public void run() {
                    InitTime.getInstance().ignoreStart(InitTime.INIT_TIME_TAG);
                    PrivacyPolicyFragment.a(new PrivacyPolicyFragment.a() { // from class: com.ss.union.game.sdk.core.init.b.k.2.1
                        @Override // com.ss.union.game.sdk.core.init.fragment.PrivacyPolicyFragment.a
                        public void a() {
                            InitTime.getInstance().ignoreEnd(InitTime.INIT_TIME_TAG);
                            k.this.d();
                        }
                    });
                }
            });
        }
    }

    private void c() {
        ActivityUtils.registerActivityLifecycleCallbacks(new ActivityUtils.LifecycleCallbacks() { // from class: com.ss.union.game.sdk.core.init.b.k.3
            @Override // com.ss.union.game.sdk.common.util.ActivityUtils.LifecycleCallbacks, android.app.Application.ActivityLifecycleCallbacks
            public void onActivityCreated(Activity activity, Bundle bundle) {
                super.onActivityCreated(activity, bundle);
                ActivityUtils.unregisterActivityLifecycleCallbacks(this);
                k.this.a();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d() {
        MainThreadExecutor.post(new Runnable() { // from class: com.ss.union.game.sdk.core.init.b.k.4
            @Override // java.lang.Runnable
            public void run() {
                LGDetectionManager.getDetectionApi().detection(LGDetectionConstant.DetectionType.PRIVACY, LGDetectionConstant.PrivacyItem.DETECTION_ID_WINDOW_CALLBACK, LGDetectionConstant.DetectionState.PASS);
                k.this.finish();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean e() {
        try {
            return SPUtils.getInstance("lg_init_config").getBoolean("key_is_agree_privacy_policy", false);
        } catch (Throwable unused) {
            return false;
        }
    }
}
