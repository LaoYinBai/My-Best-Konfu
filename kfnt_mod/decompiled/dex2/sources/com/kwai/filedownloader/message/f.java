package com.kwai.filedownloader.message;

import com.kwai.filedownloader.download.d;
import com.kwai.filedownloader.message.MessageSnapshot;
import com.kwai.filedownloader.message.a;
import com.kwai.filedownloader.message.d;
import com.kwai.filedownloader.message.h;
import java.io.File;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class f {
    public static MessageSnapshot a(byte b, com.kwai.filedownloader.c.c cVar, d.a aVar) {
        int iA = cVar.a();
        if (b == -4) {
            throw new IllegalStateException(com.kwai.filedownloader.e.f.a("please use #catchWarn instead %d", Integer.valueOf(iA)));
        }
        switch (b) {
            case -3:
                return cVar.q() ? new d.b(iA, false, cVar.h()) : new h.b(iA, false, (int) cVar.h());
            case -2:
            case 0:
            case 4:
            default:
                String strA = com.kwai.filedownloader.e.f.a("it can't takes a snapshot for the task(%s) when its status is %d,", cVar, Byte.valueOf(b));
                com.kwai.filedownloader.e.d.d(f.class, "it can't takes a snapshot for the task(%s) when its status is %d,", cVar, Byte.valueOf(b));
                IllegalStateException illegalStateException = aVar.b() != null ? new IllegalStateException(strA, aVar.b()) : new IllegalStateException(strA);
                return cVar.q() ? new d.C0097d(iA, cVar.g(), illegalStateException) : new h.d(iA, (int) cVar.g(), illegalStateException);
            case -1:
                return cVar.q() ? new d.C0097d(iA, cVar.g(), aVar.b()) : new h.d(iA, (int) cVar.g(), aVar.b());
            case 1:
                return cVar.q() ? new d.f(iA, cVar.g(), cVar.h()) : new h.f(iA, (int) cVar.g(), (int) cVar.h());
            case 2:
                String strM = cVar.l() ? cVar.m() : null;
                return cVar.q() ? new d.c(iA, aVar.a(), cVar.h(), cVar.j(), strM) : new h.c(iA, aVar.a(), (int) cVar.h(), cVar.j(), strM);
            case 3:
                return cVar.q() ? new d.g(iA, cVar.g()) : new h.g(iA, (int) cVar.g());
            case 5:
                return cVar.q() ? new d.h(iA, cVar.g(), aVar.b(), aVar.c()) : new h.C0098h(iA, (int) cVar.g(), aVar.b(), aVar.c());
            case 6:
                return new MessageSnapshot.b(iA);
        }
    }

    public static MessageSnapshot a(int i, long j, long j2, boolean z) {
        return j2 > 2147483647L ? z ? new d.i(i, j, j2) : new d.j(i, j, j2) : z ? new h.i(i, (int) j, (int) j2) : new h.j(i, (int) j, (int) j2);
    }

    public static MessageSnapshot a(int i, long j, Throwable th) {
        return j > 2147483647L ? new d.C0097d(i, j, th) : new h.d(i, (int) j, th);
    }

    public static MessageSnapshot a(int i, File file, boolean z) {
        long length = file.length();
        return length > 2147483647L ? z ? new d.a(i, true, length) : new d.b(i, true, length) : z ? new h.a(i, true, (int) length) : new h.b(i, true, (int) length);
    }

    public static MessageSnapshot a(com.kwai.filedownloader.a aVar) {
        return aVar.D() ? new d.e(aVar.h(), aVar.r(), aVar.t()) : new h.e(aVar.h(), aVar.q(), aVar.s());
    }

    public static MessageSnapshot a(MessageSnapshot messageSnapshot) {
        if (messageSnapshot.b() == -3) {
            return new a.C0096a(messageSnapshot);
        }
        throw new IllegalStateException(com.kwai.filedownloader.e.f.a("take block completed snapshot, must has already be completed. %d %d", Integer.valueOf(messageSnapshot.m()), Byte.valueOf(messageSnapshot.b())));
    }
}
