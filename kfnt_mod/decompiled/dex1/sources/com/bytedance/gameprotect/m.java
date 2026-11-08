package com.bytedance.gameprotect;

import android.util.Pair;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.bytedance.frameworks.baselib.network.http.util.UrlUtils;
import com.bytedance.retrofit2.Call;
import com.bytedance.retrofit2.ExpandCallback;
import com.bytedance.retrofit2.RequestBuilder;
import com.bytedance.retrofit2.SsResponse;
import com.bytedance.retrofit2.client.Header;
import com.bytedance.retrofit2.http.AddCommonParam;
import com.bytedance.retrofit2.http.Body;
import com.bytedance.retrofit2.http.GET;
import com.bytedance.retrofit2.http.HeaderList;
import com.bytedance.retrofit2.http.POST;
import com.bytedance.retrofit2.http.QueryMap;
import com.bytedance.retrofit2.http.Url;
import com.bytedance.retrofit2.mime.TypedByteArray;
import com.bytedance.retrofit2.mime.TypedInput;
import com.bytedance.sdk.b.d.a;
import com.bytedance.ttnet.utils.RetrofitUtils;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class m {

    static class a implements ExpandCallback<TypedInput> {

        /* renamed from: a, reason: collision with root package name */
        private long f3884a;

        a(long j) {
            this.f3884a = j;
        }

        public void onAsyncPreRequest(RequestBuilder requestBuilder) {
        }

        public void onAsyncResponse(Call<TypedInput> call, SsResponse<TypedInput> ssResponse) {
            byte[] bArrB;
            try {
                bArrB = m.b(ssResponse.isSuccessful() ? (TypedInput) ssResponse.body() : ssResponse.errorBody());
            } catch (IOException unused) {
                bArrB = null;
            }
            p.a(this.f3884a, String.valueOf(ssResponse.code()), bArrB);
        }

        public void onFailure(Call<TypedInput> call, Throwable th) {
            p.a(this.f3884a, th.getMessage());
        }

        public void onResponse(Call<TypedInput> call, SsResponse<TypedInput> ssResponse) {
        }
    }

    interface b {
        @POST
        Call<TypedInput> a(@Url String str, @Body TypedByteArray typedByteArray, @QueryMap(encode = true) Map<String, String> map, @HeaderList List<Header> list, @AddCommonParam boolean z);

        @GET
        Call<TypedInput> a(@Url String str, @QueryMap(encode = true) Map<String, String> map, @HeaderList List<Header> list, @AddCommonParam boolean z);
    }

    public static Object a(long j, @NonNull String str, @Nullable Map<String, Object> map, @Nullable Map<String, Object> map2) {
        if (map == null) {
            map = new HashMap<>();
        }
        if (map2 == null) {
            map2 = new HashMap<>();
        }
        try {
            Pair urlWithValueList = UrlUtils.parseUrlWithValueList(str, (Map) null);
            Call<TypedInput> callA = ((b) RetrofitUtils.createSsService((String) urlWithValueList.first, b.class)).a((String) urlWithValueList.second, a(map), b(map2), false);
            if (j != -1) {
                callA.enqueue(new a(j));
                return 0;
            }
            SsResponse ssResponseExecute = callA.execute();
            return ssResponseExecute.isSuccessful() ? b((TypedInput) ssResponseExecute.body()) : "Exception occurred";
        } catch (Exception unused) {
            return "Exception occurred";
        }
    }

    public static Object a(long j, @NonNull String str, @NonNull byte[] bArr, @Nullable Map<String, Object> map, @Nullable Map<String, Object> map2) {
        if (map == null) {
            map = new HashMap<>();
        }
        if (map2 == null) {
            map2 = new HashMap<>();
        }
        try {
            Pair urlWithValueList = UrlUtils.parseUrlWithValueList(str, (Map) null);
            String str2 = (String) urlWithValueList.first;
            Call<TypedInput> callA = ((b) RetrofitUtils.createSsService(str2, b.class)).a((String) urlWithValueList.second, new TypedByteArray(a.f.f5151a, bArr, new String[0]), a(map), b(map2), false);
            if (j != -1) {
                callA.enqueue(new a(j));
                return 0;
            }
            SsResponse ssResponseExecute = callA.execute();
            return ssResponseExecute.isSuccessful() ? b((TypedInput) ssResponseExecute.body()) : "Exception occurred";
        } catch (Exception unused) {
            return "Exception occurred";
        }
    }

    private static Map<String, String> a(Map<String, Object> map) {
        HashMap map2 = new HashMap();
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            map2.put(entry.getKey(), entry.getValue().toString());
        }
        return map2;
    }

    @NonNull
    private static List<Header> b(@Nullable Map<String, Object> map) {
        if (map == null) {
            return new ArrayList();
        }
        ArrayList arrayList = new ArrayList(map.size());
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            arrayList.add(new Header(entry.getKey(), entry.getValue().toString()));
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static byte[] b(@NonNull TypedInput typedInput) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        InputStream inputStreamIn = typedInput.in();
        byte[] bArr = new byte[4096];
        while (true) {
            int i = inputStreamIn.read(bArr);
            if (i <= 0) {
                byteArrayOutputStream.flush();
                byteArrayOutputStream.close();
                inputStreamIn.close();
                return byteArrayOutputStream.toByteArray();
            }
            byteArrayOutputStream.write(bArr, 0, i);
        }
    }
}
