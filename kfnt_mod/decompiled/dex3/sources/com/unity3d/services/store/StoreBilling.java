package com.unity3d.services.store;

import android.content.Context;
import android.os.Bundle;
import android.os.IBinder;
import com.unity.purchasing.googleplay.IabHelper;
import com.unity3d.services.core.log.DeviceLog;
import com.unity3d.services.core.properties.ClientProperties;
import com.unity3d.services.store.core.StoreException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class StoreBilling {
    public static Object asInterface(Context context, IBinder iBinder) throws IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
        new Object[1][0] = iBinder;
        try {
            try {
                try {
                    return Class.forName("com.android.vending.billing.IInAppBillingService$Stub").getMethod("asInterface", IBinder.class).invoke(null, iBinder);
                } catch (IllegalAccessException e) {
                    DeviceLog.exception("Illegal access exception while invoking asInterface", e);
                    return null;
                } catch (InvocationTargetException e2) {
                    DeviceLog.exception("Invocation target exception while invoking asInterface", e2);
                    return null;
                }
            } catch (NoSuchMethodException e3) {
                DeviceLog.exception("asInterface method not found", e3);
                return null;
            }
        } catch (ClassNotFoundException e4) {
            DeviceLog.exception("Billing service stub not found", e4);
            return null;
        }
    }

    public static int isBillingSupported(Context context, Object obj, String str) throws IllegalAccessException, StoreException, NoSuchMethodException, ClassNotFoundException, IllegalArgumentException, InvocationTargetException {
        Object objInvoke = Class.forName("com.android.vending.billing.IInAppBillingService").getMethod("isBillingSupported", Integer.TYPE, String.class, String.class).invoke(obj, 3, ClientProperties.getAppName(), str);
        if (objInvoke != null) {
            return ((Integer) objInvoke).intValue();
        }
        throw new StoreException();
    }

    public static JSONObject getPurchases(Context context, Object obj, String str) throws IllegalAccessException, JSONException, StoreException, NoSuchMethodException, SecurityException, ClassNotFoundException, IllegalArgumentException, InvocationTargetException {
        Method method = Class.forName("com.android.vending.billing.IInAppBillingService").getMethod("getPurchases", Integer.TYPE, String.class, String.class, String.class);
        JSONObject jSONObject = new JSONObject();
        JSONArray jSONArray = new JSONArray();
        JSONArray jSONArray2 = new JSONArray();
        JSONArray jSONArray3 = new JSONArray();
        String string = null;
        do {
            Object objInvoke = method.invoke(obj, 3, ClientProperties.getAppName(), str, string);
            if (objInvoke instanceof Bundle) {
                Bundle bundle = (Bundle) objInvoke;
                int i = bundle.getInt("RESPONSE_CODE");
                DeviceLog.debug("getPurchases responds with code " + i);
                if (i == 0) {
                    Iterator<String> it = bundle.getStringArrayList(IabHelper.RESPONSE_INAPP_PURCHASE_DATA_LIST).iterator();
                    while (it.hasNext()) {
                        jSONArray.put(new JSONObject(it.next()));
                    }
                    Iterator<String> it2 = bundle.getStringArrayList(IabHelper.RESPONSE_INAPP_SIGNATURE_LIST).iterator();
                    while (it2.hasNext()) {
                        jSONArray2.put(it2.next());
                    }
                    Iterator<String> it3 = bundle.getStringArrayList(IabHelper.RESPONSE_INAPP_ITEM_LIST).iterator();
                    while (it3.hasNext()) {
                        jSONArray3.put(it3.next());
                    }
                    string = bundle.getString(IabHelper.INAPP_CONTINUATION_TOKEN);
                } else {
                    throw new StoreException(i);
                }
            } else {
                throw new StoreException();
            }
        } while (string != null);
        jSONObject.put("purchaseDataList", jSONArray);
        jSONObject.put("signatureList", jSONArray2);
        jSONObject.put("purchaseItemList", jSONArray3);
        return jSONObject;
    }

    public static JSONObject getPurchaseHistory(Context context, Object obj, String str, int i) throws IllegalAccessException, JSONException, StoreException, NoSuchMethodException, SecurityException, ClassNotFoundException, IllegalArgumentException, InvocationTargetException {
        int i2 = 5;
        char c = 0;
        char c2 = 1;
        Method method = Class.forName("com.android.vending.billing.IInAppBillingService").getMethod("getPurchaseHistory", Integer.TYPE, String.class, String.class, String.class, Bundle.class);
        JSONObject jSONObject = new JSONObject();
        JSONArray jSONArray = new JSONArray();
        JSONArray jSONArray2 = new JSONArray();
        JSONArray jSONArray3 = new JSONArray();
        String str2 = null;
        int i3 = 0;
        while (true) {
            Object[] objArr = new Object[i2];
            objArr[c] = 6;
            objArr[c2] = ClientProperties.getAppName();
            objArr[2] = str;
            objArr[3] = str2;
            objArr[4] = new Bundle();
            Object objInvoke = method.invoke(obj, objArr);
            if (objInvoke instanceof Bundle) {
                Bundle bundle = (Bundle) objInvoke;
                int i4 = bundle.getInt("RESPONSE_CODE");
                if (i4 == 0) {
                    Iterator<String> it = bundle.getStringArrayList(IabHelper.RESPONSE_INAPP_PURCHASE_DATA_LIST).iterator();
                    while (it.hasNext()) {
                        jSONArray.put(new JSONObject(it.next()));
                        i3++;
                    }
                    Iterator<String> it2 = bundle.getStringArrayList(IabHelper.RESPONSE_INAPP_SIGNATURE_LIST).iterator();
                    while (it2.hasNext()) {
                        jSONArray2.put(it2.next());
                    }
                    Iterator<String> it3 = bundle.getStringArrayList(IabHelper.RESPONSE_INAPP_ITEM_LIST).iterator();
                    while (it3.hasNext()) {
                        jSONArray3.put(it3.next());
                    }
                    String string = bundle.getString(IabHelper.INAPP_CONTINUATION_TOKEN);
                    if (string == null || (i != 0 && i3 >= i)) {
                        break;
                    }
                    str2 = string;
                    i2 = 5;
                    c = 0;
                    c2 = 1;
                } else {
                    throw new StoreException(i4);
                }
            } else {
                throw new StoreException();
            }
        }
        jSONObject.put("purchaseDataList", jSONArray);
        jSONObject.put("signatureList", jSONArray2);
        jSONObject.put("purchaseItemList", jSONArray3);
        return jSONObject;
    }

    public static JSONArray getSkuDetails(Context context, Object obj, String str, ArrayList<String> arrayList) throws IllegalAccessException, JSONException, StoreException, NoSuchMethodException, SecurityException, ClassNotFoundException, IllegalArgumentException, InvocationTargetException {
        Method method = Class.forName("com.android.vending.billing.IInAppBillingService").getMethod("getSkuDetails", Integer.TYPE, String.class, String.class, Bundle.class);
        Bundle bundle = new Bundle();
        bundle.putStringArrayList(IabHelper.GET_SKU_DETAILS_ITEM_LIST, arrayList);
        Object objInvoke = method.invoke(obj, 3, ClientProperties.getAppName(), str, bundle);
        JSONArray jSONArray = new JSONArray();
        if (objInvoke instanceof Bundle) {
            Bundle bundle2 = (Bundle) objInvoke;
            int i = bundle2.getInt("RESPONSE_CODE");
            if (i == 0) {
                Iterator<String> it = bundle2.getStringArrayList(IabHelper.RESPONSE_GET_SKU_DETAILS_LIST).iterator();
                while (it.hasNext()) {
                    jSONArray.put(new JSONObject(it.next()));
                }
                return jSONArray;
            }
            throw new StoreException(i);
        }
        throw new StoreException();
    }
}
