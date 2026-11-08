package cp.bd.c;

import android.util.SparseArray;
import com.bytedance.JProtect;
import com.bytedance.mobsec.matrix.MSProxy;

@JProtect
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public final class d {
    private static SparseArray<a> a = new SparseArray<>();

    public static abstract class a {
        public Object a(int i, int i2, long j, String str, Object obj) {
            return a(str, obj);
        }

        protected Object a(String str, Object obj) {
            return null;
        }
    }

    public static Object a(int i, int i2, long j, String str, Object obj) {
        return MSProxy.a(i, i2, j, str, obj);
    }

    public static Object a(Object obj) {
        return a(16777217, 0, 0L, null, obj);
    }

    public static void a(int i, a aVar) {
        a aVar2 = a.get(i);
        if (aVar2 != null) {
            throw new RuntimeException(String.format("%s had been register.", aVar2.toString()));
        }
        a.put(i, aVar);
    }

    public static Object b(int i, int i2, long j, String str, Object obj) {
        a aVar = a.get(i);
        if (aVar == null) {
            throw new RuntimeException(String.format("op code %d is invalid", Integer.valueOf(i)));
        }
        try {
            return aVar.a(i, i2, j, str, obj);
        } catch (Throwable th) {
            throw new RuntimeException(th);
        }
    }
}
