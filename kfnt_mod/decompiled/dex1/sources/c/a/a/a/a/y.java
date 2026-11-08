package c.a.a.a.a;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class y {

    static class a implements p {
        a() {
        }

        @Override // c.a.a.a.a.p
        public void a(v vVar) {
        }

        @Override // c.a.a.a.a.p
        public void a(Exception exc) {
        }
    }

    public static int a() {
        return 15000;
    }

    public static v a(@NonNull String str, @NonNull String str2, @Nullable Map<String, Object> map, @Nullable byte[] bArr, @Nullable Map<String, Object> map2, @Nullable Map<String, Object> map3) {
        s sVarB = b(str, str2, map, bArr, map2, map3);
        sVarB.b();
        return sVarB.a();
    }

    public static void a(@NonNull String str, @NonNull String str2, @Nullable Map<String, Object> map, @Nullable byte[] bArr, @Nullable Map<String, Object> map2, @Nullable Map<String, Object> map3, @Nullable p pVar) {
        if (pVar == null) {
            pVar = new a();
        }
        try {
            new m(b(str, str2, map, bArr, map2, map3), pVar).start();
        } catch (Exception e) {
            pVar.a(e);
        }
    }

    public static int b() {
        return 15000;
    }

    private static s b(@NonNull String str, @NonNull String str2, @Nullable Map<String, Object> map, @Nullable byte[] bArr, @Nullable Map<String, Object> map2, @Nullable Map<String, Object> map3) {
        s sVar = new s();
        if (bArr != null) {
            sVar.a(str2, bArr, map, map3);
        } else {
            sVar.a(str, str2, map, map2, map3);
        }
        return sVar;
    }
}
