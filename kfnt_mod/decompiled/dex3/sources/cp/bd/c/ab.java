package cp.bd.c;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public final class ab {
    public static boolean a(String... strArr) throws ClassNotFoundException {
        boolean z;
        boolean z2 = false;
        for (int i = 0; i <= 0; i++) {
            try {
                Class.forName(strArr[0]);
                z = true;
            } catch (Exception unused) {
                z = false;
            }
            z2 = z & true;
            if (!z2) {
                break;
            }
        }
        return z2;
    }
}
