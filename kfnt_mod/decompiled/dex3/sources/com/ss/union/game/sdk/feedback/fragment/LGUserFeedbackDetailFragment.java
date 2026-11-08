package com.ss.union.game.sdk.feedback.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.ss.union.game.sdk.common.dialog.BaseFragment;
import com.ss.union.game.sdk.common.util.AntiShakeClickListener;
import com.ss.union.game.sdk.common.util.NetworkUtils;
import com.ss.union.game.sdk.common.util.ResourceUtils;
import com.ss.union.game.sdk.common.util.ToastUtils;
import com.ss.union.game.sdk.core.base.view.NoNetWorkCard;
import com.ss.union.game.sdk.feedback.a.a;
import com.ss.union.game.sdk.feedback.c.b;
import com.ss.union.game.sdk.feedback.module.d;
import com.ss.union.game.sdk.feedback.view.FeedbackHeaderView;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class LGUserFeedbackDetailFragment extends BaseFragment implements View.OnClickListener {
    public static final String a = "key_feedback_id";
    private static final String c = "Feedback";
    private View d;
    private FeedbackHeaderView e;
    private ListView f;
    private LinearLayout g;
    private ImageView h;
    private LinearLayout i;
    private ImageView j;
    private TextView k;
    private NoNetWorkCard l;
    private LinearLayout m;
    private LinearLayout n;
    private long o;
    private a p;
    public Runnable b = new Runnable() { // from class: com.ss.union.game.sdk.feedback.fragment.LGUserFeedbackDetailFragment.1
        @Override // java.lang.Runnable
        public void run() {
            LGUserFeedbackDetailFragment.this.a(true);
        }
    };
    private long q = 0;

    @Override // com.ss.union.game.sdk.common.dialog.BaseFragment
    protected String getLayoutId() {
        return "lg_fragment_user_feedback_detail";
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
    protected String statusBarColor() {
        return "#FFFFFF";
    }

    public static LGUserFeedbackDetailFragment a(Bundle bundle) {
        LGUserFeedbackDetailFragment lGUserFeedbackDetailFragment = new LGUserFeedbackDetailFragment();
        lGUserFeedbackDetailFragment.setArguments(bundle);
        return lGUserFeedbackDetailFragment;
    }

    @Override // com.ss.union.game.sdk.common.dialog.BaseFragment
    protected void initView() {
        this.d = findViewById("lg_feedback_detail_root_view");
        this.e = (FeedbackHeaderView) findViewById("lg_feedback_header_view");
        this.f = (ListView) findViewById("user_feedback_detail_list_view");
        this.g = (LinearLayout) findViewById("user_feedback_detail_thumb_up_layout");
        this.h = (ImageView) findViewById("user_feedback_detail_thumb_up_image");
        this.i = (LinearLayout) findViewById("user_feedback_detail_thumb_down_layout");
        this.j = (ImageView) findViewById("user_feedback_detail_thumb_down_image");
        this.k = (TextView) findViewById("user_feedback_detail_leave_a_message");
        this.l = (NoNetWorkCard) findViewById("lg_feedback_card_no_network");
        this.m = (LinearLayout) findViewById("lg_feedback_detail_main_container");
        this.n = (LinearLayout) findViewById("lg_feedback_detail_tools_layout");
        fitStatusBar(this.d);
    }

    @Override // com.ss.union.game.sdk.common.dialog.BaseFragment
    protected void initData() {
        if (getArguments() != null) {
            this.o = getArguments().getLong(a, -1L);
        }
    }

    @Override // com.ss.union.game.sdk.common.dialog.BaseFragment
    protected void initListener() {
        this.e.listenBack(new View.OnClickListener() { // from class: com.ss.union.game.sdk.feedback.fragment.LGUserFeedbackDetailFragment.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LGUserFeedbackDetailFragment.this.back();
            }
        });
        this.e.listenClose(new View.OnClickListener() { // from class: com.ss.union.game.sdk.feedback.fragment.LGUserFeedbackDetailFragment.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LGUserFeedbackDetailFragment.this.close();
            }
        });
        this.g.setOnClickListener(new AntiShakeClickListener(this));
        this.i.setOnClickListener(new AntiShakeClickListener(this));
        this.k.setOnClickListener(new AntiShakeClickListener(this));
        this.l.listenRetryClick(new View.OnClickListener() { // from class: com.ss.union.game.sdk.feedback.fragment.LGUserFeedbackDetailFragment.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LGUserFeedbackDetailFragment.this.a(false);
            }
        });
    }

    @Override // com.ss.union.game.sdk.common.dialog.BaseFragment
    protected void loadData() {
        a(false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(final boolean z) {
        if (!NetworkUtils.isNetworkAvailable()) {
            this.l.setVisibility(0);
            this.m.setVisibility(8);
        } else {
            this.l.setVisibility(8);
            this.m.setVisibility(0);
            showLoading();
            com.ss.union.game.sdk.feedback.c.a.a(this.o, new b<d>() { // from class: com.ss.union.game.sdk.feedback.fragment.LGUserFeedbackDetailFragment.5
                @Override // com.ss.union.game.sdk.feedback.c.b
                public void a(int i, String str) {
                    com.ss.union.game.sdk.c.a.d("获取用户反馈详情失败，code = " + i + "---message = " + str);
                    LGUserFeedbackDetailFragment.this.n.setVisibility(8);
                    ToastUtils.getInstance().toast(str);
                    LGUserFeedbackDetailFragment.this.hideLoading();
                }

                @Override // com.ss.union.game.sdk.feedback.c.b
                public void a(d dVar) {
                    LGUserFeedbackDetailFragment.this.a(z, dVar);
                    LGUserFeedbackDetailFragment.this.b(dVar);
                    LGUserFeedbackDetailFragment.this.hideLoading();
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(boolean z, d dVar) {
        a(dVar);
        a(this.p, dVar);
        if (z) {
            this.f.setSelection(this.p.getCount() - 1);
        }
    }

    private void a(d dVar) {
        if (this.p == null) {
            this.p = new a(dVar);
            this.f.setAdapter((ListAdapter) this.p);
        } else {
            this.p.a(dVar);
        }
    }

    private void a(final a aVar, d dVar) {
        int i = 0;
        if (dVar.i != null) {
            int i2 = 0;
            while (true) {
                if (i2 >= dVar.i.size()) {
                    break;
                }
                if (dVar.i.get(i2).f) {
                    i = i2 + 1;
                    break;
                }
                i2++;
            }
        }
        if (i == 0) {
            return;
        }
        this.f.setSelection(i);
        this.f.postDelayed(new Runnable() { // from class: com.ss.union.game.sdk.feedback.fragment.LGUserFeedbackDetailFragment.6
            @Override // java.lang.Runnable
            public void run() {
                aVar.a();
            }
        }, 2000L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(d dVar) {
        this.n.setVisibility(0);
        switch (dVar.g) {
            case 1:
                d();
                break;
            case 2:
                e();
                break;
            default:
                f();
                break;
        }
    }

    private boolean a() {
        if (System.currentTimeMillis() - this.q < 2000) {
            return false;
        }
        this.q = System.currentTimeMillis();
        return true;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view == this.g) {
            if (a()) {
                b();
                return;
            } else {
                ToastUtils.getInstance().toast(ResourceUtils.getString("lg_common_operating_frequency"));
                return;
            }
        }
        if (view == this.i) {
            if (a()) {
                c();
                return;
            } else {
                ToastUtils.getInstance().toast(ResourceUtils.getString("lg_common_operating_frequency"));
                return;
            }
        }
        if (view == this.k) {
            LGFeedbackLeaveMessageFragment.a(this.o, this.b);
        }
    }

    private void b() {
        if (!NetworkUtils.isNetworkAvailable()) {
            ToastUtils.getInstance().toast(ResourceUtils.getString("error_no_network"));
            return;
        }
        d();
        ToastUtils.getInstance().toast(ResourceUtils.getString("lg_feedback_thanks"));
        b(true);
    }

    private void c() {
        if (!NetworkUtils.isNetworkAvailable()) {
            ToastUtils.getInstance().toast(ResourceUtils.getString("error_no_network"));
            return;
        }
        e();
        ToastUtils.getInstance().toast(ResourceUtils.getString("lg_feedback_thanks"));
        b(false);
    }

    private void d() {
        this.h.setImageResource(ResourceUtils.getDrawableIdByName("lg_thumb_up_light"));
        this.j.setImageResource(ResourceUtils.getDrawableIdByName("lg_thumb_down_gray"));
        this.g.setEnabled(false);
        this.i.setEnabled(true);
    }

    private void e() {
        this.j.setImageResource(ResourceUtils.getDrawableIdByName("lg_thumb_down_light"));
        this.h.setImageResource(ResourceUtils.getDrawableIdByName("lg_thumb_up_gray"));
        this.g.setEnabled(true);
        this.i.setEnabled(false);
    }

    private void f() {
        this.h.setImageResource(ResourceUtils.getDrawableIdByName("lg_thumb_up_gray"));
        this.j.setImageResource(ResourceUtils.getDrawableIdByName("lg_thumb_down_gray"));
        this.g.setEnabled(true);
        this.i.setEnabled(true);
    }

    private void b(boolean z) {
        com.ss.union.game.sdk.feedback.c.a.a(this.o, z, new b<Boolean>() { // from class: com.ss.union.game.sdk.feedback.fragment.LGUserFeedbackDetailFragment.7
            @Override // com.ss.union.game.sdk.feedback.c.b
            public void a(int i, String str) {
            }

            @Override // com.ss.union.game.sdk.feedback.c.b
            public void a(Boolean bool) {
            }
        });
    }
}
