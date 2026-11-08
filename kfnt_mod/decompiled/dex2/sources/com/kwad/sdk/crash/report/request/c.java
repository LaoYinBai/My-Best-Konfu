package com.kwad.sdk.crash.report.request;

import android.text.TextUtils;
import androidx.annotation.NonNull;
import com.kwad.sdk.crash.model.message.ExceptionMessage;
import com.kwad.sdk.crash.report.ReportEvent;
import com.kwad.sdk.crash.utils.d;
import java.util.Calendar;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class c {
    public static ReportEvent a(@NonNull ExceptionMessage exceptionMessage) {
        ReportEvent reportEvent = new ReportEvent();
        reportEvent.clientIncrementId = d.a();
        reportEvent.clientTimeStamp = exceptionMessage.mCurrentTimeStamp;
        reportEvent.sessionId = a(exceptionMessage.mCustomMsg);
        reportEvent.timeZone = Calendar.getInstance().getTimeZone().getID();
        reportEvent.statPackage = new ReportEvent.StatPackage();
        ReportEvent.ExceptionEvent exceptionEvent = new ReportEvent.ExceptionEvent();
        exceptionEvent.type = exceptionMessage.mExceptionType;
        exceptionEvent.message = exceptionMessage.toJson().toString();
        exceptionEvent.urlPackage = new ReportEvent.UrlPackage();
        reportEvent.statPackage.exceptionEvent = exceptionEvent;
        return reportEvent;
    }

    private static String a(String str) {
        if (TextUtils.isEmpty(str) || "Unknown".equals(str)) {
            return "Unknown";
        }
        try {
            String strOptString = new JSONObject(str).optString(com.kwad.sdk.crash.c.g);
            return !TextUtils.isEmpty(strOptString) ? strOptString : "Unknown";
        } catch (Exception e) {
            com.kwad.sdk.core.d.a.b(e);
            return "Unknown";
        }
    }
}
