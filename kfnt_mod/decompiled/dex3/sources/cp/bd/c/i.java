package cp.bd.c;

import com.umeng.message.MsgConstant;
import cp.bd.c.d;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public abstract class i extends d.a {
    protected static void a(InputStream inputStream) throws IOException {
        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    protected static void a(OutputStream outputStream) throws IOException {
        if (outputStream != null) {
            try {
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override // cp.bd.c.d.a
    public final Object a(int i, int i2, long j, String str, Object obj) {
        if (!ac.a(MsgConstant.PERMISSION_INTERNET)) {
            return null;
        }
        switch (i) {
            case 196609:
                Object[] objArr = (Object[]) obj;
                return a(str, (byte[]) objArr[0], (String) objArr[1]);
            case 196610:
                return a(str, (String) obj);
            case 196611:
                return b(str, (String) obj);
            default:
                return super.a(i, i2, j, str, obj);
        }
    }

    public abstract Object[] a(String str, String str2);

    public abstract Object[] a(String str, byte[] bArr, String str2);

    public abstract Object[] b(String str, String str2);
}
