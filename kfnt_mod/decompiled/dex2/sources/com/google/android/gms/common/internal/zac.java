package com.google.android.gms.common.internal;

import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.text.TextUtils;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.collection.SimpleArrayMap;
import androidx.core.os.ConfigurationCompat;
import com.google.android.gms.base.R;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.util.DeviceProperties;
import com.google.android.gms.common.wrappers.Wrappers;
import java.util.Locale;
import javax.annotation.concurrent.GuardedBy;

/* compiled from: com.google.android.gms:play-services-base@@18.1.0 */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public final class zac {

    @GuardedBy("sCache")
    private static final SimpleArrayMap zaa = new SimpleArrayMap();

    @Nullable
    @GuardedBy("sCache")
    private static Locale zab;

    public static String zaa(Context context) {
        String packageName = context.getPackageName();
        try {
            return Wrappers.packageManager(context).getApplicationLabel(packageName).toString();
        } catch (PackageManager.NameNotFoundException | NullPointerException unused) {
            String str = context.getApplicationInfo().name;
            return TextUtils.isEmpty(str) ? packageName : str;
        }
    }

    public static String zab(Context context) {
        return context.getResources().getString(R.string.common_google_play_services_notification_channel_name);
    }

    @NonNull
    public static String zac(Context context, int i) {
        Resources resources = context.getResources();
        switch (i) {
            case 1:
                return resources.getString(R.string.common_google_play_services_install_button);
            case 2:
                return resources.getString(R.string.common_google_play_services_update_button);
            case 3:
                return resources.getString(R.string.common_google_play_services_enable_button);
            default:
                return resources.getString(android.R.string.ok);
        }
    }

    @NonNull
    public static String zad(Context context, int i) {
        Resources resources = context.getResources();
        String strZaa = zaa(context);
        if (i == 5) {
            return zah(context, "common_google_play_services_invalid_account_text", strZaa);
        }
        if (i == 7) {
            return zah(context, "common_google_play_services_network_error_text", strZaa);
        }
        if (i == 9) {
            return resources.getString(R.string.common_google_play_services_unsupported_text, strZaa);
        }
        if (i == 20) {
            return zah(context, "common_google_play_services_restricted_profile_text", strZaa);
        }
        switch (i) {
            case 1:
                return resources.getString(R.string.common_google_play_services_install_text, strZaa);
            case 2:
                return DeviceProperties.isWearableWithoutPlayStore(context) ? resources.getString(R.string.common_google_play_services_wear_update_text) : resources.getString(R.string.common_google_play_services_update_text, strZaa);
            case 3:
                return resources.getString(R.string.common_google_play_services_enable_text, strZaa);
            default:
                switch (i) {
                    case 16:
                        return zah(context, "common_google_play_services_api_unavailable_text", strZaa);
                    case 17:
                        return zah(context, "common_google_play_services_sign_in_failed_text", strZaa);
                    case 18:
                        return resources.getString(R.string.common_google_play_services_updating_text, strZaa);
                    default:
                        return resources.getString(com.google.android.gms.common.R.string.common_google_play_services_unknown_issue, strZaa);
                }
        }
    }

    @NonNull
    public static String zaf(Context context, int i) {
        String strZai = i == 6 ? zai(context, "common_google_play_services_resolution_required_title") : zag(context, i);
        return strZai == null ? context.getResources().getString(R.string.common_google_play_services_notification_ticker) : strZai;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0078 A[RETURN] */
    @androidx.annotation.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.lang.String zag(android.content.Context r2, int r3) {
        /*
            android.content.res.Resources r0 = r2.getResources()
            r1 = 20
            if (r3 == r1) goto L8e
            r1 = 0
            switch(r3) {
                case 1: goto L87;
                case 2: goto L80;
                case 3: goto L79;
                case 4: goto L78;
                case 5: goto L6a;
                case 6: goto L78;
                case 7: goto L5c;
                case 8: goto L54;
                case 9: goto L4c;
                case 10: goto L44;
                case 11: goto L3c;
                default: goto Lc;
            }
        Lc:
            switch(r3) {
                case 16: goto L34;
                case 17: goto L26;
                case 18: goto L78;
                default: goto Lf;
            }
        Lf:
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r0 = "Unexpected error code "
            r2.append(r0)
            r2.append(r3)
            java.lang.String r3 = "GoogleApiAvailability"
            java.lang.String r2 = r2.toString()
            android.util.Log.e(r3, r2)
            return r1
        L26:
            java.lang.String r3 = "GoogleApiAvailability"
            java.lang.String r0 = "The specified account could not be signed in."
            android.util.Log.e(r3, r0)
            java.lang.String r3 = "common_google_play_services_sign_in_failed_title"
            java.lang.String r2 = zai(r2, r3)
            return r2
        L34:
            java.lang.String r2 = "GoogleApiAvailability"
            java.lang.String r3 = "One of the API components you attempted to connect to is not available."
            android.util.Log.e(r2, r3)
            return r1
        L3c:
            java.lang.String r2 = "GoogleApiAvailability"
            java.lang.String r3 = "The application is not licensed to the user."
            android.util.Log.e(r2, r3)
            return r1
        L44:
            java.lang.String r2 = "GoogleApiAvailability"
            java.lang.String r3 = "Developer error occurred. Please see logs for detailed information"
            android.util.Log.e(r2, r3)
            return r1
        L4c:
            java.lang.String r2 = "GoogleApiAvailability"
            java.lang.String r3 = "Google Play services is invalid. Cannot recover."
            android.util.Log.e(r2, r3)
            return r1
        L54:
            java.lang.String r2 = "GoogleApiAvailability"
            java.lang.String r3 = "Internal error occurred. Please see logs for detailed information"
            android.util.Log.e(r2, r3)
            return r1
        L5c:
            java.lang.String r3 = "GoogleApiAvailability"
            java.lang.String r0 = "Network error occurred. Please retry request later."
            android.util.Log.e(r3, r0)
            java.lang.String r3 = "common_google_play_services_network_error_title"
            java.lang.String r2 = zai(r2, r3)
            return r2
        L6a:
            java.lang.String r3 = "GoogleApiAvailability"
            java.lang.String r0 = "An invalid account was specified when connecting. Please provide a valid account."
            android.util.Log.e(r3, r0)
            java.lang.String r3 = "common_google_play_services_invalid_account_title"
            java.lang.String r2 = zai(r2, r3)
            return r2
        L78:
            return r1
        L79:
            int r2 = com.google.android.gms.base.R.string.common_google_play_services_enable_title
            java.lang.String r2 = r0.getString(r2)
            return r2
        L80:
            int r2 = com.google.android.gms.base.R.string.common_google_play_services_update_title
            java.lang.String r2 = r0.getString(r2)
            return r2
        L87:
            int r2 = com.google.android.gms.base.R.string.common_google_play_services_install_title
            java.lang.String r2 = r0.getString(r2)
            return r2
        L8e:
            java.lang.String r3 = "GoogleApiAvailability"
            java.lang.String r0 = "The current user profile is restricted and could not use authenticated features."
            android.util.Log.e(r3, r0)
            java.lang.String r3 = "common_google_play_services_restricted_profile_title"
            java.lang.String r2 = zai(r2, r3)
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.internal.zac.zag(android.content.Context, int):java.lang.String");
    }

    private static String zah(Context context, String str, String str2) throws Resources.NotFoundException {
        Resources resources = context.getResources();
        String strZai = zai(context, str);
        if (strZai == null) {
            strZai = resources.getString(com.google.android.gms.common.R.string.common_google_play_services_unknown_issue);
        }
        return String.format(resources.getConfiguration().locale, strZai, str2);
    }

    @Nullable
    private static String zai(Context context, String str) {
        synchronized (zaa) {
            Locale locale = ConfigurationCompat.getLocales(context.getResources().getConfiguration()).get(0);
            if (!locale.equals(zab)) {
                zaa.clear();
                zab = locale;
            }
            String str2 = (String) zaa.get(str);
            if (str2 != null) {
                return str2;
            }
            Resources remoteResource = GooglePlayServicesUtil.getRemoteResource(context);
            if (remoteResource == null) {
                return null;
            }
            int identifier = remoteResource.getIdentifier(str, "string", "com.google.android.gms");
            if (identifier == 0) {
                Log.w("GoogleApiAvailability", "Missing resource: " + str);
                return null;
            }
            String string = remoteResource.getString(identifier);
            if (!TextUtils.isEmpty(string)) {
                zaa.put(str, string);
                return string;
            }
            Log.w("GoogleApiAvailability", "Got empty resource: " + str);
            return null;
        }
    }

    @NonNull
    public static String zae(Context context, int i) {
        return (i == 6 || i == 19) ? zah(context, "common_google_play_services_resolution_required_text", zaa(context)) : zad(context, i);
    }
}
