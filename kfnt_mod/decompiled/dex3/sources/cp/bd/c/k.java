package cp.bd.c;

import com.bytedance.retrofit2.Call;
import com.bytedance.retrofit2.SsResponse;
import com.bytedance.retrofit2.client.Header;
import com.bytedance.retrofit2.http.GET;
import com.bytedance.retrofit2.http.POST;
import com.bytedance.retrofit2.http.Streaming;
import com.bytedance.retrofit2.mime.TypedByteArray;
import com.bytedance.retrofit2.mime.TypedInput;
import com.bytedance.ttnet.utils.RetrofitUtils;
import com.umeng.message.util.HttpRequest;
import cp.bd.c.g;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public final class k extends i {

    public interface a {
        @POST
        @Streaming
        Call<TypedInput> a();

        @GET
        @Streaming
        Call<TypedInput> b();
    }

    private static String a(List<Header> list) {
        if (list != null && list.size() > 0) {
            for (Header header : list) {
                if (header != null && "x-tt-logid".equalsIgnoreCase(header.getName())) {
                    return header.getValue();
                }
            }
        }
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r13v6, types: [byte[]] */
    private static Object[] a(String str, byte[] bArr, String str2, String str3, boolean z) throws Throwable {
        SsResponse ssResponse;
        OutputStream fileOutputStream;
        InputStream inputStreamIn;
        int iCode;
        Call<TypedInput> callB;
        SsResponse ssResponseExecute;
        g.a aVar = new g.a();
        int i = -1;
        try {
            try {
                ArrayList arrayList = new ArrayList();
                arrayList.add(new Header("Cookie", "sessionid=".concat(String.valueOf(str2))));
                arrayList.add(new Header(HttpRequest.HEADER_ACCEPT, "*/*"));
                arrayList.add(new Header("User-Agent", "ByteDance-MSSDK"));
                arrayList.add(new Header("Content-Type", "application/octet-stream"));
                a aVar2 = (a) RetrofitUtils.getSsRetrofit(str).create(a.class);
                if (z) {
                    new TypedByteArray("application/octet-stream", bArr, new String[0]);
                    callB = aVar2.a();
                } else {
                    callB = aVar2.b();
                }
                aVar.a(str);
                ssResponseExecute = callB.execute();
                try {
                    iCode = ssResponseExecute.code();
                } catch (Throwable th) {
                    fileOutputStream = null;
                    inputStreamIn = null;
                    ssResponse = ssResponseExecute;
                    th = th;
                }
            } catch (Throwable th2) {
                th = th2;
                fileOutputStream = null;
            }
            try {
                aVar.a(a((List<Header>) ssResponseExecute.headers()), iCode);
                fileOutputStream = str3 != null ? new FileOutputStream(str3) : new ByteArrayOutputStream();
                try {
                    inputStreamIn = ((TypedInput) ssResponseExecute.body()).in();
                    try {
                        try {
                            byte[] bArr2 = new byte[com.ss.union.game.sdk.feedback.picture.select.d.d];
                            while (true) {
                                int i2 = inputStreamIn.read(bArr2, 0, com.ss.union.game.sdk.feedback.picture.select.d.d);
                                if (i2 <= 0) {
                                    break;
                                }
                                fileOutputStream.write(bArr2, 0, i2);
                            }
                            fileOutputStream.flush();
                            byteArray = fileOutputStream instanceof ByteArrayOutputStream ? ((ByteArrayOutputStream) fileOutputStream).toByteArray() : null;
                            a(inputStreamIn);
                            a(fileOutputStream);
                        } catch (Throwable th3) {
                            i = iCode;
                            ssResponse = ssResponseExecute;
                            th = th3;
                            th.printStackTrace();
                            aVar.b(ssResponse != null ? a((List<Header>) ssResponse.headers()) : null, i);
                            a(inputStreamIn);
                            a(fileOutputStream);
                            iCode = i;
                            return new Object[]{String.valueOf(iCode), byteArray};
                        }
                    } catch (Throwable th4) {
                        th = th4;
                        byteArray = inputStreamIn;
                        a(byteArray);
                        a(fileOutputStream);
                        throw th;
                    }
                } catch (Throwable th5) {
                    th = th5;
                    a(byteArray);
                    a(fileOutputStream);
                    throw th;
                }
            } catch (Throwable th6) {
                i = iCode;
                inputStreamIn = null;
                ssResponse = ssResponseExecute;
                th = th6;
                fileOutputStream = null;
            }
        } catch (Throwable th7) {
            th = th7;
            ssResponse = null;
            fileOutputStream = null;
            inputStreamIn = null;
        }
        return new Object[]{String.valueOf(iCode), byteArray};
    }

    @Override // cp.bd.c.i
    public final Object[] a(String str, String str2) {
        return a(str, (byte[]) null, str2, (String) null, false);
    }

    @Override // cp.bd.c.i
    public final Object[] a(String str, byte[] bArr, String str2) {
        return a(str, bArr, str2, (String) null, true);
    }

    @Override // cp.bd.c.i
    public final Object[] b(String str, String str2) {
        return a(str, (byte[]) null, (String) null, str2, false);
    }
}
