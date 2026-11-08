package com.tachikoma.core.component.listview;

import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;
import com.kwad.v8.V8Object;
import com.kwad.v8.V8Value;
import com.tachikoma.annotation.TK_EXPORT_CLASS;
import com.tachikoma.core.utility.V8Proxy;
import java.util.List;

@TK_EXPORT_CLASS
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class TKWaterListView extends TKRecyclerView {
    public TKWaterListView(Context context, List<Object> list) {
        super(context, list);
        this.mLayoutType = "stagger";
    }

    private void initSpanCount() {
        Object obj = this.mV8AssociatedObject.get("dataSource");
        if (obj instanceof V8Object) {
            V8Object v8Object = (V8Object) obj;
            if (V8Proxy.isV8Valid(v8Object)) {
                this.mSpanCount = ((Number) v8Object.executeJSFunction("numberOfColumns", new Object[]{this.mV8AssociatedObject})).intValue();
                V8Proxy.release((V8Value) obj);
            }
        }
    }

    @Override // com.tachikoma.core.component.listview.TKRecyclerView
    protected RecyclerView.LayoutManager createLayoutManager() {
        initSpanCount();
        return super.createLayoutManager();
    }
}
