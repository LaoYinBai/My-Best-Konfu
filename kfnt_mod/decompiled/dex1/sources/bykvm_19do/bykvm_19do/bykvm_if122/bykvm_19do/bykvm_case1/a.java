package bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_case1;

import android.text.TextUtils;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class a {

    /* renamed from: a, reason: collision with root package name */
    private static final Map<String, InterfaceC0061a> f1808a = Collections.synchronizedMap(new HashMap());

    /* renamed from: bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_case1.a$a, reason: collision with other inner class name */
    public interface InterfaceC0061a {
        void a();

        void a(String str);
    }

    public static void a(String str) {
        InterfaceC0061a interfaceC0061aB;
        if (TextUtils.isEmpty(str) || (interfaceC0061aB = b(str)) == null) {
            return;
        }
        interfaceC0061aB.a();
    }

    private static InterfaceC0061a b(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return f1808a.remove(str);
    }

    public static void a(String str, String str2) {
        InterfaceC0061a interfaceC0061aB;
        if (TextUtils.isEmpty(str) || (interfaceC0061aB = b(str)) == null) {
            return;
        }
        interfaceC0061aB.a(str2);
    }
}
