package com.ss.union.game.sdk.feedback.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import androidx.annotation.Nullable;
import com.ss.union.game.sdk.common.dialog.BaseFragment;
import com.ss.union.game.sdk.common.util.DateUtils;
import com.ss.union.game.sdk.common.util.ResourceUtils;
import com.ss.union.game.sdk.core.base.view.NoNetWorkCard;
import com.ss.union.game.sdk.feedback.module.c;
import com.ss.union.game.sdk.feedback.view.FeedbackHeaderView;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class LGFeedbackRecordFragment extends BaseFragment {
    private static final String a = "Feedback";
    private ViewGroup b;
    private FeedbackHeaderView c;
    private ListView d;
    private ViewGroup e;
    private NoNetWorkCard f;
    private a g;

    @Override // com.ss.union.game.sdk.common.dialog.BaseFragment
    protected String getLayoutId() {
        return "lg_feedback_fragment_record";
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
    protected String statusBarColor() {
        return "#ffffff";
    }

    public static LGFeedbackRecordFragment a() {
        return new LGFeedbackRecordFragment();
    }

    @Override // android.app.Fragment
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        HashMap map = new HashMap();
        map.put(com.ss.union.game.sdk.feedback.d.a.a, com.ss.union.game.sdk.feedback.d.a.c);
        com.ss.union.game.sdk.feedback.d.a.a(map);
    }

    @Override // com.ss.union.game.sdk.common.dialog.BaseFragment
    protected void initView() {
        this.b = (ViewGroup) findViewById("lg_feedback_record_container");
        this.c = (FeedbackHeaderView) findViewById("lg_feedback_record_header");
        this.d = (ListView) findViewById("lg_feedback_record_list");
        this.e = (ViewGroup) findViewById("lg_feedback_record_empty");
        this.f = (NoNetWorkCard) findViewById("lg_feedback_card_no_network");
        this.g = new a(new ArrayList());
        this.d.setAdapter((ListAdapter) this.g);
        fitStatusBar(this.b);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b() {
        hideLoading();
        this.d.setVisibility(8);
        this.e.setVisibility(8);
        this.f.setVisibility(0);
        this.f.listenRetryClick(new View.OnClickListener() { // from class: com.ss.union.game.sdk.feedback.fragment.LGFeedbackRecordFragment.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LGFeedbackRecordFragment.this.loadData();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        hideLoading();
        this.d.setVisibility(8);
        this.f.setVisibility(8);
        this.e.setVisibility(0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(List<c> list) {
        hideLoading();
        this.f.setVisibility(8);
        this.e.setVisibility(8);
        this.d.setVisibility(0);
        this.d.setVerticalScrollBarEnabled(false);
        a(list);
    }

    public void a(List<c> list) {
        if (this.g != null) {
            this.g.a(list);
        }
    }

    @Override // com.ss.union.game.sdk.common.dialog.BaseFragment
    protected void initListener() {
        this.c.listenBack(new View.OnClickListener() { // from class: com.ss.union.game.sdk.feedback.fragment.LGFeedbackRecordFragment.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LGFeedbackRecordFragment.this.back();
            }
        });
        this.c.listenClose(new View.OnClickListener() { // from class: com.ss.union.game.sdk.feedback.fragment.LGFeedbackRecordFragment.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LGFeedbackRecordFragment.this.close();
            }
        });
    }

    @Override // com.ss.union.game.sdk.common.dialog.BaseFragment
    protected void loadData() {
        showLoading();
        com.ss.union.game.sdk.feedback.c.a.c(new com.ss.union.game.sdk.feedback.c.b<List<c>>() { // from class: com.ss.union.game.sdk.feedback.fragment.LGFeedbackRecordFragment.4
            @Override // com.ss.union.game.sdk.feedback.c.b
            public void a(int i, String str) {
                com.ss.union.game.sdk.c.a.d("拉取反馈记录失败: " + i + ",message=" + str);
                LGFeedbackRecordFragment.this.b();
            }

            @Override // com.ss.union.game.sdk.feedback.c.b
            public void a(List<c> list) {
                if (list == null || list.isEmpty()) {
                    LGFeedbackRecordFragment.this.c();
                } else {
                    LGFeedbackRecordFragment.this.b(list);
                }
            }
        });
    }

    class a extends BaseAdapter {
        private List<c> b;

        @Override // android.widget.Adapter
        public long getItemId(int i) {
            return i;
        }

        public a(List<c> list) {
            this.b = new ArrayList();
            this.b = list;
        }

        public void a(List<c> list) {
            this.b = list;
            notifyDataSetChanged();
        }

        @Override // android.widget.Adapter
        public int getCount() {
            return this.b.size();
        }

        @Override // android.widget.Adapter
        public Object getItem(int i) {
            return this.b.get(i);
        }

        @Override // android.widget.Adapter
        public View getView(int i, View view, ViewGroup viewGroup) {
            b bVar;
            c cVar = this.b.get(i);
            if (view == null) {
                view = LayoutInflater.from(LGFeedbackRecordFragment.this.getContext()).inflate(ResourceUtils.getLayoutIdByName("lg_feedback_record_item"), viewGroup, false);
                bVar = LGFeedbackRecordFragment.this.new b(view);
                view.setTag(bVar);
            } else {
                bVar = (b) view.getTag();
            }
            bVar.a(cVar);
            return view;
        }
    }

    class b {
        private View b;
        private TextView c;
        private TextView d;
        private TextView e;

        public b(View view) {
            this.b = view;
            this.c = (TextView) view.findViewById(ResourceUtils.getIdByName("lg_feedback_record_title"));
            this.d = (TextView) view.findViewById(ResourceUtils.getIdByName("lg_feedback_record_time"));
            this.e = (TextView) view.findViewById(ResourceUtils.getIdByName("lg_feedback_record_new_reply"));
        }

        public void a(final c cVar) {
            try {
                this.d.setText(DateUtils.dateToString(new Date(DateUtils.safeTimeStamp(cVar.f)), DateUtils.FORMAT_ONE));
                if (cVar.d) {
                    this.e.setVisibility(0);
                } else {
                    this.e.setVisibility(8);
                }
                this.c.setText(cVar.c);
                this.b.setOnClickListener(LGFeedbackRecordFragment.this.antiShake(new View.OnClickListener() { // from class: com.ss.union.game.sdk.feedback.fragment.LGFeedbackRecordFragment.b.1
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        b.this.e.setVisibility(8);
                        Bundle bundle = new Bundle();
                        bundle.putLong(LGUserFeedbackDetailFragment.a, cVar.a);
                        LGFeedbackRecordFragment.this.navigation(LGUserFeedbackDetailFragment.a(bundle));
                    }
                }));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
