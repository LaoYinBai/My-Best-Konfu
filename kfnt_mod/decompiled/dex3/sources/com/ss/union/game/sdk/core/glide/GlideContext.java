package com.ss.union.game.sdk.core.glide;

import android.content.Context;
import android.content.ContextWrapper;
import android.widget.ImageView;
import com.ss.union.game.sdk.core.glide.load.engine.Engine;
import com.ss.union.game.sdk.core.glide.load.engine.bitmap_recycle.ArrayPool;
import com.ss.union.game.sdk.core.glide.request.RequestListener;
import com.ss.union.game.sdk.core.glide.request.RequestOptions;
import com.ss.union.game.sdk.core.glide.request.target.ImageViewTargetFactory;
import com.ss.union.game.sdk.core.glide.request.target.ViewTarget;
import java.util.List;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class GlideContext extends ContextWrapper {
    static final TransitionOptions<?, ?> a = new GenericTransitionOptions();
    private final ArrayPool b;
    private final Registry c;
    private final ImageViewTargetFactory d;
    private final RequestOptions e;
    private final List<RequestListener<Object>> f;
    private final Map<Class<?>, TransitionOptions<?, ?>> g;
    private final Engine h;
    private final boolean i;
    private final int j;

    public GlideContext(Context context, ArrayPool arrayPool, Registry registry, ImageViewTargetFactory imageViewTargetFactory, RequestOptions requestOptions, Map<Class<?>, TransitionOptions<?, ?>> map, List<RequestListener<Object>> list, Engine engine, boolean z, int i) {
        super(context.getApplicationContext());
        this.b = arrayPool;
        this.c = registry;
        this.d = imageViewTargetFactory;
        this.e = requestOptions;
        this.f = list;
        this.g = map;
        this.h = engine;
        this.i = z;
        this.j = i;
    }

    public List<RequestListener<Object>> getDefaultRequestListeners() {
        return this.f;
    }

    public RequestOptions getDefaultRequestOptions() {
        return this.e;
    }

    public <T> TransitionOptions<?, T> getDefaultTransitionOptions(Class<T> cls) {
        TransitionOptions<?, T> transitionOptions = (TransitionOptions) this.g.get(cls);
        if (transitionOptions == null) {
            for (Map.Entry<Class<?>, TransitionOptions<?, ?>> entry : this.g.entrySet()) {
                if (entry.getKey().isAssignableFrom(cls)) {
                    transitionOptions = (TransitionOptions) entry.getValue();
                }
            }
        }
        return transitionOptions == null ? (TransitionOptions<?, T>) a : transitionOptions;
    }

    public <X> ViewTarget<ImageView, X> buildImageViewTarget(ImageView imageView, Class<X> cls) {
        return this.d.buildTarget(imageView, cls);
    }

    public Engine getEngine() {
        return this.h;
    }

    public Registry getRegistry() {
        return this.c;
    }

    public int getLogLevel() {
        return this.j;
    }

    public ArrayPool getArrayPool() {
        return this.b;
    }

    public boolean isLoggingRequestOriginsEnabled() {
        return this.i;
    }
}
