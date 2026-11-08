package com.umeng.message;

import android.annotation.SuppressLint;
import android.content.ContentProviderOperation;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.OperationApplicationException;
import android.os.Environment;
import android.os.RemoteException;
import android.text.TextUtils;
import com.taobao.agoo.TaobaoRegister;
import com.umeng.commonsdk.UMConfigure;
import com.umeng.commonsdk.UMConfigureImpl;
import com.umeng.commonsdk.debug.UMLog;
import com.umeng.commonsdk.framework.c;
import com.umeng.commonsdk.proguard.b;
import com.umeng.commonsdk.proguard.o;
import com.umeng.commonsdk.stateless.UMSLEnvelopeBuild;
import com.umeng.commonsdk.utils.onMessageSendListener;
import com.umeng.message.common.UmengMessageDeviceConfig;
import com.umeng.message.common.d;
import com.umeng.message.common.impl.json.JUtrack;
import com.umeng.message.common.inter.IUtrack;
import com.umeng.message.entity.UMessage;
import com.umeng.message.entity.Ucode;
import com.umeng.message.proguard.h;
import com.umeng.message.proguard.m;
import com.umeng.message.provider.a;
import com.umeng.message.service.UMJobIntentService;
import com.xiaomi.mipush.sdk.Constants;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class UTrack {
    private static final String a = "com.umeng.message.UTrack";
    private static UTrack d;
    private static IUtrack e;
    private static boolean i;
    private static boolean j;
    private static boolean k;
    private JSONObject b;
    private JSONObject c;
    private Context f;
    private boolean g;
    private final String h = "appkey";

    public interface ICallBack {
        void onMessage(boolean z, String str);
    }

    enum SuccessState {
        SUCCESS_CACHE,
        SUCCESS,
        FAIL_REQUEST,
        FAIL_PARAM
    }

    public void setClearPrevMessage(boolean z) {
        this.g = z;
    }

    private UTrack(Context context) {
        this.f = context.getApplicationContext();
        e();
    }

    public static synchronized UTrack getInstance(Context context) {
        if (d == null) {
            d = new UTrack(context);
            e = new JUtrack(context);
        }
        return d;
    }

    public void trackMsgArrival(UMessage uMessage) {
        if (uMessage == null || uMessage.msg_id == null) {
            return;
        }
        b(uMessage.msg_id, 0, 60000 * uMessage.random_min, uMessage.pulledWho);
    }

    public void trackMsgClick(UMessage uMessage) {
        if (uMessage != null && uMessage.msg_id != null) {
            b(uMessage.msg_id, 1, 60000 * uMessage.random_min, uMessage.pulledWho);
        }
        if (uMessage != null && uMessage.message_id != null) {
            a(uMessage.message_id, uMessage.task_id, "8");
        }
        if (this.g) {
            if (UMessage.DISPLAY_TYPE_NOTIFICATIONPULLAPP.equals(uMessage.display_type)) {
                ((UmengAdHandler) PushAgent.getInstance(this.f).getAdHandler()).setPrevMessage(null);
            } else {
                ((UmengMessageHandler) PushAgent.getInstance(this.f).getMessageHandler()).setPrevMessage(null);
            }
        }
    }

    public void trackMiPushMsgClick(UMessage uMessage) {
        if (uMessage != null && uMessage.msg_id != null) {
            b(uMessage.msg_id, 21, 60000 * uMessage.random_min, uMessage.pulledWho);
        }
        if (this.g) {
            if (UMessage.DISPLAY_TYPE_NOTIFICATIONPULLAPP.equals(uMessage.display_type)) {
                ((UmengAdHandler) PushAgent.getInstance(this.f).getAdHandler()).setPrevMessage(null);
            } else {
                ((UmengMessageHandler) PushAgent.getInstance(this.f).getMessageHandler()).setPrevMessage(null);
            }
        }
    }

    public void trackMsgDismissed(UMessage uMessage) {
        if (uMessage != null && uMessage.msg_id != null) {
            b(uMessage.msg_id, 2, 60000 * uMessage.random_min, uMessage.pulledWho);
        }
        if (uMessage != null && uMessage.message_id != null) {
            a(uMessage.message_id, uMessage.task_id, "9");
        }
        if (UMessage.DISPLAY_TYPE_NOTIFICATIONPULLAPP.equals(uMessage.display_type)) {
            ((UmengAdHandler) PushAgent.getInstance(this.f).getAdHandler()).setPrevMessage(null);
        } else {
            ((UmengMessageHandler) PushAgent.getInstance(this.f).getMessageHandler()).setPrevMessage(null);
        }
    }

    void a(UMessage uMessage) {
        b(uMessage.recall, 4, uMessage.random_min * 60000, uMessage.pulledWho);
    }

    void b(UMessage uMessage) {
        b(uMessage.recall, 5, uMessage.random_min * 60000, uMessage.pulledWho);
    }

    public void trackMsgPulled(UMessage uMessage, int i2) {
        if (uMessage == null || uMessage.msg_id == null) {
            return;
        }
        b(uMessage.msg_id, i2, uMessage.random_min * 60000, uMessage.pulledWho);
    }

    private void a(final String str, final int i2, long j2, final String str2) {
        if (f()) {
            if (TextUtils.isEmpty(str)) {
                UMLog uMLog = UMConfigure.umDebugLog;
                UMLog.mutlInfo(a, 0, "trackMsgLog: msgId为空");
                return;
            }
            final long jCurrentTimeMillis = System.currentTimeMillis();
            try {
                m.a(this.f).a(str, i2, jCurrentTimeMillis, str2);
            } catch (Exception e2) {
                e2.printStackTrace();
                UMLog uMLog2 = UMConfigure.umDebugLog;
                UMLog.mutlInfo(a, 2, "trackMsgLog: ", e2.toString());
            }
            Runnable runnable = new Runnable() { // from class: com.umeng.message.UTrack.1
                @Override // java.lang.Runnable
                public void run() {
                    UTrack.this.c(str, i2, jCurrentTimeMillis, str2);
                }
            };
            long jAbs = 0;
            if (j2 > 0 && i2 != 1 && i2 != 21) {
                jAbs = Math.abs(new Random().nextLong() % j2);
            }
            UMLog uMLog3 = UMConfigure.umDebugLog;
            UMLog.mutlInfo(a, 2, String.format("trackMsgLog(msgId=%s, actionType=%d, random=%d, delay=%d)", str, Integer.valueOf(i2), Long.valueOf(j2), Long.valueOf(jAbs)));
            d.a(runnable, jAbs, TimeUnit.MILLISECONDS);
        }
    }

    private void b(String str, int i2, long j2, String str2) {
        a(str, i2, j2, str2);
    }

    private void a(final String str, final String str2, final String str3) {
        d.a(new Runnable() { // from class: com.umeng.message.UTrack.5
            @Override // java.lang.Runnable
            public void run() {
                UTrack.this.sendMsgLogForAgoo(str, str2, str3);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void c(String str, int i2, long j2, String str2) {
        if (str2 == null) {
            str2 = "";
        }
        try {
            JSONObject jSONObjectI = i();
            jSONObjectI.put("msg_id", str);
            jSONObjectI.put(MsgConstant.KEY_ACTION_TYPE, i2);
            jSONObjectI.put("ts", j2);
            jSONObjectI.put("pa", str2);
            e.sendMsgLog(jSONObjectI, str, i2);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public synchronized void sendMsgLogForAgoo(String str, String str2, String str3) {
        UMLog uMLog = UMConfigure.umDebugLog;
        UMLog.mutlInfo(a, 2, "sendMsgLogForAgoo-->msgId:" + str + ",taskId:" + str2);
        if (str3.equalsIgnoreCase("8")) {
            TaobaoRegister.clickMessage(this.f, str, str2);
        } else {
            TaobaoRegister.dismissMessage(this.f, str, str2);
        }
        m.a(this.f).b(str, str3);
        if (!str3.equals("7")) {
            m.a(this.f).d(str);
        }
    }

    private void a(long j2) {
        if (f()) {
            if (i) {
                UMLog uMLog = UMConfigure.umDebugLog;
                UMLog.mutlInfo(a, 2, "appCachedPushlog已经在队列里, 忽略这次请求");
                return;
            }
            UMLog uMLog2 = UMConfigure.umDebugLog;
            UMLog.mutlInfo(a, 2, "appCachedPushlog开始, 设置appLaunchSending标志位");
            i = true;
            if (h.d(this.f)) {
                new Thread(new Runnable() { // from class: com.umeng.message.UTrack.6
                    @Override // java.lang.Runnable
                    public void run() throws InterruptedException {
                        try {
                            Thread.sleep(b.d);
                            boolean unused = UTrack.i = false;
                        } catch (InterruptedException e2) {
                            e2.printStackTrace();
                        }
                    }
                }).start();
            }
            d.a(new Runnable() { // from class: com.umeng.message.UTrack.7
                @Override // java.lang.Runnable
                public void run() throws JSONException {
                    try {
                        final JSONArray jSONArray = new JSONArray();
                        ArrayList<m.a> arrayListA = m.a(UTrack.this.f).a();
                        if (arrayListA == null || arrayListA.size() <= 0) {
                            return;
                        }
                        for (int i2 = 0; i2 < arrayListA.size(); i2++) {
                            m.a aVar = arrayListA.get(i2);
                            JSONObject jSONObject = new JSONObject();
                            jSONObject.put("ts", aVar.b);
                            jSONObject.put("pa", aVar.d);
                            jSONObject.put(MsgConstant.KEY_DEVICE_TOKEN, MessageSharedPrefs.getInstance(UTrack.this.f).getDeviceToken());
                            jSONObject.put("msg_id", aVar.a);
                            jSONObject.put(MsgConstant.KEY_ACTION_TYPE, aVar.c);
                            jSONArray.put(jSONObject);
                        }
                        UMSLEnvelopeBuild.mContext = UTrack.this.f;
                        final UMSLEnvelopeBuild uMSLEnvelopeBuild = new UMSLEnvelopeBuild();
                        final JSONObject jSONObjectBuildSLBaseHeader = uMSLEnvelopeBuild.buildSLBaseHeader(UTrack.this.f);
                        JSONObject jSONObject2 = (JSONObject) jSONObjectBuildSLBaseHeader.opt("header");
                        jSONObject2.put("din", UmengMessageDeviceConfig.getDINAes(UTrack.this.f));
                        jSONObject2.put(o.ay, MsgConstant.SDK_VERSION);
                        jSONObject2.put("umid", UmengMessageDeviceConfig.getUmid(UTrack.this.f));
                        jSONObjectBuildSLBaseHeader.put("header", jSONObject2);
                        final JSONObject jSONObject3 = new JSONObject();
                        jSONObject3.put("push", jSONArray);
                        if (h.d(UTrack.this.f)) {
                            try {
                                UMConfigureImpl.registerMessageSendListener(new onMessageSendListener() { // from class: com.umeng.message.UTrack.7.1
                                    @Override // com.umeng.commonsdk.utils.onMessageSendListener
                                    public void onMessageSend() {
                                        try {
                                            JSONObject jSONObjectBuildSLEnvelope = uMSLEnvelopeBuild.buildSLEnvelope(UTrack.this.f, jSONObjectBuildSLBaseHeader, jSONObject3, MsgConstant.UNPX_PUSH_LOGS);
                                            if (jSONObjectBuildSLEnvelope != null && !jSONObjectBuildSLEnvelope.has(c.c)) {
                                                UTrack.this.a(jSONArray);
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
                        JSONObject jSONObject4 = new JSONObject();
                        jSONObject4.put("jsonHeader", jSONObjectBuildSLBaseHeader);
                        jSONObject4.put("jsonBody", jSONObject3);
                        Intent intent = new Intent();
                        intent.setPackage(UTrack.this.f.getPackageName());
                        intent.setAction(MsgConstant.MESSAGE_MESSAGE_SEND_ACTION);
                        intent.putExtra(MsgConstant.KEY_UMPX_PATH, MsgConstant.UNPX_PUSH_LOGS);
                        intent.putExtra(MsgConstant.KEY_SENDMESSAGE, jSONObject4.toString());
                        UMJobIntentService.enqueueWork(UTrack.this.f, (Class<? extends UMJobIntentService>) UmengMessageCallbackHandlerService.class, intent);
                    } catch (JSONException e2) {
                        e2.printStackTrace();
                    }
                }
            });
            d.a(new Runnable() { // from class: com.umeng.message.UTrack.8
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        ArrayList<m.b> arrayListC = m.a(UTrack.this.f).c();
                        for (int i2 = 0; i2 < arrayListC.size(); i2++) {
                            m.b bVar = arrayListC.get(i2);
                            UTrack.this.sendMsgLogForAgoo(bVar.a, bVar.b, bVar.c);
                        }
                    } catch (Throwable th) {
                        th.printStackTrace();
                        UMLog uMLog3 = UMConfigure.umDebugLog;
                        UMLog.mutlInfo(UTrack.a, 2, th.toString());
                    }
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(JSONArray jSONArray) throws RemoteException, OperationApplicationException {
        if (jSONArray == null) {
            return;
        }
        try {
            ArrayList<ContentProviderOperation> arrayList = new ArrayList<>();
            if (jSONArray.length() > 0) {
                for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                    JSONObject jSONObject = (JSONObject) jSONArray.get(i2);
                    String strOptString = jSONObject.optString("msg_id");
                    int iOptInt = jSONObject.optInt(MsgConstant.KEY_ACTION_TYPE);
                    a.a(this.f);
                    arrayList.add(ContentProviderOperation.newDelete(a.f).withSelection("MsgId=? And ActionType=?", new String[]{strOptString, iOptInt + ""}).build());
                    if (iOptInt != 0) {
                        a.a(this.f);
                        arrayList.add(ContentProviderOperation.newDelete(a.g).withSelection("MsgId=?", new String[]{strOptString}).build());
                    }
                }
            }
            ContentResolver contentResolver = this.f.getContentResolver();
            a.a(this.f);
            contentResolver.applyBatch(a.a, arrayList);
        } catch (Exception unused) {
        }
    }

    public void sendCachedMsgLog(long j2) {
        a(j2);
    }

    public void trackAppLaunch(long j2) {
        if (f()) {
            if (MessageSharedPrefs.getInstance(this.f).getAppLaunchLogSendPolicy() == 1) {
                UMLog uMLog = UMConfigure.umDebugLog;
                UMLog.mutlInfo(a, 2, "launch_policy=1, 跳过发送应用程序启动信息");
            } else {
                if (MessageSharedPrefs.getInstance(this.f).hasAppLaunchLogSentToday()) {
                    return;
                }
                b(j2);
            }
        }
    }

    private void b(long j2) {
        if (j) {
            UMLog uMLog = UMConfigure.umDebugLog;
            UMLog.mutlInfo(a, 2, "trackAppLaunch已经在队列里, 忽略这次请求");
            return;
        }
        UMLog uMLog2 = UMConfigure.umDebugLog;
        UMLog.mutlInfo(a, 2, "trackAppLaunch开始, 设置appLaunchSending标志位");
        j = true;
        Runnable runnable = new Runnable() { // from class: com.umeng.message.UTrack.9
            @Override // java.lang.Runnable
            public void run() {
                try {
                    JSONObject jSONObjectH = UTrack.this.h();
                    JSONArray jSONArrayD = UTrack.this.d();
                    if (jSONArrayD != null) {
                        jSONObjectH.put(MsgConstant.KEY_UCODE, com.umeng.message.proguard.c.a(jSONArrayD.toString()));
                    }
                    UTrack.e.trackAppLaunch(jSONObjectH);
                } catch (Exception unused) {
                } finally {
                    boolean unused2 = UTrack.j = false;
                }
            }
        };
        UMLog uMLog3 = UMConfigure.umDebugLog;
        UMLog.mutlInfo(a, 2, String.format("trackAppLaunch(delay=%d)", Long.valueOf(j2)));
        d.a(runnable, j2, TimeUnit.MILLISECONDS);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public JSONArray d() {
        List<Ucode> listG;
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        String ucode = MessageSharedPrefs.getInstance(this.f).getUcode();
        if (ucode == null || ucode.equals("")) {
            listG = null;
        } else {
            try {
                listG = h.g(ucode);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        if (listG == null) {
            return null;
        }
        for (int i2 = 0; i2 < listG.size(); i2++) {
            sb.append("{");
            sb.append("\"p\":");
            sb.append("\"");
            sb.append(listG.get(i2).p);
            sb.append("\"");
            sb.append(Constants.ACCEPT_TIME_SEPARATOR_SP);
            sb.append("\"t\":");
            sb.append(listG.get(i2).b);
            sb.append("}");
            if (i2 != listG.size() - 1) {
                sb.append(Constants.ACCEPT_TIME_SEPARATOR_SP);
            }
        }
        sb.append("]");
        try {
            return new JSONArray(sb.toString());
        } catch (Exception e3) {
            e3.printStackTrace();
            return null;
        }
    }

    void a() {
        if (f() && !MessageSharedPrefs.getInstance(this.f).getHasRegister()) {
            if (k) {
                UMLog uMLog = UMConfigure.umDebugLog;
                UMLog.mutlInfo(a, 2, "sendRegisterLog已经在队列里，忽略这次请求");
                return;
            }
            UMLog uMLog2 = UMConfigure.umDebugLog;
            UMLog.mutlInfo(a, 2, "trackRegisterLog开始, 设置registerSending标志位");
            k = true;
            Runnable runnable = new Runnable() { // from class: com.umeng.message.UTrack.10
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        try {
                            JSONObject jSONObjectH = UTrack.this.h();
                            UMLog uMLog3 = UMConfigure.umDebugLog;
                            UMLog.mutlInfo(UTrack.a, 2, "trackRegister-->request:" + jSONObjectH.toString());
                            String strG = UTrack.this.g();
                            if (!h.d(strG)) {
                                UMLog uMLog4 = UMConfigure.umDebugLog;
                                UMLog.mutlInfo(UTrack.a, 2, "TestDevice sign =" + strG);
                                jSONObjectH.put("TD", strG);
                            }
                            UTrack.e.trackRegister(jSONObjectH);
                        } catch (Exception e2) {
                            e2.printStackTrace();
                        }
                    } finally {
                        boolean unused = UTrack.k = false;
                    }
                }
            };
            UMLog uMLog3 = UMConfigure.umDebugLog;
            UMLog.mutlInfo(a, 2, String.format("trackRegister(delay=%d)", 0));
            d.a(runnable, 0L, TimeUnit.MILLISECONDS);
        }
    }

    private void e() {
        if (this.b == null) {
            com.umeng.message.common.b bVar = new com.umeng.message.common.b();
            bVar.b(this.f, new String[0]);
            bVar.a(this.f, PushAgent.getInstance(this.f).getMessageAppkey(), PushAgent.getInstance(this.f).getMessageChannel());
            this.b = new JSONObject();
            try {
                bVar.b(this.b);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        if (this.c == null) {
            com.umeng.message.common.b bVar2 = new com.umeng.message.common.b();
            bVar2.c(this.f, new String[0]);
            bVar2.a(this.f, PushAgent.getInstance(this.f).getMessageAppkey(), PushAgent.getInstance(this.f).getMessageChannel());
            this.c = new JSONObject();
            try {
                bVar2.c(this.c);
            } catch (Exception e3) {
                e3.printStackTrace();
            }
        }
    }

    public JSONObject getHeader() {
        return this.b;
    }

    public void sendAliasFailLog() {
        new Thread(new Runnable() { // from class: com.umeng.message.UTrack.11
            @Override // java.lang.Runnable
            public void run() {
                try {
                    UTrack.e.sendAliasFailLog(UTrack.this.h());
                } catch (JSONException e2) {
                    e2.printStackTrace();
                }
            }
        }).start();
    }

    public void sendRegisterLog(final String str) {
        new Thread(new Runnable() { // from class: com.umeng.message.UTrack.12
            @Override // java.lang.Runnable
            public void run() throws JSONException {
                if (MessageSharedPrefs.getInstance(UTrack.this.f).getDaRegisterSendPolicy() != 1) {
                    try {
                        JSONObject jSONObjectH = UTrack.this.h();
                        jSONObjectH.put("registerLog", str);
                        UTrack.e.sendRegisterLog(jSONObjectH);
                        return;
                    } catch (Exception e2) {
                        e2.printStackTrace();
                        return;
                    }
                }
                UMLog uMLog = UMConfigure.umDebugLog;
                UMLog.mutlInfo(UTrack.a, 2, "da_register_policy=1, skip sending da_register info");
            }
        }).start();
    }

    public boolean addAlias(final String str, final String str2, final ICallBack iCallBack) {
        new Thread(new Runnable() { // from class: com.umeng.message.UTrack.2
            @Override // java.lang.Runnable
            public void run() throws JSONException {
                SuccessState successState = null;
                try {
                    String str3 = MessageSharedPrefs.getInstance(UTrack.this.f).get_addAliasInterval();
                    String strA = UTrack.this.a(str, str2, (str3 == null || str3.length() <= 0) ? null : new JSONObject(str3));
                    if (strA != null && strA.length() > 0) {
                        iCallBack.onMessage(false, strA);
                        return;
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
                String str4 = "";
                String str5 = "utdid:" + UmengMessageDeviceConfig.getUtdid(UTrack.this.f) + ",deviceToken:" + MessageSharedPrefs.getInstance(UTrack.this.f).getDeviceToken() + ";";
                if (TextUtils.isEmpty(str2) || TextUtils.isEmpty(str)) {
                    UMLog uMLog = UMConfigure.umDebugLog;
                    UMLog.mutlInfo(UTrack.a, 0, "addAlias: type或alias为空");
                    str5 = str5 + "addAlias: empty type or alias;";
                    successState = SuccessState.FAIL_PARAM;
                }
                if (TextUtils.isEmpty(UmengMessageDeviceConfig.getUtdid(UTrack.this.f))) {
                    UMLog uMLog2 = UMConfigure.umDebugLog;
                    UMLog.mutlInfo(UTrack.a, 0, "UTDID为空");
                    str5 = str5 + "UTDID is empty;";
                    successState = SuccessState.FAIL_PARAM;
                }
                if (TextUtils.isEmpty(MessageSharedPrefs.getInstance(UTrack.this.f).getDeviceToken())) {
                    UMLog uMLog3 = UMConfigure.umDebugLog;
                    UMLog.mutlInfo(UTrack.a, 0, "Device token为空");
                    str5 = str5 + "RegistrationId is empty;";
                    successState = SuccessState.FAIL_PARAM;
                }
                if (MessageSharedPrefs.getInstance(UTrack.this.f).isAliasSet(0, str, str2)) {
                    UMLog uMLog4 = UMConfigure.umDebugLog;
                    UMLog.mutlInfo(UTrack.a, 2, String.format("addAlias: <%s, %s> 已经同步至服务器，忽略该请求", str, str2));
                    str4 = "" + String.format("addAlias: <%s, %s> has been synced to the server before. Ingore this request;", str, str2);
                    successState = SuccessState.SUCCESS_CACHE;
                }
                try {
                    JSONObject jSONObjectH = UTrack.this.h();
                    if (successState == null) {
                        jSONObjectH.put("alias", str);
                        jSONObjectH.put("type", str2);
                        jSONObjectH.put(MsgConstant.KEY_LAST_ALIAS, MessageSharedPrefs.getInstance(UTrack.this.f).getLastAlias(0, str2));
                        jSONObjectH.put("ts", System.currentTimeMillis());
                    } else if (successState == SuccessState.FAIL_PARAM) {
                        jSONObjectH.put("fail", str5);
                    } else if (successState == SuccessState.SUCCESS_CACHE) {
                        jSONObjectH.put("success", str4);
                    }
                    UTrack.e.addAlias(str, str2, jSONObjectH, iCallBack);
                } catch (Exception e3) {
                    if (e3.getMessage() != null) {
                        if (iCallBack != null) {
                            iCallBack.onMessage(false, "alias:" + str + "添加失败:" + e3.getMessage());
                            MessageSharedPrefs.getInstance(UTrack.this.f).addAlias(str, str2, 0, 1, e3.getMessage());
                            return;
                        }
                        return;
                    }
                    if (iCallBack != null) {
                        iCallBack.onMessage(false, "alias:" + str + "添加失败");
                        MessageSharedPrefs.getInstance(UTrack.this.f).addAlias(str, str2, 0, 1, "添加失败");
                    }
                }
            }
        }).start();
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String a(String str, String str2, JSONObject jSONObject) throws UnsupportedEncodingException {
        try {
            byte[] bytes = str.getBytes("UTF-8");
            byte[] bytes2 = str2.getBytes("UTF-8");
            boolean z = true;
            boolean z2 = bytes.length <= 128 && bytes.length >= 0;
            if (bytes2.length > 64 || bytes2.length < 0) {
                z = false;
            }
            if (!z2 || !z) {
                return "alias长度不在0~128之间或aliasType长度不在0~64之间";
            }
            if (jSONObject == null) {
                return null;
            }
            long jOptLong = jSONObject.optLong("interval", 0L);
            long jOptLong2 = jSONObject.optLong("last_requestTime", 0L);
            long jCurrentTimeMillis = System.currentTimeMillis();
            if (jOptLong == 0) {
                return null;
            }
            if ((jCurrentTimeMillis - jOptLong2) / 1000 < jOptLong) {
                return "interval限制";
            }
            return null;
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public void setAlias(final String str, final String str2, final ICallBack iCallBack) {
        new Thread(new Runnable() { // from class: com.umeng.message.UTrack.3
            @Override // java.lang.Runnable
            public void run() throws JSONException {
                SuccessState successState = null;
                try {
                    String str3 = MessageSharedPrefs.getInstance(UTrack.this.f).get_setAliasInterval();
                    String strA = UTrack.this.a(str, str2, (str3 == null || str3.length() <= 0) ? null : new JSONObject(str3));
                    if (strA != null && strA.length() > 0) {
                        iCallBack.onMessage(false, strA);
                        return;
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
                String str4 = "";
                String str5 = "";
                if (TextUtils.isEmpty(str2)) {
                    UMLog uMLog = UMConfigure.umDebugLog;
                    UMLog.mutlInfo(UTrack.a, 0, "addExclusiveAlias: type为空");
                    str4 = "addExclusiveAlias: empty type";
                    successState = SuccessState.FAIL_PARAM;
                }
                if (TextUtils.isEmpty(UmengMessageDeviceConfig.getUtdid(UTrack.this.f))) {
                    UMLog uMLog2 = UMConfigure.umDebugLog;
                    UMLog.mutlInfo(UTrack.a, 0, "UTDID为空");
                    str4 = str4 + "UTDID is empty;";
                    successState = SuccessState.FAIL_PARAM;
                }
                if (TextUtils.isEmpty(MessageSharedPrefs.getInstance(UTrack.this.f).getDeviceToken())) {
                    UMLog uMLog3 = UMConfigure.umDebugLog;
                    UMLog.mutlInfo(UTrack.a, 0, "Device token为空");
                    str4 = str4 + "RegistrationId is empty;";
                    successState = SuccessState.FAIL_PARAM;
                }
                if (MessageSharedPrefs.getInstance(UTrack.this.f).isAliasSet(1, str, str2)) {
                    UMLog uMLog4 = UMConfigure.umDebugLog;
                    UMLog.mutlInfo(UTrack.a, 2, String.format("addExclusiveAlias: <%s, %s> 已经同步至服务器，忽略该请求", str, str2));
                    str5 = "" + String.format("addExclusiveAlias: <%s, %s> has been synced to the server before. Ingore this request.", str, str2);
                    successState = SuccessState.SUCCESS_CACHE;
                }
                try {
                    JSONObject jSONObjectH = UTrack.this.h();
                    if (successState == null) {
                        jSONObjectH.put("alias", str);
                        jSONObjectH.put("type", str2);
                        jSONObjectH.put(MsgConstant.KEY_LAST_ALIAS, MessageSharedPrefs.getInstance(UTrack.this.f).getLastAlias(1, str2));
                        jSONObjectH.put("ts", System.currentTimeMillis());
                    } else if (successState == SuccessState.FAIL_PARAM) {
                        jSONObjectH.put("fail", str4);
                    } else if (successState == SuccessState.SUCCESS_CACHE) {
                        jSONObjectH.put("success", str5);
                    }
                    UTrack.e.setAlias(str, str2, jSONObjectH, iCallBack);
                } catch (Exception e3) {
                    if (e3.getMessage() != null) {
                        iCallBack.onMessage(false, "alias:" + str + "添加失败:" + e3.getMessage());
                        MessageSharedPrefs.getInstance(UTrack.this.f).addAlias(str, str2, 1, 1, e3.getMessage());
                        return;
                    }
                    iCallBack.onMessage(false, "alias:" + str + "添加失败");
                    MessageSharedPrefs.getInstance(UTrack.this.f).addAlias(str, str2, 1, 1, "添加失败");
                }
            }
        }).start();
    }

    public void deleteAlias(final String str, final String str2, final ICallBack iCallBack) {
        new Thread(new Runnable() { // from class: com.umeng.message.UTrack.4
            @Override // java.lang.Runnable
            public void run() throws JSONException {
                SuccessState successState = null;
                try {
                    String str3 = MessageSharedPrefs.getInstance(UTrack.this.f).get_deleteALiasInterval();
                    String strA = UTrack.this.a(str, str2, (str3 == null || str3.length() <= 0) ? null : new JSONObject(str3));
                    if (strA != null && strA.length() > 0) {
                        iCallBack.onMessage(false, strA);
                        return;
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
                String str4 = "";
                if (TextUtils.isEmpty(str2)) {
                    UMLog uMLog = UMConfigure.umDebugLog;
                    UMLog.mutlInfo(UTrack.a, 0, "removeAlias: type为空");
                    str4 = "removeAlias: empty type";
                    successState = SuccessState.FAIL_PARAM;
                }
                if (TextUtils.isEmpty(UmengMessageDeviceConfig.getUtdid(UTrack.this.f))) {
                    UMLog uMLog2 = UMConfigure.umDebugLog;
                    UMLog.mutlInfo(UTrack.a, 0, "UTDID为空");
                    str4 = str4 + "UTDID is empty;";
                    successState = SuccessState.FAIL_PARAM;
                }
                if (TextUtils.isEmpty(MessageSharedPrefs.getInstance(UTrack.this.f).getDeviceToken())) {
                    UMLog uMLog3 = UMConfigure.umDebugLog;
                    UMLog.mutlInfo(UTrack.a, 0, "Device token为空");
                    str4 = str4 + "RegistrationId is empty;";
                    successState = SuccessState.FAIL_PARAM;
                }
                try {
                    JSONObject jSONObjectH = UTrack.this.h();
                    if (successState == null) {
                        jSONObjectH.put("alias", str);
                        jSONObjectH.put("type", str2);
                        jSONObjectH.put("ts", System.currentTimeMillis());
                    } else if (successState == SuccessState.FAIL_PARAM) {
                        jSONObjectH.put("fail", str4);
                    } else if (successState == SuccessState.SUCCESS_CACHE) {
                        jSONObjectH.put("success", "");
                    }
                    UTrack.e.deleteAlias(str, str2, jSONObjectH, iCallBack);
                } catch (Exception e3) {
                    if (e3.getMessage() != null) {
                        iCallBack.onMessage(false, "alias:" + str + "移除失败:" + e3.getMessage());
                        return;
                    }
                    iCallBack.onMessage(false, "alias:" + str + "移除失败");
                }
            }
        }).start();
    }

    private boolean f() {
        if (TextUtils.isEmpty(UmengMessageDeviceConfig.getUtdid(this.f))) {
            UMLog uMLog = UMConfigure.umDebugLog;
            UMLog.mutlInfo(a, 0, "UTDID为空");
            return false;
        }
        if (!TextUtils.isEmpty(MessageSharedPrefs.getInstance(this.f).getDeviceToken())) {
            return true;
        }
        UMLog uMLog2 = UMConfigure.umDebugLog;
        UMLog.mutlInfo(a, 0, "Device token为空");
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v5, types: [boolean] */
    /* JADX WARN: Type inference failed for: r1v6, types: [java.io.BufferedReader] */
    /* JADX WARN: Type inference failed for: r1v8 */
    @SuppressLint({"NewApi"})
    public String g() throws Throwable {
        BufferedReader bufferedReader;
        String line;
        try {
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        if (!Environment.getExternalStorageState().equals("mounted")) {
            return null;
        }
        String str = Environment.getExternalStorageDirectory().getPath() + "/data/" + this.f.getPackageName() + "/";
        UMLog uMLog = UMConfigure.umDebugLog;
        UMLog.mutlInfo(a, 2, "path=" + str);
        File file = new File(str, "umeng-message.config");
        ?? Exists = file.exists();
        try {
            if (Exists == 0) {
                return null;
            }
            try {
                bufferedReader = new BufferedReader(new FileReader(file));
            } catch (FileNotFoundException e3) {
                e = e3;
                bufferedReader = null;
            } catch (IOException e4) {
                e = e4;
                bufferedReader = null;
            } catch (Throwable th) {
                th = th;
                Exists = 0;
                if (Exists != 0) {
                    try {
                        Exists.close();
                    } catch (IOException e5) {
                        e5.printStackTrace();
                    }
                }
                throw th;
            }
            do {
                try {
                    line = bufferedReader.readLine();
                } catch (FileNotFoundException e6) {
                    e = e6;
                    e.printStackTrace();
                    if (bufferedReader != null) {
                        try {
                            bufferedReader.close();
                        } catch (IOException e7) {
                            e = e7;
                            e.printStackTrace();
                            return null;
                        }
                    }
                    return null;
                } catch (IOException e8) {
                    e = e8;
                    e.printStackTrace();
                    if (bufferedReader != null) {
                        try {
                            bufferedReader.close();
                        } catch (IOException e9) {
                            e = e9;
                            e.printStackTrace();
                            return null;
                        }
                    }
                    return null;
                }
                if (line == null) {
                    try {
                        bufferedReader.close();
                    } catch (IOException e10) {
                        e = e10;
                        e.printStackTrace();
                        return null;
                    }
                    return null;
                }
            } while (!line.startsWith("sign="));
            String strSubstring = line.substring("sign=".length());
            try {
                bufferedReader.close();
            } catch (IOException e11) {
                e11.printStackTrace();
            }
            return strSubstring;
        } catch (Throwable th2) {
            th = th2;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public JSONObject h() throws JSONException {
        String deviceToken = MessageSharedPrefs.getInstance(this.f).getDeviceToken();
        String utdid = UmengMessageDeviceConfig.getUtdid(this.f);
        JSONObject jSONObject = new JSONObject();
        this.b.put("umid", UmengMessageDeviceConfig.getUmid(this.f));
        jSONObject.put("header", this.b);
        jSONObject.put("utdid", utdid);
        jSONObject.put(MsgConstant.KEY_DEVICE_TOKEN, deviceToken);
        return jSONObject;
    }

    private JSONObject i() throws JSONException {
        String deviceToken = MessageSharedPrefs.getInstance(this.f).getDeviceToken();
        String utdid = UmengMessageDeviceConfig.getUtdid(this.f);
        JSONObject jSONObject = new JSONObject();
        JSONObject jSONObject2 = this.b;
        jSONObject2.put("umid", UmengMessageDeviceConfig.getUmid(this.f));
        jSONObject.put("header", jSONObject2);
        jSONObject.put("utdid", utdid);
        jSONObject.put(MsgConstant.KEY_DEVICE_TOKEN, deviceToken);
        return jSONObject;
    }

    private JSONObject j() throws JSONException {
        String deviceToken = MessageSharedPrefs.getInstance(this.f).getDeviceToken();
        String utdid = UmengMessageDeviceConfig.getUtdid(this.f);
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("appkey", PushAgent.getInstance(this.f).getMessageAppkey());
        jSONObject.put("utdid", utdid);
        jSONObject.put(MsgConstant.KEY_DEVICE_TOKEN, deviceToken);
        return jSONObject;
    }

    public void updateHeader() {
        com.umeng.message.common.b bVar = new com.umeng.message.common.b();
        bVar.b(this.f, new String[0]);
        bVar.a(this.f, PushAgent.getInstance(this.f).getMessageAppkey(), PushAgent.getInstance(this.f).getMessageChannel());
        this.b = new JSONObject();
        try {
            bVar.b(this.b);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        com.umeng.message.common.b bVar2 = new com.umeng.message.common.b();
        bVar2.c(this.f, new String[0]);
        bVar2.a(this.f, PushAgent.getInstance(this.f).getMessageAppkey(), PushAgent.getInstance(this.f).getMessageChannel());
        this.c = new JSONObject();
        try {
            bVar2.c(this.c);
        } catch (Exception e3) {
            e3.printStackTrace();
        }
    }
}
