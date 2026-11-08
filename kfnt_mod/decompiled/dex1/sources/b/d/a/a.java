package b.d.a;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public interface a extends IInterface {

    /* renamed from: b.d.a.a$a, reason: collision with other inner class name */
    public static abstract class AbstractBinderC0036a extends Binder implements a {

        /* renamed from: b.d.a.a$a$a, reason: collision with other inner class name */
        public static class C0037a implements a {

            /* renamed from: a, reason: collision with root package name */
            public IBinder f1231a;

            public C0037a(IBinder iBinder) {
                this.f1231a = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.f1231a;
            }
        }

        public static a a(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.heytap.openid.IOpenID");
            return (iInterfaceQueryLocalInterface == null || !(iInterfaceQueryLocalInterface instanceof a)) ? new C0037a(iBinder) : (a) iInterfaceQueryLocalInterface;
        }
    }
}
