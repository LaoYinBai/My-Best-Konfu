package com.bytedance.a;

import com.appsflyer.share.Constants;
import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONObject;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\b\u0002\b&\u0018\u0000 \u000b2\u00020\u0001:\u0001\u000bB\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H&J\b\u0010\u0007\u001a\u00020\u0006H&J\u0014\u0010\b\u001a\u0010\u0012\u0004\u0012\u00020\n\u0012\u0006\u0012\u0004\u0018\u00010\n0\t¨\u0006\f"}, d2 = {"Lcom/bytedance/applog/alink/model/BaseData;", "", "()V", "initWithJson", "", "json", "Lorg/json/JSONObject;", "toJson", "toMap", "", "", "Companion", "agent_liteChinaRelease"}, k = 1, mv = {1, 1, 16})
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public abstract class bd {
    public static final a G = new a(null);

    public static final class a {
        public /* synthetic */ a(DefaultConstructorMarker defaultConstructorMarker) {
        }

        @org.b.a.e
        public final <T extends bd> T a(@org.b.a.e JSONObject jSONObject, @org.b.a.d Class<T> cls) throws IllegalAccessException, InstantiationException, IllegalArgumentException, InvocationTargetException {
            Intrinsics.checkParameterIsNotNull(cls, Constants.URL_CAMPAIGN);
            if (jSONObject == null) {
                return null;
            }
            T tNewInstance = cls.getConstructor(new Class[0]).newInstance(new Object[0]);
            tNewInstance.a(jSONObject);
            return tNewInstance;
        }
    }

    @org.b.a.d
    public abstract JSONObject a();

    public abstract void a(@org.b.a.d JSONObject jSONObject);

    @org.b.a.d
    public final Map<String, String> b() {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        JSONObject jSONObjectA = a();
        Iterator<String> itKeys = jSONObjectA.keys();
        Intrinsics.checkExpressionValueIsNotNull(itKeys, "jo.keys()");
        while (itKeys.hasNext()) {
            String next = itKeys.next();
            Intrinsics.checkExpressionValueIsNotNull(next, "x");
            linkedHashMap.put(next, jSONObjectA.get(next).toString());
        }
        return linkedHashMap;
    }
}
