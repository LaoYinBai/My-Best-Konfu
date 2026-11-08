package com.bytedance.sdk.adnet.game.ok3.okhttp3.internal.io;

import com.bytedance.sdk.adnet.game.ok3.okio.Okio;
import com.bytedance.sdk.adnet.game.ok3.okio.Sink;
import com.bytedance.sdk.adnet.game.ok3.okio.Source;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public interface FileSystem {
    public static final FileSystem SYSTEM = new FileSystem() { // from class: com.bytedance.sdk.adnet.game.ok3.okhttp3.internal.io.FileSystem.1
        @Override // com.bytedance.sdk.adnet.game.ok3.okhttp3.internal.io.FileSystem
        public Sink appendingSink(File file) {
            try {
                return Okio.appendingSink(file);
            } catch (FileNotFoundException unused) {
                file.getParentFile().mkdirs();
                return Okio.appendingSink(file);
            }
        }

        @Override // com.bytedance.sdk.adnet.game.ok3.okhttp3.internal.io.FileSystem
        public void delete(File file) throws IOException {
            if (file.delete() || !file.exists()) {
                return;
            }
            throw new IOException("failed to delete " + file);
        }

        @Override // com.bytedance.sdk.adnet.game.ok3.okhttp3.internal.io.FileSystem
        public void deleteContents(File file) throws IOException {
            File[] fileArrListFiles = file.listFiles();
            if (fileArrListFiles == null) {
                throw new IOException("not a readable directory: " + file);
            }
            for (File file2 : fileArrListFiles) {
                if (file2.isDirectory()) {
                    deleteContents(file2);
                }
                if (!file2.delete()) {
                    throw new IOException("failed to delete " + file2);
                }
            }
        }

        @Override // com.bytedance.sdk.adnet.game.ok3.okhttp3.internal.io.FileSystem
        public boolean exists(File file) {
            return file.exists();
        }

        @Override // com.bytedance.sdk.adnet.game.ok3.okhttp3.internal.io.FileSystem
        public void rename(File file, File file2) throws IOException {
            delete(file2);
            if (file.renameTo(file2)) {
                return;
            }
            throw new IOException("failed to rename " + file + " to " + file2);
        }

        @Override // com.bytedance.sdk.adnet.game.ok3.okhttp3.internal.io.FileSystem
        public Sink sink(File file) {
            try {
                return Okio.sink(file);
            } catch (FileNotFoundException unused) {
                file.getParentFile().mkdirs();
                return Okio.sink(file);
            }
        }

        @Override // com.bytedance.sdk.adnet.game.ok3.okhttp3.internal.io.FileSystem
        public long size(File file) {
            return file.length();
        }

        @Override // com.bytedance.sdk.adnet.game.ok3.okhttp3.internal.io.FileSystem
        public Source source(File file) {
            return Okio.source(file);
        }
    };

    Sink appendingSink(File file);

    void delete(File file);

    void deleteContents(File file);

    boolean exists(File file);

    void rename(File file, File file2);

    Sink sink(File file);

    long size(File file);

    Source source(File file);
}
