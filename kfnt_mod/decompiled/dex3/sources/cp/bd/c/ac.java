package cp.bd.c;

import android.content.Context;
import android.os.Process;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public final class ac {
    public static boolean a(String... strArr) {
        try {
            Context context = c.a().a;
            for (int i = 0; i <= 0; i++) {
                if (context.checkPermission(strArr[0], Process.myPid(), Process.myUid()) != 0) {
                    return false;
                }
            }
            return true;
        } catch (Throwable unused) {
            return false;
        }
    }
}
