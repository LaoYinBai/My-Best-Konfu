package com.kwad.sdk.collector.b;

import android.app.ActivityManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.Process;
import android.os.RemoteException;
import android.webkit.WebView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.kwad.sdk.api.proxy.app.ServiceProxyRemote;
import com.kwad.sdk.collector.AppStatusRules;
import com.kwad.sdk.collector.d;
import com.kwad.sdk.collector.model.b;
import com.kwad.sdk.crash.utils.SystemUtil;
import com.kwad.sdk.h.c;
import com.kwad.sdk.utils.al;
import com.kwad.sdk.utils.h;
import com.kwad.sdk.utils.t;
import com.meizu.cloud.pushsdk.constants.PushConstants;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONArray;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class a extends c {
    public static volatile Message a;
    private static AtomicBoolean d = new AtomicBoolean(false);
    private HandlerC0027a b = new HandlerC0027a();
    private Messenger c = new Messenger(this.b);

    /* renamed from: com.kwad.sdk.collector.b.a$a, reason: collision with other inner class name */
    private static class HandlerC0027a extends Handler {
        private WeakReference<Service> a;

        private HandlerC0027a() {
        }

        public void a(@Nullable Service service) {
            if (service != null) {
                this.a = new WeakReference<>(service);
            } else {
                this.a = null;
            }
        }

        @Override // android.os.Handler
        public void handleMessage(@NonNull Message message) {
            super.handleMessage(message);
            com.kwad.sdk.core.d.a.a("RemoteService", "handleMessage");
            Service service = this.a != null ? this.a.get() : null;
            if (service == null) {
                return;
            }
            if (!a.a().get()) {
                com.kwad.sdk.core.d.a.a("RemoteService", "save buffered message");
                a.a = Message.obtain(message);
                return;
            }
            final Messenger messenger = message.replyTo;
            int i = message.what;
            com.kwad.sdk.core.d.a.a("RemoteService", "handleMessage what: " + i);
            if (i != 100) {
                return;
            }
            final Bundle bundle = new Bundle();
            h.a(service, new h.b() { // from class: com.kwad.sdk.collector.b.a.a.1
                @Override // com.kwad.sdk.utils.h.b
                public void a(List<b> list) throws RemoteException {
                    com.kwad.sdk.core.d.a.a("RemoteService", "RemoteService: onAppStatusResult list: " + list);
                    if (list != null && !list.isEmpty()) {
                        com.kwad.sdk.core.d.a.a("RemoteService", "RemoteService: onAppStatusResult: " + list.size());
                        JSONArray jSONArrayA = h.a.a(list);
                        String string = jSONArrayA != null ? jSONArrayA.toString() : null;
                        com.kwad.sdk.core.d.a.a("RemoteService", "resultJson :" + string);
                        if (string != null) {
                            AppStatusRules appStatusRulesA = h.a();
                            ArrayList<AppStatusRules.Strategy> allStrategy = appStatusRulesA != null ? appStatusRulesA.getAllStrategy() : null;
                            String string2 = allStrategy != null ? t.b(allStrategy).toString() : null;
                            bundle.putString("resultJson", string);
                            bundle.putString("allStrategyJson", string2);
                        }
                    }
                    try {
                        Message messageObtain = Message.obtain();
                        messageObtain.what = 101;
                        messageObtain.setData(bundle);
                        messenger.send(messageObtain);
                    } catch (RemoteException unused) {
                    }
                    if (list == null || list.isEmpty()) {
                        return;
                    }
                    Iterator<b> it = list.iterator();
                    while (it.hasNext()) {
                        it.next().destroy();
                    }
                }
            });
        }
    }

    public static AtomicBoolean a() {
        return d;
    }

    public static void a(@NonNull Context context, ServiceConnection serviceConnection) {
        com.kwad.sdk.core.d.a.a("RemoteService", "bindASService");
        context.bindService(new Intent(context, (Class<?>) ServiceProxyRemote.class), serviceConnection, 1);
    }

    private static boolean a(Context context) {
        if (al.a(context) == null) {
            return false;
        }
        return !context.getPackageName().equals(r0);
    }

    private String b(Context context) {
        if (context == null) {
            return "ksad_remote";
        }
        try {
            for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : ((ActivityManager) context.getSystemService(PushConstants.INTENT_ACTIVITY_NAME)).getRunningAppProcesses()) {
                if (runningAppProcessInfo.pid == Process.myPid()) {
                    return runningAppProcessInfo.processName;
                }
            }
            return "ksad_remote";
        } catch (Exception e) {
            e.printStackTrace();
            return "ksad_remote";
        }
    }

    public static void b(@NonNull Context context, ServiceConnection serviceConnection) {
        com.kwad.sdk.core.d.a.a("RemoteService", "unbindASService");
        try {
            context.unbindService(serviceConnection);
        } catch (Exception e) {
            com.kwad.sdk.core.d.a.a(e);
        }
    }

    @Override // com.kwad.sdk.h.c, com.kwad.sdk.api.proxy.IServiceProxy
    public IBinder onBind(@NonNull Service service, Intent intent) {
        return this.c.getBinder();
    }

    @Override // com.kwad.sdk.h.c, com.kwad.sdk.api.proxy.IServiceProxy
    public void onCreate(Service service) {
        super.onCreate(service);
        if (Build.VERSION.SDK_INT >= 28) {
            try {
                WebView.setDataDirectorySuffix(b(service.getApplicationContext()));
            } catch (Exception e) {
                com.kwad.sdk.core.d.a.a("RemoteService", "WebView has already been initialized " + e.getMessage());
            }
        }
        com.kwad.sdk.core.d.a.a("RemoteService", "onCreate processName:" + al.a(service));
        if (SystemUtil.b(service)) {
            d.set(true);
        } else {
            d.a(service, new d.a() { // from class: com.kwad.sdk.collector.b.a.1
                @Override // com.kwad.sdk.collector.d.a
                public void a() {
                    com.kwad.sdk.core.d.a.a("RemoteService", "onLoaded");
                    a.d.set(true);
                    if (a.a != null) {
                        a.this.b.handleMessage(a.a);
                        a.a = null;
                    }
                }

                @Override // com.kwad.sdk.collector.d.a
                public void a(String str) {
                    com.kwad.sdk.core.d.a.e("RemoteService", "onLoadError: " + str);
                    a.d.set(false);
                }
            });
        }
        this.b.a(service);
    }

    @Override // com.kwad.sdk.h.c, com.kwad.sdk.api.proxy.IServiceProxy
    public void onDestroy(@NonNull Service service) {
        super.onDestroy(service);
        com.kwad.sdk.core.d.a.a("RemoteService", "onDestroy");
        this.b.a(null);
        if (a(service)) {
            com.kwad.sdk.core.d.a.a("RemoteService", "goto kill myself");
            Process.killProcess(Process.myPid());
        }
    }
}
