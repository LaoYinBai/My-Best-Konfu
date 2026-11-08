package com.tachikoma.core.event.view;

import android.content.Context;
import com.tachikoma.annotation.TK_EXPORT_CLASS;
import com.tachikoma.core.event.base.TKBaseEvent;
import java.util.List;

@TK_EXPORT_CLASS
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class TKScrollEvent extends TKBaseEvent {
    public static final String TK_EVENT_LIST_SCROLL = "scroll";
    public static final int TK_SCROLL_STATE_SCROLL = 2;
    public float dx;
    public float dy;
    public float scrollOffsetX;
    public float scrollOffsetY;
    public int state;

    public TKScrollEvent(Context context, List<Object> list) {
        super(context, list);
    }

    public void setDx(float f) {
        this.dx = f;
    }

    public void setDy(float f) {
        this.dy = f;
    }

    public void setScrollOffsetX(float f) {
        this.scrollOffsetX = f;
    }

    public void setScrollOffsetY(float f) {
        this.scrollOffsetY = f;
    }

    @Override // com.tachikoma.core.event.base.TKBaseEvent, com.tachikoma.core.event.base.IBaseEvent
    public void setState(int i) {
        this.state = i;
    }
}
