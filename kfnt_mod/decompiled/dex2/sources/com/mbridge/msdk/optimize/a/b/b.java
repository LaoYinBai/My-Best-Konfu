package com.mbridge.msdk.optimize.a.b;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* compiled from: LenovoIDInterface.java */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public interface b extends IInterface {
    String a();

    String a(String str);

    String b();

    String b(String str);

    boolean c();

    /* compiled from: LenovoIDInterface.java */
    public static abstract class a extends Binder implements b {

        /* compiled from: LenovoIDInterface.java */
        /* renamed from: com.mbridge.msdk.optimize.a.b.b$a$a, reason: collision with other inner class name */
        public static class C0128a implements b {
            private IBinder a;

            @Override // android.os.IInterface
            public final IBinder asBinder() {
                return null;
            }

            public C0128a(IBinder iBinder) {
                this.a = iBinder;
            }

            @Override // com.mbridge.msdk.optimize.a.b.b
            public final String a() {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    try {
                        parcelObtain.writeInterfaceToken("com.zui.deviceidservice.IDeviceidInterface");
                        this.a.transact(1, parcelObtain, parcelObtain2, 0);
                        parcelObtain2.readException();
                        return parcelObtain2.readString();
                    } catch (Exception e) {
                        e.printStackTrace();
                        parcelObtain2.recycle();
                        parcelObtain.recycle();
                        return null;
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.mbridge.msdk.optimize.a.b.b
            public final String a(String str) {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    try {
                        parcelObtain.writeInterfaceToken("com.zui.deviceidservice.IDeviceidInterface");
                        this.a.transact(4, parcelObtain, parcelObtain2, 0);
                        parcelObtain2.readException();
                        return parcelObtain2.readString();
                    } catch (Exception e) {
                        e.printStackTrace();
                        parcelObtain2.recycle();
                        parcelObtain.recycle();
                        return null;
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.mbridge.msdk.optimize.a.b.b
            public final String b() {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    try {
                        parcelObtain.writeInterfaceToken("com.zui.deviceidservice.IDeviceidInterface");
                        this.a.transact(2, parcelObtain, parcelObtain2, 0);
                        parcelObtain2.readException();
                        return parcelObtain2.readString();
                    } catch (Exception e) {
                        e.printStackTrace();
                        parcelObtain2.recycle();
                        parcelObtain.recycle();
                        return null;
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.mbridge.msdk.optimize.a.b.b
            public final String b(String str) {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    try {
                        parcelObtain.writeInterfaceToken("com.zui.deviceidservice.IDeviceidInterface");
                        this.a.transact(5, parcelObtain, parcelObtain2, 0);
                        parcelObtain2.readException();
                        return parcelObtain2.readString();
                    } catch (Exception e) {
                        e.printStackTrace();
                        parcelObtain2.recycle();
                        parcelObtain.recycle();
                        return null;
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.mbridge.msdk.optimize.a.b.b
            public final boolean c() {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.zui.deviceidservice.IDeviceidInterface");
                    this.a.transact(3, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    z = parcelObtain2.readInt() != 0;
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                } catch (Throwable unused) {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
                return z;
            }
        }

        @Override // android.os.Binder
        protected boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i != 1598968902) {
                switch (i) {
                    case 1:
                        parcel.enforceInterface("com.zui.deviceidservice.IDeviceidInterface");
                        String strA = a();
                        parcel2.writeNoException();
                        parcel2.writeString(strA);
                        return true;
                    case 2:
                        parcel.enforceInterface("com.zui.deviceidservice.IDeviceidInterface");
                        String strB = b();
                        parcel2.writeNoException();
                        parcel2.writeString(strB);
                        return true;
                    case 3:
                        parcel.enforceInterface("com.zui.deviceidservice.IDeviceidInterface");
                        boolean zC = c();
                        parcel2.writeNoException();
                        parcel2.writeInt(zC ? 1 : 0);
                        return true;
                    case 4:
                        parcel.enforceInterface("com.zui.deviceidservice.IDeviceidInterface");
                        String strA2 = a(parcel.readString());
                        parcel2.writeNoException();
                        parcel2.writeString(strA2);
                        return true;
                    case 5:
                        parcel.enforceInterface("com.zui.deviceidservice.IDeviceidInterface");
                        String strB2 = b(parcel.readString());
                        parcel2.writeNoException();
                        parcel2.writeString(strB2);
                        return true;
                    default:
                        return super.onTransact(i, parcel, parcel2, i2);
                }
            }
            parcel2.writeString("com.zui.deviceidservice.IDeviceidInterface");
            return true;
        }
    }
}
