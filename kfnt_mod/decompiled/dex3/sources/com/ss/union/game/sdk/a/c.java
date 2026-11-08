package com.ss.union.game.sdk.a;

import android.text.TextUtils;
import com.ss.union.game.sdk.account.fragment.oneKey.OneKeyBindFragment;
import com.ss.union.game.sdk.account.fragment.oneKey.OneKeySwitchFragment;
import com.ss.union.game.sdk.common.dialog.BaseFragment;
import com.ss.union.game.sdk.common.util.ResourceUtils;
import com.ss.union.game.sdk.core.base.account.LGAccountDataUtil;
import com.ss.union.game.sdk.core.base.dialog.callback.StandardDialogListenerAdapter;
import com.ss.union.game.sdk.core.base.dialog.fragment.StandardFragmentDialog;
import com.ss.union.game.sdk.core.base.dialog.model.StandardDialogParams;
import com.ss.union.game.sdk.core.realName.LGRealNameManagerImpl;
import com.ss.union.game.sdk.core.realName.event.RealNameLogEvent;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class c {
    private static boolean b(int i) {
        return i == 105 || i == 107 || i == 104 || i == 103;
    }

    public static void a(int i, StandardDialogListenerAdapter standardDialogListenerAdapter) {
        a aVarB = b(i, standardDialogListenerAdapter);
        StandardFragmentDialog.show(aVarB.a, aVarB.b);
    }

    public static boolean a(int i) {
        boolean z = LGAccountDataUtil.isVisitor() || (i == 103);
        boolean z2 = i == 108;
        if (z) {
            return b(i) || z2;
        }
        return false;
    }

    private static a b(int i, StandardDialogListenerAdapter standardDialogListenerAdapter) {
        if (i == 108) {
            a aVarA = a(standardDialogListenerAdapter);
            RealNameLogEvent.reportShow(RealNameLogEvent.REAL_NAME_EVENT_VALUE_CHOSE_ZHUDONG);
            return aVarA;
        }
        a aVarC = c(i, standardDialogListenerAdapter);
        RealNameLogEvent.reportShow(RealNameLogEvent.REAL_NAME_EVENT_VALUE_CHOSE_BEIDONG);
        return aVarC;
    }

    private static a a(final StandardDialogListenerAdapter standardDialogListenerAdapter) {
        StandardDialogParams standardDialogParams = new StandardDialogParams();
        standardDialogParams.setCloseBtnEnable(true).setBackBtnEnable(false).setWeaknessBtnEnable(true).setDescription(ResourceUtils.getString("lg_login_and_real_name_dialog_description_outer_invoke")).setTitle(ResourceUtils.getString("lg_login_and_real_name_dialog_to_auth")).setEnhanceBtnText(ResourceUtils.getString("lg_login_and_real_name_dialog_to_login")).setWeaknessBtnText(ResourceUtils.getString("lg_login_and_real_name_dialog_continue_auth"));
        return new a(standardDialogParams, new StandardDialogListenerAdapter() { // from class: com.ss.union.game.sdk.a.c.1
            @Override // com.ss.union.game.sdk.core.base.dialog.callback.StandardDialogListenerAdapter, com.ss.union.game.sdk.core.base.dialog.callback.a
            public boolean onEnhanceBtnClick(BaseFragment baseFragment) {
                RealNameLogEvent.reportShow(RealNameLogEvent.REAL_NAME_EVENT_VALUE_LOGIN_ZHUDONG);
                if (TextUtils.isEmpty(LGAccountDataUtil.getCurrentUserOpenId())) {
                    OneKeySwitchFragment.a(baseFragment, false);
                    return true;
                }
                OneKeyBindFragment.a(baseFragment, false);
                return true;
            }

            @Override // com.ss.union.game.sdk.core.base.dialog.callback.StandardDialogListenerAdapter, com.ss.union.game.sdk.core.base.dialog.callback.a
            public boolean onWeaknessBtnClick(BaseFragment baseFragment) {
                RealNameLogEvent.reportShow(RealNameLogEvent.REAL_NAME_EVENT_VALUE_WINDOW_ZHUDONG);
                return standardDialogListenerAdapter.onWeaknessBtnClick(baseFragment);
            }

            @Override // com.ss.union.game.sdk.core.base.dialog.callback.StandardDialogListenerAdapter, com.ss.union.game.sdk.core.base.dialog.callback.a
            public boolean onCloseClick(BaseFragment baseFragment) {
                LGRealNameManagerImpl.getInstance().globalRealNameFail(-1004, "取消实名认证");
                return super.onCloseClick(baseFragment);
            }
        });
    }

    private static a c(int i, final StandardDialogListenerAdapter standardDialogListenerAdapter) {
        StandardDialogParams standardDialogParams = new StandardDialogParams();
        standardDialogParams.setCloseBtnEnable(false).setBackBtnEnable(false).setWeaknessBtnEnable(true).setTitle(ResourceUtils.getString("lg_login_and_real_name_dialog_to_auth")).setDescription(ResourceUtils.getString("lg_login_and_real_name_dialog_description_outer_invoke")).setWeaknessBtnText(ResourceUtils.getString("lg_login_and_real_name_dialog_to_auth")).setEnhanceBtnText(ResourceUtils.getString("lg_login_and_real_name_dialog_to_login"));
        return new a(standardDialogParams, new StandardDialogListenerAdapter() { // from class: com.ss.union.game.sdk.a.c.2
            @Override // com.ss.union.game.sdk.core.base.dialog.callback.StandardDialogListenerAdapter, com.ss.union.game.sdk.core.base.dialog.callback.a
            public boolean onEnhanceBtnClick(BaseFragment baseFragment) {
                if (TextUtils.isEmpty(LGAccountDataUtil.getCurrentUserOpenId())) {
                    OneKeySwitchFragment.a(baseFragment, false);
                } else {
                    OneKeyBindFragment.a(baseFragment, false);
                }
                RealNameLogEvent.reportShow(RealNameLogEvent.REAL_NAME_EVENT_VALUE_LOGIN_BEIDONG);
                return true;
            }

            @Override // com.ss.union.game.sdk.core.base.dialog.callback.StandardDialogListenerAdapter, com.ss.union.game.sdk.core.base.dialog.callback.a
            public boolean onWeaknessBtnClick(BaseFragment baseFragment) {
                RealNameLogEvent.reportShow(RealNameLogEvent.REAL_NAME_EVENT_VALUE_WINDOW_BEIDONG);
                return standardDialogListenerAdapter.onWeaknessBtnClick(baseFragment);
            }
        });
    }

    static class a {
        StandardDialogParams a;
        StandardDialogListenerAdapter b;

        public a(StandardDialogParams standardDialogParams, StandardDialogListenerAdapter standardDialogListenerAdapter) {
            this.a = standardDialogParams;
            this.b = standardDialogListenerAdapter;
        }
    }
}
