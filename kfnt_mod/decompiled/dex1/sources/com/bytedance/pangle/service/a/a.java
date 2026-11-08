package com.bytedance.pangle.service.a;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.RemoteException;
import androidx.annotation.Nullable;
import com.bytedance.pangle.Zeus;
import com.bytedance.pangle.d;
import com.bytedance.pangle.f;
import com.bytedance.pangle.log.ZeusLogger;
import com.bytedance.pangle.plugin.Plugin;
import com.bytedance.pangle.plugin.PluginManager;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class a extends d.a {

    /* renamed from: b, reason: collision with root package name */
    private static volatile a f4690b;

    /* renamed from: c, reason: collision with root package name */
    private final HashMap<ComponentName, IBinder> f4692c = new HashMap<>();
    private final HashMap<ComponentName, b> d = new HashMap<>();
    private final C0144a<Intent> e = new C0144a<>();
    private final HashMap<ComponentName, com.bytedance.pangle.service.a> f = new HashMap<>();
    private final HashSet<ComponentName> g = new HashSet<>();
    private final HashSet<ComponentName> h = new HashSet<>();

    /* renamed from: a, reason: collision with root package name */
    private final Handler f4691a = new Handler(Looper.getMainLooper());

    @Override // com.bytedance.pangle.d.a, android.os.IInterface
    public IBinder asBinder() {
        return null;
    }

    class b extends HashSet<f> {
        b() {
        }

        @Override // java.util.HashSet, java.util.AbstractCollection, java.util.Collection, java.util.Set
        public final boolean contains(@Nullable Object obj) {
            if (super.contains(obj)) {
                return true;
            }
            if (!(obj instanceof f)) {
                return false;
            }
            Iterator<f> it = iterator();
            while (it.hasNext()) {
                try {
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                if (it.next().a() == ((f) obj).a()) {
                    return true;
                }
            }
            return false;
        }

        @Override // java.util.HashSet, java.util.AbstractCollection, java.util.Collection, java.util.Set
        public final boolean remove(@Nullable Object obj) {
            if (super.remove(obj)) {
                return true;
            }
            f fVar = null;
            Iterator<f> it = iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                f next = it.next();
                try {
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                if (next.a() == ((f) obj).a()) {
                    fVar = next;
                    break;
                }
            }
            return super.remove(fVar);
        }
    }

    /* renamed from: com.bytedance.pangle.service.a.a$a, reason: collision with other inner class name */
    class C0144a<T> extends HashMap<f, T> {
        C0144a() {
        }

        @Override // java.util.HashMap, java.util.AbstractMap, java.util.Map
        public final boolean containsKey(@Nullable Object obj) {
            if (super.containsKey(obj)) {
                return true;
            }
            if (!(obj instanceof f)) {
                return false;
            }
            Iterator<f> it = keySet().iterator();
            while (it.hasNext()) {
                try {
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                if (it.next().a() == ((f) obj).a()) {
                    return true;
                }
            }
            return false;
        }

        @Override // java.util.HashMap, java.util.AbstractMap, java.util.Map
        @Nullable
        public final T remove(@Nullable Object obj) {
            f next;
            T t = (T) super.remove(obj);
            if (t != null) {
                return t;
            }
            Iterator<f> it = keySet().iterator();
            while (true) {
                if (!it.hasNext()) {
                    next = null;
                    break;
                }
                next = it.next();
                try {
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                if (next.a() == ((f) obj).a()) {
                    break;
                }
            }
            return (T) super.remove(next);
        }
    }

    public static a b() {
        if (f4690b == null) {
            synchronized (a.class) {
                if (f4690b == null) {
                    f4690b = new a();
                }
            }
        }
        return f4690b;
    }

    private a() {
    }

    @Override // com.bytedance.pangle.d
    public final ComponentName a(final Intent intent, final String str) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            return c(intent, str);
        }
        this.f4691a.post(new Runnable() { // from class: com.bytedance.pangle.service.a.a.1
            @Override // java.lang.Runnable
            public final void run() {
                a.this.c(intent, str);
            }
        });
        return intent.getComponent();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized ComponentName c(Intent intent, String str) {
        ComponentName component = intent.getComponent();
        if (!this.f.containsKey(component)) {
            com.bytedance.pangle.service.a aVarD = d(intent, str);
            if (aVarD == null) {
                return component;
            }
            this.f.put(component, aVarD);
            this.g.add(component);
        }
        com.bytedance.pangle.service.a aVar = this.f.get(component);
        if (aVar != null) {
            aVar.onStartCommand(intent, 0, 0);
        }
        return component;
    }

    private static com.bytedance.pangle.service.a d(Intent intent, String str) {
        com.bytedance.pangle.service.a aVarE = e(intent, str);
        if (aVarE != null) {
            aVarE.onCreate();
        }
        return aVarE;
    }

    private static com.bytedance.pangle.service.a e(Intent intent, String str) {
        boolean zLoadPlugin;
        ComponentName component = intent.getComponent();
        Plugin plugin = PluginManager.getInstance().getPlugin(str);
        try {
            zLoadPlugin = Zeus.loadPlugin(str);
        } catch (Exception e) {
            e = e;
            zLoadPlugin = false;
        }
        try {
            com.bytedance.pangle.service.a aVar = (com.bytedance.pangle.service.a) plugin.mClassLoader.loadClass(component.getClassName()).newInstance();
            aVar.attach(plugin);
            return aVar;
        } catch (Exception e2) {
            e = e2;
            ZeusLogger.e(ZeusLogger.TAG_SERVICE, "newServiceInstance failed! loadPlugin = ".concat(String.valueOf(zLoadPlugin)), e);
            throw new RuntimeException(e);
        }
    }

    @Override // com.bytedance.pangle.d
    public final boolean b(final Intent intent, String str) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            b().a(intent.getComponent());
            return true;
        }
        this.f4691a.post(new Runnable() { // from class: com.bytedance.pangle.service.a.a.2
            @Override // java.lang.Runnable
            public final void run() {
                a.b().a(intent.getComponent());
            }
        });
        return true;
    }

    public final synchronized boolean a(ComponentName componentName) {
        if (!this.f.containsKey(componentName)) {
            return false;
        }
        this.h.add(componentName);
        return b(componentName);
    }

    private boolean b(ComponentName componentName) {
        if (!this.g.contains(componentName)) {
            if (this.d.get(componentName) != null) {
                return false;
            }
            c(componentName);
            return true;
        }
        if (!this.h.contains(componentName) || this.d.containsKey(componentName)) {
            return false;
        }
        c(componentName);
        return true;
    }

    private void c(ComponentName componentName) {
        com.bytedance.pangle.service.a aVarRemove = this.f.remove(componentName);
        this.h.remove(componentName);
        this.f4692c.remove(componentName);
        this.g.remove(componentName);
        if (aVarRemove != null) {
            aVarRemove.onDestroy();
        }
    }

    @Override // com.bytedance.pangle.d
    public final boolean a(final Intent intent, final f fVar, final int i, final String str) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            return a(intent, fVar, str);
        }
        this.f4691a.post(new Runnable() { // from class: com.bytedance.pangle.service.a.a.3
            @Override // java.lang.Runnable
            public final void run() {
                try {
                    a.this.a(intent, fVar, str);
                } catch (RemoteException e) {
                    ZeusLogger.e(ZeusLogger.TAG_SERVICE, "bindService failed", e);
                }
            }
        });
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized boolean a(Intent intent, f fVar, String str) {
        ComponentName component = intent.getComponent();
        if (!this.f.containsKey(component)) {
            com.bytedance.pangle.service.a aVarD = d(intent, str);
            if (aVarD == null) {
                return false;
            }
            this.f.put(component, aVarD);
        }
        com.bytedance.pangle.service.a aVar = this.f.get(component);
        if (!this.f4692c.containsKey(component)) {
            this.f4692c.put(component, aVar.onBind(intent));
        }
        IBinder iBinder = this.f4692c.get(component);
        if (iBinder != null) {
            if (this.d.containsKey(component)) {
                if (!this.d.get(component).contains(fVar)) {
                    this.d.get(component).add(fVar);
                    this.e.put(fVar, intent);
                    fVar.a(component, iBinder);
                }
            } else {
                b bVar = new b();
                bVar.add(fVar);
                this.d.put(component, bVar);
                this.e.put(fVar, intent);
                fVar.a(component, iBinder);
            }
        }
        return true;
    }

    @Override // com.bytedance.pangle.d
    public final void a(final f fVar) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            b(fVar);
        } else {
            this.f4691a.post(new Runnable() { // from class: com.bytedance.pangle.service.a.a.4
                @Override // java.lang.Runnable
                public final void run() {
                    a.this.b(fVar);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void b(f fVar) {
        for (ComponentName componentName : this.d.keySet()) {
            b bVar = this.d.get(componentName);
            if (bVar.contains(fVar)) {
                bVar.remove(fVar);
                Intent intentRemove = this.e.remove(fVar);
                if (bVar.size() == 0) {
                    this.d.remove(componentName);
                    com.bytedance.pangle.service.a aVar = this.f.get(componentName);
                    if (aVar != null) {
                        aVar.onUnbind(intentRemove);
                    }
                }
                b(componentName);
                return;
            }
        }
    }
}
