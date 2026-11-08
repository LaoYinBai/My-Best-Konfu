package com.ss.union.game.sdk.customerSystem;

import android.app.Activity;
import androidx.annotation.Keep;
import com.ss.union.game.sdk.customerSystem.callback.FindUnReadMessageCountCallback;
import com.ss.union.game.sdk.customerSystem.callback.OpenCustomerSystemCallback;

@Keep
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public interface ICustomerSystemService {
    void findUnReadMessageCount(FindUnReadMessageCountCallback findUnReadMessageCountCallback);

    void openCustomerSystem(Activity activity, OpenCustomerSystemCallback openCustomerSystemCallback);

    void setPollingUnreadMessageIntervalSecond(long j);

    void startPollingUnreadMessageCount(FindUnReadMessageCountCallback findUnReadMessageCountCallback);

    void stopPolling();
}
