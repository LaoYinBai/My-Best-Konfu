package com.kwad.sdk.crash.model.message;

import androidx.annotation.Nullable;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class JavaExceptionMessage extends ExceptionMessage {
    private static final long serialVersionUID = -2410125079234148135L;

    public JavaExceptionMessage() {
        this.mExceptionType = 1;
    }

    @Override // com.kwad.sdk.crash.model.message.ExceptionMessage
    protected String getTypePrefix() {
        return "";
    }

    @Override // com.kwad.sdk.crash.model.message.ExceptionMessage, com.kwad.sdk.core.b
    public void parseJson(@Nullable JSONObject jSONObject) {
        super.parseJson(jSONObject);
        if (jSONObject == null) {
        }
    }

    @Override // com.kwad.sdk.crash.model.message.ExceptionMessage, com.kwad.sdk.core.b
    public JSONObject toJson() {
        return super.toJson();
    }
}
