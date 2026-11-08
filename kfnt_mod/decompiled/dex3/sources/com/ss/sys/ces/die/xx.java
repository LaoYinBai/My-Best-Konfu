package com.ss.sys.ces.die;

import android.content.Context;
import android.os.Looper;
import android.widget.Toast;
import androidx.annotation.Keep;
import com.ss.sys.ces.b;

@Keep
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class xx {
    public static final int DEBUG = 1;
    public static final int DEFAULT = 0;
    public static final int REPEATED_PACK = 2;
    private static Context mContext = b.e;

    static class a extends Thread {
        final /* synthetic */ String a;

        a(String str) {
            this.a = str;
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            Looper.prepare();
            Toast.makeText(xx.mContext, this.a, 1).show();
            Looper.loop();
        }
    }

    public static void HelloKitty(int i) {
        String str = "APP ERROR !!!";
        if (i == 1) {
            str = "[-] 检测到您设备环境不安全, 请勿进行异常操作";
        } else if (i == 2) {
            str = "[-] 检测到您当前安装的软件为非官方版本, 为了保证正常安全浏览请点击下载安装官方版本";
        }
        toast(str);
    }

    private static void toast(String str) {
        new a(str).start();
    }
}
