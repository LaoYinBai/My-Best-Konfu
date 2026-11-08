package com.kwai.filedownloader.services;

import android.app.Notification;
import android.content.Intent;
import android.os.IBinder;
import com.kwai.filedownloader.b.b;
import com.kwai.filedownloader.n;
import java.lang.ref.WeakReference;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class e extends b.a implements i {
    private final g a;
    private final WeakReference<FileDownloadServiceProxy> b;

    public interface a {
        void a();

        void a(e eVar);
    }

    e(WeakReference<FileDownloadServiceProxy> weakReference, g gVar) {
        this.b = weakReference;
        this.a = gVar;
    }

    @Override // com.kwai.filedownloader.services.i
    public IBinder a(Intent intent) {
        return null;
    }

    @Override // com.kwai.filedownloader.b.b
    public void a() {
        this.a.a();
    }

    @Override // com.kwai.filedownloader.b.b
    public void a(int i, Notification notification) {
        if (this.b == null || this.b.get() == null) {
            return;
        }
        this.b.get().context.startForeground(i, notification);
    }

    @Override // com.kwai.filedownloader.services.i
    public void a(Intent intent, int i, int i2) {
        n.b().a(this);
    }

    @Override // com.kwai.filedownloader.b.b
    public void a(com.kwai.filedownloader.b.a aVar) {
    }

    @Override // com.kwai.filedownloader.b.b
    public void a(String str, String str2, boolean z, int i, int i2, int i3, boolean z2, com.kwai.filedownloader.c.b bVar, boolean z3) {
        this.a.a(str, str2, z, i, i2, i3, z2, bVar, z3);
    }

    @Override // com.kwai.filedownloader.b.b
    public void a(boolean z) {
        if (this.b == null || this.b.get() == null) {
            return;
        }
        this.b.get().context.stopForeground(z);
    }

    @Override // com.kwai.filedownloader.b.b
    public boolean a(int i) {
        return this.a.b(i);
    }

    @Override // com.kwai.filedownloader.b.b
    public boolean a(String str, String str2) {
        return this.a.a(str, str2);
    }

    @Override // com.kwai.filedownloader.b.b
    public void b(com.kwai.filedownloader.b.a aVar) {
    }

    @Override // com.kwai.filedownloader.b.b
    public boolean b() {
        return this.a.b();
    }

    @Override // com.kwai.filedownloader.b.b
    public boolean b(int i) {
        return this.a.f(i);
    }

    @Override // com.kwai.filedownloader.b.b
    public long c(int i) {
        return this.a.c(i);
    }

    @Override // com.kwai.filedownloader.b.b
    public void c() {
        this.a.c();
    }

    @Override // com.kwai.filedownloader.b.b
    public long d(int i) {
        return this.a.d(i);
    }

    @Override // com.kwai.filedownloader.b.b
    public byte e(int i) {
        return this.a.e(i);
    }

    @Override // com.kwai.filedownloader.services.i
    public void e() {
        n.b().a();
    }

    @Override // com.kwai.filedownloader.b.b
    public boolean f(int i) {
        return this.a.g(i);
    }
}
