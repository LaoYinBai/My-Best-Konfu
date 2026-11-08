package com.taobao.agoo;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import com.taobao.accs.base.TaoBaseService;
import com.taobao.accs.utl.ALog;
import org.android.agoo.control.AgooFactory;
import org.android.agoo.control.NotifManager;
import org.android.agoo.message.MessageService;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
class b implements Runnable {
    final /* synthetic */ Intent a;
    final /* synthetic */ a b;

    b(a aVar, Intent intent) {
        this.b = aVar;
        this.a = intent;
    }

    @Override // java.lang.Runnable
    public void run() throws Throwable {
        Intent intent = null;
        try {
            try {
                try {
                    if (this.a != null) {
                        String msgByThirdPush = this.b.parseMsgByThirdPush(this.a);
                        if (!TextUtils.isEmpty(msgByThirdPush) && !TextUtils.isEmpty(this.b.msgSource)) {
                            if (this.b.notifyManager == null) {
                                this.b.notifyManager = new NotifManager();
                            }
                            if (this.b.agooFactory == null) {
                                this.b.agooFactory = new AgooFactory();
                                this.b.agooFactory.init(this.b.context, this.b.notifyManager, (MessageService) null);
                            }
                            Bundle bundleMsgReceiverPreHandler = this.b.agooFactory.msgReceiverPreHandler(msgByThirdPush.getBytes("UTF-8"), this.b.msgSource, (TaoBaseService.ExtraInfo) null, false);
                            String string = bundleMsgReceiverPreHandler.getString("body");
                            ALog.i("accs.BaseNotifyClick", "begin parse EncryptedMsg", new Object[0]);
                            String encryptedMsg = AgooFactory.parseEncryptedMsg(string);
                            if (!TextUtils.isEmpty(encryptedMsg)) {
                                bundleMsgReceiverPreHandler.putString("body", encryptedMsg);
                            } else {
                                ALog.e("accs.BaseNotifyClick", "parse EncryptedMsg fail, empty", new Object[0]);
                            }
                            Intent intent2 = new Intent();
                            try {
                                intent2.putExtras(bundleMsgReceiverPreHandler);
                                this.b.agooFactory.saveMsg(msgByThirdPush.getBytes("UTF-8"), "2");
                                this.b.reportClickNotifyMsg(intent2);
                                intent = intent2;
                            } catch (Throwable th) {
                                th = th;
                                intent = intent2;
                                try {
                                    this.b.onMessage(intent);
                                } catch (Throwable th2) {
                                    ALog.e("accs.BaseNotifyClick", "onMessage", th2, new Object[0]);
                                }
                                throw th;
                            }
                        } else {
                            ALog.e("accs.BaseNotifyClick", "parseMsgFromNotifyListener null!!", "source", this.b.msgSource);
                        }
                    }
                    this.b.onMessage(intent);
                } catch (Throwable th3) {
                    ALog.e("accs.BaseNotifyClick", "onMessage", th3, new Object[0]);
                }
            } catch (Throwable th4) {
                th = th4;
            }
        } catch (Throwable th5) {
            th = th5;
        }
    }
}
