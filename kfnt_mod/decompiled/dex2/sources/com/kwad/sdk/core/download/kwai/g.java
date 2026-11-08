package com.kwad.sdk.core.download.kwai;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import com.kwad.sdk.R;
import com.kwad.sdk.api.loader.Wrapper;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class g extends com.kwad.sdk.widget.a {
    private b a;
    private String b;
    private String c;
    private int d;
    private String e;
    private int f;

    public static class a {
        private Context a;
        private b b;
        private String c;
        private String d;
        private int e;
        private String f;
        private int g;

        public a(@NonNull Context context) {
            this.a = context;
        }

        public a a(b bVar) {
            this.b = bVar;
            return this;
        }

        public a a(@NonNull String str) {
            this.c = str;
            return this;
        }

        public com.kwad.sdk.widget.a a() {
            return new g(this);
        }
    }

    public interface b {
        void a(DialogInterface dialogInterface);

        void b(DialogInterface dialogInterface);

        void c(DialogInterface dialogInterface);
    }

    public g(a aVar) {
        super(aVar.a);
        a(aVar);
    }

    private View a() {
        View viewInflate = LayoutInflater.from(Wrapper.wrapContextIfNeed(getContext())).inflate(R.layout.ksad_no_title_common_dialog_content_layout, (ViewGroup) null, false);
        viewInflate.findViewById(R.id.ksad_no_title_common_dialog_layout).setOnClickListener(new View.OnClickListener() { // from class: com.kwad.sdk.core.download.kwai.g.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (g.this.a != null) {
                    g.this.a.c(g.this);
                }
            }
        });
        TextView textView = (TextView) viewInflate.findViewById(R.id.ksad_no_title_common_positive_btn);
        if (!TextUtils.isEmpty(this.c)) {
            textView.setText(this.c);
        }
        textView.setTextColor(this.d);
        textView.setOnClickListener(new View.OnClickListener() { // from class: com.kwad.sdk.core.download.kwai.g.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (g.this.a != null) {
                    g.this.a.a(g.this);
                }
            }
        });
        TextView textView2 = (TextView) viewInflate.findViewById(R.id.ksad_no_title_common_negative_btn);
        if (!TextUtils.isEmpty(this.e)) {
            textView2.setText(this.e);
        }
        textView2.setTextColor(this.f);
        textView2.setOnClickListener(new View.OnClickListener() { // from class: com.kwad.sdk.core.download.kwai.g.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (g.this.a != null) {
                    g.this.a.b(g.this);
                }
            }
        });
        ((TextView) viewInflate.findViewById(R.id.ksad_no_title_common_content_text)).setText(this.b);
        return viewInflate;
    }

    private void a(a aVar) {
        if (aVar.a instanceof Activity) {
            setOwnerActivity((Activity) aVar.a);
        }
        this.a = aVar.b;
        this.b = aVar.c;
        this.c = !TextUtils.isEmpty(aVar.d) ? aVar.d : aVar.a.getString(R.string.ksad_no_title_common_dialog_positivebtn_title);
        this.d = aVar.e != 0 ? aVar.e : aVar.a.getResources().getColor(R.color.ksad_no_title_common_dialog_positivebtn_color);
        this.e = !TextUtils.isEmpty(aVar.f) ? aVar.f : aVar.a.getString(R.string.ksad_no_title_common_dialog_negativebtn_title);
        this.f = aVar.g != 0 ? aVar.g : aVar.a.getResources().getColor(R.color.ksad_no_title_common_dialog_negativebtn_color);
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
