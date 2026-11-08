package com.coloros;

import android.content.Context;
import android.text.TextUtils;
import android.util.Pair;
import com.bytedance.push.i;
import com.bytedance.push.third.IPushAdapter;
import com.bytedance.push.third.e;
import com.bytedance.sdk.account.monitor.AccountMonitorConstants;
import com.bytedance.sdk.account.platform.base.OnekeyLoginConstants;
import com.heytap.mcssdk.PushManager;
import com.heytap.mcssdk.callback.PushCallback;
import com.heytap.mcssdk.mode.SubscribeResult;
import com.ss.android.message.a.b;
import com.ss.android.message.d;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class OpPushAdapter implements IPushAdapter, PushCallback {
    private static int OP_PUSH = -1;
    private static final String TAG = "OpPush";
    private Context mContext;
    private String mRegisterId;

    public void onGetAliases(int i, List<SubscribeResult> list) {
    }

    public void onGetNotificationStatus(int i, int i2) {
    }

    public void onGetPushStatus(int i, int i2) {
    }

    public void onGetTags(int i, List<SubscribeResult> list) {
    }

    public void onGetUserAccounts(int i, List<SubscribeResult> list) {
    }

    public void onSetAliases(int i, List<SubscribeResult> list) {
    }

    public void onSetPushTime(int i, String str) {
    }

    public void onSetTags(int i, List<SubscribeResult> list) {
    }

    public void onSetUserAccounts(int i, List<SubscribeResult> list) {
    }

    public void onUnRegister(int i) {
    }

    public void onUnsetAliases(int i, List<SubscribeResult> list) {
    }

    public void onUnsetTags(int i, List<SubscribeResult> list) {
    }

    public void onUnsetUserAccounts(int i, List<SubscribeResult> list) {
    }

    @Override // com.bytedance.push.third.IPushAdapter
    public void trackPush(Context context, int i, Object obj) {
    }

    public static int getOpPush() {
        if (OP_PUSH == -1) {
            OP_PUSH = e.a(com.ss.android.message.a.a()).a(OpPushAdapter.class.getName());
        }
        return OP_PUSH;
    }

    @Override // com.bytedance.push.third.IPushAdapter
    public boolean isPushAvailable(Context context, int i) {
        return PushManager.isSupportPush(context);
    }

    @Override // com.bytedance.push.third.IPushAdapter
    public void registerPush(Context context, int i) {
        String str;
        if (context == null || i != getOpPush() || !PushManager.isSupportPush(context)) {
            if (context == null) {
                str = "空 context";
            } else {
                str = i != getOpPush() ? "通道注册错误" : "该手机不支持OP Push";
            }
            i.o().a(i, 101, OnekeyLoginConstants.CU_RESULT_SUCCESS, str);
            return;
        }
        this.mContext = context.getApplicationContext();
        i.l().c(TAG, "registerOpPush");
        Pair<String, String> pairA = i.m().a(getOpPush());
        if (pairA == null) {
            i.o().a(i, 106, OnekeyLoginConstants.CU_RESULT_SUCCESS, "配置为空");
            return;
        }
        try {
            PushManager.getInstance().register(context, (String) pairA.first, (String) pairA.second, this);
        } catch (Throwable th) {
            i.l().a(TAG, "op register push get exception=" + th.getMessage());
        }
        PushManager.getInstance().resumePush();
    }

    @Override // com.bytedance.push.third.IPushAdapter
    public void setAlias(Context context, String str, int i) {
        if (context == null || i != getOpPush() || TextUtils.isEmpty(str) || !PushManager.isSupportPush(context)) {
            return;
        }
        i.l().c(TAG, "set alias:" + str);
        try {
            ArrayList arrayList = new ArrayList();
            arrayList.add(str);
            PushManager.getInstance().setAliases(arrayList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.bytedance.push.third.IPushAdapter
    public void unregisterPush(Context context, int i) {
        if (context != null && i == getOpPush() && PushManager.isSupportPush(context)) {
            i.l().c(TAG, "unregisterOpPush");
            try {
                PushManager.getInstance().pausePush();
                PushManager.getInstance().unRegister();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void onRegister(int i, String str) {
        if (i == 0 && !TextUtils.isEmpty(str)) {
            i.l().c(TAG, "register onSuccess registerId = " + str);
            this.mRegisterId = str;
            i.m().a(this.mContext, getOpPush(), str);
            return;
        }
        if (i != 0) {
            i.l().b(TAG, "register onFailure resultCode " + i + " registerId = " + str);
            StringBuilder sb = new StringBuilder();
            sb.append(i);
            sb.append(" ");
            sb.append(str);
            String string = sb.toString();
            i.m().a(getOpPush(), String.valueOf(i), string);
            i.o().a(getOpPush(), 104, String.valueOf(i), string);
            return;
        }
        i.o().a(getOpPush(), 102, OnekeyLoginConstants.CU_RESULT_SUCCESS, "token is empty");
    }

    @Override // com.bytedance.push.third.IPushAdapter
    public boolean checkThirdPushConfig(String str, Context context) throws Exception {
        return a.a(str, context);
    }

    @Override // com.bytedance.push.third.IPushAdapter
    public boolean requestNotificationPermission(int i) {
        try {
            i.k().a("push_guide_request_oppo", null);
        } catch (Throwable unused) {
        }
        if (i != getOpPush()) {
            i.l().a(TAG, "OpPushAdapter.requestNotificationPermission error, push_type is not " + getOpPush());
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put(AccountMonitorConstants.CommonParameter.SENDCODE_RESULT, OnekeyLoginConstants.CU_RESULT_SUCCESS);
                jSONObject.put("error_msg", "push_type is not for oppo");
                i.k().a("push_guide_show_oppo", jSONObject);
            } catch (Throwable unused2) {
            }
            return false;
        }
        if (TextUtils.isEmpty(this.mRegisterId)) {
            i.l().a(TAG, "OpPushAdapter.requestNotificationPermission op register failed or not registered");
            JSONObject jSONObject2 = new JSONObject();
            try {
                jSONObject2.put(AccountMonitorConstants.CommonParameter.SENDCODE_RESULT, OnekeyLoginConstants.CU_RESULT_SUCCESS);
                jSONObject2.put("error_msg", "oppo register failed or not registered");
                i.k().a("push_guide_show_oppo", jSONObject2);
            } catch (Throwable unused3) {
            }
            return false;
        }
        if (1 != b.d(this.mContext)) {
            PushManager.getInstance().requestNotificationPermission();
            i.l().a(TAG, "OpPushAdapter.requestNotificationPermission no permission, request");
            JSONObject jSONObject3 = new JSONObject();
            try {
                jSONObject3.put(AccountMonitorConstants.CommonParameter.SENDCODE_RESULT, "1");
                jSONObject3.put("error_msg", "success");
                i.k().a("push_guide_show_oppo", jSONObject3);
            } catch (Throwable unused4) {
            }
            d.a().a(new Runnable() { // from class: com.coloros.OpPushAdapter.1
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        JSONObject jSONObject4 = new JSONObject();
                        jSONObject4.put("notification_status", 1 == b.d(OpPushAdapter.this.mContext) ? "1" : OnekeyLoginConstants.CU_RESULT_SUCCESS);
                        i.k().a("push_guide_status_change_oppo", jSONObject4);
                    } catch (Throwable unused5) {
                    }
                }
            }, TimeUnit.SECONDS.toMillis(15L));
        } else {
            i.l().a(TAG, "OpPushAdapter.requestNotificationPermission already has permission");
            JSONObject jSONObject4 = new JSONObject();
            try {
                jSONObject4.put(AccountMonitorConstants.CommonParameter.SENDCODE_RESULT, OnekeyLoginConstants.CU_RESULT_SUCCESS);
                jSONObject4.put("error_msg", "already has permission");
                i.k().a("push_guide_show_oppo", jSONObject4);
            } catch (Throwable unused5) {
            }
        }
        return true;
    }
}
