package com.bytedance.pangle.res;

import android.content.res.AssetManager;
import android.content.res.Resources;
import android.os.Build;
import android.text.TextUtils;
import anet.channel.strategy.dispatch.DispatchConstants;
import com.bytedance.pangle.Zeus;
import com.bytedance.pangle.log.ZeusLogger;
import com.bytedance.pangle.util.FieldUtils;
import com.bytedance.pangle.util.MethodUtils;
import com.bytedance.pangle.util.g;
import com.bytedance.pangle.util.h;
import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONException;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public final class a {

    /* renamed from: a, reason: collision with root package name */
    private static Map<String, Integer> f4680a = new HashMap();

    /* renamed from: b, reason: collision with root package name */
    private LinkedHashMap<String, Integer> f4681b = new LinkedHashMap<>();

    static {
        List<String> listA = h.a();
        if (listA == null || listA.size() <= 0) {
            return;
        }
        Iterator<String> it = listA.iterator();
        while (it.hasNext()) {
            f4680a.put(it.next(), 0);
        }
    }

    public a() {
        this.f4681b.put(Zeus.getAppApplication().getApplicationInfo().sourceDir, 0);
    }

    public final AssetManager a(AssetManager assetManager, String str, boolean z) throws JSONException, NoSuchMethodException, SecurityException {
        AssetManager assetManagerA;
        if (!g.a()) {
            assetManagerA = a(assetManager, str);
        } else {
            if (Build.VERSION.SDK_INT >= 21 && Build.VERSION.SDK_INT <= 25) {
                assetManagerA = c(assetManager, str, z);
                if (!h.a(assetManagerA, str)) {
                    assetManagerA = b(assetManager, str, z);
                }
            } else {
                assetManagerA = b(assetManager, str, z);
            }
        }
        synchronized (this.f4681b) {
            this.f4681b.put(str, 0);
        }
        ZeusLogger.i(ZeusLogger.TAG_LOAD, "AssetManagerProcessor updateAssetManager, newAssetManager=" + assetManagerA + ", assets=" + h.b(assetManagerA));
        return assetManagerA;
    }

    private static AssetManager b(AssetManager assetManager, String str, boolean z) throws JSONException, NoSuchMethodException, SecurityException {
        int iIntValue;
        String str2 = z ? "addAssetPathAsSharedLibrary" : "addAssetPath";
        Method accessibleMethod = MethodUtils.getAccessibleMethod(AssetManager.class, str2, String.class);
        if (accessibleMethod == null && z) {
            str2 = "addAssetPath";
            accessibleMethod = MethodUtils.getAccessibleMethod(AssetManager.class, "addAssetPath", String.class);
            ZeusLogger.e(ZeusLogger.TAG_LOAD, "AssetManagerProcessor AssetManager.addAssetPath() invoke addAssetPathAsSharedLibrary failed. use addAssetPath.");
        }
        if (accessibleMethod != null) {
            int i = 3;
            while (true) {
                int i2 = i - 1;
                if (i < 0) {
                    break;
                }
                try {
                    iIntValue = ((Integer) accessibleMethod.invoke(assetManager, str)).intValue();
                } catch (Exception e) {
                    ZeusLogger.e(ZeusLogger.TAG_LOAD, "AssetManagerProcessor invoke AssetManager.addAssetPath() failed. asSharedLibrary = " + z + ", methodName = " + str2, e);
                }
                if (iIntValue != 0) {
                    ZeusLogger.i(ZeusLogger.TAG_LOAD, "AssetManagerProcessor invoke AssetManager.addAssetPath() success, cookie = " + iIntValue + ", path = " + str);
                    break;
                }
                ZeusLogger.e(ZeusLogger.TAG_LOAD, "AssetManagerProcessor invoke AssetManager.addAssetPath() failed, cookie = ".concat(String.valueOf(iIntValue)));
                i = i2;
            }
        } else {
            ZeusLogger.e(ZeusLogger.TAG_LOAD, "AssetManagerProcessor reflect AssetManager.addAssetPath() failed. addAssetPathMethod == null. asSharedLibrary = ".concat(String.valueOf(z)));
        }
        return assetManager;
    }

    private static AssetManager c(AssetManager assetManager, String str, boolean z) {
        int iIntValue;
        int i = 3;
        Throwable th = null;
        int i2 = 3;
        loop0: while (true) {
            int i3 = i2 - 1;
            if (i2 < 0) {
                break;
            }
            try {
                synchronized (assetManager) {
                    iIntValue = 0;
                    for (int i4 = 0; i4 < i; i4++) {
                        try {
                            if (g.b()) {
                                iIntValue = ((Integer) MethodUtils.invokeMethod(assetManager, "addAssetPathNative", new Object[]{str}, new Class[]{String.class})).intValue();
                            } else if (Build.VERSION.SDK_INT >= 24 && Build.VERSION.SDK_INT <= 25) {
                                iIntValue = ((Integer) MethodUtils.invokeMethod(assetManager, "addAssetPathNative", new Object[]{str, Boolean.valueOf(z)}, new Class[]{String.class, Boolean.TYPE})).intValue();
                            }
                            if (iIntValue != 0) {
                                break loop0;
                            }
                        } finally {
                        }
                    }
                    break loop0;
                }
            } catch (Throwable th2) {
                th = th2;
                i2 = i3;
                i = 3;
            }
        }
        if (iIntValue != 0) {
            Object field = FieldUtils.readField(assetManager, "mStringBlocks");
            int length = field != null ? Array.getLength(field) : 0;
            int iIntValue2 = ((Integer) MethodUtils.invokeMethod(assetManager, "getStringBlockCount", new Object[0])).intValue();
            Object objNewInstance = Array.newInstance(field.getClass().getComponentType(), iIntValue2);
            for (int i5 = 0; i5 < iIntValue2; i5++) {
                if (i5 < length) {
                    Array.set(objNewInstance, i5, Array.get(field, i5));
                } else {
                    Array.set(objNewInstance, i5, MethodUtils.invokeConstructor(field.getClass().getComponentType(), new Object[]{Long.valueOf(((Long) MethodUtils.invokeMethod(assetManager, "getNativeStringBlock", new Object[]{Integer.valueOf(i5)}, new Class[]{Integer.TYPE})).longValue()), Boolean.TRUE}, new Class[]{Long.TYPE, Boolean.TYPE}));
                }
            }
            FieldUtils.writeField(assetManager, "mStringBlocks", objNewInstance);
            ZeusLogger.d(ZeusLogger.TAG_LOAD, "AssetManagerProcessor appendAssetPathSafely success, sourceDir = ".concat(String.valueOf(str)));
        }
        if (th != null) {
            ZeusLogger.e(ZeusLogger.TAG_LOAD, "AssetManagerProcessor appendAssetPathSafely failed, sourceDir = ".concat(String.valueOf(str)), th);
        }
        return assetManager;
    }

    private AssetManager a(AssetManager assetManager, String str) throws JSONException, NoSuchMethodException, SecurityException {
        AssetManager assetManager2;
        List<String> listA = h.a(assetManager);
        ArrayList<String> arrayList = new ArrayList();
        StringBuilder sb = new StringBuilder();
        for (String str2 : listA) {
            if (!f4680a.containsKey(str2) && !this.f4681b.containsKey(str2) && !str2.equals(str)) {
                arrayList.add(str2);
            }
        }
        ZeusLogger.d(ZeusLogger.TAG_LOAD, "AssetManagerProcessor newAssetManager, runtimeAdditionalAssets");
        try {
            if (assetManager.getClass().getName().equals("android.content.res.BaiduAssetManager")) {
                assetManager2 = (AssetManager) Class.forName("android.content.res.BaiduAssetManager").getConstructor(new Class[0]).newInstance(new Object[0]);
            } else {
                assetManager2 = (AssetManager) AssetManager.class.newInstance();
            }
            ZeusLogger.i(ZeusLogger.TAG_LOAD, "AssetManagerProcessor newAssetManager = ".concat(String.valueOf(assetManager2)));
            synchronized (this.f4681b) {
                for (Map.Entry<String, Integer> entry : this.f4681b.entrySet()) {
                    if (!f4680a.containsKey(entry.getKey())) {
                        sb.append(entry.getKey());
                        b(assetManager2, entry.getKey(), false);
                    }
                }
            }
            if (!sb.toString().contains(Zeus.getAppApplication().getApplicationInfo().sourceDir)) {
                b(assetManager2, Zeus.getAppApplication().getApplicationInfo().sourceDir, false);
                ZeusLogger.e(ZeusLogger.TAG_LOAD, "AssetManagerProcessor newAssetManager lost host path : " + f4680a.containsKey(Zeus.getAppApplication().getApplicationInfo().sourceDir));
            }
            sb.append(str);
            b(assetManager2, str, false);
            if (!arrayList.isEmpty()) {
                for (String str3 : arrayList) {
                    sb.append(str3);
                    b(assetManager2, str3, false);
                }
            }
            if ((Build.VERSION.SDK_INT >= 21 && Build.VERSION.SDK_INT < 23) && !sb.toString().toLowerCase().contains("webview")) {
                try {
                    Resources resources = Zeus.getAppApplication().getResources();
                    String str4 = Zeus.getAppApplication().createPackageContext(resources.getString(resources.getIdentifier("android:string/config_webViewPackageName", "string", DispatchConstants.ANDROID)), 0).getApplicationInfo().sourceDir;
                    if (!TextUtils.isEmpty(str4)) {
                        b(assetManager2, str4, false);
                    }
                } catch (Exception e) {
                    ZeusLogger.e(ZeusLogger.TAG_LOAD, "AssetManagerProcessor newAssetManager appendAsset webview failed.", e);
                }
            }
            assetManager = assetManager2;
        } catch (Exception e2) {
            ZeusLogger.e(ZeusLogger.TAG_LOAD, "AssetManagerProcessor newAssetManager failed.", e2);
            b(assetManager, str, false);
        }
        try {
            MethodUtils.invokeMethod(assetManager, "ensureStringBlocks", new Object[0]);
            ZeusLogger.i(ZeusLogger.TAG_LOAD, "AssetManagerProcessor ensureStringBlocks");
        } catch (Exception e3) {
            ZeusLogger.e(ZeusLogger.TAG_LOAD, "AssetManagerProcessor ensureStringBlocks failed.", e3);
        }
        return assetManager;
    }
}
