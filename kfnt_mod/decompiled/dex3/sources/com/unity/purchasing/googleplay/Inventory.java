package com.unity.purchasing.googleplay;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class Inventory {
    public Map<String, SkuDetails> mSkuMap = new HashMap();
    Map<String, Purchase> mPurchaseMap = new HashMap();
    Map<String, Purchase> mConsumablePurchaseHistoryMap = new HashMap();
    Set<String> mPurchaseHistoryOfSub = new HashSet();

    Inventory() {
    }

    public boolean hasPurchaseHistory(String str) {
        return this.mPurchaseHistoryOfSub.contains(str);
    }

    public List<String> getSubscriptionsWithHistory() {
        return new ArrayList(this.mPurchaseHistoryOfSub);
    }

    public SkuDetails getSkuDetails(String str) {
        return this.mSkuMap.get(str);
    }

    public Purchase getPurchase(String str) {
        return this.mPurchaseMap.get(str);
    }

    public Purchase getHistoryPurchase(String str) {
        return this.mConsumablePurchaseHistoryMap.get(str);
    }

    public boolean hasPurchase(String str) {
        return this.mPurchaseMap.containsKey(str);
    }

    public boolean hasConsumablePurchaseHistory(String str) {
        return this.mConsumablePurchaseHistoryMap.containsKey(str);
    }

    public boolean hasDetails(String str) {
        return this.mSkuMap.containsKey(str);
    }

    public void erasePurchase(String str) {
        if (this.mPurchaseMap.containsKey(str)) {
            this.mPurchaseMap.remove(str);
        }
    }

    List<String> getAllOwnedSkus() {
        return new ArrayList(this.mPurchaseMap.keySet());
    }

    List<String> getAllSkus(String str) {
        ArrayList arrayList = new ArrayList();
        for (SkuDetails skuDetails : this.mSkuMap.values()) {
            if (skuDetails.getType().equals(str)) {
                arrayList.add(skuDetails.getSku());
            }
        }
        return arrayList;
    }

    List<String> getAllOwnedSkus(String str) {
        ArrayList arrayList = new ArrayList();
        for (Purchase purchase : this.mPurchaseMap.values()) {
            if (purchase.getItemType().equals(str)) {
                arrayList.add(purchase.getSku());
            }
        }
        return arrayList;
    }

    List<Purchase> getAllPurchases() {
        return new ArrayList(this.mPurchaseMap.values());
    }

    void addSkuDetails(SkuDetails skuDetails) {
        this.mSkuMap.put(skuDetails.getSku(), skuDetails);
    }

    void addPurchase(Purchase purchase) {
        this.mPurchaseMap.put(purchase.getSku(), purchase);
    }

    void addPurchaseToSubscriptionPurchaseHistory(String str) {
        this.mPurchaseHistoryOfSub.add(str);
    }

    void addPurchaseToConsumablePurchaseHistory(String str, Purchase purchase) {
        this.mConsumablePurchaseHistoryMap.put(str, purchase);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("skuDetails = ");
        sb.append("[");
        for (String str : this.mSkuMap.keySet()) {
            sb.append(str);
            sb.append(" = ");
            sb.append(getSkuDetails(str));
            sb.append(", ");
        }
        sb.append("]");
        sb.append(", purchases = ");
        sb.append("[");
        for (String str2 : this.mPurchaseMap.keySet()) {
            sb.append(str2);
            sb.append(" = ");
            sb.append(getSkuDetails(str2));
            sb.append(", ");
        }
        sb.append("]");
        return "{Inventory: " + sb.toString() + "}";
    }
}
