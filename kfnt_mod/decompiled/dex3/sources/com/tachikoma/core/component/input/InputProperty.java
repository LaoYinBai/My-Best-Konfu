package com.tachikoma.core.component.input;

import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import com.tachikoma.core.component.text.FontManager;
import java.lang.reflect.Field;
import java.util.LinkedList;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class InputProperty {
    private static final InputFilter[] EMPTY_FILTERS = new InputFilter[0];
    private boolean isSingleLine;
    private int mStagedInputTypeFlags;
    private final EditText mView;
    private MaxLinesTextWatcher maxLinesTextWatcher = new MaxLinesTextWatcher(3);
    private final View.OnFocusChangeListener mOnFocusChangeListener = new View.OnFocusChangeListener() { // from class: com.tachikoma.core.component.input.InputProperty.1
        @Override // android.view.View.OnFocusChangeListener
        public void onFocusChange(View view, boolean z) {
        }
    };

    private class MaxLinesTextWatcher implements TextWatcher {
        int maxLines;

        public MaxLinesTextWatcher(int i) {
            this.maxLines = i;
        }

        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable editable) {
            String strSubstring;
            if (InputProperty.this.mView.getLineCount() > this.maxLines) {
                String string = editable.toString();
                int selectionStart = InputProperty.this.mView.getSelectionStart();
                if (selectionStart != InputProperty.this.mView.getSelectionEnd() || selectionStart >= string.length() || selectionStart < 1) {
                    strSubstring = string.substring(0, editable.length() - 1);
                } else {
                    strSubstring = string.substring(0, selectionStart - 1) + string.substring(selectionStart);
                }
                InputProperty.this.mView.setText(strSubstring);
                InputProperty.this.mView.setSelection(InputProperty.this.mView.getText().length());
            }
        }

        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        public void setMaxLines(int i) {
            this.maxLines = i;
        }
    }

    public InputProperty(EditText editText, boolean z) {
        this.mStagedInputTypeFlags = editText.getInputType();
        this.isSingleLine = z;
        this.mView = editText;
        this.mView.setPadding(0, 0, 0, 0);
        this.mView.setSingleLine(z);
        this.mView.setOnFocusChangeListener(this.mOnFocusChangeListener);
        if (this.isSingleLine) {
            return;
        }
        this.mView.addTextChangedListener(this.maxLinesTextWatcher);
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x0024  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private int getGravity(java.lang.String r4) {
        /*
            r3 = this;
            int r0 = r4.hashCode()
            r1 = -1364013995(0xffffffffaeb2cc55, float:-8.1307995E-11)
            r2 = 1
            if (r0 == r1) goto L1a
            r1 = 108511772(0x677c21c, float:4.6598146E-35)
            if (r0 == r1) goto L10
            goto L24
        L10:
            java.lang.String r0 = "right"
            boolean r4 = r4.equals(r0)
            if (r4 == 0) goto L24
            r4 = 1
            goto L25
        L1a:
            java.lang.String r0 = "center"
            boolean r4 = r4.equals(r0)
            if (r4 == 0) goto L24
            r4 = 0
            goto L25
        L24:
            r4 = -1
        L25:
            switch(r4) {
                case 0: goto L38;
                case 1: goto L2f;
                default: goto L28;
            }
        L28:
            boolean r4 = r3.isSingleLine
            if (r4 == 0) goto L3f
            r4 = 19
            goto L40
        L2f:
            boolean r4 = r3.isSingleLine
            if (r4 == 0) goto L36
            r4 = 21
            goto L37
        L36:
            r4 = 5
        L37:
            return r4
        L38:
            boolean r4 = r3.isSingleLine
            if (r4 == 0) goto L3e
            r2 = 17
        L3e:
            return r2
        L3f:
            r4 = 3
        L40:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tachikoma.core.component.input.InputProperty.getGravity(java.lang.String):int");
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x0042  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private int getImeOption(java.lang.String r5) {
        /*
            r4 = this;
            int r0 = r5.hashCode()
            r1 = -906336856(0xffffffffc9fa65a8, float:-2051253.0)
            r2 = 3
            r3 = 2
            if (r0 == r1) goto L38
            r1 = 3304(0xce8, float:4.63E-42)
            if (r0 == r1) goto L2e
            r1 = 3377907(0x338af3, float:4.733456E-39)
            if (r0 == r1) goto L24
            r1 = 3526536(0x35cf88, float:4.94173E-39)
            if (r0 == r1) goto L1a
            goto L42
        L1a:
            java.lang.String r0 = "send"
            boolean r5 = r5.equals(r0)
            if (r5 == 0) goto L42
            r5 = 2
            goto L43
        L24:
            java.lang.String r0 = "next"
            boolean r5 = r5.equals(r0)
            if (r5 == 0) goto L42
            r5 = 3
            goto L43
        L2e:
            java.lang.String r0 = "go"
            boolean r5 = r5.equals(r0)
            if (r5 == 0) goto L42
            r5 = 0
            goto L43
        L38:
            java.lang.String r0 = "search"
            boolean r5 = r5.equals(r0)
            if (r5 == 0) goto L42
            r5 = 1
            goto L43
        L42:
            r5 = -1
        L43:
            switch(r5) {
                case 0: goto L4d;
                case 1: goto L4c;
                case 2: goto L4a;
                case 3: goto L48;
                default: goto L46;
            }
        L46:
            r5 = 6
            return r5
        L48:
            r5 = 5
            return r5
        L4a:
            r5 = 4
            return r5
        L4c:
            return r2
        L4d:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tachikoma.core.component.input.InputProperty.getImeOption(java.lang.String):int");
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x0044  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private int getInputType(java.lang.String r6) {
        /*
            r5 = this;
            int r0 = r6.hashCode()
            r1 = -1034364087(0xffffffffc258db49, float:-54.214146)
            r2 = 3
            r3 = 2
            r4 = 0
            if (r0 == r1) goto L3a
            r1 = 114715(0x1c01b, float:1.6075E-40)
            if (r0 == r1) goto L30
            r1 = 96619420(0x5c24b9c, float:1.8271447E-35)
            if (r0 == r1) goto L26
            r1 = 1216985755(0x4889ba9b, float:282068.84)
            if (r0 == r1) goto L1c
            goto L44
        L1c:
            java.lang.String r0 = "password"
            boolean r6 = r6.equals(r0)
            if (r6 == 0) goto L44
            r6 = 3
            goto L45
        L26:
            java.lang.String r0 = "email"
            boolean r6 = r6.equals(r0)
            if (r6 == 0) goto L44
            r6 = 0
            goto L45
        L30:
            java.lang.String r0 = "tel"
            boolean r6 = r6.equals(r0)
            if (r6 == 0) goto L44
            r6 = 2
            goto L45
        L3a:
            java.lang.String r0 = "number"
            boolean r6 = r6.equals(r0)
            if (r6 == 0) goto L44
            r6 = 1
            goto L45
        L44:
            r6 = -1
        L45:
            switch(r6) {
                case 0: goto L4f;
                case 1: goto L4d;
                case 2: goto L51;
                case 3: goto L4a;
                default: goto L48;
            }
        L48:
            r2 = 0
            goto L51
        L4a:
            r2 = 128(0x80, float:1.8E-43)
            goto L51
        L4d:
            r2 = 2
            goto L51
        L4f:
            r2 = 32
        L51:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tachikoma.core.component.input.InputProperty.getInputType(java.lang.String):int");
    }

    private void setStagedInputTypeFlags(int i, int i2) {
        this.mStagedInputTypeFlags = ((~i) & this.mStagedInputTypeFlags) | i2;
        this.mView.setInputType(this.mStagedInputTypeFlags);
    }

    public String getText() {
        return this.mView.getText().toString();
    }

    public void setCursorColor(int i) throws IllegalAccessException, NoSuchFieldException, IllegalArgumentException {
        Class<?> type;
        try {
            Field declaredField = TextView.class.getDeclaredField("mCursorDrawableRes");
            declaredField.setAccessible(true);
            int i2 = declaredField.getInt(this.mView);
            if (i2 == 0) {
                return;
            }
            Drawable drawable = ContextCompat.getDrawable(this.mView.getContext(), i2);
            drawable.setColorFilter(i, PorterDuff.Mode.SRC_IN);
            Drawable[] drawableArr = {drawable, drawable};
            Field declaredField2 = TextView.class.getDeclaredField("mEditor");
            declaredField2.setAccessible(true);
            Object obj = declaredField2.get(this.mView);
            if (Build.VERSION.SDK_INT < 16) {
                type = TextView.class;
            } else {
                Field declaredField3 = TextView.class.getDeclaredField("mEditor");
                declaredField3.setAccessible(true);
                type = declaredField3.getType();
            }
            if (Build.VERSION.SDK_INT >= 28) {
                Field declaredField4 = type.getDeclaredField("mDrawableForCursor");
                declaredField4.setAccessible(true);
                declaredField4.set(obj, drawable);
            } else {
                Field declaredField5 = type.getDeclaredField("mCursorDrawable");
                declaredField5.setAccessible(true);
                declaredField5.set(obj, drawableArr);
            }
        } catch (IllegalAccessException | NoSuchFieldException unused) {
        }
    }

    public void setFocused(boolean z) {
        if (!z) {
            this.mView.clearFocus();
        } else {
            this.mView.requestFocus();
            ((InputMethodManager) this.mView.getContext().getSystemService("input_method")).toggleSoftInput(2, 2);
        }
    }

    public void setFontFamily(String str, String str2) {
        int style = this.mView.getTypeface() != null ? this.mView.getTypeface().getStyle() : 0;
        String strConcat = "";
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
            strConcat = str2.concat(str);
        }
        this.mView.setTypeface(FontManager.getInstance().getTypeface(str, style, this.mView.getContext().getAssets(), strConcat));
    }

    public void setFontSize(float f) {
        this.mView.setTextSize(1, f);
    }

    public void setMaxLength(int i) {
        InputFilter[] filters = this.mView.getFilters();
        InputFilter[] inputFilterArr = EMPTY_FILTERS;
        if (i == 0) {
            if (filters.length > 0) {
                LinkedList linkedList = new LinkedList();
                for (int i2 = 0; i2 < filters.length; i2++) {
                    if (!(filters[i2] instanceof InputFilter.LengthFilter)) {
                        linkedList.add(filters[i2]);
                    }
                }
                if (!linkedList.isEmpty()) {
                    inputFilterArr = (InputFilter[]) linkedList.toArray(new InputFilter[linkedList.size()]);
                }
            }
        } else if (filters.length > 0) {
            boolean z = false;
            for (int i3 = 0; i3 < filters.length; i3++) {
                if (filters[i3] instanceof InputFilter.LengthFilter) {
                    filters[i3] = new InputFilter.LengthFilter(i);
                    z = true;
                }
            }
            if (z) {
                inputFilterArr = filters;
            } else {
                inputFilterArr = new InputFilter[filters.length + 1];
                System.arraycopy(filters, 0, inputFilterArr, 0, filters.length);
                filters[filters.length] = new InputFilter.LengthFilter(i);
            }
        } else {
            inputFilterArr = new InputFilter[]{new InputFilter.LengthFilter(i)};
        }
        this.mView.setFilters(inputFilterArr);
    }

    public void setMaxLines(int i) {
        this.maxLinesTextWatcher.setMaxLines(i);
    }

    public void setPlaceholder(String str) {
        this.mView.setHint(str);
    }

    public void setPlaceholderColor(int i) {
        this.mView.setHintTextColor(i);
    }

    public void setPlaceholderFontSize(float f) {
        this.mView.setTextSize(1, f);
    }

    public void setReturnKeyType(String str) {
        this.mView.setImeOptions(getImeOption(str));
    }

    public void setSecureTextEntry(boolean z) {
        if (z) {
            setStagedInputTypeFlags(144, 128);
        } else {
            setStagedInputTypeFlags(128, 144);
        }
    }

    public void setText(String str) {
        this.mView.setText(str);
        if (this.mView.getText().length() > 0) {
            this.mView.setSelection(this.mView.getText().length());
        }
    }

    public void setTextAlign(String str) {
        this.mView.setGravity(getGravity(str));
    }

    public void setTextColor(int i) {
        this.mView.setTextColor(i);
    }

    public void setType(String str) {
        setStagedInputTypeFlags(0, getInputType(str));
    }
}
