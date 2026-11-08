package b.a.d;

import b.ac;
import b.v;
import java.net.Proxy;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public final class i {
    private i() {
    }

    public static String a(ac acVar, Proxy.Type type) {
        StringBuilder sb = new StringBuilder();
        sb.append(acVar.b());
        sb.append(' ');
        if (b(acVar, type)) {
            sb.append(acVar.a());
        } else {
            sb.append(a(acVar.a()));
        }
        sb.append(" HTTP/1.1");
        return sb.toString();
    }

    private static boolean b(ac acVar, Proxy.Type type) {
        return !acVar.h() && type == Proxy.Type.HTTP;
    }

    public static String a(v vVar) {
        String strL = vVar.l();
        String strO = vVar.o();
        if (strO == null) {
            return strL;
        }
        return strL + '?' + strO;
    }
}
