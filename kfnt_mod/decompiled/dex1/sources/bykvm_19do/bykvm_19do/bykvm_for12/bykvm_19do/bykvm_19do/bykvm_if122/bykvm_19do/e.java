package bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do;

import java.io.IOException;
import java.nio.channels.ReadableByteChannel;
import java.nio.charset.Charset;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public interface e extends s, ReadableByteChannel {
    long a(byte b2) throws IOException;

    @Deprecated
    c a();

    String a(Charset charset) throws IOException;

    void a(long j) throws IOException;

    boolean a(long j, f fVar) throws IOException;

    f b(long j) throws IOException;

    String c(long j) throws IOException;

    short c() throws IOException;

    String d() throws IOException;

    byte[] d(long j) throws IOException;

    int e() throws IOException;

    boolean f() throws IOException;

    long g() throws IOException;

    byte readByte() throws IOException;

    void readFully(byte[] bArr) throws IOException;

    int readInt() throws IOException;

    short readShort() throws IOException;

    void skip(long j) throws IOException;
}
