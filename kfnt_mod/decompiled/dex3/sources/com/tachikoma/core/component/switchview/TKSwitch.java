package com.tachikoma.core.component.switchview;

import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.widget.CompoundButton;
import android.widget.Switch;
import androidx.annotation.Nullable;
import com.tachikoma.annotation.TK_EXPORT_CLASS;
import com.tachikoma.core.component.TKBase;
import com.tachikoma.core.event.TKEventProcess;
import com.tachikoma.core.event.base.IBaseEvent;
import com.tachikoma.core.event.base.TKBaseEvent;
import com.tachikoma.core.event.view.TKSwitchEvent;
import java.util.List;

@TK_EXPORT_CLASS
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class TKSwitch extends TKBase<Switch> implements CompoundButton.OnCheckedChangeListener {
    public boolean checked;

    @Nullable
    private Integer mOffTrackColor;

    @Nullable
    private Integer mOnTrackColor;

    public TKSwitch(Context context, @Nullable List<Object> list) {
        super(context, list);
    }

    private void setColor(Drawable drawable, @Nullable Integer num) {
        if (num == null) {
            drawable.clearColorFilter();
        } else {
            drawable.setColorFilter(num.intValue(), PorterDuff.Mode.MULTIPLY);
        }
    }

    private void setTrackColor(@Nullable Integer num) {
        setColor(getView().getTrackDrawable(), num);
    }

    private void setTrackColor(boolean z) {
        setTrackColor(z ? this.mOnTrackColor : this.mOffTrackColor);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.tachikoma.core.component.TKBase
    public Switch createViewInstance(Context context) {
        return new Switch(context);
    }

    public void doChecked(boolean z) {
        if (getView().isChecked() != z) {
            getView().setChecked(z);
            setTrackColor(z);
        }
    }

    public boolean getChecked() {
        return this.checked;
    }

    @Override // android.widget.CompoundButton.OnCheckedChangeListener
    public void onCheckedChanged(CompoundButton compoundButton, final boolean z) {
        this.checked = z;
        setTrackColor(z);
        dispatchEvent(TKBaseEvent.TK_SWITCH_EVENT_NAME, new TKEventProcess.EventInterface() { // from class: com.tachikoma.core.component.switchview.TKSwitch.1
            @Override // com.tachikoma.core.event.TKEventProcess.EventInterface
            public void callBackEvent(IBaseEvent iBaseEvent) {
                if (iBaseEvent instanceof TKSwitchEvent) {
                    iBaseEvent.setType(TKBaseEvent.TK_SWITCH_EVENT_NAME);
                    ((TKSwitchEvent) iBaseEvent).setState(z);
                }
            }
        });
    }

    @Override // com.tachikoma.core.component.TKBase, com.tachikoma.core.common.ILifeCycle
    public void onCreate() {
        super.onCreate();
        getView().setOnCheckedChangeListener(this);
    }

    @Override // com.tachikoma.core.component.TKBase, com.tachikoma.core.common.ILifeCycle
    public void onDestroy() {
        super.onDestroy();
        if (getView() != null) {
            getView().setOnCheckedChangeListener(null);
        }
    }

    public void setChecked(boolean z) {
        this.checked = z;
        doChecked(this.checked);
    }

    public void setOffColor(int i) {
        this.mOffTrackColor = Integer.valueOf(i);
        if (getView().isChecked()) {
            return;
        }
        setTrackColor(Integer.valueOf(i));
    }

    public void setOnColor(int i) {
        this.mOnTrackColor = Integer.valueOf(i);
        if (getView().isChecked()) {
            setTrackColor(Integer.valueOf(i));
        }
    }

    public void setThumbColor(int i) {
        setColor(getView().getThumbDrawable(), Integer.valueOf(i));
    }
}
