package com.ss.union.game.sdk.common.util.logger;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.ss.union.game.sdk.common.util.DateUtils;
import com.ss.union.game.sdk.common.util.ProcessUtils;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
class b implements f {
    private final Handler a;

    public b(Handler handler) {
        this.a = handler;
    }

    @Override // com.ss.union.game.sdk.common.util.logger.f
    public void a(int i, String str, String str2) {
        if (str2 == null || str2.isEmpty()) {
            return;
        }
        this.a.sendMessage(this.a.obtainMessage(i, str2));
    }

    static class a extends Handler {
        private final String a;
        private final int b;

        a(Looper looper, String str, int i) {
            super(looper);
            this.a = str;
            this.b = i;
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            File fileA;
            FileWriter fileWriter;
            if (message == null || !(message.obj instanceof String)) {
                return;
            }
            String str = (String) message.obj;
            if (j.a((CharSequence) str) || (fileA = a(this.a, DateUtils.format2(System.currentTimeMillis()))) == null) {
                return;
            }
            try {
                fileWriter = new FileWriter(fileA, true);
                try {
                    a(fileWriter, str);
                    fileWriter.flush();
                    fileWriter.close();
                } catch (Throwable unused) {
                    if (fileWriter != null) {
                        try {
                            fileWriter.flush();
                            fileWriter.close();
                        } catch (Throwable unused2) {
                        }
                    }
                }
            } catch (Throwable unused3) {
                fileWriter = null;
            }
        }

        private void a(FileWriter fileWriter, String str) throws IOException {
            if (fileWriter == null || str == null || str.isEmpty()) {
                return;
            }
            fileWriter.append((CharSequence) str);
        }

        private File a(String str, String str2) {
            File file = new File(str);
            if (!file.exists()) {
                file.mkdirs();
            }
            String currentProcessName = ProcessUtils.getCurrentProcessName();
            File file2 = null;
            File file3 = new File(file, String.format("%s_%s_%s.l", currentProcessName, str2, 0));
            int i = 0;
            while (file3.exists()) {
                i++;
                file2 = file3;
                file3 = new File(file, String.format("%s_%s_%s.l", currentProcessName, str2, Integer.valueOf(i)));
            }
            return (file2 == null || file2.length() >= ((long) this.b)) ? file3 : file2;
        }
    }
}
