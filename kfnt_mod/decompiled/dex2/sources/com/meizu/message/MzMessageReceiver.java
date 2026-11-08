package com.meizu.message;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.bytedance.push.i;
import com.meizu.cloud.pushsdk.MzPushMessageReceiver;
import com.meizu.cloud.pushsdk.constants.PushConstants;
import com.meizu.cloud.pushsdk.handler.MzPushMessage;
import com.meizu.cloud.pushsdk.notification.PushNotificationBuilder;
import com.meizu.cloud.pushsdk.platform.message.BasicPushStatus;
import com.meizu.cloud.pushsdk.platform.message.PushSwitchStatus;
import com.meizu.cloud.pushsdk.platform.message.RegisterStatus;
import com.meizu.cloud.pushsdk.platform.message.SubAliasStatus;
import com.meizu.cloud.pushsdk.platform.message.SubTagsStatus;
import com.meizu.cloud.pushsdk.platform.message.UnRegisterStatus;
import com.mz.MzPushAdapter;
import com.ss.android.message.a.a;
import java.util.zip.DataFormatException;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class MzMessageReceiver extends MzPushMessageReceiver {
    private static final String TAG = "MzPushReceiver";

    @Override // com.meizu.cloud.pushsdk.MzPushMessageReceiver, com.meizu.cloud.pushsdk.base.IntentReceiver
    public void onHandleIntent(Context context, Intent intent) {
        try {
            super.onHandleIntent(context, intent);
            printLog("onHandleIntent " + intent.toUri(0));
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    @Override // com.meizu.cloud.pushsdk.MzPushMessageReceiver
    public void onUpdateNotificationBuilder(PushNotificationBuilder pushNotificationBuilder) {
        pushNotificationBuilder.setStatusBarIcon(a.b(com.ss.android.message.a.a(), PushConstants.MZ_PUSH_NOTIFICATION_SMALL_ICON));
    }

    @Override // com.meizu.cloud.pushsdk.MzPushMessageReceiver
    public void onPushStatus(Context context, PushSwitchStatus pushSwitchStatus) {
        if (pushSwitchStatus == null) {
            return;
        }
        printLog("pushStatus " + pushSwitchStatus.toString());
    }

    @Override // com.meizu.cloud.pushsdk.MzPushMessageReceiver
    public void onRegisterStatus(Context context, RegisterStatus registerStatus) {
        if (registerStatus == null) {
            return;
        }
        printLog("registerStatus " + registerStatus.toString());
        if (!BasicPushStatus.SUCCESS_CODE.equals(registerStatus.getCode())) {
            String str = "code = " + registerStatus.getCode() + " message = " + registerStatus.getMessage();
            i.m().a(MzPushAdapter.getMzPush(), registerStatus.getCode(), registerStatus.getMessage());
            i.o().a(MzPushAdapter.getMzPush(), 104, registerStatus.getCode(), registerStatus.getMessage());
            return;
        }
        String pushId = registerStatus.getPushId();
        if (!TextUtils.isEmpty(pushId)) {
            i.m().a(context, MzPushAdapter.getMzPush(), pushId);
        } else {
            i.o().a(MzPushAdapter.getMzPush(), 102, PushConstants.PUSH_TYPE_NOTIFY, "token is empty");
        }
    }

    @Override // com.meizu.cloud.pushsdk.MzPushMessageReceiver
    public void onUnRegisterStatus(Context context, UnRegisterStatus unRegisterStatus) {
        if (unRegisterStatus == null) {
            return;
        }
        printLog("unRegisterStatus " + unRegisterStatus.toString());
    }

    @Override // com.meizu.cloud.pushsdk.MzPushMessageReceiver
    public void onSubTagsStatus(Context context, SubTagsStatus subTagsStatus) {
        if (subTagsStatus == null) {
            return;
        }
        printLog("subTagsStatus " + subTagsStatus.toString());
    }

    @Override // com.meizu.cloud.pushsdk.MzPushMessageReceiver
    public void onSubAliasStatus(Context context, SubAliasStatus subAliasStatus) {
        if (subAliasStatus == null) {
            return;
        }
        printLog("subAliasStatus " + subAliasStatus.toString());
    }

    /* JADX WARN: Removed duplicated region for block: B:27:0x00c4 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:28:0x00c5  */
    @Override // com.meizu.cloud.pushsdk.MzPushMessageReceiver
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void onNotificationClicked(android.content.Context r7, com.meizu.cloud.pushsdk.handler.MzPushMessage r8) {
        /*
            r6 = this;
            com.bytedance.push.d.a r0 = com.bytedance.push.i.l()
            java.lang.String r1 = "MzPushReceiver"
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "onNotificationClicked title "
            r2.append(r3)
            java.lang.String r3 = r8.getTitle()
            r2.append(r3)
            java.lang.String r3 = "content "
            r2.append(r3)
            java.lang.String r3 = r8.getContent()
            r2.append(r3)
            java.lang.String r3 = " selfDefineContentString "
            r2.append(r3)
            java.lang.String r3 = r8.getSelfDefineContentString()
            r2.append(r3)
            java.lang.String r3 = " notifyId "
            r2.append(r3)
            int r3 = r8.getNotifyId()
            r2.append(r3)
            java.lang.String r2 = r2.toString()
            r0.a(r1, r2)
            java.lang.String r0 = r8.getTitle()
            java.lang.String r1 = r8.getContent()
            java.lang.String r8 = r8.getSelfDefineContentString()
            if (r7 == 0) goto Ld5
            boolean r2 = android.text.TextUtils.isEmpty(r8)
            if (r2 == 0) goto L58
            goto Ld5
        L58:
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "onNotificationClicked "
            r2.append(r3)
            r2.append(r0)
            java.lang.String r0 = " "
            r2.append(r0)
            r2.append(r1)
            java.lang.String r0 = " "
            r2.append(r0)
            r2.append(r8)
            java.lang.String r0 = r2.toString()
            printLog(r0)
            r0 = 0
            r1 = 0
            char r2 = r8.charAt(r0)     // Catch: java.lang.Throwable -> Lb7 java.util.zip.DataFormatException -> Lbd
            r3 = 97
            if (r2 == r3) goto L99
            r4 = 99
            if (r2 == r4) goto L99
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> Lb7 java.util.zip.DataFormatException -> Lbd
            r2.<init>()     // Catch: java.lang.Throwable -> Lb7 java.util.zip.DataFormatException -> Lbd
            r2.append(r3)     // Catch: java.lang.Throwable -> Lb7 java.util.zip.DataFormatException -> Lbd
            r2.append(r8)     // Catch: java.lang.Throwable -> Lb7 java.util.zip.DataFormatException -> Lbd
            java.lang.String r8 = r2.toString()     // Catch: java.lang.Throwable -> Lb7 java.util.zip.DataFormatException -> Lbd
        L99:
            com.bytedance.push.c.k r2 = com.bytedance.push.i.m()     // Catch: java.lang.Throwable -> Lb7 java.util.zip.DataFormatException -> Lbd
            byte[] r8 = r8.getBytes()     // Catch: java.lang.Throwable -> Lb7 java.util.zip.DataFormatException -> Lbd
            r3 = 1
            org.json.JSONObject r8 = r2.a(r8, r3)     // Catch: java.lang.Throwable -> Lb7 java.util.zip.DataFormatException -> Lbd
            java.lang.String r2 = "pass_through"
            r8.put(r2, r0)     // Catch: java.lang.Throwable -> Lad java.util.zip.DataFormatException -> Lb2
            r0 = r8
            goto Lc2
        Lad:
            r0 = move-exception
            r5 = r0
            r0 = r8
            r8 = r5
            goto Lb9
        Lb2:
            r0 = move-exception
            r5 = r0
            r0 = r8
            r8 = r5
            goto Lbf
        Lb7:
            r8 = move-exception
            r0 = r1
        Lb9:
            r8.printStackTrace()
            goto Lc2
        Lbd:
            r8 = move-exception
            r0 = r1
        Lbf:
            r8.printStackTrace()
        Lc2:
            if (r0 != 0) goto Lc5
            return
        Lc5:
            com.bytedance.push.c.k r8 = com.bytedance.push.i.m()
            java.lang.String r0 = r0.toString()
            int r2 = com.mz.MzPushAdapter.getMzPush()
            r8.a(r7, r0, r2, r1)
            return
        Ld5:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.meizu.message.MzMessageReceiver.onNotificationClicked(android.content.Context, com.meizu.cloud.pushsdk.handler.MzPushMessage):void");
    }

    @Override // com.meizu.cloud.pushsdk.MzPushMessageReceiver
    public void onNotificationArrived(Context context, MzPushMessage mzPushMessage) {
        i.l().a(TAG, "onNotificationArrived title " + mzPushMessage.getTitle() + "content " + mzPushMessage.getContent() + " selfDefineContentString " + mzPushMessage.getSelfDefineContentString() + " notifyId " + mzPushMessage.getNotifyId());
        String title = mzPushMessage.getTitle();
        String content = mzPushMessage.getContent();
        String selfDefineContentString = mzPushMessage.getSelfDefineContentString();
        if (context == null || TextUtils.isEmpty(selfDefineContentString)) {
            return;
        }
        printLog("onNotificationArrived " + title + " " + content + " " + selfDefineContentString);
        try {
            char cCharAt = selfDefineContentString.charAt(0);
            if (cCharAt != 'a' && cCharAt != 'c') {
                selfDefineContentString = 'a' + selfDefineContentString;
            }
            if (i.m().a(selfDefineContentString.getBytes(), true) == null) {
            }
        } catch (DataFormatException e) {
            e.printStackTrace();
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    @Override // com.meizu.cloud.pushsdk.MzPushMessageReceiver
    public void onNotificationDeleted(Context context, MzPushMessage mzPushMessage) {
        i.l().a(TAG, "onNotificationDeleted title " + mzPushMessage.getTitle() + "content " + mzPushMessage.getContent() + " selfDefineContentString " + mzPushMessage.getSelfDefineContentString() + " notifyId " + mzPushMessage.getNotifyId());
    }

    public static void printLog(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        i.l().a("MzPush", str);
    }
}
