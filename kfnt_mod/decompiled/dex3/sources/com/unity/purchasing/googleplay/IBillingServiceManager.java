package com.unity.purchasing.googleplay;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public interface IBillingServiceManager {
    void dispose();

    void initialise() throws GooglePlayBillingUnAvailableException;

    void workWith(BillingServiceProcessor billingServiceProcessor);
}
