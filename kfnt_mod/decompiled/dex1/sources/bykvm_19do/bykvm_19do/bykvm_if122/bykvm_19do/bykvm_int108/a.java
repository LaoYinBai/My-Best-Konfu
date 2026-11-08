package bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_int108;

import android.content.Context;
import android.os.AsyncTask;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import com.bytedance.msdk.adapter.config.DefaultAdapterClasses;
import com.bytedance.msdk.adapter.config.ITTAdapterConfiguration;
import com.bytedance.msdk.adapter.util.AsyncTasks;
import com.bytedance.msdk.adapter.util.Logger;
import com.bytedance.msdk.adapter.util.Preconditions;
import com.bytedance.msdk.adapter.util.Reflection;
import com.bytedance.msdk.api.AdError;
import com.bytedance.msdk.api.error.InitSdkError;
import com.bytedance.msdk.api.v2.ad.custom.init.GMCustomAdapterConfiguration;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class a implements b {

    /* renamed from: a, reason: collision with root package name */
    private final Map<String, ITTAdapterConfiguration> f1878a = new ConcurrentHashMap();

    /* renamed from: b, reason: collision with root package name */
    private final Map<String, GMCustomAdapterConfiguration> f1879b = new ConcurrentHashMap();

    /* renamed from: c, reason: collision with root package name */
    private List<c> f1880c = new CopyOnWriteArrayList();

    public void a(String str, GMCustomAdapterConfiguration gMCustomAdapterConfiguration) {
        this.f1879b.put(str, gMCustomAdapterConfiguration);
    }

    public GMCustomAdapterConfiguration b(String str) {
        return this.f1879b.get(str);
    }

    @Override // com.bytedance.msdk.adapter.config.TTOnNetworkInitializationFinishedListener
    public void onNetworkInitializationFinished(@NonNull Class<? extends ITTAdapterConfiguration> cls, @NonNull AdError adError) {
        StringBuilder sb = new StringBuilder();
        sb.append(cls);
        sb.append(" initialized status: ");
        sb.append(adError instanceof InitSdkError ? ((InitSdkError) adError).printMsg() : "");
        Logger.i("TTMediationSDK", sb.toString());
    }

    private void b() {
        synchronized (this.f1880c) {
            for (c cVar : this.f1880c) {
                if (cVar != null) {
                    cVar.a();
                }
            }
            this.f1880c.clear();
        }
    }

    public void a(Context context, Set<String> set, Map<String, Map<String, String>> map, Map<String, Map<String, String>> map2, c cVar) {
        Preconditions.checkNotNull(context);
        Preconditions.checkNotNull(set);
        Preconditions.checkNotNull(map);
        Preconditions.checkNotNull(map2);
        a(cVar);
        AsyncTasks.safeExecuteOnExecutor(new AsyncTaskC0066a(context.getApplicationContext(), set, map, map2, this, this.f1878a, cVar != null), new Void[0]);
    }

    public String a(Context context, Map<String, Object> map, String str) {
        Preconditions.checkNotNull(context);
        ITTAdapterConfiguration iTTAdapterConfigurationA = a(str);
        if (iTTAdapterConfigurationA == null) {
            return null;
        }
        return iTTAdapterConfigurationA.getBiddingToken(context, map);
    }

    @Override // bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_int108.b
    public void a(Map<String, ITTAdapterConfiguration> map) {
        b();
    }

    private void a(c cVar) {
        if (cVar == null) {
            return;
        }
        synchronized (this.f1880c) {
            if (!this.f1880c.contains(cVar)) {
                this.f1880c.add(cVar);
            }
        }
    }

    public Map<String, ITTAdapterConfiguration> a() {
        ITTAdapterConfiguration iTTAdapterConfiguration;
        for (String str : DefaultAdapterClasses.getClassNamesSet()) {
            try {
                if ((this.f1878a.containsKey(str) ? this.f1878a.get(str) : null) == null && (iTTAdapterConfiguration = (ITTAdapterConfiguration) Reflection.instantiateClassWithEmptyConstructor(str, ITTAdapterConfiguration.class)) != null) {
                    this.f1878a.put(str, iTTAdapterConfiguration);
                }
            } catch (Exception unused) {
            }
        }
        return this.f1878a;
    }

    public ITTAdapterConfiguration a(String str) {
        String classNameByAdnName = DefaultAdapterClasses.getClassNameByAdnName(str);
        if (TextUtils.isEmpty(classNameByAdnName)) {
            return null;
        }
        ITTAdapterConfiguration iTTAdapterConfiguration = this.f1878a.get(classNameByAdnName);
        if (iTTAdapterConfiguration != null) {
            return iTTAdapterConfiguration;
        }
        try {
            ITTAdapterConfiguration iTTAdapterConfiguration2 = (ITTAdapterConfiguration) Reflection.instantiateClassWithEmptyConstructor(classNameByAdnName, ITTAdapterConfiguration.class);
            if (iTTAdapterConfiguration2 != null) {
                try {
                    this.f1878a.put(classNameByAdnName, iTTAdapterConfiguration2);
                } catch (Throwable unused) {
                }
            }
            return iTTAdapterConfiguration2;
        } catch (Throwable unused2) {
            return iTTAdapterConfiguration;
        }
    }

    /* renamed from: bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_int108.a$a, reason: collision with other inner class name */
    private static class AsyncTaskC0066a extends AsyncTask<Void, Void, Map<String, ITTAdapterConfiguration>> {

        /* renamed from: a, reason: collision with root package name */
        @NonNull
        private Context f1881a;

        /* renamed from: b, reason: collision with root package name */
        @NonNull
        private final Set<String> f1882b;

        /* renamed from: c, reason: collision with root package name */
        @NonNull
        private final Map<String, Map<String, String>> f1883c;

        @NonNull
        private final Map<String, Map<String, String>> d;

        @NonNull
        private final b e;
        private final Map<String, ITTAdapterConfiguration> f;
        private boolean g;

        AsyncTaskC0066a(@NonNull Context context, @NonNull Set<String> set, @NonNull Map<String, Map<String, String>> map, @NonNull Map<String, Map<String, String>> map2, @NonNull b bVar, Map<String, ITTAdapterConfiguration> map3, boolean z) {
            Preconditions.checkNotNull(context);
            Preconditions.checkNotNull(set);
            Preconditions.checkNotNull(map);
            Preconditions.checkNotNull(map2);
            Preconditions.checkNotNull(bVar);
            this.f1881a = context;
            this.f1882b = set;
            this.f1883c = map;
            this.d = map2;
            this.e = bVar;
            this.f = map3;
            this.g = z;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public Map<String, ITTAdapterConfiguration> doInBackground(Void... voidArr) {
            Map<String, ITTAdapterConfiguration> map;
            synchronized (a.class) {
                for (String str : this.f1882b) {
                    try {
                        ITTAdapterConfiguration iTTAdapterConfiguration = this.f.containsKey(str) ? this.f.get(str) : null;
                        if (iTTAdapterConfiguration == null) {
                            iTTAdapterConfiguration = (ITTAdapterConfiguration) Reflection.instantiateClassWithEmptyConstructor(str, ITTAdapterConfiguration.class);
                        }
                        if (iTTAdapterConfiguration != null) {
                            if (this.f1881a == null) {
                                Logger.e("TTAdapterConfigurationManager", "Context null. Unable to initialize adapter configuration " + str);
                            } else {
                                Map<String, String> map2 = this.f1883c.get(str);
                                HashMap map3 = new HashMap();
                                if (map2 != null) {
                                    for (Map.Entry<String, String> entry : map2.entrySet()) {
                                        map3.put(entry.getKey(), entry.getValue());
                                    }
                                }
                                Map<String, String> map4 = this.d.get(str);
                                if (map4 != null) {
                                    iTTAdapterConfiguration.setMsdkRequestOptions(map4);
                                }
                                Logger.e("TTAdapterConfigurationManager", String.format(Locale.US, "Initializing %s version %s with network sdk version %s and with params %s", str, iTTAdapterConfiguration.getAdapterVersion(), iTTAdapterConfiguration.getNetworkSdkVersion(), map3));
                                iTTAdapterConfiguration.initializeNetwork(this.f1881a, map3, this.e);
                                if (this.f.get(str) == null) {
                                    this.f.put(str, iTTAdapterConfiguration);
                                }
                            }
                        }
                    } catch (Exception e) {
                        Logger.i("TTAdapterConfigurationManager", "Unable to find class " + str, e);
                    }
                }
                map = this.f;
            }
            return map;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public void onPostExecute(@NonNull Map<String, ITTAdapterConfiguration> map) {
            if (this.g) {
                this.e.a(map);
            }
        }
    }
}
