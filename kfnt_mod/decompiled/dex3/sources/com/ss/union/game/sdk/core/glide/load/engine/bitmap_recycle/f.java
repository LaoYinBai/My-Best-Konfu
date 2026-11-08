package com.ss.union.game.sdk.core.glide.load.engine.bitmap_recycle;

import java.util.Map;
import java.util.TreeMap;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
class f<K, V> extends TreeMap<K, V> {
    f() {
    }

    @Override // java.util.AbstractMap
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("( ");
        for (Map.Entry<K, V> entry : entrySet()) {
            sb.append('{');
            sb.append(entry.getKey());
            sb.append(':');
            sb.append(entry.getValue());
            sb.append("}, ");
        }
        if (!isEmpty()) {
            sb.replace(sb.length() - 2, sb.length(), "");
        }
        sb.append(" )");
        return sb.toString();
    }
}
