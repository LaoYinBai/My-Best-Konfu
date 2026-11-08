package com.ss.union.game.sdk.core.base.debug.automatic_detection.e;

import android.text.TextUtils;
import com.ss.union.game.sdk.common.net.CoreNetClient;
import com.ss.union.game.sdk.common.net.http.base.in.ACoreRequestPost;
import com.ss.union.game.sdk.common.net.http.base.in.ICoreNetCallback;
import com.ss.union.game.sdk.common.net.http.base.response.CoreNetResponse;
import com.ss.union.game.sdk.core.base.config.AppIdManager;
import com.ss.union.game.sdk.core.base.config.ConfigManager;
import com.ss.union.game.sdk.core.base.debug.automatic_detection.LGDetectionManager;
import com.ss.union.game.sdk.core.base.debug.automatic_detection.c.c;
import com.ss.union.game.sdk.core.base.debug.automatic_detection.constants.LGDetectionConstant;
import com.ss.union.game.sdk.core.base.debug.automatic_detection.in.ILGDetection;
import com.ss.union.game.sdk.core.base.utils.LogCoreUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class a implements ILGDetection {
    private static final String a = "LGDetectionImpl";
    private Map<LGDetectionConstant.DetectionType, c> b;
    private Map<String, Boolean> c;

    public static a a() {
        return C0071a.a;
    }

    /* renamed from: com.ss.union.game.sdk.core.base.debug.automatic_detection.e.a$a, reason: collision with other inner class name */
    private static class C0071a {
        static a a = new a();

        private C0071a() {
        }
    }

    private a() {
        this.b = new LinkedHashMap();
        this.c = new HashMap();
    }

    private List<c> b() {
        ArrayList arrayList = new ArrayList();
        Iterator<Map.Entry<LGDetectionConstant.DetectionType, c>> it = this.b.entrySet().iterator();
        while (it.hasNext()) {
            arrayList.add(it.next().getValue());
        }
        return arrayList;
    }

    @Override // com.ss.union.game.sdk.core.base.debug.automatic_detection.in.ILGDetection
    public void generateDetectionReport(final com.ss.union.game.sdk.core.base.debug.automatic_detection.b.a<com.ss.union.game.sdk.core.base.debug.automatic_detection.c.a> aVar) {
        if (g()) {
            try {
                f();
                com.ss.union.game.sdk.core.base.debug.automatic_detection.d.a.a();
                CoreNetClient.post(LGDetectionConstant.AutomaticDetection.URL_CREATE_REPORT).param("init_param", AppIdManager.secretAppId()).param("ad_rit", c()).param("detect_items", d()).api(new ICoreNetCallback<JSONObject, ACoreRequestPost>() { // from class: com.ss.union.game.sdk.core.base.debug.automatic_detection.e.a.1
                    @Override // com.ss.union.game.sdk.common.net.http.base.in.ICoreNetCallback
                    /* renamed from: a, reason: merged with bridge method [inline-methods] */
                    public void onNetError(ACoreRequestPost aCoreRequestPost, CoreNetResponse<JSONObject, ACoreRequestPost> coreNetResponse) {
                        super.onNetError(aCoreRequestPost, coreNetResponse);
                        if (aVar != null) {
                            aVar.a(coreNetResponse.code(), coreNetResponse.message());
                        }
                    }

                    @Override // com.ss.union.game.sdk.common.net.http.base.in.ICoreNetCallback
                    /* renamed from: b, reason: merged with bridge method [inline-methods] */
                    public void onNetSuccess(ACoreRequestPost aCoreRequestPost, CoreNetResponse<JSONObject, ACoreRequestPost> coreNetResponse) {
                        super.onNetSuccess(aCoreRequestPost, coreNetResponse);
                        if (aVar != null) {
                            com.ss.union.game.sdk.core.base.debug.automatic_detection.c.a aVar2 = new com.ss.union.game.sdk.core.base.debug.automatic_detection.c.a();
                            aVar2.a(coreNetResponse.data.optJSONObject("data").toString());
                            aVar.a(aVar2);
                        }
                    }
                });
            } catch (Exception e) {
                aVar.a(-202, e.getMessage());
            }
        }
    }

    @Override // com.ss.union.game.sdk.core.base.debug.automatic_detection.in.ILGDetection
    public void detection(LGDetectionConstant.DetectionType detectionType, String str, LGDetectionConstant.DetectionState detectionState) {
        if (g()) {
            if (this.b.size() == 0) {
                e();
            }
            if (this.b.containsKey(detectionType)) {
                c cVar = this.b.get(detectionType);
                if (cVar != null) {
                    if (!a(detectionType, str, cVar)) {
                        b(detectionType, str, cVar);
                    }
                    cVar.a(str, detectionState);
                    return;
                }
                LogCoreUtils.logAutomaticDetection("LGDetectionImpl 不包含当前检测类型");
                return;
            }
            LogCoreUtils.logAutomaticDetection("LGDetectionImpl 不包含当前的检测类型");
        }
    }

    private String c() throws JSONException {
        com.ss.union.game.sdk.core.base.debug.automatic_detection.c.b parameterByKey = LGDetectionManager.getDetectionParamsApi().getParameterByKey(LGDetectionConstant.Params.MEDIATION_REWARD_ID);
        com.ss.union.game.sdk.core.base.debug.automatic_detection.c.b parameterByKey2 = LGDetectionManager.getDetectionParamsApi().getParameterByKey(LGDetectionConstant.Params.MEDIATION_FULL_SCREEN_ID);
        com.ss.union.game.sdk.core.base.debug.automatic_detection.c.b parameterByKey3 = LGDetectionManager.getDetectionParamsApi().getParameterByKey(LGDetectionConstant.Params.PANGOLIN_SPLASH);
        JSONObject jSONObject = new JSONObject();
        if (parameterByKey != null) {
            try {
                jSONObject.putOpt("award_rits", parameterByKey.c());
            } catch (Exception e) {
                e.printStackTrace();
                return "";
            }
        }
        if (parameterByKey2 != null) {
            jSONObject.putOpt("interstitia_rits", parameterByKey2.c());
        }
        if (parameterByKey3 != null) {
            jSONObject.putOpt("splash_rits", parameterByKey3.c());
        }
        return jSONObject.toString();
    }

    private boolean a(LGDetectionConstant.DetectionType detectionType, String str, c cVar) {
        if (detectionType == LGDetectionConstant.DetectionType.AD) {
            for (Map.Entry<String, Boolean> entry : this.c.entrySet()) {
                String key = entry.getKey();
                Boolean value = entry.getValue();
                if (str.contains(key) && value.booleanValue()) {
                    return true;
                }
            }
            return false;
        }
        return cVar.d;
    }

    private String d() {
        List<c> listB = b();
        JSONArray jSONArray = new JSONArray();
        Iterator<c> it = listB.iterator();
        while (it.hasNext()) {
            jSONArray.put(it.next().c());
        }
        return jSONArray.toString();
    }

    private void e() {
        if (g()) {
            this.b.put(LGDetectionConstant.DetectionType.INIT, new c(LGDetectionConstant.DetectionType.INIT.getKey(), LGDetectionConstant.DetectionType.INIT.getValue()));
            this.b.put(LGDetectionConstant.DetectionType.AD, new c(LGDetectionConstant.DetectionType.AD.getKey(), LGDetectionConstant.DetectionType.AD.getValue()));
            this.b.put(LGDetectionConstant.DetectionType.APP_LOG, new c(LGDetectionConstant.DetectionType.APP_LOG.getKey(), LGDetectionConstant.DetectionType.APP_LOG.getValue()));
            this.b.put(LGDetectionConstant.DetectionType.PRIVACY, new c(LGDetectionConstant.DetectionType.PRIVACY.getKey(), LGDetectionConstant.DetectionType.PRIVACY.getValue()));
            this.b.put(LGDetectionConstant.DetectionType.ACCOUNT, new c(LGDetectionConstant.DetectionType.ACCOUNT.getKey(), LGDetectionConstant.DetectionType.ACCOUNT.getValue()));
        }
    }

    private void b(LGDetectionConstant.DetectionType detectionType, String str, c cVar) {
        switch (detectionType) {
            case INIT:
                cVar.a(LGDetectionConstant.InitItem.DETECTION_ID_PANGOLIN);
                cVar.a(LGDetectionConstant.InitItem.DETECTION_ID_APP_LOG);
                if (com.ss.union.game.sdk.core.base.component.a.b()) {
                    cVar.a(LGDetectionConstant.InitItem.DETECTION_ID_DOU_YIN);
                    break;
                }
                break;
            case AD:
                if (str.contains("激励视频")) {
                    cVar.a(LGDetectionConstant.AdItem.DETECTION_ID_REWARD_LOAD);
                    cVar.a(LGDetectionConstant.AdItem.DETECTION_ID_REWARD_LOAD_SUCCESS);
                    cVar.a(LGDetectionConstant.AdItem.DETECTION_ID_REWARD_SHOW);
                    cVar.a(LGDetectionConstant.AdItem.DETECTION_ID_REWARD_SHOW_SUCCESS);
                    cVar.a(LGDetectionConstant.AdItem.DETECTION_ID_REWARD_CLOSE);
                    this.c.put("激励视频", true);
                    break;
                } else if (str.contains("全屏视频")) {
                    cVar.a(LGDetectionConstant.AdItem.DETECTION_ID_FULL_SCREEN_LOAD);
                    cVar.a(LGDetectionConstant.AdItem.DETECTION_ID_FULL_SCREEN_LOAD_SUCCESS);
                    cVar.a(LGDetectionConstant.AdItem.DETECTION_ID_FULL_SCREEN_SHOW);
                    cVar.a(LGDetectionConstant.AdItem.DETECTION_ID_FULL_SCREEN_SHOW_SUCCESS);
                    cVar.a(LGDetectionConstant.AdItem.DETECTION_ID_FULL_SCREEN_CLOSE);
                    this.c.put("全屏视频", true);
                    break;
                } else if (str.contains("开屏广告")) {
                    cVar.a(LGDetectionConstant.AdItem.DETECTION_ID_SPLASH_LOAD);
                    cVar.a(LGDetectionConstant.AdItem.DETECTION_ID_SPLASH_LOAD_SUCCESS);
                    this.c.put("开屏广告", true);
                    break;
                } else {
                    LogCoreUtils.logAutomaticDetection("LGDetectionImpl 不包含当前的广告类型");
                    break;
                }
            case APP_LOG:
                cVar.a(LGDetectionConstant.AppLogItem.DETECTION_ID_DID_RETURN);
                cVar.a(LGDetectionConstant.AppLogItem.DETECTION_ID_SCHEME);
                break;
            case PRIVACY:
                cVar.a(LGDetectionConstant.PrivacyItem.DETECTION_ID_BASIC_INFO);
                cVar.a(LGDetectionConstant.PrivacyItem.DETECTION_ID_WINDOW_INVOKE);
                cVar.a(LGDetectionConstant.PrivacyItem.DETECTION_ID_WINDOW_CALLBACK);
                break;
            case ACCOUNT:
                cVar.a(LGDetectionConstant.AccountItem.DETECTION_ID_INVOKE_LOGIN);
                cVar.a(LGDetectionConstant.AccountItem.DETECTION_ID_LOGIN_SUCCESS);
                cVar.a(LGDetectionConstant.AccountItem.DETECTION_ID_INVOKE_SWITCH);
                cVar.a(LGDetectionConstant.AccountItem.DETECTION_ID_SWITCH_SUCCESS);
                cVar.a(LGDetectionConstant.AccountItem.DETECTION_ID_VISITOR_BIND);
                cVar.a(LGDetectionConstant.AccountItem.DETECTION_ID_VISITOR_BIND_SUCCESS);
                break;
            default:
                LogCoreUtils.logAutomaticDetection("LGDetectionImpl 不包含当前的检测类型");
                break;
        }
    }

    private void f() throws Exception {
        if (g()) {
            detection(LGDetectionConstant.DetectionType.APP_LOG, LGDetectionConstant.AppLogItem.DETECTION_ID_SCHEME, AppIdManager.appLogScheme().equals(ConfigManager.AppConfig.appLogSchema()) ? LGDetectionConstant.DetectionState.PASS : LGDetectionConstant.DetectionState.FAIL);
            detection(LGDetectionConstant.DetectionType.PRIVACY, LGDetectionConstant.PrivacyItem.DETECTION_ID_BASIC_INFO, TextUtils.isEmpty(ConfigManager.AppConfig.appName()) ? LGDetectionConstant.DetectionState.FAIL : LGDetectionConstant.DetectionState.PASS);
        }
    }

    private boolean g() {
        return ConfigManager.AppConfig.isDebug();
    }
}
