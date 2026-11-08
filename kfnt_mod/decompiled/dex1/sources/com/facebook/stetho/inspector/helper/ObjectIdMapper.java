package com.facebook.stetho.inspector.helper;

import android.util.SparseArray;
import java.util.IdentityHashMap;
import java.util.Map;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class ObjectIdMapper {
    protected final Object mSync = new Object();

    @GuardedBy("mSync")
    private int mNextId = 1;

    @GuardedBy("mSync")
    private final Map<Object, Integer> mObjectToIdMap = new IdentityHashMap();

    @GuardedBy("mSync")
    private SparseArray<Object> mIdToObjectMap = new SparseArray<>();

    protected void onMapped(Object obj, int i) {
    }

    protected void onUnmapped(Object obj, int i) {
    }

    public void clear() {
        SparseArray<Object> sparseArray;
        synchronized (this.mSync) {
            sparseArray = this.mIdToObjectMap;
            this.mObjectToIdMap.clear();
            this.mIdToObjectMap = new SparseArray<>();
        }
        int size = sparseArray.size();
        for (int i = 0; i < size; i++) {
            onUnmapped(sparseArray.valueAt(i), sparseArray.keyAt(i));
        }
    }

    public boolean containsId(int i) {
        boolean z;
        synchronized (this.mSync) {
            z = this.mIdToObjectMap.get(i) != null;
        }
        return z;
    }

    public boolean containsObject(Object obj) {
        boolean zContainsKey;
        synchronized (this.mSync) {
            zContainsKey = this.mObjectToIdMap.containsKey(obj);
        }
        return zContainsKey;
    }

    @Nullable
    public Object getObjectForId(int i) {
        Object obj;
        synchronized (this.mSync) {
            obj = this.mIdToObjectMap.get(i);
        }
        return obj;
    }

    @Nullable
    public Integer getIdForObject(Object obj) {
        Integer num;
        synchronized (this.mSync) {
            num = this.mObjectToIdMap.get(obj);
        }
        return num;
    }

    public int putObject(Object obj) {
        synchronized (this.mSync) {
            Integer num = this.mObjectToIdMap.get(obj);
            if (num != null) {
                return num.intValue();
            }
            int i = this.mNextId;
            this.mNextId = i + 1;
            Integer numValueOf = Integer.valueOf(i);
            this.mObjectToIdMap.put(obj, numValueOf);
            this.mIdToObjectMap.put(numValueOf.intValue(), obj);
            onMapped(obj, numValueOf.intValue());
            return numValueOf.intValue();
        }
    }

    @Nullable
    public Object removeObjectById(int i) {
        synchronized (this.mSync) {
            Object obj = this.mIdToObjectMap.get(i);
            if (obj == null) {
                return null;
            }
            this.mIdToObjectMap.remove(i);
            this.mObjectToIdMap.remove(obj);
            onUnmapped(obj, i);
            return obj;
        }
    }

    @Nullable
    public Integer removeObject(Object obj) {
        synchronized (this.mSync) {
            Integer numRemove = this.mObjectToIdMap.remove(obj);
            if (numRemove == null) {
                return null;
            }
            this.mIdToObjectMap.remove(numRemove.intValue());
            onUnmapped(obj, numRemove.intValue());
            return numRemove;
        }
    }

    public int size() {
        int size;
        synchronized (this.mSync) {
            size = this.mObjectToIdMap.size();
        }
        return size;
    }
}
