package com.vivo.push.ups;

import android.content.Context;
import android.os.Bundle;
import com.vivo.push.IPushActionListener;
import com.vivo.push.NoPorGuard;
import com.vivo.push.PushClient;

@NoPorGuard
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class VUpsManager {
    private static VUpsManager mVUpsManager;

    public void onCommandResult(Context context, Bundle bundle) {
    }

    public static VUpsManager getInstance() {
        if (mVUpsManager == null) {
            mVUpsManager = new VUpsManager();
        }
        return mVUpsManager;
    }

    public void registerToken(final Context context, String str, String str2, String str3, final UPSRegisterCallback uPSRegisterCallback) {
        PushClient.getInstance(context).turnOnPush(new IPushActionListener() { // from class: com.vivo.push.ups.VUpsManager.1
            @Override // com.vivo.push.IPushActionListener
            public final void onStateChanged(int i) {
                uPSRegisterCallback.onResult(new TokenResult(i, PushClient.getInstance(context).getRegId()));
            }
        });
    }

    public void unRegisterToken(Context context, final UPSRegisterCallback uPSRegisterCallback) {
        PushClient.getInstance(context).turnOffPush(new IPushActionListener() { // from class: com.vivo.push.ups.VUpsManager.2
            @Override // com.vivo.push.IPushActionListener
            public final void onStateChanged(int i) {
                uPSRegisterCallback.onResult(new TokenResult(i, ""));
            }
        });
    }

    public void turnOnPush(Context context, UPSTurnCallback uPSTurnCallback) {
        PushClient.getInstance(context).initialize();
        uPSTurnCallback.onResult(new CodeResult(0));
    }

    public void turnOffPush(Context context, final UPSTurnCallback uPSTurnCallback) {
        PushClient.getInstance(context).turnOffPush(new IPushActionListener() { // from class: com.vivo.push.ups.VUpsManager.3
            @Override // com.vivo.push.IPushActionListener
            public final void onStateChanged(int i) {
                uPSTurnCallback.onResult(new CodeResult(i));
            }
        });
    }
}
