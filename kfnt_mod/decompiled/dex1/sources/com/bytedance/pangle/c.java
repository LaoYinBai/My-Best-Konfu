package com.bytedance.pangle;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public interface c extends IInterface {
    boolean a(String str);

    int b(String str);

    boolean c(String str);

    public static abstract class a extends Binder implements c {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public a() {
            attachInterface(this, "com.bytedance.pangle.IPackageManager");
        }

        public static c a(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.bytedance.pangle.IPackageManager");
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof c)) {
                return (c) iInterfaceQueryLocalInterface;
            }
            return new C0133a(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
            if (i == 1598968902) {
                parcel2.writeString("com.bytedance.pangle.IPackageManager");
                return true;
            }
            switch (i) {
                case 1:
                    parcel.enforceInterface("com.bytedance.pangle.IPackageManager");
                    boolean zA = a(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(zA ? 1 : 0);
                    return true;
                case 2:
                    parcel.enforceInterface("com.bytedance.pangle.IPackageManager");
                    int iB = b(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(iB);
                    return true;
                case 3:
                    parcel.enforceInterface("com.bytedance.pangle.IPackageManager");
                    boolean zC = c(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(zC ? 1 : 0);
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        /* renamed from: com.bytedance.pangle.c$a$a, reason: collision with other inner class name */
        static class C0133a implements c {

            /* renamed from: a, reason: collision with root package name */
            public static c f4533a;

            /* renamed from: b, reason: collision with root package name */
            private IBinder f4534b;

            C0133a(IBinder iBinder) {
                this.f4534b = iBinder;
            }

            @Override // android.os.IInterface
            public final IBinder asBinder() {
                return this.f4534b;
            }

            @Override // com.bytedance.pangle.c
            public final boolean a(String str) {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.bytedance.pangle.IPackageManager");
                    parcelObtain.writeString(str);
                    if (!this.f4534b.transact(1, parcelObtain, parcelObtain2, 0) && a.a() != null) {
                        return a.a().a(str);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.bytedance.pangle.c
            public final int b(String str) {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.bytedance.pangle.IPackageManager");
                    parcelObtain.writeString(str);
                    if (!this.f4534b.transact(2, parcelObtain, parcelObtain2, 0) && a.a() != null) {
                        return a.a().b(str);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.bytedance.pangle.c
            public final boolean c(String str) {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.bytedance.pangle.IPackageManager");
                    parcelObtain.writeString(str);
                    if (!this.f4534b.transact(3, parcelObtain, parcelObtain2, 0) && a.a() != null) {
                        return a.a().c(str);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public static c a() {
            return C0133a.f4533a;
        }
    }
}
