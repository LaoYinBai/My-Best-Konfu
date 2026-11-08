package b.b.a.k;

import android.text.TextUtils;
import java.io.File;
import java.util.ArrayList;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class f {

    /* renamed from: a, reason: collision with root package name */
    public final ArrayList<String> f1128a = new ArrayList<>();

    public final void a(String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        StringBuilder sbA = b.a.a.a.a.a(str);
        sbA.append(File.separator);
        sbA.append(str2);
        sbA.append(".dat");
        File file = new File(sbA.toString());
        if (file.exists()) {
            file.delete();
        }
    }
}
