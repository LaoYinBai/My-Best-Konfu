package com.bytedance.a;

import com.bytedance.a.bd;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONObject;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u000b\n\u0002\u0010\u000e\n\u0002\b\u0006\u0018\u0000 \u0017*\b\b\u0000\u0010\u0001*\u00020\u00022\u00020\u0003:\u0001\u0017B\u0005¢\u0006\u0002\u0010\u0004R\u001a\u0010\u0005\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001e\u0010\u000b\u001a\u0004\u0018\u00018\u0000X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u0010\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u001a\u0010\u0011\u001a\u00020\u0012X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016¨\u0006\u0018"}, d2 = {"Lcom/bytedance/applog/alink/model/ApiResponse;", "T", "Lcom/bytedance/applog/alink/model/BaseData;", "", "()V", "code", "", "getCode", "()I", "setCode", "(I)V", "data", "getData", "()Lcom/bytedance/applog/alink/model/BaseData;", "setData", "(Lcom/bytedance/applog/alink/model/BaseData;)V", "Lcom/bytedance/applog/alink/model/BaseData;", "message", "", "getMessage", "()Ljava/lang/String;", "setMessage", "(Ljava/lang/String;)V", "Companion", "agent_liteChinaRelease"}, k = 1, mv = {1, 1, 16})
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public final class ao<T extends bd> {

    /* renamed from: a, reason: collision with root package name */
    public static final a f3362a = new a(null);

    /* renamed from: b, reason: collision with root package name */
    public int f3363b;

    /* renamed from: c, reason: collision with root package name */
    @org.b.a.d
    public String f3364c = "";

    @org.b.a.e
    public T d;

    public static final class a {
        public /* synthetic */ a(DefaultConstructorMarker defaultConstructorMarker) {
        }

        @org.b.a.d
        public final <T extends bd> ao<T> a() {
            ao<T> aoVar = new ao<>();
            aoVar.d = null;
            aoVar.f3363b = -1;
            Intrinsics.checkParameterIsNotNull("request failed!", "<set-?>");
            aoVar.f3364c = "request failed!";
            return aoVar;
        }

        @org.b.a.d
        public final <T extends bd> ao<T> a(@org.b.a.d String str, @org.b.a.d Class<T> cls) {
            Intrinsics.checkParameterIsNotNull(str, "string");
            Intrinsics.checkParameterIsNotNull(cls, "clazz");
            JSONObject jSONObject = new JSONObject(str);
            ao<T> aoVar = new ao<>();
            aoVar.f3363b = jSONObject.optInt("code");
            String strOptString = jSONObject.optString("message");
            Intrinsics.checkExpressionValueIsNotNull(strOptString, "jo.optString(\"message\")");
            Intrinsics.checkParameterIsNotNull(strOptString, "<set-?>");
            aoVar.f3364c = strOptString;
            aoVar.d = (T) bd.G.a(jSONObject.optJSONObject("data"), cls);
            return aoVar;
        }
    }
}
