package com.ss.union.game.sdk.feedback.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.annotation.Keep;
import com.ss.union.game.sdk.common.util.ActivityUtils;
import com.ss.union.game.sdk.common.util.ResourceUtils;
import com.ss.union.game.sdk.feedback.picture.select.c;
import com.ss.union.game.sdk.feedback.picture.wall.PictureWallFragment;
import com.ss.union.game.sdk.feedback.picture.wall.b;
import java.util.ArrayList;
import java.util.List;

@Keep
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class FlowPhotoCard extends AddPhotoCard {
    private ArrayList<String> mPaths;

    private void init(Context context, AttributeSet attributeSet, int i) {
    }

    public FlowPhotoCard(Context context) {
        this(context, null);
    }

    public FlowPhotoCard(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public FlowPhotoCard(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mPaths = new ArrayList<>();
        init(context, attributeSet, i);
    }

    @Override // com.ss.union.game.sdk.feedback.view.AddPhotoCard, com.ss.union.game.sdk.core.base.view.CardView
    public void initView() {
        this.mllContainer = (LinearLayout) findViewById(ResourceUtils.getIdByName("lg_feedback_photo_container"));
    }

    public void setData(List<String> list) {
        this.mPaths.clear();
        this.mPaths.addAll(clipData(list));
        updateData(this.mPaths);
    }

    public List<String> clipData(List<String> list) {
        return (list == null || list.isEmpty() || list.size() <= 3) ? list : list.subList(0, 3);
    }

    private void updateData(List<String> list) {
        if (list == null || list.isEmpty()) {
            this.mllContainer.removeAllViews();
            setVisibility(8);
            return;
        }
        setVisibility(0);
        this.mllContainer.removeAllViews();
        for (final int i = 0; i < list.size(); i++) {
            String str = list.get(i);
            ImageView imageView = getImageView(getContext(), false);
            imageView.setOnClickListener(antiShake(new View.OnClickListener() { // from class: com.ss.union.game.sdk.feedback.view.FlowPhotoCard.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    FlowPhotoCard.this.jumpToDetail(i);
                }
            }));
            c.a(imageView, str);
            if (i != 0) {
                setMarginStart(imageView, 8);
            }
            this.mllContainer.addView(imageView);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void jumpToDetail(int i) {
        b bVar = new b();
        bVar.a = this.mPaths;
        bVar.c = i;
        bVar.b = false;
        if (ActivityUtils.unwrap(getContext()) != null) {
            PictureWallFragment.a(bVar.b, bVar.c, bVar.a, this);
        }
    }
}
