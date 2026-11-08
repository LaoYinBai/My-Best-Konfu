package c.a.a.a.a;

import android.content.Intent;
import android.os.AsyncTask;
import com.bytedance.JProtect;
import org.json.JSONObject;

@JProtect
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class k extends AsyncTask<Void, Void, Void> {

    /* renamed from: a, reason: collision with root package name */
    private af f2105a;

    public k(af afVar) {
        this.f2105a = afVar;
    }

    public static void a(Intent intent, String str) {
        af afVarA = ai.a(intent, str);
        if (afVarA == null) {
            afVarA = ac.a(intent, str);
        }
        if (afVarA != null) {
            new k(afVarA).execute(new Void[0]);
        }
    }

    public static void a(String str, String str2) {
        ac acVarA = ac.a(str, str2);
        if (acVarA != null) {
            new k(acVarA).execute(new Void[0]);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public Void doInBackground(Void... voidArr) {
        a(this.f2105a.b(), this.f2105a.a());
        return null;
    }

    public void a(int i, JSONObject jSONObject) {
        e eVar = h.f2103a;
        if (eVar != null) {
            eVar.a(i, jSONObject);
        }
    }
}
