package com.sigmob.volley.toolbox;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import com.sigmob.volley.s;
import com.sigmob.volley.toolbox.k;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class NetworkImageView extends ImageView {
    private String a;
    private int b;
    private int c;
    private k d;
    private k.c e;

    /* renamed from: com.sigmob.volley.toolbox.NetworkImageView$1, reason: invalid class name */
    class AnonymousClass1 implements k.d {
        final /* synthetic */ boolean a;

        AnonymousClass1(boolean z) {
            this.a = z;
        }

        @Override // com.sigmob.volley.toolbox.k.d
        public void a(final k.c cVar, boolean z) {
            if (z && this.a) {
                NetworkImageView.this.post(new Runnable() { // from class: com.sigmob.volley.toolbox.NetworkImageView.1.1
                    @Override // java.lang.Runnable
                    public void run() {
                        AnonymousClass1.this.a(cVar, false);
                    }
                });
            } else if (cVar.b() != null) {
                NetworkImageView.this.setImageBitmap(cVar.b());
            } else if (NetworkImageView.this.b != 0) {
                NetworkImageView.this.setImageResource(NetworkImageView.this.b);
            }
        }

        @Override // com.sigmob.volley.n.a
        public void onErrorResponse(s sVar) {
            if (NetworkImageView.this.c != 0) {
                NetworkImageView.this.setImageResource(NetworkImageView.this.c);
            }
        }
    }

    public NetworkImageView(Context context) {
        this(context, null);
    }

    public NetworkImageView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public NetworkImageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    private void a() {
        if (this.b != 0) {
            setImageResource(this.b);
        } else {
            setImageBitmap(null);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x0031  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0041  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0051  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    void a(boolean r9) {
        /*
            r8 = this;
            int r0 = r8.getWidth()
            int r1 = r8.getHeight()
            android.widget.ImageView$ScaleType r7 = r8.getScaleType()
            android.view.ViewGroup$LayoutParams r2 = r8.getLayoutParams()
            r3 = 1
            r4 = 0
            if (r2 == 0) goto L2a
            android.view.ViewGroup$LayoutParams r2 = r8.getLayoutParams()
            int r2 = r2.width
            r5 = -2
            if (r2 != r5) goto L1f
            r2 = 1
            goto L20
        L1f:
            r2 = 0
        L20:
            android.view.ViewGroup$LayoutParams r6 = r8.getLayoutParams()
            int r6 = r6.height
            if (r6 != r5) goto L2b
            r5 = 1
            goto L2c
        L2a:
            r2 = 0
        L2b:
            r5 = 0
        L2c:
            if (r2 == 0) goto L31
            if (r5 == 0) goto L31
            goto L32
        L31:
            r3 = 0
        L32:
            if (r0 != 0) goto L39
            if (r1 != 0) goto L39
            if (r3 != 0) goto L39
            return
        L39:
            java.lang.String r3 = r8.a
            boolean r3 = android.text.TextUtils.isEmpty(r3)
            if (r3 == 0) goto L51
            com.sigmob.volley.toolbox.k$c r9 = r8.e
            if (r9 == 0) goto L4d
            com.sigmob.volley.toolbox.k$c r9 = r8.e
            r9.a()
            r9 = 0
            r8.e = r9
        L4d:
            r8.a()
            return
        L51:
            com.sigmob.volley.toolbox.k$c r3 = r8.e
            if (r3 == 0) goto L74
            com.sigmob.volley.toolbox.k$c r3 = r8.e
            java.lang.String r3 = r3.c()
            if (r3 == 0) goto L74
            com.sigmob.volley.toolbox.k$c r3 = r8.e
            java.lang.String r3 = r3.c()
            java.lang.String r6 = r8.a
            boolean r3 = r3.equals(r6)
            if (r3 == 0) goto L6c
            return
        L6c:
            com.sigmob.volley.toolbox.k$c r3 = r8.e
            r3.a()
            r8.a()
        L74:
            if (r2 == 0) goto L77
            r0 = 0
        L77:
            if (r5 == 0) goto L7b
            r6 = 0
            goto L7c
        L7b:
            r6 = r1
        L7c:
            com.sigmob.volley.toolbox.k r2 = r8.d
            java.lang.String r3 = r8.a
            com.sigmob.volley.toolbox.NetworkImageView$1 r4 = new com.sigmob.volley.toolbox.NetworkImageView$1
            r4.<init>(r9)
            r5 = r0
            com.sigmob.volley.toolbox.k$c r9 = r2.a(r3, r4, r5, r6, r7)
            r8.e = r9
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sigmob.volley.toolbox.NetworkImageView.a(boolean):void");
    }

    @Override // android.widget.ImageView, android.view.View
    protected void drawableStateChanged() {
        super.drawableStateChanged();
        invalidate();
    }

    @Override // android.widget.ImageView, android.view.View
    protected void onDetachedFromWindow() {
        if (this.e != null) {
            this.e.a();
            setImageBitmap(null);
            this.e = null;
        }
        super.onDetachedFromWindow();
    }

    @Override // android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        a(true);
    }

    public void setDefaultImageResId(int i) {
        this.b = i;
    }

    public void setErrorImageResId(int i) {
        this.c = i;
    }
}
