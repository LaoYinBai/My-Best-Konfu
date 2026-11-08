package com.tachikoma.core.manager;

import com.tachikoma.core.bridge.TKJSContext;
import com.tachikoma.core.common.ILifeCycle;
import java.util.HashMap;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class TKJSContextManager implements ILifeCycle {
    private HashMap<String, TKJSContext> mContextMap;

    @Override // com.tachikoma.core.common.ILifeCycle
    public void onCreate() {
        this.mContextMap = new HashMap<>();
    }

    @Override // com.tachikoma.core.common.ILifeCycle
    public void onDestroy() {
        this.mContextMap.clear();
    }

    public void putNJJSContext(TKJSContext tKJSContext) {
        this.mContextMap.put(tKJSContext.toString(), tKJSContext);
    }

    public void removeNJJSContext(String str) {
        this.mContextMap.remove(str);
    }
}
