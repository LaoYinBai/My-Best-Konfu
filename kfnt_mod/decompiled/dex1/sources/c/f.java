package c;

import com.appsflyer.share.Constants;
import com.bytedance.common.utility.StringEncryptUtils;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import javax.annotation.Nullable;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class f implements Serializable, Comparable<f> {

    /* renamed from: a, reason: collision with root package name */
    static final char[] f2138a = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    /* renamed from: b, reason: collision with root package name */
    public static final f f2139b = a(new byte[0]);
    private static final long serialVersionUID = 1;

    /* renamed from: c, reason: collision with root package name */
    final byte[] f2140c;
    transient int d;
    transient String e;

    f(byte[] bArr) {
        this.f2140c = bArr;
    }

    public static f a(byte... bArr) {
        if (bArr == null) {
            throw new IllegalArgumentException("data == null");
        }
        return new f((byte[]) bArr.clone());
    }

    public static f a(byte[] bArr, int i, int i2) {
        if (bArr == null) {
            throw new IllegalArgumentException("data == null");
        }
        ab.a(bArr.length, i, i2);
        byte[] bArr2 = new byte[i2];
        System.arraycopy(bArr, i, bArr2, 0, i2);
        return new f(bArr2);
    }

    public static f a(ByteBuffer byteBuffer) {
        if (byteBuffer == null) {
            throw new IllegalArgumentException("data == null");
        }
        byte[] bArr = new byte[byteBuffer.remaining()];
        byteBuffer.get(bArr);
        return new f(bArr);
    }

    public static f a(String str) {
        if (str == null) {
            throw new IllegalArgumentException("s == null");
        }
        f fVar = new f(str.getBytes(ab.f2127a));
        fVar.e = str;
        return fVar;
    }

    public static f a(String str, Charset charset) {
        if (str == null) {
            throw new IllegalArgumentException("s == null");
        }
        if (charset == null) {
            throw new IllegalArgumentException("charset == null");
        }
        return new f(str.getBytes(charset));
    }

    public String a() {
        String str = this.e;
        if (str != null) {
            return str;
        }
        String str2 = new String(this.f2140c, ab.f2127a);
        this.e = str2;
        return str2;
    }

    public String a(Charset charset) {
        if (charset == null) {
            throw new IllegalArgumentException("charset == null");
        }
        return new String(this.f2140c, charset);
    }

    public String b() {
        return b.a(this.f2140c);
    }

    public f c() {
        return d("MD5");
    }

    public f d() {
        return d(StringEncryptUtils.SHA_1);
    }

    public f e() {
        return d(StringEncryptUtils.SHA_256);
    }

    public f f() {
        return d("SHA-512");
    }

    private f d(String str) {
        try {
            return a(MessageDigest.getInstance(str).digest(this.f2140c));
        } catch (NoSuchAlgorithmException e) {
            throw new AssertionError(e);
        }
    }

    public f a(f fVar) {
        return a("HmacSHA1", fVar);
    }

    public f b(f fVar) {
        return a("HmacSHA256", fVar);
    }

    public f c(f fVar) {
        return a("HmacSHA512", fVar);
    }

    private f a(String str, f fVar) throws NoSuchAlgorithmException, InvalidKeyException {
        try {
            Mac mac = Mac.getInstance(str);
            mac.init(new SecretKeySpec(fVar.l(), str));
            return a(mac.doFinal(this.f2140c));
        } catch (InvalidKeyException e) {
            throw new IllegalArgumentException(e);
        } catch (NoSuchAlgorithmException e2) {
            throw new AssertionError(e2);
        }
    }

    public String g() {
        return b.b(this.f2140c);
    }

    @Nullable
    public static f b(String str) {
        if (str == null) {
            throw new IllegalArgumentException("base64 == null");
        }
        byte[] bArrA = b.a(str);
        if (bArrA != null) {
            return new f(bArrA);
        }
        return null;
    }

    public String h() {
        char[] cArr = new char[this.f2140c.length * 2];
        int i = 0;
        for (byte b2 : this.f2140c) {
            int i2 = i + 1;
            cArr[i] = f2138a[(b2 >> 4) & 15];
            i = i2 + 1;
            cArr[i2] = f2138a[b2 & 15];
        }
        return new String(cArr);
    }

    public static f c(String str) {
        if (str == null) {
            throw new IllegalArgumentException("hex == null");
        }
        if (str.length() % 2 != 0) {
            throw new IllegalArgumentException("Unexpected hex string: " + str);
        }
        byte[] bArr = new byte[str.length() / 2];
        for (int i = 0; i < bArr.length; i++) {
            int i2 = i * 2;
            bArr[i] = (byte) ((a(str.charAt(i2)) << 4) + a(str.charAt(i2 + 1)));
        }
        return a(bArr);
    }

    private static int a(char c2) {
        if (c2 >= '0' && c2 <= '9') {
            return c2 - '0';
        }
        if (c2 >= 'a' && c2 <= 'f') {
            return (c2 - 'a') + 10;
        }
        if (c2 >= 'A' && c2 <= 'F') {
            return (c2 - 'A') + 10;
        }
        throw new IllegalArgumentException("Unexpected hex digit: " + c2);
    }

    public static f a(InputStream inputStream, int i) throws IOException {
        if (inputStream == null) {
            throw new IllegalArgumentException("in == null");
        }
        if (i < 0) {
            throw new IllegalArgumentException("byteCount < 0: " + i);
        }
        byte[] bArr = new byte[i];
        int i2 = 0;
        while (i2 < i) {
            int i3 = inputStream.read(bArr, i2, i - i2);
            if (i3 == -1) {
                throw new EOFException();
            }
            i2 += i3;
        }
        return new f(bArr);
    }

    public f i() {
        for (int i = 0; i < this.f2140c.length; i++) {
            byte b2 = this.f2140c[i];
            if (b2 >= 65 && b2 <= 90) {
                byte[] bArr = (byte[]) this.f2140c.clone();
                bArr[i] = (byte) (b2 + 32);
                for (int i2 = i + 1; i2 < bArr.length; i2++) {
                    byte b3 = bArr[i2];
                    if (b3 >= 65 && b3 <= 90) {
                        bArr[i2] = (byte) (b3 + 32);
                    }
                }
                return new f(bArr);
            }
        }
        return this;
    }

    public f j() {
        for (int i = 0; i < this.f2140c.length; i++) {
            byte b2 = this.f2140c[i];
            if (b2 >= 97 && b2 <= 122) {
                byte[] bArr = (byte[]) this.f2140c.clone();
                bArr[i] = (byte) (b2 - 32);
                for (int i2 = i + 1; i2 < bArr.length; i2++) {
                    byte b3 = bArr[i2];
                    if (b3 >= 97 && b3 <= 122) {
                        bArr[i2] = (byte) (b3 - 32);
                    }
                }
                return new f(bArr);
            }
        }
        return this;
    }

    public f a(int i) {
        return a(i, this.f2140c.length);
    }

    public f a(int i, int i2) {
        if (i < 0) {
            throw new IllegalArgumentException("beginIndex < 0");
        }
        if (i2 > this.f2140c.length) {
            throw new IllegalArgumentException("endIndex > length(" + this.f2140c.length + ")");
        }
        int i3 = i2 - i;
        if (i3 < 0) {
            throw new IllegalArgumentException("endIndex < beginIndex");
        }
        if (i == 0 && i2 == this.f2140c.length) {
            return this;
        }
        byte[] bArr = new byte[i3];
        System.arraycopy(this.f2140c, i, bArr, 0, i3);
        return new f(bArr);
    }

    public byte b(int i) {
        return this.f2140c[i];
    }

    public int k() {
        return this.f2140c.length;
    }

    public byte[] l() {
        return (byte[]) this.f2140c.clone();
    }

    byte[] m() {
        return this.f2140c;
    }

    public ByteBuffer n() {
        return ByteBuffer.wrap(this.f2140c).asReadOnlyBuffer();
    }

    public void a(OutputStream outputStream) throws IOException {
        if (outputStream == null) {
            throw new IllegalArgumentException("out == null");
        }
        outputStream.write(this.f2140c);
    }

    void a(c cVar) {
        cVar.c(this.f2140c, 0, this.f2140c.length);
    }

    public boolean a(int i, f fVar, int i2, int i3) {
        return fVar.a(i2, this.f2140c, i, i3);
    }

    public boolean a(int i, byte[] bArr, int i2, int i3) {
        return i >= 0 && i <= this.f2140c.length - i3 && i2 >= 0 && i2 <= bArr.length - i3 && ab.a(this.f2140c, i, bArr, i2, i3);
    }

    public final boolean d(f fVar) {
        return a(0, fVar, 0, fVar.k());
    }

    public final boolean b(byte[] bArr) {
        return a(0, bArr, 0, bArr.length);
    }

    public final boolean e(f fVar) {
        return a(k() - fVar.k(), fVar, 0, fVar.k());
    }

    public final boolean c(byte[] bArr) {
        return a(k() - bArr.length, bArr, 0, bArr.length);
    }

    public final int f(f fVar) {
        return a(fVar.m(), 0);
    }

    public final int a(f fVar, int i) {
        return a(fVar.m(), i);
    }

    public final int d(byte[] bArr) {
        return a(bArr, 0);
    }

    public int a(byte[] bArr, int i) {
        int length = this.f2140c.length - bArr.length;
        for (int iMax = Math.max(i, 0); iMax <= length; iMax++) {
            if (ab.a(this.f2140c, iMax, bArr, 0, bArr.length)) {
                return iMax;
            }
        }
        return -1;
    }

    public final int g(f fVar) {
        return b(fVar.m(), k());
    }

    public final int b(f fVar, int i) {
        return b(fVar.m(), i);
    }

    public final int e(byte[] bArr) {
        return b(bArr, k());
    }

    public int b(byte[] bArr, int i) {
        for (int iMin = Math.min(i, this.f2140c.length - bArr.length); iMin >= 0; iMin--) {
            if (ab.a(this.f2140c, iMin, bArr, 0, bArr.length)) {
                return iMin;
            }
        }
        return -1;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof f) {
            f fVar = (f) obj;
            if (fVar.k() == this.f2140c.length && fVar.a(0, this.f2140c, 0, this.f2140c.length)) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        int i = this.d;
        if (i != 0) {
            return i;
        }
        int iHashCode = Arrays.hashCode(this.f2140c);
        this.d = iHashCode;
        return iHashCode;
    }

    @Override // java.lang.Comparable
    /* renamed from: h, reason: merged with bridge method [inline-methods] */
    public int compareTo(f fVar) {
        int iK = k();
        int iK2 = fVar.k();
        int iMin = Math.min(iK, iK2);
        for (int i = 0; i < iMin; i++) {
            int iB = b(i) & 255;
            int iB2 = fVar.b(i) & 255;
            if (iB != iB2) {
                return iB < iB2 ? -1 : 1;
            }
        }
        if (iK == iK2) {
            return 0;
        }
        return iK < iK2 ? -1 : 1;
    }

    public String toString() {
        if (this.f2140c.length == 0) {
            return "[size=0]";
        }
        String strA = a();
        int iA = a(strA, 64);
        if (iA == -1) {
            if (this.f2140c.length <= 64) {
                return "[hex=" + h() + "]";
            }
            return "[size=" + this.f2140c.length + " hex=" + a(0, 64).h() + "…]";
        }
        String strReplace = strA.substring(0, iA).replace("\\", "\\\\").replace("\n", "\\n").replace("\r", "\\r");
        if (iA < strA.length()) {
            return "[size=" + this.f2140c.length + " text=" + strReplace + "…]";
        }
        return "[text=" + strReplace + "]";
    }

    static int a(String str, int i) {
        int length = str.length();
        int iCharCount = 0;
        int i2 = 0;
        while (iCharCount < length) {
            if (i2 == i) {
                return iCharCount;
            }
            int iCodePointAt = str.codePointAt(iCharCount);
            if ((Character.isISOControl(iCodePointAt) && iCodePointAt != 10 && iCodePointAt != 13) || iCodePointAt == 65533) {
                return -1;
            }
            i2++;
            iCharCount += Character.charCount(iCodePointAt);
        }
        return str.length();
    }

    private void readObject(ObjectInputStream objectInputStream) throws IllegalAccessException, NoSuchFieldException, IOException, IllegalArgumentException {
        f fVarA = a(objectInputStream, objectInputStream.readInt());
        try {
            Field declaredField = f.class.getDeclaredField(Constants.URL_CAMPAIGN);
            declaredField.setAccessible(true);
            declaredField.set(this, fVarA.f2140c);
        } catch (IllegalAccessException unused) {
            throw new AssertionError();
        } catch (NoSuchFieldException unused2) {
            throw new AssertionError();
        }
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.writeInt(this.f2140c.length);
        objectOutputStream.write(this.f2140c);
    }
}
