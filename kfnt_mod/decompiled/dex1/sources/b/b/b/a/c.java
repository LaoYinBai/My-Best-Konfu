package b.b.b.a;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public interface c extends IInterface {

    public static abstract class a extends Binder implements c {

        /* renamed from: b.b.b.a.c$a$a, reason: collision with other inner class name */
        public static class C0031a implements c {

            /* renamed from: a, reason: collision with root package name */
            public IBinder f1156a;

            public C0031a(IBinder iBinder) {
                this.f1156a = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.f1156a;
            }
        }

        public static c a(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.asus.msa.SupplementaryDID.IDidAidlInterface");
            return (iInterfaceQueryLocalInterface == null || !(iInterfaceQueryLocalInterface instanceof c)) ? new C0031a(iBinder) : (c) iInterfaceQueryLocalInterface;
        }
    }
}
