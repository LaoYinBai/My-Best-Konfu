package c;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.channels.WritableByteChannel;
import java.nio.charset.Charset;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public interface d extends x, WritableByteChannel {
    d I() throws IOException;

    long a(y yVar) throws IOException;

    d a(y yVar, long j) throws IOException;

    d b(String str) throws IOException;

    d b(String str, int i, int i2) throws IOException;

    d b(String str, int i, int i2, Charset charset) throws IOException;

    d b(String str, Charset charset) throws IOException;

    c c();

    d c(byte[] bArr, int i, int i2) throws IOException;

    d d(byte[] bArr) throws IOException;

    OutputStream d();

    d f() throws IOException;

    @Override // c.x, java.io.Flushable
    void flush() throws IOException;

    d g(f fVar) throws IOException;

    d i(int i) throws IOException;

    d j(int i) throws IOException;

    d k(int i) throws IOException;

    d l(int i) throws IOException;

    d m(int i) throws IOException;

    d n(int i) throws IOException;

    d n(long j) throws IOException;

    d o(long j) throws IOException;

    d p(long j) throws IOException;

    d q(long j) throws IOException;
}
