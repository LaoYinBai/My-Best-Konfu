package com.ss.union.game.sdk.feedback.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import com.ss.union.game.sdk.common.dialog.BaseFragment;
import com.ss.union.game.sdk.common.dialog.OperationBuilder;
import com.ss.union.game.sdk.common.util.AntiShakeClickListener;
import com.ss.union.game.sdk.common.util.KeyboardUtils;
import com.ss.union.game.sdk.common.util.NetworkUtils;
import com.ss.union.game.sdk.common.util.ResourceUtils;
import com.ss.union.game.sdk.common.util.ToastUtils;
import com.ss.union.game.sdk.common.util.logger.LogUtils;
import com.ss.union.game.sdk.feedback.c.a;
import com.ss.union.game.sdk.feedback.c.b;
import com.ss.union.game.sdk.feedback.view.AddPhotoCard;
import java.io.File;
import java.util.HashMap;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class LGFeedbackLeaveMessageFragment extends BaseFragment implements View.OnClickListener {
    private static final int a = 3;
    private View b;
    private ImageView c;
    private EditText d;
    private TextView e;
    private TextView f;
    private AddPhotoCard g;
    private long h;
    private Runnable i;

    @Override // com.ss.union.game.sdk.common.dialog.BaseFragment
    protected String getLayoutId() {
        return "lg_fragment_feedback_leave_message";
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
    protected void loadData() {
    }

    public static void a(long j, Runnable runnable) {
        Bundle bundle = new Bundle();
        bundle.putLong(LGUserFeedbackDetailFragment.a, j);
        LGFeedbackLeaveMessageFragment lGFeedbackLeaveMessageFragmentA = a(bundle);
        lGFeedbackLeaveMessageFragmentA.a(runnable);
        new OperationBuilder(lGFeedbackLeaveMessageFragmentA).animation(OperationBuilder.Animation.BOTTOM).cancelable(true).show();
    }

    public static LGFeedbackLeaveMessageFragment a(Bundle bundle) {
        LGFeedbackLeaveMessageFragment lGFeedbackLeaveMessageFragment = new LGFeedbackLeaveMessageFragment();
        lGFeedbackLeaveMessageFragment.setArguments(bundle);
        return lGFeedbackLeaveMessageFragment;
    }

    public void a(Runnable runnable) {
        this.i = runnable;
    }

    @Override // com.ss.union.game.sdk.common.dialog.BaseFragment
    protected void initView() {
        this.b = findViewById("lg_feedback_leave_message_root_view");
        this.c = (ImageView) findViewById("lg_feedback_leave_message_iv_close");
        this.d = (EditText) findViewById("lg_feedback_leave_message_et");
        this.e = (TextView) findViewById("lg_feedback_leave_message_text_num");
        this.f = (TextView) findViewById("lg_feedback_leave_message_submit_btn");
        this.g = (AddPhotoCard) findViewById("lg_feedback_leave_message_add_photo_card");
        fitStatusBar(this.b);
    }

    @Override // com.ss.union.game.sdk.common.dialog.BaseFragment
    protected void initData() {
        if (getArguments() != null) {
            this.h = getArguments().getLong(LGUserFeedbackDetailFragment.a, 0L);
        }
    }

    @Override // com.ss.union.game.sdk.common.dialog.BaseFragment
    protected void initListener() {
        this.b.setOnClickListener(this);
        this.c.setOnClickListener(this);
        this.f.setOnClickListener(new AntiShakeClickListener(this));
        this.d.setFocusable(true);
        this.d.setFocusableInTouchMode(true);
        this.d.requestFocus();
        this.d.postDelayed(new Runnable() { // from class: com.ss.union.game.sdk.feedback.fragment.LGFeedbackLeaveMessageFragment.1
            @Override // java.lang.Runnable
            public void run() {
                KeyboardUtils.showKeyboard(LGFeedbackLeaveMessageFragment.this.d.getContext());
            }
        }, 100L);
        this.d.addTextChangedListener(new TextWatcher() { // from class: com.ss.union.game.sdk.feedback.fragment.LGFeedbackLeaveMessageFragment.2
            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
                LGFeedbackLeaveMessageFragment.this.a(editable.toString());
            }
        });
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view == this.c) {
            back();
        } else if (view == this.f) {
            c();
        } else if (view == this.b) {
            KeyboardUtils.hideKeyboard(getContext(), this.d);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.ss.union.game.sdk.common.dialog.BaseFragment
    public void back() {
        KeyboardUtils.hideKeyboard(getContext(), this.d);
        super.back();
    }

    /* JADX INFO: Access modifiers changed from: private */
    @SuppressLint({"SetTextI18n"})
    public void a(String str) {
        this.e.setText(str.length() + "/50");
    }

    private String a() {
        return this.d.getText().toString().trim();
    }

    private boolean b() {
        if (a().length() < 3) {
            ToastUtils.getInstance().toast(ResourceUtils.getString("lg_feedback_min_input_num"));
            return true;
        }
        if (NetworkUtils.isNetworkAvailable()) {
            return false;
        }
        ToastUtils.getInstance().toast(ResourceUtils.getString("error_no_network"));
        return true;
    }

    private void c() {
        if (b()) {
            return;
        }
        showLoading();
        if (!this.g.containsPicture()) {
            a((String[]) null);
            return;
        }
        this.g.asyncWaitCompressResult(new AddPhotoCard.a() { // from class: com.ss.union.game.sdk.feedback.fragment.LGFeedbackLeaveMessageFragment.3
            @Override // com.ss.union.game.sdk.feedback.view.AddPhotoCard.a
            public void a(List<File> list) {
                a.a(list, new b<List<com.ss.union.game.sdk.feedback.module.b>>() { // from class: com.ss.union.game.sdk.feedback.fragment.LGFeedbackLeaveMessageFragment.3.1
                    @Override // com.ss.union.game.sdk.feedback.c.b
                    public void a(int i, String str) {
                        LGFeedbackLeaveMessageFragment.this.a(i);
                    }

                    @Override // com.ss.union.game.sdk.feedback.c.b
                    public void a(List<com.ss.union.game.sdk.feedback.module.b> list2) {
                        String[] strArr = new String[list2.size()];
                        for (int i = 0; i < list2.size(); i++) {
                            strArr[i] = list2.get(i).a;
                        }
                        LGFeedbackLeaveMessageFragment.this.a(strArr);
                    }
                });
            }

            @Override // com.ss.union.game.sdk.feedback.view.AddPhotoCard.a
            public void b(List<Throwable> list) {
                LGFeedbackLeaveMessageFragment.this.hideLoading();
                LGFeedbackLeaveMessageFragment.this.d();
                LGFeedbackLeaveMessageFragment.this.g.reset();
            }
        });
        HashMap map = new HashMap();
        map.put(com.ss.union.game.sdk.feedback.d.a.d, com.ss.union.game.sdk.feedback.d.a.f);
        com.ss.union.game.sdk.feedback.d.a.a(map);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String[] strArr) {
        a.a(this.h, a(), strArr, new b<Boolean>() { // from class: com.ss.union.game.sdk.feedback.fragment.LGFeedbackLeaveMessageFragment.4
            @Override // com.ss.union.game.sdk.feedback.c.b
            public void a(int i, String str) {
                LGFeedbackLeaveMessageFragment.this.a(i);
            }

            @Override // com.ss.union.game.sdk.feedback.c.b
            public void a(Boolean bool) {
                LGFeedbackLeaveMessageFragment.this.hideLoading();
                if (bool.booleanValue()) {
                    ToastUtils.getInstance().toast(ResourceUtils.getString("lg_feedback_leave_message_success"));
                    HashMap map = new HashMap();
                    map.put(com.ss.union.game.sdk.feedback.d.a.d, com.ss.union.game.sdk.feedback.d.a.f);
                    map.put(com.ss.union.game.sdk.feedback.d.a.g, com.ss.union.game.sdk.feedback.d.a.j);
                    com.ss.union.game.sdk.feedback.d.a.a(map);
                    if (LGFeedbackLeaveMessageFragment.this.i != null) {
                        LGFeedbackLeaveMessageFragment.this.i.run();
                    }
                    LGFeedbackLeaveMessageFragment.this.back();
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i) {
        hideLoading();
        a(false);
        b(i);
    }

    private void b(int i) {
        HashMap map = new HashMap();
        map.put(com.ss.union.game.sdk.feedback.d.a.d, com.ss.union.game.sdk.feedback.d.a.f);
        map.put(com.ss.union.game.sdk.feedback.d.a.g, com.ss.union.game.sdk.feedback.d.a.k);
        map.put(com.ss.union.game.sdk.feedback.d.a.l, i + "");
        com.ss.union.game.sdk.feedback.d.a.a(map);
    }

    private void a(boolean z) {
        int drawableIdByName;
        String string;
        try {
            if (z) {
                drawableIdByName = ResourceUtils.getDrawableIdByName("lg_user_feedback_submit_result_success");
                string = ResourceUtils.getString("lg_feedback_leave_message_success");
            } else {
                drawableIdByName = ResourceUtils.getDrawableIdByName("lg_user_feedback_submit_result_failure");
                string = ResourceUtils.getString("lg_feedback_leave_message_failure");
            }
            a(drawableIdByName, string);
        } catch (Throwable unused) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d() {
        try {
            a(ResourceUtils.getDrawableIdByName("lg_user_feedback_submit_result_failure"), ResourceUtils.getString("lg_user_feedback_submit_picture_result_toast_failure"));
        } catch (Throwable unused) {
        }
    }

    private void a(int i, String str) {
        try {
            if (getActivity() != null && !getActivity().isFinishing()) {
                View viewInflate = View.inflate(getActivity(), ResourceUtils.getLayoutIdByName("lg_user_feedback_submit_result_toast"), null);
                ImageView imageView = (ImageView) viewInflate.findViewById(ResourceUtils.getIdByName("lg_user_feedback_submit_result_toast_icon"));
                TextView textView = (TextView) viewInflate.findViewById(ResourceUtils.getIdByName("lg_user_feedback_submit_result_toast_content"));
                imageView.setImageResource(i);
                textView.setText(str);
                ToastUtils.getInstance().toast(new ToastUtils.Builder().view(viewInflate).toast("placeHolder"));
                return;
            }
            LogUtils.log("activity_is_null_or_finishing");
        } catch (Throwable unused) {
        }
    }
}
