package com.mbridge.msdk.widget.custom.baseview;

import android.R;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.StateSet;
import android.view.View;
import androidx.appcompat.widget.AppCompatButton;
import com.mbridge.msdk.foundation.tools.m;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class MBCircularProgressButton extends AppCompatButton {
    public static final int ERROR_STATE_PROGRESS = -1;
    public static final int IDLE_STATE_PROGRESS = 0;
    public static final int INDETERMINATE_STATE_PROGRESS = 50;
    public static final int SUCCESS_STATE_PROGRESS = 100;
    private d A;
    private d B;
    private f a;
    private com.mbridge.msdk.widget.custom.baseview.a b;
    private b c;
    private ColorStateList d;
    private ColorStateList e;
    private ColorStateList f;
    private StateListDrawable g;
    private StateListDrawable h;
    private StateListDrawable i;
    private e j;
    private a k;
    private int l;
    private int m;
    private int n;
    private int o;
    private int p;
    private int q;
    private int r;
    private float s;
    private boolean t;
    private boolean u;
    private int v;
    private int w;
    private boolean x;
    private d y;
    private d z;

    private enum a {
        PROGRESS,
        IDLE,
        COMPLETE,
        ERROR
    }

    public MBCircularProgressButton(Context context) {
        super(context);
        this.y = new d() { // from class: com.mbridge.msdk.widget.custom.baseview.MBCircularProgressButton.1
            @Override // com.mbridge.msdk.widget.custom.baseview.d
            public final void a() {
                MBCircularProgressButton.this.x = false;
                MBCircularProgressButton.this.k = a.PROGRESS;
                MBCircularProgressButton.this.j.b(MBCircularProgressButton.this);
            }
        };
        this.z = new d() { // from class: com.mbridge.msdk.widget.custom.baseview.MBCircularProgressButton.2
            @Override // com.mbridge.msdk.widget.custom.baseview.d
            public final void a() throws Resources.NotFoundException {
                if (MBCircularProgressButton.this.o != 0) {
                    MBCircularProgressButton.a(MBCircularProgressButton.this, MBCircularProgressButton.this.o);
                }
                MBCircularProgressButton.this.x = false;
                MBCircularProgressButton.this.k = a.COMPLETE;
                MBCircularProgressButton.this.j.b(MBCircularProgressButton.this);
            }
        };
        this.A = new d() { // from class: com.mbridge.msdk.widget.custom.baseview.MBCircularProgressButton.3
            @Override // com.mbridge.msdk.widget.custom.baseview.d
            public final void a() {
                MBCircularProgressButton.this.a();
                MBCircularProgressButton.this.x = false;
                MBCircularProgressButton.this.k = a.IDLE;
                MBCircularProgressButton.this.j.b(MBCircularProgressButton.this);
            }
        };
        this.B = new d() { // from class: com.mbridge.msdk.widget.custom.baseview.MBCircularProgressButton.4
            @Override // com.mbridge.msdk.widget.custom.baseview.d
            public final void a() throws Resources.NotFoundException {
                if (MBCircularProgressButton.this.p != 0) {
                    MBCircularProgressButton.a(MBCircularProgressButton.this, MBCircularProgressButton.this.p);
                }
                MBCircularProgressButton.this.x = false;
                MBCircularProgressButton.this.k = a.ERROR;
                MBCircularProgressButton.this.j.b(MBCircularProgressButton.this);
            }
        };
        a(context, (AttributeSet) null);
    }

    public MBCircularProgressButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.y = new d() { // from class: com.mbridge.msdk.widget.custom.baseview.MBCircularProgressButton.1
            @Override // com.mbridge.msdk.widget.custom.baseview.d
            public final void a() {
                MBCircularProgressButton.this.x = false;
                MBCircularProgressButton.this.k = a.PROGRESS;
                MBCircularProgressButton.this.j.b(MBCircularProgressButton.this);
            }
        };
        this.z = new d() { // from class: com.mbridge.msdk.widget.custom.baseview.MBCircularProgressButton.2
            @Override // com.mbridge.msdk.widget.custom.baseview.d
            public final void a() throws Resources.NotFoundException {
                if (MBCircularProgressButton.this.o != 0) {
                    MBCircularProgressButton.a(MBCircularProgressButton.this, MBCircularProgressButton.this.o);
                }
                MBCircularProgressButton.this.x = false;
                MBCircularProgressButton.this.k = a.COMPLETE;
                MBCircularProgressButton.this.j.b(MBCircularProgressButton.this);
            }
        };
        this.A = new d() { // from class: com.mbridge.msdk.widget.custom.baseview.MBCircularProgressButton.3
            @Override // com.mbridge.msdk.widget.custom.baseview.d
            public final void a() {
                MBCircularProgressButton.this.a();
                MBCircularProgressButton.this.x = false;
                MBCircularProgressButton.this.k = a.IDLE;
                MBCircularProgressButton.this.j.b(MBCircularProgressButton.this);
            }
        };
        this.B = new d() { // from class: com.mbridge.msdk.widget.custom.baseview.MBCircularProgressButton.4
            @Override // com.mbridge.msdk.widget.custom.baseview.d
            public final void a() throws Resources.NotFoundException {
                if (MBCircularProgressButton.this.p != 0) {
                    MBCircularProgressButton.a(MBCircularProgressButton.this, MBCircularProgressButton.this.p);
                }
                MBCircularProgressButton.this.x = false;
                MBCircularProgressButton.this.k = a.ERROR;
                MBCircularProgressButton.this.j.b(MBCircularProgressButton.this);
            }
        };
        a(context, attributeSet);
    }

    public MBCircularProgressButton(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.y = new d() { // from class: com.mbridge.msdk.widget.custom.baseview.MBCircularProgressButton.1
            @Override // com.mbridge.msdk.widget.custom.baseview.d
            public final void a() {
                MBCircularProgressButton.this.x = false;
                MBCircularProgressButton.this.k = a.PROGRESS;
                MBCircularProgressButton.this.j.b(MBCircularProgressButton.this);
            }
        };
        this.z = new d() { // from class: com.mbridge.msdk.widget.custom.baseview.MBCircularProgressButton.2
            @Override // com.mbridge.msdk.widget.custom.baseview.d
            public final void a() throws Resources.NotFoundException {
                if (MBCircularProgressButton.this.o != 0) {
                    MBCircularProgressButton.a(MBCircularProgressButton.this, MBCircularProgressButton.this.o);
                }
                MBCircularProgressButton.this.x = false;
                MBCircularProgressButton.this.k = a.COMPLETE;
                MBCircularProgressButton.this.j.b(MBCircularProgressButton.this);
            }
        };
        this.A = new d() { // from class: com.mbridge.msdk.widget.custom.baseview.MBCircularProgressButton.3
            @Override // com.mbridge.msdk.widget.custom.baseview.d
            public final void a() {
                MBCircularProgressButton.this.a();
                MBCircularProgressButton.this.x = false;
                MBCircularProgressButton.this.k = a.IDLE;
                MBCircularProgressButton.this.j.b(MBCircularProgressButton.this);
            }
        };
        this.B = new d() { // from class: com.mbridge.msdk.widget.custom.baseview.MBCircularProgressButton.4
            @Override // com.mbridge.msdk.widget.custom.baseview.d
            public final void a() throws Resources.NotFoundException {
                if (MBCircularProgressButton.this.p != 0) {
                    MBCircularProgressButton.a(MBCircularProgressButton.this, MBCircularProgressButton.this.p);
                }
                MBCircularProgressButton.this.x = false;
                MBCircularProgressButton.this.k = a.ERROR;
                MBCircularProgressButton.this.j.b(MBCircularProgressButton.this);
            }
        };
        a(context, attributeSet);
    }

    private void a(Context context, AttributeSet attributeSet) {
        this.q = 0;
        b(context, attributeSet);
        this.v = 100;
        this.k = a.IDLE;
        this.j = new e(this);
        b();
        setBackgroundCompat(this.g);
    }

    private void b() {
        int iA = a(this.d);
        int iB = b(this.d);
        int iC = c(this.d);
        int iD = d(this.d);
        if (this.a == null) {
            this.a = a(iA);
        }
        f fVarA = a(iD);
        f fVarA2 = a(iC);
        f fVarA3 = a(iB);
        this.g = new StateListDrawable();
        this.g.addState(new int[]{R.attr.state_pressed}, fVarA3.a());
        this.g.addState(new int[]{R.attr.state_focused}, fVarA2.a());
        this.g.addState(new int[]{-16842910}, fVarA.a());
        this.g.addState(StateSet.WILD_CARD, this.a.a());
    }

    private int a(ColorStateList colorStateList) {
        return colorStateList.getColorForState(new int[]{R.attr.state_enabled}, 0);
    }

    private int b(ColorStateList colorStateList) {
        return colorStateList.getColorForState(new int[]{R.attr.state_pressed}, 0);
    }

    private int c(ColorStateList colorStateList) {
        return colorStateList.getColorForState(new int[]{R.attr.state_focused}, 0);
    }

    private int d(ColorStateList colorStateList) {
        return colorStateList.getColorForState(new int[]{-16842910}, 0);
    }

    private f a(int i) {
        GradientDrawable gradientDrawable = (GradientDrawable) getResources().getDrawable(m.a(getContext(), "mbridge_cpb_background", "drawable")).mutate();
        gradientDrawable.setColor(i);
        gradientDrawable.setCornerRadius(this.s);
        f fVar = new f(gradientDrawable);
        fVar.b(i);
        fVar.a(this.q);
        return fVar;
    }

    protected void drawableStateChanged() {
        if (this.k != a.COMPLETE) {
            if (this.k == a.IDLE) {
                b();
                setBackgroundCompat(this.g);
            } else if (this.k == a.ERROR) {
                f fVarA = a(b(this.f));
                this.i = new StateListDrawable();
                this.i.addState(new int[]{R.attr.state_pressed}, fVarA.a());
                this.i.addState(StateSet.WILD_CARD, this.a.a());
                setBackgroundCompat(this.i);
            }
        } else {
            f fVarA2 = a(b(this.e));
            this.h = new StateListDrawable();
            this.h.addState(new int[]{R.attr.state_pressed}, fVarA2.a());
            this.h.addState(StateSet.WILD_CARD, this.a.a());
            setBackgroundCompat(this.h);
        }
        if (this.k != a.PROGRESS) {
            super.drawableStateChanged();
        }
    }

    private void b(Context context, AttributeSet attributeSet) {
        this.s = 100.0f;
        this.r = 0;
        int iB = b(m.a(context, "mbridge_cpb_green", "color"));
        int iB2 = b(m.a(context, "mbridge_cpb_white", "color"));
        int iB3 = b(m.a(context, "mbridge_cpb_grey", "color"));
        this.d = getResources().getColorStateList(m.a(context, "mbridge_cpb_idle_state_selector", "color"));
        this.e = getResources().getColorStateList(m.a(context, "mbridge_cpb_complete_state_selector", "color"));
        this.f = getResources().getColorStateList(m.a(context, "mbridge_cpb_error_state_selector", "color"));
        this.l = iB2;
        this.m = iB;
        this.n = iB3;
    }

    private int b(int i) {
        return getResources().getColor(i);
    }

    /* JADX WARN: Multi-variable type inference failed */
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.w <= 0 || this.k != a.PROGRESS || this.x) {
            return;
        }
        if (this.t) {
            if (this.b == null) {
                int width = (getWidth() - getHeight()) / 2;
                this.b = new com.mbridge.msdk.widget.custom.baseview.a(this.m, com.mbridge.msdk.widget.custom.b.a.a(getContext(), 2.0f));
                this.b.setBounds(this.r + width, this.r, (getWidth() - width) - this.r, getHeight() - this.r);
                this.b.setCallback(this);
                this.b.start();
                return;
            }
            this.b.draw(canvas);
            return;
        }
        if (this.c == null) {
            int width2 = (getWidth() - getHeight()) / 2;
            this.c = new b(getHeight() - (this.r * 2), com.mbridge.msdk.widget.custom.b.a.a(getContext(), 4), this.m);
            int i = width2 + this.r;
            this.c.setBounds(i, this.r, i, this.r);
        }
        this.c.a((360.0f / this.v) * this.w);
        this.c.draw(canvas);
    }

    public boolean isIndeterminateProgressMode() {
        return this.t;
    }

    public void setIndeterminateProgressMode(boolean z) {
        this.t = z;
    }

    protected boolean verifyDrawable(Drawable drawable) {
        return drawable == this.b || super.verifyDrawable(drawable);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private c c() {
        this.x = true;
        c cVar = new c(this, this.a);
        cVar.a(this.s);
        cVar.b(this.s);
        cVar.b(getWidth());
        cVar.c(getWidth());
        if (this.u) {
            cVar.a(1);
        } else {
            cVar.a(500);
        }
        this.u = false;
        return cVar;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private c a(float f, float f2, int i, int i2) {
        this.x = true;
        c cVar = new c(this, this.a);
        cVar.a(f);
        cVar.b(f2);
        cVar.c(this.r);
        cVar.b(i);
        cVar.c(i2);
        if (this.u) {
            cVar.a(1);
        } else {
            cVar.a(500);
        }
        this.u = false;
        return cVar;
    }

    protected final void a() {
        setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
        setPadding(0, 0, 0, 0);
    }

    public void setBackgroundCompat(Drawable drawable) {
        if (Build.VERSION.SDK_INT >= 16) {
            setBackground(drawable);
        } else {
            setBackgroundDrawable(drawable);
        }
    }

    public void setProgress(int i) {
        this.w = i;
        if (this.x || getWidth() == 0) {
            return;
        }
        this.j.a(this);
        if (this.w >= this.v) {
            if (this.k != a.PROGRESS) {
                if (this.k == a.IDLE) {
                    c cVarC = c();
                    cVarC.d(a(this.d));
                    cVarC.e(a(this.e));
                    cVarC.f(a(this.d));
                    cVarC.g(a(this.e));
                    cVarC.a(this.z);
                    cVarC.a();
                    return;
                }
                return;
            }
            c cVarA = a(getHeight(), this.s, getHeight(), getWidth());
            cVarA.d(this.l);
            cVarA.e(a(this.e));
            cVarA.f(this.m);
            cVarA.g(a(this.e));
            cVarA.a(this.z);
            cVarA.a();
            return;
        }
        if (this.w > 0) {
            if (this.k != a.IDLE) {
                if (this.k == a.PROGRESS) {
                    invalidate();
                    return;
                }
                return;
            }
            setWidth(getWidth());
            c cVarA2 = a(this.s, getHeight(), getWidth(), getHeight());
            cVarA2.d(a(this.d));
            cVarA2.e(this.l);
            cVarA2.f(a(this.d));
            cVarA2.g(this.n);
            cVarA2.a(this.y);
            cVarA2.a();
            return;
        }
        if (this.w == -1) {
            if (this.k != a.PROGRESS) {
                if (this.k == a.IDLE) {
                    c cVarC2 = c();
                    cVarC2.d(a(this.d));
                    cVarC2.e(a(this.f));
                    cVarC2.f(a(this.d));
                    cVarC2.g(a(this.f));
                    cVarC2.a(this.B);
                    cVarC2.a();
                    return;
                }
                return;
            }
            c cVarA3 = a(getHeight(), this.s, getHeight(), getWidth());
            cVarA3.d(this.l);
            cVarA3.e(a(this.f));
            cVarA3.f(this.m);
            cVarA3.g(a(this.f));
            cVarA3.a(this.B);
            cVarA3.a();
            return;
        }
        if (this.w == 0) {
            if (this.k != a.COMPLETE) {
                if (this.k == a.PROGRESS) {
                    if (this.t) {
                        c cVarA4 = a(getHeight(), this.s, getHeight(), getWidth());
                        cVarA4.d(this.l);
                        cVarA4.e(a(this.d));
                        cVarA4.f(this.m);
                        cVarA4.g(a(this.d));
                        cVarA4.a(new d() { // from class: com.mbridge.msdk.widget.custom.baseview.MBCircularProgressButton.5
                            @Override // com.mbridge.msdk.widget.custom.baseview.d
                            public final void a() {
                                MBCircularProgressButton.this.a();
                                MBCircularProgressButton.this.x = false;
                                MBCircularProgressButton.this.k = a.IDLE;
                                MBCircularProgressButton.this.j.b(MBCircularProgressButton.this);
                            }
                        });
                        cVarA4.a();
                        return;
                    }
                    return;
                }
                if (this.k == a.ERROR) {
                    c cVarC3 = c();
                    cVarC3.d(a(this.f));
                    cVarC3.e(a(this.d));
                    cVarC3.f(a(this.f));
                    cVarC3.g(a(this.d));
                    cVarC3.a(this.A);
                    cVarC3.a();
                    return;
                }
                return;
            }
            c cVarC4 = c();
            cVarC4.d(a(this.e));
            cVarC4.e(a(this.d));
            cVarC4.f(a(this.e));
            cVarC4.g(a(this.d));
            cVarC4.a(this.A);
            cVarC4.a();
        }
    }

    public int getProgress() {
        return this.w;
    }

    public void setBackgroundColor(int i) {
        this.a.a().setColor(i);
    }

    public void setStrokeColor(int i) {
        this.a.b(i);
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        if (z) {
            setProgress(this.w);
        }
    }

    public Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        savedState.c = this.w;
        savedState.a = this.t;
        savedState.b = true;
        return savedState;
    }

    public void onRestoreInstanceState(Parcelable parcelable) {
        if (parcelable instanceof SavedState) {
            SavedState savedState = (SavedState) parcelable;
            this.w = savedState.c;
            this.t = savedState.a;
            this.u = savedState.b;
            super.onRestoreInstanceState(savedState.getSuperState());
            setProgress(this.w);
            return;
        }
        super.onRestoreInstanceState(parcelable);
    }

    static class SavedState extends View.BaseSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.Creator<SavedState>() { // from class: com.mbridge.msdk.widget.custom.baseview.MBCircularProgressButton.SavedState.1
            @Override // android.os.Parcelable.Creator
            public final /* bridge */ /* synthetic */ SavedState[] newArray(int i) {
                return new SavedState[i];
            }

            @Override // android.os.Parcelable.Creator
            public final /* synthetic */ SavedState createFromParcel(Parcel parcel) {
                return new SavedState(parcel);
            }
        };
        private boolean a;
        private boolean b;
        private int c;

        public SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        private SavedState(Parcel parcel) {
            super(parcel);
            this.c = parcel.readInt();
            this.a = parcel.readInt() == 1;
            this.b = parcel.readInt() == 1;
        }

        @Override // android.view.View.BaseSavedState, android.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeInt(this.c);
            parcel.writeInt(this.a ? 1 : 0);
            parcel.writeInt(this.b ? 1 : 0);
        }
    }

    static /* synthetic */ void a(MBCircularProgressButton mBCircularProgressButton, int i) throws Resources.NotFoundException {
        Drawable drawable = mBCircularProgressButton.getResources().getDrawable(i);
        if (drawable != null) {
            int width = (mBCircularProgressButton.getWidth() / 2) - (drawable.getIntrinsicWidth() / 2);
            mBCircularProgressButton.setCompoundDrawablesWithIntrinsicBounds(i, 0, 0, 0);
            mBCircularProgressButton.setPadding(width, 0, 0, 0);
        }
    }
}
