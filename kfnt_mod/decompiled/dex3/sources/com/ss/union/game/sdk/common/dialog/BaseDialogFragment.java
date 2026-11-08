package com.ss.union.game.sdk.common.dialog;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowInsets;
import android.view.WindowManager;
import android.widget.FrameLayout;
import com.ss.union.game.sdk.common.util.GlobalApplicationUtils;
import com.ss.union.game.sdk.common.util.MainThreadExecutor;
import com.ss.union.game.sdk.common.util.ResourceUtils;
import com.ss.union.game.sdk.common.util.StatusBarUtils;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public abstract class BaseDialogFragment extends DialogFragment {
    private Context a;
    protected View mRootView;
    private boolean b = false;
    protected List<OnDismissListener> onDismissListener = new ArrayList();

    public interface OnDismissListener {
        void onDismiss(DialogInterface dialogInterface);
    }

    protected boolean canCanceledOnTouchOutside() {
        return false;
    }

    protected boolean cancelable() {
        return false;
    }

    protected int dialogGravity() {
        return 17;
    }

    protected int dialogHeight() {
        return -1;
    }

    protected int dialogWidth() {
        return -1;
    }

    protected float getBackgroundDimAmount() {
        return -1.0f;
    }

    protected abstract String getLayoutName();

    public boolean hideStatusBar() {
        return true;
    }

    protected abstract void initData();

    protected abstract boolean initExtraData();

    protected abstract void initListener();

    protected abstract void initView();

    protected boolean isBackgroundTransparent() {
        return false;
    }

    protected abstract void loadData();

    protected boolean onBackPressed() {
        return false;
    }

    protected int softInputMode() {
        return -1;
    }

    protected int windowEnterAnimations() {
        return 0;
    }

    protected int windowExitAnimations() {
        return 0;
    }

    @Override // android.app.DialogFragment
    public Dialog onCreateDialog(Bundle bundle) {
        int styleIdByName = ResourceUtils.getStyleIdByName("lg_style_dialog_theme");
        if (styleIdByName != 0) {
            setStyle(0, styleIdByName);
        }
        return super.onCreateDialog(bundle);
    }

    @Override // android.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        try {
            initLayout();
        } catch (Throwable unused) {
        }
        int layoutIdByName = ResourceUtils.getLayoutIdByName(getLayoutName());
        if (layoutIdByName == 0) {
            Context context = Build.VERSION.SDK_INT >= 23 ? getContext() : null;
            if (context == null) {
                context = getActivity();
            }
            if (context == null && viewGroup != null) {
                context = viewGroup.getContext();
            }
            if (context == null && getDialog() != null) {
                context = getDialog().getContext();
            }
            if (context != null && GlobalApplicationUtils.getContext() == null) {
                GlobalApplicationUtils.init(context.getApplicationContext());
                layoutIdByName = ResourceUtils.getLayoutIdByName(getLayoutName());
            }
            if (layoutIdByName != 0) {
                this.mRootView = layoutInflater.inflate(layoutIdByName, viewGroup, false);
            } else if (context != null) {
                this.mRootView = new FrameLayout(context);
            }
        } else {
            this.mRootView = layoutInflater.inflate(layoutIdByName, viewGroup, false);
        }
        if (this.mRootView != null) {
            this.a = this.mRootView.getContext();
        }
        return this.mRootView;
    }

    @Override // android.app.DialogFragment, android.app.Fragment
    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        if (initExtraData()) {
            initView();
            initData();
            initListener();
            loadData();
            return;
        }
        dismiss();
    }

    protected View findViewByName(String str) {
        if (this.mRootView == null) {
            return null;
        }
        return this.mRootView.findViewById(ResourceUtils.getIdByName(str));
    }

    @Override // android.app.DialogFragment
    public void show(FragmentManager fragmentManager, String str) {
        if (this.b) {
            return;
        }
        this.b = true;
        super.show(fragmentManager, str);
    }

    @Override // android.app.DialogFragment
    public void dismiss() {
        dismissAllowingStateLoss();
    }

    @Override // android.app.DialogFragment
    public void dismissAllowingStateLoss() {
        try {
            super.dismissAllowingStateLoss();
        } catch (Throwable unused) {
        }
        this.b = false;
    }

    public boolean isShowing() {
        return this.b;
    }

    protected void initLayout() {
        final Window window;
        Dialog dialog = getDialog();
        if (dialog == null || (window = getDialog().getWindow()) == null) {
            return;
        }
        window.requestFeature(1);
        window.setBackgroundDrawable(new ColorDrawable(0));
        if (hideStatusBar()) {
            StatusBarUtils.openImmersion(this);
        }
        int iSoftInputMode = softInputMode();
        if (iSoftInputMode != -1) {
            window.setSoftInputMode(iSoftInputMode);
            if (iSoftInputMode == 16) {
                final View decorView = window.getDecorView();
                if (Build.VERSION.SDK_INT >= 20) {
                    decorView.setOnApplyWindowInsetsListener(new View.OnApplyWindowInsetsListener() { // from class: com.ss.union.game.sdk.common.dialog.BaseDialogFragment.1
                        @Override // android.view.View.OnApplyWindowInsetsListener
                        public WindowInsets onApplyWindowInsets(View view, WindowInsets windowInsets) {
                            decorView.setPadding(decorView.getPaddingLeft(), decorView.getPaddingTop(), decorView.getPaddingRight(), windowInsets.getSystemWindowInsetBottom());
                            return windowInsets;
                        }
                    });
                }
            }
        }
        window.setGravity(dialogGravity());
        int iWindowEnterAnimations = windowEnterAnimations();
        if (iWindowEnterAnimations != 0) {
            window.setWindowAnimations(iWindowEnterAnimations);
            final int iWindowExitAnimations = windowExitAnimations();
            if (iWindowExitAnimations != 0) {
                MainThreadExecutor.postDelayed(new Runnable() { // from class: com.ss.union.game.sdk.common.dialog.BaseDialogFragment.2
                    @Override // java.lang.Runnable
                    public void run() {
                        window.setWindowAnimations(iWindowExitAnimations);
                    }
                }, ResourceUtils.getInteger("lg_int_slide_anim_time", 400));
            }
        }
        if (window.getDecorView() != null) {
            window.getDecorView().setPadding(0, 0, 0, 0);
        }
        dialog.setOnKeyListener(new DialogInterface.OnKeyListener() { // from class: com.ss.union.game.sdk.common.dialog.BaseDialogFragment.3
            @Override // android.content.DialogInterface.OnKeyListener
            public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
                if (i != 4) {
                    return false;
                }
                if (keyEvent.getAction() == 1 && !BaseDialogFragment.this.onBackPressed() && BaseDialogFragment.this.cancelable()) {
                    BaseDialogFragment.this.dismiss();
                }
                return true;
            }
        });
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(canCanceledOnTouchOutside());
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.width = dialogWidth();
        attributes.height = dialogHeight();
        if (isBackgroundTransparent()) {
            attributes.dimAmount = 0.0f;
        } else {
            float backgroundDimAmount = getBackgroundDimAmount();
            if (backgroundDimAmount >= 0.0f && backgroundDimAmount <= 1.0f) {
                attributes.dimAmount = backgroundDimAmount;
            }
        }
        window.setAttributes(attributes);
    }

    @Override // android.app.DialogFragment, android.content.DialogInterface.OnDismissListener
    public void onDismiss(DialogInterface dialogInterface) {
        super.onDismiss(dialogInterface);
        this.b = false;
        if (this.onDismissListener.isEmpty()) {
            return;
        }
        Iterator<OnDismissListener> it = this.onDismissListener.iterator();
        while (it.hasNext()) {
            it.next().onDismiss(dialogInterface);
        }
    }

    public BaseDialogFragment addOnDismissListener(OnDismissListener onDismissListener) {
        this.onDismissListener.add(onDismissListener);
        return this;
    }
}
