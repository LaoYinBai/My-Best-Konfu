package com.tachikoma.core.event.guesture;

import android.content.Context;
import com.tachikoma.annotation.TK_EXPORT_CLASS;
import com.tachikoma.core.event.base.TKBaseEvent;
import java.util.HashMap;
import java.util.List;

@TK_EXPORT_CLASS
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class TKSwipeEvent extends TKBaseEvent {
    public static final String DIRECTION_DOWN = "down";
    public static final String DIRECTION_LEFT = "left";
    public static final String DIRECTION_RIGHT = "right";
    public static final String DIRECTION_UP = "up";
    public String direction;

    public TKSwipeEvent(Context context, List<Object> list) {
        super(context, list);
    }

    private float getFloatValue(Object obj) {
        if (obj instanceof Number) {
            return ((Number) obj).floatValue();
        }
        return 0.0f;
    }

    @Override // com.tachikoma.core.event.base.TKBaseEvent, com.tachikoma.core.event.base.IBaseEvent
    public void configWithData(HashMap<String, Object> map) {
        String str;
        super.configWithData(map);
        float floatValue = getFloatValue(map.get("beginX"));
        float floatValue2 = getFloatValue(map.get("beginY"));
        float floatValue3 = getFloatValue(map.get("endX"));
        float floatValue4 = getFloatValue(map.get("endY"));
        float floatValue5 = getFloatValue(map.get("velocityX"));
        float floatValue6 = getFloatValue(map.get("velocityY"));
        if (floatValue - floatValue3 > 120.0f && Math.abs(floatValue5) > 0.0f) {
            str = "left";
        } else if (floatValue3 - floatValue > 120.0f && Math.abs(floatValue5) > 0.0f) {
            str = "right";
        } else if (floatValue2 - floatValue4 > 120.0f && Math.abs(floatValue6) > 0.0f) {
            str = "up";
        } else if (floatValue4 - floatValue2 <= 120.0f || Math.abs(floatValue6) <= 0.0f) {
            return;
        } else {
            str = "down";
        }
        this.direction = str;
    }

    public void setDirection(String str) {
        this.direction = str;
    }
}
