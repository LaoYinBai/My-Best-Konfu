package com.vivo.push.util;

import android.content.Context;
import android.content.res.Resources;
import android.os.Build;
import android.text.TextUtils;
import com.vivo.push.model.InsideNotificationItem;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public final class g implements BaseNotifyDataAdapter {
    private static int e;
    private static int f;
    private Resources a;
    private String b;
    private String c;
    private String d;

    private static boolean a(int i) {
        return (i == -1 || i == 0) ? false : true;
    }

    @Override // com.vivo.push.util.BaseNotifyDataAdapter
    public final void init(Context context) {
        this.b = context.getPackageName();
        this.a = context.getResources();
        this.c = i.a();
        this.d = Build.VERSION.RELEASE;
    }

    @Override // com.vivo.push.util.BaseNotifyDataAdapter
    public final int getDefaultNotifyIcon() {
        if (a(e)) {
            return e;
        }
        String str = this.d;
        int iA = !a(str) ? -1 : a(str, "_notifyicon");
        e = iA;
        if (a(iA)) {
            return e;
        }
        for (String strSubstring = this.c; !TextUtils.isEmpty(strSubstring); strSubstring = strSubstring.substring(0, strSubstring.length() - 1)) {
            int identifier = this.a.getIdentifier("vivo_push_rom" + strSubstring + "_notifyicon", "drawable", this.b);
            if (identifier > 0) {
                return identifier;
            }
        }
        return this.a.getIdentifier("vivo_push_notifyicon", "drawable", this.b);
    }

    @Override // com.vivo.push.util.BaseNotifyDataAdapter
    public final int getDefaultSmallIconId() {
        if (a(f)) {
            return f;
        }
        String str = this.d;
        int iA = !a(str) ? -1 : a(str, "_icon");
        f = iA;
        if (a(iA)) {
            return f;
        }
        for (String strSubstring = this.c; !TextUtils.isEmpty(strSubstring); strSubstring = strSubstring.substring(0, strSubstring.length() - 1)) {
            int identifier = this.a.getIdentifier("vivo_push_rom" + strSubstring + "_icon", "drawable", this.b);
            if (identifier > 0) {
                return identifier;
            }
        }
        return this.a.getIdentifier("vivo_push_icon", "drawable", this.b);
    }

    private static boolean a(String str) {
        if (Build.VERSION.SDK_INT < 26) {
            return false;
        }
        if (!TextUtils.isEmpty(str)) {
            return true;
        }
        n.d("DefaultNotifyDataAdapter", "systemVersion is not suit ");
        return false;
    }

    private int a(String str, String str2) throws NumberFormatException {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return -1;
        }
        String[] strArrSplit = str.split("\\.");
        if (strArrSplit != null && strArrSplit.length > 0) {
            str = strArrSplit[0];
        }
        try {
            for (int i = Integer.parseInt(str); i > 0; i--) {
                String str3 = "vivo_push_ard" + i + str2;
                n.c("DefaultNotifyDataAdapter", "get notify icon : " + str3);
                int identifier = this.a.getIdentifier(str3, "drawable", this.b);
                if (identifier > 0) {
                    n.c("DefaultNotifyDataAdapter", "find notify icon : " + str3);
                    return identifier;
                }
            }
        } catch (Exception e2) {
            n.a("DefaultNotifyDataAdapter", e2);
        }
        return -1;
    }

    @Override // com.vivo.push.util.BaseNotifyDataAdapter
    public final int getNotifyMode(InsideNotificationItem insideNotificationItem) {
        return Build.VERSION.SDK_INT >= 21 ? 2 : 1;
    }
}
