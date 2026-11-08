package com.appsflyer;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.WorkerThread;
import com.appsflyer.share.Constants;
import com.appsflyer.share.LinkGenerator;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import javax.net.ssl.HttpsURLConnection;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class CreateOneLinkHttpTask extends OneLinkHttpTask {

    /* renamed from: ʻ, reason: contains not printable characters */
    private boolean f150;

    /* renamed from: ʽ, reason: contains not printable characters */
    private Context f151;

    /* renamed from: ˊ, reason: contains not printable characters */
    private String f152;

    /* renamed from: ˋ, reason: contains not printable characters */
    private Map<String, String> f153;

    /* renamed from: ˎ, reason: contains not printable characters */
    private String f154;

    /* renamed from: ˏ, reason: contains not printable characters */
    private ResponseListener f155;

    public interface ResponseListener {
        @WorkerThread
        void onResponse(String str);

        @WorkerThread
        void onResponseError(String str);
    }

    public CreateOneLinkHttpTask(@NonNull String str, @NonNull Map<String, String> map, AppsFlyerLib appsFlyerLib, @NonNull Context context, boolean z) {
        super(appsFlyerLib);
        this.f152 = "";
        this.f150 = false;
        this.f150 = z;
        this.f151 = context;
        if (this.f151 != null) {
            this.f152 = context.getPackageName();
        } else {
            AFLogger.afWarnLog("CreateOneLinkHttpTask: context can't be null");
        }
        this.f158 = str;
        this.f154 = "-1";
        this.f153 = map;
    }

    public void setListener(@NonNull ResponseListener responseListener) {
        this.f155 = responseListener;
    }

    @Override // com.appsflyer.OneLinkHttpTask
    /* renamed from: ˊ, reason: contains not printable characters */
    final void mo122(HttpsURLConnection httpsURLConnection) throws JSONException, IOException {
        if (this.f150) {
            return;
        }
        httpsURLConnection.setRequestMethod("POST");
        httpsURLConnection.setDoInput(true);
        httpsURLConnection.setDoOutput(true);
        httpsURLConnection.setUseCaches(false);
        JSONObject jSONObject = new JSONObject();
        JSONObject jSONObject2 = new JSONObject(this.f153);
        jSONObject.put("ttl", this.f154);
        jSONObject.put("data", jSONObject2);
        httpsURLConnection.connect();
        DataOutputStream dataOutputStream = new DataOutputStream(httpsURLConnection.getOutputStream());
        dataOutputStream.writeBytes(jSONObject.toString());
        dataOutputStream.flush();
        dataOutputStream.close();
    }

    @Override // com.appsflyer.OneLinkHttpTask
    /* renamed from: ˎ, reason: contains not printable characters */
    final String mo124() {
        StringBuilder sb = new StringBuilder();
        sb.append(ServerConfigHandler.getUrl("https://onelink.%s/shortlink-sdk/v1"));
        sb.append(Constants.URL_PATH_DELIMITER);
        sb.append(this.f158);
        return sb.toString();
    }

    @Override // com.appsflyer.OneLinkHttpTask
    /* renamed from: ˏ, reason: contains not printable characters */
    final void mo125(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            Iterator<String> itKeys = jSONObject.keys();
            while (itKeys.hasNext()) {
                this.f155.onResponse(jSONObject.optString(itKeys.next()));
            }
        } catch (JSONException e) {
            this.f155.onResponseError("Can't parse one link data");
            AFLogger.afErrorLog("Error while parsing to json ".concat(String.valueOf(str)), e);
        }
    }

    @Override // com.appsflyer.OneLinkHttpTask
    /* renamed from: ˋ, reason: contains not printable characters */
    final void mo123() {
        LinkGenerator linkGeneratorAddParameters = new LinkGenerator(Constants.USER_INVITE_LINK_TYPE).setBaseURL(this.f158, AppsFlyerProperties.getInstance().getString(AppsFlyerProperties.ONELINK_DOMAIN), this.f152).addParameter(Constants.URL_SITE_ID, this.f152).addParameters(this.f153);
        String string = AppsFlyerProperties.getInstance().getString(AppsFlyerProperties.APP_USER_ID);
        if (string != null) {
            linkGeneratorAddParameters.setReferrerCustomerId(string);
        }
        this.f155.onResponse(linkGeneratorAddParameters.generateLink());
    }
}
