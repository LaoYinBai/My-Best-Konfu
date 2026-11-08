package com.vivo;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import com.bytedance.common.push.utility.a;
import com.bytedance.push.i;
import com.vivo.push.model.UPSNotificationMessage;
import com.vivo.push.sdk.OpenClientPushMessageReceiver;
import com.vv.VvPushAdapter;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class VivoPushMessageReceiver extends OpenClientPushMessageReceiver {
    private final Handler a = new Handler(Looper.getMainLooper());

    @Override // com.vivo.push.sdk.PushMessageCallback
    public void onNotificationMessageClicked(Context context, UPSNotificationMessage uPSNotificationMessage) {
        JSONObject jSONObjectA;
        if (context == null || uPSNotificationMessage == null) {
            return;
        }
        a.b("VivoPushMessageReceiver", "onNotificationMessageClicked: message = " + uPSNotificationMessage.getSkipContent());
        try {
            String skipContent = uPSNotificationMessage.getSkipContent();
            if (TextUtils.isEmpty(skipContent) || (jSONObjectA = i.m().a(skipContent.getBytes(), true)) == null) {
                return;
            }
            jSONObjectA.put("pass_through", 0);
            i.m().a(context, jSONObjectA.toString(), VvPushAdapter.getVvPush(), (String) null);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    @Override // com.vivo.push.sdk.PushMessageCallback
    public void onReceiveRegId(final Context context, final String str) {
        this.a.post(new Runnable() { // from class: com.vivo.VivoPushMessageReceiver.1
            @Override // java.lang.Runnable
            public void run() {
                i.l().a("VivoPushMessageReceiver", "onReceiveRegId token = " + str);
                VvPushAdapter.sendToken(context, str);
            }
        });
    }
}
