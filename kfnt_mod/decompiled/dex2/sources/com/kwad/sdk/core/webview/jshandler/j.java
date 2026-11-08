package com.kwad.sdk.core.webview.jshandler;

import android.os.Handler;
import android.os.Looper;
import android.view.ViewGroup;
import android.webkit.WebView;
import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class j implements com.kwad.sdk.core.webview.kwai.a {
    private Handler a;
    private final WebView b;

    @Nullable
    private com.kwad.sdk.core.webview.kwai.c c;

    @Nullable
    private b d;
    private boolean e;

    public static final class a implements com.kwad.sdk.core.b {
        public int a;
        public int b;
        public int c;
        public int d;

        @Override // com.kwad.sdk.core.b
        public void parseJson(@Nullable JSONObject jSONObject) {
            if (jSONObject == null) {
                return;
            }
            this.a = jSONObject.optInt("height");
            this.b = jSONObject.optInt("leftMargin");
            this.c = jSONObject.optInt("rightMargin");
            this.d = jSONObject.optInt("bottomMargin");
        }

        @Override // com.kwad.sdk.core.b
        public JSONObject toJson() {
            JSONObject jSONObject = new JSONObject();
            com.kwad.sdk.utils.t.a(jSONObject, "height", this.a);
            com.kwad.sdk.utils.t.a(jSONObject, "leftMargin", this.b);
            com.kwad.sdk.utils.t.a(jSONObject, "rightMargin", this.c);
            com.kwad.sdk.utils.t.a(jSONObject, "bottomMargin", this.d);
            return jSONObject;
        }
    }

    public interface b {
        @MainThread
        void a(@NonNull a aVar);
    }

    public j(com.kwad.sdk.core.webview.a aVar, @Nullable b bVar) {
        this(aVar, bVar, true);
    }

    public j(com.kwad.sdk.core.webview.a aVar, @Nullable b bVar, boolean z) {
        this.e = true;
        this.a = new Handler(Looper.getMainLooper());
        this.b = aVar.e;
        this.d = bVar;
        this.e = z;
    }

    @Override // com.kwad.sdk.core.webview.kwai.a
    @NonNull
    public String a() {
        return "initKsAdFrame";
    }

    @Override // com.kwad.sdk.core.webview.kwai.a
    public void a(String str, @NonNull com.kwad.sdk.core.webview.kwai.c cVar) {
        this.c = cVar;
        try {
            JSONObject jSONObject = new JSONObject(str);
            final a aVar = new a();
            aVar.parseJson(jSONObject);
            this.a.post(new Runnable() { // from class: com.kwad.sdk.core.webview.jshandler.j.1
                @Override // java.lang.Runnable
                public void run() {
                    if (j.this.b != null && j.this.e) {
                        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) j.this.b.getLayoutParams();
                        marginLayoutParams.width = -1;
                        marginLayoutParams.height = aVar.a;
                        marginLayoutParams.leftMargin = aVar.b;
                        marginLayoutParams.rightMargin = aVar.c;
                        marginLayoutParams.bottomMargin = aVar.d;
                        j.this.b.setLayoutParams(marginLayoutParams);
                    }
                    if (j.this.d != null) {
                        j.this.d.a(aVar);
                    }
                }
            });
            this.a.post(new Runnable() { // from class: com.kwad.sdk.core.webview.jshandler.j.2
                @Override // java.lang.Runnable
                public void run() {
                    if (j.this.c != null) {
                        j.this.c.a(null);
                    }
                }
            });
        } catch (JSONException e) {
            com.kwad.sdk.core.d.a.a(e);
            cVar.a(-1, e.getMessage());
        }
    }

    @Override // com.kwad.sdk.core.webview.kwai.a
    public void b() {
        this.c = null;
        this.d = null;
        this.a.removeCallbacksAndMessages(null);
    }
}
