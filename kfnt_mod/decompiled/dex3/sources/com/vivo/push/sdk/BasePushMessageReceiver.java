package com.vivo.push.sdk;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.text.TextUtils;
import com.vivo.push.NoPorGuard;
import com.vivo.push.c.o;
import com.vivo.push.d;
import com.vivo.push.e;
import com.vivo.push.f;
import com.vivo.push.g;
import com.vivo.push.model.UnvarnishedMessage;
import com.vivo.push.util.ContextDelegate;
import com.vivo.push.util.n;
import com.vivo.push.util.r;
import java.util.List;

@NoPorGuard
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public abstract class BasePushMessageReceiver extends BroadcastReceiver implements PushMessageCallback {
    public static final String TAG = "PushMessageReceiver";

    @Override // com.vivo.push.sdk.PushMessageCallback
    public void onBind(Context context, int i, String str) {
    }

    @Override // com.vivo.push.sdk.PushMessageCallback
    public void onDelAlias(Context context, int i, List<String> list, List<String> list2, String str) {
    }

    @Override // com.vivo.push.sdk.PushMessageCallback
    public void onDelTags(Context context, int i, List<String> list, List<String> list2, String str) {
    }

    @Override // com.vivo.push.sdk.PushMessageCallback
    @Deprecated
    public void onListTags(Context context, int i, List<String> list, String str) {
    }

    @Override // com.vivo.push.sdk.PushMessageCallback
    public void onLog(Context context, String str, int i, boolean z) {
    }

    @Override // com.vivo.push.sdk.PushMessageCallback
    @Deprecated
    public void onPublish(Context context, int i, String str) {
    }

    @Override // com.vivo.push.sdk.PushMessageCallback
    public void onSetAlias(Context context, int i, List<String> list, List<String> list2, String str) {
    }

    @Override // com.vivo.push.sdk.PushMessageCallback
    public void onSetTags(Context context, int i, List<String> list, List<String> list2, String str) {
    }

    @Override // com.vivo.push.sdk.PushMessageCallback
    public void onTransmissionMessage(Context context, UnvarnishedMessage unvarnishedMessage) {
    }

    @Override // com.vivo.push.sdk.PushMessageCallback
    public void onUnBind(Context context, int i, String str) {
    }

    @Override // android.content.BroadcastReceiver
    public final void onReceive(Context context, Intent intent) {
        Context applicationContext = ContextDelegate.getContext(context).getApplicationContext();
        d.a().a(applicationContext);
        try {
            n.d(TAG, "PushMessageReceiver " + applicationContext.getPackageName() + " ; type = " + intent.getIntExtra("method", -1) + " ; requestId = " + intent.getStringExtra("req_id"));
            try {
                d dVarA = d.a();
                g gVarCreateReceiverCommand = dVarA.k.createReceiverCommand(intent);
                Context context2 = d.a().e;
                if (gVarCreateReceiverCommand == null) {
                    n.a("PushClientManager", "sendCommand, null command!");
                    if (context2 != null) {
                        n.c(context2, "[执行指令失败]指令空！");
                        return;
                    }
                    return;
                }
                o oVarCreateReceiveTask = dVarA.k.createReceiveTask(gVarCreateReceiverCommand);
                if (oVarCreateReceiveTask == null) {
                    n.a("PushClientManager", "sendCommand, null command task! pushCommand = " + gVarCreateReceiverCommand);
                    if (context2 != null) {
                        n.c(context2, "[执行指令失败]指令" + gVarCreateReceiverCommand + "任务空！");
                        return;
                    }
                    return;
                }
                if (context2 != null && !(gVarCreateReceiverCommand instanceof com.vivo.push.b.n)) {
                    n.a(context2, "[接收指令]" + gVarCreateReceiverCommand);
                }
                oVarCreateReceiveTask.a(this);
                f.a((e) oVarCreateReceiveTask);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e2) {
            n.b(TAG, "get method error", e2);
        }
    }

    @Override // com.vivo.push.sdk.PushMessageCallback
    public boolean isAllowNet(Context context) {
        if (context == null) {
            n.a(TAG, "isAllowNet sContext is null");
            return false;
        }
        String packageName = context.getPackageName();
        if (TextUtils.isEmpty(packageName)) {
            n.a(TAG, "isAllowNet pkgName is null");
            return false;
        }
        Intent intent = new Intent("com.vivo.pushservice.action.PUSH_SERVICE");
        intent.setPackage(packageName);
        List<ResolveInfo> listQueryIntentServices = context.getPackageManager().queryIntentServices(intent, 576);
        if (listQueryIntentServices == null || listQueryIntentServices.size() <= 0) {
            n.a(TAG, "this is client sdk");
            return true;
        }
        return r.a(context, packageName);
    }
}
