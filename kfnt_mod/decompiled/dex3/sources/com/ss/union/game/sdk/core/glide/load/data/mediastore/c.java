package com.ss.union.game.sdk.core.glide.load.data.mediastore;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.text.TextUtils;
import com.ss.union.game.sdk.core.glide.load.ImageHeaderParser;
import com.ss.union.game.sdk.core.glide.load.engine.bitmap_recycle.ArrayPool;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
class c {
    private static final String a = "ThumbStreamOpener";
    private static final a b = new a();
    private final a c;
    private final b d;
    private final ArrayPool e;
    private final ContentResolver f;
    private final List<ImageHeaderParser> g;

    c(List<ImageHeaderParser> list, b bVar, ArrayPool arrayPool, ContentResolver contentResolver) {
        this(list, b, bVar, arrayPool, contentResolver);
    }

    c(List<ImageHeaderParser> list, a aVar, b bVar, ArrayPool arrayPool, ContentResolver contentResolver) {
        this.c = aVar;
        this.d = bVar;
        this.e = arrayPool;
        this.f = contentResolver;
        this.g = list;
    }

    /* JADX WARN: Removed duplicated region for block: B:28:0x0047 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    int a(android.net.Uri r7) throws java.lang.Throwable {
        /*
            r6 = this;
            r0 = 0
            android.content.ContentResolver r1 = r6.f     // Catch: java.lang.Throwable -> L17 java.lang.Throwable -> L1a
            java.io.InputStream r1 = r1.openInputStream(r7)     // Catch: java.lang.Throwable -> L17 java.lang.Throwable -> L1a
            java.util.List<com.ss.union.game.sdk.core.glide.load.ImageHeaderParser> r0 = r6.g     // Catch: java.lang.Throwable -> L15 java.lang.Throwable -> L44
            com.ss.union.game.sdk.core.glide.load.engine.bitmap_recycle.ArrayPool r2 = r6.e     // Catch: java.lang.Throwable -> L15 java.lang.Throwable -> L44
            int r0 = com.ss.union.game.sdk.core.glide.load.ImageHeaderParserUtils.getOrientation(r0, r1, r2)     // Catch: java.lang.Throwable -> L15 java.lang.Throwable -> L44
            if (r1 == 0) goto L14
            r1.close()     // Catch: java.io.IOException -> L14
        L14:
            return r0
        L15:
            r0 = move-exception
            goto L1e
        L17:
            r7 = move-exception
            r1 = r0
            goto L45
        L1a:
            r1 = move-exception
            r5 = r1
            r1 = r0
            r0 = r5
        L1e:
            java.lang.String r2 = "ThumbStreamOpener"
            r3 = 3
            boolean r2 = android.util.Log.isLoggable(r2, r3)     // Catch: java.lang.Throwable -> L44
            if (r2 == 0) goto L3d
            java.lang.String r2 = "ThumbStreamOpener"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L44
            r3.<init>()     // Catch: java.lang.Throwable -> L44
            java.lang.String r4 = "Failed to open uri: "
            r3.append(r4)     // Catch: java.lang.Throwable -> L44
            r3.append(r7)     // Catch: java.lang.Throwable -> L44
            java.lang.String r7 = r3.toString()     // Catch: java.lang.Throwable -> L44
            android.util.Log.d(r2, r7, r0)     // Catch: java.lang.Throwable -> L44
        L3d:
            if (r1 == 0) goto L42
            r1.close()     // Catch: java.io.IOException -> L42
        L42:
            r7 = -1
            return r7
        L44:
            r7 = move-exception
        L45:
            if (r1 == 0) goto L4a
            r1.close()     // Catch: java.io.IOException -> L4a
        L4a:
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ss.union.game.sdk.core.glide.load.data.mediastore.c.a(android.net.Uri):int");
    }

    public InputStream b(Uri uri) throws FileNotFoundException {
        String strC = c(uri);
        if (TextUtils.isEmpty(strC)) {
            return null;
        }
        File fileA = this.c.a(strC);
        if (!a(fileA)) {
            return null;
        }
        Uri uriFromFile = Uri.fromFile(fileA);
        try {
            return this.f.openInputStream(uriFromFile);
        } catch (NullPointerException e) {
            throw ((FileNotFoundException) new FileNotFoundException("NPE opening uri: " + uri + " -> " + uriFromFile).initCause(e));
        }
    }

    private String c(Uri uri) {
        Cursor cursorA = this.d.a(uri);
        if (cursorA != null) {
            try {
                if (cursorA.moveToFirst()) {
                    return cursorA.getString(0);
                }
            } finally {
                if (cursorA != null) {
                    cursorA.close();
                }
            }
        }
        if (cursorA != null) {
            cursorA.close();
        }
        return null;
    }

    private boolean a(File file) {
        return this.c.a(file) && 0 < this.c.b(file);
    }
}
