package com.ss.union.game.sdk.core.glide.load.model;

import android.os.ParcelFileDescriptor;
import android.util.Log;
import com.ss.android.socialbase.downloader.utils.DownloadExpSwitchCode;
import com.ss.union.game.sdk.core.glide.Priority;
import com.ss.union.game.sdk.core.glide.load.DataSource;
import com.ss.union.game.sdk.core.glide.load.Options;
import com.ss.union.game.sdk.core.glide.load.data.DataFetcher;
import com.ss.union.game.sdk.core.glide.load.model.ModelLoader;
import com.ss.union.game.sdk.core.glide.signature.ObjectKey;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class FileLoader<Data> implements ModelLoader<File, Data> {
    private static final String a = "FileLoader";
    private final FileOpener<Data> b;

    public interface FileOpener<Data> {
        void close(Data data) throws IOException;

        Class<Data> getDataClass();

        Data open(File file) throws FileNotFoundException;
    }

    @Override // com.ss.union.game.sdk.core.glide.load.model.ModelLoader
    public boolean handles(File file) {
        return true;
    }

    public FileLoader(FileOpener<Data> fileOpener) {
        this.b = fileOpener;
    }

    @Override // com.ss.union.game.sdk.core.glide.load.model.ModelLoader
    public ModelLoader.LoadData<Data> buildLoadData(File file, int i, int i2, Options options) {
        return new ModelLoader.LoadData<>(new ObjectKey(file), new a(file, this.b));
    }

    private static final class a<Data> implements DataFetcher<Data> {
        private final File a;
        private final FileOpener<Data> b;
        private Data c;

        @Override // com.ss.union.game.sdk.core.glide.load.data.DataFetcher
        public void cancel() {
        }

        a(File file, FileOpener<Data> fileOpener) {
            this.a = file;
            this.b = fileOpener;
        }

        @Override // com.ss.union.game.sdk.core.glide.load.data.DataFetcher
        public void loadData(Priority priority, DataFetcher.DataCallback<? super Data> dataCallback) {
            try {
                this.c = this.b.open(this.a);
                dataCallback.onDataReady(this.c);
            } catch (FileNotFoundException e) {
                if (Log.isLoggable(FileLoader.a, 3)) {
                    Log.d(FileLoader.a, "Failed to open file", e);
                }
                dataCallback.onLoadFailed(e);
            }
        }

        @Override // com.ss.union.game.sdk.core.glide.load.data.DataFetcher
        public void cleanup() {
            if (this.c != null) {
                try {
                    this.b.close(this.c);
                } catch (IOException unused) {
                }
            }
        }

        @Override // com.ss.union.game.sdk.core.glide.load.data.DataFetcher
        public Class<Data> getDataClass() {
            return this.b.getDataClass();
        }

        @Override // com.ss.union.game.sdk.core.glide.load.data.DataFetcher
        public DataSource getDataSource() {
            return DataSource.LOCAL;
        }
    }

    public static class Factory<Data> implements ModelLoaderFactory<File, Data> {
        private final FileOpener<Data> a;

        @Override // com.ss.union.game.sdk.core.glide.load.model.ModelLoaderFactory
        public final void teardown() {
        }

        public Factory(FileOpener<Data> fileOpener) {
            this.a = fileOpener;
        }

        @Override // com.ss.union.game.sdk.core.glide.load.model.ModelLoaderFactory
        public final ModelLoader<File, Data> build(MultiModelLoaderFactory multiModelLoaderFactory) {
            return new FileLoader(this.a);
        }
    }

    public static class StreamFactory extends Factory<InputStream> {
        public StreamFactory() {
            super(new FileOpener<InputStream>() { // from class: com.ss.union.game.sdk.core.glide.load.model.FileLoader.StreamFactory.1
                @Override // com.ss.union.game.sdk.core.glide.load.model.FileLoader.FileOpener
                /* renamed from: a, reason: merged with bridge method [inline-methods] */
                public InputStream open(File file) throws FileNotFoundException {
                    return new FileInputStream(file);
                }

                @Override // com.ss.union.game.sdk.core.glide.load.model.FileLoader.FileOpener
                /* renamed from: a, reason: merged with bridge method [inline-methods] */
                public void close(InputStream inputStream) throws IOException {
                    inputStream.close();
                }

                @Override // com.ss.union.game.sdk.core.glide.load.model.FileLoader.FileOpener
                public Class<InputStream> getDataClass() {
                    return InputStream.class;
                }
            });
        }
    }

    public static class FileDescriptorFactory extends Factory<ParcelFileDescriptor> {
        public FileDescriptorFactory() {
            super(new FileOpener<ParcelFileDescriptor>() { // from class: com.ss.union.game.sdk.core.glide.load.model.FileLoader.FileDescriptorFactory.1
                @Override // com.ss.union.game.sdk.core.glide.load.model.FileLoader.FileOpener
                /* renamed from: a, reason: merged with bridge method [inline-methods] */
                public ParcelFileDescriptor open(File file) throws FileNotFoundException {
                    return ParcelFileDescriptor.open(file, DownloadExpSwitchCode.BUGFIX_GETPACKAGEINFO_BY_UNZIP);
                }

                @Override // com.ss.union.game.sdk.core.glide.load.model.FileLoader.FileOpener
                /* renamed from: a, reason: merged with bridge method [inline-methods] */
                public void close(ParcelFileDescriptor parcelFileDescriptor) throws IOException {
                    parcelFileDescriptor.close();
                }

                @Override // com.ss.union.game.sdk.core.glide.load.model.FileLoader.FileOpener
                public Class<ParcelFileDescriptor> getDataClass() {
                    return ParcelFileDescriptor.class;
                }
            });
        }
    }
}
