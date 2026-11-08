package com.xiaomi.push;

import android.content.Context;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileLock;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public final class u {
    private static final Set<String> a = Collections.synchronizedSet(new HashSet());

    /* renamed from: a, reason: collision with other field name */
    private Context f984a;

    /* renamed from: a, reason: collision with other field name */
    private RandomAccessFile f985a;

    /* renamed from: a, reason: collision with other field name */
    private String f986a;

    /* renamed from: a, reason: collision with other field name */
    private FileLock f987a;

    private u(Context context) {
        this.f984a = context;
    }

    public static u a(Context context, File file) throws IOException {
        com.xiaomi.channel.commonutils.logger.b.c("Locking: " + file.getAbsolutePath());
        String str = file.getAbsolutePath() + ".LOCK";
        File file2 = new File(str);
        if (!file2.exists()) {
            file2.getParentFile().mkdirs();
            file2.createNewFile();
        }
        if (!a.add(str)) {
            throw new IOException("abtain lock failure");
        }
        u uVar = new u(context);
        uVar.f986a = str;
        try {
            uVar.f985a = new RandomAccessFile(file2, "rw");
            uVar.f987a = uVar.f985a.getChannel().lock();
            com.xiaomi.channel.commonutils.logger.b.c("Locked: " + str + " :" + uVar.f987a);
            return uVar;
        } finally {
            if (uVar.f987a == null) {
                if (uVar.f985a != null) {
                    y.a(uVar.f985a);
                }
                a.remove(uVar.f986a);
            }
        }
    }

    public void a() throws IOException {
        com.xiaomi.channel.commonutils.logger.b.c("unLock: " + this.f987a);
        if (this.f987a != null && this.f987a.isValid()) {
            try {
                this.f987a.release();
            } catch (IOException unused) {
            }
            this.f987a = null;
        }
        if (this.f985a != null) {
            y.a(this.f985a);
        }
        a.remove(this.f986a);
    }
}
