package bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_case1;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import bykvm_19do.bykvm_19do.bykvm_if122.bykvm_new1.t;
import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class e {
    private static final String d = "e";
    private static e e;

    /* renamed from: a, reason: collision with root package name */
    private final Set<String> f1813a = new HashSet(1);

    /* renamed from: b, reason: collision with root package name */
    private final List<WeakReference<f>> f1814b = new ArrayList(1);

    /* renamed from: c, reason: collision with root package name */
    private final List<f> f1815c = new ArrayList(1);

    private e() {
        b();
    }

    public static e a() {
        if (e == null) {
            e = new e();
        }
        return e;
    }

    private synchronized void b() {
        String str;
        for (Field field : Manifest.permission.class.getFields()) {
            try {
                str = (String) field.get("");
            } catch (IllegalAccessException e2) {
                Log.e(d, "Could not access field", e2);
                str = null;
            }
            this.f1813a.add(str);
        }
    }

    @NonNull
    private List<String> c(@NonNull Activity activity, @NonNull String[] strArr, @Nullable f fVar) {
        ArrayList arrayList = new ArrayList(strArr.length);
        for (String str : strArr) {
            if (this.f1813a.contains(str)) {
                if (!a(activity, str)) {
                    arrayList.add(str);
                } else if (fVar != null) {
                    fVar.a(str, d.GRANTED);
                }
            } else if (fVar != null) {
                fVar.a(str, d.NOT_FOUND);
            }
        }
        return arrayList;
    }

    private synchronized void a(@NonNull String[] strArr, @Nullable f fVar) {
        if (fVar == null) {
            return;
        }
        fVar.a(strArr);
        this.f1815c.add(fVar);
        this.f1814b.add(new WeakReference<>(fVar));
    }

    private synchronized void a(@Nullable f fVar) {
        Iterator<WeakReference<f>> it = this.f1814b.iterator();
        while (it.hasNext()) {
            WeakReference<f> next = it.next();
            if (next.get() == fVar || next.get() == null) {
                it.remove();
            }
        }
        Iterator<f> it2 = this.f1815c.iterator();
        while (it2.hasNext()) {
            if (it2.next() == fVar) {
                it2.remove();
            }
        }
    }

    private void b(@NonNull Activity activity, @NonNull String[] strArr, @Nullable f fVar) {
        boolean zA;
        for (String str : strArr) {
            if (fVar != null) {
                try {
                    if (!this.f1813a.contains(str)) {
                        zA = fVar.a(str, d.NOT_FOUND);
                    } else if (c.a(activity, str) != 0) {
                        zA = fVar.a(str, d.DENIED);
                    } else {
                        zA = fVar.a(str, d.GRANTED);
                    }
                    if (zA) {
                        break;
                    }
                } catch (Throwable th) {
                    th.printStackTrace();
                }
            }
        }
        a(fVar);
    }

    public synchronized boolean a(@Nullable Context context, @NonNull String str) {
        if (context == null) {
            return false;
        }
        if (t.t()) {
            if (b.b(context, str) && (c.a(context, str) == 0 || !this.f1813a.contains(str))) {
                z = true;
            }
            return z;
        }
        return c.a(context, str) == 0 || !this.f1813a.contains(str);
    }

    public synchronized void a(@Nullable Activity activity, @NonNull String[] strArr, @Nullable f fVar) {
        if (activity == null) {
            return;
        }
        try {
            a(strArr, fVar);
            if (Build.VERSION.SDK_INT < 23) {
                b(activity, strArr, fVar);
            } else {
                List<String> listC = c(activity, strArr, fVar);
                if (listC.isEmpty()) {
                    a(fVar);
                } else {
                    c.a(activity, (String[]) listC.toArray(new String[listC.size()]), 1);
                }
            }
        } finally {
        }
    }

    public synchronized void a(@NonNull Activity activity, @NonNull String[] strArr, @NonNull int[] iArr) {
        try {
            new ArrayList(3);
            int length = strArr.length;
            for (int i = 0; i < length; i++) {
                String str = strArr[i];
                if ((iArr[i] == -1 || (t.t() && !b.b(activity, str))) && iArr[i] != -1) {
                    iArr[i] = -1;
                }
            }
            a(strArr, iArr, (String[]) null);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    private void a(@NonNull String[] strArr, @NonNull int[] iArr, String[] strArr2) {
        int i;
        try {
            int length = strArr.length;
            if (iArr.length < length) {
                length = iArr.length;
            }
            Iterator<WeakReference<f>> it = this.f1814b.iterator();
            while (it.hasNext()) {
                f fVar = it.next().get();
                while (i < length) {
                    i = (fVar == null || fVar.a(strArr[i], iArr[i])) ? 0 : i + 1;
                    it.remove();
                    break;
                }
            }
            Iterator<f> it2 = this.f1815c.iterator();
            while (it2.hasNext()) {
                it2.next();
                it2.remove();
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }
}
