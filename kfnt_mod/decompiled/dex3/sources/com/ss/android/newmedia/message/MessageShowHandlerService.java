package com.ss.android.newmedia.message;

import android.app.NotificationManager;
import android.content.Context;
import android.os.Looper;
import android.os.Message;
import com.bytedance.common.push.utility.a;
import com.bytedance.common.push.utility.a.c;
import com.bytedance.push.i;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public abstract class MessageShowHandlerService extends MessageReceiverService {
    public static final String APP_NOTIFY_TAG = "app_notify";
    private static final String TAG = "MessageShowHandlerService";
    protected static c sHandler = new c(Looper.getMainLooper(), new c.a() { // from class: com.ss.android.newmedia.message.MessageShowHandlerService.1
        public void handleMsg(Message message) {
        }
    });
    private NotificationManager mNm;

    public boolean isHandleBySdk() {
        return true;
    }

    public void onHandleBySelf(Context context, int i, String str, int i2, String str2) {
    }

    @Override // com.ss.android.newmedia.message.MessageReceiverService
    public void onHandleMessage(Context context, int i, final String str, final int i2, final String str2) {
        a.b(TAG, "onHandleMessage() called with: type = [" + i + "], obj = [" + str + "], from = [" + i2 + "], extra = [" + str2 + "]");
        if (!isHandleBySdk()) {
            onHandleBySelf(context, i, str, i2, str2);
        } else {
            sHandler.post(new Runnable() { // from class: com.ss.android.newmedia.message.MessageShowHandlerService.2
                @Override // java.lang.Runnable
                public void run() {
                    i.n().a(str, i2, str2);
                }
            });
        }
    }
}
