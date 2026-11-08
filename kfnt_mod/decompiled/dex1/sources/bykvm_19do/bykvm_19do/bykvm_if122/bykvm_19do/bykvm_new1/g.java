package bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_new1;

import android.text.TextUtils;
import com.bytedance.msdk.adapter.util.Logger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class g extends d {
    private List<e> d;
    private String e;

    class a implements Comparator<e> {
        a(g gVar) {
        }

        @Override // java.util.Comparator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public int compare(e eVar, e eVar2) {
            long jE = eVar.e() - eVar2.e();
            if (jE == 0) {
                return 0;
            }
            return jE > 0 ? 1 : -1;
        }
    }

    public g(String str, String str2, int i, String str3) {
        super(str, str2, i);
        this.e = str3;
    }

    public void a(String str, int i) {
        for (e eVar : e()) {
            if (TextUtils.equals(eVar.d(), str)) {
                eVar.a(i);
                return;
            }
        }
    }

    public List<e> e() throws JSONException {
        g gVarH;
        List<e> list = this.d;
        if (list != null && list.size() != 0) {
            return this.d;
        }
        this.d = new ArrayList();
        if (this.e == null && (gVarH = i.b().h(this.f1892a)) != null) {
            this.e = gVarH.e;
        }
        if (TextUtils.isEmpty(this.e)) {
            return this.d;
        }
        try {
            JSONArray jSONArray = new JSONArray(this.e);
            for (int i = 0; i < jSONArray.length(); i++) {
                JSONObject jSONObject = jSONArray.getJSONObject(i);
                e eVar = new e();
                String string = jSONObject.getString("waterfall_show_rule_id");
                if (jSONObject != null && !TextUtils.isEmpty(string)) {
                    eVar.b(jSONObject.optInt("waterfall_show_freq"));
                    eVar.b(jSONObject.optLong("waterfall_show_span"));
                    eVar.a(string);
                    if (jSONObject.has("count")) {
                        eVar.a(jSONObject.optInt("count"));
                    }
                    if (jSONObject.has("effective_time")) {
                        eVar.a(jSONObject.optLong("effective_time"));
                    }
                    this.d.add(eVar);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (this.d.size() > 0) {
            Collections.sort(this.d, new a(this));
        }
        return this.d;
    }

    public String f() throws JSONException {
        try {
            JSONArray jSONArray = new JSONArray();
            for (e eVar : e()) {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("waterfall_show_freq", eVar.c());
                jSONObject.put("waterfall_show_span", eVar.e());
                jSONObject.put("waterfall_show_rule_id", eVar.d());
                jSONObject.put("count", eVar.a());
                jSONObject.put("effective_time", eVar.b());
                jSONArray.put(jSONObject);
            }
            this.e = jSONArray.toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return this.e;
    }

    public String g() throws JSONException {
        try {
            JSONArray jSONArray = new JSONArray(this.e);
            for (int i = 0; i < jSONArray.length(); i++) {
                JSONObject jSONObject = jSONArray.getJSONObject(i);
                jSONObject.put("count", 0);
                Logger.d("TTMediationSDK_IntervalFreqctlBean", "ruleId = " + jSONObject.optString("waterfall_show_rule_id"));
                jSONObject.put("effective_time", f.a(jSONObject.getLong("waterfall_show_span")));
            }
            this.e = jSONArray.toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return this.e;
    }

    public String toString() {
        return "BaseIntervalBean{waterfallId='" + this.f1892a + "', showRulesVersion='" + this.f1893b + "', timingMode=" + this.f1894c + "}IntervalFreqctlBean{freqctlRules=" + this.d + ", freqctlRulesJson='" + this.e + "'}";
    }

    public void a(String str, long j) {
        for (e eVar : e()) {
            if (TextUtils.equals(eVar.d(), str)) {
                eVar.a(j);
                return;
            }
        }
    }
}
