package cp.bd.c;

import android.graphics.Bitmap;
import com.sigmob.sdk.common.Constants;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import kotlin.UByte;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class b {
    private static final String k = "b";
    protected int a;
    protected int b;
    protected int c;
    protected Bitmap d;
    protected Bitmap e;
    protected ArrayList<c> i;
    protected int j;
    private d n;
    private e o;
    private f p;
    private final b l = this;
    protected int f = 0;
    protected int g = 0;
    protected int h = 0;
    private int m = 0;

    class a {
        public byte[] a;
        public int b;

        public a(byte[] bArr, int i) {
            this.b = 14;
            int i2 = bArr[this.b + i] & UByte.MAX_VALUE;
            int i3 = this.b;
            while (true) {
                this.b = i3 + 1;
                if (i2 == 0) {
                    this.a = new byte[this.b];
                    System.arraycopy(bArr, i, this.a, 0, this.b);
                    return;
                } else {
                    this.b += i2;
                    i2 = bArr[this.b + i] & UByte.MAX_VALUE;
                    i3 = this.b;
                }
            }
        }
    }

    /* renamed from: cp.bd.c.b$b, reason: collision with other inner class name */
    class C0167b {
        public byte[] a;
        public int b;

        public C0167b(byte[] bArr, int i) {
            this.b = 2;
            int i2 = bArr[this.b + i] & UByte.MAX_VALUE;
            int i3 = this.b;
            while (true) {
                this.b = i3 + 1;
                if (i2 == 0) {
                    this.a = new byte[this.b];
                    System.arraycopy(bArr, i, this.a, 0, this.b);
                    return;
                } else {
                    this.b += i2;
                    i2 = bArr[this.b + i] & UByte.MAX_VALUE;
                    i3 = this.b;
                }
            }
        }
    }

    static class c {
        public Bitmap a;
        public int b;

        public c(Bitmap bitmap, int i) {
            this.a = bitmap;
            this.b = i;
        }
    }

    class d {
        public byte[] a;
        public int b;

        public d(byte[] bArr, int i) {
            int i2 = i + 10;
            boolean z = (bArr[i2] & 128) != 0;
            int i3 = bArr[i2] & 7;
            this.b = 13;
            if (z) {
                this.b = (int) (this.b + (Math.pow(2.0d, i3 + 1) * 3.0d));
            }
            this.a = new byte[this.b];
            System.arraycopy(bArr, i, this.a, 0, this.b);
        }
    }

    class e {
        public int b = 8;
        public byte[] a = new byte[this.b];

        public e(byte[] bArr, int i) {
            System.arraycopy(bArr, i, this.a, 0, this.b);
        }

        public final int a() {
            return (this.a[3] & 28) >> 2;
        }
    }

    class f {
        public byte[] a;
        public int b;

        public f(byte[] bArr, int i) {
            int i2 = i + 9;
            boolean z = (bArr[i2] & 128) != 0;
            int i3 = bArr[i2] & 7;
            this.b = 10;
            if (z) {
                this.b = (int) (this.b + (Math.pow(2.0d, i3 + 1) * 3.0d));
            }
            this.b++;
            byte b = bArr[this.b + i];
            while (true) {
                int i4 = b & UByte.MAX_VALUE;
                this.b++;
                if (i4 == 0) {
                    this.a = new byte[this.b];
                    System.arraycopy(bArr, i, this.a, 0, this.b);
                    return;
                } else {
                    this.b += i4;
                    b = bArr[this.b + i];
                }
            }
        }
    }

    class g {
        public byte[] a;
        public int b;

        public g(byte[] bArr, int i) {
            this.b = 15;
            int i2 = bArr[this.b + i] & UByte.MAX_VALUE;
            int i3 = this.b;
            while (true) {
                this.b = i3 + 1;
                if (i2 == 0) {
                    this.a = new byte[this.b];
                    System.arraycopy(bArr, i, this.a, 0, this.b);
                    return;
                } else {
                    this.b += i2;
                    i2 = bArr[this.b + i] & UByte.MAX_VALUE;
                    i3 = this.b;
                }
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:9:0x003f A[Catch: all -> 0x00e5, IOException -> 0x00e7, TryCatch #0 {IOException -> 0x00e7, blocks: (B:3:0x0006, B:5:0x0012, B:7:0x002a, B:12:0x006f, B:9:0x003f, B:11:0x0049, B:13:0x0076, B:15:0x00a4, B:22:0x00b1, B:28:0x00d2, B:30:0x00d6), top: B:52:0x0006, outer: #2 }] */
    @android.annotation.SuppressLint({"NewApi"})
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private android.graphics.Bitmap b() throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 253
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: cp.bd.c.b.b():android.graphics.Bitmap");
    }

    private static String b(int i) {
        String upperCase = Integer.toHexString(i).toUpperCase();
        if (upperCase.length() >= 2) {
            return upperCase.length() > 2 ? upperCase.substring(upperCase.length() - 2) : upperCase;
        }
        while (upperCase.length() < 2) {
            upperCase = Constants.FAIL.concat(String.valueOf(upperCase));
        }
        return upperCase;
    }

    private static byte[] b(InputStream inputStream) throws IOException {
        byte[] bArr = new byte[com.ss.union.game.sdk.feedback.picture.select.d.d];
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        while (true) {
            int i = inputStream.read(bArr);
            if (i != -1) {
                byteArrayOutputStream.write(bArr, 0, i);
            } else {
                try {
                    break;
                } catch (IOException e2) {
                    e2.printStackTrace();
                }
            }
        }
        inputStream.close();
        return byteArrayOutputStream.toByteArray();
    }

    public final int a() {
        return this.j;
    }

    public final int a(InputStream inputStream) throws IOException {
        int i;
        int i2;
        this.a = 0;
        this.j = 0;
        this.i = new ArrayList<>();
        if (inputStream != null) {
            byte[] bArrB = b(inputStream);
            this.n = new d(bArrB, this.m);
            this.m += this.n.b;
            d dVar = this.n;
            this.b = (dVar.a[6] & UByte.MAX_VALUE) + ((dVar.a[7] & UByte.MAX_VALUE) << 8);
            d dVar2 = this.n;
            this.c = (dVar2.a[8] & UByte.MAX_VALUE) + ((dVar2.a[9] & UByte.MAX_VALUE) << 8);
            if (!new String(this.n.a, 0, 3).equals("GIF")) {
                return 1;
            }
            while (bArrB[this.m] != 59) {
                if (bArrB[this.m] == 44) {
                    this.p = new f(bArrB, this.m);
                    this.m += this.p.b;
                    this.j++;
                    this.d = b();
                    if (this.g > 0 && this.g == 3) {
                        int i3 = this.j - 2;
                        this.e = i3 > 0 ? a(i3 - 1) : null;
                    }
                    this.i.add(new c(this.d, this.h));
                    this.g = this.f;
                    this.e = this.d;
                    this.f = 0;
                    this.h = 0;
                } else {
                    if (bArrB[this.m] != 33) {
                        throw new IOException();
                    }
                    if (bArrB[this.m + 1] == -7) {
                        this.o = new e(bArrB, this.m);
                        this.m += this.o.b;
                        this.f = this.o.a();
                        if (this.f == 0) {
                            this.f = 1;
                        }
                        e eVar = this.o;
                        this.h = ((eVar.a[4] & UByte.MAX_VALUE) + ((eVar.a[5] & UByte.MAX_VALUE) << 8)) * 10;
                    } else {
                        if (bArrB[this.m + 1] == -1) {
                            a aVar = new a(bArrB, this.m);
                            i = this.m;
                            i2 = aVar.b;
                        } else if (bArrB[this.m + 1] == -2) {
                            C0167b c0167b = new C0167b(bArrB, this.m);
                            i = this.m;
                            i2 = c0167b.b;
                        } else {
                            if (bArrB[this.m + 1] != 1) {
                                throw new IOException();
                            }
                            g gVar = new g(bArrB, this.m);
                            i = this.m;
                            i2 = gVar.b;
                        }
                        this.m = i + i2;
                    }
                }
            }
        } else {
            this.a = 2;
        }
        return this.a;
    }

    public final Bitmap a(int i) {
        if (this.j <= 0) {
            return null;
        }
        return this.i.get(i % this.j).a;
    }
}
