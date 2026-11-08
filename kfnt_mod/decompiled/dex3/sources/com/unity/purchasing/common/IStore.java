package com.unity.purchasing.common;

import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public interface IStore {
    void FinishTransaction(ProductDefinition productDefinition, String str);

    void Purchase(ProductDefinition productDefinition);

    void Purchase(ProductDefinition productDefinition, String str);

    void RetrieveProducts(List<ProductDefinition> list);
}
