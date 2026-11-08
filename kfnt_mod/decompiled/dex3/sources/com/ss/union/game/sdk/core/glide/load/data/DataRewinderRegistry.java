package com.ss.union.game.sdk.core.glide.load.data;

import com.ss.union.game.sdk.core.glide.load.data.DataRewinder;
import com.ss.union.game.sdk.core.glide.util.Preconditions;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class DataRewinderRegistry {
    private static final DataRewinder.Factory<?> b = new DataRewinder.Factory<Object>() { // from class: com.ss.union.game.sdk.core.glide.load.data.DataRewinderRegistry.1
        @Override // com.ss.union.game.sdk.core.glide.load.data.DataRewinder.Factory
        public DataRewinder<Object> build(Object obj) {
            return new a(obj);
        }

        @Override // com.ss.union.game.sdk.core.glide.load.data.DataRewinder.Factory
        public Class<Object> getDataClass() {
            throw new UnsupportedOperationException("Not implemented");
        }
    };
    private final Map<Class<?>, DataRewinder.Factory<?>> a = new HashMap();

    public synchronized void register(DataRewinder.Factory<?> factory) {
        this.a.put(factory.getDataClass(), factory);
    }

    public synchronized <T> DataRewinder<T> build(T t) {
        DataRewinder.Factory<?> factory;
        Preconditions.checkNotNull(t);
        factory = this.a.get(t.getClass());
        if (factory == null) {
            Iterator<DataRewinder.Factory<?>> it = this.a.values().iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                DataRewinder.Factory<?> next = it.next();
                if (next.getDataClass().isAssignableFrom(t.getClass())) {
                    factory = next;
                    break;
                }
            }
        }
        if (factory == null) {
            factory = b;
        }
        return (DataRewinder<T>) factory.build(t);
    }

    private static final class a implements DataRewinder<Object> {
        private final Object a;

        @Override // com.ss.union.game.sdk.core.glide.load.data.DataRewinder
        public void cleanup() {
        }

        a(Object obj) {
            this.a = obj;
        }

        @Override // com.ss.union.game.sdk.core.glide.load.data.DataRewinder
        public Object rewindAndGet() {
            return this.a;
        }
    }
}
