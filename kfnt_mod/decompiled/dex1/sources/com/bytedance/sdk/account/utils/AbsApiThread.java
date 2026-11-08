package com.bytedance.sdk.account.utils;

import com.bytedance.sdk.account.network.dispatcher.ApiThread;
import com.bytedance.sdk.account.network.dispatcher.IRequest;
import com.bytedance.sdk.account.utility.StringUtils;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public abstract class AbsApiThread extends ApiThread {
    public static final String KEY_DATA = "data";
    public static final String KEY_MESSAGE = "message";
    public static final String STATUS_ERROR = "error";
    public static final String STATUS_EXCEPTION = "exception";
    public static final String STATUS_SUCCESS = "success";

    public static boolean isApiSuccess(JSONObject jSONObject) throws JSONException {
        return jSONObject != null && "success".equals(jSONObject.optString("message"));
    }

    public static boolean isApiSuccess(String str) {
        if (StringUtils.isEmpty(str)) {
            return false;
        }
        try {
            return isApiSuccess(new JSONObject(str));
        } catch (JSONException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean optBoolean(JSONObject jSONObject, String str, boolean z) {
        if (jSONObject == null || jSONObject.isNull(str)) {
            return z;
        }
        int iOptInt = jSONObject.optInt(str, -1);
        if (iOptInt >= 1) {
            return true;
        }
        if (iOptInt == 0) {
            return false;
        }
        return jSONObject.optBoolean(str, z);
    }

    public static boolean getHasMore(JSONObject jSONObject, boolean z) {
        return optBoolean(jSONObject, "has_more", z);
    }

    protected AbsApiThread(String str, IRequest.Priority priority) {
        super(str, priority);
    }

    protected AbsApiThread(String str, boolean z) {
        this(str, z ? IRequest.Priority.LOW : IRequest.Priority.NORMAL);
    }

    protected AbsApiThread(String str) {
        this(str, IRequest.Priority.NORMAL);
    }

    protected AbsApiThread() {
        this((String) null, IRequest.Priority.NORMAL);
    }

    protected AbsApiThread(IRequest.Priority priority) {
        this((String) null, priority);
    }

    protected AbsApiThread(boolean z) {
        this((String) null, z ? IRequest.Priority.LOW : IRequest.Priority.NORMAL);
    }
}
