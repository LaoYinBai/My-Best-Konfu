package com.google.android.gms.common.util;

import android.os.Process;
import android.os.StrictMode;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Preconditions;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import javax.annotation.Nullable;

/* compiled from: com.google.android.gms:play-services-basement@@18.1.0 */
@KeepForSdk
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class ProcessUtils {

    @Nullable
    private static String zza;
    private static int zzb;

    private ProcessUtils() {
    }

    @androidx.annotation.Nullable
    @KeepForSdk
    public static String getMyProcessName() throws Throwable {
        BufferedReader bufferedReader;
        if (zza == null) {
            int iMyPid = zzb;
            if (iMyPid == 0) {
                iMyPid = Process.myPid();
                zzb = iMyPid;
            }
            String str = null;
            str = null;
            BufferedReader bufferedReader2 = null;
            if (iMyPid > 0) {
                try {
                    String str2 = "/proc/" + iMyPid + "/cmdline";
                    StrictMode.ThreadPolicy threadPolicyAllowThreadDiskReads = StrictMode.allowThreadDiskReads();
                    try {
                        bufferedReader = new BufferedReader(new FileReader(str2));
                    } finally {
                        StrictMode.setThreadPolicy(threadPolicyAllowThreadDiskReads);
                    }
                } catch (IOException unused) {
                    bufferedReader = null;
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    String line = bufferedReader.readLine();
                    Preconditions.checkNotNull(line);
                    String strTrim = line.trim();
                    IOUtils.closeQuietly(bufferedReader);
                    str = strTrim;
                } catch (IOException unused2) {
                    IOUtils.closeQuietly(bufferedReader);
                    zza = str;
                    return zza;
                } catch (Throwable th2) {
                    th = th2;
                    bufferedReader2 = bufferedReader;
                    IOUtils.closeQuietly(bufferedReader2);
                    throw th;
                }
            }
            zza = str;
        }
        return zza;
    }
}
