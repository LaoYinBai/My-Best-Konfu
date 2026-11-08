package anet.channel.strategy.utils;

import java.util.LinkedHashMap;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class SerialLruCache<K, V> extends LinkedHashMap<K, V> {

    /* renamed from: a, reason: collision with root package name */
    private int f588a;

    public boolean entryRemoved(Map.Entry<K, V> entry) {
        return true;
    }

    public SerialLruCache(int i) {
        super(i + 1, 1.0f, true);
        this.f588a = i;
    }

    @Override // java.util.LinkedHashMap
    protected boolean removeEldestEntry(Map.Entry<K, V> entry) {
        if (size() > this.f588a) {
            return entryRemoved(entry);
        }
        return false;
    }
}
