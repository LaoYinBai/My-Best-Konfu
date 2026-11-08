package com.ss.union.game.sdk.nick.b;

import com.ss.union.game.sdk.core.base.event.PageStater;
import java.util.HashMap;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class a {
    public static final String a = "ohayoo_sdk_nickname";
    public static final String b = "nickname_show";
    public static final String c = "nickname_click";
    public static final String d = "random_click";
    public static final String e = "nicknameedit_random_click";
    public static final String f = "confirm_click";
    public static final String g = "nicknameedit_confirm_click";
    public static final String h = "nickname_login";
    public static final String i = "nickname_switch";
    public static final String j = "nickname_bind";
    public static final String k = "nickname_edit";

    public static void a(int i2) {
        String str;
        HashMap map = new HashMap();
        switch (i2) {
            case 2:
                str = j;
                break;
            case 3:
                str = i;
                break;
            case 4:
                str = k;
                break;
            default:
                str = h;
                break;
        }
        map.put(b, str);
        PageStater.onEvent(a, map);
    }

    public static void b(int i2) {
        HashMap map = new HashMap();
        if (4 == i2) {
            map.put(c, e);
        } else {
            map.put(c, d);
        }
        PageStater.onEvent(a, map);
    }

    public static void c(int i2) {
        HashMap map = new HashMap();
        if (4 == i2) {
            map.put(c, g);
        } else {
            map.put(c, f);
        }
        PageStater.onEvent(a, map);
    }
}
