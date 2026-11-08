package com.ss.union.game.sdk.common.ui.verifyCode;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.text.Editable;
import android.text.InputFilter;
import android.text.Selection;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.ss.android.socialbase.downloader.utils.DownloadExpSwitchCode;
import com.ss.union.game.sdk.common.ui.verifyCode.CodeView;
import com.ss.union.game.sdk.common.util.KeyboardUtils;
import com.ss.union.game.sdk.common.util.ResourceUtils;
import java.lang.reflect.Field;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class VerifyCodeEditText extends RelativeLayout {
    private EditText a;
    private Context b;
    private LinearLayout c;
    private CodeView[] d;
    private int e;
    private OnTextFinishListener f;
    private OnTextEditorStartListener g;
    private OnKeyboardWillShowListener h;
    private OnInputCompleteListener i;

    public interface OnInputCompleteListener {
        void onInputComplete(String str);
    }

    public interface OnKeyboardWillShowListener {
        void onKeyboardWillShow();
    }

    public interface OnTextEditorStartListener {
        void onStart();
    }

    public interface OnTextFinishListener {
        void onFinish(String str);
    }

    public void setOnTextEditorStartListener(OnTextEditorStartListener onTextEditorStartListener) {
        this.g = onTextEditorStartListener;
    }

    public VerifyCodeEditText(Context context) {
        this(context, null);
    }

    public VerifyCodeEditText(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public VerifyCodeEditText(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.e = 4;
        this.b = context;
    }

    private static int a(Context context, float f) {
        return (int) ((f * context.getResources().getDisplayMetrics().density) + 0.5f);
    }

    public void initStyle(VerifyCodeEditTextParams verifyCodeEditTextParams) throws IllegalAccessException, NoSuchFieldException, IllegalArgumentException {
        this.e = verifyCodeEditTextParams.j;
        a(verifyCodeEditTextParams.k, verifyCodeEditTextParams.l);
        a(verifyCodeEditTextParams);
    }

    private void a(int i, int i2) throws IllegalAccessException, NoSuchFieldException, IllegalArgumentException {
        this.a = new EditText(this.b);
        this.a.setId(ResourceUtils.getIdByName("lg_input_verify_code_edit_text"));
        try {
            Field declaredField = TextView.class.getDeclaredField("mCursorDrawableRes");
            declaredField.setAccessible(true);
            declaredField.set(this.a, Integer.valueOf(ResourceUtils.getDrawableIdByName("lg_verify_code_cursor_bg")));
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e2) {
            e2.printStackTrace();
        }
        this.a.setCursorVisible(false);
        this.a.setBackgroundColor(i);
        this.a.setTextSize(0.0f);
        this.a.setInputType(i2);
        this.a.setFilters(new InputFilter[]{new InputFilter.LengthFilter(this.e)});
        this.a.setImeOptions(DownloadExpSwitchCode.BACK_CLEAR_DATA);
        this.a.addTextChangedListener(new TextWatcher() { // from class: com.ss.union.game.sdk.common.ui.verifyCode.VerifyCodeEditText.1
            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i3, int i4, int i5) {
            }

            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i3, int i4, int i5) {
                Editable text = VerifyCodeEditText.this.a.getText();
                Selection.setSelection(text, text.length());
                if (text.length() != 0 || VerifyCodeEditText.this.g == null) {
                    return;
                }
                VerifyCodeEditText.this.g.onStart();
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
                VerifyCodeEditText.this.initData(editable);
                if (editable.length() != VerifyCodeEditText.this.e || VerifyCodeEditText.this.f == null) {
                    return;
                }
                VerifyCodeEditText.this.f.onFinish(editable.toString().trim());
            }
        });
        this.a.setOnEditorActionListener(new TextView.OnEditorActionListener() { // from class: com.ss.union.game.sdk.common.ui.verifyCode.VerifyCodeEditText.2
            @Override // android.widget.TextView.OnEditorActionListener
            public boolean onEditorAction(TextView textView, int i3, KeyEvent keyEvent) {
                if (i3 != 6 || VerifyCodeEditText.this.i == null) {
                    return false;
                }
                VerifyCodeEditText.this.i.onInputComplete(textView.getText().toString());
                return true;
            }
        });
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
        layoutParams.addRule(12, -1);
        addView(this.a, layoutParams);
        this.a.setOnLongClickListener(new View.OnLongClickListener() { // from class: com.ss.union.game.sdk.common.ui.verifyCode.VerifyCodeEditText.3
            @Override // android.view.View.OnLongClickListener
            public boolean onLongClick(View view) {
                VerifyCodeEditText.this.a.setCursorVisible(true);
                return false;
            }
        });
        this.a.setOnClickListener(new View.OnClickListener() { // from class: com.ss.union.game.sdk.common.ui.verifyCode.VerifyCodeEditText.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (VerifyCodeEditText.this.h != null) {
                    VerifyCodeEditText.this.h.onKeyboardWillShow();
                }
                VerifyCodeEditText.this.a.setCursorVisible(false);
            }
        });
        this.a.setOnFocusChangeListener(new View.OnFocusChangeListener() { // from class: com.ss.union.game.sdk.common.ui.verifyCode.VerifyCodeEditText.5
            @Override // android.view.View.OnFocusChangeListener
            public void onFocusChange(View view, boolean z) {
                if (VerifyCodeEditText.this.h == null || view.hasFocus() || !z) {
                    return;
                }
                VerifyCodeEditText.this.h.onKeyboardWillShow();
            }
        });
    }

    private void a(VerifyCodeEditTextParams verifyCodeEditTextParams) {
        this.c = new LinearLayout(this.b);
        this.c.setBackgroundColor(verifyCodeEditTextParams.k);
        this.c.setLayoutParams(new RelativeLayout.LayoutParams(-1, -1));
        this.c.setOrientation(0);
        addView(this.c);
        this.d = new CodeView[this.e];
        CodeView.Parameters errorColor = new CodeView.Parameters().setCursorColor(verifyCodeEditTextParams.c).setFlickerCursor(verifyCodeEditTextParams.b).setSelectedLineColor(verifyCodeEditTextParams.g).setUnSelectedLineColor(verifyCodeEditTextParams.f).setShowCursor(verifyCodeEditTextParams.a).setTextSize(verifyCodeEditTextParams.d).setTextColor(verifyCodeEditTextParams.e).setInputType(verifyCodeEditTextParams.l).setErrorColor(verifyCodeEditTextParams.h);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(0, -1);
        layoutParams.weight = 1.0f;
        layoutParams.gravity = 17;
        LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(a(this.b, verifyCodeEditTextParams.i), -1);
        for (int i = 0; i < this.d.length; i++) {
            this.d[i] = new CodeView(this.b);
            this.d[i].initCodeView(errorColor);
            this.c.addView(this.d[i], layoutParams);
            if (i < this.d.length - 1) {
                View view = new View(this.b);
                view.setBackgroundColor(0);
                this.c.addView(view, layoutParams2);
            }
        }
        initData(this.a.getText());
    }

    public void clearText() {
        this.a.setText("");
        for (int i = 0; i < this.e; i++) {
            this.d[i].deleteText();
            if (i == 0) {
                this.d[i].setSelected();
            }
        }
    }

    public void setOnTextFinishListener(OnTextFinishListener onTextFinishListener) {
        this.f = onTextFinishListener;
    }

    public void setOnKeyboardWillShowListener(OnKeyboardWillShowListener onKeyboardWillShowListener) {
        this.h = onKeyboardWillShowListener;
    }

    public void setOnInputCompleteListener(OnInputCompleteListener onInputCompleteListener) {
        this.i = onInputCompleteListener;
    }

    public void removeOnInputCompleteListener() {
        this.i = null;
    }

    public void initData(Editable editable) {
        int i = 0;
        if (editable.length() > 0) {
            int length = editable.length();
            while (i < this.e) {
                if (i < length) {
                    this.d[i].setText(String.valueOf(editable.charAt(i)));
                } else if (i == length) {
                    this.d[i].setSelected();
                } else {
                    this.d[i].deleteText();
                }
                i++;
            }
            return;
        }
        while (i < this.e) {
            this.d[i].deleteText();
            if (i == 0) {
                this.d[i].setSelected();
            }
            i++;
        }
    }

    public void setError() {
        if (this.d == null) {
            return;
        }
        for (CodeView codeView : this.d) {
            codeView.setError();
            a();
        }
    }

    private void a() {
        int length = this.a.getText().length();
        if (length >= this.e) {
            this.d[this.e - 1].showCursor();
        } else {
            this.d[length].showCursor();
        }
    }

    public String getVerifyCodeText() {
        return this.a != null ? this.a.getText().toString().trim() : "";
    }

    public void requestInputFocus() {
        if (this.a == null) {
            return;
        }
        this.a.setFocusable(true);
        this.a.setFocusableInTouchMode(true);
        this.a.requestFocus();
        if (KeyboardUtils.isSoftShowing((Activity) getContext())) {
            return;
        }
        postDelayed(new Runnable() { // from class: com.ss.union.game.sdk.common.ui.verifyCode.VerifyCodeEditText.6
            @Override // java.lang.Runnable
            public void run() {
                KeyboardUtils.showKeyboard(VerifyCodeEditText.this.getContext());
            }
        }, 500L);
    }

    @Override // android.view.View
    public void setOnCreateContextMenuListener(View.OnCreateContextMenuListener onCreateContextMenuListener) {
        super.setOnCreateContextMenuListener(onCreateContextMenuListener);
    }

    public static class VerifyCodeEditTextParams {
        private boolean b;
        private int i;
        private boolean a = true;
        private int c = Color.rgb(51, 119, 255);
        private int d = 17;
        private int e = Color.rgb(20, 31, 51);
        private int f = Color.rgb(237, 237, 240);
        private int g = Color.rgb(51, 119, 255);
        private int h = Color.rgb(255, 91, 76);
        private int j = 4;
        private int k = 0;
        private int l = 18;

        public VerifyCodeEditTextParams setShowCursor(boolean z) {
            this.a = z;
            return this;
        }

        public VerifyCodeEditTextParams setFlickerCursor(boolean z) {
            this.b = z;
            return this;
        }

        public VerifyCodeEditTextParams setCursorColor(int i) {
            this.c = i;
            return this;
        }

        public VerifyCodeEditTextParams setTextSize(int i) {
            this.d = i;
            return this;
        }

        public VerifyCodeEditTextParams setTextColor(int i) {
            this.e = i;
            return this;
        }

        public VerifyCodeEditTextParams setUnSelectedLineColor(int i) {
            this.f = i;
            return this;
        }

        public VerifyCodeEditTextParams setSelectedLineColor(int i) {
            this.g = i;
            return this;
        }

        public VerifyCodeEditTextParams setSplitWidth(int i) {
            this.i = i;
            return this;
        }

        public VerifyCodeEditTextParams setCodeLength(int i) {
            this.j = i;
            return this;
        }

        public VerifyCodeEditTextParams setBgColor(int i) {
            this.k = i;
            return this;
        }

        public VerifyCodeEditTextParams setInputType(int i) {
            this.l = i;
            return this;
        }

        public VerifyCodeEditTextParams setErrorColor(int i) {
            this.h = i;
            return this;
        }
    }
}
