package com.kwad.sdk.pngencrypt;

import com.liulishuo.filedownloader.model.FileDownloadStatus;
import java.util.Arrays;
import java.util.zip.Inflater;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class j extends DeflatedChunksSet {
    protected byte[] g;
    protected byte[] h;
    protected final k i;
    protected final e j;
    final p k;
    protected int[] l;

    public j(String str, boolean z, k kVar, e eVar) {
        this(str, z, kVar, eVar, null, null);
    }

    public j(String str, boolean z, k kVar, e eVar, Inflater inflater, byte[] bArr) {
        super(str, z, (eVar != null ? eVar.h() : kVar.k) + 1, kVar.k + 1, inflater, bArr);
        this.l = new int[5];
        this.i = kVar;
        this.j = eVar;
        this.k = new p(kVar, eVar);
        com.kwad.sdk.core.d.a.a("PNG_ENCRYPT", "Creating IDAT set ");
    }

    private void c(int i) {
        int i2 = 1;
        int i3 = 1 - this.i.j;
        while (i2 <= i) {
            this.g[i2] = (byte) (this.a[i2] + (((i3 > 0 ? this.g[i3] & FileDownloadStatus.error : 0) + (this.h[i2] & FileDownloadStatus.error)) / 2));
            i2++;
            i3++;
        }
    }

    private void d(int i) {
        for (int i2 = 1; i2 <= i; i2++) {
            this.g[i2] = this.a[i2];
        }
    }

    private void e(int i) {
        int i2 = 1;
        int i3 = 1 - this.i.j;
        while (i2 <= i) {
            int i4 = 0;
            int i5 = i3 > 0 ? this.g[i3] & FileDownloadStatus.error : 0;
            if (i3 > 0) {
                i4 = this.h[i3] & FileDownloadStatus.error;
            }
            this.g[i2] = (byte) (this.a[i2] + n.a(i5, this.h[i2] & FileDownloadStatus.error, i4));
            i2++;
            i3++;
        }
    }

    private void f(int i) {
        for (int i2 = 1; i2 <= this.i.j; i2++) {
            this.g[i2] = this.a[i2];
        }
        int i3 = this.i.j + 1;
        int i4 = 1;
        while (i3 <= i) {
            this.g[i3] = (byte) (this.a[i3] + this.g[i4]);
            i3++;
            i4++;
        }
    }

    private void g(int i) {
        for (int i2 = 1; i2 <= i; i2++) {
            this.g[i2] = (byte) (this.a[i2] + this.h[i2]);
        }
    }

    @Override // com.kwad.sdk.pngencrypt.DeflatedChunksSet
    protected void a() {
        super.a();
        this.k.a(h());
        i();
        this.k.a(this.g, this.k.m + 1);
    }

    @Override // com.kwad.sdk.pngencrypt.DeflatedChunksSet
    protected int b() {
        return j();
    }

    protected void b(int i) {
        if (this.g == null || this.g.length < this.a.length) {
            this.g = new byte[this.a.length];
            this.h = new byte[this.a.length];
        }
        if (this.k.j == 0) {
            Arrays.fill(this.g, (byte) 0);
        }
        byte[] bArr = this.g;
        this.g = this.h;
        this.h = bArr;
        byte b = this.a[0];
        if (!FilterType.isValidStandard(b)) {
            throw new PngjException("Filter type " + ((int) b) + " invalid");
        }
        FilterType byVal = FilterType.getByVal(b);
        int[] iArr = this.l;
        iArr[b] = iArr[b] + 1;
        this.g[0] = this.a[0];
        switch (byVal) {
            case FILTER_NONE:
                d(i);
                return;
            case FILTER_SUB:
                f(i);
                return;
            case FILTER_UP:
                g(i);
                return;
            case FILTER_AVERAGE:
                c(i);
                return;
            case FILTER_PAETH:
                e(i);
                return;
            default:
                throw new PngjException("Filter type " + ((int) b) + " not implemented");
        }
    }

    @Override // com.kwad.sdk.pngencrypt.DeflatedChunksSet
    protected void c() {
        super.c();
    }

    @Override // com.kwad.sdk.pngencrypt.DeflatedChunksSet
    public void f() {
        super.f();
        this.g = null;
        this.h = null;
    }

    public void i() {
        b(this.k.m);
    }

    public int j() {
        int iH;
        int i = 0;
        if (this.j == null) {
            if (h() < this.i.b - 1) {
                iH = this.i.k;
                i = iH + 1;
            }
        } else if (this.j.a()) {
            iH = this.j.h();
            i = iH + 1;
        }
        if (!this.c) {
            a(i);
        }
        return i;
    }
}
