package com.bumptech.glide.load.model;

import android.os.ParcelFileDescriptor;
import android.util.Log;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.data.DataFetcher;
import java.io.InputStream;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class ImageVideoModelLoader<A> implements ModelLoader<A, ImageVideoWrapper> {
    private static final String TAG = "IVML";
    private final ModelLoader<A, ParcelFileDescriptor> fileDescriptorLoader;
    private final ModelLoader<A, InputStream> streamLoader;

    public ImageVideoModelLoader(ModelLoader<A, InputStream> modelLoader, ModelLoader<A, ParcelFileDescriptor> modelLoader2) {
        if (modelLoader == null && modelLoader2 == null) {
            throw new NullPointerException("At least one of streamLoader and fileDescriptorLoader must be non null");
        }
        this.streamLoader = modelLoader;
        this.fileDescriptorLoader = modelLoader2;
    }

    @Override // com.bumptech.glide.load.model.ModelLoader
    public DataFetcher<ImageVideoWrapper> getResourceFetcher(A a2, int i, int i2) {
        DataFetcher<InputStream> resourceFetcher = this.streamLoader != null ? this.streamLoader.getResourceFetcher(a2, i, i2) : null;
        DataFetcher<ParcelFileDescriptor> resourceFetcher2 = this.fileDescriptorLoader != null ? this.fileDescriptorLoader.getResourceFetcher(a2, i, i2) : null;
        if (resourceFetcher == null && resourceFetcher2 == null) {
            return null;
        }
        return new ImageVideoFetcher(resourceFetcher, resourceFetcher2);
    }

    static class ImageVideoFetcher implements DataFetcher<ImageVideoWrapper> {
        private final DataFetcher<ParcelFileDescriptor> fileDescriptorFetcher;
        private final DataFetcher<InputStream> streamFetcher;

        public ImageVideoFetcher(DataFetcher<InputStream> dataFetcher, DataFetcher<ParcelFileDescriptor> dataFetcher2) {
            this.streamFetcher = dataFetcher;
            this.fileDescriptorFetcher = dataFetcher2;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.bumptech.glide.load.data.DataFetcher
        public ImageVideoWrapper loadData(Priority priority) throws Exception {
            InputStream inputStreamLoadData;
            ParcelFileDescriptor parcelFileDescriptorLoadData;
            if (this.streamFetcher != null) {
                try {
                    inputStreamLoadData = this.streamFetcher.loadData(priority);
                } catch (Exception e) {
                    if (Log.isLoggable(ImageVideoModelLoader.TAG, 2)) {
                        Log.v(ImageVideoModelLoader.TAG, "Exception fetching input stream, trying ParcelFileDescriptor", e);
                    }
                    if (this.fileDescriptorFetcher == null) {
                        throw e;
                    }
                }
            } else {
                inputStreamLoadData = null;
            }
            if (this.fileDescriptorFetcher != null) {
                try {
                    parcelFileDescriptorLoadData = this.fileDescriptorFetcher.loadData(priority);
                } catch (Exception e2) {
                    if (Log.isLoggable(ImageVideoModelLoader.TAG, 2)) {
                        Log.v(ImageVideoModelLoader.TAG, "Exception fetching ParcelFileDescriptor", e2);
                    }
                    if (inputStreamLoadData == null) {
                        throw e2;
                    }
                }
            } else {
                parcelFileDescriptorLoadData = null;
            }
            return new ImageVideoWrapper(inputStreamLoadData, parcelFileDescriptorLoadData);
        }

        @Override // com.bumptech.glide.load.data.DataFetcher
        public void cleanup() {
            if (this.streamFetcher != null) {
                this.streamFetcher.cleanup();
            }
            if (this.fileDescriptorFetcher != null) {
                this.fileDescriptorFetcher.cleanup();
            }
        }

        @Override // com.bumptech.glide.load.data.DataFetcher
        public String getId() {
            if (this.streamFetcher != null) {
                return this.streamFetcher.getId();
            }
            return this.fileDescriptorFetcher.getId();
        }

        @Override // com.bumptech.glide.load.data.DataFetcher
        public void cancel() {
            if (this.streamFetcher != null) {
                this.streamFetcher.cancel();
            }
            if (this.fileDescriptorFetcher != null) {
                this.fileDescriptorFetcher.cancel();
            }
        }
    }
}
