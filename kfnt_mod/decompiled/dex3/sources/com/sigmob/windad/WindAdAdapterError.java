package com.sigmob.windad;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class WindAdAdapterError {
    int a;
    String message;

    public WindAdAdapterError(int i, String str) {
        this.a = i;
        this.message = str;
    }

    public int getErrorCode() {
        return this.a;
    }

    public String getMessage() {
        return this.message;
    }

    public void setErrorCode(int i) {
        this.a = i;
    }

    public void setMessage(String str) {
        this.message = str;
    }

    public String toString() {
        return "{errorCode:" + this.a + ", message:'" + this.message + "'}";
    }
}
