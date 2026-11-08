package com.bytedance.pangle.f;

import android.system.Os;
import android.system.OsConstants;
import java.io.FileDescriptor;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
final class l implements k {

    /* renamed from: a, reason: collision with root package name */
    private static final long f4618a = Os.sysconf(OsConstants._SC_PAGESIZE);

    /* renamed from: b, reason: collision with root package name */
    private final FileDescriptor f4619b;

    /* renamed from: c, reason: collision with root package name */
    private final long f4620c;
    private final long d;

    l(FileDescriptor fileDescriptor, long j, long j2) {
        this.f4619b = fileDescriptor;
        this.f4620c = j;
        this.d = j2;
    }

    @Override // com.bytedance.pangle.f.k
    public final long a() {
        return this.d;
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x005c  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x00fc  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x0128 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:80:0x014e A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:85:0x0124 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:91:0x0060 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:93:0x00c8 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    @Override // com.bytedance.pangle.f.k
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void a(com.bytedance.pangle.f.j r20, long r21, int r23) throws java.lang.Throwable {
        /*
            Method dump skipped, instructions count: 338
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bytedance.pangle.f.l.a(com.bytedance.pangle.f.j, long, int):void");
    }
}
