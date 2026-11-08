package com.kwad.tachikoma;

import android.text.TextUtils;
import android.webkit.JavascriptInterface;
import com.kuaishou.tachikoma.api.TKContext;
import com.kwad.sdk.core.report.CommercialAction;
import com.kwad.sdk.core.report.i;
import com.kwad.sdk.core.webview.kwai.e;
import com.kwad.sdk.plugin.f;
import com.kwad.sdk.plugin.g;
import com.kwad.v8.V8Object;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class a {
    private final Map<String, com.kwad.sdk.core.webview.kwai.a> a = new ConcurrentHashMap();
    private com.kwad.sdk.core.webview.kwai.a b = new com.kwad.sdk.core.webview.kwai.d();
    private boolean c;
    private TKContext d;
    private String e;
    private String f;

    public a(TKContext tKContext, String str) {
        this.d = tKContext;
        this.f = str;
        g gVar = (g) f.a(g.class);
        if (gVar != null) {
            this.e = gVar.d();
        }
    }

    private void a(String str) {
        i.a("ad_client_error_log", new CommercialAction.TKPerformMsg().setRenderState(3).setErrorReason(str).setTemplateId(this.f).setVersionCode(this.e));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String str, String str2) {
        String str3;
        String str4;
        if (this.c) {
            str3 = "TKJSBridge";
            str4 = "callJS after destroy jsInterface, " + str2;
        } else {
            if (str != null) {
                com.kwad.sdk.core.d.a.c("TKJSBridge", "callJS callback:+ " + str + "--params: " + str2);
                try {
                    this.d.evaluateScript(str + "(" + JSONObject.quote(str2) + ")", null, null);
                    return;
                } catch (Throwable th) {
                    g gVar = (g) f.a(g.class);
                    if (gVar != null) {
                        gVar.c();
                    }
                    a(th.getMessage());
                    com.kwad.sdk.core.kwai.a.a(th);
                    com.kwad.sdk.core.d.a.a(th);
                    return;
                }
            }
            str3 = "TKJSBridge";
            str4 = "callJS callback is empty";
        }
        com.kwad.sdk.core.d.a.c(str3, str4);
    }

    public void a(com.kwad.sdk.core.webview.kwai.a aVar) {
        if (aVar == null || TextUtils.isEmpty(aVar.a())) {
            com.kwad.sdk.core.d.a.e("TKJSBridge", "handler and handler'key cannot be null");
            return;
        }
        if (this.a.containsKey(aVar.a())) {
            com.kwad.sdk.core.d.a.e("TKJSBridge", "cannot register handler again, handler: " + aVar.a());
        }
        this.a.put(aVar.a(), aVar);
    }

    @JavascriptInterface
    public void callAdBridge(V8Object v8Object) {
        String string = v8Object.getString("action");
        String string2 = v8Object.getString("data");
        final String string3 = v8Object.getString("callback");
        com.kwad.sdk.core.d.a.c("TKJSBridge", "callAdBridge == action " + string + " param: " + string2 + " callback" + string3);
        com.kwad.sdk.core.webview.kwai.a aVar = this.a.get(string);
        if (aVar == null) {
            a("Js Action not found");
            aVar = this.b;
        }
        if (aVar == null) {
            com.kwad.sdk.core.d.a.e("TKJSBridge", "bridgeHandler is null");
            return;
        }
        try {
            aVar.a(string2, string3 != null ? new com.kwad.sdk.core.webview.kwai.c() { // from class: com.kwad.tachikoma.a.1
                @Override // com.kwad.sdk.core.webview.kwai.c
                public void a(int i, String str) {
                    a.this.a(string3, new e(i, str).toJson().toString());
                }

                @Override // com.kwad.sdk.core.webview.kwai.c
                public void a(com.kwad.sdk.core.b bVar) {
                    a.this.a(string3, new com.kwad.sdk.core.webview.kwai.f(bVar).toJson().toString());
                }
            } : new com.kwad.sdk.core.webview.kwai.c() { // from class: com.kwad.tachikoma.a.2
                @Override // com.kwad.sdk.core.webview.kwai.c
                public void a(int i, String str) {
                }

                @Override // com.kwad.sdk.core.webview.kwai.c
                public void a(com.kwad.sdk.core.b bVar) {
                }
            });
        } catch (Throwable th) {
            a(th.getMessage());
            com.kwad.sdk.core.kwai.a.a(th);
        }
    }
}
