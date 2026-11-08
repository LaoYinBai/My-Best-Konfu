package b.b.b;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public interface u extends IInterface {

    public static abstract class a extends Binder implements u {

        /* renamed from: b.b.b.u$a$a, reason: collision with other inner class name */
        public static class C0032a implements u {

            /* renamed from: a, reason: collision with root package name */
            public IBinder f1189a;

            public C0032a(IBinder iBinder) {
                this.f1189a = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.f1189a;
            }
        }

        public static u a(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.uodis.opendevice.aidl.OpenDeviceIdentifierService");
            return (iInterfaceQueryLocalInterface == null || !(iInterfaceQueryLocalInterface instanceof u)) ? new C0032a(iBinder) : (u) iInterfaceQueryLocalInterface;
        }
    }
}
