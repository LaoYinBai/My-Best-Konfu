package com.tachikoma.core.component.listview;

import android.content.Context;
import android.database.DataSetObserver;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import androidx.core.util.Pair;
import com.kwad.sdk.R;
import com.kwad.v8.V8Object;
import com.tachikoma.annotation.TK_EXPORT_CLASS;
import com.tachikoma.core.bridge.TKJSContext;
import com.tachikoma.core.component.TKBase;
import com.tachikoma.core.component.anim.AnimationProperty;
import com.tachikoma.core.layout.TKYogaConfig;
import com.tachikoma.core.utility.V8Proxy;
import java.util.ArrayList;
import java.util.List;

@TK_EXPORT_CLASS
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class TKBaseAdapter extends BaseAdapter {
    private final TKJSContext mTKJSContext;
    private final V8Object mV8Adapter;
    private final List<V8Object> v8Objects = new ArrayList();

    final class ViewHolder {
        TKBase base;
        V8Object jsObj;

        ViewHolder() {
        }
    }

    TKBaseAdapter(Context context, List<Object> list) {
        this.mV8Adapter = ((V8Object) list.get(list.size() - 1)).twin();
        this.mTKJSContext = V8Proxy.getTKContext(list);
    }

    public View convertView(View view, int i) {
        V8Proxy.executeJsFunction(this.mV8Adapter, this.mTKJSContext, "convertView", ((ViewHolder) view.getTag(R.id.holder)).jsObj, Integer.valueOf(i));
        return view;
    }

    public List<V8Object> getAssociatV8Objects() {
        return this.v8Objects;
    }

    @Override // android.widget.Adapter
    public int getCount() {
        return V8Proxy.executeIntegerFunction(this.mV8Adapter, this.mTKJSContext, "getCount", new int[0]);
    }

    @Override // android.widget.Adapter
    public Object getItem(int i) {
        return null;
    }

    @Override // android.widget.Adapter
    public long getItemId(int i) {
        return V8Proxy.executeIntegerFunction(this.mV8Adapter, this.mTKJSContext, "getItemId", i);
    }

    @Override // android.widget.BaseAdapter, android.widget.Adapter
    public int getItemViewType(int i) {
        return V8Proxy.executeIntegerFunction(this.mV8Adapter, this.mTKJSContext, "getItemViewType", i);
    }

    public View getView(int i) {
        long jCurrentTimeMillis = System.currentTimeMillis();
        Pair<V8Object, Object> pairExecuteJsFunction = V8Proxy.executeJsFunction(this.mV8Adapter, this.mTKJSContext, "getView", Integer.valueOf(i));
        if (pairExecuteJsFunction == null) {
            return null;
        }
        V8Object v8Object = (V8Object) pairExecuteJsFunction.first;
        TKBase tKBase = (TKBase) pairExecuteJsFunction.second;
        Object obj = tKBase.style.get(AnimationProperty.WIDTH);
        Object obj2 = tKBase.style.get(AnimationProperty.HEIGHT);
        int number = obj != null ? (int) TKYogaConfig.formatNumber(0, obj) : -1;
        int number2 = obj2 != null ? (int) TKYogaConfig.formatNumber(0, obj2) : -2;
        View view = tKBase.getView();
        view.setLayoutParams(new ViewGroup.LayoutParams(number, number2));
        V8Object v8ObjectTwin = v8Object.twin();
        ViewHolder viewHolder = new ViewHolder();
        viewHolder.base = tKBase;
        viewHolder.jsObj = v8ObjectTwin;
        this.v8Objects.add(v8ObjectTwin);
        view.setTag(R.id.holder, viewHolder);
        Log.e("test1", "consumer time :: " + (System.currentTimeMillis() - jCurrentTimeMillis));
        return view;
    }

    @Override // android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        return view == null ? getView(i) : convertView(view, i);
    }

    @Override // android.widget.BaseAdapter, android.widget.Adapter
    public int getViewTypeCount() {
        return V8Proxy.executeIntegerFunction(this.mV8Adapter, this.mTKJSContext, "getViewTypeCount", new int[0]);
    }

    @Override // android.widget.BaseAdapter
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }

    @Override // android.widget.BaseAdapter
    public void notifyDataSetInvalidated() {
        super.notifyDataSetInvalidated();
    }

    @Override // android.widget.BaseAdapter, android.widget.Adapter
    public void registerDataSetObserver(DataSetObserver dataSetObserver) {
        super.registerDataSetObserver(dataSetObserver);
    }

    @Override // android.widget.BaseAdapter, android.widget.Adapter
    public void unregisterDataSetObserver(DataSetObserver dataSetObserver) {
        super.unregisterDataSetObserver(dataSetObserver);
    }
}
