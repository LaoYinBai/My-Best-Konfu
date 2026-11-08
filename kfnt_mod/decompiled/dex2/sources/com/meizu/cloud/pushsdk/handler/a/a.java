package com.meizu.cloud.pushsdk.handler.a;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.SparseArray;
import com.mbridge.msdk.playercommon.exoplayer2.C;
import com.meizu.cloud.pushinternal.DebugLogger;
import com.meizu.cloud.pushsdk.constants.PushConstants;
import com.meizu.cloud.pushsdk.handler.MessageV3;
import com.meizu.cloud.pushsdk.handler.MzPushMessage;
import com.meizu.cloud.pushsdk.handler.a.b.e;
import com.meizu.cloud.pushsdk.util.MinSdkChecker;
import com.meizu.cloud.pushsdk.util.MzSystemUtils;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public abstract class a<T> implements com.meizu.cloud.pushsdk.handler.c {
    private com.meizu.cloud.pushsdk.handler.a a;
    private Context b;
    private SparseArray<String> c;

    protected a(Context context, com.meizu.cloud.pushsdk.handler.a aVar) {
        if (context == null) {
            throw new IllegalArgumentException("Context must not be null.");
        }
        this.b = context.getApplicationContext();
        this.a = aVar;
        this.c = new SparseArray<>();
        this.c.put(2, "MESSAGE_TYPE_PUSH_SERVICE_V2");
        this.c.put(4, "MESSAGE_TYPE_PUSH_SERVICE_V3");
        this.c.put(16, "MESSAGE_TYPE_REGISTER");
        this.c.put(32, "MESSAGE_TYPE_UNREGISTER");
        this.c.put(8, "MESSAGE_TYPE_THROUGH");
        this.c.put(64, "MESSAGE_TYPE_NOTIFICATION_CLICK");
        this.c.put(128, "MESSAGE_TYPE_NOTIFICATION_DELETE");
        this.c.put(256, "MESSAGE_TYPE_PUSH_SWITCH_STATUS");
        this.c.put(512, "MESSAGE_TYPE_PUSH_REGISTER_STATUS");
        this.c.put(2048, "MESSAGE_TYPE_PUSH_SUBTAGS_STATUS");
        this.c.put(1024, "MESSAGE_TYPE_PUSH_UNREGISTER_STATUS");
        this.c.put(4096, "MESSAGE_TYPE_PUSH_SUBALIAS_STATUS");
        this.c.put(8192, "MESSAGE_TYPE_SCHEDULE_NOTIFICATION");
        this.c.put(16384, "MESSAGE_TYPE_RECEIVE_NOTIFY_MESSAGE");
        this.c.put(32768, "MESSAGE_TYPE_NOTIFICATION_STATE");
        this.c.put(C.DEFAULT_BUFFER_SEGMENT_SIZE, "MESSAGE_TYPE_UPLOAD_FILE_LOG");
        this.c.put(131072, "MESSAGE_TYPE_NOTIFICATION_ARRIVED");
        this.c.put(262144, "MESSAGE_TYPE_NOTIFICATION_WITHDRAW");
        this.c.put(524288, "MESSAGE_TYPE_BRIGHT_NOTIFICATION");
    }

    private String a(int i) {
        return this.c.get(i);
    }

    private boolean a(String str, MessageV3 messageV3, String str2) {
        String str3;
        String str4;
        if (!TextUtils.isEmpty(str)) {
            str3 = "AbstractMessageHandler";
            str4 = "special approval, public key not empty";
        } else if (!messageV3.getWhiteList()) {
            str3 = "AbstractMessageHandler";
            str4 = "special approval, message not white list";
        } else if (!PushConstants.MZ_PUSH_MESSAGE_METHOD_ACTION_PRIVATE.equals(str2)) {
            str3 = "AbstractMessageHandler";
            str4 = "special approval, message not click method";
        } else {
            if (com.meizu.cloud.pushsdk.util.b.l(d(), messageV3.getPackageName())) {
                com.meizu.cloud.pushsdk.util.b.c(d(), messageV3.getPackageName(), false);
                return true;
            }
            str3 = "AbstractMessageHandler";
            str4 = "special approval, not first request";
        }
        DebugLogger.e(str3, str4);
        return false;
    }

    private boolean b(String str, MessageV3 messageV3, String str2) {
        if (TextUtils.isEmpty(str)) {
            DebugLogger.e("AbstractMessageHandler", "security check fail, public key is null");
            return false;
        }
        String strA = com.meizu.cloud.pushsdk.util.c.a(str, str2);
        DebugLogger.e("AbstractMessageHandler", "decrypt sign: " + strA);
        boolean zA = com.meizu.cloud.pushsdk.handler.a.b.e.a(strA, messageV3);
        DebugLogger.i("AbstractMessageHandler", "check public key result: " + zA);
        return zA;
    }

    private String e() {
        String strB = null;
        for (int i = 0; i < 2; i++) {
            strB = b();
            if (!TextUtils.isEmpty(strB)) {
                break;
            }
        }
        return strB;
    }

    protected com.meizu.cloud.pushsdk.notification.c a(T t) {
        return null;
    }

    protected void a(MessageV3 messageV3) {
        com.meizu.cloud.pushsdk.notification.model.a aVarA = com.meizu.cloud.pushsdk.notification.model.a.a(messageV3);
        if (aVarA != null) {
            DebugLogger.e("AbstractMessageHandler", "delete notifyKey " + aVarA.b() + " notifyId " + aVarA.a());
            if (TextUtils.isEmpty(aVarA.b())) {
                com.meizu.cloud.pushsdk.notification.c.b.c(d(), messageV3.getUploadDataPackageName(), aVarA.a());
            } else {
                com.meizu.cloud.pushsdk.notification.c.b.a(d(), messageV3.getUploadDataPackageName(), aVarA.b());
            }
        }
    }

    protected abstract void a(T t, com.meizu.cloud.pushsdk.notification.c cVar);

    protected boolean a(int i, String str) {
        boolean zH = true;
        if (i == 0) {
            zH = com.meizu.cloud.pushsdk.util.b.e(d(), str);
        } else if (i == 1) {
            zH = com.meizu.cloud.pushsdk.util.b.h(d(), str);
        }
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append(i == 0 ? " canNotificationMessage " : " canThroughMessage ");
        sb.append(zH);
        DebugLogger.e("AbstractMessageHandler", sb.toString());
        return zH;
    }

    protected final boolean a(MessageV3 messageV3, String str) throws JSONException {
        String str2;
        String str3;
        String strA = com.meizu.cloud.pushsdk.handler.a.b.e.a(messageV3);
        if (TextUtils.isEmpty(strA)) {
            DebugLogger.i("AbstractMessageHandler", "message does not contain signature field");
            return false;
        }
        String strK = com.meizu.cloud.pushsdk.util.b.k(d(), messageV3.getPackageName());
        DebugLogger.i("AbstractMessageHandler", "local public key is: " + strK);
        if (a(strK, messageV3, str)) {
            str2 = "AbstractMessageHandler";
            str3 = "message special approval no check";
        } else {
            if (!b(strK, messageV3, strA)) {
                String strE = e();
                DebugLogger.i("AbstractMessageHandler", "network request public key: " + strE);
                if (!b(strE, messageV3, strA)) {
                    DebugLogger.e("AbstractMessageHandler", "security check fail");
                    return false;
                }
                com.meizu.cloud.pushsdk.util.b.j(d(), messageV3.getPackageName(), strE);
            }
            str2 = "AbstractMessageHandler";
            str3 = "security check passed";
        }
        DebugLogger.i(str2, str3);
        return true;
    }

    protected boolean a(T t, String str) {
        return true;
    }

    protected boolean a(String str) {
        try {
            return d().getPackageName().equals(new JSONObject(str).getString("appId"));
        } catch (Exception unused) {
            DebugLogger.e("AbstractMessageHandler", "parse notification error");
            return false;
        }
    }

    protected String b() {
        return new e.a((String) com.meizu.cloud.pushsdk.b.a.a(PushConstants.GET_PUBLIC_KEY).a().a().a()).a();
    }

    public String b(String str) throws JSONException {
        try {
            JSONObject jSONObject = new JSONObject(str).getJSONObject("launcher");
            return (!jSONObject.has("pkg") || TextUtils.isEmpty(jSONObject.getString("pkg"))) ? "" : jSONObject.getString("pkg");
        } catch (Exception unused) {
            DebugLogger.e("AbstractMessageHandler", "parse desk top json error");
            return "";
        }
    }

    protected void b(MessageV3 messageV3) {
        if (!MinSdkChecker.isSupportSetDrawableSmallIcon()) {
            c().b(d(), MzPushMessage.fromMessageV3(messageV3));
            return;
        }
        if (MzSystemUtils.isRunningProcess(d(), messageV3.getUploadDataPackageName())) {
            DebugLogger.i("AbstractMessageHandler", "send notification arrived message to " + messageV3.getUploadDataPackageName());
            Intent intent = new Intent();
            intent.putExtra(PushConstants.MZ_PUSH_PRIVATE_MESSAGE, messageV3);
            intent.putExtra("method", PushConstants.MZ_PUSH_MESSAGE_METHOD_ACTION_NOTIFICATION_ARRIVED);
            MzSystemUtils.sendMessageFromBroadcast(d(), intent, PushConstants.MZ_PUSH_ON_MESSAGE_ACTION, messageV3.getUploadDataPackageName());
        }
    }

    protected void b(T t) {
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @Override // com.meizu.cloud.pushsdk.handler.c
    public boolean b(Intent intent) {
        String str;
        String str2;
        boolean z = false;
        if (a(intent)) {
            DebugLogger.e("AbstractMessageHandler", "current message Type " + a(a()));
            T tC = c(intent);
            if (!a((a<T>) tC, k(intent))) {
                DebugLogger.e("AbstractMessageHandler", "invalid push message");
                return false;
            }
            DebugLogger.e("AbstractMessageHandler", "current Handler message " + tC);
            b((a<T>) tC);
            boolean z2 = true;
            switch (d((a<T>) tC)) {
                case 0:
                    str = "AbstractMessageHandler";
                    str2 = "schedule send message off, send message directly";
                    DebugLogger.e(str, str2);
                    z = true;
                    break;
                case 1:
                    DebugLogger.e("AbstractMessageHandler", "expire notification, don't show message");
                    z2 = false;
                    break;
                case 2:
                    str = "AbstractMessageHandler";
                    str2 = "notification on time ,show message";
                    DebugLogger.e(str, str2);
                    z = true;
                    break;
                case 3:
                    DebugLogger.e("AbstractMessageHandler", "schedule notification");
                    e((a<T>) tC);
                    z = true;
                    z2 = false;
                    break;
                case 4:
                    DebugLogger.e("AbstractMessageHandler", "bright notification");
                    f((a<T>) tC);
                    z = true;
                    z2 = false;
                    break;
                default:
                    z2 = false;
                    break;
            }
            boolean zG = g((a<T>) tC);
            DebugLogger.e("AbstractMessageHandler", "can send message " + zG);
            if (z && z2 && zG) {
                a((a<T>) tC, a((a<T>) tC));
                c((a<T>) tC);
                DebugLogger.e("AbstractMessageHandler", "send message end ");
            }
        }
        return z;
    }

    protected com.meizu.cloud.pushsdk.handler.a c() {
        return this.a;
    }

    protected abstract T c(Intent intent);

    protected void c(T t) {
    }

    protected int d(T t) {
        return 0;
    }

    protected Context d() {
        return this.b;
    }

    protected String d(Intent intent) {
        String stringExtra = intent != null ? intent.getStringExtra(PushConstants.MZ_PUSH_MESSAGE_STATISTICS_IMEI_KEY) : null;
        if (!TextUtils.isEmpty(stringExtra)) {
            return stringExtra;
        }
        String strA = com.meizu.cloud.pushsdk.base.c.a(d());
        DebugLogger.e("AbstractMessageHandler", "force get deviceId " + strA);
        return strA;
    }

    protected String e(Intent intent) {
        return intent.getStringExtra(PushConstants.EXTRA_APP_PUSH_TASK_ID);
    }

    protected void e(T t) {
    }

    protected String f(Intent intent) {
        return intent.getStringExtra(PushConstants.EXTRA_APP_PUSH_SEQ_ID);
    }

    protected void f(T t) {
    }

    protected String g(Intent intent) {
        String stringExtra = intent.getStringExtra(PushConstants.EXTRA_APP_PUSH_SERVICE_DEFAULT_PACKAGE_NAME);
        return TextUtils.isEmpty(stringExtra) ? d().getPackageName() : stringExtra;
    }

    protected boolean g(T t) {
        return true;
    }

    protected String h(Intent intent) {
        String stringExtra = intent.getStringExtra(PushConstants.EXTRA_APP_PUSH_TASK_TIMES_TAMP);
        DebugLogger.e("AbstractMessageHandler", "receive push timestamp from pushservice " + stringExtra);
        return TextUtils.isEmpty(stringExtra) ? String.valueOf(System.currentTimeMillis() / 1000) : stringExtra;
    }

    protected boolean i(Intent intent) {
        boolean booleanExtra = intent.getBooleanExtra(PushConstants.MZ_PUSH_WHITE_LIST, false);
        DebugLogger.e("AbstractMessageHandler", "receive push whiteList from pushservice " + booleanExtra);
        return booleanExtra;
    }

    protected long j(Intent intent) {
        long longExtra = intent.getLongExtra(PushConstants.MZ_PUSH_DELAYED_REPORT_MILLIS, 0L);
        DebugLogger.e("AbstractMessageHandler", "receive push delayedReportMillis from pushservice " + longExtra);
        return longExtra;
    }

    protected String k(Intent intent) {
        return intent.getStringExtra("method");
    }
}
