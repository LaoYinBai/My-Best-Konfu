package org.android.agoo.message;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import com.taobao.accs.utl.ALog;
import org.android.agoo.common.AgooConstants;
import org.android.agoo.control.BaseIntentService;
import org.android.agoo.service.SendMessage;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes4.dex */
public abstract class MessageReceiverService extends Service {
    SendMessage.Stub a = new SendMessage.Stub() { // from class: org.android.agoo.message.MessageReceiverService.1
        @Override // org.android.agoo.service.SendMessage
        public int doSend(Intent intent) throws RemoteException {
            BaseIntentService.runIntentInService(MessageReceiverService.this.getApplicationContext(), intent, MessageReceiverService.this.getIntentServiceClassName(MessageReceiverService.this.getApplicationContext()));
            return 0;
        }
    };

    public abstract String getIntentServiceClassName(Context context);

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        ALog.d("MessageReceiverService", "Message receiver aidl was binded {}", new Object[]{intent.getAction()});
        if (AgooConstants.BINDER_MSGRECEIVER_ACTION.equals(intent.getAction())) {
            return this.a;
        }
        return this.a;
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
    }
}
