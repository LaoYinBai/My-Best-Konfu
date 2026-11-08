package com.cmic.sso.sdk.d;

import android.text.TextUtils;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class m {
    public static boolean a() {
        try {
            for (String str : new String[]{"/system/xbin/", "/system/bin/", "/system/sbin/", "/sbin/", "/vendor/bin/", "/su/bin/"}) {
                String str2 = str + "su";
                if (new File(str2).exists()) {
                    String strA = a(new String[]{"ls", "-l", str2});
                    c.b("cyb", "isRooted=" + strA);
                    if (TextUtils.isEmpty(strA)) {
                        return false;
                    }
                    return strA.indexOf("root") != strA.lastIndexOf("root");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    private static String a(String[] strArr) throws IOException {
        StringBuilder sb = new StringBuilder();
        try {
            Process processStart = new ProcessBuilder(strArr).start();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(processStart.getInputStream()));
            while (true) {
                String line = bufferedReader.readLine();
                if (line == null) {
                    break;
                }
                sb.append(line);
            }
            processStart.getInputStream().close();
            processStart.destroy();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sb.toString();
    }
}
