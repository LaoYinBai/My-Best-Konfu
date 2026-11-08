package com.ss.union.game.sdk.core.luban;

import java.io.IOException;
import java.io.InputStream;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public abstract class InputStreamAdapter implements InputStreamProvider {
    private InputStream inputStream;

    public abstract InputStream openInternal() throws IOException;

    @Override // com.ss.union.game.sdk.core.luban.InputStreamProvider
    public InputStream open() throws IOException {
        close();
        this.inputStream = openInternal();
        return this.inputStream;
    }

    @Override // com.ss.union.game.sdk.core.luban.InputStreamProvider
    public void close() {
        if (this.inputStream != null) {
            try {
                this.inputStream.close();
            } catch (IOException unused) {
            } catch (Throwable th) {
                this.inputStream = null;
                throw th;
            }
            this.inputStream = null;
        }
    }
}
