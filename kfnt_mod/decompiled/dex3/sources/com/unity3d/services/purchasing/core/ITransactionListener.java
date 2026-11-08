package com.unity3d.services.purchasing.core;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public interface ITransactionListener {
    void onTransactionComplete(TransactionDetails transactionDetails);

    void onTransactionError(TransactionErrorDetails transactionErrorDetails);
}
