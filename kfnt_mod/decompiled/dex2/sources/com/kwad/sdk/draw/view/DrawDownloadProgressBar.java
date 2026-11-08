package com.kwad.sdk.draw.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.kwad.sdk.R;
import com.kwad.sdk.a.kwai.a;
import com.kwad.sdk.core.page.widget.TextProgressBar;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class DrawDownloadProgressBar extends FrameLayout {
    private Context a;
    private TextProgressBar b;
    private View c;

    public DrawDownloadProgressBar(@NonNull Context context) {
        super(context);
        a(context);
    }

    public DrawDownloadProgressBar(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context);
    }

    public DrawDownloadProgressBar(@NonNull Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a(context);
    }

    private void a(Context context) {
        this.a = context;
        inflate(this.a, R.layout.ksad_draw_download_bar, this);
        this.b = (TextProgressBar) findViewById(R.id.ksad_download_progress);
        this.c = findViewById(R.id.ksad_download_progress_cover);
        this.c.setOnClickListener(new View.OnClickListener() { // from class: com.kwad.sdk.draw.view.DrawDownloadProgressBar.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                DrawDownloadProgressBar.this.performClick();
            }
        });
    }

    public void a(String str, int i) {
        View view;
        int i2;
        if (i == 0 || i == getMax()) {
            view = this.c;
            i2 = 0;
        } else {
            view = this.c;
            i2 = 8;
        }
        view.setVisibility(i2);
        this.b.a(str, i);
    }

    public int getMax() {
        return this.b.getMax();
    }

    public void setTextColor(int i) {
        this.b.setTextColor(i);
    }

    public void setTextSize(int i) {
        this.b.setTextDimen(a.a(getContext(), i));
    }
}
