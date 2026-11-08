package com.huawei.a.a.b.g;

import com.huawei.a.a.b.a.b;
import com.huawei.a.a.b.k.d;
import java.io.File;
import java.io.IOException;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class a {
    private String a = com.huawei.a.a.a.b.j().getFilesDir().getPath();

    a() {
    }

    private String a(String str) throws Throwable {
        File file = new File(b(str), "hianalytics_" + str);
        if (a(file)) {
            return d.a(file);
        }
        String strB = com.huawei.a.a.b.a.b.a().b(b.a.RSA);
        d.a(file, strB);
        return strB;
    }

    private void a(String str, String str2) throws Throwable {
        File file = new File(b(str));
        File file2 = new File(b(str), "hianalytics_" + str);
        if (!file.exists() && file.mkdirs()) {
            com.huawei.a.a.b.e.a.b("ComponentManager", "file directory is mkdirs");
        }
        if (a(file2)) {
            d.a(file2, str2);
        } else {
            com.huawei.a.a.b.e.a.c("ComponentManager", "refreshComponent():file is not found,and file is create failed");
        }
    }

    private boolean a(File file) {
        if (file.exists()) {
            return true;
        }
        try {
            return file.createNewFile();
        } catch (IOException unused) {
            com.huawei.a.a.b.e.a.c("ComponentManager", "create new file error!");
            return false;
        }
    }

    private char[] a(String str, String str2, String str3, String str4) {
        byte[] bArrB = com.huawei.a.a.b.a.b.a().b(str);
        byte[] bArrB2 = com.huawei.a.a.b.a.b.a().b(str2);
        byte[] bArrB3 = com.huawei.a.a.b.a.b.a().b(str3);
        byte[] bArrB4 = com.huawei.a.a.b.a.b.a().b(str4);
        int length = bArrB.length;
        if (length > bArrB2.length) {
            length = bArrB2.length;
        }
        if (length > bArrB3.length) {
            length = bArrB3.length;
        }
        if (length > bArrB4.length) {
            length = bArrB4.length;
        }
        char[] cArr = new char[length];
        for (int i = 0; i < length; i++) {
            cArr[i] = (char) (((bArrB[i] ^ bArrB2[i]) ^ bArrB3[i]) ^ bArrB4[i]);
        }
        return cArr;
    }

    private String b(String str) {
        return this.a + "/hms/component/".replace("component", str);
    }

    private boolean b() {
        long jB = com.huawei.a.a.b.i.a.b(com.huawei.a.a.a.b.j(), "Privacy_MY", "assemblyFlash", -1L);
        if (-1 != jB) {
            return System.currentTimeMillis() - jB > 31536000000L;
        }
        com.huawei.a.a.b.e.a.b("ComponentManager", "First init components");
        return true;
    }

    private String c() {
        return "f6040d0e807aaec325ecf44823765544e92905158169f694b282bf17388632cf95a83bae7d2d235c1f039b0df1dcca5fda619b6f7f459f2ff8d70ddb7b601592fe29fcae58c028f319b3b12495e67aa5390942a997a8cb572c8030b2df5c2b622608bea02b0c3e5d4dff3f72c9e3204049a45c0760cd3604af8d57f0e0c693cc";
    }

    public String a() throws Throwable {
        String strA;
        String strA2;
        String strA3;
        String strA4;
        String strC = c();
        if (b()) {
            com.huawei.a.a.b.e.a.b("ComponentManager", "refresh components");
            strA = com.huawei.a.a.b.a.b.a().b(b.a.RSA);
            a("aprpap", strA);
            strA2 = com.huawei.a.a.b.a.b.a().b(b.a.RSA);
            a("febdoc", strA2);
            strA3 = com.huawei.a.a.b.a.b.a().b(b.a.RSA);
            a("marfil", strA3);
            strA4 = com.huawei.a.a.b.a.b.a().b(b.a.RSA);
            a("maywnj", strA4);
            com.huawei.a.a.b.i.a.a(com.huawei.a.a.a.b.j(), "Privacy_MY", "assemblyFlash", System.currentTimeMillis());
        } else {
            strA = a("aprpap");
            strA2 = a("febdoc");
            strA3 = a("marfil");
            strA4 = a("maywnj");
        }
        return com.huawei.a.a.b.a.b.a().a(a(strA, strA2, strA3, strC), com.huawei.a.a.b.a.b.a().b(strA4));
    }
}
