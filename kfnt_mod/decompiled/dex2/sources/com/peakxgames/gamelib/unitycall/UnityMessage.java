package com.peakxgames.gamelib.unitycall;

import android.util.Log;
import com.mbridge.msdk.foundation.entity.CampaignEx;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class UnityMessage {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public static String TAG = "UnityMessage";
    public static String UNITY_OBJECT_NAME = "Global";
    private String methodName;
    private Map<String, Serializable> params = new HashMap();

    public UnityMessage(String str) {
        this.methodName = str;
    }

    public UnityMessage put(String str, Serializable serializable) {
        this.params.put(str, serializable);
        return this;
    }

    public UnityMessage putCancelled() {
        put("cancelled", true);
        return this;
    }

    public UnityMessage putID(String str) {
        put("id", str);
        return this;
    }

    public void sendError(String str) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        put(CampaignEx.JSON_NATIVE_VIDEO_ERROR, str);
        send();
    }

    public void send() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        String string = new UnityParams(this.params).toString();
        Log.v(TAG, "sending to Unity " + this.methodName + "(" + string + ")");
        try {
            UnityReflection.SendMessage(UNITY_OBJECT_NAME, this.methodName, string);
        } catch (UnsatisfiedLinkError unused) {
            Log.v(TAG, "message not send, Unity not initialized");
        }
    }

    public static UnityMessage createWithCallbackFromParams(String str, UnityParams unityParams) {
        UnityMessage unityMessage = new UnityMessage(str);
        if (unityParams.hasString("callback_id").booleanValue()) {
            unityMessage.put("callback_id", unityParams.getString("callback_id"));
        }
        return unityMessage;
    }
}
