package com.mbridge.msdk.mbdownload.manager;

import com.mbridge.msdk.out.IDownloadListener;
import java.util.Observable;
import java.util.Observer;

/* compiled from: ApkDownloadTaskObserver.java */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public final class b implements Observer {
    private boolean a = true;
    private IDownloadListener b;

    public b(IDownloadListener iDownloadListener) {
        this.b = iDownloadListener;
    }

    public final void a(IDownloadListener iDownloadListener) {
        this.b = iDownloadListener;
    }

    @Override // java.util.Observer
    public final void update(Observable observable, Object obj) {
        try {
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
        if (this.b != null) {
            if (obj != null) {
                c cVar = (c) obj;
                int iB = cVar.b();
                if (iB == -1) {
                    this.b.onStatus(-1);
                    return;
                }
                if (iB == 5) {
                    this.b.onStatus(5);
                    return;
                }
                switch (iB) {
                    case 1:
                        this.b.onEnd(1, 0, cVar.a());
                        this.b.onStatus(1);
                        break;
                    case 2:
                        this.b.onStatus(2);
                        if (this.a) {
                            this.b.onStart();
                            this.a = false;
                        }
                        this.b.onProgressUpdate(cVar.c());
                        break;
                    default:
                        switch (iB) {
                            case 8:
                                this.b.onStatus(8);
                                break;
                            case 9:
                                this.b.onStatus(9);
                                break;
                            default:
                                this.b.onStatus(cVar.b());
                                break;
                        }
                }
                return;
            }
            this.b.onStatus(8);
        }
    }
}
