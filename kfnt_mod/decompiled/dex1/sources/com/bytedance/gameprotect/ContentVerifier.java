package com.bytedance.gameprotect;

import android.content.Context;
import androidx.annotation.Keep;
import com.bytedance.mobile.cpsdk.CPConfig;
import com.bytedance.mobile.cpsdk.CPManager;

@Keep
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class ContentVerifier {
    private String applicationID;
    private final String channelID;
    private Context context;
    private boolean cpsdkNeeded;
    private String deviceId;
    private boolean inited;
    private String sessionID;

    @Keep
    public class ContentTypeTransformer {
        public static final int AUDIO = 3;
        public static final int IMAGE = 1;
        public static final int VIDEO = 2;

        public ContentTypeTransformer() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public CPConfig.ContentType transform(int i) {
            if (i == 1) {
                return CPConfig.ContentType.kCPContentTypeImage;
            }
            if (i == 2) {
                return CPConfig.ContentType.kCPContentTypeVideo;
            }
            if (i == 3) {
                return CPConfig.ContentType.kCPContentTypeAudio;
            }
            throw new IllegalArgumentException("invalid content type");
        }
    }

    @Keep
    public class ContentVerifyResult {
        public static final int NOT_INITED = -1;
        public static final int PASS = 1;
        public static final int REJECT = 2;
        public static final int UNKOWN = 3;

        public ContentVerifyResult() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public int transform(CPConfig.CPVerrificationResult cPVerrificationResult) {
            if (cPVerrificationResult == CPConfig.CPVerrificationResult.CPVerificationResultPass) {
                return 1;
            }
            return cPVerrificationResult == CPConfig.CPVerrificationResult.CPVerificationResultReject ? 2 : 3;
        }
    }

    private static class b {
        static boolean a() {
            try {
                Class.forName(CPConfig.class.getName());
                return true;
            } catch (Throwable unused) {
                return false;
            }
        }
    }

    private static class c {

        /* renamed from: a, reason: collision with root package name */
        private static final ContentVerifier f3861a = new ContentVerifier();
    }

    private ContentVerifier() {
        this.deviceId = "3333";
        this.sessionID = "1111";
        this.channelID = "1002";
        this.context = null;
        this.applicationID = "2333";
        this.inited = false;
        this.cpsdkNeeded = b.a();
    }

    public static ContentVerifier getInstance() {
        return c.f3861a;
    }

    public synchronized boolean init() {
        if (!this.cpsdkNeeded) {
            return false;
        }
        if (this.context == null) {
            return false;
        }
        CPManager.init(new CPConfig.Builder().setAppID(this.applicationID).setChannel("1002").setDeviceID(this.deviceId).setSecssionID(this.sessionID).addScene(CPConfig.CPScenes.kCPSceneTypeGeneralPublicChat).addScene(CPConfig.CPScenes.kCPSceneTypeRoleName).build(), this.context);
        this.inited = true;
        return true;
    }

    public synchronized void initContentVerifier(Context context, String str) {
        this.context = context;
        this.applicationID = str;
    }

    public synchronized void setDeviceID(String str) {
        this.deviceId = str;
    }

    public synchronized int verifyChatContent(String str) {
        if (!this.inited) {
            return -1;
        }
        return new ContentVerifyResult().transform(CPManager.get().verifyContent(str, CPConfig.ContentType.kCPContentTypeText, CPConfig.CPScenes.kCPSceneTypeRoleName));
    }

    public synchronized int verifyMedia(String str, int i) {
        if (!this.inited) {
            return -1;
        }
        return new ContentVerifyResult().transform(CPManager.get().verifyContent(str, new ContentTypeTransformer().transform(i), CPConfig.CPScenes.kCPSceneTypeGeneralPublicChat));
    }
}
