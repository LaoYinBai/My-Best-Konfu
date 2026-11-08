package com.unity3d.services.store.core.api;

import com.unity3d.services.core.webview.WebViewApp;
import com.unity3d.services.core.webview.WebViewEventCategory;
import com.unity3d.services.core.webview.bridge.WebViewCallback;
import com.unity3d.services.core.webview.bridge.WebViewExposed;
import com.unity3d.services.store.StoreError;
import com.unity3d.services.store.StoreEvent;
import com.unity3d.services.store.StoreMonitor;
import com.unity3d.services.store.core.StoreException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class Store {
    @WebViewExposed
    public static void initialize(String str, String str2, WebViewCallback webViewCallback) throws IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
        try {
            StoreMonitor.initialize(str, str2);
            webViewCallback.invoke(new Object[0]);
        } catch (Exception e) {
            webViewCallback.error(StoreError.UNKNOWN_ERROR, e.getMessage(), e.getClass().getName());
        }
    }

    @WebViewExposed
    public static void startPurchaseTracking(Boolean bool, JSONArray jSONArray, JSONArray jSONArray2, WebViewCallback webViewCallback) throws IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
        if (!StoreMonitor.isInitialized()) {
            webViewCallback.error(StoreError.NOT_INITIALIZED, new Object[0]);
            return;
        }
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        for (int i = 0; i < jSONArray.length(); i++) {
            try {
                arrayList.add(jSONArray.getString(i));
            } catch (JSONException e) {
                webViewCallback.error(StoreError.JSON_ERROR, e.getMessage());
                return;
            }
        }
        for (int i2 = 0; i2 < jSONArray2.length(); i2++) {
            arrayList2.add(jSONArray2.getString(i2));
        }
        StoreMonitor.startPurchaseTracking(bool.booleanValue(), arrayList, arrayList2);
        webViewCallback.invoke(new Object[0]);
    }

    @WebViewExposed
    public static void stopPurchaseTracking(WebViewCallback webViewCallback) throws IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
        if (!StoreMonitor.isInitialized()) {
            webViewCallback.error(StoreError.NOT_INITIALIZED, new Object[0]);
        } else {
            StoreMonitor.stopPurchaseTracking();
            webViewCallback.invoke(new Object[0]);
        }
    }

    @WebViewExposed
    public static void isBillingSupported(final Integer num, final String str, WebViewCallback webViewCallback) throws IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
        if (!StoreMonitor.isInitialized()) {
            webViewCallback.error(StoreError.NOT_INITIALIZED, new Object[0]);
        } else {
            new Thread(new Runnable() { // from class: com.unity3d.services.store.core.api.Store.1
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.STORE, StoreEvent.BILLING_SUPPORTED_RESULT, num, Integer.valueOf(StoreMonitor.isBillingSupported(str)));
                    } catch (StoreException e) {
                        WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.STORE, StoreEvent.BILLING_SUPPORTED_ERROR, num, StoreError.STORE_ERROR, e.getMessage(), Integer.valueOf(e.getResultCode()));
                    } catch (ClassNotFoundException e2) {
                        WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.STORE, StoreEvent.BILLING_SUPPORTED_ERROR, num, StoreError.CLASS_NOT_FOUND, e2.getMessage());
                    } catch (IllegalAccessException e3) {
                        WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.STORE, StoreEvent.BILLING_SUPPORTED_ERROR, num, StoreError.ILLEGAL_ACCESS, e3.getMessage());
                    } catch (NoSuchMethodException e4) {
                        WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.STORE, StoreEvent.BILLING_SUPPORTED_ERROR, num, StoreError.NO_SUCH_METHOD, e4.getMessage());
                    } catch (InvocationTargetException e5) {
                        WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.STORE, StoreEvent.BILLING_SUPPORTED_ERROR, num, StoreError.INVOCATION_TARGET, e5.getMessage());
                    } catch (Exception e6) {
                        WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.STORE, StoreEvent.BILLING_SUPPORTED_ERROR, num, StoreError.UNKNOWN_ERROR, e6.getMessage(), e6.getClass().getName());
                    }
                }
            }).start();
            webViewCallback.invoke(new Object[0]);
        }
    }

    @WebViewExposed
    public static void getPurchases(final Integer num, final String str, WebViewCallback webViewCallback) throws IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
        if (!StoreMonitor.isInitialized()) {
            webViewCallback.error(StoreError.NOT_INITIALIZED, new Object[0]);
        } else {
            new Thread(new Runnable() { // from class: com.unity3d.services.store.core.api.Store.2
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.STORE, StoreEvent.GETPURCHASES_RESULT, num, StoreMonitor.getPurchases(str));
                    } catch (StoreException e) {
                        WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.STORE, StoreEvent.GETPURCHASES_ERROR, num, StoreError.STORE_ERROR, e.getMessage(), Integer.valueOf(e.getResultCode()));
                    } catch (ClassNotFoundException e2) {
                        WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.STORE, StoreEvent.GETPURCHASES_ERROR, num, StoreError.CLASS_NOT_FOUND, e2.getMessage());
                    } catch (IllegalAccessException e3) {
                        WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.STORE, StoreEvent.GETPURCHASES_ERROR, num, StoreError.ILLEGAL_ACCESS, e3.getMessage());
                    } catch (NoSuchMethodException e4) {
                        WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.STORE, StoreEvent.GETPURCHASES_ERROR, num, StoreError.NO_SUCH_METHOD, e4.getMessage());
                    } catch (InvocationTargetException e5) {
                        WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.STORE, StoreEvent.GETPURCHASES_ERROR, num, StoreError.INVOCATION_TARGET, e5.getMessage());
                    } catch (JSONException e6) {
                        WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.STORE, StoreEvent.GETPURCHASES_ERROR, num, StoreError.JSON_ERROR, e6.getMessage());
                    } catch (Exception e7) {
                        WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.STORE, StoreEvent.GETPURCHASES_ERROR, num, StoreError.UNKNOWN_ERROR, e7.getMessage(), e7.getClass().getName());
                    }
                }
            }).start();
            webViewCallback.invoke(new Object[0]);
        }
    }

    @WebViewExposed
    public static void getPurchaseHistory(final Integer num, final String str, final Integer num2, WebViewCallback webViewCallback) throws IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
        if (!StoreMonitor.isInitialized()) {
            webViewCallback.error(StoreError.NOT_INITIALIZED, new Object[0]);
        } else {
            new Thread(new Runnable() { // from class: com.unity3d.services.store.core.api.Store.3
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.STORE, StoreEvent.PURCHASE_HISTORY_RESULT, num, StoreMonitor.getPurchaseHistory(str, num2.intValue()));
                    } catch (StoreException e) {
                        WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.STORE, StoreEvent.PURCHASE_HISTORY_ERROR, num, StoreError.STORE_ERROR, e.getMessage(), Integer.valueOf(e.getResultCode()));
                    } catch (ClassNotFoundException e2) {
                        WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.STORE, StoreEvent.PURCHASE_HISTORY_ERROR, num, StoreError.CLASS_NOT_FOUND, e2.getMessage());
                    } catch (IllegalAccessException e3) {
                        WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.STORE, StoreEvent.PURCHASE_HISTORY_ERROR, num, StoreError.ILLEGAL_ACCESS, e3.getMessage());
                    } catch (NoSuchMethodException e4) {
                        WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.STORE, StoreEvent.PURCHASE_HISTORY_ERROR, num, StoreError.NO_SUCH_METHOD, e4.getMessage());
                    } catch (InvocationTargetException e5) {
                        WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.STORE, StoreEvent.PURCHASE_HISTORY_ERROR, num, StoreError.INVOCATION_TARGET, e5.getMessage());
                    } catch (JSONException e6) {
                        WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.STORE, StoreEvent.PURCHASE_HISTORY_ERROR, num, StoreError.JSON_ERROR, e6.getMessage());
                    } catch (Exception e7) {
                        WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.STORE, StoreEvent.PURCHASE_HISTORY_ERROR, num, StoreError.UNKNOWN_ERROR, e7.getMessage(), e7.getClass().getName());
                    }
                }
            }).start();
            webViewCallback.invoke(new Object[0]);
        }
    }

    @WebViewExposed
    public static void getSkuDetails(final Integer num, final String str, final JSONArray jSONArray, WebViewCallback webViewCallback) throws IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
        if (!StoreMonitor.isInitialized()) {
            webViewCallback.error(StoreError.NOT_INITIALIZED, new Object[0]);
        } else {
            new Thread(new Runnable() { // from class: com.unity3d.services.store.core.api.Store.4
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        ArrayList arrayList = new ArrayList();
                        for (int i = 0; i < jSONArray.length(); i++) {
                            arrayList.add(jSONArray.getString(i));
                        }
                        WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.STORE, StoreEvent.SKU_DETAILS_RESULT, num, StoreMonitor.getSkuDetails(str, arrayList));
                    } catch (StoreException e) {
                        WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.STORE, StoreEvent.SKU_DETAILS_ERROR, num, StoreError.STORE_ERROR, e.getMessage(), Integer.valueOf(e.getResultCode()));
                    } catch (ClassNotFoundException e2) {
                        WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.STORE, StoreEvent.SKU_DETAILS_ERROR, num, StoreError.CLASS_NOT_FOUND, e2.getMessage());
                    } catch (IllegalAccessException e3) {
                        WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.STORE, StoreEvent.SKU_DETAILS_ERROR, num, StoreError.ILLEGAL_ACCESS, e3.getMessage());
                    } catch (NoSuchMethodException e4) {
                        WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.STORE, StoreEvent.SKU_DETAILS_ERROR, num, StoreError.NO_SUCH_METHOD, e4.getMessage());
                    } catch (InvocationTargetException e5) {
                        WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.STORE, StoreEvent.SKU_DETAILS_ERROR, num, StoreError.INVOCATION_TARGET, e5.getMessage());
                    } catch (JSONException e6) {
                        WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.STORE, StoreEvent.SKU_DETAILS_ERROR, num, StoreError.JSON_ERROR, e6.getMessage());
                    } catch (Exception e7) {
                        WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.STORE, StoreEvent.SKU_DETAILS_ERROR, num, StoreError.UNKNOWN_ERROR, e7.getMessage(), e7.getClass().getName());
                    }
                }
            }).start();
            webViewCallback.invoke(new Object[0]);
        }
    }
}
