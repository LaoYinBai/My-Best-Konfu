package com.sigmob.sdk.base.models;

import android.text.TextUtils;
import com.sigmob.logger.SigmobLog;
import com.sigmob.sdk.common.a;
import com.sigmob.sdk.common.utils.k;
import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class BaseMacroCommon implements Serializable {
    private static final long serialVersionUID = 1;

    public enum SigmobMacro {
        _ANDROIDID_,
        _ANDROIDIDMD5_,
        _IMEI_,
        _IMEIMD5_,
        _IMEI1_,
        _IMEI1MD5_,
        _IMEI2_,
        _IMEI2MD5_,
        _MAC_,
        _MACMD5_,
        _GAID_,
        _GAIDMD5_,
        _MC_,
        _COUNTRY_,
        _TIMESTAMP_,
        _OSVERSION_,
        _BUNDLEID_,
        _LANGUAGE_,
        _TIMEMILLIS_,
        _OAID_,
        _VAID_,
        _AAID_,
        _MSAUDID_;

        static String a(String str) {
            try {
                switch (valueOf(str)) {
                    case _MC_:
                        return a.n().T();
                    case _MAC_:
                        return a.h();
                    case _ANDROIDID_:
                        return a.n().t();
                    case _ANDROIDIDMD5_:
                        return k.a(a.n().t());
                    case _GAID_:
                        return a.n().w();
                    case _GAIDMD5_:
                        return k.a(a.n().w());
                    case _IMEI_:
                        return a.n().y();
                    case _IMEIMD5_:
                        return k.a(a.n().y());
                    case _IMEI1_:
                        return a.n().b(0);
                    case _IMEI1MD5_:
                        return k.a(a.n().b(0));
                    case _IMEI2_:
                        return a.n().b(1);
                    case _IMEI2MD5_:
                        return k.a(a.n().b(1));
                    case _MACMD5_:
                        return k.a(a.h());
                    case _COUNTRY_:
                        return a.n().X().getCountry();
                    case _BUNDLEID_:
                        return a.n().P();
                    case _LANGUAGE_:
                        return a.n().X().getDisplayLanguage();
                    case _OSVERSION_:
                        return a.c();
                    case _TIMESTAMP_:
                        return String.valueOf(System.currentTimeMillis() / 1000);
                    case _TIMEMILLIS_:
                        return String.valueOf(System.currentTimeMillis());
                    case _OAID_:
                        return a.n().Y();
                    default:
                        return "unFind";
                }
            } catch (Throwable unused) {
                return "unFind";
            }
        }
    }

    public String macroProcess(String str) {
        Matcher matcher = Pattern.compile("_([A-Z,0-9])+_").matcher(str);
        String strReplaceAll = str;
        while (matcher.find()) {
            try {
                String strGroup = matcher.group();
                String strA = SigmobMacro.a(strGroup);
                SigmobLog.d("macroProcess() called with: url = [" + str + "][" + strGroup + "][" + strA + "]");
                if (!TextUtils.isEmpty(strA) && !strA.equals("unFind")) {
                    strReplaceAll = strReplaceAll.replaceAll(strGroup, strA);
                }
            } catch (Throwable th) {
                SigmobLog.e(th.getMessage());
            }
        }
        return strReplaceAll;
    }
}
