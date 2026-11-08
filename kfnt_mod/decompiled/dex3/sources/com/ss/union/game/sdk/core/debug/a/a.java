package com.ss.union.game.sdk.core.debug.a;

import android.app.Activity;
import com.ss.union.game.sdk.common.util.ActivityUtils;
import com.ss.union.game.sdk.common.util.MainThreadExecutor;
import com.ss.union.game.sdk.core.base.dialog.fragment.StandardFragmentDialog;
import com.ss.union.game.sdk.core.base.dialog.model.StandardDialogParams;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class a {

    /* renamed from: com.ss.union.game.sdk.core.debug.a.a$a, reason: collision with other inner class name */
    private interface InterfaceC0075a {
        void a(Activity activity);
    }

    public static void a(com.ss.union.game.sdk.core.base.dialog.callback.a aVar) {
        b(aVar);
    }

    private static void b(final com.ss.union.game.sdk.core.base.dialog.callback.a aVar) {
        b(new InterfaceC0075a() { // from class: com.ss.union.game.sdk.core.debug.a.a.1
            @Override // com.ss.union.game.sdk.core.debug.a.a.InterfaceC0075a
            public void a(Activity activity) {
                StandardDialogParams standardDialogParams = new StandardDialogParams();
                standardDialogParams.setTitle("沙盒环境").setDescription("当前为沙盒环境，上线前请切换环境").setEnhanceBtnText("知道了");
                StandardFragmentDialog.show(standardDialogParams, aVar);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void b(final InterfaceC0075a interfaceC0075a) {
        Activity topActivity = ActivityUtils.getTopActivity();
        if (topActivity == null || topActivity.getComponentName().getClassName().contains("com.ss.union.game.sdk.core.splashEffect")) {
            MainThreadExecutor.postDelayed(new Runnable() { // from class: com.ss.union.game.sdk.core.debug.a.a.2
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        a.b(interfaceC0075a);
                    } catch (Exception unused) {
                    }
                }
            }, 1000L);
        } else {
            interfaceC0075a.a(topActivity);
        }
    }
}
