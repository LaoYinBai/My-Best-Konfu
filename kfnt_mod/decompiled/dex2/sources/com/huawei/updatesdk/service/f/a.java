package com.huawei.updatesdk.service.f;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import com.huawei.updatesdk.service.otaupdate.g;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class a {
    private com.huawei.updatesdk.service.f.b a;
    private Context b;
    private String c;
    private CharSequence d;
    private AlertDialog e;
    private AlertDialog.Builder f;
    private DialogInterface.OnShowListener g;
    private DialogInterface.OnDismissListener h;

    /* renamed from: com.huawei.updatesdk.service.f.a$a, reason: collision with other inner class name */
    public enum EnumC0016a {
        CONFIRM,
        CANCEL
    }

    public interface b {
        void a();
    }

    protected a(Context context, String str, CharSequence charSequence) {
        this.b = context;
        this.c = str;
        this.d = charSequence;
        this.f = new AlertDialog.Builder(context);
        this.f.setTitle(this.c);
        this.f.setPositiveButton(g.b(context, "upsdk_third_app_dl_sure_cancel_download"), (DialogInterface.OnClickListener) null);
        this.f.setNegativeButton(g.b(context, "upsdk_cancel"), (DialogInterface.OnClickListener) null);
        this.f.setMessage(this.d);
    }

    public static a a(Context context, String str, CharSequence charSequence) {
        return ((context instanceof Activity) && ((Activity) context).isFinishing()) ? new c(context, str, charSequence) : new a(context, str, charSequence);
    }

    public void a() {
        if (this.f == null) {
            return;
        }
        this.f.setNegativeButton((CharSequence) null, (DialogInterface.OnClickListener) null);
    }

    public void a(int i, int i2) {
        if (com.huawei.updatesdk.service.e.b.a().b() >= 11) {
            Button button = this.e != null ? this.e.getButton(-1) : null;
            if (button != null) {
                button.setBackgroundResource(i);
                button.setTextColor(this.b.getResources().getColor(i2));
            }
        }
    }

    public void a(DialogInterface.OnDismissListener onDismissListener) {
        this.h = onDismissListener;
    }

    public void a(DialogInterface.OnKeyListener onKeyListener) {
        if (this.e != null) {
            this.e.setOnKeyListener(onKeyListener);
        }
    }

    public void a(DialogInterface.OnShowListener onShowListener) {
        this.g = onShowListener;
    }

    public void a(View view) {
        ImageView imageView;
        if (this.f != null) {
            int iB = com.huawei.updatesdk.service.e.b.a().b();
            if ((iB < 11 || iB >= 17) && (imageView = (ImageView) view.findViewById(g.a(view.getContext(), "divider"))) != null) {
                imageView.setVisibility(8);
            }
            this.f.setMessage((CharSequence) null);
            this.f.setView(view);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x001c A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:15:0x001d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void a(com.huawei.updatesdk.service.f.a.EnumC0016a r2, java.lang.String r3) {
        /*
            r1 = this;
            android.app.AlertDialog r0 = r1.e
            if (r0 != 0) goto L5
            return
        L5:
            com.huawei.updatesdk.service.f.a$a r0 = com.huawei.updatesdk.service.f.a.EnumC0016a.CONFIRM
            if (r2 != r0) goto L11
            android.app.AlertDialog r2 = r1.e
            r0 = -1
        Lc:
            android.widget.Button r2 = r2.getButton(r0)
            goto L1a
        L11:
            com.huawei.updatesdk.service.f.a$a r0 = com.huawei.updatesdk.service.f.a.EnumC0016a.CANCEL
            if (r2 != r0) goto L19
            android.app.AlertDialog r2 = r1.e
            r0 = -2
            goto Lc
        L19:
            r2 = 0
        L1a:
            if (r2 != 0) goto L1d
            return
        L1d:
            r2.setText(r3)
            r3 = 1
            r2.setAllCaps(r3)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.updatesdk.service.f.a.a(com.huawei.updatesdk.service.f.a$a, java.lang.String):void");
    }

    public void a(b bVar) {
        if (this.b == null || ((Activity) this.b).isFinishing()) {
            if (bVar != null) {
                bVar.a();
                return;
            }
            return;
        }
        if (b()) {
            StringBuilder sb = new StringBuilder();
            sb.append("show dlg error, mContext = ");
            sb.append(this.b);
            sb.append(", mContext.isFinishing is ");
            sb.append(this.b == null ? "mContext == null" : Boolean.valueOf(((Activity) this.b).isFinishing()));
            com.huawei.updatesdk.a.a.b.a.a.a.d("BaseAlertDialog", sb.toString());
            return;
        }
        try {
            this.e = this.f.create();
            this.e.setCanceledOnTouchOutside(false);
            this.e.setOnShowListener(new DialogInterface.OnShowListener() { // from class: com.huawei.updatesdk.service.f.a.1
                @Override // android.content.DialogInterface.OnShowListener
                public void onShow(DialogInterface dialogInterface) {
                    AlertDialog alertDialog = (AlertDialog) dialogInterface;
                    alertDialog.getButton(-1).setOnClickListener(new View.OnClickListener() { // from class: com.huawei.updatesdk.service.f.a.1.1
                        @Override // android.view.View.OnClickListener
                        public void onClick(View view) {
                            if (a.this.a != null) {
                                a.this.a.a();
                            }
                        }
                    });
                    Button button = alertDialog.getButton(-2);
                    if (button != null) {
                        button.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.updatesdk.service.f.a.1.2
                            @Override // android.view.View.OnClickListener
                            public void onClick(View view) {
                                if (a.this.a != null) {
                                    a.this.a.b();
                                }
                            }
                        });
                    }
                    if (a.this.g != null) {
                        a.this.g.onShow(dialogInterface);
                    }
                }
            });
            this.e.setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: com.huawei.updatesdk.service.f.a.2
                @Override // android.content.DialogInterface.OnDismissListener
                public void onDismiss(DialogInterface dialogInterface) {
                    if (a.this.h != null) {
                        a.this.h.onDismiss(dialogInterface);
                    }
                }
            });
            this.e.show();
        } catch (Exception e) {
            if (bVar != null) {
                bVar.a();
            }
            com.huawei.updatesdk.a.a.b.a.a.a.a("BaseAlertDialog", "show dlg error, e: ", e);
        }
    }

    public void a(com.huawei.updatesdk.service.f.b bVar) {
        this.a = bVar;
    }

    public void a(boolean z) {
        if (this.e != null) {
            this.e.setCancelable(z);
        }
    }

    public boolean b() {
        return this.e != null && this.e.isShowing();
    }

    public void c() {
        try {
            if (this.e != null) {
                this.e.dismiss();
                this.e = null;
            }
        } catch (IllegalArgumentException unused) {
            com.huawei.updatesdk.a.a.b.a.a.a.a("BaseAlertDialog", "dialog dismiss IllegalArgumentException");
        }
    }
}
