package com.tachikoma.core.event.guesture;

import android.content.Context;
import com.tachikoma.annotation.TK_EXPORT_CLASS;
import com.tachikoma.core.event.base.TKBaseEvent;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@TK_EXPORT_CLASS
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class TKDispatchEvent extends TKBaseEvent {
    public HashMap<String, Float> mPosition;

    public TKDispatchEvent(Context context, List<Object> list) {
        super(context, list);
        this.mPosition = new HashMap<>();
    }

    @Override // com.tachikoma.core.event.base.TKBaseEvent, com.tachikoma.core.event.base.IBaseEvent
    public void configWithData(HashMap<String, Object> map) {
        super.configWithData(map);
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            this.mPosition.put(entry.getKey(), Float.valueOf(((Number) entry.getValue()).floatValue()));
        }
    }

    public HashMap<String, Float> getPosition() {
        return this.mPosition;
    }

    public void setPosition(HashMap<String, Float> map) {
        this.mPosition = map;
    }
}
