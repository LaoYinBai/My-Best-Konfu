package com.tachikoma.core.component.toast;

import android.content.Context;
import android.widget.Toast;
import com.tachikoma.annotation.TK_EXPORT_CLASS;
import com.tachikoma.core.Tachikoma;
import java.util.List;

@TK_EXPORT_CLASS
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class TKToast {
    public String content;

    public TKToast(Context context, List<Object> list) {
    }

    public void show() {
        Toast.makeText(Tachikoma.sApplication, this.content, 1).show();
    }
}
