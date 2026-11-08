package com.kwad.sdk.core.b.kwai;

import com.kwad.sdk.core.request.model.TaskStat;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class ed implements com.kwad.sdk.core.d<TaskStat> {
    @Override // com.kwad.sdk.core.d
    public void a(TaskStat taskStat, JSONObject jSONObject) {
        if (jSONObject == null) {
            return;
        }
        taskStat.adStyle = jSONObject.optInt("adStyle");
        taskStat.taskType = jSONObject.optInt("taskType");
        taskStat.count = jSONObject.optInt("count");
    }

    @Override // com.kwad.sdk.core.d
    public JSONObject b(TaskStat taskStat, JSONObject jSONObject) {
        if (jSONObject == null) {
            jSONObject = new JSONObject();
        }
        com.kwad.sdk.utils.t.a(jSONObject, "adStyle", taskStat.adStyle);
        com.kwad.sdk.utils.t.a(jSONObject, "taskType", taskStat.taskType);
        com.kwad.sdk.utils.t.a(jSONObject, "count", taskStat.count);
        return jSONObject;
    }
}
