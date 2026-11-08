package com.taobao.agoo;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.sigmob.sdk.common.mta.PointCategory;
import com.ss.android.downloadad.api.download.AdDownloadModel;
import com.taobao.accs.base.TaoBaseService;
import com.taobao.accs.common.ThreadPoolExecutorFactory;
import com.taobao.accs.utl.ALog;
import com.taobao.accs.utl.k;
import com.taobao.agoo.BaseNotifyClickActivity;
import java.util.Iterator;
import org.android.agoo.common.MsgDO;
import org.android.agoo.control.AgooFactory;
import org.android.agoo.control.NotifManager;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public abstract class a {
    private static final String TAG = "accs.BaseNotifyClick";
    private AgooFactory agooFactory;
    private Context context;
    private String msgSource;
    private NotifManager notifyManager;

    public abstract void onMessage(Intent intent);

    public void onCreate(Context context, Intent intent) {
        ALog.i(TAG, "onCreate", new Object[0]);
        this.context = context;
        buildMessage(intent);
    }

    public void onNewIntent(Intent intent) {
        ALog.i(TAG, "onNewIntent", new Object[0]);
        buildMessage(intent);
    }

    private void buildMessage(Intent intent) {
        ThreadPoolExecutorFactory.execute(new b(this, intent));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String parseMsgByThirdPush(Intent intent) {
        String msgFromIntent;
        if (BaseNotifyClickActivity.notifyListeners != null && BaseNotifyClickActivity.notifyListeners.size() > 0) {
            Iterator<BaseNotifyClickActivity.INotifyListener> it = BaseNotifyClickActivity.notifyListeners.iterator();
            msgFromIntent = null;
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                BaseNotifyClickActivity.INotifyListener next = it.next();
                String msgFromIntent2 = next.parseMsgFromIntent(intent);
                if (!TextUtils.isEmpty(msgFromIntent2)) {
                    this.msgSource = next.getMsgSource();
                    msgFromIntent = msgFromIntent2;
                    break;
                }
                msgFromIntent = msgFromIntent2;
            }
        } else {
            ALog.e(TAG, "no impl, try use default impl to parse intent!", new Object[0]);
            BaseNotifyClickActivity.INotifyListener cVar = new c();
            msgFromIntent = cVar.parseMsgFromIntent(intent);
            if (TextUtils.isEmpty(msgFromIntent)) {
                cVar = new g();
                msgFromIntent = cVar.parseMsgFromIntent(intent);
            }
            if (TextUtils.isEmpty(msgFromIntent)) {
                cVar = new e();
                msgFromIntent = cVar.parseMsgFromIntent(intent);
            }
            if (TextUtils.isEmpty(msgFromIntent)) {
                cVar = new f();
                msgFromIntent = cVar.parseMsgFromIntent(intent);
            }
            if (TextUtils.isEmpty(msgFromIntent)) {
                cVar = new d();
                msgFromIntent = cVar.parseMsgFromIntent(intent);
            }
            if (TextUtils.isEmpty(msgFromIntent)) {
                k.a("accs", "error", "parse 3push error", 0.0d);
            } else {
                this.msgSource = cVar.getMsgSource();
                k.a("accs", "error", "parse 3push default " + this.msgSource, 0.0d);
            }
        }
        ALog.i(TAG, "parseMsgByThirdPush", "result", msgFromIntent, "msgSource", this.msgSource);
        return msgFromIntent;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void reportClickNotifyMsg(Intent intent) {
        try {
            String stringExtra = intent.getStringExtra(AdDownloadModel.JsonKey.ID);
            String stringExtra2 = intent.getStringExtra("message_source");
            String stringExtra3 = intent.getStringExtra(PointCategory.REPORT);
            String stringExtra4 = intent.getStringExtra("extData");
            MsgDO msgDO = new MsgDO();
            msgDO.msgIds = stringExtra;
            msgDO.extData = stringExtra4;
            msgDO.messageSource = stringExtra2;
            msgDO.reportStr = stringExtra3;
            msgDO.msgStatus = "8";
            ALog.i(TAG, "reportClickNotifyMsg messageId:" + stringExtra + " source:" + stringExtra2 + " reportStr:" + stringExtra3 + " status:" + msgDO.msgStatus, new Object[0]);
            this.notifyManager.report(msgDO, (TaoBaseService.ExtraInfo) null);
        } catch (Exception e) {
            ALog.e(TAG, "reportClickNotifyMsg exception: " + e, new Object[0]);
        }
    }
}
