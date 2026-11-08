package com.ss.union.game.sdk.feedback.fragment;

import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.annotation.Nullable;
import com.ss.android.downloadlib.applink.AppLinkMonitor;
import com.ss.union.game.sdk.common.dialog.BaseFragment;
import com.ss.union.game.sdk.common.dialog.OperationBuilder;
import com.ss.union.game.sdk.common.util.ActivityUtils;
import com.ss.union.game.sdk.common.util.KeyboardUtils;
import com.ss.union.game.sdk.common.util.LGSpannableStringUtil;
import com.ss.union.game.sdk.common.util.NetworkUtils;
import com.ss.union.game.sdk.common.util.ResourceUtils;
import com.ss.union.game.sdk.common.util.ToastUtils;
import com.ss.union.game.sdk.common.util.UIUtils;
import com.ss.union.game.sdk.common.util.WindowKeyBoardUtils;
import com.ss.union.game.sdk.core.base.view.NoNetWorkCard;
import com.ss.union.game.sdk.feedback.a.b;
import com.ss.union.game.sdk.feedback.callback.LGUserFeedbackCallback;
import com.ss.union.game.sdk.feedback.d.a;
import com.ss.union.game.sdk.feedback.module.LGFeedbackStatus;
import com.ss.union.game.sdk.feedback.module.e;
import com.ss.union.game.sdk.feedback.picture.select.c;
import com.ss.union.game.sdk.feedback.view.AddPhotoCard;
import com.ss.union.game.sdk.feedback.view.FeedbackHeaderView;
import com.xiaomi.mipush.sdk.Constants;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class LGUserFeedbackFragment extends BaseFragment {
    public static String a = "key_activity_orientation";
    private static final String b = "LGUserFeedbackFragment";
    private b D;
    private Handler F;
    private WindowKeyBoardUtils G;
    private LGUserFeedbackCallback H;
    private Activity c;
    private View d;
    private View e;
    private View f;
    private View g;
    private TextView h;
    private View i;
    private View j;
    private ScrollView k;
    private View l;
    private TextView m;
    private GridView n;
    private TextView o;
    private EditText p;
    private TextView q;
    private TextView r;
    private FeedbackHeaderView s;
    private EditText t;
    private AddPhotoCard u;
    private NoNetWorkCard v;
    private RelativeLayout w;
    private ImageView x;
    private int y;
    private boolean z = false;
    private boolean A = false;
    private boolean B = false;
    private Integer C = null;
    private int E = -1;
    private boolean I = false;

    @Override // com.ss.union.game.sdk.common.dialog.BaseFragment
    protected String getLayoutId() {
        return "";
    }

    @Override // com.ss.union.game.sdk.common.dialog.BaseFragment
    protected boolean initArgument(Bundle bundle) {
        return true;
    }

    @Override // com.ss.union.game.sdk.common.dialog.BaseFragment
    protected boolean isShowStatusBar() {
        return true;
    }

    @Override // com.ss.union.game.sdk.common.dialog.BaseFragment
    protected boolean isStatusBarDarkMode() {
        return true;
    }

    @Override // com.ss.union.game.sdk.common.dialog.BaseFragment
    protected String statusBarColor() {
        return "#ffffff";
    }

    public static void a(Activity activity, LGUserFeedbackCallback lGUserFeedbackCallback) {
        int iReplaceOrientation = ActivityUtils.replaceOrientation(activity, 1);
        Bundle bundle = new Bundle();
        bundle.putInt(a, iReplaceOrientation);
        LGUserFeedbackFragment lGUserFeedbackFragmentA = a(bundle);
        lGUserFeedbackFragmentA.a(lGUserFeedbackCallback);
        new OperationBuilder(lGUserFeedbackFragmentA).animation(OperationBuilder.Animation.NONE).cancelable(true).show();
    }

    public static LGUserFeedbackFragment a(Bundle bundle) {
        LGUserFeedbackFragment lGUserFeedbackFragment = new LGUserFeedbackFragment();
        lGUserFeedbackFragment.setArguments(bundle);
        return lGUserFeedbackFragment;
    }

    @Override // android.app.Fragment
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        HashMap map = new HashMap();
        map.put(a.a, a.b);
        a.a(map);
    }

    @Override // com.ss.union.game.sdk.common.dialog.BaseFragment, android.app.Fragment
    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        View viewInflate = layoutInflater.inflate(ResourceUtils.getLayoutIdByName("lg_fragment_user_feedback"), viewGroup, false);
        this.e = viewInflate.findViewById(ResourceUtils.getIdByName("id_user_feedback_frame"));
        this.s = (FeedbackHeaderView) viewInflate.findViewById(ResourceUtils.getIdByName("lg_feedback_header"));
        this.u = (AddPhotoCard) viewInflate.findViewById(ResourceUtils.getIdByName("lg_feedback_card_add_photo"));
        this.f = viewInflate.findViewById(ResourceUtils.getIdByName("id_user_feedback_content"));
        this.g = viewInflate.findViewById(ResourceUtils.getIdByName("id_user_feedback_body"));
        this.h = (TextView) viewInflate.findViewById(ResourceUtils.getIdByName("id_user_feedback_title"));
        this.j = viewInflate.findViewById(ResourceUtils.getIdByName("id_user_feedback_line"));
        this.k = (ScrollView) viewInflate.findViewById(ResourceUtils.getIdByName("id_user_feedback_scroll"));
        this.l = viewInflate.findViewById(ResourceUtils.getIdByName("id_user_feedback_scroll_body"));
        this.m = (TextView) viewInflate.findViewById(ResourceUtils.getIdByName("id_user_feedback_question_type_title"));
        this.n = (GridView) viewInflate.findViewById(ResourceUtils.getIdByName("id_user_feedback_question_types"));
        this.o = (TextView) viewInflate.findViewById(ResourceUtils.getIdByName("id_user_feedback_question_desc_title"));
        this.p = (EditText) viewInflate.findViewById(ResourceUtils.getIdByName("id_user_feedback_question_desc"));
        this.q = (TextView) viewInflate.findViewById(ResourceUtils.getIdByName("id_user_feedback_question_desc_words_left"));
        this.r = (TextView) viewInflate.findViewById(ResourceUtils.getIdByName("id_user_feedback_question_submit"));
        this.t = (EditText) viewInflate.findViewById(ResourceUtils.getIdByName("lg_feedback_et_input_phone"));
        this.w = (RelativeLayout) viewInflate.findViewById(ResourceUtils.getIdByName("lg_rl_loading"));
        this.x = (ImageView) viewInflate.findViewById(ResourceUtils.getIdByName("lg_iv_loading"));
        this.v = (NoNetWorkCard) viewInflate.findViewById(ResourceUtils.getIdByName("lg_feedback_no_network"));
        return viewInflate;
    }

    @Override // com.ss.union.game.sdk.common.dialog.BaseFragment, android.app.Fragment
    public void onActivityCreated(Bundle bundle) {
        this.c = getActivity();
        super.onActivityCreated(bundle);
    }

    @Override // com.ss.union.game.sdk.common.dialog.BaseFragment
    protected void loadData() {
        q();
        if (!NetworkUtils.isNetworkAvailable()) {
            o();
            return;
        }
        this.F.removeCallbacksAndMessages(null);
        this.F.postDelayed(new Runnable() { // from class: com.ss.union.game.sdk.feedback.fragment.LGUserFeedbackFragment.1
            @Override // java.lang.Runnable
            public void run() {
                LGUserFeedbackFragment.this.a((List<String>) null);
            }
        }, AppLinkMonitor.DELAY_TIME);
        j();
        k();
    }

    @Override // com.ss.union.game.sdk.common.dialog.BaseFragment
    protected void initData() {
        Bundle arguments = getArguments();
        if (arguments != null && arguments.containsKey(a)) {
            this.C = Integer.valueOf(arguments.getInt(a));
        }
        this.F = new Handler(Looper.getMainLooper());
    }

    @Override // com.ss.union.game.sdk.common.dialog.BaseFragment
    protected void initView() {
        this.d = this.c.getWindow().getDecorView();
        int color = Color.parseColor("#FF9900");
        this.m.setText(LGSpannableStringUtil.create(ResourceUtils.getString("lg_user_feedback_question_type_title")).setTextForegroundColor(color, 0, 1).makeText());
        this.o.setText(LGSpannableStringUtil.create(ResourceUtils.getString("lg_user_feedback_question_desc_title")).setTextForegroundColor(color, 0, 1).makeText());
        this.D = new b(this.c);
        this.n.setAdapter((ListAdapter) this.D);
        this.p.requestFocus();
        a();
    }

    private void a() {
        if (getDialog() == null || getDialog().getDialog() == null) {
            return;
        }
        final Window window = getDialog().getDialog().getWindow();
        this.g.getViewTreeObserver().addOnGlobalFocusChangeListener(new ViewTreeObserver.OnGlobalFocusChangeListener() { // from class: com.ss.union.game.sdk.feedback.fragment.LGUserFeedbackFragment.12
            @Override // android.view.ViewTreeObserver.OnGlobalFocusChangeListener
            public void onGlobalFocusChanged(View view, View view2) {
                if (window == null) {
                    return;
                }
                if (view2 == LGUserFeedbackFragment.this.t) {
                    window.setSoftInputMode(16);
                    LGUserFeedbackFragment.this.a(window);
                } else {
                    window.setSoftInputMode(32);
                    LGUserFeedbackFragment.this.b();
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(Window window) {
        this.G = WindowKeyBoardUtils.setListener(window, new WindowKeyBoardUtils.OnSoftKeyBoardChangeListener() { // from class: com.ss.union.game.sdk.feedback.fragment.LGUserFeedbackFragment.14
            private int b = 0;

            @Override // com.ss.union.game.sdk.common.util.WindowKeyBoardUtils.OnSoftKeyBoardChangeListener
            public void keyBoardShow(int i) {
                this.b = LGUserFeedbackFragment.this.k.getScrollY();
                LGUserFeedbackFragment.this.k.fullScroll(130);
            }

            @Override // com.ss.union.game.sdk.common.util.WindowKeyBoardUtils.OnSoftKeyBoardChangeListener
            public void keyBoardHide(int i) {
                LGUserFeedbackFragment.this.k.setScrollY(this.b);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b() {
        if (this.G != null) {
            this.G.release();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        this.B = true;
        this.s.showRecordRedDot(true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d() {
        this.B = false;
        this.s.showRecordRedDot(false);
    }

    @Override // com.ss.union.game.sdk.common.dialog.BaseFragment
    protected void initListener() {
        this.s.showRecord(true);
        this.s.listenBack(new View.OnClickListener() { // from class: com.ss.union.game.sdk.feedback.fragment.LGUserFeedbackFragment.15
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LGUserFeedbackFragment.this.back();
            }
        });
        this.s.listenClose(new View.OnClickListener() { // from class: com.ss.union.game.sdk.feedback.fragment.LGUserFeedbackFragment.16
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LGUserFeedbackFragment.this.back();
            }
        });
        this.s.listenJumpFeedbackRecord(new View.OnClickListener() { // from class: com.ss.union.game.sdk.feedback.fragment.LGUserFeedbackFragment.17
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LGUserFeedbackFragment.this.e();
                LGUserFeedbackFragment.this.B = false;
                LGUserFeedbackFragment.this.navigation(LGFeedbackRecordFragment.a());
            }
        });
        this.u.addCompressListener(new AddPhotoCard.a() { // from class: com.ss.union.game.sdk.feedback.fragment.LGUserFeedbackFragment.18
            @Override // com.ss.union.game.sdk.feedback.view.AddPhotoCard.a
            public void a(List<File> list) {
                StringBuilder sb = new StringBuilder();
                sb.append("fileCount: ");
                sb.append(list.size());
                sb.append("\n");
                Iterator<File> it = list.iterator();
                while (it.hasNext()) {
                    sb.append(it.next().getAbsolutePath());
                    sb.append("\n");
                }
                com.ss.union.game.sdk.c.a.d("压缩成功:" + sb.toString());
            }

            @Override // com.ss.union.game.sdk.feedback.view.AddPhotoCard.a
            public void b(List<Throwable> list) {
                com.ss.union.game.sdk.c.a.d("压缩失败: " + list.get(0).getMessage());
            }
        });
        this.D.a(new AdapterView.OnItemClickListener() { // from class: com.ss.union.game.sdk.feedback.fragment.LGUserFeedbackFragment.19
            @Override // android.widget.AdapterView.OnItemClickListener
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                LGUserFeedbackFragment.this.k.smoothScrollTo(0, LGUserFeedbackFragment.this.o.getTop());
                LGUserFeedbackFragment.this.h();
            }
        });
        this.p.addTextChangedListener(new TextWatcher() { // from class: com.ss.union.game.sdk.feedback.fragment.LGUserFeedbackFragment.20
            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
                LGUserFeedbackFragment.this.h();
                int length = LGUserFeedbackFragment.this.p.getText().length();
                LGUserFeedbackFragment.this.q.setText(length + "/50");
            }
        });
        this.r.setOnClickListener(antiShake(new View.OnClickListener() { // from class: com.ss.union.game.sdk.feedback.fragment.LGUserFeedbackFragment.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LGUserFeedbackFragment.this.hideKeyboard();
                LGUserFeedbackFragment.this.l();
            }
        }));
        this.r.setEnabled(false);
        this.k.getChildAt(0).setOnClickListener(antiShake(new View.OnClickListener() { // from class: com.ss.union.game.sdk.feedback.fragment.LGUserFeedbackFragment.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LGUserFeedbackFragment.this.hideKeyboard();
            }
        }));
        this.f.setOnClickListener(antiShake(new View.OnClickListener() { // from class: com.ss.union.game.sdk.feedback.fragment.LGUserFeedbackFragment.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LGUserFeedbackFragment.this.hideKeyboard();
            }
        }));
        this.v.listenRetryClick(new View.OnClickListener() { // from class: com.ss.union.game.sdk.feedback.fragment.LGUserFeedbackFragment.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LGUserFeedbackFragment.this.loadData();
            }
        });
    }

    @Override // com.ss.union.game.sdk.common.dialog.BaseFragment, android.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        this.c = null;
        if (this.C != null) {
            ActivityUtils.replaceOrientation(getActivity(), this.C.intValue());
        }
        if (this.H != null) {
            this.H.onClose(this.I);
        }
        c.a();
    }

    @Override // com.ss.union.game.sdk.common.dialog.BaseFragment, android.app.Fragment
    public void onPause() {
        super.onPause();
        e();
    }

    @Override // com.ss.union.game.sdk.common.dialog.BaseFragment, android.app.Fragment
    public void onHiddenChanged(boolean z) {
        super.onHiddenChanged(z);
        if (z) {
            return;
        }
        k();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e() {
        KeyboardUtils.hideKeyboard(this.c);
        g();
    }

    private void f() {
        if (isLandscape()) {
            return;
        }
        try {
            Animation animationLoadAnimation = AnimationUtils.loadAnimation(this.c, ResourceUtils.getAnimIdByName("slide_in_bottom"));
            animationLoadAnimation.setFillAfter(true);
            this.f.startAnimation(animationLoadAnimation);
        } catch (Throwable unused) {
        }
    }

    private void a(final Animation.AnimationListener animationListener) {
        if (!isLandscape()) {
            try {
                Animation animationLoadAnimation = AnimationUtils.loadAnimation(this.c, ResourceUtils.getAnimIdByName("slide_out_bottom"));
                animationLoadAnimation.setAnimationListener(new Animation.AnimationListener() { // from class: com.ss.union.game.sdk.feedback.fragment.LGUserFeedbackFragment.6
                    @Override // android.view.animation.Animation.AnimationListener
                    public void onAnimationRepeat(Animation animation) {
                    }

                    @Override // android.view.animation.Animation.AnimationListener
                    public void onAnimationStart(Animation animation) {
                    }

                    @Override // android.view.animation.Animation.AnimationListener
                    public void onAnimationEnd(Animation animation) {
                        animationListener.onAnimationEnd(null);
                    }
                });
                animationLoadAnimation.setFillAfter(true);
                this.f.startAnimation(animationLoadAnimation);
                Animation animationLoadAnimation2 = AnimationUtils.loadAnimation(this.c, ResourceUtils.getAnimIdByName("demo_alpha_out"));
                animationLoadAnimation2.setFillAfter(true);
                this.e.startAnimation(animationLoadAnimation2);
                return;
            } catch (Throwable unused) {
                animationListener.onAnimationEnd(null);
                return;
            }
        }
        animationListener.onAnimationEnd(null);
    }

    private void a(int i) {
        if (isLandscape()) {
            a(0.0f, -(i / 2), 0, 0);
        } else {
            this.y = this.g.getMeasuredHeight();
            a(0.0f, -(i - UIUtils.dip2Px(8.0f)), this.y, UIUtils.dip2Px(357.0f));
        }
    }

    private void g() {
        if (isLandscape()) {
            a(this.f.getTranslationY(), 0.0f, 0, 0);
        } else {
            a(this.f.getTranslationY(), 0.0f, this.g.getMeasuredHeight(), this.y);
        }
    }

    private void a(final float f, float f2, final int i, final int i2) {
        if (this.k != null) {
            this.k.fullScroll(130);
        }
        final int[] iArr = new int[2];
        final float f3 = f2 - f;
        final int i3 = i2 - i;
        ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(0.0f, 1.0f);
        valueAnimatorOfFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.ss.union.game.sdk.feedback.fragment.LGUserFeedbackFragment.7
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                try {
                    iArr[1] = 0;
                    if (LGUserFeedbackFragment.this.d != null) {
                        LGUserFeedbackFragment.this.d.getLocationOnScreen(iArr);
                    }
                    if (iArr[1] != 0) {
                        valueAnimator.cancel();
                        return;
                    }
                    float fFloatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                    LGUserFeedbackFragment.this.f.setTranslationY(f + (f3 * fFloatValue));
                    if (i2 != 0 && i != i2) {
                        ViewGroup.LayoutParams layoutParams = LGUserFeedbackFragment.this.g.getLayoutParams();
                        layoutParams.height = (int) (i + (i3 * fFloatValue));
                        LGUserFeedbackFragment.this.g.setLayoutParams(layoutParams);
                    }
                } catch (Throwable unused) {
                }
            }
        });
        valueAnimatorOfFloat.setDuration(100L);
        valueAnimatorOfFloat.start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void h() {
        if (this.D.a() != -1 && this.p.getText().length() > 0) {
            this.r.setEnabled(true);
            this.r.setTextColor(Color.parseColor("#333333"));
            this.r.setBackgroundResource(ResourceUtils.getDrawableIdByName("lg_bg_user_feedback_submit_enable"));
        } else {
            this.r.setEnabled(false);
            this.r.setTextColor(-1);
            this.r.setBackgroundResource(ResourceUtils.getDrawableIdByName("lg_bg_user_feedback_submit_disable"));
        }
    }

    private void i() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(ResourceUtils.getString("lg_user_feedback_question_type_1"));
        arrayList.add(ResourceUtils.getString("lg_user_feedback_question_type_2"));
        arrayList.add(ResourceUtils.getString("lg_user_feedback_question_type_3"));
        arrayList.add(ResourceUtils.getString("lg_user_feedback_question_type_4"));
        a(arrayList);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(List<String> list) {
        if (this.D.getCount() > 0) {
            return;
        }
        if (list == null || list.size() <= 0) {
            i();
            return;
        }
        if (this.D != null) {
            this.D.a(list);
        }
        p();
    }

    @Override // com.ss.union.game.sdk.common.dialog.BaseFragment, android.app.Fragment, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
    }

    private void j() {
        com.ss.union.game.sdk.feedback.c.a.a(new com.ss.union.game.sdk.feedback.c.b<e>() { // from class: com.ss.union.game.sdk.feedback.fragment.LGUserFeedbackFragment.8
            @Override // com.ss.union.game.sdk.feedback.c.b
            public void a(int i, String str) {
                com.ss.union.game.sdk.c.a.d("GetQuestionTypes error: code= " + i + " msg= " + str);
                LGUserFeedbackFragment.this.s();
                LGUserFeedbackFragment.this.hideLoading();
                LGUserFeedbackFragment.this.a((List<String>) null);
            }

            @Override // com.ss.union.game.sdk.feedback.c.b
            public void a(e eVar) {
                com.ss.union.game.sdk.c.a.d("GetQuestionTypes success: " + eVar.toString());
                LGUserFeedbackFragment.this.s();
                LGUserFeedbackFragment.this.hideLoading();
                LGUserFeedbackFragment.this.a(eVar.a());
            }
        });
    }

    private void k() {
        com.ss.union.game.sdk.feedback.c.a.b(new com.ss.union.game.sdk.feedback.c.b<LGFeedbackStatus>() { // from class: com.ss.union.game.sdk.feedback.fragment.LGUserFeedbackFragment.9
            @Override // com.ss.union.game.sdk.feedback.c.b
            public void a(int i, String str) {
                com.ss.union.game.sdk.c.a.d("getFeedbackStatusFail: " + i + ",msg=" + str);
                LGUserFeedbackFragment.this.d();
            }

            @Override // com.ss.union.game.sdk.feedback.c.b
            public void a(LGFeedbackStatus lGFeedbackStatus) {
                com.ss.union.game.sdk.c.a.d("getFeedbackStatusSuccess: " + lGFeedbackStatus);
                if (lGFeedbackStatus == null || lGFeedbackStatus.unreadReplyCount <= 0) {
                    LGUserFeedbackFragment.this.d();
                } else {
                    LGUserFeedbackFragment.this.c();
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void l() {
        final String strTrim = this.p.getText().toString().trim();
        int length = strTrim.length();
        if (length < 10) {
            a(0, ResourceUtils.getString("lg_user_feedback_submit_error_words_limit"));
            return;
        }
        if (length > 50) {
            a(0, ResourceUtils.getString("lg_user_feedback_submit_error_words_over_limit"));
            return;
        }
        final String strB = this.D.b();
        if (strB == null) {
            a(0, ResourceUtils.getString("lg_user_feedback_submit_error_type_not_selected"));
            return;
        }
        showLoading();
        final String strTrim2 = this.t != null ? this.t.getText().toString().replace(" ", "").trim() : "";
        if (!this.u.containsPicture()) {
            a(strB, strTrim, strTrim2, "");
            return;
        }
        this.u.asyncWaitCompressResult(new AddPhotoCard.a() { // from class: com.ss.union.game.sdk.feedback.fragment.LGUserFeedbackFragment.10
            @Override // com.ss.union.game.sdk.feedback.view.AddPhotoCard.a
            public void a(List<File> list) {
                if (list == null || list.isEmpty()) {
                    LGUserFeedbackFragment.this.a(strB, strTrim, strTrim2, "");
                } else {
                    LGUserFeedbackFragment.this.a(strB, strTrim, strTrim2, list);
                }
            }

            @Override // com.ss.union.game.sdk.feedback.view.AddPhotoCard.a
            public void b(List<Throwable> list) {
                LGUserFeedbackFragment.this.hideLoading();
                LGUserFeedbackFragment.this.m();
                LGUserFeedbackFragment.this.u.reset();
            }
        });
        HashMap map = new HashMap();
        map.put(a.d, a.e);
        a.a(map);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String str, String str2, String str3, String str4) {
        com.ss.union.game.sdk.feedback.c.a.a(str, str2, str3, str4, new com.ss.union.game.sdk.feedback.c.b() { // from class: com.ss.union.game.sdk.feedback.fragment.LGUserFeedbackFragment.11
            @Override // com.ss.union.game.sdk.feedback.c.b
            public void a(int i, String str5) {
                com.ss.union.game.sdk.c.a.d("submitUserFeedbackFail: " + i + ",msg=" + str5);
                LGUserFeedbackFragment.this.s();
                LGUserFeedbackFragment.this.hideLoading();
                LGUserFeedbackFragment.this.a(false);
                LGUserFeedbackFragment.this.b(i);
            }

            @Override // com.ss.union.game.sdk.feedback.c.b
            public void a(Object obj) {
                com.ss.union.game.sdk.c.a.d("submitUserFeedbackSuccess ");
                LGUserFeedbackFragment.this.s();
                LGUserFeedbackFragment.this.hideLoading();
                LGUserFeedbackFragment.this.I = true;
                LGUserFeedbackFragment.this.n();
                LGUserFeedbackFragment.this.replace(LGFeedbackSubmitSuccessFragment.a(LGUserFeedbackFragment.this.B));
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(final String str, final String str2, final String str3, List<File> list) {
        com.ss.union.game.sdk.feedback.c.a.a(list, new com.ss.union.game.sdk.feedback.c.b<List<com.ss.union.game.sdk.feedback.module.b>>() { // from class: com.ss.union.game.sdk.feedback.fragment.LGUserFeedbackFragment.13
            @Override // com.ss.union.game.sdk.feedback.c.b
            public void a(int i, String str4) {
                LGUserFeedbackFragment.this.hideLoading();
                LGUserFeedbackFragment.this.m();
                LGUserFeedbackFragment.this.b(i);
            }

            @Override // com.ss.union.game.sdk.feedback.c.b
            public void a(List<com.ss.union.game.sdk.feedback.module.b> list2) {
                if (list2 == null || list2.isEmpty()) {
                    LGUserFeedbackFragment.this.hideLoading();
                    LGUserFeedbackFragment.this.m();
                    return;
                }
                String[] strArr = new String[list2.size()];
                for (int i = 0; i < list2.size(); i++) {
                    strArr[i] = list2.get(i).a;
                }
                LGUserFeedbackFragment.this.a(str, str2, str3, LGUserFeedbackFragment.this.a(strArr));
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String a(String[] strArr) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < strArr.length; i++) {
            sb.append(strArr[i]);
            if (i < strArr.length - 1) {
                sb.append(Constants.ACCEPT_TIME_SEPARATOR_SP);
            }
        }
        return sb.toString();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(boolean z) {
        int drawableIdByName;
        String string;
        try {
            if (z) {
                drawableIdByName = ResourceUtils.getDrawableIdByName("lg_user_feedback_submit_result_success");
                string = ResourceUtils.getString("lg_user_feedback_submit_result_toast_success");
            } else {
                drawableIdByName = ResourceUtils.getDrawableIdByName("lg_user_feedback_submit_result_failure");
                string = ResourceUtils.getString("lg_user_feedback_submit_result_toast_failure");
            }
            a(drawableIdByName, string);
        } catch (Throwable unused) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void m() {
        try {
            a(ResourceUtils.getDrawableIdByName("lg_user_feedback_submit_result_failure"), ResourceUtils.getString("lg_user_feedback_submit_picture_result_toast_failure"));
        } catch (Throwable unused) {
        }
    }

    private void a(int i, String str) {
        try {
            View viewInflate = View.inflate(this.c, ResourceUtils.getLayoutIdByName("lg_user_feedback_submit_result_toast"), null);
            ImageView imageView = (ImageView) viewInflate.findViewById(ResourceUtils.getIdByName("lg_user_feedback_submit_result_toast_icon"));
            TextView textView = (TextView) viewInflate.findViewById(ResourceUtils.getIdByName("lg_user_feedback_submit_result_toast_content"));
            imageView.setImageResource(i);
            textView.setText(str);
            ToastUtils.getInstance().toast(new ToastUtils.Builder().view(viewInflate).toast("placeHolder"));
        } catch (Throwable unused) {
        }
    }

    @Override // android.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        if (this.F != null) {
            this.F.removeCallbacksAndMessages(null);
        }
        if (this.x != null) {
            this.x.clearAnimation();
        }
        b();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(int i) {
        HashMap map = new HashMap();
        map.put(a.d, a.e);
        map.put(a.g, a.i);
        map.put(a.l, i + "");
        a.a(map);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void n() {
        HashMap map = new HashMap();
        map.put(a.d, a.e);
        map.put(a.g, a.h);
        a.a(map);
    }

    private void o() {
        this.g.setVisibility(8);
        this.w.setVisibility(8);
        this.v.setVisibility(0);
        s();
    }

    private void p() {
        this.w.setVisibility(8);
        this.v.setVisibility(8);
        this.g.setVisibility(0);
        s();
    }

    private void q() {
        this.v.setVisibility(8);
        this.g.setVisibility(8);
        this.w.setVisibility(0);
        r();
    }

    private void r() {
        RotateAnimation rotateAnimation = new RotateAnimation(0.0f, 359.0f, 1, 0.5f, 1, 0.5f);
        rotateAnimation.setInterpolator(new LinearInterpolator());
        rotateAnimation.setDuration(1000L);
        rotateAnimation.setRepeatCount(-1);
        rotateAnimation.setFillAfter(true);
        rotateAnimation.setStartOffset(10L);
        this.x.startAnimation(rotateAnimation);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void s() {
        this.x.clearAnimation();
    }

    public void a(LGUserFeedbackCallback lGUserFeedbackCallback) {
        this.H = lGUserFeedbackCallback;
    }
}
