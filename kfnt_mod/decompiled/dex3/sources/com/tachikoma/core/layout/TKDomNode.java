package com.tachikoma.core.layout;

import android.os.SystemClock;
import android.view.View;
import androidx.annotation.Nullable;
import com.kwad.yoga.YogaNode;
import com.kwad.yoga.YogaNodeJNIFinalizer;
import com.tachikoma.core.component.TKBase;
import com.tachikoma.core.yoga.layout.YogaLayout;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class TKDomNode<T extends View> {
    private HashMap domAttr;
    private HashMap domStyle;
    private TKBase<T> linkView;
    public String nodeID;
    private YogaNode yogaNode;

    public TKDomNode(TKBase<T> tKBase, @Nullable String str) {
        this.linkView = tKBase;
        getYogaNode();
        this.domAttr = new HashMap();
        this.domStyle = new HashMap();
        this.nodeID = str == null ? createViewID() : str;
    }

    private void bindLayoutWithStyle(HashMap map) {
        if (map == null) {
            return;
        }
        YogaNode yogaNode = getYogaNode();
        TKYogaConfig.applyLayoutBackgroudParams(yogaNode, this.linkView);
        if (yogaNode != null) {
            for (Map.Entry entry : map.entrySet()) {
                String string = entry.getKey().toString();
                Object value = entry.getValue();
                if (!(value instanceof HashMap)) {
                    setLayoutNodeStyle(string, value, yogaNode);
                }
            }
        }
    }

    private void configureLayout(HashMap map) {
        if (map == null) {
            return;
        }
        bindLayoutWithStyle(map);
    }

    private String createViewID() {
        return "_" + String.valueOf(SystemClock.uptimeMillis());
    }

    public static <T extends View> TKDomNode nodeForView(View view, String[] strArr) {
        return new TKDomNode(null, strArr.length > 0 ? strArr[0] : null);
    }

    public static <T extends View> TKDomNode nodeForView(TKBase<T> tKBase, @Nullable List<Object> list) {
        return new TKDomNode(tKBase, null);
    }

    private void setLayoutNodeStyle(String str, Object obj, YogaNode yogaNode) {
        if (obj == null || str == null || yogaNode == null) {
            return;
        }
        TKYogaConfig.applyLayoutParams(yogaNode, str, obj);
    }

    private YogaLayout superview() {
        if (this.linkView.getView() instanceof YogaLayout) {
            return (YogaLayout) this.linkView.getView();
        }
        return null;
    }

    public void addSubview(View view, YogaNode yogaNode) {
        YogaLayout yogaLayoutSuperview;
        if (view == null || yogaNode == null || (yogaLayoutSuperview = superview()) == null) {
            return;
        }
        int childCount = this.yogaNode.getChildCount();
        yogaLayoutSuperview.addView(view, yogaNode);
        this.yogaNode.addChildAt(yogaNode, childCount);
    }

    public void addSubview(TKBase tKBase) {
        TKDomNode domNode;
        YogaLayout yogaLayoutSuperview;
        if (tKBase == null || (domNode = tKBase.getDomNode()) == null || (yogaLayoutSuperview = superview()) == null) {
            return;
        }
        int childCount = this.yogaNode.getChildCount();
        YogaNode yogaNode = domNode.getYogaNode();
        yogaLayoutSuperview.addView(tKBase.getView(), yogaNode);
        this.yogaNode.addChildAt(yogaNode, childCount);
    }

    public YogaNode getYogaNode() {
        if (this.yogaNode == null) {
            if (this.linkView.getView() instanceof YogaLayout) {
                this.yogaNode = ((YogaLayout) this.linkView.getView()).getYogaNode();
            } else {
                this.yogaNode = new YogaNodeJNIFinalizer();
                this.yogaNode.setData(this.linkView.getView());
                this.yogaNode.setMeasureFunction(new YogaLayout.ViewMeasureFunction());
            }
        }
        return this.yogaNode;
    }

    public void insertBefore(TKBase tKBase, TKBase tKBase2) {
        TKDomNode domNode;
        YogaLayout yogaLayoutSuperview;
        if (tKBase == null || (domNode = tKBase.getDomNode()) == null || (yogaLayoutSuperview = superview()) == null) {
            return;
        }
        yogaLayoutSuperview.addView(tKBase.getView(), domNode.getYogaNode());
        this.yogaNode.addChildAt(domNode.getYogaNode(), yogaLayoutSuperview.indexOfChild(tKBase2.getView()));
    }

    public void layoutSubviews() {
        if (superview() == null) {
            return;
        }
        this.yogaNode.calculateLayout(0.0f, 0.0f);
    }

    public void removeAllSubviews() {
        YogaLayout yogaLayoutSuperview = superview();
        if (yogaLayoutSuperview == null) {
            return;
        }
        yogaLayoutSuperview.removeAllViews();
    }

    public void removeSubview(TKBase tKBase) {
        YogaLayout yogaLayoutSuperview;
        if (tKBase == null || tKBase.getDomNode() == null || (yogaLayoutSuperview = superview()) == null) {
            return;
        }
        yogaLayoutSuperview.removeView(tKBase.getView());
    }

    public void replaceSubview(TKBase tKBase, TKBase tKBase2) {
        TKDomNode domNode;
        YogaLayout yogaLayoutSuperview;
        if (tKBase == null || tKBase2 == null || (domNode = tKBase.getDomNode()) == null || (yogaLayoutSuperview = superview()) == null) {
            return;
        }
        int iIndexOfChild = yogaLayoutSuperview.indexOfChild(tKBase2.getView());
        removeSubview(tKBase2);
        yogaLayoutSuperview.addView(tKBase.getView(), domNode.getYogaNode());
        this.yogaNode.addChildAt(domNode.getYogaNode(), iIndexOfChild);
    }

    public void reset() {
        this.domAttr.clear();
        this.domStyle.clear();
        if (this.yogaNode != null) {
            this.yogaNode.setData((Object) null);
            this.yogaNode = null;
        }
        this.nodeID = null;
        this.domStyle = null;
        this.domAttr = null;
        this.linkView = null;
    }

    public void setDomStyle(HashMap map) {
        if (map == null) {
            return;
        }
        for (Map.Entry entry : map.entrySet()) {
            String string = entry.getKey().toString();
            (TKYogaConfig.defaultConfig().ygPropertyWithCSSStyle(string) == Integer.MAX_VALUE ? this.domAttr : this.domStyle).put(string, entry.getValue());
        }
        configureLayout(this.domStyle);
        HashMap map2 = new HashMap();
        map2.putAll(this.domAttr);
        map2.putAll(this.domStyle);
        if (map.equals(map2)) {
            return;
        }
        this.linkView.getView().requestLayout();
    }

    public void setYogaNode(YogaNode yogaNode) {
        this.yogaNode = yogaNode;
    }
}
