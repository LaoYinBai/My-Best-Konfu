package com.sigmob.sdk.nativead;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.ss.union.game.sdk.common.ui.roundedimageview.RoundedDrawable;
import java.util.ArrayList;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class SigFlowLayout extends ViewGroup {
    private final int a;
    private final int b;
    private final int c;
    private final int d;
    private final float e;
    private final float f;
    private final float g;
    private final float h;
    private final float i;
    private int j;
    private int k;
    private boolean l;
    private int m;
    private int n;
    private float o;
    private int p;
    private int q;
    private int r;
    private int s;
    private boolean t;
    private boolean u;
    private float[] v;
    private d w;
    private a x;
    private int y;

    static class SavedState extends View.BaseSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.Creator<SavedState>() { // from class: com.sigmob.sdk.nativead.SigFlowLayout.SavedState.1
            @Override // android.os.Parcelable.Creator
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public SavedState createFromParcel(Parcel parcel) {
                return new SavedState(parcel);
            }

            @Override // android.os.Parcelable.Creator
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public SavedState[] newArray(int i) {
                return new SavedState[i];
            }
        };
        int a;
        String[] b;
        int c;

        public SavedState(Parcel parcel) {
            super(parcel);
            this.a = parcel.readInt();
            this.b = new String[this.a];
            parcel.readStringArray(this.b);
            this.c = parcel.readInt();
        }

        public SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        @Override // android.view.View.BaseSavedState, android.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            this.a = this.b.length;
            parcel.writeInt(this.a);
            parcel.writeStringArray(this.b);
            parcel.writeInt(this.c);
        }
    }

    class a implements View.OnClickListener {
        a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (SigFlowLayout.this.l) {
                b bVar = (b) view;
                int selectedIndex = -1;
                b selectedItem = SigFlowLayout.this.getSelectedItem();
                int i = 0;
                if (SigFlowLayout.this.t) {
                    bVar.a(!bVar.b);
                    int childCount = SigFlowLayout.this.getChildCount();
                    while (true) {
                        if (i >= childCount) {
                            break;
                        }
                        if (SigFlowLayout.this.a(i) == bVar) {
                            selectedIndex = i;
                            break;
                        }
                        i++;
                    }
                } else {
                    if (selectedItem != null) {
                        selectedItem.a(false);
                    }
                    bVar.a(true);
                    selectedIndex = SigFlowLayout.this.getSelectedIndex();
                }
                if (SigFlowLayout.this.w != null) {
                    SigFlowLayout.this.w.a(selectedIndex, bVar.getText().toString());
                }
            }
        }
    }

    class b extends TextView {
        private boolean b;
        private Paint c;
        private Rect d;

        public b(Context context, CharSequence charSequence) {
            super(context);
            this.b = false;
            this.c = new Paint(1);
            this.d = new Rect();
            this.c.setStyle(Paint.Style.FILL);
            setPadding(SigFlowLayout.this.r, SigFlowLayout.this.s, SigFlowLayout.this.r, SigFlowLayout.this.s);
            setLayoutParams(new c(SigFlowLayout.this.y, -2));
            setGravity(17);
            setTextSize(0, SigFlowLayout.this.o);
            setSingleLine(true);
            setEllipsize(TextUtils.TruncateAt.valueOf("END"));
            setMaxEms(4);
            setText(charSequence);
            setClickable(true);
            a();
        }

        private void a() {
            int i;
            SigFlowLayout.this.u = false;
            if (this.b) {
                this.c.setColor(SigFlowLayout.this.n);
                i = SigFlowLayout.this.m;
            } else {
                this.c.setColor(SigFlowLayout.this.k);
                i = SigFlowLayout.this.j;
            }
            setTextColor(i);
        }

        private void b() {
            int i = this.b ? SigFlowLayout.this.n : SigFlowLayout.this.k;
            GradientDrawable gradientDrawable = new GradientDrawable();
            gradientDrawable.setCornerRadii(SigFlowLayout.this.v);
            gradientDrawable.setColor(i);
            if (Build.VERSION.SDK_INT < 16) {
                setBackgroundDrawable(gradientDrawable);
            } else {
                setBackground(gradientDrawable);
            }
        }

        public void a(boolean z) {
            this.b = z;
            a();
        }

        @Override // android.widget.TextView
        protected boolean getDefaultEditable() {
            return false;
        }

        @Override // android.widget.TextView, android.view.View
        protected void onDraw(Canvas canvas) {
            if (!SigFlowLayout.this.u) {
                b();
            }
            super.onDraw(canvas);
        }

        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        /* JADX WARN: Removed duplicated region for block: B:9:0x0020  */
        @Override // android.widget.TextView, android.view.View
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public boolean onTouchEvent(android.view.MotionEvent r4) {
            /*
                r3 = this;
                int r0 = r4.getAction()
                switch(r0) {
                    case 0: goto L1b;
                    case 1: goto L20;
                    case 2: goto L8;
                    default: goto L7;
                }
            L7:
                goto L26
            L8:
                android.graphics.Rect r0 = r3.d
                float r1 = r4.getX()
                int r1 = (int) r1
                float r2 = r4.getY()
                int r2 = (int) r2
                boolean r0 = r0.contains(r1, r2)
                if (r0 != 0) goto L26
                goto L20
            L1b:
                android.graphics.Rect r0 = r3.d
                r3.getDrawingRect(r0)
            L20:
                r3.a()
                r3.invalidate()
            L26:
                boolean r4 = super.onTouchEvent(r4)
                return r4
            */
            throw new UnsupportedOperationException("Method not decompiled: com.sigmob.sdk.nativead.SigFlowLayout.b.onTouchEvent(android.view.MotionEvent):boolean");
        }
    }

    public static class c extends ViewGroup.LayoutParams {
        public c(int i, int i2) {
            super(i, i2);
        }

        public c(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }
    }

    public interface d {
        void a(int i, String str);
    }

    public SigFlowLayout(Context context) {
        this(context, null);
    }

    public SigFlowLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public SigFlowLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.a = RoundedDrawable.DEFAULT_BORDER_COLOR;
        this.b = Color.parseColor("#EEEEEE");
        this.c = -1;
        this.d = Color.parseColor("#FE7E03");
        this.u = false;
        this.v = new float[]{0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f};
        this.x = new a();
        int i2 = context.getResources().getDisplayMetrics().widthPixels;
        int i3 = context.getResources().getDisplayMetrics().heightPixels;
        int iA = (int) a(100.0f);
        if (i3 > i2) {
            this.y = (i2 - iA) / 3;
        } else {
            this.y = (i3 - iA) / 3;
        }
        this.e = b(13.0f);
        this.f = a(10.0f);
        this.g = a(10.0f);
        this.h = a(10.0f);
        this.i = a(5.0f);
        this.j = RoundedDrawable.DEFAULT_BORDER_COLOR;
        this.k = this.b;
        this.m = -1;
        this.n = this.d;
        this.o = this.e;
        this.p = (int) this.f;
        this.q = (int) this.g;
        this.r = (int) this.h;
        this.s = (int) this.i;
        this.t = false;
        this.l = true;
    }

    private float a(float f) {
        return TypedValue.applyDimension(1, f, getResources().getDisplayMetrics());
    }

    private void a(CharSequence charSequence) {
        b bVar = new b(getContext(), charSequence);
        bVar.setOnClickListener(this.x);
        addView(bVar);
    }

    private int b(float f) {
        return (int) TypedValue.applyDimension(2, f, getResources().getDisplayMetrics());
    }

    protected b a(int i) {
        if (getChildAt(i) == null) {
            return null;
        }
        return (b) getChildAt(i);
    }

    public void a() {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            b bVarA = a(i);
            if (bVarA != null && bVarA.b) {
                bVarA.a(false);
            }
        }
    }

    @Override // android.view.ViewGroup
    public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new c(getContext(), attributeSet);
    }

    public ArrayList<Integer> getAllItemSelectedIndex() {
        int childCount = getChildCount();
        ArrayList<Integer> arrayList = new ArrayList<>();
        for (int i = 0; i < childCount; i++) {
            if (a(i).b) {
                arrayList.add(Integer.valueOf(i));
            }
        }
        return arrayList;
    }

    public ArrayList<String> getAllItemSelectedTextWithListArray() {
        int childCount = getChildCount();
        ArrayList<String> arrayList = new ArrayList<>();
        for (int i = 0; i < childCount; i++) {
            b bVarA = a(i);
            if (bVarA.b) {
                arrayList.add(bVarA.getText().toString());
            }
        }
        return arrayList;
    }

    public String[] getAllItemSelectedTextWithStringArray() {
        int childCount = getChildCount();
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < childCount; i++) {
            b bVarA = a(i);
            if (bVarA.b) {
                arrayList.add(bVarA.getText().toString());
            }
        }
        return (String[]) arrayList.toArray(new String[arrayList.size()]);
    }

    public String[] getAllItemText() {
        int childCount = getChildCount();
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < childCount; i++) {
            arrayList.add(a(i).getText().toString());
        }
        return (String[]) arrayList.toArray(new String[arrayList.size()]);
    }

    public int getSelectedIndex() {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            if (a(i).b) {
                return i;
            }
        }
        return -1;
    }

    protected b getSelectedItem() {
        int selectedIndex = getSelectedIndex();
        if (selectedIndex != -1) {
            return a(selectedIndex);
        }
        return null;
    }

    protected String getSelectedItemText() {
        if (getSelectedItem() != null) {
            return getSelectedItem().getText().toString();
        }
        return null;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int paddingLeft = getPaddingLeft();
        int paddingRight = (i3 - i) - getPaddingRight();
        int paddingTop = getPaddingTop();
        getPaddingBottom();
        int childCount = getChildCount();
        int i5 = paddingTop;
        int iMax = 0;
        int i6 = paddingLeft;
        for (int i7 = 0; i7 < childCount; i7++) {
            View childAt = getChildAt(i7);
            int measuredWidth = childAt.getMeasuredWidth();
            int measuredHeight = childAt.getMeasuredHeight();
            if (childAt.getVisibility() != 8) {
                if (i6 + measuredWidth > paddingRight) {
                    i5 += iMax + this.q;
                    i6 = paddingLeft;
                    iMax = measuredHeight;
                } else {
                    iMax = Math.max(iMax, measuredHeight);
                }
                childAt.layout(i6, i5, i6 + measuredWidth, measuredHeight + i5);
                i6 += measuredWidth + this.p;
            }
        }
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        int mode = View.MeasureSpec.getMode(i);
        int mode2 = View.MeasureSpec.getMode(i2);
        int size = View.MeasureSpec.getSize(i);
        int size2 = View.MeasureSpec.getSize(i2);
        measureChildren(i, i2);
        int childCount = getChildCount();
        int i3 = 0;
        int i4 = 0;
        int i5 = 0;
        int i6 = 0;
        for (int i7 = 0; i7 < childCount; i7++) {
            View childAt = getChildAt(i7);
            int measuredWidth = childAt.getMeasuredWidth();
            int measuredHeight = childAt.getMeasuredHeight();
            if (childAt.getVisibility() != 8) {
                int i8 = i6 + measuredWidth;
                if (i8 > size) {
                    i3 += i4 + this.q;
                    i5++;
                    i8 = measuredWidth;
                } else {
                    measuredHeight = Math.max(i4, measuredHeight);
                }
                i6 = i8 + this.p;
                i4 = measuredHeight;
            }
        }
        int paddingTop = getPaddingTop() + getPaddingBottom() + i3 + i4;
        int paddingLeft = i5 == 0 ? getPaddingLeft() + getPaddingRight() + i6 : size;
        if (mode == 1073741824) {
            paddingLeft = size;
        }
        if (mode2 == 1073741824) {
            paddingTop = size2;
        }
        setMeasuredDimension(paddingLeft, paddingTop);
    }

    @Override // android.view.View
    public void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof SavedState)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.getSuperState());
        setList(savedState.b);
        b bVarA = a(savedState.c);
        if (bVarA != null) {
            bVarA.a(true);
        }
    }

    @Override // android.view.View
    public Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        savedState.b = getAllItemText();
        savedState.c = getSelectedIndex();
        return savedState;
    }

    public void setIndexListItemSelected(List<Integer> list) {
        if (list == null || list.isEmpty() || list.size() == 0) {
            return;
        }
        if (list.size() > getChildCount()) {
            return;
        }
        for (int i = 0; i < list.size(); i++) {
            a(i).a(true);
        }
    }

    public void setList(List<String> list) {
        setList((String[]) list.toArray(new String[list.size()]));
    }

    public void setList(String... strArr) {
        removeAllViews();
        for (String str : strArr) {
            a(str);
        }
    }

    public void setOnItemClickListener(d dVar) {
        this.w = dVar;
    }
}
