package com.taobao.accs;

import androidx.annotation.Keep;
import com.taobao.aranger.annotation.type.Callback;
import java.util.Map;

@Callback
@Keep
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public interface IAppReceiver {
    Map<String, String> getAllServices();

    String getService(String str);

    void onBindApp(int i);

    void onBindUser(String str, int i);

    void onData(String str, String str2, byte[] bArr);

    void onSendData(String str, int i);

    void onUnbindApp(int i);

    void onUnbindUser(int i);
}
