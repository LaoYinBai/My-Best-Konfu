package com.umeng.message.common.impl.json;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.text.TextUtils;
import com.sigmob.sdk.common.Constants;
import com.umeng.commonsdk.UMConfigure;
import com.umeng.commonsdk.UMConfigureImpl;
import com.umeng.commonsdk.debug.UMLog;
import com.umeng.commonsdk.framework.UMEnvelopeBuild;
import com.umeng.commonsdk.framework.c;
import com.umeng.commonsdk.proguard.o;
import com.umeng.commonsdk.stateless.UMSLEnvelopeBuild;
import com.umeng.commonsdk.utils.onMessageSendListener;
import com.umeng.message.MessageSharedPrefs;
import com.umeng.message.MsgConstant;
import com.umeng.message.UTrack;
import com.umeng.message.UmengMessageCallbackHandlerService;
import com.umeng.message.common.UmengMessageDeviceConfig;
import com.umeng.message.common.inter.ITagManager;
import com.umeng.message.common.inter.IUtrack;
import com.umeng.message.entity.Alias;
import com.umeng.message.proguard.h;
import com.umeng.message.proguard.l;
import com.umeng.message.proguard.m;
import com.umeng.message.service.UMJobIntentService;
import com.umeng.message.util.HttpRequest;
import com.umeng.message.util.b;
import java.net.URL;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class JUtrack implements IUtrack {
    private static final String a = "JUtrack";
    private Context b;

    public JUtrack(Context context) {
        this.b = context.getApplicationContext();
    }

    @Override // com.umeng.message.common.inter.IUtrack
    public void sendMsgLog(JSONObject jSONObject, final String str, final int i) throws Exception {
        UMSLEnvelopeBuild.mContext = this.b;
        final UMSLEnvelopeBuild uMSLEnvelopeBuild = new UMSLEnvelopeBuild();
        final JSONObject jSONObjectBuildSLBaseHeader = uMSLEnvelopeBuild.buildSLBaseHeader(this.b);
        JSONObject jSONObject2 = (JSONObject) jSONObjectBuildSLBaseHeader.opt("header");
        jSONObject2.put("din", UmengMessageDeviceConfig.getDINAes(this.b));
        jSONObject2.put(o.ay, MsgConstant.SDK_VERSION);
        jSONObject2.put("push_switch", UmengMessageDeviceConfig.isNotificationEnabled(this.b));
        jSONObjectBuildSLBaseHeader.put("header", jSONObject2);
        JSONObject jSONObject3 = new JSONObject();
        jSONObject3.put("ts", jSONObject.getLong("ts"));
        jSONObject3.put("pa", jSONObject.getString("pa"));
        jSONObject3.put(MsgConstant.KEY_DEVICE_TOKEN, MessageSharedPrefs.getInstance(this.b).getDeviceToken());
        jSONObject3.put("msg_id", jSONObject.getString("msg_id"));
        jSONObject3.put(MsgConstant.KEY_ACTION_TYPE, jSONObject.getInt(MsgConstant.KEY_ACTION_TYPE));
        JSONArray jSONArray = new JSONArray();
        jSONArray.put(jSONObject3);
        final JSONObject jSONObject4 = new JSONObject();
        jSONObject4.put("push", jSONArray);
        if (h.d(this.b)) {
            try {
                UMConfigureImpl.registerMessageSendListener(new onMessageSendListener() { // from class: com.umeng.message.common.impl.json.JUtrack.1
                    @Override // com.umeng.commonsdk.utils.onMessageSendListener
                    public void onMessageSend() {
                        try {
                            JSONObject jSONObjectBuildSLEnvelope = uMSLEnvelopeBuild.buildSLEnvelope(JUtrack.this.b, jSONObjectBuildSLBaseHeader, jSONObject4, MsgConstant.UNPX_PUSH_LOGS);
                            if (jSONObjectBuildSLEnvelope != null && !jSONObjectBuildSLEnvelope.has(c.c)) {
                                m.a(JUtrack.this.b).a(str, i);
                                if (i != 0) {
                                    m.a(JUtrack.this.b).b(str);
                                }
                            }
                            UMConfigureImpl.removeMessageSendListener(this);
                        } catch (Throwable unused) {
                        }
                    }
                });
                return;
            } catch (Throwable unused) {
                return;
            }
        }
        JSONObject jSONObject5 = new JSONObject();
        jSONObject5.put("jsonHeader", jSONObjectBuildSLBaseHeader);
        jSONObject5.put("jsonBody", jSONObject4);
        jSONObject5.put("msgId", str);
        jSONObject5.put("actionType", i);
        Intent intent = new Intent();
        intent.setPackage(this.b.getPackageName());
        intent.setAction(MsgConstant.MESSAGE_MESSAGE_SEND_ACTION);
        intent.putExtra(MsgConstant.KEY_UMPX_PATH, MsgConstant.UNPX_PUSH_LOGS);
        intent.putExtra(MsgConstant.KEY_SENDMESSAGE, jSONObject5.toString());
        UMJobIntentService.enqueueWork(this.b, (Class<? extends UMJobIntentService>) UmengMessageCallbackHandlerService.class, intent);
    }

    @Override // com.umeng.message.common.inter.IUtrack
    public void trackAppLaunch(JSONObject jSONObject) throws Exception {
        UMSLEnvelopeBuild.mContext = this.b;
        final UMSLEnvelopeBuild uMSLEnvelopeBuild = new UMSLEnvelopeBuild();
        final JSONObject jSONObjectBuildSLBaseHeader = uMSLEnvelopeBuild.buildSLBaseHeader(this.b);
        JSONObject jSONObject2 = (JSONObject) jSONObjectBuildSLBaseHeader.opt("header");
        jSONObject2.put("din", UmengMessageDeviceConfig.getDINAes(this.b));
        jSONObject2.put(o.ay, MsgConstant.SDK_VERSION);
        jSONObject2.put("push_switch", UmengMessageDeviceConfig.isNotificationEnabled(this.b));
        jSONObjectBuildSLBaseHeader.put("header", jSONObject2);
        JSONObject jSONObject3 = new JSONObject();
        jSONObject3.put(MsgConstant.KEY_DEVICE_TOKEN, MessageSharedPrefs.getInstance(this.b).getDeviceToken());
        final JSONObject jSONObject4 = new JSONObject();
        jSONObject4.put("push", jSONObject3);
        if (h.d(this.b)) {
            try {
                UMConfigureImpl.registerMessageSendListener(new onMessageSendListener() { // from class: com.umeng.message.common.impl.json.JUtrack.2
                    @Override // com.umeng.commonsdk.utils.onMessageSendListener
                    public void onMessageSend() {
                        try {
                            JSONObject jSONObjectBuildSLEnvelope = uMSLEnvelopeBuild.buildSLEnvelope(JUtrack.this.b, jSONObjectBuildSLBaseHeader, jSONObject4, MsgConstant.UMPX_PUSH_LAUNCH);
                            if (jSONObjectBuildSLEnvelope != null && !jSONObjectBuildSLEnvelope.has(c.c)) {
                                m.a(JUtrack.this.b).a(System.currentTimeMillis());
                                int i = Integer.parseInt(UMEnvelopeBuild.imprintProperty(JUtrack.this.b, "launch_policy", "-1"));
                                UMLog uMLog = UMConfigure.umDebugLog;
                                UMLog.mutlInfo(JUtrack.a, 2, "launch_policy:" + i);
                                int i2 = Integer.parseInt(UMEnvelopeBuild.imprintProperty(JUtrack.this.b, "tag_policy", "-1"));
                                UMLog uMLog2 = UMConfigure.umDebugLog;
                                UMLog.mutlInfo(JUtrack.a, 2, "tag_policy:" + i2);
                                if (i > 0) {
                                    MessageSharedPrefs.getInstance(JUtrack.this.b).setAppLaunchLogSendPolicy(i);
                                }
                                if (i2 > 0) {
                                    MessageSharedPrefs.getInstance(JUtrack.this.b).setTagSendPolicy(i2);
                                }
                            }
                            UMConfigureImpl.removeMessageSendListener(this);
                        } catch (Throwable unused) {
                        }
                    }
                });
                return;
            } catch (Throwable unused) {
                return;
            }
        }
        JSONObject jSONObject5 = new JSONObject();
        jSONObject5.put("jsonHeader", jSONObjectBuildSLBaseHeader);
        jSONObject5.put("jsonBody", jSONObject4);
        Intent intent = new Intent();
        intent.setPackage(this.b.getPackageName());
        intent.setAction(MsgConstant.MESSAGE_MESSAGE_SEND_ACTION);
        intent.putExtra(MsgConstant.KEY_UMPX_PATH, MsgConstant.UMPX_PUSH_LAUNCH);
        intent.putExtra(MsgConstant.KEY_SENDMESSAGE, jSONObject5.toString());
        UMJobIntentService.enqueueWork(this.b, (Class<? extends UMJobIntentService>) UmengMessageCallbackHandlerService.class, intent);
    }

    @Override // com.umeng.message.common.inter.IUtrack
    public void trackRegister(JSONObject jSONObject) throws Exception {
        UMSLEnvelopeBuild.mContext = this.b;
        final UMSLEnvelopeBuild uMSLEnvelopeBuild = new UMSLEnvelopeBuild();
        final JSONObject jSONObjectBuildSLBaseHeader = uMSLEnvelopeBuild.buildSLBaseHeader(this.b);
        JSONObject jSONObject2 = (JSONObject) jSONObjectBuildSLBaseHeader.opt("header");
        jSONObject2.put("din", UmengMessageDeviceConfig.getDINAes(this.b));
        jSONObject2.put(o.ay, MsgConstant.SDK_VERSION);
        jSONObject2.put("push_switch", UmengMessageDeviceConfig.isNotificationEnabled(this.b));
        jSONObjectBuildSLBaseHeader.put("header", jSONObject2);
        JSONObject jSONObject3 = new JSONObject();
        jSONObject3.put(MsgConstant.KEY_DEVICE_TOKEN, MessageSharedPrefs.getInstance(this.b).getDeviceToken());
        final JSONObject jSONObject4 = new JSONObject();
        jSONObject4.put("push", jSONObject3);
        if (h.d(this.b)) {
            try {
                UMConfigureImpl.registerMessageSendListener(new onMessageSendListener() { // from class: com.umeng.message.common.impl.json.JUtrack.3
                    @Override // com.umeng.commonsdk.utils.onMessageSendListener
                    public void onMessageSend() {
                        try {
                            JSONObject jSONObjectBuildSLEnvelope = uMSLEnvelopeBuild.buildSLEnvelope(JUtrack.this.b, jSONObjectBuildSLBaseHeader, jSONObject4, MsgConstant.UMPX_PUSH_REGISTER);
                            if (jSONObjectBuildSLEnvelope != null && !jSONObjectBuildSLEnvelope.has(c.c)) {
                                MessageSharedPrefs.getInstance(JUtrack.this.b).setHasResgister(true);
                                if (TextUtils.isEmpty(MessageSharedPrefs.getInstance(JUtrack.this.b).getDeviceToken())) {
                                    UMLog uMLog = UMConfigure.umDebugLog;
                                    UMLog.mutlInfo(JUtrack.a, 0, "setRegisteredToUmeng: device token为空");
                                }
                            }
                            UMConfigureImpl.removeMessageSendListener(this);
                        } catch (Throwable unused) {
                        }
                    }
                });
            } catch (Throwable unused) {
            }
        }
    }

    @Override // com.umeng.message.common.inter.IUtrack
    public void trackLocation(JSONObject jSONObject) throws Exception {
        JSONObject jSONObjectSendRequest;
        try {
            try {
                jSONObjectSendRequest = sendRequest(jSONObject, MsgConstant.LBS_ENDPOINT);
            } catch (Exception e) {
                if (e.getMessage() != null && e.getMessage().contains(MsgConstant.HTTPSDNS_ERROR) && UmengMessageDeviceConfig.isOnline(this.b)) {
                    jSONObjectSendRequest = sendRequest(this.b, jSONObject, MsgConstant.LBS_ENDPOINT);
                } else {
                    throw new Exception(e);
                }
            }
            if (jSONObjectSendRequest == null || !TextUtils.equals(jSONObjectSendRequest.getString("success"), ITagManager.SUCCESS)) {
                return;
            }
            UMLog uMLog = UMConfigure.umDebugLog;
            UMLog.mutlInfo(a, 2, "location track success");
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    @Override // com.umeng.message.common.inter.IUtrack
    public void addAlias(String str, String str2, JSONObject jSONObject, UTrack.ICallBack iCallBack) throws Exception {
        JSONObject jSONObjectSendRequest;
        String strOptString = jSONObject.optString("fail", "");
        String strOptString2 = jSONObject.optString("success", "");
        UMLog uMLog = UMConfigure.umDebugLog;
        UMLog.mutlInfo(a, 2, "keyfail:" + strOptString + ",keysuccess:" + strOptString2);
        if (strOptString.equals("") && strOptString2.equals("")) {
            try {
                jSONObjectSendRequest = sendRequest(jSONObject, MsgConstant.ALIAS_ENDPOINT);
            } catch (Exception e) {
                if (e.getMessage() != null && e.getMessage().contains(MsgConstant.HTTPSDNS_ERROR) && UmengMessageDeviceConfig.isOnline(this.b)) {
                    jSONObjectSendRequest = sendRequest(this.b, jSONObject, MsgConstant.ALIAS_ENDPOINT);
                } else {
                    throw new Exception(e);
                }
            }
            if (jSONObjectSendRequest != null && TextUtils.equals(jSONObjectSendRequest.optString("success", ""), ITagManager.SUCCESS)) {
                MessageSharedPrefs.getInstance(this.b).addAlias(str, str2, 0, 0, "");
                try {
                    JSONObject jSONObject2 = new JSONObject();
                    jSONObject2.put("interval", jSONObjectSendRequest.optLong("interval", 0L));
                    jSONObject2.put("last_requestTime", System.currentTimeMillis());
                    MessageSharedPrefs.getInstance(this.b).add_addAliasInterval(jSONObject2.toString());
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
                iCallBack.onMessage(true, "alias:" + str + "添加成功");
                return;
            }
            MessageSharedPrefs.getInstance(this.b).addAlias(str, str2, 0, 1, "网络请求失败alias:" + str + ",type:" + str2 + ",devicetoken:" + MessageSharedPrefs.getInstance(this.b).getDeviceToken());
            StringBuilder sb = new StringBuilder();
            sb.append("alias:");
            sb.append(str);
            sb.append("添加失败");
            iCallBack.onMessage(false, sb.toString());
            return;
        }
        if (!strOptString.equals("")) {
            iCallBack.onMessage(false, "alias:" + str + "添加失败");
            MessageSharedPrefs.getInstance(this.b).addAlias(str, str2, 0, 1, strOptString);
        }
        if (strOptString2.equals("")) {
            return;
        }
        iCallBack.onMessage(true, "alias:" + str + "已经添加");
        MessageSharedPrefs.getInstance(this.b).addAlias(str, str2, 0, 2, strOptString2);
    }

    @Override // com.umeng.message.common.inter.IUtrack
    public void setAlias(String str, String str2, JSONObject jSONObject, UTrack.ICallBack iCallBack) throws Exception {
        JSONObject jSONObjectSendRequest;
        String strOptString = jSONObject.optString("fail", "");
        String strOptString2 = jSONObject.optString("success", "");
        UMLog uMLog = UMConfigure.umDebugLog;
        UMLog.mutlInfo(a, 2, "keyfail:" + strOptString + ",keysuccess:" + strOptString2);
        if (strOptString.equals("") && strOptString2.equals("")) {
            try {
                jSONObjectSendRequest = sendRequest(jSONObject, MsgConstant.ALIAS_EXCLUSIVE_ENDPOINT);
            } catch (Exception e) {
                if (e.getMessage() != null && e.getMessage().contains(MsgConstant.HTTPSDNS_ERROR) && UmengMessageDeviceConfig.isOnline(this.b)) {
                    jSONObjectSendRequest = sendRequest(this.b, jSONObject, MsgConstant.ALIAS_EXCLUSIVE_ENDPOINT);
                } else {
                    throw new Exception(e);
                }
            }
            if (jSONObjectSendRequest != null && TextUtils.equals(jSONObjectSendRequest.optString("success", ""), ITagManager.SUCCESS)) {
                MessageSharedPrefs.getInstance(this.b).addAlias(str, str2, 1, 0, "");
                try {
                    JSONObject jSONObject2 = new JSONObject();
                    jSONObject2.put("interval", jSONObjectSendRequest.optLong("interval", 0L));
                    jSONObject2.put("last_requestTime", System.currentTimeMillis());
                    MessageSharedPrefs.getInstance(this.b).add_setAliasInterval(jSONObject2.toString());
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
                iCallBack.onMessage(true, "alias:" + str + "添加成功");
                return;
            }
            MessageSharedPrefs.getInstance(this.b).addAlias(str, str2, 1, 1, "网络请求失败alias:" + str + ",type:" + str2 + ",devicetoken:" + MessageSharedPrefs.getInstance(this.b).getDeviceToken());
            StringBuilder sb = new StringBuilder();
            sb.append("alias:");
            sb.append(str);
            sb.append("添加失败");
            iCallBack.onMessage(false, sb.toString());
            return;
        }
        if (!strOptString.equals("")) {
            iCallBack.onMessage(false, "alias:" + str + "添加失败");
            MessageSharedPrefs.getInstance(this.b).addAlias(str, str2, 1, 1, strOptString);
        }
        if (strOptString2.equals("")) {
            return;
        }
        iCallBack.onMessage(true, "alias:" + str + "已经添加");
        MessageSharedPrefs.getInstance(this.b).addAlias(str, str2, 1, 2, strOptString2);
    }

    @Override // com.umeng.message.common.inter.IUtrack
    public void deleteAlias(String str, String str2, JSONObject jSONObject, UTrack.ICallBack iCallBack) throws Exception {
        JSONObject jSONObjectSendRequest;
        try {
            jSONObjectSendRequest = sendRequest(jSONObject, MsgConstant.DELETE_ALIAS_ENDPOINT);
        } catch (Exception e) {
            if (e.getMessage() != null && e.getMessage().contains(MsgConstant.HTTPSDNS_ERROR) && UmengMessageDeviceConfig.isOnline(this.b)) {
                jSONObjectSendRequest = sendRequest(this.b, jSONObject, MsgConstant.DELETE_ALIAS_ENDPOINT);
            } else {
                throw new Exception(e);
            }
        }
        if (jSONObjectSendRequest == null || !TextUtils.equals(jSONObjectSendRequest.getString("success"), ITagManager.SUCCESS)) {
            return;
        }
        MessageSharedPrefs.getInstance(this.b).removeAlias(0, str, str2);
        MessageSharedPrefs.getInstance(this.b).removeAlias(1, str, str2);
        try {
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("interval", jSONObjectSendRequest.optLong("interval", 0L));
            jSONObject2.put("last_requestTime", System.currentTimeMillis());
            MessageSharedPrefs.getInstance(this.b).add_deleteAliasInterval(jSONObject2.toString());
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        iCallBack.onMessage(true, "alias:" + str + ",type:" + str2 + "删除成功");
    }

    public static JSONObject sendRequest(JSONObject jSONObject, String str) throws Exception {
        String strBody = HttpRequest.post(str).acceptJson().contentType(HttpRequest.CONTENT_TYPE_JSON).send(jSONObject.toString()).body("UTF-8");
        UMLog uMLog = UMConfigure.umDebugLog;
        UMLog.mutlInfo(a, 2, "sendRequest() url=" + str + "\n request = " + jSONObject + "\n response = " + strBody);
        return new JSONObject(strBody);
    }

    public static JSONObject sendRequest(Context context, JSONObject jSONObject, String str) throws Exception {
        String host = new URL(str).getHost();
        String strA = b.a(context, host);
        UMLog uMLog = UMConfigure.umDebugLog;
        UMLog.mutlInfo(a, 2, "ip:" + strA);
        if (strA == null) {
            return null;
        }
        URL url = new URL(str.replaceFirst(host, strA));
        String strBody = HttpRequest.post(url).acceptJson().contentType(HttpRequest.CONTENT_TYPE_JSON).header(Constants.HOST, host).trustHosts().send(jSONObject.toString()).body("UTF-8");
        UMLog uMLog2 = UMConfigure.umDebugLog;
        UMLog.mutlInfo(a, 2, "dns-->sendRequest() url=" + url.toString() + "\n request = " + jSONObject + "\n response = " + strBody);
        return new JSONObject(strBody);
    }

    @Override // com.umeng.message.common.inter.IUtrack
    public void sendAliasFailLog(JSONObject jSONObject) {
        if (MessageSharedPrefs.getInstance(this.b).getDaRegisterSendPolicy() == 1) {
            UMLog uMLog = UMConfigure.umDebugLog;
            UMLog.mutlInfo(a, 2, "da_register_policy=1, skip sending da_register info.");
            return;
        }
        try {
            ContentResolver contentResolver = this.b.getContentResolver();
            com.umeng.message.provider.a.a(this.b);
            Cursor cursorQuery = contentResolver.query(com.umeng.message.provider.a.d, new String[]{"message", l.D}, "error=?", new String[]{"1"}, null);
            if (cursorQuery == null) {
                return;
            }
            ArrayList<Alias> arrayList = new ArrayList();
            cursorQuery.moveToFirst();
            while (!cursorQuery.isAfterLast()) {
                String string = cursorQuery.getString(cursorQuery.getColumnIndex("message"));
                long j = cursorQuery.getLong(cursorQuery.getColumnIndex(l.D));
                Alias alias = new Alias();
                alias.aliasMessage = string;
                alias.aliasTime = j;
                arrayList.add(alias);
                cursorQuery.moveToNext();
            }
            if (cursorQuery != null) {
                cursorQuery.close();
            }
            for (Alias alias2 : arrayList) {
                a(jSONObject, alias2.aliasMessage, alias2.aliasTime);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.umeng.message.common.inter.IUtrack
    public void sendRegisterLog(JSONObject jSONObject) throws Exception {
        JSONObject jSONObjectSendRequest;
        int i;
        try {
            jSONObjectSendRequest = sendRequest(jSONObject, MsgConstant.ALIAS_LOG);
        } catch (Exception e) {
            if (e.getMessage() != null && e.getMessage().contains(MsgConstant.HTTPSDNS_ERROR) && UmengMessageDeviceConfig.isOnline(this.b)) {
                jSONObjectSendRequest = sendRequest(this.b, jSONObject, MsgConstant.ALIAS_LOG);
            } else {
                throw new Exception(e);
            }
        }
        if (jSONObjectSendRequest == null || !TextUtils.equals(jSONObjectSendRequest.optString("success", ""), ITagManager.SUCCESS) || (i = Integer.parseInt(jSONObjectSendRequest.getString("da_register_policy"))) <= 0) {
            return;
        }
        MessageSharedPrefs.getInstance(this.b).setDaRegisterSendPolicy(i);
    }

    private void a(JSONObject jSONObject, String str, long j) throws Exception {
        JSONObject jSONObjectSendRequest;
        if (str.equals("")) {
            return;
        }
        jSONObject.put(MsgConstant.KEY_ALIAS_FAIL_LOG, str);
        try {
            jSONObjectSendRequest = sendRequest(jSONObject, MsgConstant.ALIAS_LOG);
        } catch (Exception e) {
            if (e.getMessage() != null && e.getMessage().contains(MsgConstant.HTTPSDNS_ERROR) && UmengMessageDeviceConfig.isOnline(this.b)) {
                jSONObjectSendRequest = sendRequest(this.b, jSONObject, MsgConstant.ALIAS_LOG);
            } else {
                throw new Exception(e);
            }
        }
        if (jSONObjectSendRequest == null || !TextUtils.equals(jSONObjectSendRequest.optString("success", ""), ITagManager.SUCCESS)) {
            return;
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put("error", "3");
        String[] strArr = {j + ""};
        ContentResolver contentResolver = this.b.getContentResolver();
        com.umeng.message.provider.a.a(this.b);
        contentResolver.update(com.umeng.message.provider.a.d, contentValues, "time=?", strArr);
        int i = Integer.parseInt(jSONObjectSendRequest.optString("da_register_policy"));
        if (i > 0) {
            MessageSharedPrefs.getInstance(this.b).setDaRegisterSendPolicy(i);
        }
    }
}
