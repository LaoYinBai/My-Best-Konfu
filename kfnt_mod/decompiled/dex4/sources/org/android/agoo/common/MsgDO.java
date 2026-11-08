package org.android.agoo.common;

import android.text.TextUtils;
import java.util.HashMap;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes4.dex */
public class MsgDO {
    public boolean agooFlag;
    public String body;
    public String dataId;
    public String errorCode;
    public int evokeAppStatus;
    public String extData;
    public String fromAppkey;
    public String fromPkg;
    public boolean isGlobalClick;
    public long lastActiveTime;
    public String messageSource;
    public String msgIds;
    public String msgStatus;
    public String notifyEnable;
    public String pack;
    public String removePacks;
    public String reportStr;
    public String type;
    public boolean isStartProc = false;
    public boolean isFromCache = false;

    public String getMsgInfo() {
        HashMap map = new HashMap();
        map.put("msgIds", this.msgIds);
        map.put(AgooConstants.MESSAGE_EXT, this.extData);
        map.put(AgooConstants.MESSAGE_BODY, this.body);
        map.put("dataId", this.dataId);
        map.put("pack", this.pack);
        map.put("messageSource", this.messageSource);
        if (!TextUtils.isEmpty(this.removePacks)) {
            map.put("removePacks", this.removePacks);
        }
        if (!TextUtils.isEmpty(this.errorCode)) {
            map.put("errorCode", this.errorCode);
        }
        if (!TextUtils.isEmpty(this.type)) {
            map.put(AgooConstants.MESSAGE_TYPE, this.type);
        }
        return new JSONObject(map).toString();
    }
}
