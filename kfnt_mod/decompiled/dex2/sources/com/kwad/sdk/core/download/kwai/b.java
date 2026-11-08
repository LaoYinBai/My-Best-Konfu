package com.kwad.sdk.core.download.kwai;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.kwad.sdk.api.core.ResContext;
import com.kwad.sdk.api.loader.Wrapper;
import com.kwad.sdk.core.response.model.AdTemplate;
import com.kwad.sdk.utils.af;
import com.kwad.sdk.utils.x;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class b extends AlertDialog {
    private static b a;
    private String b;
    private a c;
    private AdTemplate d;
    private boolean e;

    private b(Activity activity, @NonNull AdTemplate adTemplate, String str) {
        super(Wrapper.wrapContextIfNeed(activity));
        this.e = false;
        setOwnerActivity(activity);
        this.d = adTemplate;
        this.b = str;
    }

    public static b a(Activity activity, AdTemplate adTemplate, String str) {
        b bVar = new b(activity, adTemplate, str);
        bVar.getWindow().addFlags(1024);
        return bVar;
    }

    public static boolean a() {
        if (a != null) {
            return a.isShowing();
        }
        return false;
    }

    public static boolean a(@NonNull Context context, @NonNull AdTemplate adTemplate, @Nullable String str) {
        return a(context, adTemplate, str, null, null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static boolean a(@NonNull Context context, @NonNull AdTemplate adTemplate, @Nullable String str, @Nullable DialogInterface.OnShowListener onShowListener, @Nullable DialogInterface.OnDismissListener onDismissListener) {
        boolean z = context instanceof ResContext;
        Object delegatedContext = context;
        if (z) {
            delegatedContext = ((ResContext) context).getDelegatedContext();
        }
        Activity activityC = delegatedContext instanceof Activity ? (Activity) delegatedContext : x.a().c();
        if (activityC == null) {
            return false;
        }
        try {
            if (a == null) {
                a = a(activityC, adTemplate, str);
                if (onShowListener != null) {
                    a.setOnShowListener(onShowListener);
                }
                if (onDismissListener != null) {
                    a.setOnDismissListener(onDismissListener);
                }
            }
            if (a.isShowing()) {
                return true;
            }
            a.show();
            com.kwad.sdk.core.report.a.c(adTemplate, 86, null);
            return true;
        } catch (Throwable th) {
            com.kwad.sdk.core.d.a.a(th);
            return false;
        }
    }

    public void a(boolean z) {
        this.e = z;
        dismiss();
    }

    public boolean b() {
        return this.e;
    }

    @Override // android.app.Dialog, android.content.DialogInterface
    public void dismiss() {
        super.dismiss();
        a = null;
    }

    @Override // android.app.Dialog
    public boolean isShowing() {
        return super.isShowing();
    }

    @Override // android.app.Dialog
    public void onBackPressed() {
        super.onBackPressed();
        com.kwad.sdk.core.report.a.n(this.d);
    }

    @Override // android.app.AlertDialog, android.app.Dialog
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (this.c == null) {
            this.c = new a(Wrapper.wrapContextIfNeed(getOwnerActivity()), this, this.d, !af.e(getOwnerActivity()) ? 1 : 0, this.b);
            this.c.setOnClickListener(new View.OnClickListener() { // from class: com.kwad.sdk.core.download.kwai.b.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    com.kwad.sdk.core.report.a.n(b.this.d);
                    b.this.dismiss();
                }
            });
        }
        setContentView(this.c);
        setCanceledOnTouchOutside(true);
        getWindow().getDecorView().setPadding(0, 0, 0, 0);
        getWindow().setBackgroundDrawable(new ColorDrawable(0));
        getWindow().setLayout(-1, -1);
    }

    @Override // android.app.Dialog, android.view.Window.Callback
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.c != null) {
            this.c.c();
        }
        a = null;
    }

    @Override // android.app.Dialog
    protected void onStart() {
        super.onStart();
        if (a != null) {
            a.setTitle((CharSequence) null);
        }
    }

    @Override // android.app.Dialog
    public void show() {
        super.show();
    }
}
