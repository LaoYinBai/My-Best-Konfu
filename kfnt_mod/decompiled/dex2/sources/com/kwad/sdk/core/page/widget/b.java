package com.kwad.sdk.core.page.widget;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import com.kwad.sdk.R;
import com.kwad.sdk.api.loader.Wrapper;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class b extends Dialog {
    private a a;

    public interface a {
        void a(DialogInterface dialogInterface);

        void b(DialogInterface dialogInterface);

        void c(DialogInterface dialogInterface);
    }

    public b(@NonNull Context context, @NonNull a aVar) {
        super(context);
        if (context instanceof Activity) {
            setOwnerActivity((Activity) context);
        }
        this.a = aVar;
    }

    private View a() {
        View viewInflate = LayoutInflater.from(Wrapper.wrapContextIfNeed(getContext())).inflate(R.layout.ksad_web_exit_intercept_content_layout, (ViewGroup) null, false);
        viewInflate.findViewById(R.id.ksad_exit_intercept_dialog_layout).setOnClickListener(new View.OnClickListener() { // from class: com.kwad.sdk.core.page.widget.b.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                b.this.a.c(b.this);
            }
        });
        viewInflate.findViewById(R.id.ksad_exit_intercept_content_layout).setOnClickListener(new View.OnClickListener() { // from class: com.kwad.sdk.core.page.widget.b.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
            }
        });
        viewInflate.findViewById(R.id.ksad_web_exit_intercept_positive_btn).setOnClickListener(new View.OnClickListener() { // from class: com.kwad.sdk.core.page.widget.b.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                b.this.a.a(b.this);
            }
        });
        viewInflate.findViewById(R.id.ksad_web_exit_intercept_negative_btn).setOnClickListener(new View.OnClickListener() { // from class: com.kwad.sdk.core.page.widget.b.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                b.this.a.b(b.this);
            }
        });
        return viewInflate;
    }

    @Override // android.app.Dialog
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        requestWindowFeature(1);
        setContentView(a());
        setCanceledOnTouchOutside(true);
        getWindow().setBackgroundDrawable(new ColorDrawable(0));
        getWindow().setLayout(-1, -1);
    }
}
