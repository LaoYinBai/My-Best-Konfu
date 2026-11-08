package com.ss.union.game.sdk.core.glide.load.model;

import android.util.Log;
import com.ss.union.game.sdk.core.glide.load.Encoder;
import com.ss.union.game.sdk.core.glide.load.Options;
import com.ss.union.game.sdk.core.glide.load.engine.bitmap_recycle.ArrayPool;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class StreamEncoder implements Encoder<InputStream> {
    private static final String a = "StreamEncoder";
    private final ArrayPool b;

    public StreamEncoder(ArrayPool arrayPool) {
        this.b = arrayPool;
    }

    @Override // com.ss.union.game.sdk.core.glide.load.Encoder
    public boolean encode(InputStream inputStream, File file, Options options) throws Throwable {
        byte[] bArr = (byte[]) this.b.get(65536, byte[].class);
        boolean z = false;
        FileOutputStream fileOutputStream = null;
        try {
            try {
                try {
                    FileOutputStream fileOutputStream2 = new FileOutputStream(file);
                    while (true) {
                        try {
                            int i = inputStream.read(bArr);
                            if (i == -1) {
                                break;
                            }
                            fileOutputStream2.write(bArr, 0, i);
                        } catch (IOException e) {
                            e = e;
                            fileOutputStream = fileOutputStream2;
                            if (Log.isLoggable(a, 3)) {
                                Log.d(a, "Failed to encode data onto the OutputStream", e);
                            }
                            if (fileOutputStream != null) {
                                fileOutputStream.close();
                            }
                            this.b.put(bArr);
                            return z;
                        } catch (Throwable th) {
                            th = th;
                            fileOutputStream = fileOutputStream2;
                            if (fileOutputStream != null) {
                                try {
                                    fileOutputStream.close();
                                } catch (IOException unused) {
                                }
                            }
                            this.b.put(bArr);
                            throw th;
                        }
                    }
                    fileOutputStream2.close();
                    z = true;
                    fileOutputStream2.close();
                } catch (IOException unused2) {
                }
            } catch (IOException e2) {
                e = e2;
            }
            this.b.put(bArr);
            return z;
        } catch (Throwable th2) {
            th = th2;
        }
    }
}
