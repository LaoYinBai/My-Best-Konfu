package com.tachikoma.core.utility;

import android.content.Context;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class TKUtility {

    private static class TKUtilityHold {
        public static TKUtility instance = new TKUtility();

        private TKUtilityHold() {
        }
    }

    public static String TKJSONEncode(Object obj) {
        return _NJJSONStringWithObject(obj);
    }

    private static String _NJJSONStringWithObject(Object obj) {
        if (obj == null) {
            return null;
        }
        if (obj instanceof ArrayList) {
            return sharedInstance().getJsonFromArray((ArrayList) obj).toString();
        }
        if (obj instanceof Map) {
            return sharedInstance().getJsonFromMap((Map) obj).toString();
        }
        return null;
    }

    private JSONArray getJsonFromArray(ArrayList arrayList) {
        return new JSONArray((Collection) arrayList);
    }

    private JSONObject getJsonFromMap(Map<String, Object> map) throws JSONException {
        JSONObject jSONObject;
        try {
            jSONObject = new JSONObject();
            try {
                for (String str : map.keySet()) {
                    Object jsonFromArray = map.get(str);
                    if (jsonFromArray instanceof Map) {
                        jsonFromArray = getJsonFromMap((Map) jsonFromArray);
                    } else if (jsonFromArray instanceof ArrayList) {
                        jsonFromArray = getJsonFromArray((ArrayList) jsonFromArray);
                    }
                    jSONObject.put(str, jsonFromArray);
                }
            } catch (JSONException e) {
                e = e;
                e.printStackTrace();
                return jSONObject;
            }
        } catch (JSONException e2) {
            e = e2;
            jSONObject = null;
        }
        return jSONObject;
    }

    public static int getResourceId(Context context, String str, String str2, String str3) {
        try {
            return context.getResources().getIdentifier(str, str2, str3);
        } catch (Exception e) {
            throw new RuntimeException("Error getResourceId", e);
        }
    }

    public static int getResourceId(String str, String str2, String str3) {
        if (str3 == null) {
            try {
                str3 = TKContextUtil.getContext().getPackageName();
            } catch (Exception e) {
                throw new RuntimeException("Error getResourceId by TKContextUtil.getContext()", e);
            }
        }
        return TKContextUtil.getContext().getResources().getIdentifier(str, str2, str3);
    }

    public static TKUtility sharedInstance() {
        return TKUtilityHold.instance;
    }
}
