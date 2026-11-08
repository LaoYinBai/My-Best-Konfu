package com.ss.union.game.sdk.common.dialog;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Build;
import android.os.Bundle;
import com.ss.union.game.sdk.common.dialog.OperationBuilder;
import com.ss.union.game.sdk.common.util.ResourceUtils;
import com.ss.union.game.sdk.redemptionCode.a;
import java.util.ArrayList;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class MainDialog extends BaseDialogFragment {
    public static final String BUNDLE_CANCELABLE = "bundle_cancelable";
    public static final String BUNDLE_FLOW_ANIMATION = "flow_animation";
    public static final String BUNDLE_STATUS_BAR_STATUS = "bundle_status_bar_status";
    public static final String BUNDLE_TRANSPARENT_BACKGROUND = "bundle_transparent_background";
    public static final String BUNDLE_WINDOW_SOFT_INPUT_MODE = "bundle_window_soft_input_mode";
    private BaseFragment a;
    private final Bundle b = new Bundle();

    @Override // com.ss.union.game.sdk.common.dialog.BaseDialogFragment
    protected String getLayoutName() {
        return "lg_dialog_main";
    }

    @Override // com.ss.union.game.sdk.common.dialog.BaseDialogFragment
    public boolean hideStatusBar() {
        return true;
    }

    @Override // com.ss.union.game.sdk.common.dialog.BaseDialogFragment
    protected void initListener() {
    }

    @Override // com.ss.union.game.sdk.common.dialog.BaseDialogFragment
    protected void initView() {
    }

    @Override // com.ss.union.game.sdk.common.dialog.BaseDialogFragment
    protected void loadData() {
    }

    public void setBaseFragment(BaseFragment baseFragment) {
        this.a = baseFragment;
    }

    @Override // com.ss.union.game.sdk.common.dialog.BaseDialogFragment
    protected boolean initExtraData() {
        return (getArguments() == null || this.a == null) ? false : true;
    }

    @Override // com.ss.union.game.sdk.common.dialog.BaseDialogFragment
    protected void initData() {
        BaseFragment baseFragment = this.a;
        if (baseFragment == null) {
            dismiss();
        }
        navigation(null, baseFragment);
    }

    @Override // com.ss.union.game.sdk.common.dialog.BaseDialogFragment
    protected boolean onBackPressed() {
        List<Fragment> listFindAllFragments = findAllFragments();
        if (listFindAllFragments.size() >= 0) {
            Fragment fragment = listFindAllFragments.get(listFindAllFragments.size() - 1);
            if ((fragment instanceof BaseFragment) && ((BaseFragment) fragment).onBackPressed()) {
                return true;
            }
            if (listFindAllFragments.size() > 1) {
                getChildFragmentManager().popBackStack();
                return true;
            }
        }
        return super.onBackPressed();
    }

    @Override // com.ss.union.game.sdk.common.dialog.BaseDialogFragment
    protected int windowEnterAnimations() {
        Bundle arguments = getArguments();
        int i = OperationBuilder.Animation.DEFAULT.type;
        if (arguments != null) {
            i = arguments.getInt(BUNDLE_FLOW_ANIMATION, i);
        }
        if (OperationBuilder.Animation.DEFAULT.type == i || OperationBuilder.Animation.NONE.type == i) {
            return super.windowEnterAnimations();
        }
        if (OperationBuilder.Animation.LEFT.type == i) {
            return ResourceUtils.getStyleIdByName("lg_style_dialog_animation_enter_left");
        }
        if (OperationBuilder.Animation.RIGHT.type == i) {
            return ResourceUtils.getStyleIdByName("lg_style_dialog_animation_enter_right");
        }
        if (OperationBuilder.Animation.BOTTOM.type == i) {
            return ResourceUtils.getStyleIdByName("lg_style_dialog_animation_enter_bottom");
        }
        if (OperationBuilder.Animation.TOP.type == i) {
            return ResourceUtils.getStyleIdByName("lg_style_dialog_animation_enter_top");
        }
        return super.windowEnterAnimations();
    }

    @Override // com.ss.union.game.sdk.common.dialog.BaseDialogFragment
    protected int windowExitAnimations() {
        Bundle arguments = getArguments();
        int i = OperationBuilder.Animation.DEFAULT.type;
        if (arguments != null) {
            i = arguments.getInt(BUNDLE_FLOW_ANIMATION, i);
        }
        if (OperationBuilder.Animation.DEFAULT.type == i || OperationBuilder.Animation.NONE.type == i) {
            return super.windowExitAnimations();
        }
        if (OperationBuilder.Animation.LEFT.type == i) {
            return ResourceUtils.getStyleIdByName("lg_style_dialog_animation_exit_left");
        }
        if (OperationBuilder.Animation.RIGHT.type == i) {
            return ResourceUtils.getStyleIdByName("lg_style_dialog_animation_exit_right");
        }
        if (OperationBuilder.Animation.BOTTOM.type == i) {
            return ResourceUtils.getStyleIdByName("lg_style_dialog_animation_exit_bottom");
        }
        if (OperationBuilder.Animation.TOP.type == i) {
            return ResourceUtils.getStyleIdByName("lg_style_dialog_animation_exit_top");
        }
        return super.windowExitAnimations();
    }

    @Override // com.ss.union.game.sdk.common.dialog.BaseDialogFragment
    protected int softInputMode() {
        Bundle arguments = getArguments();
        if (arguments != null && arguments.containsKey(BUNDLE_WINDOW_SOFT_INPUT_MODE)) {
            return arguments.getInt(BUNDLE_WINDOW_SOFT_INPUT_MODE, super.softInputMode());
        }
        return super.softInputMode();
    }

    @Override // com.ss.union.game.sdk.common.dialog.BaseDialogFragment
    protected boolean isBackgroundTransparent() {
        Bundle arguments = getArguments();
        if (arguments != null) {
            return arguments.getBoolean(BUNDLE_TRANSPARENT_BACKGROUND, super.isBackgroundTransparent());
        }
        return super.isBackgroundTransparent();
    }

    @Override // com.ss.union.game.sdk.common.dialog.BaseDialogFragment
    protected boolean cancelable() {
        Bundle arguments = getArguments();
        if (arguments != null) {
            return arguments.getBoolean(BUNDLE_CANCELABLE, super.cancelable());
        }
        return super.cancelable();
    }

    public void navigation(BaseFragment baseFragment, BaseFragment baseFragment2) {
        if (baseFragment2 != null) {
            baseFragment2.a(this);
            if (baseFragment == null) {
                getChildFragmentManager().beginTransaction().replace(ResourceUtils.getIdByName("lg_main_dialog_content"), baseFragment2, baseFragment2.getClass().getSimpleName()).commitAllowingStateLoss();
            } else {
                a().hide(baseFragment).add(ResourceUtils.getIdByName("lg_main_dialog_content"), baseFragment2, baseFragment2.getClass().getSimpleName()).addToBackStack(null).commitAllowingStateLoss();
            }
        }
    }

    public void back() {
        if (findAllFragments().size() > 1) {
            getChildFragmentManager().popBackStack();
        } else {
            dismiss();
        }
    }

    private FragmentTransaction a() {
        return getChildFragmentManager().beginTransaction().setCustomAnimations(ResourceUtils.getAnimatorIdByName("lg_animator_slide_in_right"), ResourceUtils.getAnimatorIdByName("lg_animator_slide_out_left"), ResourceUtils.getAnimatorIdByName("lg_animator_slide_in_left"), ResourceUtils.getAnimatorIdByName("lg_animator_slide_out_right"));
    }

    public List<Fragment> findAllFragments() {
        ArrayList arrayList = new ArrayList();
        try {
            FragmentManager childFragmentManager = getChildFragmentManager();
            if (Build.VERSION.SDK_INT >= 26) {
                arrayList.addAll(childFragmentManager.getFragments());
            } else {
                int i = 0;
                while (true) {
                    int i2 = i + 1;
                    this.b.putInt(a.p, i);
                    Fragment fragment = null;
                    try {
                        fragment = childFragmentManager.getFragment(this.b, a.p);
                    } catch (Exception unused) {
                    }
                    if (fragment == null) {
                        break;
                    }
                    arrayList.add(fragment);
                    i = i2;
                }
            }
        } catch (Throwable unused2) {
        }
        return arrayList;
    }
}
