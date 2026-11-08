package com.tachikoma.core.event;

import com.tachikoma.core.event.base.TKBaseEvent;
import com.tachikoma.core.event.guesture.TKDispatchEvent;
import com.tachikoma.core.event.guesture.TKDownEvent;
import com.tachikoma.core.event.guesture.TKLongPressEvent;
import com.tachikoma.core.event.guesture.TKPanEvent;
import com.tachikoma.core.event.guesture.TKPinchEvent;
import com.tachikoma.core.event.guesture.TKSwipeEvent;
import com.tachikoma.core.event.guesture.TKTapEvent;
import com.tachikoma.core.event.guesture.TKUpEvent;
import com.tachikoma.core.event.view.TKInputEvent;
import com.tachikoma.core.event.view.TKScrollEvent;
import com.tachikoma.core.event.view.TKSwitchEvent;
import java.util.HashMap;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class TKEventCollection {
    static TKEventCollection instance = new TKEventCollection();
    private static HashMap<String, String> mEventHashMap;

    static {
        mEventHashMap.put(TKBaseEvent.TK_CLICK_EVENT_NAME, TKTapEvent.class.getName());
        mEventHashMap.put("down", TKDownEvent.class.getName());
        mEventHashMap.put("up", TKUpEvent.class.getName());
        mEventHashMap.put(TKBaseEvent.TK_LONG_PRESS_EVENT_NAME, TKLongPressEvent.class.getName());
        mEventHashMap.put(TKBaseEvent.TK_SWIPE_EVENT_NAME, TKSwipeEvent.class.getName());
        mEventHashMap.put(TKBaseEvent.TK_PINCH_EVENT_NAME, TKPinchEvent.class.getName());
        mEventHashMap.put(TKBaseEvent.TK_PAN_EVENT_NAME, TKPanEvent.class.getName());
        mEventHashMap.put("scroll", TKScrollEvent.class.getName());
        mEventHashMap.put(TKBaseEvent.TK_INPUT_EVENT_NAME, TKInputEvent.class.getName());
        mEventHashMap.put(TKBaseEvent.TK_SWITCH_EVENT_NAME, TKSwitchEvent.class.getName());
        mEventHashMap.put(TKBaseEvent.TK_DISPATCH_EVENT_NAME, TKDispatchEvent.class.getName());
    }

    private TKEventCollection() {
        mEventHashMap = new HashMap<>();
    }

    public static TKEventCollection getInstance() {
        return instance;
    }

    public void addEventPlugins(HashMap<String, String> map) {
        if (map != null) {
            mEventHashMap.putAll(map);
        }
    }

    public String getEventName(String str) {
        String str2 = mEventHashMap.get(str);
        return str2 != null ? str2 : "Event";
    }
}
