package com.xiaomi.push;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.IBinder;
import android.os.Looper;
import android.os.Parcel;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
class ap implements ar {
    private static boolean a;

    /* renamed from: a, reason: collision with other field name */
    private Context f119a;

    /* renamed from: a, reason: collision with other field name */
    private ServiceConnection f120a;

    /* renamed from: a, reason: collision with other field name */
    private volatile int f118a = 0;

    /* renamed from: a, reason: collision with other field name */
    private volatile String f122a = null;

    /* renamed from: b, reason: collision with other field name */
    private volatile boolean f123b = false;
    private volatile String b = null;

    /* renamed from: a, reason: collision with other field name */
    private final Object f121a = new Object();

    private class a implements ServiceConnection {
        private a() {
        }

        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            try {
                ap.this.f122a = b.a(iBinder);
                ap.this.f123b = b.m103a(iBinder);
                ap.this.b();
                ap.this.f118a = 2;
                synchronized (ap.this.f121a) {
                    try {
                        ap.this.f121a.notifyAll();
                    } catch (Exception unused) {
                    }
                }
            } catch (Exception unused2) {
                ap.this.b();
                ap.this.f118a = 2;
                synchronized (ap.this.f121a) {
                    try {
                        ap.this.f121a.notifyAll();
                    } catch (Exception unused3) {
                    }
                }
            } catch (Throwable th) {
                ap.this.b();
                ap.this.f118a = 2;
                synchronized (ap.this.f121a) {
                    try {
                        ap.this.f121a.notifyAll();
                    } catch (Exception unused4) {
                    }
                    throw th;
                }
            }
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
        }
    }

    private static class b {
        static String a(IBinder iBinder) {
            Parcel parcelObtain = Parcel.obtain();
            Parcel parcelObtain2 = Parcel.obtain();
            try {
                parcelObtain.writeInterfaceToken("com.uodis.opendevice.aidl.OpenDeviceIdentifierService");
                iBinder.transact(1, parcelObtain, parcelObtain2, 0);
                parcelObtain2.readException();
                return parcelObtain2.readString();
            } finally {
                parcelObtain2.recycle();
                parcelObtain.recycle();
            }
        }

        /* renamed from: a, reason: collision with other method in class */
        static boolean m103a(IBinder iBinder) {
            Parcel parcelObtain = Parcel.obtain();
            Parcel parcelObtain2 = Parcel.obtain();
            try {
                parcelObtain.writeInterfaceToken("com.uodis.opendevice.aidl.OpenDeviceIdentifierService");
                iBinder.transact(2, parcelObtain, parcelObtain2, 0);
                parcelObtain2.readException();
                return parcelObtain2.readInt() != 0;
            } finally {
                parcelObtain2.recycle();
                parcelObtain.recycle();
            }
        }
    }

    public ap(Context context) {
        this.f119a = context;
        a();
    }

    private static String a(Context context) {
        String string;
        String string2;
        try {
            if (Build.VERSION.SDK_INT >= 24) {
                string2 = context.createDeviceProtectedStorageContext().getSharedPreferences("aaid", 0).getString("aaid", null);
                if (string2 != null) {
                    return string2;
                }
            } else {
                string2 = null;
            }
            try {
                string = context.getSharedPreferences("aaid", 0).getString("aaid", null);
            } catch (Exception unused) {
                string = string2;
            }
        } catch (Exception unused2) {
            string = null;
        }
        return string == null ? "" : string;
    }

    private void a() {
        boolean zBindService;
        this.f120a = new a();
        Intent intent = new Intent("com.uodis.opendevice.OPENIDS_SERVICE");
        intent.setPackage("com.huawei.hwid");
        try {
            zBindService = this.f119a.bindService(intent, this.f120a, 1);
        } catch (Exception unused) {
            zBindService = false;
        }
        this.f118a = zBindService ? 1 : 2;
    }

    private void a(String str) {
        if (this.f118a != 1 || Looper.myLooper() == Looper.getMainLooper()) {
            return;
        }
        synchronized (this.f121a) {
            try {
                com.xiaomi.channel.commonutils.logger.b.m22a("huawei's " + str + " wait...");
                this.f121a.wait(3000L);
            } catch (Exception unused) {
            }
        }
    }

    /* renamed from: a, reason: collision with other method in class */
    public static boolean m99a(Context context) throws PackageManager.NameNotFoundException {
        boolean z;
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo("com.huawei.hwid", 128);
            z = (packageInfo.applicationInfo.flags & 1) != 0;
            a = packageInfo.versionCode >= 20602000;
        } catch (Exception unused) {
        }
        return z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b() {
        if (this.f120a != null) {
            try {
                this.f119a.unbindService(this.f120a);
            } catch (Exception unused) {
            }
        }
    }

    @Override // com.xiaomi.push.ar
    /* renamed from: a, reason: collision with other method in class */
    public String mo100a() {
        return null;
    }

    @Override // com.xiaomi.push.ar
    /* renamed from: a, reason: collision with other method in class */
    public boolean mo101a() {
        return a;
    }

    @Override // com.xiaomi.push.ar
    /* renamed from: b, reason: collision with other method in class */
    public String mo102b() {
        a("getOAID");
        return this.f122a;
    }

    @Override // com.xiaomi.push.ar
    public String c() {
        return null;
    }

    @Override // com.xiaomi.push.ar
    public String d() {
        if (this.b == null) {
            synchronized (this) {
                if (this.b == null) {
                    this.b = a(this.f119a);
                }
            }
        }
        return this.b;
    }
}
