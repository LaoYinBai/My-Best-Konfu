package com.ss.union.game.sdk.core.glide.load.resource.gif;

import android.util.Log;
import com.ss.union.game.sdk.core.glide.load.ImageHeaderParser;
import com.ss.union.game.sdk.core.glide.load.ImageHeaderParserUtils;
import com.ss.union.game.sdk.core.glide.load.Options;
import com.ss.union.game.sdk.core.glide.load.ResourceDecoder;
import com.ss.union.game.sdk.core.glide.load.engine.Resource;
import com.ss.union.game.sdk.core.glide.load.engine.bitmap_recycle.ArrayPool;
import com.taobao.accs.data.Message;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class StreamGifDecoder implements ResourceDecoder<InputStream, GifDrawable> {
    private static final String a = "StreamGifDecoder";
    private final List<ImageHeaderParser> b;
    private final ResourceDecoder<ByteBuffer, GifDrawable> c;
    private final ArrayPool d;

    public StreamGifDecoder(List<ImageHeaderParser> list, ResourceDecoder<ByteBuffer, GifDrawable> resourceDecoder, ArrayPool arrayPool) {
        this.b = list;
        this.c = resourceDecoder;
        this.d = arrayPool;
    }

    @Override // com.ss.union.game.sdk.core.glide.load.ResourceDecoder
    public boolean handles(InputStream inputStream, Options options) throws IOException {
        return !((Boolean) options.get(GifOptions.DISABLE_ANIMATION)).booleanValue() && ImageHeaderParserUtils.getType(this.b, inputStream, this.d) == ImageHeaderParser.ImageType.GIF;
    }

    @Override // com.ss.union.game.sdk.core.glide.load.ResourceDecoder
    public Resource<GifDrawable> decode(InputStream inputStream, int i, int i2, Options options) throws IOException {
        byte[] bArrA = a(inputStream);
        if (bArrA == null) {
            return null;
        }
        return this.c.decode(ByteBuffer.wrap(bArrA), i, i2, options);
    }

    private static byte[] a(InputStream inputStream) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(Message.FLAG_REQ_BIT1);
        try {
            byte[] bArr = new byte[Message.FLAG_REQ_BIT1];
            while (true) {
                int i = inputStream.read(bArr);
                if (i != -1) {
                    byteArrayOutputStream.write(bArr, 0, i);
                } else {
                    byteArrayOutputStream.flush();
                    return byteArrayOutputStream.toByteArray();
                }
            }
        } catch (IOException e) {
            if (!Log.isLoggable(a, 5)) {
                return null;
            }
            Log.w(a, "Error reading data from stream", e);
            return null;
        }
    }
}
