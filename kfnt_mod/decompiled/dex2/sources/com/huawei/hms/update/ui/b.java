package com.huawei.hms.update.ui;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import com.huawei.hms.support.log.HMSLog;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public abstract class b {
    private AlertDialog a;
    private a b;

    protected abstract AlertDialog a();

    private static int a(Context context) {
        if (context == null) {
            return 0;
        }
        return context.getResources().getIdentifier("androidhwext:style/Theme.Emui", null, null);
    }

    public void a(a aVar) {
        this.b = aVar;
        if (f() == null || f().isFinishing()) {
            HMSLog.e("AbstractDialog", "In show, The activity is null or finishing.");
            return;
        }
        this.a = a();
        this.a.setCanceledOnTouchOutside(false);
        this.a.setOnCancelListener(new DialogInterface.OnCancelListener() { // from class: com.huawei.hms.update.ui.b.1
            @Override // android.content.DialogInterface.OnCancelListener
            public void onCancel(DialogInterface dialogInterface) {
                b.this.d();
            }
        });
        this.a.show();
    }

    public void b() {
        if (this.a != null) {
            this.a.cancel();
        }
    }

    public void c() {
        if (this.a != null) {
            this.a.dismiss();
        }
    }

    protected void d() {
        if (this.b != null) {
            this.b.a(this);
        }
    }

    protected void e() {
        if (this.b != null) {
            this.b.b(this);
        }
    }

    protected Activity f() {
        if (this.b != null) {
            return this.b.b();
        }
        return null;
    }

    protected int g() {
        return (a(f()) == 0 || Build.VERSION.SDK_INT < 16) ? 3 : 0;
    }
}
