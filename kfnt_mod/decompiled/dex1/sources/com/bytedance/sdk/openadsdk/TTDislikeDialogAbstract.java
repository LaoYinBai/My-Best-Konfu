package com.bytedance.sdk.openadsdk;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.bytedance.sdk.openadsdk.dislike.TTDislikeListView;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public abstract class TTDislikeDialogAbstract extends Dialog {

    /* renamed from: a, reason: collision with root package name */
    private View f5179a;

    /* renamed from: b, reason: collision with root package name */
    private TTDislikeController f5180b;

    public abstract int getLayoutId();

    public abstract ViewGroup.LayoutParams getLayoutParams();

    public abstract int[] getTTDislikeListViewIds();

    public TTDislikeDialogAbstract(Context context) {
        super(context);
    }

    public TTDislikeDialogAbstract(Context context, int i) {
        super(context, i);
    }

    @Override // android.app.Dialog
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.f5179a = LayoutInflater.from(getContext()).inflate(getLayoutId(), (ViewGroup) null);
        if (this.f5179a == null) {
            throw new IllegalArgumentException("getLayoutId布局文件id可能异常，请检查");
        }
        ViewGroup.LayoutParams layoutParams = getLayoutParams();
        View view = this.f5179a;
        if (layoutParams == null) {
            layoutParams = new ViewGroup.LayoutParams(-1, -1);
        }
        setContentView(view, layoutParams);
        a();
    }

    protected void a() {
        if (this.f5180b == null || this.f5179a == null) {
            return;
        }
        int[] tTDislikeListViewIds = getTTDislikeListViewIds();
        if (tTDislikeListViewIds == null || tTDislikeListViewIds.length <= 0) {
            throw new IllegalArgumentException("dislike选项列表为空，请设置TTDislikeListView");
        }
        for (int i : tTDislikeListViewIds) {
            View viewFindViewById = this.f5179a.findViewById(i);
            if (viewFindViewById == null) {
                throw new IllegalArgumentException("getTTDislikeListViewIds提供的id找不到view，请检查");
            }
            if (!(viewFindViewById instanceof TTDislikeListView)) {
                throw new IllegalArgumentException("getTTDislikeListViewIds找到的view类型异常，请检查");
            }
            ((TTDislikeListView) viewFindViewById).setDislikeInfo(this.f5180b);
        }
    }

    public void setDislikeModel(TTDislikeController tTDislikeController) {
        this.f5180b = tTDislikeController;
        a();
    }

    public void startPersonalizePromptActivity() {
        if (this.f5180b != null) {
            this.f5180b.openWebPage(getContext(), true);
            this.f5180b.onDislikeEvent(getContext(), false);
        }
    }

    @Override // android.app.Dialog
    public void show() {
        super.show();
        b();
    }

    protected void b() {
        if (this.f5180b != null) {
            this.f5180b.onDislikeEvent(getContext(), true);
        }
    }
}
