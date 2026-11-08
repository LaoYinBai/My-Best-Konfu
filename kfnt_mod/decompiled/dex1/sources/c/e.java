package c;

import java.io.IOException;
import java.io.InputStream;
import java.nio.channels.ReadableByteChannel;
import java.nio.charset.Charset;
import javax.annotation.Nullable;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public interface e extends y, ReadableByteChannel {
    int a(q qVar) throws IOException;

    int a(byte[] bArr) throws IOException;

    int a(byte[] bArr, int i, int i2) throws IOException;

    long a(byte b2) throws IOException;

    long a(byte b2, long j) throws IOException;

    long a(byte b2, long j, long j2) throws IOException;

    long a(f fVar, long j) throws IOException;

    long a(x xVar) throws IOException;

    String a(long j, Charset charset) throws IOException;

    String a(Charset charset) throws IOException;

    void a(long j) throws IOException;

    boolean a(long j, f fVar) throws IOException;

    boolean a(long j, f fVar, int i, int i2) throws IOException;

    long b(f fVar) throws IOException;

    long b(f fVar, long j) throws IOException;

    void b(c cVar, long j) throws IOException;

    void b(byte[] bArr) throws IOException;

    boolean b(long j) throws IOException;

    long c(f fVar) throws IOException;

    c c();

    f d(long j) throws IOException;

    String e(long j) throws IOException;

    String f(long j) throws IOException;

    boolean g() throws IOException;

    InputStream h();

    byte[] h(long j) throws IOException;

    void i(long j) throws IOException;

    byte j() throws IOException;

    short k() throws IOException;

    int l() throws IOException;

    long m() throws IOException;

    short n() throws IOException;

    int o() throws IOException;

    long p() throws IOException;

    long q() throws IOException;

    long r() throws IOException;

    f s() throws IOException;

    String t() throws IOException;

    @Nullable
    String u() throws IOException;

    String v() throws IOException;

    int w() throws IOException;

    byte[] x() throws IOException;
}
