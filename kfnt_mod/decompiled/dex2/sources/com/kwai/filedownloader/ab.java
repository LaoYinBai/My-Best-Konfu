package com.kwai.filedownloader;

import android.os.Handler;
import android.util.SparseArray;
import java.util.Iterator;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
class ab implements w {
    private final SparseArray<Handler> a = new SparseArray<>();

    ab() {
    }

    private void a(Handler handler) {
        handler.sendEmptyMessage(2);
    }

    private void b(Handler handler) {
        handler.sendEmptyMessage(3);
    }

    @Override // com.kwai.filedownloader.w
    public void a() {
        for (int i = 0; i < this.a.size(); i++) {
            a(this.a.get(this.a.keyAt(i)));
        }
    }

    @Override // com.kwai.filedownloader.w
    public void a(List<Integer> list) {
        Iterator<Integer> it = list.iterator();
        while (it.hasNext()) {
            b(this.a.get(it.next().intValue()));
        }
    }

    @Override // com.kwai.filedownloader.w
    public boolean a(int i) {
        return this.a.get(i) != null;
    }

    @Override // com.kwai.filedownloader.w
    public int b() {
        return this.a.size();
    }
}
