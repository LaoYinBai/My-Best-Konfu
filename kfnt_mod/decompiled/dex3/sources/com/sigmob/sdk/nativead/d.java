package com.sigmob.sdk.nativead;

import android.content.Context;
import com.sigmob.sdk.base.common.t;
import com.sigmob.sdk.base.models.BaseAdUnit;
import com.sigmob.sdk.base.models.SigMacroCommon;
import com.sigmob.sdk.common.Constants;
import com.sigmob.sdk.common.mta.PointCategory;
import com.sigmob.sdk.common.mta.PointEntitySigmob;
import com.ss.android.downloadlib.constants.DownloadConstants;
import java.util.HashMap;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class d implements com.sigmob.sdk.base.common.o {
    Context a;

    private void a(BaseAdUnit baseAdUnit, String str, String str2, final HashMap<String, String> map) {
        com.sigmob.sdk.base.common.t.a(str, str2, baseAdUnit, new t.a() { // from class: com.sigmob.sdk.nativead.d.1
            @Override // com.sigmob.sdk.base.common.t.a
            public void a(Object obj) {
                if (obj instanceof PointEntitySigmob) {
                    ((PointEntitySigmob) obj).setOptions(map);
                }
            }
        });
    }

    public boolean a(Context context, BaseAdUnit baseAdUnit) {
        this.a = context.getApplicationContext();
        return true;
    }

    @Override // com.sigmob.sdk.base.common.o
    public boolean a(Context context, BaseAdUnit baseAdUnit, String str) {
        return false;
    }

    @Override // com.sigmob.sdk.base.common.o
    public boolean a(BaseAdUnit baseAdUnit) {
        this.a = null;
        return true;
    }

    @Override // com.sigmob.sdk.base.common.o
    public boolean a(BaseAdUnit baseAdUnit, int i, int i2) {
        return false;
    }

    @Override // com.sigmob.sdk.base.common.o
    public boolean a(BaseAdUnit baseAdUnit, com.sigmob.sdk.base.common.a aVar, int i) {
        HashMap<String, String> map;
        String str;
        HashMap<String, String> map2;
        String str2;
        String str3;
        String str4;
        try {
            map = null;
        } catch (Throwable unused) {
        }
        switch (aVar) {
            case AD_START:
                com.sigmob.sdk.base.a.c.a(baseAdUnit, com.sigmob.sdk.base.common.a.AD_START);
                str = PointCategory.START;
                a(baseAdUnit, str, null, null);
                return true;
            case AD_NATIVE_SHOW:
                com.sigmob.sdk.base.a.c.a(baseAdUnit, com.sigmob.sdk.base.common.a.AD_NATIVE_SHOW);
                str = PointCategory.AD_SHOW;
                a(baseAdUnit, str, null, null);
                return true;
            case AD_HIDE:
                com.sigmob.sdk.base.a.c.a(baseAdUnit, com.sigmob.sdk.base.common.a.AD_HIDE);
                str = PointCategory.AD_HIDE;
                a(baseAdUnit, str, null, null);
                return true;
            case AD_VIDEO_LINK:
                SigMacroCommon macroCommon = baseAdUnit.getMacroCommon();
                if (macroCommon instanceof SigMacroCommon) {
                    macroCommon.addMarcoKey(SigMacroCommon._VIDEOTIME_, String.valueOf(baseAdUnit.getVideoCommon().video_time));
                    macroCommon.addMarcoKey(SigMacroCommon._BEGINTIME_, String.valueOf(baseAdUnit.getVideoCommon().begin_time));
                    macroCommon.addMarcoKey(SigMacroCommon._ENDTIME_, String.valueOf(baseAdUnit.getVideoCommon().end_time));
                    macroCommon.addMarcoKey(SigMacroCommon._PLAYFIRSTFRAME_, String.valueOf(baseAdUnit.getVideoCommon().is_first));
                    macroCommon.addMarcoKey(SigMacroCommon._PLAYLASTFRAME_, String.valueOf(baseAdUnit.getVideoCommon().is_last));
                    macroCommon.addMarcoKey(SigMacroCommon._SCENE_, String.valueOf(baseAdUnit.getVideoCommon().scene));
                    macroCommon.addMarcoKey(SigMacroCommon._TYPE_, String.valueOf(baseAdUnit.getVideoCommon().type));
                    macroCommon.addMarcoKey(SigMacroCommon._BEHAVIOR_, String.valueOf(baseAdUnit.getVideoCommon().is_auto_play));
                    macroCommon.addMarcoKey(SigMacroCommon._STATUS_, String.valueOf(baseAdUnit.getVideoCommon().status));
                }
                com.sigmob.sdk.base.a.c.a(baseAdUnit, com.sigmob.sdk.base.common.a.AD_VIDEO_LINK, true);
                map2 = new HashMap<>();
                map2.put("video_time", String.valueOf(baseAdUnit.getVideoCommon().video_time));
                map2.put("begin_time", String.valueOf(baseAdUnit.getVideoCommon().begin_time));
                map2.put("end_time", String.valueOf(baseAdUnit.getVideoCommon().end_time));
                map2.put("is_first", String.valueOf(baseAdUnit.getVideoCommon().is_first));
                map2.put(DownloadConstants.EVENT_SCENE, String.valueOf(baseAdUnit.getVideoCommon().scene));
                map2.put("type", String.valueOf(baseAdUnit.getVideoCommon().type));
                map2.put("is_auto_play", String.valueOf(baseAdUnit.getVideoCommon().is_auto_play));
                map2.put("status", String.valueOf(baseAdUnit.getVideoCommon().status));
                str2 = PointCategory.VIDEO_LINK;
                a(baseAdUnit, str2, null, map2);
                return true;
            case AD_NATIVE_VIDEO_RESTART:
                SigMacroCommon macroCommon2 = baseAdUnit.getMacroCommon();
                if (macroCommon2 instanceof SigMacroCommon) {
                    macroCommon2.addMarcoKey(SigMacroCommon._VIDEOTIME_, String.valueOf(baseAdUnit.getVideoCommon().video_time));
                    macroCommon2.addMarcoKey(SigMacroCommon._BEGINTIME_, String.valueOf(baseAdUnit.getVideoCommon().begin_time));
                    macroCommon2.addMarcoKey(SigMacroCommon._ENDTIME_, String.valueOf(baseAdUnit.getVideoCommon().end_time));
                    macroCommon2.addMarcoKey(SigMacroCommon._PLAYFIRSTFRAME_, String.valueOf(baseAdUnit.getVideoCommon().is_first));
                    macroCommon2.addMarcoKey(SigMacroCommon._PLAYLASTFRAME_, String.valueOf(baseAdUnit.getVideoCommon().is_last));
                    macroCommon2.addMarcoKey(SigMacroCommon._SCENE_, String.valueOf(baseAdUnit.getVideoCommon().scene));
                    macroCommon2.addMarcoKey(SigMacroCommon._TYPE_, String.valueOf(baseAdUnit.getVideoCommon().type));
                    macroCommon2.addMarcoKey(SigMacroCommon._BEHAVIOR_, String.valueOf(baseAdUnit.getVideoCommon().is_auto_play));
                    macroCommon2.addMarcoKey(SigMacroCommon._STATUS_, String.valueOf(baseAdUnit.getVideoCommon().status));
                }
                com.sigmob.sdk.base.a.c.a(baseAdUnit, com.sigmob.sdk.base.common.a.AD_NATIVE_VIDEO_RESTART);
                map2 = new HashMap<>();
                map2.put("video_time", String.valueOf(baseAdUnit.getVideoCommon().video_time));
                map2.put("begin_time", String.valueOf(baseAdUnit.getVideoCommon().begin_time));
                map2.put("end_time", String.valueOf(baseAdUnit.getVideoCommon().end_time));
                map2.put("is_first", String.valueOf(baseAdUnit.getVideoCommon().is_first));
                map2.put(DownloadConstants.EVENT_SCENE, String.valueOf(baseAdUnit.getVideoCommon().scene));
                map2.put("type", String.valueOf(baseAdUnit.getVideoCommon().type));
                map2.put("is_auto_play", String.valueOf(baseAdUnit.getVideoCommon().is_auto_play));
                map2.put("status", String.valueOf(baseAdUnit.getVideoCommon().status));
                str2 = PointCategory.VIDEO_RESTART;
                a(baseAdUnit, str2, null, map2);
                return true;
            case AD_NATIVE_VIDEO_START:
                SigMacroCommon macroCommon3 = baseAdUnit.getMacroCommon();
                if (macroCommon3 instanceof SigMacroCommon) {
                    macroCommon3.addMarcoKey(SigMacroCommon._VIDEOTIME_, String.valueOf(baseAdUnit.getVideoCommon().video_time));
                    macroCommon3.addMarcoKey(SigMacroCommon._BEGINTIME_, String.valueOf(baseAdUnit.getVideoCommon().begin_time));
                    macroCommon3.addMarcoKey(SigMacroCommon._ENDTIME_, String.valueOf(baseAdUnit.getVideoCommon().end_time));
                    macroCommon3.addMarcoKey(SigMacroCommon._PLAYFIRSTFRAME_, String.valueOf(baseAdUnit.getVideoCommon().is_first));
                    macroCommon3.addMarcoKey(SigMacroCommon._PLAYLASTFRAME_, String.valueOf(baseAdUnit.getVideoCommon().is_last));
                    macroCommon3.addMarcoKey(SigMacroCommon._SCENE_, String.valueOf(baseAdUnit.getVideoCommon().scene));
                    macroCommon3.addMarcoKey(SigMacroCommon._TYPE_, String.valueOf(baseAdUnit.getVideoCommon().type));
                    macroCommon3.addMarcoKey(SigMacroCommon._BEHAVIOR_, String.valueOf(baseAdUnit.getVideoCommon().is_auto_play));
                    macroCommon3.addMarcoKey(SigMacroCommon._STATUS_, String.valueOf(baseAdUnit.getVideoCommon().status));
                }
                com.sigmob.sdk.base.a.c.a(baseAdUnit, com.sigmob.sdk.base.common.a.AD_NATIVE_VIDEO_START);
                map2 = new HashMap<>();
                map2.put("video_time", String.valueOf(baseAdUnit.getVideoCommon().video_time));
                map2.put("begin_time", String.valueOf(baseAdUnit.getVideoCommon().begin_time));
                map2.put("end_time", String.valueOf(baseAdUnit.getVideoCommon().end_time));
                map2.put("is_first", String.valueOf(baseAdUnit.getVideoCommon().is_first));
                map2.put(DownloadConstants.EVENT_SCENE, String.valueOf(baseAdUnit.getVideoCommon().scene));
                map2.put("type", String.valueOf(baseAdUnit.getVideoCommon().type));
                map2.put("is_auto_play", String.valueOf(baseAdUnit.getVideoCommon().is_auto_play));
                map2.put("status", String.valueOf(baseAdUnit.getVideoCommon().status));
                str2 = PointCategory.VIDEO_START;
                a(baseAdUnit, str2, null, map2);
                return true;
            case AD_NATIVE_VIDEO_PAUSE:
                SigMacroCommon macroCommon4 = baseAdUnit.getMacroCommon();
                if (macroCommon4 instanceof SigMacroCommon) {
                    macroCommon4.addMarcoKey(SigMacroCommon._VIDEOTIME_, String.valueOf(baseAdUnit.getVideoCommon().video_time));
                    macroCommon4.addMarcoKey(SigMacroCommon._BEGINTIME_, String.valueOf(baseAdUnit.getVideoCommon().begin_time));
                    macroCommon4.addMarcoKey(SigMacroCommon._ENDTIME_, String.valueOf(baseAdUnit.getVideoCommon().end_time));
                    macroCommon4.addMarcoKey(SigMacroCommon._PLAYFIRSTFRAME_, String.valueOf(baseAdUnit.getVideoCommon().is_first));
                    macroCommon4.addMarcoKey(SigMacroCommon._PLAYLASTFRAME_, String.valueOf(baseAdUnit.getVideoCommon().is_last));
                    macroCommon4.addMarcoKey(SigMacroCommon._SCENE_, String.valueOf(baseAdUnit.getVideoCommon().scene));
                    macroCommon4.addMarcoKey(SigMacroCommon._TYPE_, String.valueOf(baseAdUnit.getVideoCommon().type));
                    macroCommon4.addMarcoKey(SigMacroCommon._BEHAVIOR_, String.valueOf(baseAdUnit.getVideoCommon().is_auto_play));
                    macroCommon4.addMarcoKey(SigMacroCommon._STATUS_, String.valueOf(baseAdUnit.getVideoCommon().status));
                }
                com.sigmob.sdk.base.a.c.a(baseAdUnit, com.sigmob.sdk.base.common.a.AD_NATIVE_VIDEO_PAUSE, true);
                map2 = new HashMap<>();
                map2.put("video_time", String.valueOf(baseAdUnit.getVideoCommon().video_time));
                map2.put("begin_time", String.valueOf(baseAdUnit.getVideoCommon().begin_time));
                map2.put("end_time", String.valueOf(baseAdUnit.getVideoCommon().end_time));
                map2.put("is_first", String.valueOf(baseAdUnit.getVideoCommon().is_first));
                map2.put(DownloadConstants.EVENT_SCENE, String.valueOf(baseAdUnit.getVideoCommon().scene));
                map2.put("type", String.valueOf(baseAdUnit.getVideoCommon().type));
                map2.put("is_auto_play", String.valueOf(baseAdUnit.getVideoCommon().is_auto_play));
                map2.put("status", String.valueOf(baseAdUnit.getVideoCommon().status));
                str2 = PointCategory.VIDEO_PAUSE;
                a(baseAdUnit, str2, null, map2);
                return true;
            case AD_PLAY_QUARTER:
                com.sigmob.sdk.base.a.c.a(baseAdUnit, com.sigmob.sdk.base.common.a.AD_PLAY_QUARTER);
                str3 = PointCategory.PLAY;
                str4 = "0.25";
                a(baseAdUnit, str3, str4, map);
                return true;
            case AD_PLAY_TWO_QUARTERS:
                com.sigmob.sdk.base.a.c.a(baseAdUnit, com.sigmob.sdk.base.common.a.AD_PLAY_TWO_QUARTERS);
                str3 = PointCategory.PLAY;
                str4 = "0.50";
                a(baseAdUnit, str3, str4, map);
                return true;
            case AD_PLAY_THREE_QUARTERS:
                com.sigmob.sdk.base.a.c.a(baseAdUnit, com.sigmob.sdk.base.common.a.AD_PLAY_THREE_QUARTERS);
                str3 = PointCategory.PLAY;
                str4 = "0.75";
                a(baseAdUnit, str3, str4, map);
                return true;
            case AD_COMPLETE:
                SigMacroCommon macroCommon5 = baseAdUnit.getMacroCommon();
                if (macroCommon5 instanceof SigMacroCommon) {
                    macroCommon5.addMarcoKey(SigMacroCommon._VIDEOTIME_, String.valueOf(baseAdUnit.getVideoCommon().video_time));
                    macroCommon5.addMarcoKey(SigMacroCommon._BEGINTIME_, String.valueOf(baseAdUnit.getVideoCommon().begin_time));
                    macroCommon5.addMarcoKey(SigMacroCommon._ENDTIME_, String.valueOf(baseAdUnit.getVideoCommon().end_time));
                    macroCommon5.addMarcoKey(SigMacroCommon._PLAYFIRSTFRAME_, String.valueOf(baseAdUnit.getVideoCommon().is_first));
                    macroCommon5.addMarcoKey(SigMacroCommon._PLAYLASTFRAME_, String.valueOf(baseAdUnit.getVideoCommon().is_last));
                    macroCommon5.addMarcoKey(SigMacroCommon._SCENE_, String.valueOf(baseAdUnit.getVideoCommon().scene));
                    macroCommon5.addMarcoKey(SigMacroCommon._TYPE_, String.valueOf(baseAdUnit.getVideoCommon().type));
                    macroCommon5.addMarcoKey(SigMacroCommon._BEHAVIOR_, String.valueOf(baseAdUnit.getVideoCommon().is_auto_play));
                    macroCommon5.addMarcoKey(SigMacroCommon._STATUS_, String.valueOf(baseAdUnit.getVideoCommon().status));
                }
                com.sigmob.sdk.base.a.c.a(baseAdUnit, com.sigmob.sdk.base.common.a.AD_COMPLETE, true);
                map2 = new HashMap<>();
                map2.put("video_time", String.valueOf(baseAdUnit.getVideoCommon().video_time));
                map2.put("begin_time", String.valueOf(baseAdUnit.getVideoCommon().begin_time));
                map2.put("end_time", String.valueOf(baseAdUnit.getVideoCommon().end_time));
                map2.put("is_first", String.valueOf(baseAdUnit.getVideoCommon().is_first));
                map2.put(DownloadConstants.EVENT_SCENE, String.valueOf(baseAdUnit.getVideoCommon().scene));
                map2.put("type", String.valueOf(baseAdUnit.getVideoCommon().type));
                map2.put("is_auto_play", String.valueOf(baseAdUnit.getVideoCommon().is_auto_play));
                map2.put("status", String.valueOf(baseAdUnit.getVideoCommon().status));
                str2 = PointCategory.COMPLETE;
                a(baseAdUnit, str2, null, map2);
                return true;
            case AD_CLICK:
                String str5 = baseAdUnit.getClickCommon().is_final_click ? "1" : Constants.FAIL;
                SigMacroCommon macroCommon6 = baseAdUnit.getMacroCommon();
                if (macroCommon6 instanceof SigMacroCommon) {
                    macroCommon6.addMarcoKey(SigMacroCommon._CLICKAREA_, String.valueOf(baseAdUnit.getClickCommon().click_area));
                    macroCommon6.addMarcoKey(SigMacroCommon._CLICKSCENE_, String.valueOf(baseAdUnit.getClickCommon().click_scene));
                    macroCommon6.addMarcoKey(SigMacroCommon._AUTOCLICK_, String.valueOf(baseAdUnit.getClickCommon().auto_click));
                    macroCommon6.addMarcoKey(SigMacroCommon._FINALCLICK_, str5);
                    macroCommon6.addMarcoKey(SigMacroCommon._DOWNX_, String.valueOf(baseAdUnit.getClickCommon().down.a()));
                    macroCommon6.addMarcoKey(SigMacroCommon._DOWNY_, String.valueOf(baseAdUnit.getClickCommon().down.b()));
                    macroCommon6.addMarcoKey(SigMacroCommon._UPX_, String.valueOf(baseAdUnit.getClickCommon().up.b()));
                    macroCommon6.addMarcoKey(SigMacroCommon._UPY_, String.valueOf(baseAdUnit.getClickCommon().up.b()));
                }
                com.sigmob.sdk.base.a.c.a(baseAdUnit, com.sigmob.sdk.base.common.a.AD_CLICK);
                map = new HashMap<>();
                map.put("click_area", baseAdUnit.getClickCommon().click_area);
                map.put("click_scene", baseAdUnit.getClickCommon().click_scene);
                map.put("is_final_click", str5);
                map.put("coordinate", baseAdUnit.getClickCommon().getClickCoordinate());
                str3 = baseAdUnit.getClickCommon().click_scene;
                str4 = "click";
                a(baseAdUnit, str3, str4, map);
                return true;
            case AD_FOUR_ELEMENTS_SHOW:
                str3 = PointCategory.FOURELEMENTS;
                str4 = PointCategory.SHOW;
                a(baseAdUnit, str3, str4, map);
                return true;
            case AD_FOUR_ELEMENTS_CLOSE:
                str3 = PointCategory.FOURELEMENTS;
                str4 = "close";
                a(baseAdUnit, str3, str4, map);
                return true;
            default:
                return true;
        }
    }

    @Override // com.sigmob.sdk.base.common.o
    public boolean a(BaseAdUnit baseAdUnit, boolean z, int i) {
        return false;
    }
}
