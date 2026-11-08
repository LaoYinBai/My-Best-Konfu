package com.tachikoma.core.event.base;

import android.content.Context;
import com.tachikoma.annotation.TK_EXPORT_CLASS;
import java.util.HashMap;
import java.util.List;

@TK_EXPORT_CLASS
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class TKBaseEvent implements IBaseEvent {
    public static final String TK_CLICK_EVENT_NAME = "tap";
    public static final String TK_DISPATCH_EVENT_NAME = "dispatch";
    public static final String TK_DOWN_EVENT_NAME = "down";
    public static final int TK_GESTURE_STATE_BEGAN = 1;
    public static final int TK_GESTURE_STATE_CANCELLED = 4;
    public static final int TK_GESTURE_STATE_CHANGED = 2;
    public static final int TK_GESTURE_STATE_ENDED = 3;
    public static final int TK_GESTURE_STATE_NORMAL = 0;
    public static final String TK_INPUT_EVENT_NAME = "input";
    public static final String TK_LONG_PRESS_EVENT_NAME = "longPress";
    public static final String TK_PAN_EVENT_NAME = "pan";
    public static final String TK_PINCH_EVENT_NAME = "pinch";
    public static final String TK_SCROLL_EVENT_NAME = "scroll";
    public static final String TK_SWIPE_EVENT_NAME = "swipe";
    public static final String TK_SWITCH_EVENT_NAME = "switch";
    public static final String TK_UP_EVENT_NAME = "up";
    public int state;
    public String type;
    public HashMap<String, Object> mData = new HashMap<>();
    public long timestamp = System.currentTimeMillis();

    public TKBaseEvent(Context context, List<Object> list) {
    }

    @Override // com.tachikoma.core.event.base.IBaseEvent
    public void configWithData(HashMap<String, Object> map) {
        this.mData = map;
    }

    @Override // com.tachikoma.core.event.base.IBaseEvent
    public void setState(int i) {
        this.state = i;
    }

    @Override // com.tachikoma.core.event.base.IBaseEvent
    public void setType(String str) {
        this.type = str;
    }
}
