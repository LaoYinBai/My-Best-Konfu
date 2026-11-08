package com.ss.union.game.sdk.core.glide.load.engine;

import android.util.Log;
import androidx.core.util.Pools;
import com.ss.union.game.sdk.core.glide.load.Options;
import com.ss.union.game.sdk.core.glide.load.ResourceDecoder;
import com.ss.union.game.sdk.core.glide.load.data.DataRewinder;
import com.ss.union.game.sdk.core.glide.load.resource.transcode.ResourceTranscoder;
import com.ss.union.game.sdk.core.glide.util.Preconditions;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class DecodePath<DataType, ResourceType, Transcode> {
    private static final String a = "DecodePath";
    private final Class<DataType> b;
    private final List<? extends ResourceDecoder<DataType, ResourceType>> c;
    private final ResourceTranscoder<ResourceType, Transcode> d;
    private final Pools.Pool<List<Throwable>> e;
    private final String f;

    interface a<ResourceType> {
        Resource<ResourceType> a(Resource<ResourceType> resource);
    }

    public DecodePath(Class<DataType> cls, Class<ResourceType> cls2, Class<Transcode> cls3, List<? extends ResourceDecoder<DataType, ResourceType>> list, ResourceTranscoder<ResourceType, Transcode> resourceTranscoder, Pools.Pool<List<Throwable>> pool) {
        this.b = cls;
        this.c = list;
        this.d = resourceTranscoder;
        this.e = pool;
        this.f = "Failed DecodePath{" + cls.getSimpleName() + "->" + cls2.getSimpleName() + "->" + cls3.getSimpleName() + "}";
    }

    public Resource<Transcode> decode(DataRewinder<DataType> dataRewinder, int i, int i2, Options options, a<ResourceType> aVar) throws GlideException {
        return this.d.transcode(aVar.a(a(dataRewinder, i, i2, options)), options);
    }

    private Resource<ResourceType> a(DataRewinder<DataType> dataRewinder, int i, int i2, Options options) throws GlideException {
        List<Throwable> list = (List) Preconditions.checkNotNull(this.e.acquire());
        try {
            return a(dataRewinder, i, i2, options, list);
        } finally {
            this.e.release(list);
        }
    }

    private Resource<ResourceType> a(DataRewinder<DataType> dataRewinder, int i, int i2, Options options, List<Throwable> list) throws IOException, GlideException {
        int size = this.c.size();
        Resource<ResourceType> resourceDecode = null;
        for (int i3 = 0; i3 < size; i3++) {
            ResourceDecoder<DataType, ResourceType> resourceDecoder = this.c.get(i3);
            try {
                if (resourceDecoder.handles(dataRewinder.rewindAndGet(), options)) {
                    resourceDecode = resourceDecoder.decode(dataRewinder.rewindAndGet(), i, i2, options);
                }
            } catch (IOException | OutOfMemoryError | RuntimeException e) {
                if (Log.isLoggable(a, 2)) {
                    Log.v(a, "Failed to decode data for " + resourceDecoder, e);
                }
                list.add(e);
            }
            if (resourceDecode != null) {
                break;
            }
        }
        if (resourceDecode != null) {
            return resourceDecode;
        }
        throw new GlideException(this.f, new ArrayList(list));
    }

    public String toString() {
        return "DecodePath{ dataClass=" + this.b + ", decoders=" + this.c + ", transcoder=" + this.d + '}';
    }
}
