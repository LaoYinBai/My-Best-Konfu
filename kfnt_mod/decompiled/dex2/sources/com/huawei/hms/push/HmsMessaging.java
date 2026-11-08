package com.huawei.hms.push;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import com.heytap.mcssdk.mode.Message;
import com.huawei.hmf.tasks.f;
import com.huawei.hmf.tasks.g;
import com.huawei.hmf.tasks.i;
import com.huawei.hms.aaid.constant.AaidIdConstant;
import com.huawei.hms.aaid.encrypt.PushEncrypter;
import com.huawei.hms.aaid.init.AutoInitHelper;
import com.huawei.hms.android.HwBuildEx;
import com.huawei.hms.api.Api;
import com.huawei.hms.api.HuaweiApiAvailability;
import com.huawei.hms.common.ApiException;
import com.huawei.hms.common.HuaweiApi;
import com.huawei.hms.common.internal.AbstractClientBuilder;
import com.huawei.hms.common.internal.Preconditions;
import com.huawei.hms.push.constant.a;
import com.huawei.hms.push.utils.b;
import com.huawei.hms.push.utils.d;
import com.huawei.hms.push.utils.e;
import com.huawei.hms.support.api.entity.push.EnableNotifyReq;
import com.huawei.hms.support.api.entity.push.SubscribeReq;
import com.huawei.hms.support.api.entity.push.UpSendMsgReq;
import com.huawei.hms.support.api.push.c.a.a.c;
import com.huawei.hms.support.log.HMSLog;
import com.huawei.hms.utils.JsonUtil;
import com.huawei.hms.utils.NetWorkUtil;
import com.kwad.v8.Platform;
import java.util.regex.Pattern;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class HmsMessaging {
    public static final String DEFAULT_TOKEN_SCOPE = "HCM";
    private static final Pattern a = Pattern.compile("[\\u4e00-\\u9fa5\\w-_.~%]{1,900}");
    private Context b;
    private HuaweiApi<Api.ApiOptions.NoOptions> c;

    private HmsMessaging(Context context) {
        Preconditions.checkNotNull(context);
        this.b = context;
        Api api = new Api(HuaweiApiAvailability.HMS_API_NAME_PUSH);
        if (context instanceof Activity) {
            this.c = new HuaweiApi<>((Activity) context, (Api<Api.ApiOptions>) api, (Api.ApiOptions) null, (AbstractClientBuilder) new d());
        } else {
            this.c = new HuaweiApi<>(context, (Api<Api.ApiOptions>) api, (Api.ApiOptions) null, new d());
        }
        this.c.setKitSdkVersion(40004301);
    }

    public static synchronized HmsMessaging getInstance(Context context) {
        return new HmsMessaging(context);
    }

    public boolean isAutoInitEnabled() {
        return AutoInitHelper.isAutoInitEnabled(this.b);
    }

    public void setAutoInitEnabled(boolean z) {
        AutoInitHelper.setAutoInitEnabled(this.b, z);
    }

    public f<Void> subscribe(String str) {
        String strA = b.a(this.b, "push.subscribe");
        HMSLog.i("HmsMessaging", "invoke subscribe");
        return a(str, "Sub", strA);
    }

    public f<Void> unsubscribe(String str) {
        String strA = b.a(this.b, "push.subscribe");
        HMSLog.i("HmsMessaging", "invoke unsubscribe");
        return a(str, "UnSub", strA);
    }

    private f<Void> a(String str, String str2, String str3) throws ApiException {
        if (str == null || !a.matcher(str).matches()) {
            b.a(this.b, "push.subscribe", str3, a.ERROR_ARGUMENTS_INVALID);
            HMSLog.e("HmsMessaging", "Invalid topic: topic should match the format:[\\u4e00-\\u9fa5\\w-_.~%]{1,900}");
            throw new IllegalArgumentException("Invalid topic: topic should match the format:[\\u4e00-\\u9fa5\\w-_.~%]{1,900}");
        }
        try {
            HMSLog.i("HmsMessaging", "EMUI:" + HwBuildEx.VERSION.EMUI_SDK_INT);
            a aVarA = e.a(this.b);
            if (aVarA != a.SUCCESS) {
                throw a.a(aVarA);
            }
            if (NetWorkUtil.getNetworkType(this.b) == 0) {
                HMSLog.e("HmsMessaging", "no network");
                throw a.a(a.ERROR_NO_NETWORK);
            }
            SubscribeReq subscribeReq = new SubscribeReq(this.b.getPackageName(), str2, str);
            subscribeReq.setToken(com.huawei.hms.support.api.push.c.a.b.a(this.b, AaidIdConstant.PushClientSelfInfo.FILE_NAME));
            if (com.huawei.hms.support.api.push.c.a.a()) {
                return this.c.doWrite(new com.huawei.hms.push.a.a("push.subscribe", JsonUtil.createJsonString(subscribeReq), str3));
            }
            return this.c.doWrite(new com.huawei.hms.push.a.d("push.subscribe", JsonUtil.createJsonString(subscribeReq), str3));
        } catch (ApiException e) {
            g gVar = new g();
            gVar.a((Exception) e);
            b.a(this.b, "push.subscribe", str3, e.getStatusCode());
            return gVar.a();
        } catch (Exception unused) {
            g gVar2 = new g();
            gVar2.a((Exception) a.a(a.ERROR_INTERNAL_ERROR));
            b.a(this.b, "push.subscribe", str3, a.ERROR_INTERNAL_ERROR);
            return gVar2.a();
        }
    }

    public void send(RemoteMessage remoteMessage) {
        String strA = b.a(this.b, "push.sendMessage");
        HMSLog.i("HmsMessaging", "send upstream message");
        a(remoteMessage, strA);
    }

    private void a(RemoteMessage remoteMessage, String str) {
        a aVarA = e.a(this.b);
        if (aVarA != a.SUCCESS) {
            HMSLog.e("HmsMessaging", "Message sent failed:" + aVarA.b() + ':' + aVarA.c());
            b.a(this.b, "push.sendMessage", str, aVarA);
            throw new UnsupportedOperationException(aVarA.c());
        }
        if (TextUtils.isEmpty(remoteMessage.getTo())) {
            HMSLog.e("HmsMessaging", "Mandatory parameter 'to' missing");
            b.a(this.b, "push.sendMessage", str, a.ERROR_ARGUMENTS_INVALID);
            throw new IllegalArgumentException("Mandatory parameter 'to' missing");
        }
        if (TextUtils.isEmpty(remoteMessage.getMessageId())) {
            HMSLog.e("HmsMessaging", "Mandatory parameter 'message_id' missing");
            b.a(this.b, "push.sendMessage", str, a.ERROR_ARGUMENTS_INVALID);
            throw new IllegalArgumentException("Mandatory parameter 'message_id' missing");
        }
        if (TextUtils.isEmpty(remoteMessage.getData())) {
            HMSLog.e("HmsMessaging", "Mandatory parameter 'data' missing");
            b.a(this.b, "push.sendMessage", str, a.ERROR_ARGUMENTS_INVALID);
            throw new IllegalArgumentException("Mandatory parameter 'data' missing");
        }
        UpSendMsgReq upSendMsgReq = new UpSendMsgReq();
        upSendMsgReq.setPackageName(this.b.getPackageName());
        upSendMsgReq.setMessageId(remoteMessage.getMessageId());
        upSendMsgReq.setTo(remoteMessage.getTo());
        upSendMsgReq.setData(remoteMessage.getData());
        upSendMsgReq.setMessageType(remoteMessage.getMessageType());
        upSendMsgReq.setTtl(remoteMessage.getTtl());
        upSendMsgReq.setCollapseKey(remoteMessage.getCollapseKey());
        upSendMsgReq.setSendMode(remoteMessage.getSendMode());
        upSendMsgReq.setReceiptMode(remoteMessage.getReceiptMode());
        if (com.huawei.hms.support.api.push.c.a.a()) {
            this.c.doWrite(new com.huawei.hms.push.a.a("push.sendMessage", JsonUtil.createJsonString(upSendMsgReq), str));
        } else {
            a(upSendMsgReq, str);
        }
    }

    public f<Void> turnOnPush() {
        String strA = b.a(this.b, "push.setNotifyFlag");
        HMSLog.i("HmsMessaging", "invoke turnOnPush");
        return a(true, strA);
    }

    public f<Void> turnOffPush() {
        String strA = b.a(this.b, "push.setNotifyFlag");
        HMSLog.i("HmsMessaging", "invoke turnOffPush");
        return a(false, strA);
    }

    private f<Void> a(boolean z, String str) {
        if (!com.huawei.hms.support.api.push.c.a.a(this.b) || com.huawei.hms.support.api.push.c.a.a()) {
            HMSLog.i("HmsMessaging", "turn on/off with AIDL");
            EnableNotifyReq enableNotifyReq = new EnableNotifyReq();
            enableNotifyReq.setPackageName(this.b.getPackageName());
            enableNotifyReq.setEnable(z);
            return this.c.doWrite(new com.huawei.hms.push.a.a("push.setNotifyFlag", JsonUtil.createJsonString(enableNotifyReq), str));
        }
        if (HwBuildEx.VERSION.EMUI_SDK_INT < 12) {
            HMSLog.e("HmsMessaging", "operation not available on Huawei device with EMUI lower than 5.1");
            g gVar = new g();
            gVar.a((Exception) a.a(a.ERROR_OPERATION_NOT_SUPPORTED));
            b.a(this.b, "push.setNotifyFlag", str, a.ERROR_OPERATION_NOT_SUPPORTED);
            return gVar.a();
        }
        if (com.huawei.hms.support.api.push.c.a.b(this.b) < 90101310) {
            HMSLog.i("HmsMessaging", "turn on/off with broadcast v1");
            Intent intentPutExtra = new Intent("com.huawei.intent.action.SELF_SHOW_FLAG").putExtra("enalbeFlag", PushEncrypter.encrypterOld(this.b, this.b.getPackageName() + "#" + z));
            intentPutExtra.setPackage(Platform.ANDROID);
            return i.a(new com.huawei.hms.push.a.b(this.b, intentPutExtra, str));
        }
        HMSLog.i("HmsMessaging", "turn on/off with broadcast v2");
        new c(this.b, "push_notify_flag").a("notify_msg_enable", !z);
        Uri uri = Uri.parse("content://" + this.b.getPackageName() + ".huawei.push.provider/push_notify_flag.xml");
        Intent intent = new Intent("com.huawei.android.push.intent.SDK_COMMAND");
        intent.putExtra(Message.TYPE, "enalbeFlag");
        intent.putExtra("pkgName", this.b.getPackageName());
        intent.putExtra("url", uri);
        intent.setPackage(Platform.ANDROID);
        return i.a(new com.huawei.hms.push.a.b(this.b, intent, str));
    }

    private void a(UpSendMsgReq upSendMsgReq, String str) {
        upSendMsgReq.setToken(com.huawei.hms.support.api.push.c.a.b.a(this.b, AaidIdConstant.PushClientSelfInfo.FILE_NAME));
        try {
            this.c.doWrite(new com.huawei.hms.push.a.c("push.sendMessage", JsonUtil.createJsonString(upSendMsgReq), str, upSendMsgReq.getPackageName(), upSendMsgReq.getMessageId()));
        } catch (Exception e) {
            if (e.getCause() instanceof ApiException) {
                b.a(this.b, "push.sendMessage", str, ((ApiException) e.getCause()).getStatusCode());
            } else {
                b.a(this.b, "push.sendMessage", str, a.ERROR_INTERNAL_ERROR);
            }
        }
    }
}
