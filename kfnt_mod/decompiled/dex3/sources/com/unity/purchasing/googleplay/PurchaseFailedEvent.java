package com.unity.purchasing.googleplay;

import com.unity.purchasing.common.PurchaseFailureReason;
import com.unity.purchasing.common.SaneJSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class PurchaseFailedEvent {
    public static String jsonEncodePurchaseFailure(String str, PurchaseFailureReason purchaseFailureReason, String str2) {
        SaneJSONObject saneJSONObject = new SaneJSONObject();
        saneJSONObject.put("productId", str);
        saneJSONObject.put("reason", purchaseFailureReason);
        saneJSONObject.put("message", str2);
        return saneJSONObject.toString();
    }
}
