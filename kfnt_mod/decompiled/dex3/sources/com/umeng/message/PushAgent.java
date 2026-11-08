package com.umeng.message;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import anet.channel.AwcnConfig;
import anet.channel.strategy.dispatch.DispatchConstants;
import com.ss.android.downloadlib.constants.DownloadConstants;
import com.taobao.accs.ACCSClient;
import com.taobao.accs.AccsClientConfig;
import com.taobao.accs.AccsException;
import com.taobao.accs.client.GlobalConfig;
import com.taobao.accs.utl.ALog;
import com.taobao.agoo.ICallback;
import com.taobao.agoo.IRegister;
import com.taobao.agoo.TaobaoRegister;
import com.umeng.commonsdk.UMConfigure;
import com.umeng.commonsdk.debug.UMLog;
import com.umeng.commonsdk.proguard.o;
import com.umeng.message.UTrack;
import com.umeng.message.common.UmengMessageDeviceConfig;
import com.umeng.message.common.d;
import com.umeng.message.entity.UMessage;
import com.umeng.message.proguard.h;
import com.umeng.message.proguard.k;
import com.umeng.message.service.UMJobIntentService;
import com.umeng.message.tag.TagManager;
import com.umeng.message.util.b;
import java.util.Random;
import org.android.spdy.SpdyAgent;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class PushAgent {
    public static boolean DEBUG = false;
    private static final String TAG = "com.umeng.message.PushAgent";
    private static boolean sAppLaunchBy;
    private static PushAgent singleton;
    private Handler handler;
    private UHandler mAd;
    private Context mContext;
    private UHandler mMessageHandler;
    private UHandler mNotificationClickHandler;
    private IUmengRegisterCallback mRegisterCallback;
    private TagManager mTagMgr;
    private IUmengCallback mUnregisterCallback;
    private boolean mPushCheck = false;
    private boolean powerMode = true;

    private PushAgent(Context context) {
        try {
            this.mContext = context;
            this.mTagMgr = TagManager.getInstance(context);
            this.mMessageHandler = new UmengMessageHandler();
            this.mAd = new UmengAdHandler();
            this.mNotificationClickHandler = new UmengNotificationClickHandler();
            b.a(context);
        } catch (Exception e) {
            UMLog uMLog = UMConfigure.umDebugLog;
            UMLog.mutlInfo(TAG, 0, "PushAgent初始化失败", e.getMessage());
        }
        this.handler = new Handler(context.getMainLooper()) { // from class: com.umeng.message.PushAgent.1
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                super.handleMessage(message);
            }
        };
    }

    private PushAgent() {
    }

    public static synchronized PushAgent getInstance(Context context) {
        if (singleton == null) {
            singleton = new PushAgent(context.getApplicationContext());
        }
        return singleton;
    }

    private void register() {
        try {
        } catch (Exception unused) {
            UMLog uMLog = UMConfigure.umDebugLog;
            UMLog.mutlInfo(TAG, 0, "注册失败");
        }
        if (Build.VERSION.SDK_INT < 14) {
            UMLog uMLog2 = UMConfigure.umDebugLog;
            UMLog.mutlInfo(TAG, 0, "U-Push最低支持的系统版本为Android 4.0");
            return;
        }
        if (!h.a(this.mContext, this.handler)) {
            UMLog uMLog3 = UMConfigure.umDebugLog;
            UMLog.mutlInfo(TAG, 0, "AndroidManifest权限或参数错误");
            return;
        }
        UMLog uMLog4 = UMConfigure.umDebugLog;
        UMLog.mutlInfo(TAG, 2, "AndroidManifest配置正确、参数正确");
        if (!TextUtils.isEmpty(getMessageAppkey()) && !TextUtils.isEmpty(getMessageSecret())) {
            h.a(this.mContext, (Class<?>) UmengMessageCallbackHandlerService.class);
            if (DEBUG) {
                h.b(this.mContext, this.handler);
            }
            ALog.setUseTlog(false);
            anet.channel.util.ALog.setUseTlog(false);
            AwcnConfig.setAccsSessionCreateForbiddenInBg(false);
            ACCSClient.setEnvironment(this.mContext, 0);
            ACCSClient.init(this.mContext, new AccsClientConfig.Builder().setAppKey("umeng:" + getMessageAppkey()).setAppSecret(getMessageSecret()).setInappHost("umengacs.m.taobao.com").setInappPubKey(11).setChannelHost("umengjmacs.m.taobao.com").setChannelPubKey(11).setKeepAlive(getPowerMode()).setAutoUnit(false).setTag("default").build());
            DispatchConstants.setAmdcServerDomain(new String[]{"amdcopen.m.taobao.com", "amdc.wapa.taobao.com", "amdc.taobao.net"});
            DispatchConstants.setAmdcServerFixIp(new String[][]{new String[]{"106.11.61.135", "106.11.61.137"}, null, null});
            if (UmengMessageDeviceConfig.isMiui8()) {
                TaobaoRegister.setAgooMsgReceiveService("com.umeng.message.XiaomiIntentService");
            } else {
                TaobaoRegister.setAgooMsgReceiveService("com.umeng.message.UmengIntentService");
            }
            d.a(new Runnable() { // from class: com.umeng.message.PushAgent.2
                @Override // java.lang.Runnable
                public void run() {
                    String str = "umeng:" + PushAgent.this.getMessageAppkey();
                    String messageSecret = PushAgent.this.getMessageSecret();
                    UMLog uMLog5 = UMConfigure.umDebugLog;
                    UMLog.mutlInfo(PushAgent.TAG, 2, "appkey:" + str + ",secret:" + messageSecret);
                    try {
                        TaobaoRegister.register(PushAgent.this.mContext, "default", str, messageSecret, "android@umeng", new IRegister() { // from class: com.umeng.message.PushAgent.2.1
                            @Override // com.taobao.agoo.IRegister
                            public void onSuccess(String str2) {
                                UMLog uMLog6 = UMConfigure.umDebugLog;
                                UMLog.mutlInfo(PushAgent.TAG, 2, "注册成功:" + str2);
                                PushAgent.this.registerSuccesss(str2);
                            }

                            @Override // com.taobao.agoo.IRegister, com.taobao.agoo.ICallback
                            public void onFailure(String str2, String str3) {
                                UMLog uMLog6 = UMConfigure.umDebugLog;
                                UMLog.mutlInfo(PushAgent.TAG, 0, "注册失败-->s:" + str2 + ",s1:" + str3);
                                PushAgent.this.registerFailure(str2, str3);
                                UMLog uMLog7 = UMConfigure.umDebugLog;
                                UMLog.aq(k.a, 0, "\\|");
                            }
                        });
                    } catch (AccsException unused2) {
                        UMLog uMLog6 = UMConfigure.umDebugLog;
                        UMLog.mutlInfo(PushAgent.TAG, 0, "注册失败");
                    }
                }
            });
            if (Build.VERSION.SDK_INT < 26 || UmengMessageHandler.isChannelSet) {
                return;
            }
            UmengMessageHandler.isChannelSet = true;
            NotificationChannel notificationChannel = new NotificationChannel(UmengMessageHandler.PRIMARY_CHANNEL, getNotificationChannel(this.mContext), 3);
            NotificationManager notificationManager = (NotificationManager) this.mContext.getSystemService(UMessage.DISPLAY_TYPE_NOTIFICATION);
            if (notificationManager != null) {
                notificationManager.createNotificationChannel(notificationChannel);
                return;
            }
            return;
        }
        UMLog uMLog5 = UMConfigure.umDebugLog;
        UMLog.mutlInfo(TAG, 0, "Appkey和Secret key均不能为空");
    }

    private String getNotificationChannel(Context context) {
        String notificationChannelName = getInstance(context).getNotificationChannelName();
        return TextUtils.isEmpty(notificationChannelName) ? UmengMessageHandler.DEFAULT_NOTIFICATION_CHANNEL_NAME : notificationChannelName;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void registerSuccesss(String str) {
        Intent intent = new Intent();
        intent.setPackage(this.mContext.getPackageName());
        intent.setAction(MsgConstant.MESSAGE_REGISTER_CALLBACK_ACTION);
        intent.putExtra(MsgConstant.KEY_REGISTRATION_ID, str);
        intent.putExtra("status", true);
        UMJobIntentService.enqueueWork(this.mContext, (Class<? extends UMJobIntentService>) UmengMessageCallbackHandlerService.class, intent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void registerFailure(String str, String str2) {
        Intent intent = new Intent();
        intent.setPackage(this.mContext.getPackageName());
        intent.setAction(MsgConstant.MESSAGE_REGISTER_CALLBACK_ACTION);
        intent.putExtra("status", false);
        intent.putExtra(o.at, str);
        intent.putExtra("s1", str2);
        UMJobIntentService.enqueueWork(this.mContext, (Class<? extends UMJobIntentService>) UmengMessageCallbackHandlerService.class, intent);
    }

    private void enable() {
        try {
            TaobaoRegister.bindAgoo(this.mContext, new ICallback() { // from class: com.umeng.message.PushAgent.3
                @Override // com.taobao.agoo.ICallback
                public void onSuccess() {
                    UMLog uMLog = UMConfigure.umDebugLog;
                    UMLog.mutlInfo(PushAgent.TAG, 2, "开启推送成功");
                    Intent intent = new Intent();
                    intent.setPackage(PushAgent.this.mContext.getPackageName());
                    intent.setAction(MsgConstant.MESSAGE_ENABLE_CALLBACK_ACTION);
                    intent.putExtra("status", true);
                    UMJobIntentService.enqueueWork(PushAgent.this.mContext, (Class<? extends UMJobIntentService>) UmengMessageCallbackHandlerService.class, intent);
                }

                @Override // com.taobao.agoo.ICallback
                public void onFailure(String str, String str2) {
                    UMLog uMLog = UMConfigure.umDebugLog;
                    UMLog.mutlInfo(PushAgent.TAG, 2, "开启推送失败-->s:" + str + ",s1:" + str2);
                    Intent intent = new Intent();
                    intent.setPackage(PushAgent.this.mContext.getPackageName());
                    intent.setAction(MsgConstant.MESSAGE_ENABLE_CALLBACK_ACTION);
                    intent.putExtra("status", false);
                    intent.putExtra(o.at, str);
                    intent.putExtra("s1", str2);
                    UMJobIntentService.enqueueWork(PushAgent.this.mContext, (Class<? extends UMJobIntentService>) UmengMessageCallbackHandlerService.class, intent);
                }
            });
        } catch (Exception unused) {
            UMLog uMLog = UMConfigure.umDebugLog;
            UMLog.mutlInfo(TAG, 0, "开启推送失败");
        }
    }

    private void disable() {
        try {
            TaobaoRegister.unbindAgoo(this.mContext, new ICallback() { // from class: com.umeng.message.PushAgent.4
                @Override // com.taobao.agoo.ICallback
                public void onSuccess() {
                    UMLog uMLog = UMConfigure.umDebugLog;
                    UMLog.mutlInfo(PushAgent.TAG, 2, "关闭推送成功");
                    Intent intent = new Intent();
                    intent.setPackage(PushAgent.this.mContext.getPackageName());
                    intent.setAction(MsgConstant.MESSAGE_DISABLE_CALLBACK_ACTION);
                    intent.putExtra("status", true);
                    UMJobIntentService.enqueueWork(PushAgent.this.mContext, (Class<? extends UMJobIntentService>) UmengMessageCallbackHandlerService.class, intent);
                }

                @Override // com.taobao.agoo.ICallback
                public void onFailure(String str, String str2) {
                    UMLog uMLog = UMConfigure.umDebugLog;
                    UMLog.mutlInfo(PushAgent.TAG, 0, "关闭推送失败-->s:" + str + ",s1:" + str2);
                    Intent intent = new Intent();
                    intent.setPackage(PushAgent.this.mContext.getPackageName());
                    intent.setAction(MsgConstant.MESSAGE_DISABLE_CALLBACK_ACTION);
                    intent.putExtra("status", false);
                    intent.putExtra(o.at, str);
                    intent.putExtra("s1", str2);
                    UMJobIntentService.enqueueWork(PushAgent.this.mContext, (Class<? extends UMJobIntentService>) UmengMessageCallbackHandlerService.class, intent);
                }
            });
        } catch (Exception unused) {
            UMLog uMLog = UMConfigure.umDebugLog;
            UMLog.mutlInfo(TAG, 0, "关闭推送失败");
        }
    }

    public void register(IUmengRegisterCallback iUmengRegisterCallback) {
        setRegisterCallback(iUmengRegisterCallback);
        register();
    }

    public void enable(IUmengCallback iUmengCallback) {
        setCallback(iUmengCallback);
        enable();
    }

    public void disable(IUmengCallback iUmengCallback) {
        setCallback(iUmengCallback);
        disable();
    }

    public void setMessageHandler(UHandler uHandler) {
        this.mMessageHandler = uHandler;
    }

    public UHandler getMessageHandler() {
        return this.mMessageHandler;
    }

    public UHandler getAdHandler() {
        return this.mAd;
    }

    public void setNotificationClickHandler(UHandler uHandler) {
        this.mNotificationClickHandler = uHandler;
    }

    public UHandler getNotificationClickHandler() {
        return this.mNotificationClickHandler;
    }

    public TagManager getTagManager() {
        return this.mTagMgr;
    }

    public void addAlias(String str, String str2, UTrack.ICallBack iCallBack) {
        UTrack.getInstance(this.mContext).addAlias(str, str2, iCallBack);
    }

    public void setAlias(String str, String str2, UTrack.ICallBack iCallBack) {
        UTrack.getInstance(this.mContext).setAlias(str, str2, iCallBack);
    }

    public void deleteAlias(String str, String str2, UTrack.ICallBack iCallBack) {
        UTrack.getInstance(this.mContext).deleteAlias(str, str2, iCallBack);
    }

    public String getMessageSecret() {
        String messageAppSecret = MessageSharedPrefs.getInstance(this.mContext).getMessageAppSecret();
        return TextUtils.isEmpty(messageAppSecret) ? UmengMessageDeviceConfig.getMetaData(this.mContext, "UMENG_MESSAGE_SECRET") : messageAppSecret;
    }

    public String getMessageAppkey() {
        return MessageSharedPrefs.getInstance(this.mContext).getMessageAppKey();
    }

    public String getMessageChannel() {
        String messageChannel = MessageSharedPrefs.getInstance(this.mContext).getMessageChannel();
        return TextUtils.isEmpty(messageChannel) ? UmengMessageDeviceConfig.getChannel(this.mContext) : messageChannel;
    }

    public void onAppStart() {
        UTrack.getInstance(this.mContext).trackAppLaunch(DownloadConstants.DEFAULT_NEXT_INSTALL_MIN_INTERVAL);
        UTrack.getInstance(this.mContext).sendCachedMsgLog(isAppLaunchByMessage() ? Math.abs(new Random().nextLong() % MsgConstant.b) : 0L);
    }

    public <U extends UmengMessageService> void setPushIntentServiceClass(Class<U> cls) {
        if (h.d(this.mContext)) {
            MessageSharedPrefs.getInstance(this.mContext).setPushIntentServiceClass(cls);
        }
    }

    public String getPushIntentServiceClass() {
        return MessageSharedPrefs.getInstance(this.mContext).getPushIntentServiceClass();
    }

    private void setDebugMode(boolean z) {
        DEBUG = z;
        ALog.setPrintLog(z);
        anet.channel.util.ALog.setPrintLog(z);
        SpdyAgent.enableDebug = z;
    }

    public void setNoDisturbMode(int i, int i2, int i3, int i4) {
        if (h.d(this.mContext)) {
            MessageSharedPrefs.getInstance(this.mContext).a(i, i2, i3, i4);
        }
    }

    public int getNoDisturbStartHour() {
        return MessageSharedPrefs.getInstance(this.mContext).a();
    }

    public int getNoDisturbStartMinute() {
        return MessageSharedPrefs.getInstance(this.mContext).b();
    }

    public int getNoDisturbEndHour() {
        return MessageSharedPrefs.getInstance(this.mContext).c();
    }

    public int getNoDisturbEndMinute() {
        return MessageSharedPrefs.getInstance(this.mContext).d();
    }

    public static void setAppLaunchByMessage() {
        sAppLaunchBy = true;
    }

    public static boolean isAppLaunchByMessage() {
        return sAppLaunchBy;
    }

    public String getRegistrationId() {
        return MessageSharedPrefs.getInstance(this.mContext).getDeviceToken();
    }

    public void setDisplayNotificationNumber(int i) {
        if (!h.d(this.mContext) || i < 0 || i > 10) {
            return;
        }
        MessageSharedPrefs.getInstance(this.mContext).setDisplayNotificationNumber(i);
    }

    public int getDisplayNotificationNumber() {
        return MessageSharedPrefs.getInstance(this.mContext).getDisplayNotificationNumber();
    }

    @Deprecated
    private void setAppkeyAndSecret(String str, String str2) {
        if (h.d(this.mContext)) {
            String messageAppKey = MessageSharedPrefs.getInstance(this.mContext).getMessageAppKey();
            String messageAppSecret = MessageSharedPrefs.getInstance(this.mContext).getMessageAppSecret();
            if (!messageAppKey.equals(str) && !messageAppSecret.equals(str2)) {
                MessageSharedPrefs.getInstance(this.mContext).removeMessageAppKey();
                MessageSharedPrefs.getInstance(this.mContext).removeMessageAppSecret();
            }
            MessageSharedPrefs.getInstance(this.mContext).setMessageAppKey(str);
            MessageSharedPrefs.getInstance(this.mContext).setMessageAppSecret(str2);
            UTrack.getInstance(this.mContext).updateHeader();
        }
    }

    @Deprecated
    private void setAppkey(String str) {
        if (h.d(this.mContext)) {
            if (str == null || str.equals("")) {
                UMLog uMLog = UMConfigure.umDebugLog;
                UMLog.mutlInfo(TAG, 0, "appkey不能为null");
            } else {
                MessageSharedPrefs.getInstance(this.mContext).setMessageAppKey(str);
            }
        }
    }

    @Deprecated
    private void setSecret(String str) {
        if (h.d(this.mContext)) {
            if (str == null || str.equals("")) {
                UMLog uMLog = UMConfigure.umDebugLog;
                UMLog.mutlInfo(TAG, 0, "appSecret不能为null");
            } else {
                MessageSharedPrefs.getInstance(this.mContext).setMessageAppSecret(str);
            }
        }
    }

    @Deprecated
    private void setMessageChannel(String str) {
        System.currentTimeMillis();
        if (h.d(this.mContext)) {
            MessageSharedPrefs.getInstance(this.mContext).setMessageChannel(str);
            d.a(new Runnable() { // from class: com.umeng.message.PushAgent.5
                @Override // java.lang.Runnable
                public void run() {
                    UTrack.getInstance(PushAgent.this.mContext).updateHeader();
                }
            });
        }
    }

    public void setRegisterCallback(IUmengRegisterCallback iUmengRegisterCallback) {
        this.mRegisterCallback = iUmengRegisterCallback;
    }

    public IUmengRegisterCallback getRegisterCallback() {
        return this.mRegisterCallback;
    }

    public void setCallback(IUmengCallback iUmengCallback) {
        this.mUnregisterCallback = iUmengCallback;
    }

    public IUmengCallback getCallback() {
        return this.mUnregisterCallback;
    }

    public void setMuteDurationSeconds(int i) {
        if (h.d(this.mContext)) {
            MessageSharedPrefs.getInstance(this.mContext).setMuteDuration(i);
        }
    }

    public int getMuteDurationSeconds() {
        return MessageSharedPrefs.getInstance(this.mContext).getMuteDuration();
    }

    public boolean isIncludesUmengUpdateSDK() throws ClassNotFoundException {
        Class<?> cls;
        try {
            cls = Class.forName("com.umeng.update.UmengUpdateAgent");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            cls = null;
        }
        return cls != null;
    }

    public boolean getNotificationOnForeground() {
        return MessageSharedPrefs.getInstance(this.mContext).getNotificaitonOnForeground();
    }

    public void setNotificaitonOnForeground(boolean z) {
        if (h.d(this.mContext)) {
            MessageSharedPrefs.getInstance(this.mContext).setNotificaitonOnForeground(z);
        }
    }

    public String getResourcePackageName() {
        return MessageSharedPrefs.getInstance(this.mContext).getResourcePackageName();
    }

    public void setResourcePackageName(String str) {
        if (h.d(this.mContext)) {
            MessageSharedPrefs.getInstance(this.mContext).setResourcePackageName(str);
        }
    }

    public boolean isPushCheck() {
        return this.mPushCheck;
    }

    public void setPushCheck(boolean z) {
        this.mPushCheck = z;
    }

    public int getNotificationPlayVibrate() {
        return MessageSharedPrefs.getInstance(this.mContext).getNotificationPlayVibrate();
    }

    public void setNotificationPlayVibrate(int i) {
        if (h.d(this.mContext)) {
            MessageSharedPrefs.getInstance(this.mContext).setNotificationPlayVibrate(i);
        }
    }

    public int getNotificationPlayLights() {
        return MessageSharedPrefs.getInstance(this.mContext).getNotificationPlayLights();
    }

    public void setNotificationPlayLights(int i) {
        if (h.d(this.mContext)) {
            MessageSharedPrefs.getInstance(this.mContext).setNotificationPlayLights(i);
        }
    }

    public int getNotificationPlaySound() {
        return MessageSharedPrefs.getInstance(this.mContext).getNotificationPlaySound();
    }

    public void setNotificationPlaySound(int i) {
        if (h.d(this.mContext)) {
            MessageSharedPrefs.getInstance(this.mContext).setNotificationPlaySound(i);
        }
    }

    public void keepLowPowerMode(boolean z) {
        this.powerMode = !z;
    }

    private boolean getPowerMode() {
        return this.powerMode;
    }

    public void setEnableForground(Context context, boolean z) {
        GlobalConfig.setEnableForground(context, z);
    }

    public void setNotificationChannelName(String str) {
        if (h.d(this.mContext)) {
            MessageSharedPrefs.getInstance(this.mContext).b(str);
        }
    }

    public String getNotificationChannelName() {
        return MessageSharedPrefs.getInstance(this.mContext).h();
    }
}
