package com.bytedance.sdk.adnet.game.ok3.okhttp3;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public interface Authenticator {
    public static final Authenticator NONE = new Authenticator() { // from class: com.bytedance.sdk.adnet.game.ok3.okhttp3.Authenticator.1
        @Override // com.bytedance.sdk.adnet.game.ok3.okhttp3.Authenticator
        public Request authenticate(Route route, Response response) {
            return null;
        }
    };

    Request authenticate(Route route, Response response);
}
