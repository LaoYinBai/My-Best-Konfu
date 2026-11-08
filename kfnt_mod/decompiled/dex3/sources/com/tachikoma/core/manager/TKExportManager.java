package com.tachikoma.core.manager;

import com.tachikoma.core.common.ILifeCycle;
import com.tachikoma.core.component.IFactory;
import java.util.ArrayList;
import java.util.HashMap;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class TKExportManager implements ILifeCycle {
    private volatile boolean isInit;

    private static final class InstanceHolder {
        private static final TKExportManager instance = new TKExportManager();

        private InstanceHolder() {
        }
    }

    private TKExportManager() {
        this.isInit = false;
    }

    public static TKExportManager getInstance() {
        return InstanceHolder.instance;
    }

    private synchronized void maybeReInit() {
        if (!isCreate()) {
            onCreate();
        }
    }

    public synchronized HashMap<String, String> allExportClasses() {
        maybeReInit();
        return TKProviderCollection.allExportClasses();
    }

    public synchronized void applyAttributes(String str, Object obj, HashMap<String, Object> map) {
        maybeReInit();
        TKProviderCollection.applyAttributes(str, obj, map);
    }

    public synchronized void applyProperties(String str, Object obj, HashMap<String, Object> map) {
        maybeReInit();
        TKProviderCollection.applyProperties(str, obj, map);
    }

    public synchronized HashMap<String, Class[]> getClassMethods(String str) {
        maybeReInit();
        return TKProviderCollection.getClassMethods(str);
    }

    public synchronized IFactory getIFactory(String str) {
        maybeReInit();
        return TKProviderCollection.getIFactory(str);
    }

    public synchronized String getJsFunctionName(String str) {
        maybeReInit();
        return TKProviderCollection.getJsFunctionName(str);
    }

    public synchronized ArrayList<String> getProperties(String str) {
        maybeReInit();
        return TKProviderCollection.getProperties(str);
    }

    public boolean isCreate() {
        return this.isInit;
    }

    @Override // com.tachikoma.core.common.ILifeCycle
    public synchronized void onCreate() {
        TKProviderCollection.init();
        this.isInit = true;
    }

    @Override // com.tachikoma.core.common.ILifeCycle
    public synchronized void onDestroy() {
        TKProviderCollection.clear();
        this.isInit = false;
    }

    public synchronized HashMap<String, Object> retrieveEvent(String str, Object obj) {
        maybeReInit();
        return TKProviderCollection.retrieveEvent(str, obj);
    }
}
