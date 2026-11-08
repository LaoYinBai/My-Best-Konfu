package com.kwad.sdk.core.a;

import com.liulishuo.filedownloader.model.FileDownloadStatus;
import java.nio.charset.Charset;
import java.util.Arrays;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class c {
    private static final Charset a = Charset.forName("ISO-8859-1");

    public static class a {
        static final a a = new a(false, false);
        static final a b = new a(true, false);
        private static final int[] c = new int[256];
        private static final int[] d = new int[256];
        private final boolean e;
        private final boolean f;

        static {
            Arrays.fill(c, -1);
            for (int i = 0; i < b.d.length; i++) {
                c[b.d[i]] = i;
            }
            c[61] = -2;
            Arrays.fill(d, -1);
            for (int i2 = 0; i2 < b.e.length; i2++) {
                d[b.e[i2]] = i2;
            }
            d[61] = -2;
        }

        private a(boolean z, boolean z2) {
            this.e = z;
            this.f = z2;
        }

        private int a(byte[] bArr, int i, int i2) {
            int i3;
            int[] iArr = this.e ? d : c;
            int i4 = i2 - i;
            int i5 = 0;
            if (i4 == 0) {
                return 0;
            }
            if (i4 < 2) {
                if (this.f && iArr[0] == -1) {
                    return 0;
                }
                throw new IllegalArgumentException("Input byte[] should at least have 2 bytes for base64 bytes");
            }
            if (this.f) {
                int i6 = 0;
                while (true) {
                    if (i >= i2) {
                        break;
                    }
                    int i7 = i + 1;
                    int i8 = bArr[i] & FileDownloadStatus.error;
                    if (i8 == 61) {
                        i4 -= (i2 - i7) + 1;
                        break;
                    }
                    if (iArr[i8] == -1) {
                        i6++;
                    }
                    i = i7;
                }
                i4 -= i6;
            } else if (bArr[i2 - 1] == 61) {
                i5 = bArr[i2 - 2] == 61 ? 2 : 1;
            }
            if (i5 == 0 && (i3 = i4 & 3) != 0) {
                i5 = 4 - i3;
            }
            return (((i4 + 3) / 4) * 3) - i5;
        }

        /* JADX WARN: Code restructure failed: missing block: B:19:0x0030, code lost:
        
            if (r4 != 18) goto L33;
         */
        /* JADX WARN: Code restructure failed: missing block: B:33:0x0080, code lost:
        
            if (r4 != 6) goto L35;
         */
        /* JADX WARN: Code restructure failed: missing block: B:34:0x0082, code lost:
        
            r14[r5] = (byte) (r3 >> 16);
            r5 = r5 + 1;
         */
        /* JADX WARN: Code restructure failed: missing block: B:35:0x008b, code lost:
        
            if (r4 != 0) goto L37;
         */
        /* JADX WARN: Code restructure failed: missing block: B:36:0x008d, code lost:
        
            r1 = r5 + 1;
            r14[r5] = (byte) (r3 >> 16);
            r5 = r1 + 1;
            r14[r1] = (byte) (r3 >> 8);
         */
        /* JADX WARN: Code restructure failed: missing block: B:38:0x009e, code lost:
        
            if (r4 == 12) goto L49;
         */
        /* JADX WARN: Code restructure failed: missing block: B:39:0x00a0, code lost:
        
            if (r12 >= r13) goto L57;
         */
        /* JADX WARN: Code restructure failed: missing block: B:41:0x00a4, code lost:
        
            if (r10.f == false) goto L58;
         */
        /* JADX WARN: Code restructure failed: missing block: B:42:0x00a6, code lost:
        
            r14 = r12 + 1;
         */
        /* JADX WARN: Code restructure failed: missing block: B:43:0x00ac, code lost:
        
            if (r0[r11[r12]] >= 0) goto L59;
         */
        /* JADX WARN: Code restructure failed: missing block: B:44:0x00ae, code lost:
        
            r12 = r14;
         */
        /* JADX WARN: Code restructure failed: missing block: B:45:0x00b0, code lost:
        
            r12 = r14;
         */
        /* JADX WARN: Code restructure failed: missing block: B:47:0x00c7, code lost:
        
            throw new java.lang.IllegalArgumentException("Input byte array has incorrect ending byte at " + r12);
         */
        /* JADX WARN: Code restructure failed: missing block: B:48:0x00c8, code lost:
        
            return r5;
         */
        /* JADX WARN: Code restructure failed: missing block: B:50:0x00d0, code lost:
        
            throw new java.lang.IllegalArgumentException("Last unit does not have enough valid bits");
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        private int a(byte[] r11, int r12, int r13, byte[] r14) {
            /*
                Method dump skipped, instructions count: 209
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.kwad.sdk.core.a.c.a.a(byte[], int, int, byte[]):int");
        }

        public byte[] a(String str) {
            return a(str.getBytes(c.a));
        }

        public byte[] a(byte[] bArr) {
            byte[] bArr2 = new byte[a(bArr, 0, bArr.length)];
            int iA = a(bArr, 0, bArr.length, bArr2);
            return iA != bArr2.length ? Arrays.copyOf(bArr2, iA) : bArr2;
        }
    }

    public static class b {
        private final byte[] g;
        private final int h;
        private final boolean i;
        private final boolean j;
        static final b a = new b(false, null, -1, true);
        static final b b = new b(true, null, -1, false);
        private static final char[] d = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '+', '/'};
        private static final char[] e = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '-', '_'};
        private static final byte[] f = {13, 10};
        static final b c = new b(false, f, 76, true);

        private b(boolean z, byte[] bArr, int i, boolean z2) {
            this.i = z;
            this.g = bArr;
            this.h = i;
            this.j = z2;
        }

        private final int a(int i) {
            int i2;
            if (this.j) {
                i2 = ((i + 2) / 3) * 4;
            } else {
                int i3 = i % 3;
                i2 = ((i / 3) * 4) + (i3 == 0 ? 0 : i3 + 1);
            }
            return this.h > 0 ? i2 + (((i2 - 1) / this.h) * this.g.length) : i2;
        }

        private int a(byte[] bArr, int i, int i2, byte[] bArr2) {
            char[] cArr = this.i ? e : d;
            int i3 = ((i2 - i) / 3) * 3;
            int i4 = i + i3;
            if (this.h > 0 && i3 > (this.h / 4) * 3) {
                i3 = (this.h / 4) * 3;
            }
            int i5 = 0;
            while (i < i4) {
                int iMin = Math.min(i + i3, i4);
                int i6 = i;
                int i7 = i5;
                while (i6 < iMin) {
                    int i8 = i6 + 1;
                    int i9 = i8 + 1;
                    int i10 = ((bArr[i6] & FileDownloadStatus.error) << 16) | ((bArr[i8] & FileDownloadStatus.error) << 8);
                    int i11 = i9 + 1;
                    int i12 = i10 | (bArr[i9] & FileDownloadStatus.error);
                    int i13 = i7 + 1;
                    bArr2[i7] = (byte) cArr[(i12 >>> 18) & 63];
                    int i14 = i13 + 1;
                    bArr2[i13] = (byte) cArr[(i12 >>> 12) & 63];
                    int i15 = i14 + 1;
                    bArr2[i14] = (byte) cArr[(i12 >>> 6) & 63];
                    i7 = i15 + 1;
                    bArr2[i15] = (byte) cArr[i12 & 63];
                    i6 = i11;
                }
                int i16 = ((iMin - i) / 3) * 4;
                i5 += i16;
                if (i16 == this.h && iMin < i2) {
                    byte[] bArr3 = this.g;
                    int length = bArr3.length;
                    int i17 = i5;
                    int i18 = 0;
                    while (i18 < length) {
                        bArr2[i17] = bArr3[i18];
                        i18++;
                        i17++;
                    }
                    i5 = i17;
                }
                i = iMin;
            }
            if (i >= i2) {
                return i5;
            }
            int i19 = i + 1;
            int i20 = bArr[i] & FileDownloadStatus.error;
            int i21 = i5 + 1;
            bArr2[i5] = (byte) cArr[i20 >> 2];
            if (i19 == i2) {
                int i22 = i21 + 1;
                bArr2[i21] = (byte) cArr[(i20 << 4) & 63];
                if (!this.j) {
                    return i22;
                }
                int i23 = i22 + 1;
                bArr2[i22] = 61;
                int i24 = i23 + 1;
                bArr2[i23] = 61;
                return i24;
            }
            int i25 = bArr[i19] & FileDownloadStatus.error;
            int i26 = i21 + 1;
            bArr2[i21] = (byte) cArr[((i20 << 4) & 63) | (i25 >> 4)];
            int i27 = i26 + 1;
            bArr2[i26] = (byte) cArr[(i25 << 2) & 63];
            if (!this.j) {
                return i27;
            }
            int i28 = i27 + 1;
            bArr2[i27] = 61;
            return i28;
        }

        public byte[] a(byte[] bArr) {
            byte[] bArr2 = new byte[a(bArr.length)];
            int iA = a(bArr, 0, bArr.length, bArr2);
            return iA != bArr2.length ? Arrays.copyOf(bArr2, iA) : bArr2;
        }
    }

    public static b a() {
        return b.a;
    }

    public static a b() {
        return a.a;
    }
}
