package com.ss.union.game.sdk.feedback.fragment;

import android.os.Bundle;
import android.view.View;
import androidx.annotation.Nullable;
import com.ss.union.game.sdk.common.dialog.BaseFragment;
import com.ss.union.game.sdk.feedback.view.FeedbackHeaderView;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class LGFeedbackSubmitSuccessFragment extends BaseFragment {
    private static final String a = "key_red_dot_status";
    private FeedbackHeaderView c;
    private View d;
    private View b = null;
    private boolean e = false;

    @Override // com.ss.union.game.sdk.common.dialog.BaseFragment
    protected String getLayoutId() {
        return "lg_feedback_fragment_submit_success";
    }

    @Override // com.ss.union.game.sdk.common.dialog.BaseFragment
    protected boolean initArgument(Bundle bundle) {
        return true;
    }

    @Override // com.ss.union.game.sdk.common.dialog.BaseFragment
    protected void initData() {
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
    protected void loadData() {
    }

    @Override // com.ss.union.game.sdk.common.dialog.BaseFragment
    protected String statusBarColor() {
        return "#ffffff";
    }

    public static LGFeedbackSubmitSuccessFragment a(boolean z) {
        LGFeedbackSubmitSuccessFragment lGFeedbackSubmitSuccessFragment = new LGFeedbackSubmitSuccessFragment();
        Bundle bundle = new Bundle();
        bundle.putBoolean(a, z);
        lGFeedbackSubmitSuccessFragment.setArguments(bundle);
        return lGFeedbackSubmitSuccessFragment;
    }

    @Override // android.app.Fragment
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        Bundle arguments = getArguments();
        if (arguments != null) {
            this.e = arguments.getBoolean(a, false);
        }
    }

    @Override // com.ss.union.game.sdk.common.dialog.BaseFragment
    protected void initView() {
        this.d = findViewById("lg_submit_success_container");
        this.b = findViewById("lg_view_record");
        this.c = (FeedbackHeaderView) findViewById("lg_submit_header");
        this.c.showRecord(this.e);
        fitStatusBar(this.d);
    }

    @Override // com.ss.union.game.sdk.common.dialog.BaseFragment
    protected void initListener() {
        this.c.listenClose(new View.OnClickListener() { // from class: com.ss.union.game.sdk.feedback.fragment.LGFeedbackSubmitSuccessFragment.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LGFeedbackSubmitSuccessFragment.this.close();
            }
        });
        this.c.listenBack(new View.OnClickListener() { // from class: com.ss.union.game.sdk.feedback.fragment.LGFeedbackSubmitSuccessFragment.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LGFeedbackSubmitSuccessFragment.this.back();
            }
        });
        this.c.listenJumpFeedbackRecord(new View.OnClickListener() { // from class: com.ss.union.game.sdk.feedback.fragment.LGFeedbackSubmitSuccessFragment.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LGFeedbackSubmitSuccessFragment.this.a();
            }
        });
        this.b.setOnClickListener(antiShake(new View.OnClickListener() { // from class: com.ss.union.game.sdk.feedback.fragment.LGFeedbackSubmitSuccessFragment.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LGFeedbackSubmitSuccessFragment.this.a();
            }
        }));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a() {
        this.c.showRecordRedDot(false);
        navigation(LGFeedbackRecordFragment.a());
    }
}
