package com.qq.e.comm.managers.plugin;

import com.qq.e.comm.util.FileUtil;
import com.qq.e.comm.util.GDTLogger;
import com.qq.e.comm.util.Md5Util;
import com.qq.e.comm.util.StringUtil;
import java.io.File;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
class g {
    private final File a;
    private final File b;
    private String c;
    private int d;

    public g(File file, File file2) {
        this.a = file;
        this.b = file2;
    }

    boolean a() {
        try {
            if (this.b.exists() && this.a.exists()) {
                String[] strArrSplit = StringUtil.readAll(this.b).split("#####");
                if (strArrSplit.length == 2) {
                    String str = strArrSplit[1];
                    int integer = StringUtil.parseInteger(strArrSplit[0], 0);
                    com.qq.e.comm.util.a aVarA = com.qq.e.comm.util.a.a();
                    File file = this.a;
                    if (aVarA == null) {
                        throw null;
                    }
                    if (!((file == null || !file.exists()) ? false : aVarA.a(str, Md5Util.encode(file)))) {
                        return false;
                    }
                    this.c = str;
                    this.d = integer;
                    return true;
                }
            }
            return false;
        } catch (Throwable unused) {
            GDTLogger.d("Exception while checking plugin");
            return false;
        }
    }

    boolean a(File file, File file2) {
        return (file.equals(this.a) || FileUtil.renameTo(this.a, file)) && (file2.equals(this.b) || FileUtil.renameTo(this.b, file2));
    }

    String b() {
        return this.c;
    }

    int c() {
        return this.d;
    }
}
