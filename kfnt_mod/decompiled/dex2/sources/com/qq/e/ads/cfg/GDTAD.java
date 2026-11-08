package com.qq.e.ads.cfg;

import android.content.Context;
import com.qq.e.comm.managers.GDTADManager;
import com.qq.e.comm.managers.plugin.e;
import com.qq.e.comm.util.GDTLogger;
import com.qq.e.comm.util.StringUtil;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class GDTAD {
    private static boolean a = false;
    private static InitListener b;
    private static e c = new e() { // from class: com.qq.e.ads.cfg.GDTAD.1
        public void onLoadFail() {
            GDTLogger.w("插件加载失败");
        }

        public void onLoadSuccess() {
            boolean unused = GDTAD.a = true;
            if (GDTAD.b != null) {
                GDTAD.b.onSuccess();
            }
        }
    };

    public interface InitListener {
        void onSuccess();
    }

    public static void initSDK(Context context, String str, InitListener initListener) {
        if (a) {
            GDTLogger.w("SDK已经被初始化过", (Throwable) null);
            return;
        }
        if (context == null) {
            GDTLogger.e("Context参数不能为null");
            return;
        }
        if (StringUtil.isEmpty(str)) {
            GDTLogger.e("AppId参数不能为空");
            return;
        }
        b = initListener;
        GDTADManager gDTADManager = GDTADManager.getInstance();
        gDTADManager.setPluginLoadListener(c);
        gDTADManager.initWith(context.getApplicationContext(), str);
    }
}
