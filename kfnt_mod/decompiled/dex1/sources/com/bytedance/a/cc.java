package com.bytedance.a;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.net.Uri;
import android.util.Base64;
import anet.channel.util.HttpConstant;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.List;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import kotlin.text.StringsKt;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public final class cc {

    /* renamed from: a, reason: collision with root package name */
    public static final cc f3455a = new cc();

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.TypeCastException */
    @org.b.a.e
    public final JSONObject a(@org.b.a.e ClipboardManager clipboardManager) throws TypeCastException {
        if (clipboardManager == null) {
            return null;
        }
        ClipData primaryClip = clipboardManager.getPrimaryClip();
        if (primaryClip == null) {
            de.a("剪贴板 clipData 为空", null);
            return null;
        }
        ClipData.Item itemAt = primaryClip.getItemAt(0);
        String strValueOf = String.valueOf(itemAt != null ? itemAt.getText() : null);
        de.a("剪贴板的原始数据 = " + strValueOf, null);
        clipboardManager.setPrimaryClip(ClipData.newPlainText("", ""));
        if (!StringsKt.startsWith$default(strValueOf, "datatracer:", false, 2, (Object) null)) {
            return null;
        }
        String strSubstring = strValueOf.substring(11);
        Intrinsics.checkExpressionValueIsNotNull(strSubstring, "(this as java.lang.String).substring(startIndex)");
        Charset charset = Charsets.UTF_8;
        if (strSubstring == null) {
            throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
        }
        byte[] bytes = strSubstring.getBytes(charset);
        Intrinsics.checkExpressionValueIsNotNull(bytes, "(this as java.lang.String).getBytes(charset)");
        byte[] bArrDecode = Base64.decode(bytes, 2);
        StringBuilder sb = new StringBuilder();
        sb.append("剪贴板base64解密后的数据 = ");
        Intrinsics.checkExpressionValueIsNotNull(bArrDecode, "data");
        sb.append(new String(bArrDecode, Charsets.UTF_8));
        de.a(sb.toString(), null);
        return a(new String(bArrDecode, Charsets.UTF_8), new JSONObject());
    }

    @org.b.a.e
    public final JSONObject a(@org.b.a.e Uri uri) throws JSONException {
        String query;
        JSONObject jSONObject = new JSONObject();
        if (!Intrinsics.areEqual(uri != null ? uri.getScheme() : null, "https")) {
            if (!Intrinsics.areEqual(uri != null ? uri.getScheme() : null, HttpConstant.HTTP)) {
                if (uri == null || (query = uri.getQuery()) == null) {
                    return null;
                }
                return f3455a.a(query, jSONObject);
            }
        }
        if (uri.getPathSegments().size() <= 1 || !Intrinsics.areEqual(uri.getPathSegments().get(0), "a")) {
            return null;
        }
        jSONObject.put("tr_token", uri.getLastPathSegment());
        return a(uri.getQuery(), jSONObject);
    }

    public final JSONObject a(String str, JSONObject jSONObject) throws JSONException {
        List listSplit$default = str != null ? StringsKt.split$default(str, new String[]{"&"}, false, 0, 6, (Object) null) : null;
        if (listSplit$default == null) {
            Intrinsics.throwNpe();
        }
        Iterator it = listSplit$default.iterator();
        while (it.hasNext()) {
            List listSplit$default2 = StringsKt.split$default((String) it.next(), new String[]{"="}, false, 0, 6, (Object) null);
            if (listSplit$default2.size() != 2) {
                return null;
            }
            jSONObject.put((String) listSplit$default2.get(0), listSplit$default2.get(1));
        }
        return jSONObject;
    }
}
