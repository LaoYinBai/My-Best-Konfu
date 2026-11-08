package anetwork.channel.entity;

import android.os.RemoteException;
import anet.channel.bytes.ByteArray;
import anet.channel.util.ALog;
import anetwork.channel.aidl.DefaultFinishEvent;
import java.util.List;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class d implements anetwork.channel.f.a {

    /* renamed from: a, reason: collision with root package name */
    private anetwork.channel.aidl.g f687a;

    /* renamed from: b, reason: collision with root package name */
    private String f688b;

    /* renamed from: c, reason: collision with root package name */
    private anetwork.channel.aidl.a.d f689c = null;
    private boolean d;
    private h e;

    public d(anetwork.channel.aidl.g gVar, h hVar) {
        this.d = false;
        this.e = null;
        this.f687a = gVar;
        this.e = hVar;
        if (gVar != null) {
            try {
                if ((gVar.a() & 8) != 0) {
                    this.d = true;
                }
            } catch (RemoteException unused) {
            }
        }
    }

    @Override // anetwork.channel.f.a
    public void a(int i, Map<String, List<String>> map) {
        if (ALog.isPrintLog(2)) {
            ALog.i("anet.Repeater", "[onResponseCode]", this.f688b, new Object[0]);
        }
        if (this.f687a != null) {
            a(new e(this, this.f687a, i, map));
        }
    }

    @Override // anetwork.channel.f.a
    public void a(int i, int i2, ByteArray byteArray) {
        if (this.f687a != null) {
            a(new f(this, i, byteArray, i2, this.f687a));
        }
    }

    @Override // anetwork.channel.f.a
    public void a(DefaultFinishEvent defaultFinishEvent) {
        if (ALog.isPrintLog(2)) {
            ALog.i("anet.Repeater", "[onFinish] ", this.f688b, new Object[0]);
        }
        if (this.f687a != null) {
            a(new g(this, defaultFinishEvent, this.f687a));
        }
        this.f687a = null;
    }

    private void a(Runnable runnable) {
        if (this.e.c()) {
            runnable.run();
        } else {
            b.a(this.f688b != null ? this.f688b.hashCode() : hashCode(), runnable);
        }
    }

    public void a(String str) {
        this.f688b = str;
    }
}
