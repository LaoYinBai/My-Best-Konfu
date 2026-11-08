package com.ss.union.game.sdk.core.base.debug.automatic_detection.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class LGCommonHeaderLayout extends BaseCommonLayout {
    private View a;
    private ImageView b;
    private TextView c;
    private TextView d;

    @Override // com.ss.union.game.sdk.core.base.debug.automatic_detection.ui.BaseCommonLayout
    public String getLayoutName() {
        return "lg_common_header_view";
    }

    public LGCommonHeaderLayout(@NonNull Context context) {
        this(context, null);
    }

    public LGCommonHeaderLayout(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public LGCommonHeaderLayout(@NonNull Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    @Override // com.ss.union.game.sdk.core.base.debug.automatic_detection.ui.BaseCommonLayout
    public void a() {
        super.a();
        this.a = a("lg_common_header_view_root_view");
        this.b = (ImageView) a("lg_common_header_view_iv_back");
        this.c = (TextView) a("lg_common_header_view_tv_title");
        this.d = (TextView) a("lg_common_header_view_right_text_btn");
    }

    public LGCommonHeaderLayout b(View.OnClickListener onClickListener) {
        if (this.b != null) {
            this.b.setOnClickListener(a(onClickListener));
        }
        return this;
    }

    public LGCommonHeaderLayout c(View.OnClickListener onClickListener) {
        if (this.d != null) {
            this.d.setOnClickListener(a(onClickListener));
        }
        return this;
    }

    public LGCommonHeaderLayout b(String str) {
        if (this.c != null) {
            this.c.setText(str);
        }
        return this;
    }

    public LGCommonHeaderLayout c(String str) {
        if (this.d != null) {
            this.d.setText(str);
        }
        return this;
    }

    public LGCommonHeaderLayout a(int i) {
        if (this.c != null) {
            this.c.setVisibility(i);
        }
        return this;
    }

    public LGCommonHeaderLayout b(int i) {
        if (this.d != null) {
            this.d.setVisibility(i);
        }
        return this;
    }
}
