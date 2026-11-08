package com.ss.union.game.sdk.vcenter.account.c;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Process;
import android.os.RemoteException;
import android.text.TextUtils;
import com.ss.union.game.sdk.common.util.GlobalApplicationUtils;
import com.ss.union.game.sdk.core.base.account.model.User;
import com.ss.union.game.sdk.core.vapp.VLog;
import com.ss.union.game.sdk.vcenter.account.callback.d;
import com.ss.union.game.sdk.vcenter.g;
import com.ss.union.game.sdk.vcenter.k;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public final class b extends k.a {
    private com.ss.union.game.sdk.vcenter.account.a.a g;

    public b(com.ss.union.game.sdk.vcenter.account.a.a aVar) {
        this.g = aVar;
    }

    @Override // com.ss.union.game.sdk.vcenter.k
    public void a(String str, final g gVar) throws RemoteException {
        VLog.Account.d("通知sdk，onAuthorizationSuccess: result" + str);
        this.g.a(com.ss.union.game.sdk.vcenter.b.b.a(str), 1, new com.ss.union.game.sdk.vcenter.account.callback.c() { // from class: com.ss.union.game.sdk.vcenter.account.c.b.1
            @Override // com.ss.union.game.sdk.vcenter.account.callback.c
            public void a(int i, User user) {
                try {
                    gVar.a(true, 0, "");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override // com.ss.union.game.sdk.vcenter.account.callback.c
            public void a(int i, int i2, String str2, String str3) {
                try {
                    gVar.a(false, i2, str2);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override // com.ss.union.game.sdk.vcenter.k
    public void a(int i, String str, String str2) throws RemoteException {
        VLog.Account.d("通知sdk，onAuthorizationFail: code" + i + ",message: " + str + ",ext:" + str2);
        this.g.a(1, i, str, str2);
    }

    @Override // com.ss.union.game.sdk.vcenter.k
    public void a(g gVar) throws RemoteException {
        VLog.Account.d("通知sdk，onGuestNormalLogin");
        if (this.g instanceof d) {
            ((d) this.g).a(gVar);
        }
    }

    @Override // com.ss.union.game.sdk.vcenter.k
    public void b(g gVar) throws RemoteException {
        VLog.Account.d("通知sdk，onContinueAutoLogin");
        if (this.g instanceof d) {
            ((d) this.g).b(gVar);
        }
    }

    @Override // com.ss.union.game.sdk.vcenter.k
    public void a() throws RemoteException {
        VLog.Account.d("通知sdk,账号和摸摸鱼的账号不一致，用户强制退出游戏");
        a(GlobalApplicationUtils.getContext());
    }

    private void a(Context context) {
        try {
            int i = 0;
            for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : ((ActivityManager) context.getSystemService("activity")).getRunningAppProcesses()) {
                String str = runningAppProcessInfo.processName;
                if (!TextUtils.isEmpty(str) && str.startsWith(context.getPackageName())) {
                    i++;
                    if (i >= 5) {
                        break;
                    } else if (runningAppProcessInfo.pid != Process.myPid()) {
                        Process.killProcess(runningAppProcessInfo.pid);
                    }
                }
            }
        } catch (Throwable th) {
            Process.killProcess(Process.myPid());
            throw th;
        }
        Process.killProcess(Process.myPid());
    }

    @Override // com.ss.union.game.sdk.vcenter.k
    public void b(String str, final g gVar) throws RemoteException {
        VLog.Account.d("通知sdk，做授权绑定，绑定结果需要ipc回给摸摸鱼: " + str);
        new a().a(com.ss.union.game.sdk.vcenter.b.b.a(str), 2, new com.ss.union.game.sdk.vcenter.account.callback.c() { // from class: com.ss.union.game.sdk.vcenter.account.c.b.2
            @Override // com.ss.union.game.sdk.vcenter.account.callback.c
            public void a(int i, User user) {
                try {
                    gVar.a(true, 0, "");
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                b.this.g.a(1, user);
            }

            @Override // com.ss.union.game.sdk.vcenter.account.callback.c
            public void a(int i, int i2, String str2, String str3) {
                try {
                    gVar.a(false, i2, str2);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                if (i2 != 20002) {
                    b.this.g.a(1, i2, str2, str3);
                }
            }
        });
    }

    private static class a extends com.ss.union.game.sdk.vcenter.account.a.a {
        @Override // com.ss.union.game.sdk.vcenter.account.callback.c
        public void a(int i, int i2, String str, String str2) {
        }

        @Override // com.ss.union.game.sdk.vcenter.account.callback.c
        public void a(int i, User user) {
        }

        private a() {
        }
    }
}
