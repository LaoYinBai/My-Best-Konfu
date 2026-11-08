package com.ss.union.game.sdk.core.antiAddiction.b;

import com.ss.union.game.sdk.common.net.CoreNetClient;
import com.ss.union.game.sdk.common.net.http.base.in.ACoreRequestPost;
import com.ss.union.game.sdk.common.net.http.base.in.ICoreNetCallback;
import com.ss.union.game.sdk.common.net.http.base.response.CoreNetResponse;
import com.ss.union.game.sdk.common.util.DeviceUtils;
import com.ss.union.game.sdk.core.antiAddiction.abs.AbsAntiAddictionStrategy;
import com.ss.union.game.sdk.core.antiAddiction.bean.AntiAddictionInfo;
import com.ss.union.game.sdk.core.antiAddiction.config.AntiAddictionConfig;
import com.ss.union.game.sdk.core.antiAddiction.fragment.AntiAddictionFragment;
import com.ss.union.game.sdk.core.applog.AppLogManager;
import com.ss.union.game.sdk.core.base.config.AppIdManager;
import com.ss.union.game.sdk.core.base.constant.CoreUrls;
import com.ss.union.game.sdk.core.init.config.GameOptionConfig;
import com.ss.union.game.sdk.core.realName.RealNameManager;
import com.ss.union.game.sdk.core.realName.b.a;
import com.ss.union.game.sdk.core.realName.callback.CheckDeviceRealNameCallback;
import com.ss.union.game.sdk.core.realName.callback.LGRealNameCallback;
import com.ss.union.game.sdk.core.realName.callback.LGRealNameReminderCallback;
import com.ss.union.game.sdk.core.realName.entity.CheckDeviceRealNameInfo;
import com.ss.union.game.sdk.core.realName.entity.LGRealNameRewardInfo;
import com.ss.union.game.sdk.core.realName.event.RealNameLogEvent;
import com.ss.union.game.sdk.core.realName.fragment.RealNameReminderFragment;
import com.ss.union.game.sdk.core.util.LogDevKitUtils;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class a extends AbsAntiAddictionStrategy {
    private static final String a = "AntiAddictionDevice";
    private boolean b = false;

    @Override // com.ss.union.game.sdk.core.antiAddiction.abs.AbsAntiAddictionStrategy
    public void antiAddictionStart() {
        LogDevKitUtils.logDeviceAntiAddiction("antiAddictionStart");
        a();
    }

    private void a() {
        RealNameManager.checkDeviceRealName(new CheckDeviceRealNameCallback() { // from class: com.ss.union.game.sdk.core.antiAddiction.b.a.1
            @Override // com.ss.union.game.sdk.core.realName.callback.CheckDeviceRealNameCallback
            public void onSuccess(CheckDeviceRealNameInfo checkDeviceRealNameInfo) {
                boolean z = checkDeviceRealNameInfo.is_identify_validated;
                boolean z2 = checkDeviceRealNameInfo.is_adult;
                LogDevKitUtils.logDeviceAntiAddiction("updateDeviceAntiAddiction success , isRealNameValid = " + z + " , isAdult = " + z2);
                a.C0087a.a(z);
                a.C0087a.b(z2);
                com.ss.union.game.sdk.core.realName.a.a.a(z2);
                if (!checkDeviceRealNameInfo.show_link_tips) {
                    a.this.b();
                } else {
                    RealNameReminderFragment.show(checkDeviceRealNameInfo.link_tips, new LGRealNameReminderCallback() { // from class: com.ss.union.game.sdk.core.antiAddiction.b.a.1.1
                        @Override // com.ss.union.game.sdk.core.realName.callback.LGRealNameReminderCallback
                        public void close() {
                            a.this.b = true;
                            a.this.b();
                        }
                    });
                }
                RealNameLogEvent.reportDeviceRealNameRequestSuccess();
            }

            @Override // com.ss.union.game.sdk.core.realName.callback.CheckDeviceRealNameCallback
            public void onFail(int i, String str) {
                LogDevKitUtils.logDeviceAntiAddiction("updateDeviceAntiAddiction error , errorCode = " + i + " , errorMsg = " + str);
                a.this.b();
                RealNameLogEvent.reportDeviceRealNameRequestFail();
            }
        });
        RealNameLogEvent.reportDeviceRealNameRequest(DeviceUtils.getAppPackageName());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b() {
        LogDevKitUtils.logDeviceAntiAddiction("checkDeviceAntiAddiction start");
        boolean zA = a.C0087a.a();
        boolean zB = a.C0087a.b();
        if (zA && zB) {
            LogDevKitUtils.logDeviceAntiAddiction("checkDeviceAntiAddiction user is adult");
            return;
        }
        if (!zA && GameOptionConfig.GameOption.AntiAddiction.Device.isShowRealNameWindowAuto() && !this.b) {
            LogDevKitUtils.logDeviceAntiAddiction("checkDeviceAntiAddiction show real name auto");
            this.b = true;
            RealNameManager.showRealNameWindow(100, new LGRealNameCallback() { // from class: com.ss.union.game.sdk.core.antiAddiction.b.a.2
                @Override // com.ss.union.game.sdk.core.realName.callback.LGRealNameCallback
                public void onSuccess(boolean z, boolean z2, LGRealNameRewardInfo lGRealNameRewardInfo) {
                    LogDevKitUtils.logDeviceAntiAddiction("checkDeviceAntiAddiction show real name auto success isRealNameValid = " + z + " , isAdult = " + z2);
                    if (z && z2) {
                        return;
                    }
                    a.this.c();
                }

                @Override // com.ss.union.game.sdk.core.realName.callback.LGRealNameCallback
                public void onFail(int i, String str) {
                    a.this.c();
                }
            });
            return;
        }
        c();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        LogDevKitUtils.logDeviceAntiAddiction("updateAntiAddictionTime start");
        CoreNetClient.post(CoreUrls.RealName.URL_GET_ANTI_ADDICTION_INFO).param("type", "DEVICE").param("app_id", AppIdManager.lgAppID()).api(new ICoreNetCallback<JSONObject, ACoreRequestPost>() { // from class: com.ss.union.game.sdk.core.antiAddiction.b.a.3
            @Override // com.ss.union.game.sdk.common.net.http.base.in.ICoreNetCallback
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public void onNetError(ACoreRequestPost aCoreRequestPost, CoreNetResponse<JSONObject, ACoreRequestPost> coreNetResponse) {
                super.onNetError(aCoreRequestPost, coreNetResponse);
                LogDevKitUtils.logDeviceAntiAddiction("updateAntiAddictionTime error code = " + coreNetResponse.code() + " msg = " + coreNetResponse.message());
                AntiAddictionInfo antiAddictionInfoInfo = AntiAddictionConfig.Device.info();
                antiAddictionInfoInfo.serverTime = System.currentTimeMillis() / 1000;
                antiAddictionInfoInfo.id = AppLogManager.getInstance().getDid();
                AntiAddictionConfig.Device.update(antiAddictionInfoInfo);
                a.this.d();
            }

            @Override // com.ss.union.game.sdk.common.net.http.base.in.ICoreNetCallback
            /* renamed from: b, reason: merged with bridge method [inline-methods] */
            public void onNetSuccess(ACoreRequestPost aCoreRequestPost, CoreNetResponse<JSONObject, ACoreRequestPost> coreNetResponse) {
                super.onNetSuccess(aCoreRequestPost, coreNetResponse);
                AntiAddictionInfo antiAddictionInfo = new AntiAddictionInfo();
                antiAddictionInfo.parse(coreNetResponse.data, coreNetResponse.serverTimestampS, AppLogManager.getInstance().getDid());
                AntiAddictionConfig.Device.update(antiAddictionInfo);
                LogDevKitUtils.logDeviceAntiAddiction("updateAntiAddictionTime success remainingTime = " + antiAddictionInfo.remainingTime);
                a.this.d();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d() {
        LogDevKitUtils.logDeviceAntiAddiction("checkAntiAddictionTime");
        if (AntiAddictionConfig.Device.info().isInMinorLimit) {
            LogDevKitUtils.logDeviceAntiAddiction("isInMinorLimit = true, show AntiAddictionFragment");
            AntiAddictionFragment.show();
        } else {
            e();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e() {
        if (a.C0087a.a()) {
            if (a.C0087a.b()) {
                return;
            }
            int iRemainTime = AntiAddictionConfig.Device.remainTime() * 60;
            if (iRemainTime == 0) {
                f();
                return;
            } else {
                startAntiAddiction(iRemainTime);
                return;
            }
        }
        int iRemainTime2 = AntiAddictionConfig.Device.remainTime() * 60;
        if (iRemainTime2 == 0) {
            if (this.b) {
                f();
                return;
            } else {
                this.b = true;
                RealNameManager.showRealNameWindow(105, new LGRealNameCallback() { // from class: com.ss.union.game.sdk.core.antiAddiction.b.a.4
                    @Override // com.ss.union.game.sdk.core.realName.callback.LGRealNameCallback
                    public void onSuccess(boolean z, boolean z2, LGRealNameRewardInfo lGRealNameRewardInfo) {
                        a.this.e();
                    }

                    @Override // com.ss.union.game.sdk.core.realName.callback.LGRealNameCallback
                    public void onFail(int i, String str) {
                        a.this.f();
                    }
                });
                return;
            }
        }
        startAntiAddiction(iRemainTime2);
    }

    @Override // com.ss.union.game.sdk.core.antiAddiction.abs.AbsAntiAddictionStrategy
    protected void antiAddictionFinished() {
        if (a.C0087a.a() && a.C0087a.b()) {
            return;
        }
        AntiAddictionFragment.show();
        AntiAddictionConfig.Device.triggerAnti();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void f() {
        AntiAddictionFragment.show();
    }
}
