package c;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Nullable;
import org.codehaus.mojo.animal_sniffer.IgnoreJRERequirement;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public final class p {

    /* renamed from: a, reason: collision with root package name */
    static final Logger f2160a = Logger.getLogger(p.class.getName());

    private p() {
    }

    public static e a(y yVar) {
        return new t(yVar);
    }

    public static d a(x xVar) {
        return new s(xVar);
    }

    public static x a(OutputStream outputStream) {
        return a(outputStream, new z());
    }

    private static x a(final OutputStream outputStream, final z zVar) {
        if (outputStream == null) {
            throw new IllegalArgumentException("out == null");
        }
        if (zVar == null) {
            throw new IllegalArgumentException("timeout == null");
        }
        return new x() { // from class: c.p.1
            @Override // c.x
            public void a_(c cVar, long j) throws IOException {
                ab.a(cVar.f2132c, 0L, j);
                while (j > 0) {
                    zVar.g();
                    u uVar = cVar.f2131b;
                    int iMin = (int) Math.min(j, uVar.e - uVar.d);
                    outputStream.write(uVar.f2184c, uVar.d, iMin);
                    uVar.d += iMin;
                    long j2 = iMin;
                    j -= j2;
                    cVar.f2132c -= j2;
                    if (uVar.d == uVar.e) {
                        cVar.f2131b = uVar.c();
                        v.a(uVar);
                    }
                }
            }

            @Override // c.x, java.io.Flushable
            public void flush() throws IOException {
                outputStream.flush();
            }

            @Override // c.x, java.io.Closeable, java.lang.AutoCloseable
            public void close() throws IOException {
                outputStream.close();
            }

            @Override // c.x
            public z a() {
                return zVar;
            }

            public String toString() {
                return "sink(" + outputStream + ")";
            }
        };
    }

    public static x a(Socket socket) throws IOException {
        if (socket == null) {
            throw new IllegalArgumentException("socket == null");
        }
        if (socket.getOutputStream() == null) {
            throw new IOException("socket's output stream == null");
        }
        a aVarC = c(socket);
        return aVarC.a(a(socket.getOutputStream(), aVarC));
    }

    public static y a(InputStream inputStream) {
        return a(inputStream, new z());
    }

    private static y a(final InputStream inputStream, final z zVar) {
        if (inputStream == null) {
            throw new IllegalArgumentException("in == null");
        }
        if (zVar == null) {
            throw new IllegalArgumentException("timeout == null");
        }
        return new y() { // from class: c.p.2
            @Override // c.y
            public long a(c cVar, long j) throws IOException {
                if (j < 0) {
                    throw new IllegalArgumentException("byteCount < 0: " + j);
                }
                if (j == 0) {
                    return 0L;
                }
                try {
                    zVar.g();
                    u uVarG = cVar.g(1);
                    int i = inputStream.read(uVarG.f2184c, uVarG.e, (int) Math.min(j, 8192 - uVarG.e));
                    if (i == -1) {
                        return -1L;
                    }
                    uVarG.e += i;
                    long j2 = i;
                    cVar.f2132c += j2;
                    return j2;
                } catch (AssertionError e) {
                    if (p.a(e)) {
                        throw new IOException(e);
                    }
                    throw e;
                }
            }

            @Override // c.y, java.io.Closeable, java.lang.AutoCloseable
            public void close() throws IOException {
                inputStream.close();
            }

            @Override // c.y
            public z a() {
                return zVar;
            }

            public String toString() {
                return "source(" + inputStream + ")";
            }
        };
    }

    public static y a(File file) throws FileNotFoundException {
        if (file == null) {
            throw new IllegalArgumentException("file == null");
        }
        return a(new FileInputStream(file));
    }

    @IgnoreJRERequirement
    public static y a(Path path, OpenOption... openOptionArr) throws IOException {
        if (path == null) {
            throw new IllegalArgumentException("path == null");
        }
        return a(Files.newInputStream(path, openOptionArr));
    }

    public static x b(File file) throws FileNotFoundException {
        if (file == null) {
            throw new IllegalArgumentException("file == null");
        }
        return a(new FileOutputStream(file));
    }

    public static x c(File file) throws FileNotFoundException {
        if (file == null) {
            throw new IllegalArgumentException("file == null");
        }
        return a(new FileOutputStream(file, true));
    }

    @IgnoreJRERequirement
    public static x b(Path path, OpenOption... openOptionArr) throws IOException {
        if (path == null) {
            throw new IllegalArgumentException("path == null");
        }
        return a(Files.newOutputStream(path, openOptionArr));
    }

    public static x a() {
        return new x() { // from class: c.p.3
            @Override // c.x, java.io.Closeable, java.lang.AutoCloseable
            public void close() throws IOException {
            }

            @Override // c.x, java.io.Flushable
            public void flush() throws IOException {
            }

            @Override // c.x
            public void a_(c cVar, long j) throws IOException {
                cVar.i(j);
            }

            @Override // c.x
            public z a() {
                return z.f2188c;
            }
        };
    }

    public static y b(Socket socket) throws IOException {
        if (socket == null) {
            throw new IllegalArgumentException("socket == null");
        }
        if (socket.getInputStream() == null) {
            throw new IOException("socket's input stream == null");
        }
        a aVarC = c(socket);
        return aVarC.a(a(socket.getInputStream(), aVarC));
    }

    private static a c(final Socket socket) {
        return new a() { // from class: c.p.4
            @Override // c.a
            protected IOException a(@Nullable IOException iOException) {
                SocketTimeoutException socketTimeoutException = new SocketTimeoutException("timeout");
                if (iOException != null) {
                    socketTimeoutException.initCause(iOException);
                }
                return socketTimeoutException;
            }

            @Override // c.a
            protected void a() throws IOException {
                try {
                    socket.close();
                } catch (AssertionError e) {
                    if (p.a(e)) {
                        p.f2160a.log(Level.WARNING, "Failed to close timed out socket " + socket, (Throwable) e);
                        return;
                    }
                    throw e;
                } catch (Exception e2) {
                    p.f2160a.log(Level.WARNING, "Failed to close timed out socket " + socket, (Throwable) e2);
                }
            }
        };
    }

    static boolean a(AssertionError assertionError) {
        return (assertionError.getCause() == null || assertionError.getMessage() == null || !assertionError.getMessage().contains("getsockname failed")) ? false : true;
    }
}
