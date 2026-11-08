package com.ss.union.game.sdk.common.ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.Editable;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import android.widget.EditText;
import com.ss.union.game.sdk.common.util.ResourceUtils;
import java.util.ArrayList;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class LGFormattedEditText extends EditText {
    public static final int GRAVITY_BOTTOM = 2;
    public static final int GRAVITY_CENTER = 1;
    public static final int GRAVITY_TOP = 0;
    public static final int MODE_COMPLEX = 1;
    public static final int MODE_SIMPLE = 0;
    private static final String a = " ";
    private static final String b = "*";
    private static final String c = "34443";
    private int d;
    private StringBuilder e;
    private b[] f;
    private String g;
    private String h;
    private int i;
    private int j;
    private boolean k;
    private List<TextWatcher> l;
    private String m;
    private InputFilter n;
    private Drawable o;
    private int p;
    private int q;
    private int r;
    private float[] s;
    private OnClearClickListener t;

    public interface OnClearClickListener {
        boolean onClearClick(LGFormattedEditText lGFormattedEditText, Drawable drawable);
    }

    public LGFormattedEditText(Context context) {
        super(context);
        this.e = new StringBuilder();
        this.j = 0;
        this.k = false;
        this.p = 1;
        this.r = 0;
        this.s = new float[2];
        a(context, (AttributeSet) null, 0);
    }

    public LGFormattedEditText(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.e = new StringBuilder();
        this.j = 0;
        this.k = false;
        this.p = 1;
        this.r = 0;
        this.s = new float[2];
        a(context, attributeSet, 0);
    }

    public LGFormattedEditText(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.e = new StringBuilder();
        this.j = 0;
        this.k = false;
        this.p = 1;
        this.r = 0;
        this.s = new float[2];
        a(context, attributeSet, i);
    }

    private void a(Context context, AttributeSet attributeSet, int i) {
        super.addTextChangedListener(new a());
        this.d = ViewConfiguration.get(getContext()).getScaledTouchSlop();
        if (attributeSet != null) {
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, ResourceUtils.getStyleableInts("styleable", "LGFormattedEditText"), i, 0);
            try {
                this.m = typedArrayObtainStyledAttributes.getString(ResourceUtils.getStyleableInt("styleable", "LGFormattedEditText_fet_mark"));
                setMode(typedArrayObtainStyledAttributes.getInt(ResourceUtils.getStyleableInt("styleable", "LGFormattedEditText_fet_mode"), 0));
                String string = typedArrayObtainStyledAttributes.getString(ResourceUtils.getStyleableInt("styleable", "LGFormattedEditText_fet_placeholder"));
                if (TextUtils.isEmpty(string)) {
                    string = a;
                }
                setPlaceholder(string);
                String string2 = typedArrayObtainStyledAttributes.getString(ResourceUtils.getStyleableInt("styleable", "LGFormattedEditText_fet_formatStyle"));
                if (TextUtils.isEmpty(string2)) {
                    string2 = c;
                }
                setFormatStyle(string2);
                this.o = typedArrayObtainStyledAttributes.getDrawable(ResourceUtils.getStyleableInt("styleable", "LGFormattedEditText_fet_clearDrawable"));
                this.p = typedArrayObtainStyledAttributes.getInt(ResourceUtils.getStyleableInt("styleable", "LGFormattedEditText_fet_drawableGravity"), 1);
                this.r = typedArrayObtainStyledAttributes.getDimensionPixelSize(ResourceUtils.getStyleableInt("styleable", "LGFormattedEditText_fet_drawablePadding"), 0);
            } finally {
                typedArrayObtainStyledAttributes.recycle();
            }
        } else {
            setPlaceholder(a);
        }
        setPadding(getPaddingLeft(), getPaddingTop(), getPaddingRight(), getPaddingBottom());
        Editable text = getText();
        if (this.f != null && this.f.length > 0 && text.length() > 0) {
            b(text, 0, text.length());
        }
        if (Build.VERSION.SDK_INT >= 17 && getLayoutDirection() == 1) {
            throw new UnsupportedOperationException("We can not support this feature when the layout is right-to-left");
        }
    }

    @Override // android.view.View
    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        a();
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0042  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x005c  */
    @Override // android.widget.TextView, android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    protected void onMeasure(int r8, int r9) {
        /*
            r7 = this;
            super.onMeasure(r8, r9)
            android.graphics.drawable.Drawable r0 = r7.o
            if (r0 == 0) goto L64
            android.graphics.drawable.Drawable r0 = r7.o
            int r0 = r0.getIntrinsicWidth()
            int r1 = r7.r
            int r1 = r1 * 2
            int r0 = r0 + r1
            android.graphics.drawable.Drawable r1 = r7.o
            int r1 = r1.getIntrinsicHeight()
            int r2 = r7.r
            int r2 = r2 * 2
            int r1 = r1 + r2
            int r2 = r7.getMeasuredWidth()
            int r3 = r7.getMeasuredHeight()
            r4 = -2147483648(0xffffffff80000000, float:-0.0)
            r5 = 1073741824(0x40000000, float:2.0)
            if (r2 >= r0) goto L42
            int r6 = android.view.View.MeasureSpec.getMode(r8)
            int r8 = android.view.View.MeasureSpec.getSize(r8)
            if (r6 == r5) goto L42
            int r0 = java.lang.Math.max(r0, r2)
            if (r6 != r4) goto L40
            int r8 = java.lang.Math.min(r0, r8)
            goto L43
        L40:
            r8 = r0
            goto L43
        L42:
            r8 = r2
        L43:
            if (r3 >= r1) goto L5c
            int r0 = android.view.View.MeasureSpec.getMode(r9)
            int r9 = android.view.View.MeasureSpec.getSize(r9)
            if (r0 == r5) goto L5c
            int r1 = java.lang.Math.max(r1, r3)
            if (r0 != r4) goto L5a
            int r9 = java.lang.Math.min(r1, r9)
            goto L5d
        L5a:
            r9 = r1
            goto L5d
        L5c:
            r9 = r3
        L5d:
            if (r8 != r2) goto L61
            if (r9 == r3) goto L64
        L61:
            r7.setMeasuredDimension(r8, r9)
        L64:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ss.union.game.sdk.common.ui.LGFormattedEditText.onMeasure(int, int):void");
    }

    @Override // android.widget.TextView
    public void addTextChangedListener(TextWatcher textWatcher) {
        if (this.l == null) {
            this.l = new ArrayList();
        }
        this.l.add(textWatcher);
    }

    @Override // android.widget.TextView
    public void removeTextChangedListener(TextWatcher textWatcher) {
        if (this.l != null) {
            this.l.remove(textWatcher);
        }
    }

    @Override // android.widget.TextView, android.view.View
    public void setPadding(int i, int i2, int i3, int i4) {
        this.q = i3;
        if (this.o != null) {
            i3 += this.o.getIntrinsicWidth() + (this.r * 2);
        }
        super.setPadding(i, i2, i3, i4);
        a();
    }

    @Override // android.widget.TextView, android.view.View
    public void setPaddingRelative(int i, int i2, int i3, int i4) {
        this.q = i3;
        if (this.o != null) {
            i3 += this.o.getIntrinsicWidth() + (this.r * 2);
        }
        super.setPaddingRelative(i, i2, i3, i4);
        a();
    }

    @Override // android.widget.TextView, android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.o == null || !isFocused() || length() <= 0) {
            return;
        }
        canvas.save();
        canvas.translate(getScrollX(), getScrollY());
        this.o.draw(canvas);
        canvas.restore();
    }

    @Override // android.widget.TextView, android.view.View
    protected void drawableStateChanged() {
        if (this.o != null) {
            int[] drawableState = getDrawableState();
            if (this.o.isStateful() && this.o.setState(drawableState)) {
                Rect bounds = this.o.getBounds();
                int scrollX = getScrollX();
                int scrollY = getScrollY();
                invalidate(bounds.left + scrollX, bounds.top + scrollY, bounds.right + scrollX, bounds.bottom + scrollY);
            }
        }
        super.drawableStateChanged();
    }

    @Override // android.widget.TextView, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (this.o != null) {
            float x = motionEvent.getX();
            float y = motionEvent.getY();
            switch (motionEvent.getActionMasked()) {
                case 0:
                    this.s[0] = x;
                    this.s[1] = y;
                    break;
                case 1:
                    Rect bounds = this.o.getBounds();
                    if (bounds.top - this.r <= y && bounds.bottom + this.r >= y && bounds.left - this.r <= x && bounds.right + this.r >= x && Math.abs(this.s[0] - x) <= this.d && Math.abs(this.s[1] - y) <= this.d) {
                        if (this.t == null || !this.t.onClearClick(this, this.o)) {
                            setText("");
                        }
                        super.onTouchEvent(motionEvent);
                        return true;
                    }
                    break;
            }
        }
        return super.onTouchEvent(motionEvent);
    }

    public void setClearDrawable(Drawable drawable) {
        if (this.o != drawable) {
            this.o = drawable;
            requestLayout();
        }
    }

    public void setClearDrawablePadding(int i) {
        if (this.r != i) {
            this.r = i;
            if (this.o != null) {
                requestLayout();
            }
        }
    }

    public void setOnClearClickListener(OnClearClickListener onClearClickListener) {
        this.t = onClearClickListener;
    }

    public void setFormatStyle(String str) {
        if (str != null) {
            if (this.j == 0) {
                if (TextUtils.isDigitsOnly(str)) {
                    this.f = new b[str.length()];
                    b bVar = new b();
                    bVar.a = Character.getNumericValue(str.charAt(0));
                    bVar.b = this.g;
                    this.f[0] = bVar;
                    for (int i = 1; i < str.length(); i++) {
                        int numericValue = Character.getNumericValue(str.charAt(i));
                        b bVar2 = new b();
                        bVar2.a = this.f[i - 1].a + 1 + numericValue;
                        bVar2.b = this.g;
                        this.f[i] = bVar2;
                    }
                    return;
                }
                throw new IllegalArgumentException("Format style must be numeric");
            }
            if (!str.contains(this.m)) {
                throw new IllegalArgumentException("Format style must be have Mark strings");
            }
            b[] bVarArr = new b[str.length()];
            StringBuilder sb = new StringBuilder();
            int i2 = 0;
            int i3 = 0;
            while (i2 < str.length()) {
                int i4 = i2 + 1;
                String strSubstring = str.substring(i2, i4);
                if (!strSubstring.equals(this.m)) {
                    if (sb.indexOf(strSubstring) < 0 && !TextUtils.isDigitsOnly(strSubstring)) {
                        sb.append(strSubstring);
                    }
                    b bVar3 = new b();
                    bVar3.a = i2;
                    bVar3.b = strSubstring;
                    bVarArr[i3] = bVar3;
                    i3++;
                }
                i2 = i4;
            }
            this.f = new b[i3];
            this.h = sb.toString();
            System.arraycopy(bVarArr, 0, this.f, 0, i3);
            a(bVarArr);
            return;
        }
        a(this.f);
        this.f = null;
    }

    @Override // android.widget.TextView
    public void setFilters(InputFilter[] inputFilterArr) {
        if (inputFilterArr == null) {
            throw new IllegalArgumentException("filters can not be null");
        }
        InputFilter[] inputFilterArr2 = new InputFilter[inputFilterArr.length + 1];
        if (this.n == null) {
            this.n = new c();
        }
        inputFilterArr2[0] = this.n;
        System.arraycopy(inputFilterArr, 0, inputFilterArr2, 1, inputFilterArr.length);
        super.setFilters(inputFilterArr2);
    }

    public void setMode(int i) {
        if (this.j != i) {
            String string = getText().toString();
            this.j = i;
            if (this.j == 1 && TextUtils.isEmpty(this.m)) {
                this.m = b;
            }
            if (TextUtils.isEmpty(string)) {
                return;
            }
            setText(string);
        }
    }

    public void setMark(String str) {
        if (str.length() > 1) {
            throw new IllegalArgumentException("Mark only supports length one strings");
        }
        this.m = str;
    }

    public void setPlaceholder(String str) {
        if (TextUtils.equals(this.g, str)) {
            return;
        }
        if (str.length() > 1) {
            throw new IllegalArgumentException("Placeholder only supports length one strings");
        }
        if (this.f != null) {
            for (b bVar : this.f) {
                bVar.b = str;
            }
        }
        this.g = str;
        if (this.j == 0) {
            this.h = str;
        }
    }

    public String getPhone() {
        return getText().toString().replace(a, "");
    }

    public String getRealText() {
        String string = this.e.toString();
        StringBuilder sb = new StringBuilder();
        int i = 0;
        for (int i2 = 0; i2 < string.length(); i2++) {
            if (i >= this.f.length) {
                sb.append(string.substring(i2));
                return sb.toString();
            }
            if (this.f[i].a == i2) {
                i++;
            } else {
                sb.append((CharSequence) string, i2, i2 + 1);
            }
        }
        return sb.toString();
    }

    private void a() {
        if (this.o != null) {
            int paddingTop = getPaddingTop() + this.r;
            int paddingBottom = getPaddingBottom() + this.r;
            int intrinsicWidth = this.o.getIntrinsicWidth();
            int intrinsicHeight = this.o.getIntrinsicHeight();
            int width = (getWidth() - this.q) - this.r;
            int height = getHeight();
            switch (this.p) {
                case 0:
                    this.o.setBounds(width - intrinsicWidth, paddingTop, width, intrinsicHeight + paddingTop);
                    break;
                case 1:
                    int i = paddingTop + ((((height - paddingTop) - paddingBottom) - intrinsicHeight) / 2);
                    this.o.setBounds(width - intrinsicWidth, i, width, intrinsicHeight + i);
                    break;
                default:
                    int i2 = height - paddingBottom;
                    this.o.setBounds(width - intrinsicWidth, i2 - intrinsicHeight, width, i2);
                    break;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(CharSequence charSequence, int i, int i2, int i3) {
        List<TextWatcher> list = this.l;
        if (list != null) {
            int size = list.size();
            for (int i4 = 0; i4 < size; i4++) {
                list.get(i4).beforeTextChanged(charSequence, i, i2, i3);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(CharSequence charSequence, int i, int i2, int i3) {
        List<TextWatcher> list = this.l;
        if (list != null) {
            int size = list.size();
            for (int i4 = 0; i4 < size; i4++) {
                list.get(i4).onTextChanged(charSequence, i, i2, i3);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(Editable editable) {
        List<TextWatcher> list = this.l;
        if (list != null) {
            int size = list.size();
            for (int i = 0; i < size; i++) {
                list.get(i).afterTextChanged(editable);
            }
        }
    }

    private void a(b[] bVarArr) {
        if (bVarArr != null) {
            for (int i = 0; i < bVarArr.length; i++) {
                bVarArr[i] = null;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(CharSequence charSequence, int i, int i2) {
        String string = this.e.toString();
        String string2 = charSequence.toString();
        boolean z = i >= string2.length();
        this.e.delete(i, string.length());
        if (!z) {
            a(string2, i, 0);
        }
        String string3 = this.e.toString();
        this.i = this.f.length / 2;
        int i3 = i;
        int i4 = i3;
        while (i3 > 0) {
            int i5 = i3 - 1;
            if (!string3.substring(i5, i3).equals(a(i5))) {
                break;
            }
            if (z) {
                this.e.delete(i5, i3);
            }
            i4--;
            i3--;
        }
        this.k = true;
        String string4 = this.e.toString();
        int length = string.length() - string4.length();
        a(string, i4, length, 0);
        if (!z || i4 != i || length != i2) {
            setText(string4);
            if (length() >= i4) {
                setSelection(i4);
            } else {
                setSelection(length());
            }
        }
        this.k = false;
        b(string4, i4, length, 0);
        a(getText());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(CharSequence charSequence, int i, int i2) {
        int iA;
        String string = this.e.toString();
        String string2 = charSequence.toString();
        boolean z = i > this.f[this.f.length - 1].a;
        if (!z) {
            this.e.delete(i, string.length());
            iA = a(string2, i, i2);
        } else {
            int i3 = i + i2;
            this.e.insert(i, string2.substring(i, i3));
            iA = i3;
        }
        this.k = true;
        String string3 = this.e.toString();
        int length = string3.length() - string.length();
        a(string, i, length, 0);
        if (!z || iA != i + i2 || length != i2) {
            setText(string3);
            if (length() >= iA) {
                setSelection(iA);
            } else {
                setSelection(length());
            }
        }
        this.k = false;
        b(string3, i, length, 0);
        a(getText());
    }

    private int a(String str, int i, int i2) {
        int i3;
        int length = str.length();
        int i4 = this.f[this.f.length - 1].a;
        this.i = this.f.length / 2;
        int i5 = i;
        int i6 = i5;
        int i7 = i2;
        while (i < length) {
            if (this.e.length() > i4 + 1) {
                int i8 = 0;
                if (i7 < 0) {
                    i7 = 0;
                }
                int i9 = i5 + i7;
                if (i2 > 0 && length >= (i3 = i4 + i2)) {
                    int i10 = i3 + 1;
                    if (length < i10) {
                        i10 = i3;
                    }
                    String strSubstring = str.substring(i, i10);
                    int length2 = strSubstring.length();
                    while (i8 < length2) {
                        int i11 = i8 + 1;
                        String strSubstring2 = strSubstring.substring(i8, i11);
                        if (!this.h.contains(strSubstring2)) {
                            this.e.append(strSubstring2);
                        }
                        i8 = i11;
                    }
                    this.e.append(str.substring(i10));
                    return i9;
                }
                this.e.append(str.substring(i));
                return i9;
            }
            String strSubstring3 = str.substring(i, i + 1);
            if (!this.h.contains(strSubstring3)) {
                String strA = a(i6);
                if (strA != null && (i2 > 0 || !TextUtils.equals(strA, strSubstring3))) {
                    this.e.append(strA);
                    i--;
                    i6++;
                    if (i7 >= 0) {
                        i5++;
                    }
                } else {
                    this.e.append(strSubstring3);
                    i6++;
                    i7--;
                    if (i7 >= 0) {
                        i5++;
                    }
                }
            } else if (i7 >= 0) {
                i7--;
            }
            i++;
        }
        return i5;
    }

    private String a(int i) {
        int length = this.f.length;
        int i2 = this.i;
        int i3 = this.f[i2].a;
        if (i3 == i) {
            return this.f[i2].b;
        }
        if (i3 < i) {
            while (i2 < length) {
                this.i = i2;
                if (this.f[i2].a == i) {
                    return this.f[i2].b;
                }
                if (this.f[i2].a > i) {
                    return null;
                }
                i2++;
            }
        } else {
            while (i2 >= 0) {
                this.i = i2;
                if (this.f[i2].a == i) {
                    return this.f[i2].b;
                }
                if (this.f[i2].a < i) {
                    return null;
                }
                i2--;
            }
        }
        return null;
    }

    private static class b {
        int a;
        String b;

        private b() {
        }
    }

    private class c implements InputFilter {
        private StringBuilder b;

        private c() {
            this.b = new StringBuilder();
        }

        @Override // android.text.InputFilter
        public CharSequence filter(CharSequence charSequence, int i, int i2, Spanned spanned, int i3, int i4) {
            if (LGFormattedEditText.this.h == null || LGFormattedEditText.this.k || charSequence.length() == 0) {
                return null;
            }
            int i5 = 0;
            this.b.setLength(0);
            int length = charSequence.length();
            while (i5 < length) {
                int i6 = i5 + 1;
                CharSequence charSequenceSubSequence = charSequence.subSequence(i5, i6);
                if (!LGFormattedEditText.this.h.contains(charSequenceSubSequence)) {
                    this.b.append(charSequenceSubSequence);
                }
                i5 = i6;
            }
            return this.b;
        }
    }

    private class a implements TextWatcher {
        private a() {
        }

        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            if (LGFormattedEditText.this.f == null || LGFormattedEditText.this.f.length == 0) {
                LGFormattedEditText.this.a(charSequence, i, i2, i3);
            }
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            if (LGFormattedEditText.this.f == null || LGFormattedEditText.this.f.length == 0) {
                LGFormattedEditText.this.b(charSequence, i, i2, i3);
            } else {
                if (LGFormattedEditText.this.k) {
                    return;
                }
                if (i3 == 0) {
                    LGFormattedEditText.this.a(charSequence, i, i2);
                } else {
                    LGFormattedEditText.this.b(charSequence, i, i3);
                }
            }
        }

        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable editable) {
            if (LGFormattedEditText.this.f == null || LGFormattedEditText.this.f.length == 0) {
                LGFormattedEditText.this.a(editable);
            }
            if (editable.length() != 0 || LGFormattedEditText.this.e.length() == 0) {
                return;
            }
            LGFormattedEditText.this.e.setLength(0);
        }
    }
}
