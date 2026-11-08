package com.sigmob.sdk.common.utils;

import com.sigmob.logger.SigmobLog;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.zip.GZIPInputStream;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public final class e {
    public static List<File> a(File file, File file2) throws IOException {
        GZIPInputStream gZIPInputStream;
        SigmobLog.i(String.format("Untaring %s to dir %s.", file.getAbsolutePath(), file2.getAbsolutePath()));
        LinkedList linkedList = new LinkedList();
        FileInputStream fileInputStream = new FileInputStream(file);
        try {
            gZIPInputStream = new GZIPInputStream(fileInputStream);
        } catch (Throwable th) {
            fileInputStream.close();
            fileInputStream = new FileInputStream(file);
            SigmobLog.e(th.getMessage());
            gZIPInputStream = null;
        }
        com.sigmob.a.a.b bVar = (com.sigmob.a.a.b) (gZIPInputStream != null ? new com.sigmob.a.d().a("tar", gZIPInputStream) : new com.sigmob.a.d().a("tar", fileInputStream));
        if (!file2.exists()) {
            file2.mkdirs();
        }
        while (true) {
            com.sigmob.a.a.a aVar = (com.sigmob.a.a.a) bVar.c();
            if (aVar == null) {
                bVar.close();
                if (gZIPInputStream != null) {
                    gZIPInputStream.close();
                }
                fileInputStream.close();
                return linkedList;
            }
            File file3 = new File(file2, aVar.a());
            if (aVar.g()) {
                SigmobLog.i(String.format("Attempting to write output directory %s.", file3.getAbsolutePath()));
                if (file3.exists()) {
                    continue;
                } else {
                    SigmobLog.i(String.format("Attempting to create output directory %s.", file3.getAbsolutePath()));
                    if (!file3.mkdirs()) {
                        throw new IllegalStateException(String.format("Couldn't create directory %s.", file3.getAbsolutePath()));
                    }
                }
            } else {
                SigmobLog.i(String.format("Creating output file %s.", file3.getAbsolutePath()));
                if (!file3.getParentFile().exists()) {
                    file3.getParentFile().mkdirs();
                }
                FileOutputStream fileOutputStream = new FileOutputStream(file3);
                com.sigmob.a.b.a.a(bVar, fileOutputStream);
                fileOutputStream.close();
            }
            linkedList.add(file3);
        }
    }
}
