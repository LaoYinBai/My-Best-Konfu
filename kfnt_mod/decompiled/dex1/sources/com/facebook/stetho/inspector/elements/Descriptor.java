package com.facebook.stetho.inspector.elements;

import com.facebook.stetho.common.ThreadBound;
import com.facebook.stetho.common.UncheckedCallable;
import com.facebook.stetho.common.Util;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Nullable;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public abstract class Descriptor<E> implements NodeDescriptor<E> {
    private Host mHost;

    public interface Host extends ThreadBound {
        @Nullable
        Descriptor<?> getDescriptor(@Nullable Object obj);

        void onAttributeModified(Object obj, String str, String str2);

        void onAttributeRemoved(Object obj, String str);
    }

    protected Descriptor() {
    }

    final void initialize(Host host) {
        Util.throwIfNull(host);
        Util.throwIfNotNull(this.mHost);
        this.mHost = host;
    }

    final boolean isInitialized() {
        return this.mHost != null;
    }

    protected final Host getHost() {
        return this.mHost;
    }

    @Override // com.facebook.stetho.common.ThreadBound
    public final boolean checkThreadAccess() {
        return getHost().checkThreadAccess();
    }

    @Override // com.facebook.stetho.common.ThreadBound
    public final void verifyThreadAccess() {
        getHost().verifyThreadAccess();
    }

    @Override // com.facebook.stetho.common.ThreadBound
    public final <V> V postAndWait(UncheckedCallable<V> uncheckedCallable) {
        return (V) getHost().postAndWait(uncheckedCallable);
    }

    @Override // com.facebook.stetho.common.ThreadBound
    public final void postAndWait(Runnable runnable) {
        getHost().postAndWait(runnable);
    }

    @Override // com.facebook.stetho.common.ThreadBound
    public final void postDelayed(Runnable runnable, long j) {
        getHost().postDelayed(runnable, j);
    }

    @Override // com.facebook.stetho.common.ThreadBound
    public final void removeCallbacks(Runnable runnable) {
        getHost().removeCallbacks(runnable);
    }

    protected static Map<String, String> parseSetAttributesAsTextArg(String str) {
        String string = "";
        StringBuilder sb = new StringBuilder();
        HashMap map = new HashMap();
        int length = str.length();
        String string2 = "";
        boolean z = false;
        for (int i = 0; i < length; i++) {
            char cCharAt = str.charAt(i);
            if (cCharAt == '=') {
                string = sb.toString();
                sb.setLength(0);
            } else if (cCharAt == '\"') {
                if (z) {
                    string2 = sb.toString();
                    sb.setLength(0);
                }
                z = !z;
            } else if (cCharAt == ' ' && !z) {
                map.put(string, string2);
            } else {
                sb.append(cCharAt);
            }
        }
        if (!string.isEmpty() && !string2.isEmpty()) {
            map.put(string, string2);
        }
        return map;
    }
}
