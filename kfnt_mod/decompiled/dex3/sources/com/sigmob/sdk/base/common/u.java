package com.sigmob.sdk.base.common;

import android.content.Context;
import com.sigmob.logger.SigmobLog;
import com.sigmob.sdk.base.common.t;
import com.sigmob.sdk.base.models.BaseAdUnit;
import com.sigmob.sdk.base.models.SigMacroCommon;
import com.sigmob.sdk.common.Constants;
import com.sigmob.sdk.common.mta.PointCategory;
import com.sigmob.sdk.common.mta.PointEntitySigmob;
import java.io.UnsupportedEncodingException;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
class u implements o {
    private boolean b;
    private int c;
    private int a = 0;
    private String d = "";
    private int e = 0;

    u() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String a(int i) {
        return i == 0 ? Constants.FAIL : String.valueOf(i / 1000);
    }

    private void a(BaseAdUnit baseAdUnit, String str, int i) {
        a(baseAdUnit, str, i, null);
    }

    private void a(final BaseAdUnit baseAdUnit, final String str, final int i, String str2) {
        t.a(str, str2, baseAdUnit, new t.a() { // from class: com.sigmob.sdk.base.common.u.1
            @Override // com.sigmob.sdk.base.common.t.a
            public void a(Object obj) {
                if (obj instanceof PointEntitySigmob) {
                    PointEntitySigmob pointEntitySigmob = (PointEntitySigmob) obj;
                    pointEntitySigmob.setVtime(u.this.b(u.this.a));
                    pointEntitySigmob.setSkip_show_time(u.this.b(u.this.c));
                    pointEntitySigmob.setCurrent_time(u.this.b(i));
                    pointEntitySigmob.setPlay_process(String.valueOf((i * 1.0d) / u.this.b()));
                    if (str.equals(PointCategory.START)) {
                        pointEntitySigmob.setAd_scene(u.this.d);
                        pointEntitySigmob.setBid_token(baseAdUnit.getBid_token());
                    }
                    pointEntitySigmob.setPlay_time(u.this.a(i));
                    pointEntitySigmob.setSet_close_time(String.format("%d", Integer.valueOf(u.this.e)));
                    pointEntitySigmob.setIs_truncation(u.this.a() ? "1" : Constants.FAIL);
                    pointEntitySigmob.setIs_force(u.this.b ? "1" : Constants.FAIL);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean a() {
        return this.e > 0 && this.e * 1000 < this.a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int b() {
        return (this.e <= 0 || this.e * 1000 >= this.a) ? this.a : this.e * 1000;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String b(int i) {
        return i == 0 ? Constants.FAIL : String.format("%.2f", Float.valueOf(i / 1000.0f));
    }

    @Override // com.sigmob.sdk.base.common.o
    public boolean a(Context context, BaseAdUnit baseAdUnit, String str) {
        this.d = str;
        return true;
    }

    @Override // com.sigmob.sdk.base.common.o
    public boolean a(BaseAdUnit baseAdUnit) {
        com.sigmob.sdk.base.a.c.a(baseAdUnit, a.AD_CLOSE);
        a(baseAdUnit, PointCategory.ENDCARD, b(), "close");
        return true;
    }

    @Override // com.sigmob.sdk.base.common.o
    public boolean a(BaseAdUnit baseAdUnit, int i, int i2) throws UnsupportedEncodingException {
        this.a = i;
        this.e = baseAdUnit.getEndTime();
        SigMacroCommon macroCommon = baseAdUnit.getMacroCommon();
        if (!(macroCommon instanceof SigMacroCommon)) {
            return true;
        }
        macroCommon.addMarcoKey(SigMacroCommon._VIDEOTIME_, a(this.a));
        return true;
    }

    @Override // com.sigmob.sdk.base.common.o
    public boolean a(BaseAdUnit baseAdUnit, a aVar, int i) {
        String str;
        String strAk;
        SigMacroCommon sigMacroCommon;
        String str2;
        String strValueOf;
        SigMacroCommon sigMacroCommon2;
        String str3;
        String strValueOf2;
        try {
            switch (aVar) {
                case AD_START:
                    a(baseAdUnit, PointCategory.START, i, null);
                    SigMacroCommon macroCommon = baseAdUnit.getMacroCommon();
                    if (macroCommon instanceof SigMacroCommon) {
                        macroCommon.addMarcoKey(SigMacroCommon._SETCLOSETIME_, String.valueOf(this.e));
                        macroCommon.addMarcoKey(SigMacroCommon._ENDTIME_, Constants.FAIL);
                        macroCommon.addMarcoKey(SigMacroCommon._VIDEOTIME_, a(this.a));
                        macroCommon.addMarcoKey(SigMacroCommon._COMPLETED_, Constants.FAIL);
                        macroCommon.addMarcoKey(SigMacroCommon._PLAYFIRSTFRAME_, Constants.FAIL);
                        macroCommon.addMarcoKey(SigMacroCommon._IS_TRUNCATION_, a() ? "1" : Constants.FAIL);
                    }
                    aVar = a.AD_START;
                    com.sigmob.sdk.base.a.c.a(baseAdUnit, aVar);
                    break;
                case AD_SKIP:
                    a(baseAdUnit, PointCategory.SKIP, i);
                    SigMacroCommon macroCommon2 = baseAdUnit.getMacroCommon();
                    if (macroCommon2 instanceof SigMacroCommon) {
                        macroCommon2.addMarcoKey(SigMacroCommon._ENDTIME_, a(i));
                        if (this.a <= 0 || i <= 0) {
                            macroCommon2.addMarcoKey(SigMacroCommon._PROGRESS_, Constants.FAIL);
                        } else {
                            macroCommon2.addMarcoKey(SigMacroCommon._PROGRESS_, String.valueOf((i * 100) / b()));
                        }
                    }
                    aVar = a.AD_SKIP;
                    com.sigmob.sdk.base.a.c.a(baseAdUnit, aVar);
                    break;
                case AD_CLOSE_CARD_SHOW:
                    if (i == 0) {
                        i = b();
                    }
                    str = PointCategory.CLOSECARD;
                    strAk = PointCategory.SHOW;
                    a(baseAdUnit, str, i, strAk);
                    break;
                case AD_CLOSE_CARD_CLOSE:
                    if (i == 0) {
                        i = b();
                    }
                    str = PointCategory.CLOSECARD;
                    strAk = "close";
                    a(baseAdUnit, str, i, strAk);
                    break;
                case AD_FOUR_ELEMENTS_SHOW:
                    if (i == 0) {
                        i = b();
                    }
                    str = PointCategory.FOURELEMENTS;
                    strAk = PointCategory.SHOW;
                    a(baseAdUnit, str, i, strAk);
                    break;
                case AD_FOUR_ELEMENTS_CLOSE:
                    if (i == 0) {
                        i = b();
                    }
                    str = PointCategory.FOURELEMENTS;
                    strAk = "close";
                    a(baseAdUnit, str, i, strAk);
                    break;
                case AD_SHOW:
                    if (i == 0) {
                        i = b();
                    }
                    a(baseAdUnit, PointCategory.ENDCARD, i, a.AD_SHOW.a());
                    SigMacroCommon macroCommon3 = baseAdUnit.getMacroCommon();
                    if (macroCommon3 instanceof SigMacroCommon) {
                        macroCommon3.addMarcoKey(SigMacroCommon._ENDTIME_, a(i));
                        if (this.a <= 0 || i <= 0) {
                            macroCommon3.addMarcoKey(SigMacroCommon._PROGRESS_, Constants.FAIL);
                        } else {
                            macroCommon3.addMarcoKey(SigMacroCommon._PROGRESS_, String.valueOf((i * 100) / b()));
                        }
                    }
                    aVar = a.AD_SHOW;
                    com.sigmob.sdk.base.a.c.a(baseAdUnit, aVar);
                    break;
                case AD_CLICK:
                    aVar = a.AD_CLICK;
                    com.sigmob.sdk.base.a.c.a(baseAdUnit, aVar);
                    break;
                case AD_COMPANION_CLICK:
                    if (i == 0) {
                        i = b();
                    }
                    SigMacroCommon macroCommon4 = baseAdUnit.getMacroCommon();
                    if (macroCommon4 instanceof SigMacroCommon) {
                        macroCommon4.addMarcoKey(SigMacroCommon._ENDTIME_, a(i));
                        if (this.a <= 0 || i <= 0) {
                            macroCommon4.addMarcoKey(SigMacroCommon._PROGRESS_, Constants.FAIL);
                        } else {
                            macroCommon4.addMarcoKey(SigMacroCommon._PROGRESS_, String.valueOf((i * 100) / b()));
                        }
                    }
                    aVar = a.AD_COMPANION_CLICK;
                    com.sigmob.sdk.base.a.c.a(baseAdUnit, aVar);
                    break;
                case AD_SHOW_SKIP:
                    SigMacroCommon macroCommon5 = baseAdUnit.getMacroCommon();
                    if (macroCommon5 instanceof SigMacroCommon) {
                        macroCommon5.addMarcoKey(SigMacroCommon._SHOWSKIPTIME_, a(i));
                        if (this.a <= 0 || i <= 0) {
                            sigMacroCommon = macroCommon5;
                            str2 = SigMacroCommon._PROGRESS_;
                            strValueOf = Constants.FAIL;
                        } else {
                            sigMacroCommon = macroCommon5;
                            str2 = SigMacroCommon._PROGRESS_;
                            strValueOf = String.valueOf((i * 100) / b());
                        }
                        sigMacroCommon.addMarcoKey(str2, strValueOf);
                        macroCommon5.addMarcoKey(SigMacroCommon._ENDTIME_, a(i));
                        macroCommon5.addMarcoKey(SigMacroCommon._VIDEOTIME_, a(this.a));
                    }
                    aVar = a.AD_SHOW_SKIP;
                    com.sigmob.sdk.base.a.c.a(baseAdUnit, aVar);
                    break;
                case AD_COMPLETE:
                    if (i == 0) {
                        i = b();
                    }
                    a(baseAdUnit, PointCategory.COMPLETE, i);
                    SigMacroCommon macroCommon6 = baseAdUnit.getMacroCommon();
                    if (macroCommon6 instanceof SigMacroCommon) {
                        macroCommon6.addMarcoKey(SigMacroCommon._ENDTIME_, a(i));
                        macroCommon6.addMarcoKey(SigMacroCommon._PROGRESS_, "100");
                    }
                    aVar = a.AD_COMPLETE;
                    com.sigmob.sdk.base.a.c.a(baseAdUnit, aVar);
                    break;
                case AD_REWARD:
                case AD_FINISH:
                    if (i == 0) {
                        i = b();
                    }
                    a(baseAdUnit, PointCategory.FINISH, i);
                    SigMacroCommon macroCommon7 = baseAdUnit.getMacroCommon();
                    if (macroCommon7 instanceof SigMacroCommon) {
                        if (this.a <= 0 || i <= 0) {
                            sigMacroCommon2 = macroCommon7;
                            str3 = SigMacroCommon._PROGRESS_;
                            strValueOf2 = Constants.FAIL;
                        } else {
                            sigMacroCommon2 = macroCommon7;
                            str3 = SigMacroCommon._PROGRESS_;
                            strValueOf2 = String.valueOf((i * 100) / b());
                        }
                        sigMacroCommon2.addMarcoKey(str3, strValueOf2);
                        macroCommon7.addMarcoKey(SigMacroCommon._COMPLETED_, "1");
                        macroCommon7.addMarcoKey(SigMacroCommon._PLAYLASTFRAME_, "1");
                        macroCommon7.addMarcoKey(SigMacroCommon._ENDTIME_, a(i));
                    }
                    aVar = a.AD_FINISH;
                    com.sigmob.sdk.base.a.c.a(baseAdUnit, aVar);
                    break;
                case AD_VCLOSE:
                    break;
                case AD_MUTE:
                    str = PointCategory.SILENT;
                    strAk = "1";
                    a(baseAdUnit, str, i, strAk);
                    break;
                case AD_UNMUTE:
                    str = PointCategory.SILENT;
                    strAk = Constants.FAIL;
                    a(baseAdUnit, str, i, strAk);
                    break;
                case AD_ROTATION:
                    if (com.sigmob.sdk.common.a.n() != null) {
                        str = PointCategory.SCREENSWITCH;
                        strAk = com.sigmob.sdk.common.a.n().ak();
                        a(baseAdUnit, str, i, strAk);
                        break;
                    }
                    break;
                case AD_VIDEO_START:
                    str = PointCategory.PLAY;
                    strAk = Constants.FAIL;
                    a(baseAdUnit, str, i, strAk);
                    break;
                case AD_PLAY_QUARTER:
                    a(baseAdUnit, PointCategory.PLAY, i, "0.25");
                    SigMacroCommon macroCommon8 = baseAdUnit.getMacroCommon();
                    if (macroCommon8 instanceof SigMacroCommon) {
                        macroCommon8.addMarcoKey(SigMacroCommon._ENDTIME_, a(i));
                        macroCommon8.addMarcoKey(SigMacroCommon._PROGRESS_, "25");
                    }
                    aVar = a.AD_PLAY_QUARTER;
                    com.sigmob.sdk.base.a.c.a(baseAdUnit, aVar);
                    break;
                case AD_PLAY_TWO_QUARTERS:
                    a(baseAdUnit, PointCategory.PLAY, i, "0.50");
                    SigMacroCommon macroCommon9 = baseAdUnit.getMacroCommon();
                    if (macroCommon9 instanceof SigMacroCommon) {
                        macroCommon9.addMarcoKey(SigMacroCommon._ENDTIME_, a(i));
                        macroCommon9.addMarcoKey(SigMacroCommon._PROGRESS_, "50");
                    }
                    aVar = a.AD_PLAY_TWO_QUARTERS;
                    com.sigmob.sdk.base.a.c.a(baseAdUnit, aVar);
                    break;
                case AD_PLAY_THREE_QUARTERS:
                    a(baseAdUnit, PointCategory.PLAY, i, "0.75");
                    SigMacroCommon macroCommon10 = baseAdUnit.getMacroCommon();
                    if (macroCommon10 instanceof SigMacroCommon) {
                        macroCommon10.addMarcoKey(SigMacroCommon._ENDTIME_, a(i));
                        macroCommon10.addMarcoKey(SigMacroCommon._PROGRESS_, "75");
                    }
                    aVar = a.AD_PLAY_THREE_QUARTERS;
                    com.sigmob.sdk.base.a.c.a(baseAdUnit, aVar);
                    break;
                case AD_PLAY_COMPLETE:
                    a(baseAdUnit, PointCategory.PLAY, i, "0.85");
                    SigMacroCommon macroCommon11 = baseAdUnit.getMacroCommon();
                    if (macroCommon11 instanceof SigMacroCommon) {
                        macroCommon11.addMarcoKey(SigMacroCommon._ENDTIME_, a(i));
                        macroCommon11.addMarcoKey(SigMacroCommon._PROGRESS_, "85");
                    }
                    aVar = a.AD_PLAY_COMPLETE;
                    com.sigmob.sdk.base.a.c.a(baseAdUnit, aVar);
                    break;
                case AD_CLICK_SKIP:
                    a(baseAdUnit, aVar.a(), i);
                    break;
                default:
                    a(baseAdUnit, aVar.a(), i);
                    com.sigmob.sdk.base.a.c.a(baseAdUnit, aVar);
                    break;
            }
        } catch (Throwable th) {
            SigmobLog.e("recordDisplayEvent ", th);
            return true;
        }
        return true;
    }

    @Override // com.sigmob.sdk.base.common.o
    public boolean a(BaseAdUnit baseAdUnit, boolean z, int i) {
        this.b = z;
        this.c = i;
        return true;
    }
}
