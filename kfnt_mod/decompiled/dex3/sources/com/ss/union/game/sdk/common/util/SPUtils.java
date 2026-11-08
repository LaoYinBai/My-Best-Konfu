package com.ss.union.game.sdk.common.util;

import android.content.SharedPreferences;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public final class SPUtils {
    private static final Map<String, SPUtils> a = new HashMap();
    private SharedPreferences b;

    private SPUtils(String str) {
        this(str, 0);
    }

    private SPUtils(String str, int i) {
        this.b = GlobalApplicationUtils.getContext().getSharedPreferences(str, i);
    }

    public static SPUtils getInstance() {
        return getInstance("", 0);
    }

    public static SPUtils getInstance(int i) {
        return getInstance("", i);
    }

    public static SPUtils getInstance(String str) {
        return getInstance(str, 0);
    }

    public static SPUtils getInstance(String str, int i) {
        if (a(str)) {
            str = "LGSdkSpUtils";
        }
        SPUtils sPUtils = a.get(str);
        if (sPUtils == null) {
            synchronized (SPUtils.class) {
                sPUtils = a.get(str);
                if (sPUtils == null) {
                    sPUtils = new SPUtils(str, i);
                    a.put(str, sPUtils);
                }
            }
        }
        return sPUtils;
    }

    private static boolean a(String str) {
        if (str == null) {
            return true;
        }
        int length = str.length();
        for (int i = 0; i < length; i++) {
            if (!Character.isWhitespace(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public void put(String str, String str2) {
        put(str, str2, false);
    }

    public void put(String str, String str2, boolean z) {
        if (z) {
            this.b.edit().putString(str, str2).commit();
        } else {
            this.b.edit().putString(str, str2).apply();
        }
    }

    public String getString(String str) {
        return getString(str, "");
    }

    public String getString(String str, String str2) {
        return this.b.getString(str, str2);
    }

    public void put(String str, int i) {
        put(str, i, false);
    }

    public void put(String str, int i, boolean z) {
        if (z) {
            this.b.edit().putInt(str, i).commit();
        } else {
            this.b.edit().putInt(str, i).apply();
        }
    }

    public int getInt(String str) {
        return getInt(str, -1);
    }

    public int getInt(String str, int i) {
        return this.b.getInt(str, i);
    }

    public void put(String str, long j) {
        put(str, j, false);
    }

    public void put(String str, long j, boolean z) {
        if (z) {
            this.b.edit().putLong(str, j).commit();
        } else {
            this.b.edit().putLong(str, j).apply();
        }
    }

    public long getLong(String str) {
        return getLong(str, -1L);
    }

    public long getLong(String str, long j) {
        return this.b.getLong(str, j);
    }

    public void put(String str, float f) {
        put(str, f, false);
    }

    public void put(String str, float f, boolean z) {
        if (z) {
            this.b.edit().putFloat(str, f).commit();
        } else {
            this.b.edit().putFloat(str, f).apply();
        }
    }

    public float getFloat(String str) {
        return getFloat(str, -1.0f);
    }

    public float getFloat(String str, float f) {
        return this.b.getFloat(str, f);
    }

    public void put(String str, boolean z) {
        put(str, z, false);
    }

    public void put(String str, boolean z, boolean z2) {
        if (z2) {
            this.b.edit().putBoolean(str, z).commit();
        } else {
            this.b.edit().putBoolean(str, z).apply();
        }
    }

    public boolean getBoolean(String str) {
        return getBoolean(str, false);
    }

    public boolean getBoolean(String str, boolean z) {
        return this.b.getBoolean(str, z);
    }

    public void put(String str, Set<String> set) {
        put(str, set, false);
    }

    public void put(String str, Set<String> set, boolean z) {
        if (z) {
            this.b.edit().putStringSet(str, set).commit();
        } else {
            this.b.edit().putStringSet(str, set).apply();
        }
    }

    public Set<String> getStringSet(String str) {
        return getStringSet(str, Collections.emptySet());
    }

    public Set<String> getStringSet(String str, Set<String> set) {
        return this.b.getStringSet(str, set);
    }

    public Map<String, ?> getAll() {
        return this.b.getAll();
    }

    public boolean contains(String str) {
        return this.b.contains(str);
    }

    public void remove(String str) {
        remove(str, false);
    }

    public void remove(String str, boolean z) {
        if (z) {
            this.b.edit().remove(str).commit();
        } else {
            this.b.edit().remove(str).apply();
        }
    }

    public void clear() {
        clear(false);
    }

    public void clear(boolean z) {
        if (z) {
            this.b.edit().clear().commit();
        } else {
            this.b.edit().clear().apply();
        }
    }
}
