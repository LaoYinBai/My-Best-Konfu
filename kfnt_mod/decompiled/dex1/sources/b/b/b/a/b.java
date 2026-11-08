package b.b.b.a;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public interface b extends IInterface {

    public static abstract class a extends Binder implements b {

        /* renamed from: b.b.b.a.b$a$a, reason: collision with other inner class name */
        public static class C0030a implements b {

            /* renamed from: a, reason: collision with root package name */
            public IBinder f1155a;

            public C0030a(IBinder iBinder) {
                this.f1155a = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.f1155a;
            }
        }

        public static b a(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.zui.deviceidservice.IDeviceidInterface");
            return (iInterfaceQueryLocalInterface == null || !(iInterfaceQueryLocalInterface instanceof b)) ? new C0030a(iBinder) : (b) iInterfaceQueryLocalInterface;
        }
    }
}
