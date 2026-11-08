package com.tachikoma.core.event.view;

import android.content.Context;
import com.tachikoma.annotation.TK_EXPORT_CLASS;
import com.tachikoma.core.event.base.TKBaseEvent;
import java.util.List;

@TK_EXPORT_CLASS
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class TKSwitchEvent extends TKBaseEvent {
    public boolean state;

    public TKSwitchEvent(Context context, List<Object> list) {
        super(context, list);
    }

    public void setState(boolean z) {
        this.state = z;
    }
}
