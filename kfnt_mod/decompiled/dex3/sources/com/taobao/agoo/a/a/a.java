package com.taobao.agoo.a.a;

import com.taobao.accs.common.Constants;
import com.taobao.accs.utl.ALog;
import com.taobao.accs.utl.p;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class a extends b {
    public static final String JSON_CMD_REMOVEALIAS = "removeAlias";
    public static final String JSON_CMD_SETALIAS = "setAlias";
    public static final String JSON_PUSH_USER_TOKEN = "pushAliasToken";
    public String a;
    public String b;
    public String c;
    public String d;

    public byte[] a() {
        try {
            String string = new p.a().a(b.JSON_CMD, this.e).a(Constants.KEY_APP_KEY, this.a).a(b.JSON_DEVICE_ID, this.b).a("alias", this.c).a(JSON_PUSH_USER_TOKEN, this.d).a().toString();
            ALog.i("AliasDO", "buildData", "data", string);
            return string.getBytes("utf-8");
        } catch (Throwable th) {
            ALog.e("AliasDO", "buildData", th, new Object[0]);
            return null;
        }
    }

    public static byte[] a(String str, String str2, String str3) {
        a aVar = new a();
        aVar.a = str;
        aVar.b = str2;
        aVar.c = str3;
        aVar.e = "setAlias";
        return aVar.a();
    }

    public static byte[] b(String str, String str2, String str3) {
        a aVar = new a();
        aVar.a = str;
        aVar.b = str2;
        aVar.d = str3;
        aVar.e = JSON_CMD_REMOVEALIAS;
        return aVar.a();
    }

    public static byte[] c(String str, String str2, String str3) {
        a aVar = new a();
        aVar.a = str;
        aVar.b = str2;
        aVar.c = str3;
        aVar.e = JSON_CMD_REMOVEALIAS;
        return aVar.a();
    }
}
