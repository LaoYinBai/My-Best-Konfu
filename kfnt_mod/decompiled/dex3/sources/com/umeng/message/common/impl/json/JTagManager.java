package com.umeng.message.common.impl.json;

import android.content.Context;
import android.text.TextUtils;
import com.umeng.commonsdk.UMConfigure;
import com.umeng.commonsdk.debug.UMLog;
import com.umeng.message.MessageSharedPrefs;
import com.umeng.message.MsgConstant;
import com.umeng.message.common.UmengMessageDeviceConfig;
import com.umeng.message.common.inter.ITagManager;
import com.xiaomi.mipush.sdk.Constants;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class JTagManager implements ITagManager {
    private static final String a = "JTagManager";
    private Context b;

    public JTagManager(Context context) {
        this.b = context;
    }

    @Override // com.umeng.message.common.inter.ITagManager
    public ITagManager.Result addTags(JSONObject jSONObject, String... strArr) throws Exception {
        JSONObject jSONObjectSendRequest;
        String str = MsgConstant.TAG_ENDPOINT + "/add";
        try {
            jSONObjectSendRequest = JUtrack.sendRequest(jSONObject, str);
        } catch (Exception e) {
            if (e.getMessage() != null && e.getMessage().contains(MsgConstant.HTTPSDNS_ERROR) && UmengMessageDeviceConfig.isOnline(this.b)) {
                UMLog uMLog = UMConfigure.umDebugLog;
                UMLog.mutlInfo(a, 2, "添加tag UnknownHostException");
                jSONObjectSendRequest = JUtrack.sendRequest(this.b, jSONObject, str);
            } else {
                throw new Exception(e);
            }
        }
        ITagManager.Result result = new ITagManager.Result(jSONObjectSendRequest, false);
        if (TextUtils.equals(result.status, ITagManager.SUCCESS)) {
            MessageSharedPrefs.getInstance(this.b).addTags(strArr);
            MessageSharedPrefs.getInstance(this.b).setTagRemain(result.remain);
            MessageSharedPrefs.getInstance(this.b).add_addTagsInterval(result.toString());
        }
        return result;
    }

    @Override // com.umeng.message.common.inter.ITagManager
    public ITagManager.Result addWeightedTags(JSONObject jSONObject, Hashtable<String, Integer> hashtable) throws Exception {
        JSONObject jSONObjectSendRequest;
        String str = MsgConstant.WEIGHTED_TAG_ENDPOINT + "/incr";
        try {
            jSONObjectSendRequest = JUtrack.sendRequest(jSONObject, str);
        } catch (Exception e) {
            if (e.getMessage() != null && e.getMessage().contains(MsgConstant.HTTPSDNS_ERROR) && UmengMessageDeviceConfig.isOnline(this.b)) {
                UMLog uMLog = UMConfigure.umDebugLog;
                UMLog.mutlInfo(a, 2, "添加加权标签 UnknownHostException");
                jSONObjectSendRequest = JUtrack.sendRequest(this.b, jSONObject, str);
            } else {
                throw new Exception(e);
            }
        }
        UMLog uMLog2 = UMConfigure.umDebugLog;
        UMLog.mutlInfo(a, 2, jSONObjectSendRequest.toString());
        ITagManager.Result result = new ITagManager.Result(jSONObjectSendRequest, true);
        MessageSharedPrefs.getInstance(this.b).setAddWeightedTagsInterval(result.toString());
        return result;
    }

    @Override // com.umeng.message.common.inter.ITagManager
    public ITagManager.Result update(JSONObject jSONObject, String... strArr) throws Exception {
        JSONObject jSONObjectSendRequest;
        String str = MsgConstant.TAG_ENDPOINT + "/update";
        try {
            jSONObjectSendRequest = JUtrack.sendRequest(jSONObject, str);
        } catch (Exception e) {
            if (e.getMessage() != null && e.getMessage().contains(MsgConstant.HTTPSDNS_ERROR) && UmengMessageDeviceConfig.isOnline(this.b)) {
                jSONObjectSendRequest = JUtrack.sendRequest(this.b, jSONObject, str);
            } else {
                throw new Exception(e);
            }
        }
        ITagManager.Result result = new ITagManager.Result(jSONObjectSendRequest, false);
        if (TextUtils.equals(result.status, ITagManager.SUCCESS)) {
            MessageSharedPrefs.getInstance(this.b).resetTags();
            MessageSharedPrefs.getInstance(this.b).addTags(strArr);
            MessageSharedPrefs.getInstance(this.b).setTagRemain(result.remain);
        }
        return result;
    }

    @Override // com.umeng.message.common.inter.ITagManager
    public ITagManager.Result deleteTags(JSONObject jSONObject, String... strArr) throws Exception {
        JSONObject jSONObjectSendRequest;
        String str = MsgConstant.TAG_ENDPOINT + "/delete";
        try {
            jSONObjectSendRequest = JUtrack.sendRequest(jSONObject, str);
        } catch (Exception e) {
            if (e.getMessage() != null && e.getMessage().contains(MsgConstant.HTTPSDNS_ERROR) && UmengMessageDeviceConfig.isOnline(this.b)) {
                jSONObjectSendRequest = JUtrack.sendRequest(this.b, jSONObject, str);
            } else {
                throw new Exception(e);
            }
        }
        ITagManager.Result result = new ITagManager.Result(jSONObjectSendRequest, false);
        if (TextUtils.equals(result.status, ITagManager.SUCCESS)) {
            MessageSharedPrefs.getInstance(this.b).removeTags(strArr);
            MessageSharedPrefs.getInstance(this.b).setTagRemain(result.remain);
            MessageSharedPrefs.getInstance(this.b).add_deleteTagsInterval(result.toString());
        }
        return result;
    }

    @Override // com.umeng.message.common.inter.ITagManager
    public ITagManager.Result deleteWeightedTags(JSONObject jSONObject, String... strArr) throws Exception {
        JSONObject jSONObjectSendRequest;
        String str = MsgConstant.WEIGHTED_TAG_ENDPOINT + "/delete";
        try {
            jSONObjectSendRequest = JUtrack.sendRequest(jSONObject, str);
        } catch (Exception e) {
            if (e.getMessage() != null && e.getMessage().contains(MsgConstant.HTTPSDNS_ERROR) && UmengMessageDeviceConfig.isOnline(this.b)) {
                jSONObjectSendRequest = JUtrack.sendRequest(this.b, jSONObject, str);
            } else {
                throw new Exception(e);
            }
        }
        ITagManager.Result result = new ITagManager.Result(jSONObjectSendRequest, true);
        MessageSharedPrefs.getInstance(this.b).setDeleteWeightedTagsInterval(result.toString());
        return result;
    }

    @Override // com.umeng.message.common.inter.ITagManager
    public ITagManager.Result reset(JSONObject jSONObject) throws Exception {
        JSONObject jSONObjectSendRequest;
        String str = MsgConstant.TAG_ENDPOINT + "/reset";
        try {
            jSONObjectSendRequest = JUtrack.sendRequest(jSONObject, str);
        } catch (Exception e) {
            if (e.getMessage() != null && e.getMessage().contains(MsgConstant.HTTPSDNS_ERROR) && UmengMessageDeviceConfig.isOnline(this.b)) {
                jSONObjectSendRequest = JUtrack.sendRequest(this.b, jSONObject, str);
            } else {
                throw new Exception(e);
            }
        }
        ITagManager.Result result = new ITagManager.Result(jSONObjectSendRequest, false);
        if (TextUtils.equals(result.status, ITagManager.SUCCESS)) {
            MessageSharedPrefs.getInstance(this.b).resetTags();
        }
        return result;
    }

    @Override // com.umeng.message.common.inter.ITagManager
    public List<String> getTags(JSONObject jSONObject) throws Exception {
        JSONObject jSONObjectSendRequest;
        String str = MsgConstant.TAG_ENDPOINT + "/get";
        try {
            jSONObjectSendRequest = JUtrack.sendRequest(jSONObject, str);
        } catch (Exception e) {
            if (e.getMessage() != null && e.getMessage().contains(MsgConstant.HTTPSDNS_ERROR) && UmengMessageDeviceConfig.isOnline(this.b)) {
                jSONObjectSendRequest = JUtrack.sendRequest(this.b, jSONObject, str);
            } else {
                throw new Exception(e);
            }
        }
        ITagManager.Result result = new ITagManager.Result(jSONObjectSendRequest, false);
        if (!TextUtils.equals(result.status, ITagManager.SUCCESS) || jSONObjectSendRequest.getString(MsgConstant.KEY_TAGS) == null) {
            return null;
        }
        UMLog uMLog = UMConfigure.umDebugLog;
        UMLog.mutlInfo(a, 2, jSONObjectSendRequest.getString(MsgConstant.KEY_TAGS));
        MessageSharedPrefs.getInstance(this.b).add_getTagsInteral(result.toString());
        return Arrays.asList(jSONObjectSendRequest.getString(MsgConstant.KEY_TAGS).split(Constants.ACCEPT_TIME_SEPARATOR_SP));
    }

    @Override // com.umeng.message.common.inter.ITagManager
    public Hashtable<String, Integer> getWeightedTags(JSONObject jSONObject) throws Exception {
        JSONObject jSONObjectSendRequest;
        String str = MsgConstant.WEIGHTED_TAG_ENDPOINT + "/list";
        try {
            jSONObjectSendRequest = JUtrack.sendRequest(jSONObject, str);
        } catch (Exception e) {
            if (e.getMessage() != null && e.getMessage().contains(MsgConstant.HTTPSDNS_ERROR) && UmengMessageDeviceConfig.isOnline(this.b)) {
                jSONObjectSendRequest = JUtrack.sendRequest(this.b, jSONObject, str);
            } else {
                throw new Exception(e);
            }
        }
        MessageSharedPrefs.getInstance(this.b).setListWeightedTagsInterval(new ITagManager.Result(jSONObjectSendRequest, true).toString());
        Hashtable<String, Integer> hashtable = new Hashtable<>();
        JSONObject jSONObjectOptJSONObject = jSONObjectSendRequest.optJSONObject("data").optJSONObject(MsgConstant.KEY_TAGS);
        if (jSONObjectOptJSONObject != null) {
            Iterator<String> itKeys = jSONObjectOptJSONObject.keys();
            while (itKeys.hasNext()) {
                String next = itKeys.next();
                hashtable.put(next, Integer.valueOf(jSONObjectOptJSONObject.getInt(next)));
            }
        }
        return hashtable;
    }
}
