package com.ss.union.game.sdk.common.dialog;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.FrameLayout;
import com.sigmob.sdk.common.mta.PointCategory;
import com.ss.union.game.sdk.common.mvp.BaseView;
import com.ss.union.game.sdk.common.mvp.IPresenter;
import com.ss.union.game.sdk.common.util.AntiShakeClickListener;
import com.ss.union.game.sdk.common.util.GlobalApplicationUtils;
import com.ss.union.game.sdk.common.util.LoadingUtils;
import com.ss.union.game.sdk.common.util.ResourceUtils;
import com.ss.union.game.sdk.common.util.StatusBarUtils;
import com.ss.union.game.sdk.common.util.ToastUtils;
import com.ss.union.game.sdk.common.util.UIUtils;
import com.ss.union.game.sdk.common.util.logger.LogUtils;
import com.tachikoma.core.component.anim.AnimationProperty;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public abstract class BaseFragment<C, P extends IPresenter> extends Fragment implements BaseView {
    private Context b;
    private C c;
    protected ViewGroup containerView;
    private MainDialog d;
    private ProgressDialog e;
    private String g;
    protected boolean isPaused;
    protected P mPresenter;
    protected boolean isVisible = false;
    protected boolean isLazyLoad = false;
    private boolean a = false;
    private int f = -1;

    protected View getContentView() {
        return null;
    }

    protected abstract String getLayoutId();

    protected abstract boolean initArgument(Bundle bundle);

    protected abstract void initData();

    protected abstract void initListener();

    protected P initPresenter() {
        return null;
    }

    protected abstract void initView();

    protected boolean isOpenLazyLoad() {
        return false;
    }

    protected boolean isShowStatusBar() {
        return false;
    }

    protected boolean isStatusBarDarkMode() {
        return true;
    }

    protected abstract void loadData();

    protected boolean onBackPressed() {
        return false;
    }

    protected void screenChange2Landscape() {
    }

    protected void screenChange2Portrait() {
    }

    protected String statusBarColor() {
        return "#00000000";
    }

    @Override // android.app.Fragment
    public void onAttach(Context context) {
        super.onAttach(context);
        onAttachToContext(context);
    }

    @Override // android.app.Fragment
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (Build.VERSION.SDK_INT < 23) {
            onAttachToContext(activity);
        }
    }

    protected void onAttachToContext(Context context) {
        this.b = context;
    }

    @Override // android.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        if (this.b == null) {
            this.b = viewGroup.getContext();
        }
        if (layoutInflater == null) {
            if (getActivity() != null || getContext() != null) {
                layoutInflater = LayoutInflater.from(getActivity());
            } else if (GlobalApplicationUtils.getContext() != null) {
                layoutInflater = LayoutInflater.from(GlobalApplicationUtils.getContext());
            }
        }
        this.containerView = new FrameLayout(getContext());
        this.containerView.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
        if (layoutInflater != null) {
            if (TextUtils.isEmpty(getLayoutId())) {
                this.containerView.addView(getContentView());
            } else {
                int layoutIdByName = ResourceUtils.getLayoutIdByName(getLayoutId());
                if (layoutIdByName == 0 && GlobalApplicationUtils.getContext() == null) {
                    GlobalApplicationUtils.init(getContext().getApplicationContext());
                    layoutIdByName = ResourceUtils.getLayoutIdByName(getLayoutId());
                }
                this.containerView.addView(layoutInflater.inflate(layoutIdByName, (ViewGroup) null));
            }
        } else {
            this.containerView.addView(super.onCreateView(layoutInflater, viewGroup, bundle));
        }
        this.a = true;
        this.isPaused = false;
        return this.containerView;
    }

    @Override // android.app.Fragment
    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        if (isOpenLazyLoad()) {
            a();
        } else {
            init();
        }
    }

    protected void init() {
        if (initArgument(getArguments())) {
            a((BaseFragment<C, P>) initPresenter());
            initView();
            initData();
            initListener();
            loadData();
            return;
        }
        LogUtils.log(getTAG(), " show error ，parameter error");
        back();
    }

    @Override // android.app.Fragment
    public void onResume() {
        super.onResume();
        this.isPaused = false;
        if (!isAdded() || isHidden()) {
            return;
        }
        onVisible();
    }

    @Override // android.app.Fragment
    public void onPause() {
        super.onPause();
        this.isPaused = true;
    }

    @Override // android.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        this.isLazyLoad = false;
        this.isVisible = false;
        this.a = false;
        d();
    }

    @Override // android.app.Fragment
    public void setUserVisibleHint(boolean z) {
        super.setUserVisibleHint(z);
        this.isVisible = z;
        if (this.a) {
            if (z) {
                if (isOpenLazyLoad()) {
                    a();
                }
                onVisible();
                return;
            }
            onInvisible();
        }
    }

    @Override // android.app.Fragment
    public void onHiddenChanged(boolean z) {
        super.onHiddenChanged(z);
        this.isVisible = !z;
        if (z) {
            onInvisible();
        } else {
            onVisible();
        }
    }

    protected <T extends View> T findViewById(int i) {
        if (this.containerView == null) {
            return null;
        }
        return (T) this.containerView.findViewById(i);
    }

    protected <T extends View> T findViewById(String str) {
        return (T) findViewById(ResourceUtils.getIdByName(str));
    }

    protected int layoutId(String str) {
        return ResourceUtils.getLayoutIdByName(str);
    }

    @Override // android.app.Fragment
    public Context getContext() {
        return this.b;
    }

    protected void onVisible() {
        this.isVisible = true;
        e();
        log(PointCategory.SHOW);
    }

    protected void onInvisible() {
        log("hide");
    }

    private void a() {
        if (this.isVisible && this.a && !this.isLazyLoad) {
            this.isLazyLoad = true;
            init();
        }
    }

    public void setCallback(C c) {
        this.c = c;
    }

    protected C getCallback() {
        return this.c;
    }

    void a(MainDialog mainDialog) {
        this.d = mainDialog;
    }

    public void navigation(BaseFragment baseFragment) {
        if (this.d != null) {
            hideKeyboard();
            this.d.navigation(this, baseFragment);
        }
    }

    protected void replace(BaseFragment baseFragment) {
        if (this.d != null) {
            hideKeyboard();
            this.d.navigation(null, baseFragment);
        }
    }

    protected void back() {
        if (this.d != null) {
            hideKeyboard();
            this.d.back();
        }
    }

    public void close() {
        if (this.d != null) {
            hideKeyboard();
            this.d.dismissAllowingStateLoss();
        }
    }

    protected Window getWindow() {
        if (this.d != null) {
            return this.d.getDialog().getWindow();
        }
        return null;
    }

    protected MainDialog getDialog() {
        return this.d;
    }

    protected boolean canShowBack() {
        List<Fragment> listFindAllFragments;
        return (this.d == null || (listFindAllFragments = this.d.findAllFragments()) == null || listFindAllFragments.size() <= 1) ? false : true;
    }

    protected void hideKeyboard() {
        View viewFindFocus;
        InputMethodManager inputMethodManager;
        if (this.containerView == null || this.b == null || (viewFindFocus = this.containerView.findFocus()) == null || (inputMethodManager = (InputMethodManager) this.b.getSystemService("input_method")) == null || !inputMethodManager.isActive()) {
            return;
        }
        inputMethodManager.hideSoftInputFromWindow(viewFindFocus.getWindowToken(), 0);
    }

    public void log(String str) {
        LogUtils.log(getTAG(), str);
    }

    private void b() {
        this.e = LoadingUtils.showDialog();
    }

    private void c() {
        LoadingUtils.dismissDialog(this.e);
        this.e = null;
    }

    @Override // android.app.Fragment, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        if (this.f == configuration.orientation) {
            return;
        }
        this.f = configuration.orientation;
        if (this.f == 2) {
            screenChange2Landscape();
        } else if (this.f == 1) {
            screenChange2Portrait();
        }
    }

    protected boolean isLandscape() {
        return UIUtils.isHorizontalScreen();
    }

    private void a(P p) {
        this.mPresenter = p;
        if (this.mPresenter != null) {
            this.mPresenter.attach(this);
        }
    }

    private void d() {
        if (this.mPresenter != null) {
            this.mPresenter.detach();
            this.mPresenter = null;
        }
    }

    @Override // com.ss.union.game.sdk.common.mvp.BaseView
    public void showLoading() {
        b();
    }

    @Override // com.ss.union.game.sdk.common.mvp.BaseView
    public void hideLoading() {
        c();
    }

    @Override // com.ss.union.game.sdk.common.mvp.BaseView
    public void toast(String str) {
        ToastUtils.getInstance().toast(str);
    }

    private void e() {
        if (getDialog() != null) {
            if (isShowStatusBar()) {
                StatusBarUtils.showStatusBar(getDialog());
                StatusBarUtils.changeStatusBarColor(getDialog(), statusBarColor());
                StatusBarUtils.statusBarLightMode(getDialog(), isStatusBarDarkMode());
                return;
            }
            StatusBarUtils.hideStatusBar(getDialog());
        }
    }

    protected void fitStatusBar(View view) {
        if (view != null) {
            view.setPadding(view.getPaddingLeft(), view.getPaddingTop() + StatusBarUtils.getStatusBarHeight(), view.getPaddingRight(), view.getPaddingBottom());
        }
    }

    protected String getTAG() {
        if (this.g == null) {
            this.g = getClass().getSimpleName();
        }
        return this.g;
    }

    @Override // android.app.Fragment
    public Animator onCreateAnimator(int i, boolean z, int i2) {
        try {
            if (i2 == ResourceUtils.getAnimatorIdByName("lg_animator_slide_in_right")) {
                ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat((Object) null, AnimationProperty.TRANSLATE_X, UIUtils.getScreenWidth(), 0.0f);
                objectAnimatorOfFloat.setDuration(400L);
                return objectAnimatorOfFloat;
            }
            if (i2 == ResourceUtils.getAnimatorIdByName("lg_animator_slide_out_left")) {
                ObjectAnimator objectAnimatorOfFloat2 = ObjectAnimator.ofFloat((Object) null, AnimationProperty.TRANSLATE_X, 0.0f, -UIUtils.getScreenWidth());
                objectAnimatorOfFloat2.setDuration(400L);
                return objectAnimatorOfFloat2;
            }
            if (i2 == ResourceUtils.getAnimatorIdByName("lg_animator_slide_in_left")) {
                ObjectAnimator objectAnimatorOfFloat3 = ObjectAnimator.ofFloat((Object) null, AnimationProperty.TRANSLATE_X, -UIUtils.getScreenWidth(), 0.0f);
                objectAnimatorOfFloat3.setDuration(400L);
                return objectAnimatorOfFloat3;
            }
            if (i2 == ResourceUtils.getAnimatorIdByName("lg_animator_slide_out_right")) {
                ObjectAnimator objectAnimatorOfFloat4 = ObjectAnimator.ofFloat((Object) null, AnimationProperty.TRANSLATE_X, 0.0f, UIUtils.getScreenWidth());
                objectAnimatorOfFloat4.setDuration(400L);
                return objectAnimatorOfFloat4;
            }
            return super.onCreateAnimator(i, z, i2);
        } catch (Throwable unused) {
            return super.onCreateAnimator(i, z, i2);
        }
    }

    protected View.OnClickListener antiShake(View.OnClickListener onClickListener) {
        return new AntiShakeClickListener(onClickListener);
    }
}
