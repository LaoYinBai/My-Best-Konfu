package b.c.a;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public interface a extends IInterface {

    /* renamed from: b.c.a.a$a, reason: collision with other inner class name */
    public static abstract class AbstractBinderC0033a extends Binder implements a {

        /* renamed from: b.c.a.a$a$a, reason: collision with other inner class name */
        public static class C0034a implements a {

            /* renamed from: a, reason: collision with root package name */
            public IBinder f1216a;

            public C0034a(IBinder iBinder) {
                this.f1216a = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.f1216a;
            }
        }

        public static a a(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.bun.lib.MsaIdInterface");
            return (iInterfaceQueryLocalInterface == null || !(iInterfaceQueryLocalInterface instanceof a)) ? new C0034a(iBinder) : (a) iInterfaceQueryLocalInterface;
        }
    }
}
