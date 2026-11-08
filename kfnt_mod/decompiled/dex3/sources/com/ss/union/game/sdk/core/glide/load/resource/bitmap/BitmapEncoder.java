package com.ss.union.game.sdk.core.glide.load.resource.bitmap;

import android.graphics.Bitmap;
import com.ss.union.game.sdk.core.glide.load.EncodeStrategy;
import com.ss.union.game.sdk.core.glide.load.Option;
import com.ss.union.game.sdk.core.glide.load.Options;
import com.ss.union.game.sdk.core.glide.load.ResourceEncoder;
import com.ss.union.game.sdk.core.glide.load.engine.bitmap_recycle.ArrayPool;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class BitmapEncoder implements ResourceEncoder<Bitmap> {
    private static final String a = "BitmapEncoder";
    private final ArrayPool b;
    public static final Option<Integer> COMPRESSION_QUALITY = Option.memory("resource.bitmap.BitmapEncoder.CompressionQuality", 90);
    public static final Option<Bitmap.CompressFormat> COMPRESSION_FORMAT = Option.memory("resource.bitmap.BitmapEncoder.CompressionFormat");

    public BitmapEncoder(ArrayPool arrayPool) {
        this.b = arrayPool;
    }

    @Deprecated
    public BitmapEncoder() {
        this.b = null;
    }

    /* JADX WARN: Can't wrap try/catch for region: R(15:0|2|45|3|4|40|5|(5:48|6|(2:8|9)(1:10)|11|12)|46|13|27|(1:29)|30|31|(1:(0))) */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0074 A[Catch: all -> 0x00c6, TRY_LEAVE, TryCatch #3 {all -> 0x00c6, blocks: (B:3:0x001f, B:13:0x004b, B:27:0x006b, B:29:0x0074, B:33:0x00c2, B:34:0x00c5), top: B:45:0x001f }] */
    @Override // com.ss.union.game.sdk.core.glide.load.Encoder
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean encode(com.ss.union.game.sdk.core.glide.load.engine.Resource<android.graphics.Bitmap> r8, java.io.File r9, com.ss.union.game.sdk.core.glide.load.Options r10) {
        /*
            r7 = this;
            java.lang.Object r8 = r8.get()
            android.graphics.Bitmap r8 = (android.graphics.Bitmap) r8
            android.graphics.Bitmap$CompressFormat r0 = r7.a(r8, r10)
            java.lang.String r1 = "encode: [%dx%d] %s"
            int r2 = r8.getWidth()
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)
            int r3 = r8.getHeight()
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)
            com.ss.union.game.sdk.core.glide.util.pool.GlideTrace.beginSectionFormat(r1, r2, r3, r0)
            long r1 = com.ss.union.game.sdk.core.glide.util.LogTime.getLogTime()     // Catch: java.lang.Throwable -> Lc6
            com.ss.union.game.sdk.core.glide.load.Option<java.lang.Integer> r3 = com.ss.union.game.sdk.core.glide.load.resource.bitmap.BitmapEncoder.COMPRESSION_QUALITY     // Catch: java.lang.Throwable -> Lc6
            java.lang.Object r3 = r10.get(r3)     // Catch: java.lang.Throwable -> Lc6
            java.lang.Integer r3 = (java.lang.Integer) r3     // Catch: java.lang.Throwable -> Lc6
            int r3 = r3.intValue()     // Catch: java.lang.Throwable -> Lc6
            r4 = 0
            r5 = 0
            java.io.FileOutputStream r6 = new java.io.FileOutputStream     // Catch: java.lang.Throwable -> L55 java.io.IOException -> L57
            r6.<init>(r9)     // Catch: java.lang.Throwable -> L55 java.io.IOException -> L57
            com.ss.union.game.sdk.core.glide.load.engine.bitmap_recycle.ArrayPool r9 = r7.b     // Catch: java.lang.Throwable -> L4f java.io.IOException -> L52
            if (r9 == 0) goto L43
            com.ss.union.game.sdk.core.glide.load.data.BufferedOutputStream r9 = new com.ss.union.game.sdk.core.glide.load.data.BufferedOutputStream     // Catch: java.lang.Throwable -> L4f java.io.IOException -> L52
            com.ss.union.game.sdk.core.glide.load.engine.bitmap_recycle.ArrayPool r5 = r7.b     // Catch: java.lang.Throwable -> L4f java.io.IOException -> L52
            r9.<init>(r6, r5)     // Catch: java.lang.Throwable -> L4f java.io.IOException -> L52
            r5 = r9
            goto L44
        L43:
            r5 = r6
        L44:
            r8.compress(r0, r3, r5)     // Catch: java.lang.Throwable -> L55 java.io.IOException -> L57
            r5.close()     // Catch: java.lang.Throwable -> L55 java.io.IOException -> L57
            r4 = 1
        L4b:
            r5.close()     // Catch: java.io.IOException -> L6b java.lang.Throwable -> Lc6
            goto L6b
        L4f:
            r8 = move-exception
            r5 = r6
            goto Lc0
        L52:
            r9 = move-exception
            r5 = r6
            goto L58
        L55:
            r8 = move-exception
            goto Lc0
        L57:
            r9 = move-exception
        L58:
            java.lang.String r3 = "BitmapEncoder"
            r6 = 3
            boolean r3 = android.util.Log.isLoggable(r3, r6)     // Catch: java.lang.Throwable -> L55
            if (r3 == 0) goto L68
            java.lang.String r3 = "BitmapEncoder"
            java.lang.String r6 = "Failed to encode Bitmap"
            android.util.Log.d(r3, r6, r9)     // Catch: java.lang.Throwable -> L55
        L68:
            if (r5 == 0) goto L6b
            goto L4b
        L6b:
            java.lang.String r9 = "BitmapEncoder"
            r3 = 2
            boolean r9 = android.util.Log.isLoggable(r9, r3)     // Catch: java.lang.Throwable -> Lc6
            if (r9 == 0) goto Lbc
            java.lang.String r9 = "BitmapEncoder"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> Lc6
            r3.<init>()     // Catch: java.lang.Throwable -> Lc6
            java.lang.String r5 = "Compressed with type: "
            r3.append(r5)     // Catch: java.lang.Throwable -> Lc6
            r3.append(r0)     // Catch: java.lang.Throwable -> Lc6
            java.lang.String r0 = " of size "
            r3.append(r0)     // Catch: java.lang.Throwable -> Lc6
            int r0 = com.ss.union.game.sdk.core.glide.util.Util.getBitmapByteSize(r8)     // Catch: java.lang.Throwable -> Lc6
            r3.append(r0)     // Catch: java.lang.Throwable -> Lc6
            java.lang.String r0 = " in "
            r3.append(r0)     // Catch: java.lang.Throwable -> Lc6
            double r0 = com.ss.union.game.sdk.core.glide.util.LogTime.getElapsedMillis(r1)     // Catch: java.lang.Throwable -> Lc6
            r3.append(r0)     // Catch: java.lang.Throwable -> Lc6
            java.lang.String r0 = ", options format: "
            r3.append(r0)     // Catch: java.lang.Throwable -> Lc6
            com.ss.union.game.sdk.core.glide.load.Option<android.graphics.Bitmap$CompressFormat> r0 = com.ss.union.game.sdk.core.glide.load.resource.bitmap.BitmapEncoder.COMPRESSION_FORMAT     // Catch: java.lang.Throwable -> Lc6
            java.lang.Object r10 = r10.get(r0)     // Catch: java.lang.Throwable -> Lc6
            r3.append(r10)     // Catch: java.lang.Throwable -> Lc6
            java.lang.String r10 = ", hasAlpha: "
            r3.append(r10)     // Catch: java.lang.Throwable -> Lc6
            boolean r8 = r8.hasAlpha()     // Catch: java.lang.Throwable -> Lc6
            r3.append(r8)     // Catch: java.lang.Throwable -> Lc6
            java.lang.String r8 = r3.toString()     // Catch: java.lang.Throwable -> Lc6
            android.util.Log.v(r9, r8)     // Catch: java.lang.Throwable -> Lc6
        Lbc:
            com.ss.union.game.sdk.core.glide.util.pool.GlideTrace.endSection()
            return r4
        Lc0:
            if (r5 == 0) goto Lc5
            r5.close()     // Catch: java.io.IOException -> Lc5 java.lang.Throwable -> Lc6
        Lc5:
            throw r8     // Catch: java.lang.Throwable -> Lc6
        Lc6:
            r8 = move-exception
            com.ss.union.game.sdk.core.glide.util.pool.GlideTrace.endSection()
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ss.union.game.sdk.core.glide.load.resource.bitmap.BitmapEncoder.encode(com.ss.union.game.sdk.core.glide.load.engine.Resource, java.io.File, com.ss.union.game.sdk.core.glide.load.Options):boolean");
    }

    private Bitmap.CompressFormat a(Bitmap bitmap, Options options) {
        Bitmap.CompressFormat compressFormat = (Bitmap.CompressFormat) options.get(COMPRESSION_FORMAT);
        if (compressFormat != null) {
            return compressFormat;
        }
        if (bitmap.hasAlpha()) {
            return Bitmap.CompressFormat.PNG;
        }
        return Bitmap.CompressFormat.JPEG;
    }

    @Override // com.ss.union.game.sdk.core.glide.load.ResourceEncoder
    public EncodeStrategy getEncodeStrategy(Options options) {
        return EncodeStrategy.TRANSFORMED;
    }
}
