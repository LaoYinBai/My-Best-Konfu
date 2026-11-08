package com.facebook.stetho.inspector.jsonrpc;

import com.facebook.stetho.common.Util;
import com.facebook.stetho.inspector.jsonrpc.protocol.JsonRpcError;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class JsonRpcException extends Exception {
    private final JsonRpcError mErrorMessage;

    public JsonRpcException(JsonRpcError jsonRpcError) {
        super(jsonRpcError.code + ": " + jsonRpcError.message);
        this.mErrorMessage = (JsonRpcError) Util.throwIfNull(jsonRpcError);
    }

    public JsonRpcError getErrorMessage() {
        return this.mErrorMessage;
    }
}
