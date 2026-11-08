package cp.bd.c;

import android.content.Context;
import com.bytedance.framwork.core.sdkmonitor.SDKMonitor;
import com.bytedance.framwork.core.sdkmonitor.SDKMonitorUtils;
import com.sigmob.sdk.common.Constants;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public final class h extends g {
    private SDKMonitor a;

    @Override // cp.bd.c.g
    public final void a(long j, long j2, String str, String str2, String str3, int i) {
        if (this.a == null) {
            return;
        }
        this.a.monitorSLA(j, j2, str, str2, str3, i, (JSONObject) null);
    }

    @Override // cp.bd.c.g
    public final void a(String str, JSONObject jSONObject, JSONObject jSONObject2, JSONObject jSONObject3) {
        if (this.a == null) {
            return;
        }
        if (jSONObject.length() > 0 || jSONObject2.length() > 0) {
            this.a.monitorEvent(str, jSONObject, jSONObject2, jSONObject3);
        }
    }

    @Override // cp.bd.c.g
    public final boolean a(String str) throws JSONException {
        final JSONObject jSONObject;
        String string;
        final String string2;
        ArrayList arrayList;
        ArrayList arrayList2;
        Context context = c.a().a;
        if (str != null) {
            try {
                jSONObject = new JSONObject(str);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else {
            jSONObject = null;
        }
        if (jSONObject == null) {
            return false;
        }
        try {
            string = jSONObject.getString("sdk_aid");
            try {
                string2 = jSONObject.getString("host_aid");
            } catch (JSONException e2) {
                e = e2;
                string2 = null;
            }
        } catch (JSONException e3) {
            e = e3;
            string = null;
            string2 = null;
        }
        try {
            arrayList = new ArrayList();
            arrayList2 = new ArrayList();
            JSONArray jSONArray = jSONObject.getJSONArray("configURLs");
            JSONArray jSONArray2 = jSONObject.getJSONArray("reportURLs");
            for (int i = 0; i < jSONArray.length(); i++) {
                arrayList.add(jSONArray.getString(i));
            }
            for (int i2 = 0; i2 < jSONArray2.length(); i2++) {
                arrayList2.add(jSONArray2.getString(i2));
            }
        } catch (JSONException e4) {
            e = e4;
            e.printStackTrace();
            jSONObject.remove("configURLs");
            jSONObject.remove("reportURLs");
            SDKMonitorUtils.initMonitor(context, string, jSONObject, new SDKMonitor.IGetExtendParams() { // from class: cp.bd.c.h.1
                public final Map<String, String> getCommonParams() {
                    HashMap map = new HashMap();
                    try {
                        map.put("oversea", jSONObject.getBoolean("oversea") ? "1" : Constants.FAIL);
                    } catch (JSONException e5) {
                        e5.printStackTrace();
                    }
                    map.put("host_aid", string2);
                    return map;
                }

                public final String getSessionId() {
                    return null;
                }
            });
            this.a = SDKMonitorUtils.getInstance(string);
            this.a.flushBuffer();
            this.a.flushReport();
            return true;
        }
        if (arrayList.size() <= 0 || arrayList2.size() <= 0) {
            return false;
        }
        SDKMonitorUtils.setConfigUrl(string, arrayList);
        SDKMonitorUtils.setDefaultReportUrl(string, arrayList2);
        jSONObject.remove("configURLs");
        jSONObject.remove("reportURLs");
        SDKMonitorUtils.initMonitor(context, string, jSONObject, new SDKMonitor.IGetExtendParams() { // from class: cp.bd.c.h.1
            public final Map<String, String> getCommonParams() {
                HashMap map = new HashMap();
                try {
                    map.put("oversea", jSONObject.getBoolean("oversea") ? "1" : Constants.FAIL);
                } catch (JSONException e5) {
                    e5.printStackTrace();
                }
                map.put("host_aid", string2);
                return map;
            }

            public final String getSessionId() {
                return null;
            }
        });
        this.a = SDKMonitorUtils.getInstance(string);
        this.a.flushBuffer();
        this.a.flushReport();
        return true;
    }

    @Override // cp.bd.c.g
    public final void b(long j, long j2, String str, String str2, String str3, int i) {
        if (this.a == null) {
            return;
        }
        this.a.monitorApiError(j, j2, str, str2, str3, i, (JSONObject) null);
    }
}
