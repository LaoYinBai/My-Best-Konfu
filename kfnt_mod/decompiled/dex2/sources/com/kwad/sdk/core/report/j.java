package com.kwad.sdk.core.report;

import androidx.annotation.NonNull;
import com.kwad.sdk.core.report.c;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class j<T extends c> implements h<T> {
    private Map<String, T> a = new LinkedHashMap();

    @Override // com.kwad.sdk.core.report.h
    public synchronized long a() {
        int size;
        size = this.a.size();
        com.kwad.sdk.core.d.a.a("MemReportCache", "size() = " + size);
        return size;
    }

    @Override // com.kwad.sdk.core.report.h
    public synchronized void a(@NonNull T t) {
        this.a.put(t.a, t);
    }

    @Override // com.kwad.sdk.core.report.h
    public synchronized void a(List<T> list) {
        Iterator<T> it = list.iterator();
        while (it.hasNext()) {
            this.a.remove(it.next().a);
        }
    }

    @Override // com.kwad.sdk.core.report.h
    public synchronized List<T> b() {
        ArrayList arrayList;
        arrayList = new ArrayList(this.a.size());
        Iterator<Map.Entry<String, T>> it = this.a.entrySet().iterator();
        while (it.hasNext()) {
            arrayList.add(it.next().getValue());
        }
        return arrayList;
    }
}
