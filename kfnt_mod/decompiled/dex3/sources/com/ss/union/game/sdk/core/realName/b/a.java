package com.ss.union.game.sdk.core.realName.b;

import com.ss.union.game.sdk.common.util.SPUtils;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class a {

    /* renamed from: com.ss.union.game.sdk.core.realName.b.a$a, reason: collision with other inner class name */
    public static class C0087a {
        private static final String a = "sp_key_real_name_device_has_done";
        private static final String b = "sp_key_real_name_device_is_adult";

        public static boolean a() {
            return a.c().getBoolean(a, false);
        }

        public static void a(boolean z) {
            a.c().put(a, z);
        }

        public static boolean b() {
            return a.c().getBoolean(b, false);
        }

        public static void b(boolean z) {
            a.c().put(b, z);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static SPUtils c() {
        return SPUtils.getInstance("lg_game_real_name");
    }

    public static void a() {
        SPUtils sPUtilsC = c();
        if (sPUtilsC != null) {
            sPUtilsC.clear();
        }
    }
}
