package com.vivo.push.sdk;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ProviderInfo;
import android.content.pm.ResolveInfo;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import com.taobao.accs.common.Constants;
import com.vivo.push.i;
import com.vivo.push.util.ContextDelegate;
import com.vivo.push.util.n;
import com.vivo.push.util.r;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public final class a extends i {
    private static a d;
    private static final List<Integer> f = Arrays.asList(3);
    public String c;
    private Handler e = new Handler(Looper.getMainLooper());

    private a() {
    }

    public static synchronized a a() {
        if (d == null) {
            d = new a();
        }
        return d;
    }

    public final void a(Intent intent) {
        if (intent == null || this.a == null) {
            n.d("CommandWorker", " sendMessage error: intent : " + intent + ", mContext: " + this.a);
            return;
        }
        Message messageObtain = Message.obtain();
        messageObtain.obj = intent;
        a(messageObtain);
    }

    @Override // com.vivo.push.i
    public final void b(Message message) throws IllegalAccessException, NoSuchMethodException, InstantiationException, ClassNotFoundException, SecurityException, IllegalArgumentException, InvocationTargetException {
        String stringExtra;
        boolean zBooleanValue;
        ProviderInfo providerInfoResolveContentProvider;
        Intent intent = (Intent) message.obj;
        if (intent == null || this.a == null) {
            n.d("CommandWorker", " handleMessage error: intent : " + intent + ", mContext: " + this.a);
            return;
        }
        String packageName = this.a.getPackageName();
        try {
            stringExtra = intent.getStringExtra("command_type");
        } catch (Exception e) {
            n.a("CommandWorker", e);
        }
        if (!TextUtils.isEmpty(stringExtra) && stringExtra.equals("reflect_receiver")) {
            int intExtra = intent.getIntExtra(Constants.KEY_COMMAND, -1);
            if (intExtra < 0) {
                intExtra = intent.getIntExtra("method", -1);
            }
            if (f.contains(Integer.valueOf(intExtra)) && r.a(this.a, packageName, "com.vivo.pushclient.action.RECEIVE")) {
                Context context = this.a;
                if (r.a != null) {
                    zBooleanValue = r.a.booleanValue();
                } else {
                    String str = null;
                    if (context != null && !TextUtils.isEmpty("com.vivo.push.sdk.service.SystemPushConfig") && (providerInfoResolveContentProvider = context.getPackageManager().resolveContentProvider("com.vivo.push.sdk.service.SystemPushConfig", 128)) != null) {
                        str = providerInfoResolveContentProvider.packageName;
                    }
                    Boolean boolValueOf = Boolean.valueOf("BCC35D4D3606F154F0402AB7634E8490C0B244C2675C3C6238986987024F0C02".equals(r.b(context, str)));
                    r.a = boolValueOf;
                    zBooleanValue = boolValueOf.booleanValue();
                }
                if (!zBooleanValue) {
                    return;
                }
            }
            String action = intent.getAction();
            if (TextUtils.isEmpty(this.c)) {
                this.c = a(this.a, packageName, action);
                if (TextUtils.isEmpty(this.c)) {
                    n.d("CommandWorker", " reflectReceiver error: receiver for: " + action + " not found, package: " + packageName);
                    intent.setPackage(packageName);
                    this.a.sendBroadcast(intent);
                    return;
                }
            }
            try {
                Class<?> cls = Class.forName(this.c);
                final Object objNewInstance = cls.getConstructor(new Class[0]).newInstance(new Object[0]);
                final Method method = cls.getMethod("onReceive", Context.class, Intent.class);
                intent.setClassName(packageName, this.c);
                final Object[] objArr = {ContextDelegate.getContext(this.a).getApplicationContext(), intent};
                this.e.post(new Runnable() { // from class: com.vivo.push.sdk.a.1
                    @Override // java.lang.Runnable
                    public final void run() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
                        try {
                            method.invoke(objNewInstance, objArr);
                        } catch (Exception e2) {
                            n.b("CommandWorker", "reflect e: ", e2);
                        }
                    }
                });
            } catch (Exception e2) {
                n.b("CommandWorker", "reflect e: ", e2);
            }
        }
    }

    private static String a(Context context, String str, String str2) {
        List<ResolveInfo> listQueryBroadcastReceivers;
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return null;
        }
        Intent intent = new Intent(str2);
        intent.setPackage(str);
        try {
            PackageManager packageManager = context.getPackageManager();
            if (packageManager == null || (listQueryBroadcastReceivers = packageManager.queryBroadcastReceivers(intent, 64)) == null || listQueryBroadcastReceivers.size() <= 0) {
                return null;
            }
            return listQueryBroadcastReceivers.get(0).activityInfo.name;
        } catch (Exception e) {
            n.a("CommandWorker", "error  " + e.getMessage());
            return null;
        }
    }
}
