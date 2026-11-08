package com.tachikoma.core.component.input;

import android.content.Context;
import android.graphics.Color;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import androidx.annotation.Nullable;
import com.tachikoma.annotation.TK_EXPORT_CLASS;
import com.tachikoma.core.component.TKBase;
import com.tachikoma.core.event.TKEventProcess;
import com.tachikoma.core.event.base.IBaseEvent;
import com.tachikoma.core.event.base.TKBaseEvent;
import com.tachikoma.core.event.view.TKInputEvent;
import com.tachikoma.core.utility.TKColorUtil;
import java.util.List;

@TK_EXPORT_CLASS
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class TKInput extends TKBase<EditText> {
    public boolean focused;
    private View.OnKeyListener mOnKeyListener;
    protected final InputProperty mProperty;
    private TextWatcher mTextWatcher;
    public String placeholder;
    public String text;

    public TKInput(Context context, @Nullable List<Object> list) {
        super(context, list);
        this.mTextWatcher = new TextWatcher() { // from class: com.tachikoma.core.component.input.TKInput.1
            @Override // android.text.TextWatcher
            public void afterTextChanged(final Editable editable) {
                TKInput.this.dispatchEvent(TKBaseEvent.TK_INPUT_EVENT_NAME, new TKEventProcess.EventInterface() { // from class: com.tachikoma.core.component.input.TKInput.1.3
                    @Override // com.tachikoma.core.event.TKEventProcess.EventInterface
                    public void callBackEvent(IBaseEvent iBaseEvent) {
                        if (iBaseEvent instanceof TKInputEvent) {
                            TKInputEvent tKInputEvent = (TKInputEvent) iBaseEvent;
                            tKInputEvent.setType(TKBaseEvent.TK_INPUT_EVENT_NAME);
                            tKInputEvent.setText(editable.toString());
                            tKInputEvent.setState(3);
                        }
                    }
                });
            }

            @Override // android.text.TextWatcher
            public void beforeTextChanged(final CharSequence charSequence, int i, int i2, int i3) {
                TKInput.this.dispatchEvent(TKBaseEvent.TK_INPUT_EVENT_NAME, new TKEventProcess.EventInterface() { // from class: com.tachikoma.core.component.input.TKInput.1.1
                    @Override // com.tachikoma.core.event.TKEventProcess.EventInterface
                    public void callBackEvent(IBaseEvent iBaseEvent) {
                        if (iBaseEvent instanceof TKInputEvent) {
                            TKInputEvent tKInputEvent = (TKInputEvent) iBaseEvent;
                            tKInputEvent.setType(TKBaseEvent.TK_INPUT_EVENT_NAME);
                            tKInputEvent.setText(charSequence.toString());
                            tKInputEvent.setState(1);
                        }
                    }
                });
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(final CharSequence charSequence, int i, int i2, int i3) {
                TKInput.this.dispatchEvent(TKBaseEvent.TK_INPUT_EVENT_NAME, new TKEventProcess.EventInterface() { // from class: com.tachikoma.core.component.input.TKInput.1.2
                    @Override // com.tachikoma.core.event.TKEventProcess.EventInterface
                    public void callBackEvent(IBaseEvent iBaseEvent) {
                        if (iBaseEvent instanceof TKInputEvent) {
                            TKInputEvent tKInputEvent = (TKInputEvent) iBaseEvent;
                            tKInputEvent.setType(TKBaseEvent.TK_INPUT_EVENT_NAME);
                            tKInputEvent.setText(charSequence.toString());
                            tKInputEvent.setState(2);
                        }
                    }
                });
            }
        };
        this.mOnKeyListener = new View.OnKeyListener() { // from class: com.tachikoma.core.component.input.TKInput.2
            @Override // android.view.View.OnKeyListener
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if (i != 67 || keyEvent.getAction() != 0 || TKInput.this.getView() == null || !TextUtils.isEmpty(TKInput.this.getView().getText())) {
                    return false;
                }
                TKInput.this.dispatchEvent(TKBaseEvent.TK_INPUT_EVENT_NAME, new TKEventProcess.EventInterface() { // from class: com.tachikoma.core.component.input.TKInput.2.1
                    @Override // com.tachikoma.core.event.TKEventProcess.EventInterface
                    public void callBackEvent(IBaseEvent iBaseEvent) {
                        if (iBaseEvent instanceof TKInputEvent) {
                            TKInputEvent tKInputEvent = (TKInputEvent) iBaseEvent;
                            tKInputEvent.setType(TKBaseEvent.TK_INPUT_EVENT_NAME);
                            tKInputEvent.setText("");
                            tKInputEvent.setState(2);
                        }
                    }
                });
                return false;
            }
        };
        this.mProperty = new InputProperty(getView(), isSingleLine());
    }

    public void clear() {
        this.mProperty.setText("");
    }

    public void clearFocus() {
        this.mProperty.setFocused(false);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.tachikoma.core.component.TKBase
    public EditText createViewInstance(Context context) {
        EditText editText = new EditText(context);
        editText.setBackground(null);
        return editText;
    }

    public String getText() {
        return this.mProperty.getText();
    }

    protected boolean isSingleLine() {
        return true;
    }

    @Override // com.tachikoma.core.component.TKBase, com.tachikoma.core.common.ILifeCycle
    public void onCreate() {
        super.onCreate();
        getView().addTextChangedListener(this.mTextWatcher);
        getView().setOnKeyListener(this.mOnKeyListener);
    }

    @Override // com.tachikoma.core.component.TKBase, com.tachikoma.core.common.ILifeCycle
    public void onDestroy() {
        super.onDestroy();
        if (getView() != null) {
            getView().removeTextChangedListener(this.mTextWatcher);
            getView().setOnKeyListener(null);
        }
    }

    public void setColor(String str) {
        this.mProperty.setTextColor(Color.parseColor(TKColorUtil.rgba2argb(str)));
    }

    public void setCursorColor(String str) throws IllegalAccessException, NoSuchFieldException, IllegalArgumentException {
        this.mProperty.setCursorColor(Color.parseColor(str));
    }

    public void setFocused(boolean z) {
        this.focused = z;
        this.mProperty.setFocused(this.focused);
    }

    public void setFontFamily(String str) {
        this.mProperty.setFontFamily(str, getRootDir());
    }

    public void setFontSize(int i) {
        this.mProperty.setFontSize(i);
    }

    public void setMaxLength(int i) {
        this.mProperty.setMaxLength(i);
    }

    public void setPlaceholder(String str) {
        this.placeholder = str;
        this.mProperty.setPlaceholder(this.placeholder);
    }

    public void setPlaceholderColor(String str) {
        this.mProperty.setPlaceholderColor(Color.parseColor(str));
    }

    public void setPlaceholderFontSize(float f) {
        this.mProperty.setPlaceholderFontSize(f);
    }

    public void setReturnKeyType(String str) {
        this.mProperty.setReturnKeyType(str);
    }

    public void setText(String str) {
        this.text = str;
        this.mProperty.setText(this.text);
    }

    public void setTextAlign(String str) {
        this.mProperty.setTextAlign(str);
    }

    public void setType(String str) {
        this.mProperty.setType(str);
    }
}
