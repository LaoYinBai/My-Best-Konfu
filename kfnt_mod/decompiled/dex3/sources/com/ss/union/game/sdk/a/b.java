package com.ss.union.game.sdk.a;

import com.ss.union.game.sdk.common.util.DeviceUtils;
import com.ss.union.game.sdk.common.util.ResourceUtils;
import com.ss.union.game.sdk.common.util.logger.LogUtils;
import com.ss.union.game.sdk.core.base.account.LGAccountDataUtil;
import com.ss.union.game.sdk.core.base.dialog.fragment.StandardFragmentDialog;
import com.ss.union.game.sdk.core.base.dialog.model.StandardDialogParams;
import com.ss.union.game.sdk.core.base.utils.DanJuanUtils;
import com.ss.union.game.sdk.core.init.config.GameOptionConfig;
import com.ss.union.game.sdk.core.realName.RealNameManager;
import com.ss.union.game.sdk.core.realName.callback.CheckDeviceRealNameCallback;
import com.ss.union.game.sdk.core.realName.callback.LGRealNameCallback;
import com.ss.union.game.sdk.core.realName.callback.LGRealNameReminderCallback;
import com.ss.union.game.sdk.core.realName.entity.CheckDeviceRealNameInfo;
import com.ss.union.game.sdk.core.realName.entity.LGRealNameRewardInfo;
import com.ss.union.game.sdk.core.realName.event.RealNameLogEvent;
import com.ss.union.game.sdk.core.realName.fragment.RealNameReminderFragment;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class b {
    private static final String a = "LGAntiAddictionAccountEmitter";
    private final com.ss.union.game.sdk.a.a b;
    private boolean c;
    private boolean d;

    /* renamed from: com.ss.union.game.sdk.a.b$b, reason: collision with other inner class name */
    public interface InterfaceC0048b {
        void a();
    }

    public static b a() {
        return a.a;
    }

    private static class a {
        static b a = new b();

        private a() {
        }
    }

    private b() {
        this.b = new com.ss.union.game.sdk.a.a();
    }

    public void a(InterfaceC0048b interfaceC0048b) {
        f();
        if (e()) {
            g();
            if (interfaceC0048b != null) {
                interfaceC0048b.a();
                return;
            }
            return;
        }
        if (c()) {
            b(interfaceC0048b);
        } else if (d()) {
            d(interfaceC0048b);
        } else {
            c(interfaceC0048b);
        }
    }

    private void b(final InterfaceC0048b interfaceC0048b) {
        RealNameManager.checkDeviceRealName(new CheckDeviceRealNameCallback() { // from class: com.ss.union.game.sdk.a.b.1
            @Override // com.ss.union.game.sdk.core.realName.callback.CheckDeviceRealNameCallback
            public void onSuccess(CheckDeviceRealNameInfo checkDeviceRealNameInfo) {
                if (!checkDeviceRealNameInfo.show_link_tips) {
                    if (b.this.d()) {
                        b.this.d(interfaceC0048b);
                    } else {
                        b.this.c(interfaceC0048b);
                    }
                } else {
                    RealNameReminderFragment.show(checkDeviceRealNameInfo.link_tips, new LGRealNameReminderCallback() { // from class: com.ss.union.game.sdk.a.b.1.1
                        @Override // com.ss.union.game.sdk.core.realName.callback.LGRealNameReminderCallback
                        public void close() {
                            b.this.c = true;
                            b.this.c(interfaceC0048b);
                        }
                    });
                }
                RealNameLogEvent.reportDeviceRealNameRequestSuccess();
            }

            @Override // com.ss.union.game.sdk.core.realName.callback.CheckDeviceRealNameCallback
            public void onFail(int i, String str) {
                LogUtils.log(b.a, "checkDeviceRealName onFail errorCode = " + i + "--errorMsg = " + str);
                if (b.this.d()) {
                    b.this.d(interfaceC0048b);
                } else {
                    b.this.c(interfaceC0048b);
                }
                RealNameLogEvent.reportDeviceRealNameRequestFail();
            }
        });
        RealNameLogEvent.reportDeviceRealNameRequest(DeviceUtils.getAppPackageName());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(InterfaceC0048b interfaceC0048b) {
        this.b.antiAddictionStart();
        if (interfaceC0048b != null) {
            interfaceC0048b.a();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(InterfaceC0048b interfaceC0048b) {
        this.c = true;
        if (c()) {
            a(102, interfaceC0048b);
        } else {
            a(101, interfaceC0048b);
        }
    }

    public boolean b() {
        return this.c;
    }

    public void a(LGRealNameCallback lGRealNameCallback) {
        this.b.a(lGRealNameCallback);
    }

    private boolean c() {
        return LGAccountDataUtil.isVisitor();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean d() {
        return GameOptionConfig.GameOption.AntiAddiction.Account.isShowRealNameWindowAuto() && !LGAccountDataUtil.isIdentifyValidated();
    }

    private boolean e() {
        return LGAccountDataUtil.isIdentifyValidated() && LGAccountDataUtil.isAdult() && LGAccountDataUtil.isFirstLogin() && !this.d && !DanJuanUtils.isRunningDanJuanVirtual();
    }

    private void f() {
        this.c = false;
        this.b.stopAllAntiAddictionCountdown();
    }

    private void g() {
        this.d = true;
        StandardFragmentDialog.show(new StandardDialogParams().setTitle(ResourceUtils.getString("lg_real_name_adult_title")).setDescription(ResourceUtils.getString("lg_real_name_adult_dialog_text")).setEnhanceBtnText(ResourceUtils.getString("lg_real_name_adult_dialog_btn")).setCloseBtnEnable(true), null);
        RealNameLogEvent.reportShow(RealNameLogEvent.REAL_NAME_EVENT_VALUE_ADULT_WINDOW);
    }

    private void a(int i, final InterfaceC0048b interfaceC0048b) {
        RealNameManager.showRealNameWindow(i, new LGRealNameCallback() { // from class: com.ss.union.game.sdk.a.b.2
            @Override // com.ss.union.game.sdk.core.realName.callback.LGRealNameCallback
            public void onSuccess(boolean z, boolean z2, LGRealNameRewardInfo lGRealNameRewardInfo) {
                b.this.b.antiAddictionStart();
                if (interfaceC0048b != null) {
                    interfaceC0048b.a();
                }
            }

            @Override // com.ss.union.game.sdk.core.realName.callback.LGRealNameCallback
            public void onFail(int i2, String str) {
                b.this.b.antiAddictionStart();
                if (interfaceC0048b != null) {
                    interfaceC0048b.a();
                }
            }
        });
    }
}
