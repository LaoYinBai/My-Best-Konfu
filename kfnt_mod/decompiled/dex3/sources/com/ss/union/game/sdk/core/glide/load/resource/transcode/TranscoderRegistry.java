package com.ss.union.game.sdk.core.glide.load.resource.transcode;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class TranscoderRegistry {
    private final List<a<?, ?>> a = new ArrayList();

    public synchronized <Z, R> void register(Class<Z> cls, Class<R> cls2, ResourceTranscoder<Z, R> resourceTranscoder) {
        this.a.add(new a<>(cls, cls2, resourceTranscoder));
    }

    public synchronized <Z, R> ResourceTranscoder<Z, R> get(Class<Z> cls, Class<R> cls2) {
        if (cls2.isAssignableFrom(cls)) {
            return UnitTranscoder.get();
        }
        for (a<?, ?> aVar : this.a) {
            if (aVar.a(cls, cls2)) {
                return (ResourceTranscoder<Z, R>) aVar.a;
            }
        }
        throw new IllegalArgumentException("No transcoder registered to transcode from " + cls + " to " + cls2);
    }

    public synchronized <Z, R> List<Class<R>> getTranscodeClasses(Class<Z> cls, Class<R> cls2) {
        ArrayList arrayList = new ArrayList();
        if (cls2.isAssignableFrom(cls)) {
            arrayList.add(cls2);
            return arrayList;
        }
        Iterator<a<?, ?>> it = this.a.iterator();
        while (it.hasNext()) {
            if (it.next().a(cls, cls2)) {
                arrayList.add(cls2);
            }
        }
        return arrayList;
    }

    private static final class a<Z, R> {
        final ResourceTranscoder<Z, R> a;
        private final Class<Z> b;
        private final Class<R> c;

        a(Class<Z> cls, Class<R> cls2, ResourceTranscoder<Z, R> resourceTranscoder) {
            this.b = cls;
            this.c = cls2;
            this.a = resourceTranscoder;
        }

        public boolean a(Class<?> cls, Class<?> cls2) {
            return this.b.isAssignableFrom(cls) && cls2.isAssignableFrom(this.c);
        }
    }
}
