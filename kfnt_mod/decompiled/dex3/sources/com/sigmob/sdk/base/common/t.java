package com.sigmob.sdk.base.common;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.os.Build;
import android.text.TextUtils;
import android.util.Base64;
import android.view.MotionEvent;
import com.sigmob.logger.SigmobLog;
import com.sigmob.sdk.base.models.BaseAdUnit;
import com.sigmob.sdk.base.models.LoadAdRequest;
import com.sigmob.sdk.common.Constants;
import com.sigmob.sdk.common.b.c;
import com.sigmob.sdk.common.mta.PointCategory;
import com.sigmob.sdk.common.mta.PointEntityBase;
import com.sigmob.sdk.common.mta.PointEntityClick;
import com.sigmob.sdk.common.mta.PointEntityCommon;
import com.sigmob.sdk.common.mta.PointEntitySigmob;
import com.sigmob.sdk.common.mta.PointEntitySigmobError;
import com.sigmob.sdk.common.mta.PointType;
import java.util.HashMap;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class t {

    public interface a {
        void a(Object obj);
    }

    public static void a(PackageInfo packageInfo) {
        PointEntityBase pointEntityBase = new PointEntityBase();
        pointEntityBase.setAc_type(PointType.SIGMOB_APP);
        HashMap map = new HashMap();
        try {
            map.put("app_name", String.valueOf(com.sigmob.sdk.common.a.n().af().getPackageManager().getApplicationLabel(packageInfo.applicationInfo)));
        } catch (Throwable unused) {
        }
        map.put("package_name", packageInfo.packageName);
        map.put(Constants.UPDATE, String.valueOf(packageInfo.lastUpdateTime));
        map.put("app_version", packageInfo.versionName);
        pointEntityBase.setOptions(map);
        pointEntityBase.commit();
    }

    public static void a(com.sigmob.sdk.base.a aVar, String str, BaseAdUnit baseAdUnit, final String str2, final String str3, final String str4) {
        a(aVar == null ? str : aVar.name().toLowerCase(), str, baseAdUnit, new a() { // from class: com.sigmob.sdk.base.common.t.5
            @Override // com.sigmob.sdk.base.common.t.a
            public void a(Object obj) {
                if (obj instanceof PointEntitySigmob) {
                    PointEntitySigmob pointEntitySigmob = (PointEntitySigmob) obj;
                    pointEntitySigmob.setIs_deeplink(str2);
                    pointEntitySigmob.setFinal_url(str3);
                    pointEntitySigmob.setCoordinate(str4);
                }
            }
        });
    }

    public static void a(e eVar, String str, BaseAdUnit baseAdUnit, com.sigmob.volley.i iVar, a aVar) {
        PointEntitySigmob pointEntitySigmob = new PointEntitySigmob();
        pointEntitySigmob.setAc_type(PointType.SIGMOB_REPORT_TRACKING);
        pointEntitySigmob.setUrl(str);
        if (eVar != null) {
            pointEntitySigmob.setRetry(eVar.e() != null ? "1" : Constants.FAIL);
            pointEntitySigmob.setCategory(eVar.m());
            pointEntitySigmob.setRequest_id(eVar.n());
            if (eVar.g() != 0) {
                pointEntitySigmob.setTimestamp(String.valueOf(eVar.g()));
            }
            pointEntitySigmob.setSource(eVar.a());
            if (iVar != null) {
                pointEntitySigmob.setResponse(iVar.b != null ? Base64.encodeToString(iVar.b, 2) : null);
                pointEntitySigmob.setHttp_code(String.valueOf(iVar.a));
                pointEntitySigmob.setTime_spend(String.valueOf(iVar.f));
                pointEntitySigmob.setContent_type(iVar.c.get("Content-Type"));
                pointEntitySigmob.setContent_length(iVar.c.get("Content-Length"));
            }
        }
        a(baseAdUnit, pointEntitySigmob);
        pointEntitySigmob.commit();
    }

    public static void a(e eVar, String str, BaseAdUnit baseAdUnit, final com.sigmob.volley.s sVar) {
        com.sigmob.volley.i iVar = sVar != null ? sVar.a : null;
        a(eVar, str, baseAdUnit, iVar, iVar == null ? new a() { // from class: com.sigmob.sdk.base.common.t.3
            @Override // com.sigmob.sdk.base.common.t.a
            public void a(Object obj) {
                if (obj instanceof PointEntitySigmob) {
                    PointEntitySigmob pointEntitySigmob = (PointEntitySigmob) obj;
                    pointEntitySigmob.setHttp_code("-1");
                    pointEntitySigmob.setTime_spend(sVar != null ? String.valueOf(sVar.a()) : Constants.FAIL);
                }
            }
        } : null);
    }

    public static void a(BaseAdUnit baseAdUnit, MotionEvent motionEvent, String str, boolean z) {
        long eventTime = motionEvent.getEventTime() - motionEvent.getDownTime();
        PointEntityClick pointEntityClick = new PointEntityClick();
        pointEntityClick.setAc_type(PointType.ANTI_SPAM_TOUCH);
        pointEntityClick.setCategory(str);
        pointEntityClick.setLocation(String.format("{x:%f,y:%f}", Float.valueOf(motionEvent.getRawX()), Float.valueOf(motionEvent.getRawY())));
        pointEntityClick.setClick_duration(String.valueOf(eventTime));
        pointEntityClick.setPressure(String.valueOf(motionEvent.getPressure()));
        pointEntityClick.setTouchSize(String.valueOf(motionEvent.getSize()));
        pointEntityClick.setTouchType(String.valueOf(motionEvent.getToolType(0)));
        pointEntityClick.setIs_valid_click(z ? "1" : Constants.FAIL);
        if (baseAdUnit != null) {
            pointEntityClick.setLoad_id(baseAdUnit.getLoad_id());
        }
        pointEntityClick.commit();
    }

    private static void a(BaseAdUnit baseAdUnit, PointEntitySigmob pointEntitySigmob) {
        if (baseAdUnit != null) {
            try {
                pointEntitySigmob.setAdtype(String.valueOf(baseAdUnit.getAd_type()));
                pointEntitySigmob.setCampaign_id(baseAdUnit.getCamp_id());
                pointEntitySigmob.setCreative_id(baseAdUnit.getCrid());
                pointEntitySigmob.setRequest_id(baseAdUnit.getRequestId());
                pointEntitySigmob.setPlacement_id(baseAdUnit.getadslot_id());
                pointEntitySigmob.setLoad_id(baseAdUnit.getLoad_id());
                pointEntitySigmob.setVid(baseAdUnit.getVid());
                pointEntitySigmob.setPlay_mode(String.valueOf(baseAdUnit.getPlayMode()));
                pointEntitySigmob.setCreative_type(String.valueOf(baseAdUnit.getCreativeType()));
                pointEntitySigmob.setPrice(baseAdUnit.getAd().settlement_price_enc);
                if (!TextUtils.isEmpty(baseAdUnit.getAd().product_id)) {
                    pointEntitySigmob.setProduct_id(baseAdUnit.getAd().product_id);
                }
                if (baseAdUnit.getAd_type() == 2) {
                    pointEntitySigmob.setTemplate_type(baseAdUnit.getMaterial().template_type.intValue());
                }
                if (TextUtils.isEmpty(pointEntitySigmob.getTarget_url())) {
                    pointEntitySigmob.setTarget_url(baseAdUnit.getLanding_page());
                }
                pointEntitySigmob.setAd_source_channel(baseAdUnit.getAd_source_channel());
            } catch (Throwable unused) {
            }
        }
    }

    public static void a(BaseAdUnit baseAdUnit, final String str) {
        a(PointCategory.TARGET_URL, (String) null, baseAdUnit, new a() { // from class: com.sigmob.sdk.base.common.t.1
            @Override // com.sigmob.sdk.base.common.t.a
            public void a(Object obj) {
                if (obj instanceof PointEntitySigmob) {
                    ((PointEntitySigmob) obj).setFinal_url(str);
                }
            }
        });
    }

    public static void a(final BaseAdUnit baseAdUnit, final String str, final PackageInfo packageInfo, final String str2) {
        c.a.b().a(new Runnable() { // from class: com.sigmob.sdk.base.common.t.4
            @Override // java.lang.Runnable
            public void run() {
                t.a(str, str2, baseAdUnit, new a() { // from class: com.sigmob.sdk.base.common.t.4.1
                    @Override // com.sigmob.sdk.base.common.t.a
                    public void a(Object obj) {
                        Context contextAf = com.sigmob.sdk.common.a.n().af();
                        if (obj instanceof PointEntitySigmob) {
                            PointEntitySigmob pointEntitySigmob = (PointEntitySigmob) obj;
                            HashMap map = new HashMap();
                            if (Build.VERSION.SDK_INT >= 26) {
                                try {
                                    map.put(Constants.ALLOW_INSTALL, contextAf.getPackageManager().canRequestPackageInstalls() ? "1" : Constants.FAIL);
                                } catch (Throwable th) {
                                    SigmobLog.e(th.getMessage());
                                }
                            }
                            if (packageInfo != null) {
                                try {
                                    map.put("app_name", contextAf.getPackageManager().getApplicationLabel(packageInfo.applicationInfo).toString());
                                } catch (Throwable unused) {
                                }
                                map.put("package_name", packageInfo.packageName);
                                map.put(Constants.UPDATE, String.valueOf(packageInfo.lastUpdateTime));
                                map.put("app_version", packageInfo.versionName);
                            }
                            pointEntitySigmob.setOptions(map);
                        }
                    }
                });
            }
        });
    }

    public static void a(com.sigmob.volley.toolbox.d dVar, BaseAdUnit baseAdUnit, String str, boolean z) {
        PointEntitySigmob pointEntitySigmob = new PointEntitySigmob();
        pointEntitySigmob.setAc_type(PointType.DOWNLOAD_TRACKING);
        pointEntitySigmob.setIssuccess(String.valueOf(dVar.g));
        pointEntitySigmob.setIscached(z ? "1" : Constants.FAIL);
        pointEntitySigmob.setDuration(String.valueOf(dVar.f));
        pointEntitySigmob.setFile_size(String.valueOf(dVar.e));
        pointEntitySigmob.setFile_name(Base64.encodeToString(dVar.a.getBytes(), 2));
        pointEntitySigmob.setError_message(str);
        a(baseAdUnit, pointEntitySigmob);
        pointEntitySigmob.commit();
    }

    public static void a(String str) {
        PointEntityCommon pointEntityCommon = new PointEntityCommon();
        pointEntityCommon.setAc_type(PointType.ANTI_SPAM_TOUCH);
        pointEntityCommon.setCategory(str);
        pointEntityCommon.setSha1(com.sigmob.sdk.common.a.n().u());
        pointEntityCommon.setMd5(com.sigmob.sdk.common.a.n().v());
        pointEntityCommon.commit();
    }

    public static void a(String str, int i, String str2, BaseAdUnit baseAdUnit) {
        a(str, i, str2, baseAdUnit, (a) null);
    }

    public static void a(String str, int i, String str2, BaseAdUnit baseAdUnit, a aVar) {
        PointEntitySigmobError pointEntitySigmobErrorSigmobError = PointEntitySigmobError.SigmobError(str, i, str2);
        if (aVar != null) {
            aVar.a(pointEntitySigmobErrorSigmobError);
        }
        a(baseAdUnit, pointEntitySigmobErrorSigmobError);
        pointEntitySigmobErrorSigmobError.commit();
    }

    public static void a(String str, BaseAdUnit baseAdUnit, a aVar) {
        PointEntitySigmob pointEntitySigmob = new PointEntitySigmob();
        pointEntitySigmob.setAc_type(str);
        a(baseAdUnit, pointEntitySigmob);
        if (aVar != null) {
            aVar.a(pointEntitySigmob);
        }
        pointEntitySigmob.commit();
    }

    public static void a(String str, LoadAdRequest loadAdRequest, a aVar) {
        PointEntitySigmob pointEntitySigmob = new PointEntitySigmob();
        pointEntitySigmob.setAc_type("1");
        if (loadAdRequest != null) {
            pointEntitySigmob.setAdtype(String.valueOf(loadAdRequest.getAdType()));
            pointEntitySigmob.setPlacement_id(loadAdRequest.getPlacementId());
        }
        pointEntitySigmob.setCategory(str);
        if (aVar != null) {
            aVar.a(pointEntitySigmob);
        }
        pointEntitySigmob.commit();
    }

    public static void a(String str, String str2, BaseAdUnit baseAdUnit) {
        a(str, str2, baseAdUnit, (a) null);
    }

    public static void a(String str, String str2, BaseAdUnit baseAdUnit, a aVar) {
        PointEntitySigmob pointEntitySigmobSigmobTracking = PointEntitySigmob.SigmobTracking(str, null, null);
        pointEntitySigmobSigmobTracking.setSub_category(str2);
        a(baseAdUnit, pointEntitySigmobSigmobTracking);
        if (aVar != null) {
            aVar.a(pointEntitySigmobSigmobTracking);
        }
        pointEntitySigmobSigmobTracking.commit();
    }

    public static void a(String str, String str2, BaseAdUnit baseAdUnit, final String str3, final String str4, final String str5) {
        a(str, str2, baseAdUnit, new a() { // from class: com.sigmob.sdk.base.common.t.6
            @Override // com.sigmob.sdk.base.common.t.a
            public void a(Object obj) {
                if (obj instanceof PointEntitySigmob) {
                    PointEntitySigmob pointEntitySigmob = (PointEntitySigmob) obj;
                    pointEntitySigmob.setIs_deeplink(str3);
                    pointEntitySigmob.setFinal_url(str4);
                    pointEntitySigmob.setCoordinate(str5);
                }
            }
        });
    }

    public static void a(String str, String str2, BaseAdUnit baseAdUnit, final String str3, final String str4, final String str5, final int i) {
        a(str, str2, baseAdUnit, new a() { // from class: com.sigmob.sdk.base.common.t.7
            @Override // com.sigmob.sdk.base.common.t.a
            public void a(Object obj) {
                if (obj instanceof PointEntitySigmob) {
                    PointEntitySigmob pointEntitySigmob = (PointEntitySigmob) obj;
                    pointEntitySigmob.setIs_deeplink(str3);
                    pointEntitySigmob.setTarget_url(str4);
                    pointEntitySigmob.setCoordinate(str5);
                    pointEntitySigmob.setVtime(String.format("%.2f", Float.valueOf(i / 1000.0f)));
                }
            }
        });
    }

    public static void b(String str, final String str2, BaseAdUnit baseAdUnit) {
        a(str, str2, baseAdUnit, new a() { // from class: com.sigmob.sdk.base.common.t.2
            @Override // com.sigmob.sdk.base.common.t.a
            public void a(Object obj) {
                if (obj instanceof PointEntitySigmob) {
                    ((PointEntitySigmob) obj).setSub_category(str2);
                }
            }
        });
    }
}
