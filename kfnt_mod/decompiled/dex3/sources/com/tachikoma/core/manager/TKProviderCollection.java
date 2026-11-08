package com.tachikoma.core.manager;

import com.tachikoma.core.AttributeProvider;
import com.tachikoma.core.ExportClassProvider;
import com.tachikoma.core.FactoryProvider;
import com.tachikoma.core.MethodProvider;
import com.tachikoma.core.PropertyProvider;
import com.tachikoma.core.component.IFactory;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class TKProviderCollection {
    public static ArrayList<IProvider> factoryProviderList = new ArrayList<>();
    public static ArrayList<IProvider> exportClassList = new ArrayList<>();
    public static ArrayList<IProvider> methodList = new ArrayList<>();
    public static ArrayList<IProvider> propertyProviderList = new ArrayList<>();
    public static ArrayList<IProvider> attributeProviderList = new ArrayList<>();
    private static final Set<String> sEXTRA_PKG = new HashSet();

    public static HashMap<String, String> allExportClasses() {
        HashMap<String, String> map = new HashMap<>();
        Iterator<IProvider> it = exportClassList.iterator();
        while (it.hasNext()) {
            map.putAll(it.next().get());
        }
        return map;
    }

    public static void applyAttributes(String str, Object obj, HashMap<String, Object> map) {
        Iterator<IProvider> it = attributeProviderList.iterator();
        while (it.hasNext()) {
            it.next().apply(str, obj, map);
        }
    }

    public static void applyProperties(String str, Object obj, HashMap<String, Object> map) {
        Iterator<IProvider> it = propertyProviderList.iterator();
        while (it.hasNext()) {
            it.next().apply(str, obj, map);
        }
    }

    public static void clear() {
        Iterator<IProvider> it = factoryProviderList.iterator();
        while (it.hasNext()) {
            it.next().clear();
        }
        factoryProviderList.clear();
        Iterator<IProvider> it2 = exportClassList.iterator();
        while (it2.hasNext()) {
            it2.next().clear();
        }
        exportClassList.clear();
        Iterator<IProvider> it3 = methodList.iterator();
        while (it3.hasNext()) {
            it3.next().clear();
        }
        methodList.clear();
        Iterator<IProvider> it4 = propertyProviderList.iterator();
        while (it4.hasNext()) {
            it4.next().clear();
        }
        propertyProviderList.clear();
        Iterator<IProvider> it5 = attributeProviderList.iterator();
        while (it5.hasNext()) {
            it5.next().clear();
        }
        attributeProviderList.clear();
    }

    private static IProvider createProvider(String str) {
        try {
            return (IProvider) TKProviderCollection.class.getClassLoader().loadClass(str).newInstance();
        } catch (Exception unused) {
            return null;
        }
    }

    public static HashMap<String, Class[]> getClassMethods(String str) {
        Iterator<IProvider> it = methodList.iterator();
        while (it.hasNext()) {
            IProvider next = it.next();
            if (next.of(str) != null) {
                return (HashMap) next.of(str);
            }
        }
        return null;
    }

    public static IFactory getIFactory(String str) {
        Iterator<IProvider> it = factoryProviderList.iterator();
        while (it.hasNext()) {
            IProvider next = it.next();
            if (next.of(str) != null) {
                return (IFactory) next.of(str);
            }
        }
        return null;
    }

    public static String getJsFunctionName(String str) {
        Iterator<IProvider> it = methodList.iterator();
        while (it.hasNext()) {
            IProvider next = it.next();
            if (next.get() != null && next.get().get(str) != null) {
                return next.get().get(str);
            }
        }
        return null;
    }

    public static ArrayList<String> getProperties(String str) {
        Iterator<IProvider> it = propertyProviderList.iterator();
        while (it.hasNext()) {
            IProvider next = it.next();
            if (next.of(str) != null) {
                return (ArrayList) next.of(str);
            }
        }
        return null;
    }

    public static void init() {
        register();
        Iterator<IProvider> it = factoryProviderList.iterator();
        while (it.hasNext()) {
            it.next().init();
        }
        Iterator<IProvider> it2 = exportClassList.iterator();
        while (it2.hasNext()) {
            it2.next().init();
        }
        Iterator<IProvider> it3 = methodList.iterator();
        while (it3.hasNext()) {
            it3.next().init();
        }
        Iterator<IProvider> it4 = propertyProviderList.iterator();
        while (it4.hasNext()) {
            it4.next().init();
        }
        Iterator<IProvider> it5 = attributeProviderList.iterator();
        while (it5.hasNext()) {
            it5.next().init();
        }
    }

    private static boolean isEmpty(Object[] objArr) {
        return objArr == null || objArr.length == 0;
    }

    private static void register() {
        registerExportClass(new ExportClassProvider());
        registerFactory(new FactoryProvider());
        registerMethod(new MethodProvider());
        registerProperty(new PropertyProvider());
        registerAttribute(new AttributeProvider());
        safelyRegisterExtras();
    }

    private static void registerAttribute(IProvider iProvider) {
        if (attributeProviderList.contains(iProvider)) {
            return;
        }
        attributeProviderList.add(iProvider);
    }

    private static void registerExportClass(IProvider iProvider) {
        if (exportClassList.contains(iProvider)) {
            return;
        }
        exportClassList.add(iProvider);
    }

    public static void registerExtraLib(String... strArr) {
        synchronized (sEXTRA_PKG) {
            if (isEmpty(strArr)) {
                return;
            }
            sEXTRA_PKG.addAll(Arrays.asList(strArr));
        }
    }

    private static void registerFactory(IProvider iProvider) {
        if (factoryProviderList.contains(iProvider)) {
            return;
        }
        factoryProviderList.add(iProvider);
    }

    private static void registerMethod(IProvider iProvider) {
        if (methodList.contains(iProvider)) {
            return;
        }
        methodList.add(iProvider);
    }

    private static void registerProperty(IProvider iProvider) {
        if (propertyProviderList.contains(iProvider)) {
            return;
        }
        propertyProviderList.add(iProvider);
    }

    public static HashMap<String, Object> retrieveEvent(String str, Object obj) {
        Iterator<IProvider> it = propertyProviderList.iterator();
        while (it.hasNext()) {
            IProvider next = it.next();
            HashMap<String, Object> mapRetrieveEvent = next.retrieveEvent(str, obj);
            if (mapRetrieveEvent != null && mapRetrieveEvent.size() > 0) {
                return next.retrieveEvent(str, obj);
            }
        }
        return null;
    }

    private static void safelyRegister(String str) {
        try {
            IProvider iProviderCreateProvider = createProvider(str + ".ExportClassProvider");
            if (iProviderCreateProvider != null) {
                registerExportClass(iProviderCreateProvider);
            }
            IProvider iProviderCreateProvider2 = createProvider(str + ".FactoryProvider");
            if (iProviderCreateProvider2 != null) {
                registerFactory(iProviderCreateProvider2);
            }
            IProvider iProviderCreateProvider3 = createProvider(str + ".MethodProvider");
            if (iProviderCreateProvider3 != null) {
                registerMethod(iProviderCreateProvider3);
            }
            IProvider iProviderCreateProvider4 = createProvider(str + ".PropertyProvider");
            if (iProviderCreateProvider4 != null) {
                registerProperty(iProviderCreateProvider4);
            }
            IProvider iProviderCreateProvider5 = createProvider(str + ".AttributeProvider");
            if (iProviderCreateProvider5 != null) {
                registerAttribute(iProviderCreateProvider5);
            }
        } catch (Exception unused) {
        }
    }

    private static void safelyRegisterExtras() {
        registerExtraLib("com.tachikoma.plugin", "com.tachikoma.kwai.tach");
        synchronized (sEXTRA_PKG) {
            Iterator<String> it = sEXTRA_PKG.iterator();
            while (it.hasNext()) {
                safelyRegister(it.next());
            }
        }
    }

    public static void unregisterExtraLib(String... strArr) {
        synchronized (sEXTRA_PKG) {
            if (isEmpty(strArr)) {
                return;
            }
            for (String str : strArr) {
                sEXTRA_PKG.remove(str);
            }
        }
    }
}
