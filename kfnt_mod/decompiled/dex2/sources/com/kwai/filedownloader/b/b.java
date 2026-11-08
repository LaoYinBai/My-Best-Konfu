package com.kwai.filedownloader.b;

import android.app.Notification;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import com.kwai.filedownloader.b.a;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public interface b extends IInterface {

    public static abstract class a extends Binder implements b {

        /* renamed from: com.kwai.filedownloader.b.b$a$a, reason: collision with other inner class name */
        private static class C0094a implements b {
            public static b a;
            private IBinder b;

            C0094a(IBinder iBinder) {
                this.b = iBinder;
            }

            @Override // com.kwai.filedownloader.b.b
            public void a() {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.kwai.filedownloader.i.IFileDownloadIPCService");
                    if (this.b.transact(6, parcelObtain, parcelObtain2, 0) || a.d() == null) {
                        parcelObtain2.readException();
                    } else {
                        a.d().a();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.kwai.filedownloader.b.b
            public void a(int i, Notification notification) {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.kwai.filedownloader.i.IFileDownloadIPCService");
                    parcelObtain.writeInt(i);
                    if (notification != null) {
                        parcelObtain.writeInt(1);
                        notification.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.b.transact(12, parcelObtain, null, 1) || a.d() == null) {
                        return;
                    }
                    a.d().a(i, notification);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // com.kwai.filedownloader.b.b
            public void a(com.kwai.filedownloader.b.a aVar) {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.kwai.filedownloader.i.IFileDownloadIPCService");
                    parcelObtain.writeStrongBinder(aVar != null ? aVar.asBinder() : null);
                    if (this.b.transact(1, parcelObtain, null, 1) || a.d() == null) {
                        return;
                    }
                    a.d().a(aVar);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // com.kwai.filedownloader.b.b
            public void a(String str, String str2, boolean z, int i, int i2, int i3, boolean z2, com.kwai.filedownloader.c.b bVar, boolean z3) throws Throwable {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.kwai.filedownloader.i.IFileDownloadIPCService");
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    parcelObtain.writeInt(z ? 1 : 0);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeInt(i3);
                    parcelObtain.writeInt(z2 ? 1 : 0);
                    if (bVar != null) {
                        parcelObtain.writeInt(1);
                        bVar.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeInt(z3 ? 1 : 0);
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    if (this.b.transact(4, parcelObtain, parcelObtain2, 0) || a.d() == null) {
                        parcelObtain2.readException();
                        parcelObtain2.recycle();
                        parcelObtain.recycle();
                    } else {
                        a.d().a(str, str2, z, i, i2, i3, z2, bVar, z3);
                        parcelObtain2.recycle();
                        parcelObtain.recycle();
                    }
                } catch (Throwable th2) {
                    th = th2;
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                    throw th;
                }
            }

            @Override // com.kwai.filedownloader.b.b
            public void a(boolean z) {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.kwai.filedownloader.i.IFileDownloadIPCService");
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (this.b.transact(13, parcelObtain, null, 1) || a.d() == null) {
                        return;
                    }
                    a.d().a(z);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // com.kwai.filedownloader.b.b
            public boolean a(int i) {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.kwai.filedownloader.i.IFileDownloadIPCService");
                    parcelObtain.writeInt(i);
                    if (!this.b.transact(5, parcelObtain, parcelObtain2, 0) && a.d() != null) {
                        return a.d().a(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.kwai.filedownloader.b.b
            public boolean a(String str, String str2) {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.kwai.filedownloader.i.IFileDownloadIPCService");
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    if (!this.b.transact(3, parcelObtain, parcelObtain2, 0) && a.d() != null) {
                        return a.d().a(str, str2);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.b;
            }

            @Override // com.kwai.filedownloader.b.b
            public void b(com.kwai.filedownloader.b.a aVar) {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.kwai.filedownloader.i.IFileDownloadIPCService");
                    parcelObtain.writeStrongBinder(aVar != null ? aVar.asBinder() : null);
                    if (this.b.transact(2, parcelObtain, null, 1) || a.d() == null) {
                        return;
                    }
                    a.d().b(aVar);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // com.kwai.filedownloader.b.b
            public boolean b() {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.kwai.filedownloader.i.IFileDownloadIPCService");
                    if (!this.b.transact(11, parcelObtain, parcelObtain2, 0) && a.d() != null) {
                        return a.d().b();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.kwai.filedownloader.b.b
            public boolean b(int i) {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.kwai.filedownloader.i.IFileDownloadIPCService");
                    parcelObtain.writeInt(i);
                    if (!this.b.transact(7, parcelObtain, parcelObtain2, 0) && a.d() != null) {
                        return a.d().b(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.kwai.filedownloader.b.b
            public long c(int i) {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.kwai.filedownloader.i.IFileDownloadIPCService");
                    parcelObtain.writeInt(i);
                    if (!this.b.transact(8, parcelObtain, parcelObtain2, 0) && a.d() != null) {
                        return a.d().c(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readLong();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.kwai.filedownloader.b.b
            public void c() {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.kwai.filedownloader.i.IFileDownloadIPCService");
                    if (this.b.transact(15, parcelObtain, parcelObtain2, 0) || a.d() == null) {
                        parcelObtain2.readException();
                    } else {
                        a.d().c();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.kwai.filedownloader.b.b
            public long d(int i) {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.kwai.filedownloader.i.IFileDownloadIPCService");
                    parcelObtain.writeInt(i);
                    if (!this.b.transact(9, parcelObtain, parcelObtain2, 0) && a.d() != null) {
                        return a.d().d(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readLong();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.kwai.filedownloader.b.b
            public byte e(int i) {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.kwai.filedownloader.i.IFileDownloadIPCService");
                    parcelObtain.writeInt(i);
                    if (!this.b.transact(10, parcelObtain, parcelObtain2, 0) && a.d() != null) {
                        return a.d().e(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readByte();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.kwai.filedownloader.b.b
            public boolean f(int i) {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.kwai.filedownloader.i.IFileDownloadIPCService");
                    parcelObtain.writeInt(i);
                    if (!this.b.transact(14, parcelObtain, parcelObtain2, 0) && a.d() != null) {
                        return a.d().f(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public a() {
            attachInterface(this, "com.kwai.filedownloader.i.IFileDownloadIPCService");
        }

        public static b a(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.kwai.filedownloader.i.IFileDownloadIPCService");
            return (iInterfaceQueryLocalInterface == null || !(iInterfaceQueryLocalInterface instanceof b)) ? new C0094a(iBinder) : (b) iInterfaceQueryLocalInterface;
        }

        public static b d() {
            return C0094a.a;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
            if (i == 1598968902) {
                parcel2.writeString("com.kwai.filedownloader.i.IFileDownloadIPCService");
                return true;
            }
            switch (i) {
                case 1:
                    parcel.enforceInterface("com.kwai.filedownloader.i.IFileDownloadIPCService");
                    a(a.AbstractBinderC0092a.a(parcel.readStrongBinder()));
                    return true;
                case 2:
                    parcel.enforceInterface("com.kwai.filedownloader.i.IFileDownloadIPCService");
                    b(a.AbstractBinderC0092a.a(parcel.readStrongBinder()));
                    return true;
                case 3:
                    parcel.enforceInterface("com.kwai.filedownloader.i.IFileDownloadIPCService");
                    boolean zA = a(parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(zA ? 1 : 0);
                    return true;
                case 4:
                    parcel.enforceInterface("com.kwai.filedownloader.i.IFileDownloadIPCService");
                    a(parcel.readString(), parcel.readString(), parcel.readInt() != 0, parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readInt() != 0, parcel.readInt() != 0 ? com.kwai.filedownloader.c.b.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                case 5:
                    parcel.enforceInterface("com.kwai.filedownloader.i.IFileDownloadIPCService");
                    boolean zA2 = a(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(zA2 ? 1 : 0);
                    return true;
                case 6:
                    parcel.enforceInterface("com.kwai.filedownloader.i.IFileDownloadIPCService");
                    a();
                    parcel2.writeNoException();
                    return true;
                case 7:
                    parcel.enforceInterface("com.kwai.filedownloader.i.IFileDownloadIPCService");
                    boolean zB = b(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(zB ? 1 : 0);
                    return true;
                case 8:
                    parcel.enforceInterface("com.kwai.filedownloader.i.IFileDownloadIPCService");
                    long jC = c(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeLong(jC);
                    return true;
                case 9:
                    parcel.enforceInterface("com.kwai.filedownloader.i.IFileDownloadIPCService");
                    long jD = d(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeLong(jD);
                    return true;
                case 10:
                    parcel.enforceInterface("com.kwai.filedownloader.i.IFileDownloadIPCService");
                    byte bE = e(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeByte(bE);
                    return true;
                case 11:
                    parcel.enforceInterface("com.kwai.filedownloader.i.IFileDownloadIPCService");
                    boolean zB2 = b();
                    parcel2.writeNoException();
                    parcel2.writeInt(zB2 ? 1 : 0);
                    return true;
                case 12:
                    parcel.enforceInterface("com.kwai.filedownloader.i.IFileDownloadIPCService");
                    a(parcel.readInt(), parcel.readInt() != 0 ? (Notification) Notification.CREATOR.createFromParcel(parcel) : null);
                    return true;
                case 13:
                    parcel.enforceInterface("com.kwai.filedownloader.i.IFileDownloadIPCService");
                    a(parcel.readInt() != 0);
                    return true;
                case 14:
                    parcel.enforceInterface("com.kwai.filedownloader.i.IFileDownloadIPCService");
                    boolean zF = f(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(zF ? 1 : 0);
                    return true;
                case 15:
                    parcel.enforceInterface("com.kwai.filedownloader.i.IFileDownloadIPCService");
                    c();
                    parcel2.writeNoException();
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }
    }

    void a();

    void a(int i, Notification notification);

    void a(com.kwai.filedownloader.b.a aVar);

    void a(String str, String str2, boolean z, int i, int i2, int i3, boolean z2, com.kwai.filedownloader.c.b bVar, boolean z3);

    void a(boolean z);

    boolean a(int i);

    boolean a(String str, String str2);

    void b(com.kwai.filedownloader.b.a aVar);

    boolean b();

    boolean b(int i);

    long c(int i);

    void c();

    long d(int i);

    byte e(int i);

    boolean f(int i);
}
