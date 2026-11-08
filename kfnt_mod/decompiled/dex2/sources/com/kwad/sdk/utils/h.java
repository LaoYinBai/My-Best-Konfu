package com.kwad.sdk.utils;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresPermission;
import androidx.annotation.WorkerThread;
import com.kwad.sdk.api.KsAdSDK;
import com.kwad.sdk.collector.AppStatusRules;
import com.kwad.sdk.collector.c;
import com.kwad.sdk.crash.utils.SystemUtil;
import com.kwad.sdk.utils.InstalledAppInfoManager;
import com.mbridge.msdk.playercommon.exoplayer2.C;
import com.meizu.cloud.pushsdk.constants.PushConstants;
import java.io.File;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class h {
    private static Handler a;
    private static Messenger b;
    private static ServiceConnection c = new ServiceConnection() { // from class: com.kwad.sdk.utils.h.1
        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) throws RemoteException {
            Messenger messenger = new Messenger(iBinder);
            Message messageObtain = Message.obtain();
            messageObtain.what = 100;
            if (!h.j()) {
                com.kwad.sdk.core.d.a.d("AppStatusHelper", "clientMessenger init error");
                return;
            }
            messageObtain.replyTo = h.b;
            try {
                messenger.send(messageObtain);
            } catch (RemoteException unused) {
            }
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
        }
    };
    private static volatile ExecutorService d;
    private static volatile AppStatusRules e;
    private static WeakReference<Context> f;

    /* renamed from: com.kwad.sdk.utils.h$2, reason: invalid class name */
    static class AnonymousClass2 implements Runnable {
        final /* synthetic */ Context a;

        AnonymousClass2(Context context) {
            this.a = context;
        }

        @Override // java.lang.Runnable
        public void run() {
            com.kwad.sdk.collector.c.a(this.a, new c.a() { // from class: com.kwad.sdk.utils.h.2.1
                @Override // com.kwad.sdk.collector.c.a
                public void a(int i, String str) {
                    com.kwad.sdk.core.d.a.e("AppStatusHelper", "fetchAppStatusConfig onFetchError: " + str + ", code: " + i);
                }

                @Override // com.kwad.sdk.collector.c.a
                public void a(AppStatusRules appStatusRules) throws Throwable {
                    appStatusRules.initStatus(AnonymousClass2.this.a);
                    AppStatusRules unused = h.e = appStatusRules;
                    h.b(AnonymousClass2.this.a, h.e);
                    h.a(AnonymousClass2.this.a);
                    boolean zL = h.l(AnonymousClass2.this.a);
                    boolean zIsAppStatusTargetNotEmpty = AppStatusRules.isAppStatusTargetNotEmpty(h.e);
                    com.kwad.sdk.core.d.a.a("AppStatusHelper", "appStatusTargetNotEmpty: " + zIsAppStatusTargetNotEmpty + ", permissionGranted: " + zL);
                    if (zL && zIsAppStatusTargetNotEmpty) {
                        long jObtainDefaultScanInterval = h.e.obtainDefaultScanInterval();
                        if (jObtainDefaultScanInterval > 0) {
                            h.b(AnonymousClass2.this.a, jObtainDefaultScanInterval);
                        } else {
                            h.i(AnonymousClass2.this.a);
                        }
                    }
                    boolean zIsUploadTargetNotEmpty = AppStatusRules.isUploadTargetNotEmpty(h.e);
                    boolean z = h.e.obtainUploadConfigFileMaxSize() > 0;
                    com.kwad.sdk.core.d.a.a("AppStatusHelper", "uploadTargetNotEmpty: " + zIsUploadTargetNotEmpty + ", enableUpload: " + z);
                    if (zIsUploadTargetNotEmpty && z && zL) {
                        h.k();
                        h.d.submit(new Runnable() { // from class: com.kwad.sdk.utils.h.2.1.1
                            @Override // java.lang.Runnable
                            public void run() {
                                try {
                                    com.kwad.sdk.collector.j.a(AnonymousClass2.this.a, h.e);
                                } catch (Throwable th) {
                                    com.kwad.sdk.crash.a.a(th);
                                    com.kwad.sdk.core.d.a.b(th);
                                }
                            }
                        });
                    }
                }
            });
        }
    }

    public static class a implements com.kwad.sdk.core.b {
        private String a;
        private String b;
        private List<Long> c = new ArrayList();

        public a() {
        }

        public a(String str, String str2) {
            this.a = str;
            this.b = str2;
        }

        @Nullable
        public static JSONArray a(List<com.kwad.sdk.collector.model.b> list) {
            List<a> listB;
            try {
                listB = b(list);
            } catch (Exception e) {
                com.kwad.sdk.crash.a.a(e);
                listB = null;
            }
            if (listB == null) {
                return null;
            }
            JSONArray jSONArray = new JSONArray();
            Iterator<a> it = listB.iterator();
            while (it.hasNext()) {
                jSONArray.put(it.next().toJson());
            }
            return jSONArray;
        }

        @Nullable
        public static List<a> b(List<com.kwad.sdk.collector.model.b> list) {
            a aVar;
            if (list == null || list.size() == 0) {
                return null;
            }
            HashMap map = new HashMap();
            for (com.kwad.sdk.collector.model.b bVar : list) {
                String strB = com.kwad.sdk.collector.model.c.b(bVar);
                if (map.containsKey(strB)) {
                    aVar = (a) map.get(strB);
                } else {
                    a aVar2 = new a(com.kwad.sdk.collector.model.c.a(bVar), com.kwad.sdk.collector.model.c.b(bVar));
                    map.put(strB, aVar2);
                    aVar = aVar2;
                }
                long jC = com.kwad.sdk.collector.model.c.c(bVar) / 1000;
                if (aVar != null) {
                    aVar.a(jC);
                }
            }
            return new ArrayList(map.values());
        }

        public void a(long j) {
            this.c.add(Long.valueOf(j));
        }

        @Override // com.kwad.sdk.core.b
        public void parseJson(@Nullable JSONObject jSONObject) {
            if (jSONObject == null) {
                return;
            }
            this.a = jSONObject.optString("appName");
            this.b = jSONObject.optString("packageName");
            JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray("runningTimes");
            if (jSONArrayOptJSONArray != null) {
                this.c.clear();
                int length = jSONArrayOptJSONArray.length();
                for (int i = 0; i < length; i++) {
                    try {
                        this.c.add(Long.valueOf(jSONArrayOptJSONArray.getLong(i)));
                    } catch (JSONException e) {
                        com.kwad.sdk.core.d.a.b(e);
                    }
                }
            }
        }

        @Override // com.kwad.sdk.core.b
        public JSONObject toJson() throws JSONException {
            JSONObject jSONObject = new JSONObject();
            t.a(jSONObject, "appName", this.a);
            t.a(jSONObject, "packageName", this.b);
            JSONArray jSONArray = new JSONArray();
            Iterator<Long> it = this.c.iterator();
            while (it.hasNext()) {
                jSONArray.put(it.next().longValue());
            }
            t.a(jSONObject, "runningTimes", jSONArray);
            return jSONObject;
        }
    }

    public interface b {
        void a(List<com.kwad.sdk.collector.model.b> list);
    }

    private static class c extends Handler {
        public c(@NonNull Looper looper) {
            super(looper);
        }

        private void a(Message message) throws JSONException {
            ArrayList arrayList;
            List listA;
            Bundle data = message.getData();
            List<AppStatusRules.Strategy> listA2 = null;
            if (data != null) {
                try {
                    if (data.containsKey("resultJson")) {
                        listA = t.a(data.getString("resultJson"), new com.kwad.sdk.core.c<a>() { // from class: com.kwad.sdk.utils.h.c.1
                            @Override // com.kwad.sdk.core.c
                            /* renamed from: b, reason: merged with bridge method [inline-methods] */
                            public a a() {
                                return new a();
                            }
                        });
                        arrayList = null;
                    } else {
                        arrayList = (ArrayList) data.getSerializable("data");
                        listA = null;
                    }
                } catch (Throwable unused) {
                    arrayList = null;
                    listA = null;
                }
                if (arrayList != null) {
                    com.kwad.sdk.core.d.a.a("AppStatusHelper", "ClientHandler: handleMessage data size: " + arrayList.size());
                    a((ArrayList<com.kwad.sdk.collector.model.b>) arrayList);
                }
                if (listA != null) {
                    a((List<a>) listA);
                }
            }
            if (h.f != null && h.f.get() != null && data != null) {
                if (data.containsKey("allStrategyJson")) {
                    String string = data.getString("allStrategyJson");
                    if (string != null) {
                        listA2 = t.a(string, new com.kwad.sdk.core.c<AppStatusRules.Strategy>() { // from class: com.kwad.sdk.utils.h.c.2
                            @Override // com.kwad.sdk.core.c
                            /* renamed from: b, reason: merged with bridge method [inline-methods] */
                            public AppStatusRules.Strategy a() {
                                return new AppStatusRules.Strategy();
                            }
                        });
                    }
                } else {
                    listA2 = (ArrayList) data.getSerializable("allStrategy");
                }
                if (listA2 != null) {
                    for (AppStatusRules.Strategy strategy : listA2) {
                        long needSaveLaunchTime = strategy.getNeedSaveLaunchTime();
                        if (needSaveLaunchTime >= 0) {
                            com.kwad.sdk.collector.h.a((Context) h.f.get(), strategy, needSaveLaunchTime);
                        }
                    }
                }
            }
            if (h.f == null || h.c == null) {
                return;
            }
            com.kwad.sdk.core.d.a.a("AppStatusHelper", "unbindASService");
            com.kwad.sdk.collector.b.a.b((Context) h.f.get(), h.c);
        }

        private static void a(ArrayList<com.kwad.sdk.collector.model.b> arrayList) {
            JSONArray jSONArrayA;
            if (arrayList == null || (jSONArrayA = a.a(arrayList)) == null) {
                return;
            }
            com.kwad.sdk.core.report.d.a(jSONArrayA);
        }

        private static void a(List<a> list) {
            JSONArray jSONArrayB;
            if (list == null || (jSONArrayB = t.b(list)) == null) {
                return;
            }
            com.kwad.sdk.core.report.d.a(jSONArrayB);
        }

        @Override // android.os.Handler
        public void handleMessage(@NonNull Message message) {
            super.handleMessage(message);
            if (message.what != 101) {
                return;
            }
            try {
                a(message);
            } catch (Throwable th) {
                com.kwad.sdk.core.d.a.b(th);
            }
        }
    }

    static class d implements b {
        private b a;

        public d(b bVar) {
            this.a = bVar;
        }

        @Override // com.kwad.sdk.utils.h.b
        public void a(List<com.kwad.sdk.collector.model.b> list) {
            JSONArray jSONArrayA = a.a(list);
            if (jSONArrayA != null) {
                com.kwad.sdk.core.report.d.a(jSONArrayA);
            }
            if (this.a != null) {
                this.a.a(list);
            }
        }
    }

    public static AppStatusRules a() {
        return e;
    }

    @NonNull
    private static List<com.kwad.sdk.collector.model.b> a(AppStatusRules.Strategy strategy, Map<String, InstalledAppInfoManager.AppPackageInfo> map) {
        boolean zIsNeedLaunch = strategy.isNeedLaunch();
        com.kwad.sdk.core.d.a.a("AppStatusHelper", "analysisByFile, strategy: " + strategy.getName() + ", needLaunch: " + zIsNeedLaunch);
        return !zIsNeedLaunch ? new ArrayList() : com.kwad.sdk.collector.b.a().a(strategy, map);
    }

    public static List<com.kwad.sdk.collector.model.b> a(List<com.kwad.sdk.collector.model.b> list) {
        return (list == null || list.isEmpty()) ? list : new ArrayList(new LinkedHashSet(list));
    }

    @WorkerThread
    public static void a(Context context) {
        if (e == null) {
            e = h(context);
        }
    }

    public static void a(Context context, long j) {
        if (ao.l() || com.kwad.sdk.core.config.b.a(8192L) || context == null || m(context)) {
            return;
        }
        boolean zB = SystemUtil.b(context);
        com.kwad.sdk.core.d.a.a("AppStatusHelper", "isMainProcess: " + zB);
        if (zB) {
            f = new WeakReference<>(context);
            if (j <= 0) {
                j = 30000;
            }
            if (a == null) {
                a = new Handler(Looper.getMainLooper());
            }
            a.postDelayed(new AnonymousClass2(context), j);
        }
    }

    public static void a(final Context context, final b bVar) {
        if (context == null || ao.l() || com.kwad.sdk.core.config.b.a(8192L) || m(context)) {
            return;
        }
        k();
        d.submit(new Runnable() { // from class: com.kwad.sdk.utils.h.4
            @Override // java.lang.Runnable
            public void run() {
                List listK;
                List listJ;
                try {
                    HashSet hashSet = new HashSet();
                    if (Build.VERSION.SDK_INT < 21 && (listJ = h.j(context)) != null) {
                        hashSet.addAll(listJ);
                    }
                    if (!h.l(context) || (listK = h.k(context)) == null) {
                        return;
                    }
                    Iterator it = listK.iterator();
                    while (it.hasNext()) {
                        com.kwad.sdk.core.d.a.a("AppStatusHelper", "AppRunningInfo: " + ((com.kwad.sdk.collector.model.b) it.next()));
                    }
                    hashSet.addAll(listK);
                    if (bVar != null) {
                        bVar.a(new ArrayList(hashSet));
                    }
                } catch (Throwable th) {
                    com.kwad.sdk.core.d.a.b(th);
                    com.kwad.sdk.crash.a.a(th);
                }
            }
        });
    }

    @Nullable
    public static List<String> b(@NonNull Context context) {
        String[] list;
        if (ao.l() || com.kwad.sdk.core.config.b.a(8192L)) {
            return new ArrayList();
        }
        if (!m(context) && l(context)) {
            File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/Android/data");
            if (!file.exists() || !file.isDirectory() || (list = file.list()) == null) {
                return null;
            }
            ArrayList arrayList = new ArrayList();
            for (String str : list) {
                if (!TextUtils.isEmpty(str) && !str.startsWith(".")) {
                    arrayList.add(str);
                }
            }
            return arrayList;
        }
        return new ArrayList();
    }

    public static void b(final Context context, final long j) {
        if (a == null) {
            a = new Handler(Looper.getMainLooper());
        }
        a.post(new Runnable() { // from class: com.kwad.sdk.utils.h.3
            @Override // java.lang.Runnable
            public void run() throws ClassNotFoundException {
                h.i(context);
                h.a.postDelayed(this, j);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    @WorkerThread
    public static void b(Context context, AppStatusRules appStatusRules) throws Throwable {
        File file = new File(context.getFilesDir(), "LOCAL_APP_STATUS_RULES_JSON");
        com.kwad.sdk.crash.utils.h.a(file.getAbsolutePath(), appStatusRules.toJson().toString(), false);
    }

    @RequiresPermission("android.permission.WRITE_EXTERNAL_STORAGE")
    public static List<com.kwad.sdk.collector.model.b> c(Context context) throws PackageManager.NameNotFoundException {
        ArrayList arrayList = new ArrayList();
        if (ao.l() || com.kwad.sdk.core.config.b.a(8192L) || m(context)) {
            return arrayList;
        }
        AppStatusRules appStatusRulesA = a();
        Map<String, InstalledAppInfoManager.AppPackageInfo> mapB = InstalledAppInfoManager.b(context);
        for (AppStatusRules.Strategy strategy : com.kwad.sdk.collector.h.a(appStatusRulesA)) {
            arrayList.addAll(a(strategy, mapB));
            strategy.setNeedSaveLaunchTime(System.currentTimeMillis());
        }
        AppStatusRules.Strategy strategyB = com.kwad.sdk.collector.h.b(appStatusRulesA);
        arrayList.addAll(a(strategyB, mapB));
        strategyB.setNeedSaveLaunchTime(System.currentTimeMillis());
        return a(arrayList);
    }

    @Nullable
    @WorkerThread
    private static AppStatusRules h(Context context) {
        File file = new File(context.getFilesDir(), "LOCAL_APP_STATUS_RULES_JSON");
        if (!file.exists()) {
            return null;
        }
        try {
            JSONObject jSONObject = new JSONObject(com.kwad.sdk.crash.utils.h.a(file));
            AppStatusRules appStatusRules = new AppStatusRules();
            appStatusRules.parseJson(jSONObject);
            return appStatusRules;
        } catch (Throwable unused) {
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void i(Context context) throws ClassNotFoundException {
        if (context == null) {
            return;
        }
        boolean zL = l();
        com.kwad.sdk.core.d.a.a("AppStatusHelper", "isServiceAvailable: " + zL);
        if (zL) {
            com.kwad.sdk.collector.b.a.a(context, c);
        } else {
            a(context, new d(null));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Nullable
    public static List<com.kwad.sdk.collector.model.b> j(Context context) {
        List<ActivityManager.RunningTaskInfo> runningTasks;
        if (!(av.a(context, "android.permission.GET_TASKS") == 0) || (runningTasks = ((ActivityManager) context.getSystemService(PushConstants.INTENT_ACTIVITY_NAME)).getRunningTasks(100)) == null) {
            return null;
        }
        LinkedList linkedList = new LinkedList();
        Iterator<ActivityManager.RunningTaskInfo> it = runningTasks.iterator();
        while (it.hasNext()) {
            ComponentName componentName = it.next().topActivity;
            if (componentName != null) {
                String packageName = componentName.getPackageName();
                com.kwad.sdk.core.d.a.a("AppStatusHelper", "packageName:" + packageName);
                linkedList.add(new com.kwad.sdk.collector.model.kwai.b(-1L, null, packageName));
            }
        }
        return linkedList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean j() {
        if (b == null) {
            try {
                b = new Messenger(new c(Looper.getMainLooper()));
            } catch (Throwable unused) {
            }
        }
        return b != null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    @WorkerThread
    public static List<com.kwad.sdk.collector.model.b> k(Context context) {
        if (!l(context)) {
            return new ArrayList();
        }
        if (e == null) {
            e = h(context);
        }
        return c(context);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static synchronized void k() {
        if (d != null) {
            return;
        }
        d = com.kwad.sdk.core.i.b.m();
    }

    private static boolean l() throws ClassNotFoundException {
        try {
            Class<?> cls = Class.forName("com.kwad.sdk.api.proxy.app.ServiceProxyRemote");
            if (cls != null) {
                Context context = KsAdSDK.getContext();
                return context.getPackageManager().queryIntentServices(new Intent(context, cls), C.DEFAULT_BUFFER_SEGMENT_SIZE).size() > 0;
            }
        } catch (ClassNotFoundException e2) {
            com.kwad.sdk.core.d.a.b(e2);
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean l(@NonNull Context context) {
        return context.checkCallingOrSelfPermission("android.permission.WRITE_EXTERNAL_STORAGE") == 0;
    }

    private static boolean m(@NonNull Context context) {
        return context.getApplicationInfo().targetSdkVersion >= 29 && Build.VERSION.SDK_INT >= 29 && !Environment.isExternalStorageLegacy();
    }
}
