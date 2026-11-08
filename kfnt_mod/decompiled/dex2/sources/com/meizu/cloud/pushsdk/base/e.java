package com.meizu.cloud.pushsdk.base;

import android.util.Log;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileFilter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
class e {
    private final SimpleDateFormat a = new SimpleDateFormat("yyyy-MM-dd");
    private final d b = new d("lo");
    private BufferedWriter c;

    class a implements Comparator<File> {
        a() {
        }

        @Override // java.util.Comparator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public int compare(File file, File file2) {
            long jLastModified = file.lastModified() - file2.lastModified();
            if (jLastModified > 0) {
                return -1;
            }
            return jLastModified == 0 ? 0 : 1;
        }
    }

    public void a() throws IOException {
        if (this.c != null) {
            this.c.flush();
            this.c.close();
            this.c = null;
        }
    }

    void a(File file) {
        File[] fileArrListFiles = file.listFiles(new FileFilter() { // from class: com.meizu.cloud.pushsdk.base.e.1
            @Override // java.io.FileFilter
            public boolean accept(File file2) {
                return file2.getName().endsWith(".log.txt");
            }
        });
        if (fileArrListFiles != null) {
            if (fileArrListFiles.length > 7) {
                Arrays.sort(fileArrListFiles, new a());
                for (int i = 7; i < fileArrListFiles.length; i++) {
                    fileArrListFiles[i].delete();
                }
            }
        }
    }

    public void a(String str) throws IOException {
        File file = new File(str);
        if (!file.exists() && !file.mkdirs()) {
            throw new IOException("create " + str + " dir failed!!!");
        }
        String str2 = this.a.format(new Date());
        File file2 = new File(str, str2 + ".log.txt");
        if (!file2.exists()) {
            if (file2.createNewFile()) {
                a(file);
            } else {
                Log.e("EncryptionWriter", "create new file " + str2 + " failed !!!");
            }
        }
        this.c = new BufferedWriter(new FileWriter(file2, true));
    }

    public void a(String str, String str2, String str3) throws IOException {
        if (this.c != null) {
            this.c.write(this.b.a((str + str2 + " " + str3).getBytes()));
            this.c.write("\r\n");
        }
    }
}
