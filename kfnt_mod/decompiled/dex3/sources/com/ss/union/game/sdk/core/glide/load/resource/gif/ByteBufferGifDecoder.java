package com.ss.union.game.sdk.core.glide.load.resource.gif;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import com.ss.union.game.sdk.core.glide.Glide;
import com.ss.union.game.sdk.core.glide.gifdecoder.GifDecoder;
import com.ss.union.game.sdk.core.glide.gifdecoder.GifHeader;
import com.ss.union.game.sdk.core.glide.gifdecoder.GifHeaderParser;
import com.ss.union.game.sdk.core.glide.gifdecoder.StandardGifDecoder;
import com.ss.union.game.sdk.core.glide.load.DecodeFormat;
import com.ss.union.game.sdk.core.glide.load.ImageHeaderParser;
import com.ss.union.game.sdk.core.glide.load.ImageHeaderParserUtils;
import com.ss.union.game.sdk.core.glide.load.Options;
import com.ss.union.game.sdk.core.glide.load.ResourceDecoder;
import com.ss.union.game.sdk.core.glide.load.engine.bitmap_recycle.ArrayPool;
import com.ss.union.game.sdk.core.glide.load.engine.bitmap_recycle.BitmapPool;
import com.ss.union.game.sdk.core.glide.load.resource.UnitTransformation;
import com.ss.union.game.sdk.core.glide.util.LogTime;
import com.ss.union.game.sdk.core.glide.util.Util;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.List;
import java.util.Queue;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class ByteBufferGifDecoder implements ResourceDecoder<ByteBuffer, GifDrawable> {
    private static final String a = "BufferGifDecoder";
    private static final a b = new a();
    private static final b c = new b();
    private final Context d;
    private final List<ImageHeaderParser> e;
    private final b f;
    private final a g;
    private final GifBitmapProvider h;

    public ByteBufferGifDecoder(Context context) {
        this(context, Glide.get(context).getRegistry().getImageHeaderParsers(), Glide.get(context).getBitmapPool(), Glide.get(context).getArrayPool());
    }

    public ByteBufferGifDecoder(Context context, List<ImageHeaderParser> list, BitmapPool bitmapPool, ArrayPool arrayPool) {
        this(context, list, bitmapPool, arrayPool, c, b);
    }

    ByteBufferGifDecoder(Context context, List<ImageHeaderParser> list, BitmapPool bitmapPool, ArrayPool arrayPool, b bVar, a aVar) {
        this.d = context.getApplicationContext();
        this.e = list;
        this.g = aVar;
        this.h = new GifBitmapProvider(bitmapPool, arrayPool);
        this.f = bVar;
    }

    @Override // com.ss.union.game.sdk.core.glide.load.ResourceDecoder
    public boolean handles(ByteBuffer byteBuffer, Options options) throws IOException {
        return !((Boolean) options.get(GifOptions.DISABLE_ANIMATION)).booleanValue() && ImageHeaderParserUtils.getType(this.e, byteBuffer) == ImageHeaderParser.ImageType.GIF;
    }

    @Override // com.ss.union.game.sdk.core.glide.load.ResourceDecoder
    public GifDrawableResource decode(ByteBuffer byteBuffer, int i, int i2, Options options) {
        GifHeaderParser gifHeaderParserA = this.f.a(byteBuffer);
        try {
            return a(byteBuffer, i, i2, gifHeaderParserA, options);
        } finally {
            this.f.a(gifHeaderParserA);
        }
    }

    private GifDrawableResource a(ByteBuffer byteBuffer, int i, int i2, GifHeaderParser gifHeaderParser, Options options) {
        long logTime = LogTime.getLogTime();
        try {
            GifHeader header = gifHeaderParser.parseHeader();
            if (header.getNumFrames() > 0 && header.getStatus() == 0) {
                Bitmap.Config config = options.get(GifOptions.DECODE_FORMAT) == DecodeFormat.PREFER_RGB_565 ? Bitmap.Config.RGB_565 : Bitmap.Config.ARGB_8888;
                GifDecoder gifDecoderA = this.g.a(this.h, header, byteBuffer, a(header, i, i2));
                gifDecoderA.setDefaultBitmapConfig(config);
                gifDecoderA.advance();
                Bitmap nextFrame = gifDecoderA.getNextFrame();
                if (nextFrame == null) {
                    if (Log.isLoggable(a, 2)) {
                        Log.v(a, "Decoded GIF from stream in " + LogTime.getElapsedMillis(logTime));
                    }
                    return null;
                }
                GifDrawableResource gifDrawableResource = new GifDrawableResource(new GifDrawable(this.d, gifDecoderA, UnitTransformation.get(), i, i2, nextFrame));
                if (Log.isLoggable(a, 2)) {
                    Log.v(a, "Decoded GIF from stream in " + LogTime.getElapsedMillis(logTime));
                }
                return gifDrawableResource;
            }
            if (Log.isLoggable(a, 2)) {
                Log.v(a, "Decoded GIF from stream in " + LogTime.getElapsedMillis(logTime));
            }
            return null;
        } catch (Throwable th) {
            if (Log.isLoggable(a, 2)) {
                Log.v(a, "Decoded GIF from stream in " + LogTime.getElapsedMillis(logTime));
            }
            throw th;
        }
    }

    private static int a(GifHeader gifHeader, int i, int i2) {
        int iMin = Math.min(gifHeader.getHeight() / i2, gifHeader.getWidth() / i);
        int iMax = Math.max(1, iMin == 0 ? 0 : Integer.highestOneBit(iMin));
        if (Log.isLoggable(a, 2) && iMax > 1) {
            Log.v(a, "Downsampling GIF, sampleSize: " + iMax + ", target dimens: [" + i + "x" + i2 + "], actual dimens: [" + gifHeader.getWidth() + "x" + gifHeader.getHeight() + "]");
        }
        return iMax;
    }

    static class a {
        a() {
        }

        GifDecoder a(GifDecoder.BitmapProvider bitmapProvider, GifHeader gifHeader, ByteBuffer byteBuffer, int i) {
            return new StandardGifDecoder(bitmapProvider, gifHeader, byteBuffer, i);
        }
    }

    static class b {
        private final Queue<GifHeaderParser> a = Util.createQueue(0);

        b() {
        }

        synchronized GifHeaderParser a(ByteBuffer byteBuffer) {
            GifHeaderParser gifHeaderParserPoll;
            gifHeaderParserPoll = this.a.poll();
            if (gifHeaderParserPoll == null) {
                gifHeaderParserPoll = new GifHeaderParser();
            }
            return gifHeaderParserPoll.setData(byteBuffer);
        }

        synchronized void a(GifHeaderParser gifHeaderParser) {
            gifHeaderParser.clear();
            this.a.offer(gifHeaderParser);
        }
    }
}
