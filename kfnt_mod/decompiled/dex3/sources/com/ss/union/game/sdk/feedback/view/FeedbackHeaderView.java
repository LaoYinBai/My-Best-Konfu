package com.ss.union.game.sdk.feedback.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.ss.union.game.sdk.common.util.ResourceUtils;
import com.ss.union.game.sdk.common.util.UIUtils;
import com.ss.union.game.sdk.core.base.view.CardView;

@Keep
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class FeedbackHeaderView extends CardView {
    private ImageView mIvBack;
    private View mRecordDot;
    private String mTitleStr;
    private TextView mTvClose;
    private View mTvRecord;
    private TextView mTvTitle;

    @Override // com.ss.union.game.sdk.core.base.view.CardView
    public String getLayoutName() {
        return "lg_feedback_header";
    }

    public FeedbackHeaderView(@NonNull Context context) {
        this(context, null);
    }

    public FeedbackHeaderView(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public FeedbackHeaderView(@NonNull Context context, @Nullable AttributeSet attributeSet, int i) throws Throwable {
        super(context, attributeSet, i);
        init(context, attributeSet, i);
    }

    private void init(Context context, AttributeSet attributeSet, int i) throws Throwable {
        TypedArray typedArrayObtainStyledAttributes;
        if (attributeSet != null) {
            TypedArray typedArray = null;
            try {
                try {
                    typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, ResourceUtils.getStyleableInts("styleable", "FeedbackHeaderView"), i, 0);
                } catch (Exception e) {
                    e = e;
                }
            } catch (Throwable th) {
                th = th;
                typedArrayObtainStyledAttributes = typedArray;
            }
            try {
                this.mTitleStr = typedArrayObtainStyledAttributes.getString(ResourceUtils.getStyleableInt("styleable", "FeedbackHeaderView_titleText"));
                if (TextUtils.isEmpty(this.mTitleStr)) {
                    this.mTitleStr = ResourceUtils.getString("lg_user_feedback_title");
                }
                if (typedArrayObtainStyledAttributes != null) {
                    typedArrayObtainStyledAttributes.recycle();
                }
            } catch (Exception e2) {
                e = e2;
                typedArray = typedArrayObtainStyledAttributes;
                e.printStackTrace();
                if (typedArray != null) {
                    typedArray.recycle();
                }
            } catch (Throwable th2) {
                th = th2;
                if (typedArrayObtainStyledAttributes != null) {
                    typedArrayObtainStyledAttributes.recycle();
                }
                throw th;
            }
        }
    }

    @Override // com.ss.union.game.sdk.core.base.view.CardView
    public void initView() {
        super.initView();
        this.mIvBack = (ImageView) findViewById(ResourceUtils.getIdByName("lg_user_feedback_back"));
        this.mTvClose = (TextView) findViewById(ResourceUtils.getIdByName("lg_user_feedback_close"));
        this.mTvRecord = findViewById(ResourceUtils.getIdByName("lg_user_feedback_record"));
        this.mRecordDot = findViewById(ResourceUtils.getIdByName("lg_user_feedback_record_reddot"));
        this.mTvTitle = (TextView) findViewById(ResourceUtils.getIdByName("lg_user_feedback_title"));
        this.mTvTitle.setText(this.mTitleStr);
        this.mTvRecord.setVisibility(4);
        this.mRecordDot.setVisibility(4);
        UIUtils.expandClickRegion(this.mIvBack, 5);
    }

    public void showRecord(boolean z) {
        if (z) {
            this.mTvRecord.setVisibility(0);
        } else {
            this.mTvRecord.setVisibility(4);
        }
    }

    public void showRecordRedDot(boolean z) {
        if (z) {
            this.mRecordDot.setVisibility(0);
        } else {
            this.mRecordDot.setVisibility(4);
        }
    }

    public void listenBack(View.OnClickListener onClickListener) {
        if (this.mIvBack != null) {
            this.mIvBack.setOnClickListener(antiShake(onClickListener));
        }
    }

    public void listenClose(View.OnClickListener onClickListener) {
        if (this.mTvClose != null) {
            this.mTvClose.setOnClickListener(antiShake(onClickListener));
        }
    }

    public void listenJumpFeedbackRecord(View.OnClickListener onClickListener) {
        if (this.mTvRecord != null) {
            this.mTvRecord.setOnClickListener(antiShake(onClickListener));
        }
    }
}
