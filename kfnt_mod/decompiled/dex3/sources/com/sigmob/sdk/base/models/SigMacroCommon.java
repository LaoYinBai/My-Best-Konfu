package com.sigmob.sdk.base.models;

import android.text.TextUtils;
import com.sigmob.logger.SigmobLog;
import com.sigmob.sdk.base.models.BaseMacroCommon;
import com.sigmob.sdk.common.Constants;
import com.sigmob.sdk.common.a;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class SigMacroCommon extends BaseMacroCommon implements Serializable {
    public static final String _ADSCENE_ = "_ADSCENE_";
    public static final String _AUTOCLICK_ = "_AUTOCLICK_";
    public static final String _BEGINTIME_ = "_BEGINTIME_";
    public static final String _BEHAVIOR_ = "_BEHAVIOR_";
    public static final String _CLICKAREA_ = "_CLICKAREA_";
    public static final String _CLICKID_ = "_CLICKID_";
    public static final String _CLICKSCENE_ = "_CLICKSCENE_";
    public static final String _COMPLETED_ = "_COMPLETED_";
    public static final String _DOWNX_ = "_DOWNX_";
    public static final String _DOWNY_ = "_DOWNY_";
    public static final String _ENDTIME_ = "_ENDTIME_";
    public static final String _FINALCLICK_ = "_FINALCLICK_";
    public static final String _HEIGHT_ = "_HEIGHT_";
    public static final String _IS_TRUNCATION_ = "_ISTRUNCATION_";
    public static final String _PLAYFIRSTFRAME_ = "_PLAYFIRSTFRAME_";
    public static final String _PLAYLASTFRAME_ = "_PLAYLASTFRAME_";
    public static final String _PROGRESS_ = "_PROGRESS_";
    public static final String _SCENE_ = "_SCENE_";
    public static final String _SETCLOSETIME_ = "_SETCLOSETIME_";
    public static final String _SHOWSKIPTIME_ = "_SHOWSKIPTIME_";
    public static final String _SLOTHEIGHT_ = "_SLOTHEIGHT_";
    public static final String _SLOTWIDTH_ = "_SLOTWIDTH_";
    public static final String _STATUS_ = "_STATUS_";
    public static final String _TYPE_ = "_TYPE_";
    public static final String _UPX_ = "_UPX_";
    public static final String _UPY_ = "_UPY_";
    public static final String _VIDEOTIME_ = "_VIDEOTIME_";
    public static final String _VMD5_ = "_VMD5_";
    public static final String _VURL_ = "_VURL_";
    public static final String _WIDTH_ = "_WIDTH_";
    private static final long serialVersionUID = 1;
    private Map<String, String> a = new HashMap();
    private Map<String, String> b = new HashMap();

    private static String a(String str) {
        try {
            switch (str) {
                case "_TYPE_":
                    return "1";
                case "_SCENE_":
                    return Constants.FAIL;
                case "_STATUS_":
                    return Constants.FAIL;
                case "_BEHAVIOR_":
                    return "2";
                case "_BEGINTIME_":
                    return Constants.FAIL;
                case "_SLOTWIDTH_":
                case "_WIDTH_":
                    return String.valueOf(a.n().aa().widthPixels);
                case "_SLOTHEIGHT_":
                case "_HEIGHT_":
                    return String.valueOf(a.n().aa().heightPixels);
                default:
                    return "unFind";
            }
        } catch (Throwable unused) {
            return "unFind";
        }
    }

    private String b(String str) {
        String str2 = this.b.get(str);
        SigmobLog.d("macroProcess() called with:[" + str + "][" + str2 + "]");
        if (!TextUtils.isEmpty(str2) && !str2.equals("unFind")) {
            return str2;
        }
        String str3 = this.a.get(str);
        SigmobLog.d("macroProcess() called with:[" + str + "][" + str3 + "]");
        if (TextUtils.isEmpty(str3) || str3.equals("unFind")) {
            return null;
        }
        return str3;
    }

    private String c(String str) {
        String strA = BaseMacroCommon.SigmobMacro.a(str);
        SigmobLog.d("macroProcess() called with:[" + str + "][" + strA + "]");
        if (!TextUtils.isEmpty(strA) && !strA.equals("unFind")) {
            return strA;
        }
        try {
            String strA2 = a(str);
            SigmobLog.d("macroProcess() called with: [" + str + "][" + strA2 + "]");
            if (TextUtils.isEmpty(strA2)) {
                return null;
            }
            if (strA2.equals("unFind")) {
                return null;
            }
            return strA2;
        } catch (Throwable th) {
            SigmobLog.e(th.getMessage());
            return null;
        }
    }

    public void addMarcoKey(String str, String str2) {
        this.a.put(str, str2);
    }

    @Override // com.sigmob.sdk.base.models.BaseMacroCommon
    public String macroProcess(String str) {
        Matcher matcher = Pattern.compile("_([A-Z,0-9])+_").matcher(str);
        try {
            SigmobLog.d("macroProcess() called with: origin url " + str);
            while (matcher.find()) {
                String strGroup = matcher.group();
                String strB = b(strGroup);
                if (TextUtils.isEmpty(strB) || strB.equals("unFind")) {
                    strB = c(strGroup);
                    if (TextUtils.isEmpty(strB) || strB.equals("unFind")) {
                    }
                }
                str = str.replaceAll(strGroup, strB);
            }
            SigmobLog.d("macroProcess() called with: final url " + str);
        } catch (Throwable th) {
            SigmobLog.e(th.getMessage());
        }
        return str;
    }

    public void setServerMacroMap(Map<String, String> map) {
        this.b = map;
    }
}
