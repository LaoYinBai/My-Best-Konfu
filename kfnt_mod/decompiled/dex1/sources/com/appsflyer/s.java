package com.appsflyer;

import android.net.Uri;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import com.appsflyer.share.Constants;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.net.ssl.HttpsURLConnection;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
final class s extends OneLinkHttpTask {

    /* renamed from: ˎ, reason: contains not printable characters */
    private static List<String> f294 = Arrays.asList("onelink.me", "onelnk.com", "app.aflink.com");

    /* renamed from: ˊ, reason: contains not printable characters */
    private String f295;

    /* renamed from: ˋ, reason: contains not printable characters */
    private c f296;

    interface c {
        /* renamed from: ˊ */
        void mo115(Map<String, String> map);

        /* renamed from: ˎ */
        void mo116(String str);
    }

    s(Uri uri, AppsFlyerLib appsFlyerLib) {
        super(appsFlyerLib);
        if (TextUtils.isEmpty(uri.getHost()) || TextUtils.isEmpty(uri.getPath())) {
            return;
        }
        boolean z = false;
        Iterator<String> it = f294.iterator();
        while (it.hasNext()) {
            if (uri.getHost().contains(it.next())) {
                z = true;
            }
        }
        String[] strArrSplit = uri.getPath().split(Constants.URL_PATH_DELIMITER);
        if (z && strArrSplit.length == 3) {
            this.f158 = strArrSplit[1];
            this.f295 = strArrSplit[2];
        }
    }

    /* renamed from: ˏ, reason: contains not printable characters */
    final void m229(@NonNull c cVar) {
        this.f296 = cVar;
    }

    /* renamed from: ॱ, reason: contains not printable characters */
    final boolean m230() {
        return (TextUtils.isEmpty(this.f158) || TextUtils.isEmpty(this.f295)) ? false : true;
    }

    @Override // com.appsflyer.OneLinkHttpTask
    /* renamed from: ˊ */
    final void mo122(HttpsURLConnection httpsURLConnection) throws JSONException, IOException {
        httpsURLConnection.setRequestMethod("GET");
    }

    @Override // com.appsflyer.OneLinkHttpTask
    /* renamed from: ˎ */
    final String mo124() {
        StringBuilder sb = new StringBuilder();
        sb.append(ServerConfigHandler.getUrl("https://onelink.%s/shortlink-sdk/v1"));
        sb.append(Constants.URL_PATH_DELIMITER);
        sb.append(this.f158);
        sb.append("?id=");
        sb.append(this.f295);
        return sb.toString();
    }

    @Override // com.appsflyer.OneLinkHttpTask
    /* renamed from: ˏ */
    final void mo125(String str) {
        try {
            HashMap map = new HashMap();
            JSONObject jSONObject = new JSONObject(str);
            Iterator<String> itKeys = jSONObject.keys();
            while (itKeys.hasNext()) {
                String next = itKeys.next();
                map.put(next, jSONObject.optString(next));
            }
            this.f296.mo115(map);
        } catch (JSONException e) {
            this.f296.mo116("Can't parse one link data");
            AFLogger.afErrorLog("Error while parsing to json ".concat(String.valueOf(str)), e);
        }
    }

    @Override // com.appsflyer.OneLinkHttpTask
    /* renamed from: ˋ */
    final void mo123() {
        this.f296.mo116("Can't get one link data");
    }
}
