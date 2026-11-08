package com.bytedance.pangle.d;

import com.bytedance.pangle.helper.e;
import com.bytedance.pangle.log.ZeusLogger;
import com.bytedance.pangle.util.f;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import org.json.JSONException;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public final class a {
    public static boolean a(String[] strArr) throws JSONException, IOException {
        if (strArr.length <= 0) {
            return false;
        }
        try {
            Process processExec = Runtime.getRuntime().exec(strArr);
            InputStream errorStream = processExec.getErrorStream();
            InputStream inputStream = processExec.getInputStream();
            a(errorStream);
            a(inputStream);
            if (processExec.waitFor() == 0) {
                return true;
            }
            ZeusLogger.e(ZeusLogger.TAG_INSTALL, "exec dex2oat failed : " + strArr.toString());
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } catch (InterruptedException e2) {
            e2.printStackTrace();
            return false;
        }
    }

    private static void a(final InputStream inputStream) {
        e.f4645a.execute(new Runnable() { // from class: com.bytedance.pangle.d.a.1
            @Override // java.lang.Runnable
            public final void run() throws IOException {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                while (true) {
                    try {
                        String line = bufferedReader.readLine();
                        if (line == null) {
                            return;
                        } else {
                            ZeusLogger.d(ZeusLogger.TAG_INSTALL, "exec cmd info : ".concat(String.valueOf(line)));
                        }
                    } catch (IOException e) {
                        ZeusLogger.e(ZeusLogger.TAG_INSTALL, "execCmd consumeInputStream failed : ".concat(String.valueOf(e)));
                        return;
                    } finally {
                        f.a(bufferedReader);
                    }
                }
            }
        });
    }
}
