package com.bytedance.a;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.bytedance.applog.IEventObserver;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArraySet;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class aw implements IEventObserver {

    /* renamed from: a, reason: collision with root package name */
    public static volatile aw f3379a;

    /* renamed from: b, reason: collision with root package name */
    public final CopyOnWriteArraySet<IEventObserver> f3380b = new CopyOnWriteArraySet<>();

    public static aw a() {
        if (f3379a == null) {
            synchronized (aw.class) {
                if (f3379a == null) {
                    f3379a = new aw();
                }
            }
        }
        return f3379a;
    }

    public void a(IEventObserver iEventObserver) {
        if (iEventObserver != null) {
            this.f3380b.add(iEventObserver);
        }
    }

    public void b(IEventObserver iEventObserver) {
        if (iEventObserver != null) {
            this.f3380b.remove(iEventObserver);
        }
    }

    @Override // com.bytedance.applog.IEventObserver
    public void onEvent(@NonNull String str, @NonNull String str2, String str3, long j, long j2, String str4) {
        Iterator<IEventObserver> it = this.f3380b.iterator();
        while (it.hasNext()) {
            it.next().onEvent(str, str2, str3, j, j2, str4);
        }
    }

    @Override // com.bytedance.applog.IEventObserver
    public void onEventV3(@NonNull String str, @Nullable JSONObject jSONObject) {
        Iterator<IEventObserver> it = this.f3380b.iterator();
        while (it.hasNext()) {
            it.next().onEventV3(str, jSONObject);
        }
    }
}
