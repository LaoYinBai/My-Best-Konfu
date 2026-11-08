package com.kwai.filedownloader.services;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;
import com.kwai.filedownloader.event.DownloadServiceConnectChangedEvent;
import com.kwai.filedownloader.u;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public abstract class a<CALLBACK extends Binder, INTERFACE extends IInterface> implements ServiceConnection, u {
    private volatile INTERFACE b;
    private final Class<?> c;
    private final HashMap<String, Object> d = new HashMap<>();
    private final List<Context> e = new ArrayList();
    private final ArrayList<Runnable> f = new ArrayList<>();
    private final CALLBACK a = (CALLBACK) b();

    protected a(Class<?> cls) {
        this.c = cls;
    }

    private void a(boolean z) {
        if (!z && this.b != null) {
            try {
                a((a<CALLBACK, INTERFACE>) this.b, (INTERFACE) this.a);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        if (com.kwai.filedownloader.e.d.a) {
            com.kwai.filedownloader.e.d.c(this, "release connect resources %s", this.b);
        }
        this.b = null;
        com.kwai.filedownloader.f.a().b(new DownloadServiceConnectChangedEvent(z ? DownloadServiceConnectChangedEvent.ConnectStatus.lost : DownloadServiceConnectChangedEvent.ConnectStatus.disconnected, this.c));
    }

    @Override // com.kwai.filedownloader.u
    public void a(Context context) {
        a(context, (Runnable) null);
    }

    public void a(Context context, Runnable runnable) {
        if (com.kwai.filedownloader.e.f.a(context)) {
            throw new IllegalStateException("Fatal-Exception: You can't bind the FileDownloadService in :filedownloader process.\n It's the invalid operation and is likely to cause unexpected problems.\n Maybe you want to use non-separate process mode for FileDownloader, More detail about non-separate mode, please move to wiki manually: https://github.com/lingochamp/FileDownloader/wiki/filedownloader.properties");
        }
        if (com.kwai.filedownloader.e.d.a) {
            com.kwai.filedownloader.e.d.c(this, "bindStartByContext %s", context.getClass().getSimpleName());
        }
        Intent intent = new Intent(context, this.c);
        if (runnable != null && !this.f.contains(runnable)) {
            this.f.add(runnable);
        }
        if (!this.e.contains(context)) {
            this.e.add(context);
        }
        context.bindService(intent, this, 1);
        context.startService(intent);
    }

    protected abstract void a(INTERFACE r1, CALLBACK callback);

    protected abstract CALLBACK b();

    protected abstract INTERFACE b(IBinder iBinder);

    protected abstract void b(INTERFACE r1, CALLBACK callback);

    @Override // com.kwai.filedownloader.u
    public boolean c() {
        return d() != null;
    }

    protected INTERFACE d() {
        return this.b;
    }

    @Override // android.content.ServiceConnection
    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        this.b = (INTERFACE) b(iBinder);
        if (com.kwai.filedownloader.e.d.a) {
            com.kwai.filedownloader.e.d.c(this, "onServiceConnected %s %s", componentName, this.b);
        }
        try {
            b(this.b, this.a);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        List list = (List) this.f.clone();
        this.f.clear();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            ((Runnable) it.next()).run();
        }
        com.kwai.filedownloader.f.a().b(new DownloadServiceConnectChangedEvent(DownloadServiceConnectChangedEvent.ConnectStatus.connected, this.c));
    }

    @Override // android.content.ServiceConnection
    public void onServiceDisconnected(ComponentName componentName) {
        if (com.kwai.filedownloader.e.d.a) {
            com.kwai.filedownloader.e.d.c(this, "onServiceDisconnected %s %s", componentName, this.b);
        }
        a(true);
    }
}
