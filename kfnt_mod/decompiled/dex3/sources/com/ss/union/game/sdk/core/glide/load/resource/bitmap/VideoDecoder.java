package com.ss.union.game.sdk.core.glide.load.resource.bitmap;

import android.annotation.TargetApi;
import android.content.res.AssetFileDescriptor;
import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.os.ParcelFileDescriptor;
import android.util.Log;
import com.ss.union.game.sdk.core.glide.load.Option;
import com.ss.union.game.sdk.core.glide.load.Options;
import com.ss.union.game.sdk.core.glide.load.ResourceDecoder;
import com.ss.union.game.sdk.core.glide.load.engine.Resource;
import com.ss.union.game.sdk.core.glide.load.engine.bitmap_recycle.BitmapPool;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.security.MessageDigest;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class VideoDecoder<T> implements ResourceDecoder<T, Bitmap> {
    public static final long DEFAULT_FRAME = -1;
    static final int a = 2;
    private static final String b = "VideoDecoder";
    private final c<T> d;
    private final BitmapPool e;
    private final b f;
    public static final Option<Long> TARGET_FRAME = Option.disk("resource.bitmap.VideoBitmapDecode.TargetFrame", -1L, new Option.CacheKeyUpdater<Long>() { // from class: com.ss.union.game.sdk.core.glide.load.resource.bitmap.VideoDecoder.1
        private final ByteBuffer a = ByteBuffer.allocate(8);

        @Override // com.ss.union.game.sdk.core.glide.load.Option.CacheKeyUpdater
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public void update(byte[] bArr, Long l, MessageDigest messageDigest) {
            messageDigest.update(bArr);
            synchronized (this.a) {
                this.a.position(0);
                messageDigest.update(this.a.putLong(l.longValue()).array());
            }
        }
    });
    public static final Option<Integer> FRAME_OPTION = Option.disk("resource.bitmap.VideoBitmapDecode.FrameOption", 2, new Option.CacheKeyUpdater<Integer>() { // from class: com.ss.union.game.sdk.core.glide.load.resource.bitmap.VideoDecoder.2
        private final ByteBuffer a = ByteBuffer.allocate(4);

        @Override // com.ss.union.game.sdk.core.glide.load.Option.CacheKeyUpdater
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public void update(byte[] bArr, Integer num, MessageDigest messageDigest) {
            if (num == null) {
                return;
            }
            messageDigest.update(bArr);
            synchronized (this.a) {
                this.a.position(0);
                messageDigest.update(this.a.putInt(num.intValue()).array());
            }
        }
    });
    private static final b c = new b();

    interface c<T> {
        void a(MediaMetadataRetriever mediaMetadataRetriever, T t);
    }

    @Override // com.ss.union.game.sdk.core.glide.load.ResourceDecoder
    public boolean handles(T t, Options options) {
        return true;
    }

    public static ResourceDecoder<AssetFileDescriptor, Bitmap> asset(BitmapPool bitmapPool) {
        return new VideoDecoder(bitmapPool, new a());
    }

    public static ResourceDecoder<ParcelFileDescriptor, Bitmap> parcel(BitmapPool bitmapPool) {
        return new VideoDecoder(bitmapPool, new d());
    }

    VideoDecoder(BitmapPool bitmapPool, c<T> cVar) {
        this(bitmapPool, cVar, c);
    }

    VideoDecoder(BitmapPool bitmapPool, c<T> cVar, b bVar) {
        this.e = bitmapPool;
        this.d = cVar;
        this.f = bVar;
    }

    @Override // com.ss.union.game.sdk.core.glide.load.ResourceDecoder
    public Resource<Bitmap> decode(T t, int i, int i2, Options options) throws IOException {
        long jLongValue = ((Long) options.get(TARGET_FRAME)).longValue();
        if (jLongValue < 0 && jLongValue != -1) {
            throw new IllegalArgumentException("Requested frame must be non-negative, or DEFAULT_FRAME, given: " + jLongValue);
        }
        Integer num = (Integer) options.get(FRAME_OPTION);
        if (num == null) {
            num = 2;
        }
        DownsampleStrategy downsampleStrategy = (DownsampleStrategy) options.get(DownsampleStrategy.OPTION);
        if (downsampleStrategy == null) {
            downsampleStrategy = DownsampleStrategy.DEFAULT;
        }
        DownsampleStrategy downsampleStrategy2 = downsampleStrategy;
        MediaMetadataRetriever mediaMetadataRetrieverA = this.f.a();
        try {
            try {
                this.d.a(mediaMetadataRetrieverA, t);
                Bitmap bitmapA = a(mediaMetadataRetrieverA, jLongValue, num.intValue(), i, i2, downsampleStrategy2);
                mediaMetadataRetrieverA.release();
                return BitmapResource.obtain(bitmapA, this.e);
            } catch (RuntimeException e) {
                throw new IOException(e);
            }
        } catch (Throwable th) {
            mediaMetadataRetrieverA.release();
            throw th;
        }
    }

    private static Bitmap a(MediaMetadataRetriever mediaMetadataRetriever, long j, int i, int i2, int i3, DownsampleStrategy downsampleStrategy) {
        return a(mediaMetadataRetriever, j, i);
    }

    @TargetApi(26)
    private static Bitmap b(MediaMetadataRetriever mediaMetadataRetriever, long j, int i, int i2, int i3, DownsampleStrategy downsampleStrategy) {
        try {
            int i4 = Integer.parseInt(mediaMetadataRetriever.extractMetadata(18));
            int i5 = Integer.parseInt(mediaMetadataRetriever.extractMetadata(19));
            int i6 = Integer.parseInt(mediaMetadataRetriever.extractMetadata(24));
            if (i6 == 90 || i6 == 270) {
                i5 = i4;
                i4 = i5;
            }
            float scaleFactor = downsampleStrategy.getScaleFactor(i4, i5, i2, i3);
            Math.round(i4 * scaleFactor);
            Math.round(scaleFactor * i5);
            return null;
        } catch (Throwable th) {
            if (Log.isLoggable(b, 3)) {
                Log.d(b, "Exception trying to decode frame on oreo+", th);
            }
            return null;
        }
    }

    private static Bitmap a(MediaMetadataRetriever mediaMetadataRetriever, long j, int i) {
        return mediaMetadataRetriever.getFrameAtTime(j, i);
    }

    static class b {
        b() {
        }

        public MediaMetadataRetriever a() {
            return new MediaMetadataRetriever();
        }
    }

    private static final class a implements c<AssetFileDescriptor> {
        private a() {
        }

        @Override // com.ss.union.game.sdk.core.glide.load.resource.bitmap.VideoDecoder.c
        public void a(MediaMetadataRetriever mediaMetadataRetriever, AssetFileDescriptor assetFileDescriptor) throws IllegalArgumentException {
            mediaMetadataRetriever.setDataSource(assetFileDescriptor.getFileDescriptor(), assetFileDescriptor.getStartOffset(), assetFileDescriptor.getLength());
        }
    }

    static final class d implements c<ParcelFileDescriptor> {
        d() {
        }

        @Override // com.ss.union.game.sdk.core.glide.load.resource.bitmap.VideoDecoder.c
        public void a(MediaMetadataRetriever mediaMetadataRetriever, ParcelFileDescriptor parcelFileDescriptor) throws IllegalArgumentException {
            mediaMetadataRetriever.setDataSource(parcelFileDescriptor.getFileDescriptor());
        }
    }
}
