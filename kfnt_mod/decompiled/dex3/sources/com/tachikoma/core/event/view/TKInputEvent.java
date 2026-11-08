package com.tachikoma.core.event.view;

import android.content.Context;
import com.tachikoma.annotation.TK_EXPORT_CLASS;
import com.tachikoma.core.event.base.TKBaseEvent;
import java.util.List;

@TK_EXPORT_CLASS
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class TKInputEvent extends TKBaseEvent {
    public static final int TK_INPUT_STATE_BEGAN = 1;
    public static final int TK_INPUT_STATE_CHANGED = 2;
    public static final int TK_INPUT_STATE_ENDED = 3;
    public static final int TK_INPUT_STATE_NORMAL = 0;
    public int state;
    public String text;

    public TKInputEvent(Context context, List<Object> list) {
        super(context, list);
    }

    public void setText(String str) {
        this.text = str;
    }
}
