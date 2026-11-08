package com.ss.union.game.sdk.core.glide.provider;

import com.ss.union.game.sdk.core.glide.load.ResourceEncoder;
import java.util.ArrayList;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class ResourceEncoderRegistry {
    private final List<a<?>> a = new ArrayList();

    public synchronized <Z> void append(Class<Z> cls, ResourceEncoder<Z> resourceEncoder) {
        this.a.add(new a<>(cls, resourceEncoder));
    }

    public synchronized <Z> void prepend(Class<Z> cls, ResourceEncoder<Z> resourceEncoder) {
        this.a.add(0, new a<>(cls, resourceEncoder));
    }

    public synchronized <Z> ResourceEncoder<Z> get(Class<Z> cls) {
        int size = this.a.size();
        for (int i = 0; i < size; i++) {
            a<?> aVar = this.a.get(i);
            if (aVar.a(cls)) {
                return (ResourceEncoder<Z>) aVar.a;
            }
        }
        return null;
    }

    private static final class a<T> {
        final ResourceEncoder<T> a;
        private final Class<T> b;

        a(Class<T> cls, ResourceEncoder<T> resourceEncoder) {
            this.b = cls;
            this.a = resourceEncoder;
        }

        boolean a(Class<?> cls) {
            return this.b.isAssignableFrom(cls);
        }
    }
}
