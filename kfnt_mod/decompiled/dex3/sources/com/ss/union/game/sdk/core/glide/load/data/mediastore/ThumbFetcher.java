package com.ss.union.game.sdk.core.glide.load.data.mediastore;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;
import com.ss.union.game.sdk.core.glide.Glide;
import com.ss.union.game.sdk.core.glide.Priority;
import com.ss.union.game.sdk.core.glide.load.DataSource;
import com.ss.union.game.sdk.core.glide.load.data.DataFetcher;
import com.ss.union.game.sdk.core.glide.load.data.ExifOrientationStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class ThumbFetcher implements DataFetcher<InputStream> {
    private static final String a = "MediaStoreThumbFetcher";
    private final Uri b;
    private final c c;
    private InputStream d;

    @Override // com.ss.union.game.sdk.core.glide.load.data.DataFetcher
    public void cancel() {
    }

    public static ThumbFetcher buildImageFetcher(Context context, Uri uri) {
        return a(context, uri, new a(context.getContentResolver()));
    }

    public static ThumbFetcher buildVideoFetcher(Context context, Uri uri) {
        return a(context, uri, new b(context.getContentResolver()));
    }

    private static ThumbFetcher a(Context context, Uri uri, com.ss.union.game.sdk.core.glide.load.data.mediastore.b bVar) {
        return new ThumbFetcher(uri, new c(Glide.get(context).getRegistry().getImageHeaderParsers(), bVar, Glide.get(context).getArrayPool(), context.getContentResolver()));
    }

    ThumbFetcher(Uri uri, c cVar) {
        this.b = uri;
        this.c = cVar;
    }

    @Override // com.ss.union.game.sdk.core.glide.load.data.DataFetcher
    public void loadData(Priority priority, DataFetcher.DataCallback<? super InputStream> dataCallback) {
        try {
            this.d = a();
            dataCallback.onDataReady(this.d);
        } catch (FileNotFoundException e) {
            if (Log.isLoggable(a, 3)) {
                Log.d(a, "Failed to find thumbnail file", e);
            }
            dataCallback.onLoadFailed(e);
        }
    }

    private InputStream a() throws FileNotFoundException {
        InputStream inputStreamB = this.c.b(this.b);
        int iA = inputStreamB != null ? this.c.a(this.b) : -1;
        return iA != -1 ? new ExifOrientationStream(inputStreamB, iA) : inputStreamB;
    }

    @Override // com.ss.union.game.sdk.core.glide.load.data.DataFetcher
    public void cleanup() throws IOException {
        if (this.d != null) {
            try {
                this.d.close();
            } catch (IOException unused) {
            }
        }
    }

    @Override // com.ss.union.game.sdk.core.glide.load.data.DataFetcher
    public Class<InputStream> getDataClass() {
        return InputStream.class;
    }

    @Override // com.ss.union.game.sdk.core.glide.load.data.DataFetcher
    public DataSource getDataSource() {
        return DataSource.LOCAL;
    }

    static class b implements com.ss.union.game.sdk.core.glide.load.data.mediastore.b {
        private static final String[] b = {"_data"};
        private static final String c = "kind = 1 AND video_id = ?";
        private final ContentResolver a;

        b(ContentResolver contentResolver) {
            this.a = contentResolver;
        }

        @Override // com.ss.union.game.sdk.core.glide.load.data.mediastore.b
        public Cursor a(Uri uri) {
            return this.a.query(MediaStore.Video.Thumbnails.EXTERNAL_CONTENT_URI, b, c, new String[]{uri.getLastPathSegment()}, null);
        }
    }

    static class a implements com.ss.union.game.sdk.core.glide.load.data.mediastore.b {
        private static final String[] b = {"_data"};
        private static final String c = "kind = 1 AND image_id = ?";
        private final ContentResolver a;

        a(ContentResolver contentResolver) {
            this.a = contentResolver;
        }

        @Override // com.ss.union.game.sdk.core.glide.load.data.mediastore.b
        public Cursor a(Uri uri) {
            return this.a.query(MediaStore.Images.Thumbnails.EXTERNAL_CONTENT_URI, b, c, new String[]{uri.getLastPathSegment()}, null);
        }
    }
}
