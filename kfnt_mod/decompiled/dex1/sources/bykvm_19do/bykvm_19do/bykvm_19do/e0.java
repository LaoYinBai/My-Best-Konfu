package bykvm_19do.bykvm_19do.bykvm_19do;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class e0 implements c {

    /* renamed from: a, reason: collision with root package name */
    private static ArrayList<WeakReference<c>> f1327a = new ArrayList<>(4);

    @Override // bykvm_19do.bykvm_19do.bykvm_19do.c
    public synchronized void a(String str, String str2, String str3) {
        Iterator<WeakReference<c>> it = f1327a.iterator();
        while (it.hasNext()) {
            c cVar = it.next().get();
            if (cVar == null) {
                it.remove();
            } else {
                cVar.a(str, str2, str3);
            }
        }
    }

    @Override // bykvm_19do.bykvm_19do.bykvm_19do.c
    public synchronized void b(boolean z, JSONObject jSONObject) {
        Iterator<WeakReference<c>> it = f1327a.iterator();
        while (it.hasNext()) {
            c cVar = it.next().get();
            if (cVar == null) {
                it.remove();
            } else {
                cVar.b(z, jSONObject);
            }
        }
    }

    @Override // bykvm_19do.bykvm_19do.bykvm_19do.c
    public synchronized void a(boolean z, String str, String str2, String str3, String str4, String str5, String str6) {
        Iterator<WeakReference<c>> it = f1327a.iterator();
        while (it.hasNext()) {
            c cVar = it.next().get();
            if (cVar == null) {
                it.remove();
            } else {
                cVar.a(z, str, str2, str3, str4, str5, str6);
            }
        }
    }

    @Override // bykvm_19do.bykvm_19do.bykvm_19do.c
    public synchronized void a(boolean z, JSONObject jSONObject) {
        Iterator<WeakReference<c>> it = f1327a.iterator();
        while (it.hasNext()) {
            c cVar = it.next().get();
            if (cVar == null) {
                it.remove();
            } else {
                cVar.a(z, jSONObject);
            }
        }
    }

    public synchronized void a(c cVar) {
        if (cVar != null) {
            f1327a.add(new WeakReference<>(cVar));
        }
    }
}
