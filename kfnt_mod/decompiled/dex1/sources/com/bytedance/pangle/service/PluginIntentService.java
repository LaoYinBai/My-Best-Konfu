package com.bytedance.pangle.service;

import android.app.IntentService;
import androidx.annotation.Keep;
import com.bytedance.pangle.Zeus;
import com.bytedance.pangle.log.ZeusLogger;
import com.bytedance.pangle.plugin.Plugin;
import com.bytedance.pangle.transform.ZeusTransformUtils;
import com.bytedance.pangle.util.FieldUtils;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

@Keep
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public abstract class PluginIntentService extends IntentService implements a {
    private static final String TAG = "PluginService";

    public PluginIntentService(String str) {
        super(str);
    }

    @Override // com.bytedance.pangle.service.a
    public void attach(Plugin plugin) {
        attachBaseContext(ZeusTransformUtils.wrapperContext2Application(Zeus.getAppApplication(), plugin.mPkgName));
        try {
            FieldUtils.writeField(this, "mActivityManager", createActivityManagerProxy());
            FieldUtils.writeField(this, "mClassName", getClass().getName());
            FieldUtils.writeField(this, "mApplication", Zeus.getAppApplication());
            FieldUtils.writeField(this, "mStartCompatibility", Boolean.valueOf(getApplicationInfo().targetSdkVersion < 5));
        } catch (Exception e) {
            ZeusLogger.e(ZeusLogger.TAG_SERVICE, "hook activityManager failed!", e);
        }
    }

    protected Object createActivityManagerProxy() {
        return Proxy.newProxyInstance(getClassLoader(), new Class[]{Class.forName("android.app.IActivityManager")}, new InvocationHandler() { // from class: com.bytedance.pangle.service.PluginIntentService.1
            /* JADX WARN: Removed duplicated region for block: B:18:0x0037  */
            @Override // java.lang.reflect.InvocationHandler
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public final java.lang.Object invoke(java.lang.Object r2, java.lang.reflect.Method r3, java.lang.Object[] r4) {
                /*
                    r1 = this;
                    java.lang.String r2 = r3.getName()
                    int r3 = r2.hashCode()
                    r4 = 39551382(0x25b8196, float:1.6126768E-37)
                    r0 = 0
                    if (r3 == r4) goto L2d
                    r4 = 690954390(0x292f2096, float:3.888607E-14)
                    if (r3 == r4) goto L23
                    r4 = 1930712422(0x73145566, float:1.1752198E31)
                    if (r3 == r4) goto L19
                    goto L37
                L19:
                    java.lang.String r3 = "stopServiceToken"
                    boolean r2 = r2.equals(r3)
                    if (r2 == 0) goto L37
                    r2 = 0
                    goto L38
                L23:
                    java.lang.String r3 = "getForegroundServiceType"
                    boolean r2 = r2.equals(r3)
                    if (r2 == 0) goto L37
                    r2 = 2
                    goto L38
                L2d:
                    java.lang.String r3 = "setServiceForeground"
                    boolean r2 = r2.equals(r3)
                    if (r2 == 0) goto L37
                    r2 = 1
                    goto L38
                L37:
                    r2 = -1
                L38:
                    switch(r2) {
                        case 0: goto L41;
                        case 1: goto L5f;
                        case 2: goto L3c;
                        default: goto L3b;
                    }
                L3b:
                    goto L5f
                L3c:
                    java.lang.Integer r2 = java.lang.Integer.valueOf(r0)
                    return r2
                L41:
                    com.bytedance.pangle.service.a.a r2 = com.bytedance.pangle.service.a.a.b()
                    android.content.ComponentName r3 = new android.content.ComponentName
                    com.bytedance.pangle.service.PluginIntentService r4 = com.bytedance.pangle.service.PluginIntentService.this
                    com.bytedance.pangle.service.PluginIntentService r0 = com.bytedance.pangle.service.PluginIntentService.this
                    java.lang.Class r0 = r0.getClass()
                    java.lang.String r0 = r0.getName()
                    r3.<init>(r4, r0)
                    boolean r2 = r2.a(r3)
                    java.lang.Boolean r2 = java.lang.Boolean.valueOf(r2)
                    return r2
                L5f:
                    r2 = 0
                    return r2
                */
                throw new UnsupportedOperationException("Method not decompiled: com.bytedance.pangle.service.PluginIntentService.AnonymousClass1.invoke(java.lang.Object, java.lang.reflect.Method, java.lang.Object[]):java.lang.Object");
            }
        });
    }
}
