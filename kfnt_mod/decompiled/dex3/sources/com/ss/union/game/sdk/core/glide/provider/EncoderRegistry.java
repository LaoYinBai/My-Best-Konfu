package com.ss.union.game.sdk.core.glide.provider;

import com.ss.union.game.sdk.core.glide.load.Encoder;
import java.util.ArrayList;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class EncoderRegistry {
    private final List<a<?>> a = new ArrayList();

    public synchronized <T> Encoder<T> getEncoder(Class<T> cls) {
        for (a<?> aVar : this.a) {
            if (aVar.a(cls)) {
                return (Encoder<T>) aVar.a;
            }
        }
        return null;
    }

    public synchronized <T> void append(Class<T> cls, Encoder<T> encoder) {
        this.a.add(new a<>(cls, encoder));
    }

    public synchronized <T> void prepend(Class<T> cls, Encoder<T> encoder) {
        this.a.add(0, new a<>(cls, encoder));
    }

    private static final class a<T> {
        final Encoder<T> a;
        private final Class<T> b;

        a(Class<T> cls, Encoder<T> encoder) {
            this.b = cls;
            this.a = encoder;
        }

        boolean a(Class<?> cls) {
            return this.b.isAssignableFrom(cls);
        }
    }
}
