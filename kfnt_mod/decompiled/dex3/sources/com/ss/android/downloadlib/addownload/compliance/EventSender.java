package com.ss.android.downloadlib.addownload.compliance;

import com.ss.android.downloadlib.addownload.model.ModelBox;
import com.ss.android.downloadlib.addownload.model.ModelManager;
import com.ss.android.downloadlib.constants.EventConstants;
import com.ss.android.downloadlib.event.AdEventHandler;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
class EventSender {
    EventSender() {
    }

    public static void sendDialogClickEvent(String str, long j) {
        ModelBox modelBox = ModelManager.getInstance().getModelBox(j);
        if (modelBox.notValid()) {
            return;
        }
        modelBox.event.setRefer(str);
        AdEventHandler.getInstance().sendEvent(EventConstants.Label.LP_APP_DIALOG_CLICK, modelBox);
    }

    public static void sendEvent(String str, long j) {
        sendEvent(str, (JSONObject) null, j);
    }

    public static void sendEvent(String str, JSONObject jSONObject, long j) {
        AdEventHandler.getInstance().sendEvent(str, jSONObject, ModelManager.getInstance().getModelBox(j));
    }

    public static void sendEvent(String str, ModelBox modelBox) {
        AdEventHandler.getInstance().sendEvent(str, modelBox);
    }

    public static void sendEvent(String str, JSONObject jSONObject, ModelBox modelBox) {
        AdEventHandler.getInstance().sendEvent(str, jSONObject, modelBox);
    }

    public static void sendErrorEvent(int i, ModelBox modelBox) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.putOpt("error_code", Integer.valueOf(i));
        } catch (Exception e) {
            e.printStackTrace();
        }
        AdEventHandler.getInstance().sendEvent(EventConstants.Label.LP_COMPLIANCE_ERROR, jSONObject, modelBox);
    }

    public static void sendErrorEvent(int i, long j) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.putOpt("error_code", Integer.valueOf(i));
        } catch (Exception e) {
            e.printStackTrace();
        }
        AdEventHandler.getInstance().sendEvent(EventConstants.Label.LP_COMPLIANCE_ERROR, jSONObject, ModelManager.getInstance().getModelBox(j));
    }
}
