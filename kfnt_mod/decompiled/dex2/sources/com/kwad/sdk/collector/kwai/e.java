package com.kwad.sdk.collector.kwai;

import android.hardware.SensorEvent;
import com.kwad.sdk.utils.t;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class e extends com.kwad.sdk.core.response.kwai.a implements com.kwad.sdk.core.b {
    public int a = -1;
    public List<Float> b = new ArrayList();
    public long c = 0;

    public static e a(SensorEvent sensorEvent, long j) {
        if (sensorEvent == null) {
            return null;
        }
        e eVar = new e();
        eVar.a = sensorEvent.sensor.getType();
        eVar.c = j / 1000;
        for (float f : sensorEvent.values) {
            eVar.b.add(Float.valueOf(f));
        }
        return eVar;
    }

    @Override // com.kwad.sdk.core.response.kwai.a
    public void afterToJson(JSONObject jSONObject) throws JSONException {
        super.afterToJson(jSONObject);
        t.a(jSONObject, "values", this.b);
    }
}
