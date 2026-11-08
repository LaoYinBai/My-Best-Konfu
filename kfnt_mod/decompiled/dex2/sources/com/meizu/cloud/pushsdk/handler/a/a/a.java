package com.meizu.cloud.pushsdk.handler.a.a;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Environment;
import com.mbridge.msdk.playercommon.exoplayer2.C;
import com.meizu.cloud.pushinternal.DebugLogger;
import com.meizu.cloud.pushsdk.constants.PushConstants;
import com.meizu.cloud.pushsdk.handler.a.b.g;
import com.meizu.cloud.pushsdk.notification.c;
import com.meizu.cloud.pushsdk.util.d;
import java.io.File;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class a extends com.meizu.cloud.pushsdk.handler.a.a<g> {
    public a(Context context, com.meizu.cloud.pushsdk.handler.a aVar) {
        super(context, aVar);
    }

    @Override // com.meizu.cloud.pushsdk.handler.c
    public int a() {
        return C.DEFAULT_BUFFER_SEGMENT_SIZE;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.meizu.cloud.pushsdk.handler.a.a
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public void b(g gVar) {
        d.b(d(), d().getPackageName(), gVar.d().b().d(), gVar.d().b().a(), gVar.d().b().e(), gVar.d().b().b());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.meizu.cloud.pushsdk.handler.a.a
    public void a(g gVar, c cVar) {
        StringBuilder sb;
        String str;
        String message;
        File file;
        DebugLogger.flush();
        String strA = gVar.d().b().a();
        String strD = gVar.d().b().d();
        if (Build.VERSION.SDK_INT >= 29) {
            sb = new StringBuilder();
            sb.append(d().getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS).getPath());
            str = "/pushSdktmp/";
        } else {
            sb = new StringBuilder();
            sb.append(Environment.getExternalStorageDirectory().getAbsolutePath());
            str = "/Android/data/pushSdktmp/";
        }
        sb.append(str);
        sb.append(strA);
        sb.append("_");
        sb.append(strD);
        sb.append(".zip");
        String string = sb.toString();
        try {
            new b(string).a(gVar.c());
            file = new File(string);
            message = null;
        } catch (Exception e) {
            message = e.getMessage();
            DebugLogger.e("AbstractMessageHandler", "zip error message " + message);
            file = null;
        }
        if (file != null && file.length() / 1024 > gVar.a()) {
            message = "the upload file exceeds the max size";
        } else if (gVar.b() && !com.meizu.cloud.pushsdk.util.a.b(d())) {
            message = "current network not allowed upload log file";
        }
        com.meizu.cloud.pushsdk.b.a.c<String> cVarA = com.meizu.cloud.pushsdk.platform.a.b.a(d()).a(gVar.d().b().a(), gVar.d().b().d(), message, file);
        if (cVarA != null && cVarA.b()) {
            if (file != null) {
                file.delete();
            }
            DebugLogger.e("AbstractMessageHandler", "upload success " + cVarA.a());
            return;
        }
        if (cVarA == null) {
            DebugLogger.i("AbstractMessageHandler", "upload error");
            return;
        }
        DebugLogger.i("AbstractMessageHandler", "upload error code " + cVarA.c() + cVarA.a());
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0027  */
    @Override // com.meizu.cloud.pushsdk.handler.c
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean a(android.content.Intent r4) {
        /*
            r3 = this;
            java.lang.String r0 = "AbstractMessageHandler"
            java.lang.String r1 = "start LogUploadMessageHandler match"
            com.meizu.cloud.pushinternal.DebugLogger.i(r0, r1)
            java.lang.String r0 = "mz_push_control_message"
            java.lang.String r0 = r4.getStringExtra(r0)
            boolean r1 = android.text.TextUtils.isEmpty(r0)
            r2 = 0
            if (r1 != 0) goto L27
            com.meizu.cloud.pushsdk.handler.a.b.b r0 = com.meizu.cloud.pushsdk.handler.a.b.b.a(r0)
            com.meizu.cloud.pushsdk.handler.a.b.a r1 = r0.a()
            if (r1 == 0) goto L27
            com.meizu.cloud.pushsdk.handler.a.b.a r0 = r0.a()
            int r0 = r0.a()
            goto L28
        L27:
            r0 = 0
        L28:
            java.lang.String r1 = "com.meizu.flyme.push.intent.MESSAGE"
            java.lang.String r4 = r4.getAction()
            boolean r4 = r1.equals(r4)
            if (r4 == 0) goto L41
            java.lang.String r4 = "2"
            java.lang.String r0 = java.lang.String.valueOf(r0)
            boolean r4 = r4.equals(r0)
            if (r4 == 0) goto L41
            r2 = 1
        L41:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.meizu.cloud.pushsdk.handler.a.a.a.a(android.content.Intent):boolean");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.meizu.cloud.pushsdk.handler.a.a
    /* renamed from: l, reason: merged with bridge method [inline-methods] */
    public g c(Intent intent) {
        String stringExtra = intent.getStringExtra(PushConstants.MZ_PUSH_CONTROL_MESSAGE);
        String stringExtra2 = intent.getStringExtra(PushConstants.EXTRA_APP_PUSH_SEQ_ID);
        return new g(intent.getStringExtra(PushConstants.MZ_PUSH_PRIVATE_MESSAGE), stringExtra, intent.getStringExtra(PushConstants.MZ_PUSH_MESSAGE_STATISTICS_IMEI_KEY), stringExtra2);
    }
}
