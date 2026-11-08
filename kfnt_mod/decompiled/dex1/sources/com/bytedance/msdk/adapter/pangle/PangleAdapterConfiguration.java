package com.bytedance.msdk.adapter.pangle;

import android.content.Context;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import com.bytedance.b.a.a.a;
import com.bytedance.msdk.adapter.ThirdSdkInit;
import com.bytedance.msdk.adapter.config.TTBaseAdapterConfiguration;
import com.bytedance.msdk.adapter.config.TTOnNetworkInitializationFinishedListener;
import com.bytedance.msdk.adapter.util.Logger;
import com.bytedance.msdk.adapter.util.Preconditions;
import com.bytedance.msdk.api.AdError;
import com.bytedance.msdk.api.AdSlot;
import com.bytedance.msdk.api.error.InitSdkError;
import com.bytedance.msdk.api.v2.GMPrivacyConfig;
import com.bytedance.sdk.account.platform.base.OnekeyLoginConstants;
import com.bytedance.sdk.openadsdk.LocationProvider;
import com.bytedance.sdk.openadsdk.TTAdConfig;
import com.bytedance.sdk.openadsdk.TTAdConstant;
import com.bytedance.sdk.openadsdk.TTAdSdk;
import com.bytedance.sdk.openadsdk.TTCustomController;
import com.bytedance.sdk.openadsdk.TTLocation;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class PangleAdapterConfiguration extends TTBaseAdapterConfiguration {
    public static final int ADN_INIT_ERROR_NO_APPID = 1;
    public static final String AD_PLACEMENT_ID_EXTRA_KEY = "ad_placement_id";
    public static final String APP_ID_EXTRA_KEY = "app_id";
    public static final int TT_THEME_STATUS_DAY = 0;
    public static final int TT_THEME_STATUS_NIGHT = 1;

    /* renamed from: a, reason: collision with root package name */
    private static final String f4119a = "PangleAdapterConfiguration";

    /* renamed from: b, reason: collision with root package name */
    private boolean f4120b;

    /* renamed from: c, reason: collision with root package name */
    private boolean f4121c;
    private final List<ThirdSdkInit.InitCallback> d = new CopyOnWriteArrayList();
    private GMPrivacyConfig e = new GMPrivacyConfig();
    private final TTCustomController f = new TTCustomController() { // from class: com.bytedance.msdk.adapter.pangle.PangleAdapterConfiguration.2
        @Override // com.bytedance.sdk.openadsdk.TTCustomController
        public boolean alist() {
            return PangleAdapterConfiguration.this.e.appList();
        }

        @Override // com.bytedance.sdk.openadsdk.TTCustomController
        public String getDevImei() {
            return PangleAdapterConfiguration.this.e.getDevImei();
        }

        @Override // com.bytedance.sdk.openadsdk.TTCustomController
        public String getDevOaid() {
            return PangleAdapterConfiguration.this.e.getDevOaid();
        }

        public String getMacAddress() {
            return PangleAdapterConfiguration.this.e.getMacAddress();
        }

        @Override // com.bytedance.sdk.openadsdk.TTCustomController
        public LocationProvider getTTLocation() {
            if (PangleAdapterConfiguration.this.e.getTTLocation() != null) {
                return new TTLocation(PangleAdapterConfiguration.this.e.getTTLocation().getLatitude(), PangleAdapterConfiguration.this.e.getTTLocation().getLongitude());
            }
            return null;
        }

        @Override // com.bytedance.sdk.openadsdk.TTCustomController
        public boolean isCanUseLocation() {
            return PangleAdapterConfiguration.this.e.isCanUseLocation();
        }

        @Override // com.bytedance.sdk.openadsdk.TTCustomController
        public boolean isCanUsePhoneState() {
            return PangleAdapterConfiguration.this.e.isCanUsePhoneState();
        }

        @Override // com.bytedance.sdk.openadsdk.TTCustomController
        public boolean isCanUseWifiState() {
            return PangleAdapterConfiguration.this.e.isCanUseWifiState();
        }

        @Override // com.bytedance.sdk.openadsdk.TTCustomController
        public boolean isCanUseWriteExternal() {
            return PangleAdapterConfiguration.this.e.isCanUseWriteExternal();
        }
    };
    private TTAdSdk.InitCallback g = new TTAdSdk.InitCallback() { // from class: com.bytedance.msdk.adapter.pangle.PangleAdapterConfiguration.3
        @Override // com.bytedance.sdk.openadsdk.TTAdSdk.InitCallback
        public void fail(int i, String str) {
            PangleAdapterConfiguration.this.a(i, str);
        }

        @Override // com.bytedance.sdk.openadsdk.TTAdSdk.InitCallback
        public void success() {
            Logger.e("TTMediationSDK_SDK_Init", "pangle_init_ success.........:");
            PangleAdapterConfiguration.this.b();
        }
    };

    private TTAdConfig a(Context context, String str, Map<String, Object> map) throws JSONException {
        Boolean bool = (Boolean) map.get(TTBaseAdapterConfiguration.PANGLE_IS_PANGLE_USE_TEXTURE_VIEW);
        boolean z = bool != null && bool.booleanValue();
        String str2 = (String) map.get(TTBaseAdapterConfiguration.PANGLE_APP_NAME);
        Boolean bool2 = (Boolean) map.get(TTBaseAdapterConfiguration.PANGLE_IS_PANGLE_PAID);
        boolean z2 = bool2 != null && bool2.booleanValue();
        Integer num = (Integer) map.get(TTBaseAdapterConfiguration.PANGLE_TITLE_BAR_THEME);
        Boolean bool3 = (Boolean) map.get(TTBaseAdapterConfiguration.PANGLE_IS_PANGLE_ALLOW_SHOW_NOTIFY);
        boolean z3 = bool3 != null && bool3.booleanValue();
        Boolean bool4 = (Boolean) map.get(TTBaseAdapterConfiguration.PANGLE_IS_PANGLE_ALLOW_SHOW_PAGE_WHEN_SCREEN_LOCK);
        boolean z4 = bool4 != null && bool4.booleanValue();
        int[] iArr = (int[]) map.get(TTBaseAdapterConfiguration.PANGLE_DIRECT_DOWNLOAD_NETWORKTYPE);
        String strA = a((String) map.get(TTBaseAdapterConfiguration.PANGLE_DATA));
        String[] strArr = (String[]) map.get(TTBaseAdapterConfiguration.PANGLE_NEED_CLEAR_TASK_RESET);
        String str3 = (String) map.get(TTBaseAdapterConfiguration.PANGLE_KEYWORDS);
        Integer num2 = (Integer) map.get(TTBaseAdapterConfiguration.PANGLE_PLUGIN_UPDATE_CONFIG);
        TTAdConfig.Builder builderDebug = new TTAdConfig.Builder().appId(str).useTextureView(z).appName(str2).paid(z2).titleBarTheme(num == null ? 0 : num.intValue()).allowShowNotify(z3).allowShowPageWhenScreenLock(z4).debug(Logger.isDebug());
        if (iArr == null) {
            iArr = new int[0];
        }
        TTAdConfig.Builder builderSupportMultiProcess = builderDebug.directDownloadNetworkType(iArr).supportMultiProcess(false);
        if (strA == null) {
            strA = "";
        }
        TTAdConfig.Builder builderData = builderSupportMultiProcess.data(strA);
        if (strArr == null) {
            strArr = new String[0];
        }
        TTAdConfig.Builder builderCustomController = builderData.needClearTaskReset(strArr).customController(this.f);
        if (str3 == null) {
            str3 = "";
        }
        return builderCustomController.keywords(str3).setPluginUpdateConfig(num2 == null ? 2 : num2.intValue()).build();
    }

    private String a(String str) throws JSONException {
        String strA = a(str, "personal_ads_type", this.e.isLimitPersonalAds() ? OnekeyLoginConstants.CU_RESULT_SUCCESS : "1");
        PangleAdapterUtils.setPangleData(strA);
        return strA;
    }

    private String a(String str, String str2, String str3) throws JSONException {
        JSONArray jSONArray;
        if (!TextUtils.isEmpty(str2) && !TextUtils.isEmpty(str3)) {
            try {
                if (TextUtils.isEmpty(str)) {
                    jSONArray = new JSONArray();
                } else {
                    try {
                        jSONArray = new JSONArray(str);
                    } catch (JSONException unused) {
                        jSONArray = new JSONArray();
                    }
                }
                boolean z = false;
                int i = 0;
                while (true) {
                    if (i < jSONArray.length()) {
                        JSONObject jSONObjectOptJSONObject = jSONArray.optJSONObject(i);
                        if (jSONObjectOptJSONObject != null && str2.equals(jSONObjectOptJSONObject.getString("name"))) {
                            jSONObjectOptJSONObject.put(a.n, str3);
                            z = true;
                            break;
                        }
                        i++;
                    } else {
                        break;
                    }
                }
                if (!z) {
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put("name", str2);
                    jSONObject.put(a.n, str3);
                    jSONArray.put(jSONObject);
                }
                return jSONArray.toString();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i, String str) {
        for (ThirdSdkInit.InitCallback initCallback : this.d) {
            if (initCallback != null) {
                initCallback.fail(i, str);
            }
        }
        this.d.clear();
        this.f4121c = true;
        this.f4120b = false;
    }

    private void a(Context context, TTAdConfig tTAdConfig) {
        try {
            TTAdSdk.init(context, tTAdConfig);
            b();
        } catch (Throwable th) {
            a(AdError.ERROR_CODE_ADAPTER_CONFIGURATION_ERROR, "pangle");
            th.printStackTrace();
        }
    }

    private void a(Context context, String str, Map<String, Object> map, ThirdSdkInit.InitCallback initCallback) {
        if (!TextUtils.isEmpty(str) && context != null) {
            b(context, str, map, initCallback);
            return;
        }
        Logger.e("TTMediationSDK_ADAPTER", f4119a + ",Invalid Pangle app ID. Ensure the app id is valid on the csj dashboard.");
        if (initCallback != null) {
            initCallback.fail(1, "Invalid Pangle app ID");
        }
    }

    private void a(Context context, Map<String, Object> map, final TTOnNetworkInitializationFinishedListener tTOnNetworkInitializationFinishedListener, String str) {
        a(context, str, map, new ThirdSdkInit.InitCallback() { // from class: com.bytedance.msdk.adapter.pangle.PangleAdapterConfiguration.1
            @Override // com.bytedance.msdk.adapter.ThirdSdkInit.InitCallback
            public void fail(int i, String str2) {
                PangleAdapterConfiguration.this.setInitedSuccess(false);
                TTOnNetworkInitializationFinishedListener tTOnNetworkInitializationFinishedListener2 = tTOnNetworkInitializationFinishedListener;
                if (tTOnNetworkInitializationFinishedListener2 != null) {
                    tTOnNetworkInitializationFinishedListener2.onNetworkInitializationFinished(PangleAdapterConfiguration.class, new InitSdkError(AdError.ERROR_CODE_ADAPTER_CONFIGURATION_ERROR, "pangle"));
                }
                Logger.e("TTMediationSDK_ADAPTER", "Initializing Pangle has encountered an exception.code=" + i + ",msg=" + str2);
            }

            @Override // com.bytedance.msdk.adapter.ThirdSdkInit.InitCallback
            public void success() {
                PangleAdapterConfiguration.this.setInitedSuccess(true);
                TTOnNetworkInitializationFinishedListener tTOnNetworkInitializationFinishedListener2 = tTOnNetworkInitializationFinishedListener;
                if (tTOnNetworkInitializationFinishedListener2 != null) {
                    tTOnNetworkInitializationFinishedListener2.onNetworkInitializationFinished(PangleAdapterConfiguration.class, new InitSdkError(AdError.ERROR_CODE_ADAPTER_INITIALIZATION_SUCCESS, "pangle"));
                }
                Logger.i("TTMediationSDK_SDK_Init", "Initializing Pangle has success !!!");
            }
        });
    }

    private void a(Context context, Map<String, Object> map, String str) {
        synchronized (ThirdSdkInit.class) {
            Logger.e("TTMediationSDK_SDK_Init", "pangle doInit pangleInit=" + this.f4120b);
            if (this.f4120b) {
                if (TTAdSdk.isInitSuccess()) {
                    b();
                } else if (this.f4121c) {
                    a(AdError.ERROR_CODE_ADAPTER_CONFIGURATION_ERROR, "pangle");
                }
            } else {
                if (TextUtils.isEmpty(str)) {
                    Logger.e("TTMediationSDK_SDK_Init", "pangle_init_error.........:appId为空");
                    return;
                }
                this.f4120b = true;
                TTAdConfig tTAdConfigA = a(context, str, map);
                if (a()) {
                    TTAdSdk.init(context, tTAdConfigA, this.g);
                } else {
                    a(context, tTAdConfigA);
                }
            }
        }
    }

    private void a(String str, String str2) throws JSONException {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return;
        }
        String strA = a(PangleAdapterUtils.getPangleData(), str, str2);
        PangleAdapterUtils.setPangleData(strA);
        TTAdSdk.updateAdConfig(new TTAdConfig.Builder().data(strA).build());
    }

    private boolean a() {
        boolean z = false;
        try {
            Class<?> cls = Class.forName("com.bytedance.sdk.openadsdk.TTAdConstant");
            Field declaredField = cls.getDeclaredField("IS_P");
            declaredField.setAccessible(true);
            boolean zBooleanValue = ((Boolean) declaredField.get(cls)).booleanValue();
            try {
                Logger.e("TTMediationSDK_SDK_Init", "穿山甲版本：isP=" + zBooleanValue);
                return zBooleanValue;
            } catch (Throwable th) {
                z = zBooleanValue;
                th = th;
                th.printStackTrace();
                return z;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    private int[] a(int i, AdSlot adSlot) {
        switch (i) {
            case 1:
                return new int[]{320, 50};
            case 2:
                return new int[]{320, 100};
            case 3:
                return new int[]{TTAdConstant.DEFAULT_LIVE_SHOW_TIME_MAX, ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION};
            case 4:
                return new int[]{468, 60};
            case 5:
                return new int[]{728, 90};
            case 6:
                if (adSlot.getImgAcceptedWidth() > 0 && adSlot.getImgAcceptedHeight() > 0) {
                    return new int[]{adSlot.getImgAcceptedWidth(), adSlot.getImgAcceptedHeight()};
                }
                if (adSlot.getImgAcceptedWidth() > 0 && adSlot.getImgAcceptedHeight() < 0) {
                    return new int[]{adSlot.getImgAcceptedWidth(), 0};
                }
                break;
        }
        return new int[]{320, 50};
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b() {
        for (ThirdSdkInit.InitCallback initCallback : this.d) {
            if (initCallback != null) {
                initCallback.success();
            }
        }
        this.d.clear();
    }

    private void b(Context context, String str, Map<String, Object> map, ThirdSdkInit.InitCallback initCallback) {
        this.d.add(initCallback);
        try {
            if (TextUtils.isEmpty(str)) {
                str = ThirdSdkInit.getTTPangleAppId();
            }
            a(context, map, str);
        } catch (Throwable th) {
            synchronized (ThirdSdkInit.class) {
                this.f4120b = false;
                this.f4121c = false;
                th.printStackTrace();
                Logger.e("TTMediationSDK_SDK_Init", "TTSDK 初始化失败。。 e=" + th.toString());
            }
        }
    }

    @Override // com.bytedance.msdk.adapter.config.ITTAdapterConfiguration
    public String getAdNetworkName() {
        return "pangle";
    }

    @Override // com.bytedance.msdk.adapter.config.ITTAdapterConfiguration
    public String getAdapterVersion() {
        return "4.0.1.9.1";
    }

    /* JADX WARN: Code restructure failed: missing block: B:100:0x018c, code lost:
    
        if (r12.getImgAcceptedHeight() > 0) goto L101;
     */
    /* JADX WARN: Code restructure failed: missing block: B:101:0x018e, code lost:
    
        r2 = r12.getImgAcceptedWidth();
        r4 = r12.getImgAcceptedHeight();
     */
    /* JADX WARN: Code restructure failed: missing block: B:56:0x0101, code lost:
    
        if (r12.getImgAcceptedHeight() > 0) goto L101;
     */
    /* JADX WARN: Code restructure failed: missing block: B:72:0x012b, code lost:
    
        if (r12.getImgAcceptedHeight() > 0) goto L101;
     */
    /* JADX WARN: Removed duplicated region for block: B:103:0x019b A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:106:0x01a0  */
    /* JADX WARN: Removed duplicated region for block: B:111:0x01ad A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:112:0x01af  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x00be  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x0152  */
    @Override // com.bytedance.msdk.adapter.config.ITTAdapterConfiguration
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.String getBiddingToken(android.content.Context r12, java.util.Map<java.lang.String, java.lang.Object> r13) {
        /*
            Method dump skipped, instructions count: 447
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bytedance.msdk.adapter.pangle.PangleAdapterConfiguration.getBiddingToken(android.content.Context, java.util.Map):java.lang.String");
    }

    @Override // com.bytedance.msdk.adapter.config.ITTAdapterConfiguration
    public String getGroMoreSdkVersion() {
        return "3.1.0.1";
    }

    @Override // com.bytedance.msdk.adapter.config.ITTAdapterConfiguration
    public String getNetworkSdkVersion() {
        return TTAdSdk.getAdManager().getSDKVersion();
    }

    @Override // com.bytedance.msdk.adapter.config.ITTAdapterConfiguration
    public void initializeNetwork(@NonNull Context context, Map<String, Object> map, @NonNull TTOnNetworkInitializationFinishedListener tTOnNetworkInitializationFinishedListener) {
        Preconditions.checkNotNull(context);
        Preconditions.checkNotNull(tTOnNetworkInitializationFinishedListener);
        if (isInitedSuccess() || map == null || map.isEmpty()) {
            return;
        }
        try {
            a(context, map, tTOnNetworkInitializationFinishedListener, (String) map.get("app_id"));
        } catch (Exception e) {
            Logger.e("TTMediationSDK_ADAPTER", "Initializing Pangle has encountered an exception.", e);
            if (tTOnNetworkInitializationFinishedListener != null) {
                tTOnNetworkInitializationFinishedListener.onNetworkInitializationFinished(PangleAdapterConfiguration.class, new InitSdkError(AdError.ERROR_CODE_ADAPTER_CONFIGURATION_ERROR, "pangle"));
            }
        }
    }

    @Override // com.bytedance.msdk.adapter.config.TTBaseAdapterConfiguration, com.bytedance.msdk.adapter.config.ITTAdapterConfiguration
    public void setPrivacyConfig(GMPrivacyConfig gMPrivacyConfig) throws JSONException {
        if (gMPrivacyConfig != null) {
            this.e = gMPrivacyConfig;
            a("personal_ads_type", this.e.isLimitPersonalAds() ? OnekeyLoginConstants.CU_RESULT_SUCCESS : "1");
        }
    }

    @Override // com.bytedance.msdk.adapter.config.TTBaseAdapterConfiguration, com.bytedance.msdk.adapter.config.ITTAdapterConfiguration
    public void setThemeStatus(Map<String, Object> map) {
        super.setThemeStatus(map);
        if (map == null) {
            return;
        }
        Object obj = map.get(TTBaseAdapterConfiguration.TT_MSDK_THEME_STATUS);
        int iIntValue = obj instanceof Integer ? ((Integer) obj).intValue() : 0;
        if (iIntValue == 0 || iIntValue == 1) {
            TTAdSdk.getAdManager().setThemeStatus(iIntValue);
        }
    }
}
