package com.ss.union.game.sdk.vcenter.account;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.ss.union.game.sdk.LGAccountManager;
import com.ss.union.game.sdk.common.util.ActivityUtils;
import com.ss.union.game.sdk.common.util.GlobalApplicationUtils;
import com.ss.union.game.sdk.core.base.config.ConfigManager;
import com.ss.union.game.sdk.core.vapp.VLog;
import com.ss.union.game.sdk.vcenter.account.callback.IGameCenterAccountChangeListener;
import java.util.ArrayList;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class b implements com.ss.union.game.sdk.vcenter.account.callback.a {
    private static b a = new b();
    private boolean b = false;
    private List<IGameCenterAccountChangeListener> c = new ArrayList();
    private IGameCenterAccountChangeListener d = new IGameCenterAccountChangeListener() { // from class: com.ss.union.game.sdk.vcenter.account.b.1
        @Override // com.ss.union.game.sdk.vcenter.account.callback.IGameCenterAccountChangeListener
        public void onAccountChange() {
            Activity topActivity = ActivityUtils.getTopActivity();
            if (topActivity != null) {
                LGAccountManager.getAccountService().loginNormal(topActivity);
            }
        }
    };

    @Override // com.ss.union.game.sdk.vcenter.account.callback.a
    public void b() {
    }

    public static b a() {
        return a;
    }

    @Override // com.ss.union.game.sdk.vcenter.account.callback.a
    public void a(IGameCenterAccountChangeListener iGameCenterAccountChangeListener) {
        this.d = iGameCenterAccountChangeListener;
    }

    @Override // com.ss.union.game.sdk.vcenter.account.callback.a
    public void b(IGameCenterAccountChangeListener iGameCenterAccountChangeListener) {
        this.c.add(iGameCenterAccountChangeListener);
    }

    @Override // com.ss.union.game.sdk.vcenter.account.callback.a
    public void c(IGameCenterAccountChangeListener iGameCenterAccountChangeListener) {
        this.c.remove(iGameCenterAccountChangeListener);
    }

    @Override // com.ss.union.game.sdk.vcenter.account.callback.a
    public void a(boolean z) {
        if (this.b) {
            VLog.Account.d("账号已经切换了,return");
            return;
        }
        if (!ConfigManager.LoginConfig.isDialogLogin()) {
            VLog.Account.d("游戏没有使用正式登录体系，return");
            return;
        }
        if (z) {
            VLog.Account.d("游戏点击切换/绑定操作，导致摸摸鱼账号改变了");
            VLog.Account.d("走正常的ipc回调即可，return");
        } else {
            this.b = true;
            final Application application = (Application) GlobalApplicationUtils.getContext();
            application.registerActivityLifecycleCallbacks(new Application.ActivityLifecycleCallbacks() { // from class: com.ss.union.game.sdk.vcenter.account.b.2
                @Override // android.app.Application.ActivityLifecycleCallbacks
                public void onActivityCreated(@NonNull Activity activity, @Nullable Bundle bundle) {
                }

                @Override // android.app.Application.ActivityLifecycleCallbacks
                public void onActivityDestroyed(@NonNull Activity activity) {
                }

                @Override // android.app.Application.ActivityLifecycleCallbacks
                public void onActivityPaused(@NonNull Activity activity) {
                }

                @Override // android.app.Application.ActivityLifecycleCallbacks
                public void onActivitySaveInstanceState(@NonNull Activity activity, @NonNull Bundle bundle) {
                }

                @Override // android.app.Application.ActivityLifecycleCallbacks
                public void onActivityStarted(@NonNull Activity activity) {
                }

                @Override // android.app.Application.ActivityLifecycleCallbacks
                public void onActivityStopped(@NonNull Activity activity) {
                }

                @Override // android.app.Application.ActivityLifecycleCallbacks
                public void onActivityResumed(@NonNull Activity activity) {
                    try {
                        application.unregisterActivityLifecycleCallbacks(this);
                        b.this.b = false;
                        b.this.d.onAccountChange();
                        for (IGameCenterAccountChangeListener iGameCenterAccountChangeListener : (IGameCenterAccountChangeListener[]) b.this.c.toArray(new IGameCenterAccountChangeListener[0])) {
                            iGameCenterAccountChangeListener.onAccountChange();
                        }
                    } catch (Throwable th) {
                        th.printStackTrace();
                    }
                }
            });
        }
    }
}
