package com.xiaomi.push;

import android.content.Context;
import android.text.TextUtils;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class cc extends ce {
    public cc(String str, String str2, String[] strArr, String str3) {
        super(str, str2, strArr, str3);
    }

    public static cc a(Context context, String str, int i) {
        com.xiaomi.channel.commonutils.logger.b.b("delete  messages when db size is too bigger");
        String strM168a = ci.a(context).m168a(str);
        if (TextUtils.isEmpty(strM168a)) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("rowDataId in (select ");
        sb.append("rowDataId from " + strM168a);
        sb.append(" order by createTimeStamp asc");
        sb.append(" limit ?)");
        return new cc(str, sb.toString(), new String[]{String.valueOf(i)}, "a job build to delete history message");
    }

    private void a(long j) {
        if (this.f192a == null || this.f192a.length <= 0) {
            return;
        }
        this.f192a[0] = String.valueOf(j);
    }

    @Override // com.xiaomi.push.ci.a
    public void a(Context context, Object obj) {
        if (obj instanceof Long) {
            long jLongValue = ((Long) obj).longValue();
            long jA = co.a(a());
            long j = ca.f175a;
            if (jA <= j) {
                com.xiaomi.channel.commonutils.logger.b.b("db size is suitable");
                return;
            }
            long j2 = (long) ((((jA - j) * 1.2d) / j) * jLongValue);
            a(j2);
            bw.a(context).a("begin delete " + j2 + "noUpload messages , because db size is " + jA + "B");
            super.a(context, obj);
        }
    }
}
