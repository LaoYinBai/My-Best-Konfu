package com.ss.union.game.sdk.core.age_tips;

import android.content.Context;
import com.ss.union.game.sdk.core.base.config.ConfigManager;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class b {
    private static volatile b b;
    public int a = ConfigManager.AppConfig.ageTipsType();

    public interface a {
        public static final int a = -1;
        public static final int b = 8;
        public static final int c = 12;
        public static final int d = 16;
    }

    public static b a(Context context) {
        if (b == null) {
            synchronized (b.class) {
                if (b == null) {
                    b = new b(context);
                }
            }
        }
        return b;
    }

    public boolean a() {
        return this.a == 8 || this.a == 12 || this.a == 16;
    }

    private b(Context context) {
    }
}
