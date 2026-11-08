package com.qq.e.comm.managers.plugin;

import android.content.Context;
import com.qq.e.comm.constants.Constants;
import com.qq.e.comm.net.NetworkCallBack;
import com.qq.e.comm.net.NetworkClient;
import com.qq.e.comm.net.NetworkClientImpl;
import com.qq.e.comm.net.rr.PlainRequest;
import com.qq.e.comm.net.rr.Request;
import com.qq.e.comm.net.rr.Response;
import com.qq.e.comm.util.FileUtil;
import com.qq.e.comm.util.GDTLogger;
import com.qq.e.comm.util.Md5Util;
import com.qq.e.comm.util.StringUtil;
import com.sigmob.sdk.common.Constants;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.Executor;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class c {
    private static final Pattern d = Pattern.compile(".*plugin\\.dex-(\\d+)\\.jar.*");
    private final Context a;
    private f b;
    private Executor c;

    private class a implements NetworkCallBack {
        private final String a;
        private final int b;

        public a(String str, int i) {
            this.a = str;
            this.b = i;
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Not initialized variable reg: 2, insn: 0x005c: MOVE (r0 I:??[OBJECT, ARRAY]) = (r2 I:??[OBJECT, ARRAY]), block:B:38:0x005c */
        /* JADX WARN: Type inference failed for: r6v0, types: [com.qq.e.comm.net.rr.Response] */
        /* JADX WARN: Type inference failed for: r6v1 */
        /* JADX WARN: Type inference failed for: r6v11 */
        /* JADX WARN: Type inference failed for: r6v12, types: [java.io.InputStream] */
        /* JADX WARN: Type inference failed for: r6v3 */
        /* JADX WARN: Type inference failed for: r6v5 */
        /* JADX WARN: Type inference failed for: r6v6, types: [java.io.InputStream] */
        /* JADX WARN: Type inference failed for: r6v7 */
        /* JADX WARN: Type inference failed for: r6v8 */
        /* JADX WARN: Type inference failed for: r6v9 */
        private String a(Response response, File file) throws Throwable {
            Throwable e;
            OutputStream outputStream;
            FileOutputStream fileOutputStream;
            String str;
            InputStream inputStream;
            MessageDigest messageDigest;
            OutputStream outputStream2 = null;
            try {
                try {
                    messageDigest = MessageDigest.getInstance("MD5");
                    response = response.getStreamContent();
                } catch (Throwable th) {
                    th = th;
                    outputStream2 = outputStream;
                }
            } catch (IOException e2) {
                e = e2;
                response = 0;
            } catch (NoSuchAlgorithmException e3) {
                e = e3;
                response = 0;
            } catch (Throwable th2) {
                th = th2;
                response = 0;
            }
            try {
                fileOutputStream = new FileOutputStream(file);
                try {
                    byte[] bArr = new byte[com.ss.union.game.sdk.feedback.picture.select.d.d];
                    while (true) {
                        int i = response.read(bArr);
                        if (i <= 0) {
                            FileUtil.tryClose((InputStream) response);
                            FileUtil.tryClose(fileOutputStream);
                            String strByteArrayToHexString = Md5Util.byteArrayToHexString(messageDigest.digest());
                            FileUtil.tryClose((InputStream) response);
                            FileUtil.tryClose(fileOutputStream);
                            return strByteArrayToHexString;
                        }
                        messageDigest.update(bArr, 0, i);
                        fileOutputStream.write(bArr, 0, i);
                    }
                } catch (IOException e4) {
                    e = e4;
                    str = "更新插件出现异常";
                    inputStream = response;
                    GDTLogger.e(str, e);
                    FileUtil.tryClose(inputStream);
                    FileUtil.tryClose(fileOutputStream);
                    return null;
                } catch (NoSuchAlgorithmException e5) {
                    e = e5;
                    str = "更新插件出现异常";
                    inputStream = response;
                    GDTLogger.e(str, e);
                    FileUtil.tryClose(inputStream);
                    FileUtil.tryClose(fileOutputStream);
                    return null;
                }
            } catch (IOException e6) {
                e = e6;
                fileOutputStream = null;
                str = "更新插件出现异常";
                inputStream = response;
                GDTLogger.e(str, e);
                FileUtil.tryClose(inputStream);
                FileUtil.tryClose(fileOutputStream);
                return null;
            } catch (NoSuchAlgorithmException e7) {
                e = e7;
                fileOutputStream = null;
                str = "更新插件出现异常";
                inputStream = response;
                GDTLogger.e(str, e);
                FileUtil.tryClose(inputStream);
                FileUtil.tryClose(fileOutputStream);
                return null;
            } catch (Throwable th3) {
                th = th3;
                FileUtil.tryClose((InputStream) response);
                FileUtil.tryClose(outputStream2);
                throw th;
            }
        }

        private void a(String str) {
            if (c.this.b != null) {
                PM.this.a();
                com.qq.e.comm.managers.plugin.a.a(new Exception(str), str);
            }
        }

        @Override // com.qq.e.comm.net.NetworkCallBack
        public void onException(Exception exc) {
            GDTLogger.w("更新插件出现异常", exc);
            a(exc.getMessage());
        }

        /* JADX WARN: Removed duplicated region for block: B:19:0x0079  */
        /* JADX WARN: Removed duplicated region for block: B:22:0x0096 A[Catch: all -> 0x00d0, TryCatch #1 {all -> 0x00d0, blocks: (B:4:0x0008, B:6:0x002e, B:7:0x0046, B:11:0x0053, B:13:0x0063, B:20:0x007a, B:22:0x0096, B:9:0x004b, B:23:0x00a4, B:25:0x00aa, B:27:0x00bb), top: B:40:0x0008, inners: #2 }] */
        @Override // com.qq.e.comm.net.NetworkCallBack
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public void onResponse(com.qq.e.comm.net.rr.Request r6, com.qq.e.comm.net.rr.Response r7) {
            /*
                Method dump skipped, instructions count: 341
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.qq.e.comm.managers.plugin.c.a.onResponse(com.qq.e.comm.net.rr.Request, com.qq.e.comm.net.rr.Response):void");
        }
    }

    public c(Context context, Executor executor) {
        this.a = context.getApplicationContext();
        this.c = executor;
    }

    public void a(f fVar) {
        this.b = fVar;
    }

    public void a(String str, String str2) {
        if (StringUtil.isEmpty(str) || StringUtil.isEmpty(str2)) {
            return;
        }
        String strGroup = Constants.FAIL;
        Matcher matcher = d.matcher(str2);
        if (matcher.matches()) {
            strGroup = matcher.group(1);
        }
        boolean z = false;
        int integer = StringUtil.parseInteger(strGroup, 0);
        if (integer < 1300) {
            GDTLogger.i("online plugin version is smaller than asset plugin version" + integer + com.xiaomi.mipush.sdk.Constants.ACCEPT_TIME_SEPARATOR_SP + Constants.PLUGIN.ASSET_PLUGIN_VERSION + ".download give up");
        } else {
            z = true;
        }
        if (z) {
            GDTLogger.d("TIMESTAP_BEFORE_OWN_PLUGIN:" + System.nanoTime());
            NetworkClientImpl.getInstance().submit(new PlainRequest(str2, Request.Method.GET, (byte[]) null), NetworkClient.Priority.High, new a(str, integer), this.c);
        }
    }
}
