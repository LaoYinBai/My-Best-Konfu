package com.taobao.accs.init;

import android.content.Context;
import com.taobao.accs.IAppReceiver;
import com.taobao.accs.ILoginInfo;
import com.taobao.accs.client.GlobalClientInfo;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class Launcher_InitAccs implements Serializable {
    public static String defaultAppkey = "21646297";
    public static String mAppkey;
    public static Context mContext;
    public static boolean mForceBindUser;
    public static boolean mIsInited;
    public static String mSid;
    public static String mTtid;
    public static String mUserId;
    public static IAppReceiver mAppReceiver = new d();
    public static final Map<String, String> SERVICES = new HashMap();

    /* JADX WARN: Removed duplicated region for block: B:36:0x00aa A[Catch: Throwable -> 0x0134, TryCatch #2 {Throwable -> 0x0134, blocks: (B:3:0x000b, B:8:0x0025, B:34:0x00a2, B:36:0x00aa, B:37:0x00b7, B:39:0x00bd, B:40:0x00d0, B:42:0x0110, B:44:0x011c, B:45:0x0127, B:33:0x0099), top: B:54:0x000b }] */
    /* JADX WARN: Removed duplicated region for block: B:39:0x00bd A[Catch: Throwable -> 0x0134, TryCatch #2 {Throwable -> 0x0134, blocks: (B:3:0x000b, B:8:0x0025, B:34:0x00a2, B:36:0x00aa, B:37:0x00b7, B:39:0x00bd, B:40:0x00d0, B:42:0x0110, B:44:0x011c, B:45:0x0127, B:33:0x0099), top: B:54:0x000b }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void init(android.app.Application r9, java.util.HashMap<java.lang.String, java.lang.Object> r10) {
        /*
            Method dump skipped, instructions count: 319
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.taobao.accs.init.Launcher_InitAccs.init(android.app.Application, java.util.HashMap):void");
    }

    static class a implements ILoginInfo {
        @Override // com.taobao.accs.ILoginInfo
        public boolean getCommentUsed() {
            return false;
        }

        @Override // com.taobao.accs.ILoginInfo
        public String getEcode() {
            return null;
        }

        @Override // com.taobao.accs.ILoginInfo
        public String getHeadPicLink() {
            return null;
        }

        @Override // com.taobao.accs.ILoginInfo
        public String getNick() {
            return null;
        }

        @Override // com.taobao.accs.ILoginInfo
        public String getSsoToken() {
            return null;
        }

        a() {
        }

        @Override // com.taobao.accs.ILoginInfo
        public String getSid() {
            return Launcher_InitAccs.mSid;
        }

        @Override // com.taobao.accs.ILoginInfo
        public String getUserId() {
            return Launcher_InitAccs.mUserId;
        }
    }

    static {
        SERVICES.put("im", "com.taobao.tao.amp.remote.AccsReceiverCallback");
        SERVICES.put("powermsg", "com.taobao.appfrmbundle.mkt.AccsReceiverService");
        SERVICES.put("pmmonitor", "com.taobao.appfrmbundle.mkt.AccsReceiverService");
        SERVICES.put("cloudsync", "com.taobao.datasync.network.accs.AccsCloudSyncService");
        SERVICES.put("acds", "com.taobao.acds.compact.AccsACDSService");
        SERVICES.put(GlobalClientInfo.AGOO_SERVICE_ID, "org.android.agoo.accs.AgooService");
        SERVICES.put("agooAck", "org.android.agoo.accs.AgooService");
        SERVICES.put("agooTokenReport", "org.android.agoo.accs.AgooService");
        SERVICES.put("AliLive", "com.taobao.playbudyy.gameplugin.danmu.DanmuCallbackService");
        SERVICES.put("orange", "com.taobao.orange.accssupport.OrangeAccsService");
        SERVICES.put("tsla", "com.taobao.android.festival.accs.HomepageAccsMassService");
        SERVICES.put("taobaoWaimaiAccsService", "com.taobao.takeout.order.detail.service.TakeoutOrderDetailACCSService");
        SERVICES.put(com.ss.union.game.sdk.account.a.a.ae, "com.taobao.android.sso.v2.service.LoginAccsService");
        SERVICES.put("ranger_abtest", "com.taobao.ranger3.RangerACCSService");
        SERVICES.put("accs_poplayer", "com.taobao.tbpoplayer.AccsPopLayerService");
        SERVICES.put("dm_abtest", "com.tmall.wireless.ant.accs.AntAccsService");
        SERVICES.put("family", "com.taobao.family.FamilyAccsService");
        SERVICES.put("taobao-dingtalk", "com.laiwang.protocol.android.LwpAccsService");
        SERVICES.put("amp-sync", "com.taobao.message.init.accs.AccsReceiverCallback");
        SERVICES.put("friend_invite_msg", "com.taobao.message.init.accs.TaoFriendAccsReceiverCallback");
        SERVICES.put("slider", "com.taobao.slide.accs.SlideAccsService");
    }
}
