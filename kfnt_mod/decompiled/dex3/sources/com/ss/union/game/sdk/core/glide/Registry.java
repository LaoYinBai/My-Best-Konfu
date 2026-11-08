package com.ss.union.game.sdk.core.glide;

import androidx.core.util.Pools;
import com.ss.union.game.sdk.core.glide.load.Encoder;
import com.ss.union.game.sdk.core.glide.load.ImageHeaderParser;
import com.ss.union.game.sdk.core.glide.load.ResourceDecoder;
import com.ss.union.game.sdk.core.glide.load.ResourceEncoder;
import com.ss.union.game.sdk.core.glide.load.data.DataRewinder;
import com.ss.union.game.sdk.core.glide.load.data.DataRewinderRegistry;
import com.ss.union.game.sdk.core.glide.load.engine.DecodePath;
import com.ss.union.game.sdk.core.glide.load.engine.LoadPath;
import com.ss.union.game.sdk.core.glide.load.engine.Resource;
import com.ss.union.game.sdk.core.glide.load.model.ModelLoader;
import com.ss.union.game.sdk.core.glide.load.model.ModelLoaderFactory;
import com.ss.union.game.sdk.core.glide.load.model.ModelLoaderRegistry;
import com.ss.union.game.sdk.core.glide.load.resource.transcode.ResourceTranscoder;
import com.ss.union.game.sdk.core.glide.load.resource.transcode.TranscoderRegistry;
import com.ss.union.game.sdk.core.glide.provider.EncoderRegistry;
import com.ss.union.game.sdk.core.glide.provider.ImageHeaderParserRegistry;
import com.ss.union.game.sdk.core.glide.provider.LoadPathCache;
import com.ss.union.game.sdk.core.glide.provider.ModelToResourceClassCache;
import com.ss.union.game.sdk.core.glide.provider.ResourceDecoderRegistry;
import com.ss.union.game.sdk.core.glide.provider.ResourceEncoderRegistry;
import com.ss.union.game.sdk.core.glide.util.pool.FactoryPools;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class Registry {
    public static final String BUCKET_BITMAP = "Bitmap";
    public static final String BUCKET_BITMAP_DRAWABLE = "BitmapDrawable";
    public static final String BUCKET_GIF = "Gif";
    private static final String a = "legacy_prepend_all";
    private static final String b = "legacy_append";
    private final ModelToResourceClassCache j = new ModelToResourceClassCache();
    private final LoadPathCache k = new LoadPathCache();
    private final Pools.Pool<List<Throwable>> l = FactoryPools.threadSafeList();
    private final ModelLoaderRegistry c = new ModelLoaderRegistry(this.l);
    private final EncoderRegistry d = new EncoderRegistry();
    private final ResourceDecoderRegistry e = new ResourceDecoderRegistry();
    private final ResourceEncoderRegistry f = new ResourceEncoderRegistry();
    private final DataRewinderRegistry g = new DataRewinderRegistry();
    private final TranscoderRegistry h = new TranscoderRegistry();
    private final ImageHeaderParserRegistry i = new ImageHeaderParserRegistry();

    public Registry() {
        setResourceDecoderBucketPriorityList(Arrays.asList(BUCKET_GIF, BUCKET_BITMAP, BUCKET_BITMAP_DRAWABLE));
    }

    @Deprecated
    public <Data> Registry register(Class<Data> cls, Encoder<Data> encoder) {
        return append(cls, encoder);
    }

    public <Data> Registry append(Class<Data> cls, Encoder<Data> encoder) {
        this.d.append(cls, encoder);
        return this;
    }

    public <Data> Registry prepend(Class<Data> cls, Encoder<Data> encoder) {
        this.d.prepend(cls, encoder);
        return this;
    }

    public <Data, TResource> Registry append(Class<Data> cls, Class<TResource> cls2, ResourceDecoder<Data, TResource> resourceDecoder) {
        append(b, cls, cls2, resourceDecoder);
        return this;
    }

    public <Data, TResource> Registry append(String str, Class<Data> cls, Class<TResource> cls2, ResourceDecoder<Data, TResource> resourceDecoder) {
        this.e.append(str, resourceDecoder, cls, cls2);
        return this;
    }

    public <Data, TResource> Registry prepend(Class<Data> cls, Class<TResource> cls2, ResourceDecoder<Data, TResource> resourceDecoder) {
        prepend(a, cls, cls2, resourceDecoder);
        return this;
    }

    public <Data, TResource> Registry prepend(String str, Class<Data> cls, Class<TResource> cls2, ResourceDecoder<Data, TResource> resourceDecoder) {
        this.e.prepend(str, resourceDecoder, cls, cls2);
        return this;
    }

    public final Registry setResourceDecoderBucketPriorityList(List<String> list) {
        ArrayList arrayList = new ArrayList(list.size());
        arrayList.addAll(list);
        arrayList.add(0, a);
        arrayList.add(b);
        this.e.setBucketPriorityList(arrayList);
        return this;
    }

    @Deprecated
    public <TResource> Registry register(Class<TResource> cls, ResourceEncoder<TResource> resourceEncoder) {
        return append((Class) cls, (ResourceEncoder) resourceEncoder);
    }

    public <TResource> Registry append(Class<TResource> cls, ResourceEncoder<TResource> resourceEncoder) {
        this.f.append(cls, resourceEncoder);
        return this;
    }

    public <TResource> Registry prepend(Class<TResource> cls, ResourceEncoder<TResource> resourceEncoder) {
        this.f.prepend(cls, resourceEncoder);
        return this;
    }

    public Registry register(DataRewinder.Factory<?> factory) {
        this.g.register(factory);
        return this;
    }

    public <TResource, Transcode> Registry register(Class<TResource> cls, Class<Transcode> cls2, ResourceTranscoder<TResource, Transcode> resourceTranscoder) {
        this.h.register(cls, cls2, resourceTranscoder);
        return this;
    }

    public Registry register(ImageHeaderParser imageHeaderParser) {
        this.i.add(imageHeaderParser);
        return this;
    }

    public <Model, Data> Registry append(Class<Model> cls, Class<Data> cls2, ModelLoaderFactory<Model, Data> modelLoaderFactory) {
        this.c.append(cls, cls2, modelLoaderFactory);
        return this;
    }

    public <Model, Data> Registry prepend(Class<Model> cls, Class<Data> cls2, ModelLoaderFactory<Model, Data> modelLoaderFactory) {
        this.c.prepend(cls, cls2, modelLoaderFactory);
        return this;
    }

    public <Model, Data> Registry replace(Class<Model> cls, Class<Data> cls2, ModelLoaderFactory<? extends Model, ? extends Data> modelLoaderFactory) {
        this.c.replace(cls, cls2, modelLoaderFactory);
        return this;
    }

    public <Data, TResource, Transcode> LoadPath<Data, TResource, Transcode> getLoadPath(Class<Data> cls, Class<TResource> cls2, Class<Transcode> cls3) {
        LoadPath<Data, TResource, Transcode> loadPath = this.k.get(cls, cls2, cls3);
        if (this.k.isEmptyLoadPath(loadPath)) {
            return null;
        }
        if (loadPath == null) {
            List<DecodePath<Data, TResource, Transcode>> listA = a(cls, cls2, cls3);
            loadPath = listA.isEmpty() ? null : new LoadPath<>(cls, cls2, cls3, listA, this.l);
            this.k.put(cls, cls2, cls3, loadPath);
        }
        return loadPath;
    }

    private <Data, TResource, Transcode> List<DecodePath<Data, TResource, Transcode>> a(Class<Data> cls, Class<TResource> cls2, Class<Transcode> cls3) {
        ArrayList arrayList = new ArrayList();
        for (Class cls4 : this.e.getResourceClasses(cls, cls2)) {
            for (Class cls5 : this.h.getTranscodeClasses(cls4, cls3)) {
                arrayList.add(new DecodePath(cls, cls4, cls5, this.e.getDecoders(cls, cls4), this.h.get(cls4, cls5), this.l));
            }
        }
        return arrayList;
    }

    public <Model, TResource, Transcode> List<Class<?>> getRegisteredResourceClasses(Class<Model> cls, Class<TResource> cls2, Class<Transcode> cls3) {
        List<Class<?>> arrayList = this.j.get(cls, cls2, cls3);
        if (arrayList == null) {
            arrayList = new ArrayList<>();
            Iterator<Class<?>> it = this.c.getDataClasses(cls).iterator();
            while (it.hasNext()) {
                for (Class<?> cls4 : this.e.getResourceClasses(it.next(), cls2)) {
                    if (!this.h.getTranscodeClasses(cls4, cls3).isEmpty() && !arrayList.contains(cls4)) {
                        arrayList.add(cls4);
                    }
                }
            }
            this.j.put(cls, cls2, cls3, Collections.unmodifiableList(arrayList));
        }
        return arrayList;
    }

    public boolean isResourceEncoderAvailable(Resource<?> resource) {
        return this.f.get(resource.getResourceClass()) != null;
    }

    public <X> ResourceEncoder<X> getResultEncoder(Resource<X> resource) throws NoResultEncoderAvailableException {
        ResourceEncoder<X> resourceEncoder = this.f.get(resource.getResourceClass());
        if (resourceEncoder != null) {
            return resourceEncoder;
        }
        throw new NoResultEncoderAvailableException(resource.getResourceClass());
    }

    public <X> Encoder<X> getSourceEncoder(X x) throws NoSourceEncoderAvailableException {
        Encoder<X> encoder = this.d.getEncoder(x.getClass());
        if (encoder != null) {
            return encoder;
        }
        throw new NoSourceEncoderAvailableException(x.getClass());
    }

    public <X> DataRewinder<X> getRewinder(X x) {
        return this.g.build(x);
    }

    public <Model> List<ModelLoader<Model, ?>> getModelLoaders(Model model) {
        List<ModelLoader<Model, ?>> modelLoaders = this.c.getModelLoaders(model);
        if (modelLoaders.isEmpty()) {
            throw new NoModelLoaderAvailableException(model);
        }
        return modelLoaders;
    }

    public List<ImageHeaderParser> getImageHeaderParsers() {
        List<ImageHeaderParser> parsers = this.i.getParsers();
        if (parsers.isEmpty()) {
            throw new NoImageHeaderParserException();
        }
        return parsers;
    }

    public static class NoModelLoaderAvailableException extends MissingComponentException {
        public NoModelLoaderAvailableException(Object obj) {
            super("Failed to find any ModelLoaders for model: " + obj);
        }

        public NoModelLoaderAvailableException(Class<?> cls, Class<?> cls2) {
            super("Failed to find any ModelLoaders for model: " + cls + " and data: " + cls2);
        }
    }

    public static class NoResultEncoderAvailableException extends MissingComponentException {
        public NoResultEncoderAvailableException(Class<?> cls) {
            super("Failed to find result encoder for resource class: " + cls + ", you may need to consider registering a new Encoder for the requested type or DiskCacheStrategy.DATA/DiskCacheStrategy.NONE if caching your transformed resource is unnecessary.");
        }
    }

    public static class NoSourceEncoderAvailableException extends MissingComponentException {
        public NoSourceEncoderAvailableException(Class<?> cls) {
            super("Failed to find source encoder for data class: " + cls);
        }
    }

    public static class MissingComponentException extends RuntimeException {
        public MissingComponentException(String str) {
            super(str);
        }
    }

    public static final class NoImageHeaderParserException extends MissingComponentException {
        public NoImageHeaderParserException() {
            super("Failed to find image header parser.");
        }
    }
}
