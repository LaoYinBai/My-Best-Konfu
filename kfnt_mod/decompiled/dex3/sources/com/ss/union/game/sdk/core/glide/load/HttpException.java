package com.ss.union.game.sdk.core.glide.load;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public final class HttpException extends Throwable {
    public static final int UNKNOWN = -1;
    private static final long serialVersionUID = 1;
    private final int a;

    public HttpException(int i) {
        this("Http request failed with status code: " + i, i);
    }

    public HttpException(String str) {
        this(str, -1);
    }

    public HttpException(String str, int i) {
        this(str, i, null);
    }

    public HttpException(String str, int i, Throwable th) {
        super(str, th);
        this.a = i;
    }

    public int getStatusCode() {
        return this.a;
    }
}
