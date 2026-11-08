package com.tachikoma.core;

import com.sigmob.sdk.common.mta.PointCategory;
import com.tachikoma.core.component.anim.AnimationProperty;
import com.tachikoma.core.manager.IProvider;
import com.taobao.accs.common.Constants;
import com.umeng.commonsdk.framework.c;
import java.util.ArrayList;
import java.util.HashMap;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class PropertyProvider implements IProvider<ArrayList<String>> {
    private static HashMap<String, ArrayList<String>> attributeMap = new HashMap<>(49);

    private static double trans(Number number) {
        return number.doubleValue();
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:113:0x01a7  */
    @Override // com.tachikoma.core.manager.IProvider
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void apply(java.lang.String r3, java.lang.Object r4, java.util.HashMap<java.lang.String, java.lang.Object> r5) {
        /*
            Method dump skipped, instructions count: 5496
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tachikoma.core.PropertyProvider.apply(java.lang.String, java.lang.Object, java.util.HashMap):void");
    }

    @Override // com.tachikoma.core.manager.IProvider
    public void clear() {
        attributeMap.clear();
    }

    @Override // com.tachikoma.core.manager.IProvider
    public HashMap<String, String> get() {
        return null;
    }

    @Override // com.tachikoma.core.manager.IProvider
    public void init() {
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("lottieRes");
        arrayList.add("lottiePath");
        arrayList.add("viewID");
        arrayList.add("style");
        arrayList.add("enabled");
        arrayList.add("uri");
        arrayList.add("src");
        arrayList.add("blurRadius");
        attributeMap.put("com_tachikoma_core_component_imageview_TKImage", arrayList);
        ArrayList<String> arrayList2 = new ArrayList<>();
        arrayList2.add("motionType");
        arrayList2.add("type");
        arrayList2.add("data");
        arrayList2.add("position");
        attributeMap.put("com_tachikoma_core_event_guesture_TKTapEvent", arrayList2);
        ArrayList<String> arrayList3 = new ArrayList<>();
        arrayList3.add("lottieRes");
        arrayList3.add("lottiePath");
        arrayList3.add("viewID");
        arrayList3.add("style");
        arrayList3.add("enabled");
        attributeMap.put("com_tachikoma_core_component_text_TKSpan", arrayList3);
        ArrayList<String> arrayList4 = new ArrayList<>();
        arrayList4.add("scheme");
        arrayList4.add("timeoutInterval");
        arrayList4.add(Constants.KEY_HOST);
        arrayList4.add(Constants.KEY_USER_ID);
        arrayList4.add("bodyParameters");
        arrayList4.add("path");
        arrayList4.add("parameters");
        arrayList4.add("headers");
        attributeMap.put("com_tachikoma_core_component_network_Network", arrayList4);
        ArrayList<String> arrayList5 = new ArrayList<>();
        arrayList5.add("motionType");
        arrayList5.add("type");
        arrayList5.add("data");
        arrayList5.add("position");
        attributeMap.put("com_tachikoma_core_event_guesture_TKLongPressEvent", arrayList5);
        ArrayList<String> arrayList6 = new ArrayList<>();
        arrayList6.add("needTranformIdentity");
        arrayList6.add("animValue");
        arrayList6.add("delay");
        arrayList6.add("timingFunction");
        arrayList6.add("duration");
        arrayList6.add("animType");
        arrayList6.add("repeatCount");
        arrayList6.add("autoReverse");
        arrayList6.add("timeFunction");
        arrayList6.add("keyframes");
        attributeMap.put("com_tachikoma_core_component_anim_TKKeyframeAnimation", arrayList6);
        ArrayList<String> arrayList7 = new ArrayList<>();
        arrayList7.add("motionType");
        arrayList7.add("type");
        arrayList7.add("data");
        arrayList7.add("position");
        attributeMap.put("com_tachikoma_core_event_guesture_TKUpEvent", arrayList7);
        ArrayList<String> arrayList8 = new ArrayList<>();
        arrayList8.add("lottieRes");
        arrayList8.add("lottiePath");
        arrayList8.add("viewID");
        arrayList8.add("style");
        arrayList8.add("enabled");
        attributeMap.put("com_tachikoma_core_component_listview_TKListView", arrayList8);
        ArrayList<String> arrayList9 = new ArrayList<>();
        arrayList9.add("motionType");
        arrayList9.add("type");
        arrayList9.add("data");
        arrayList9.add("position");
        attributeMap.put("com_tachikoma_core_event_guesture_TKDownEvent", arrayList9);
        ArrayList<String> arrayList10 = new ArrayList<>();
        arrayList10.add("motionType");
        arrayList10.add("type");
        arrayList10.add("data");
        arrayList10.add("state");
        attributeMap.put("com_tachikoma_core_event_view_TKSwitchEvent", arrayList10);
        ArrayList<String> arrayList11 = new ArrayList<>();
        arrayList11.add("motionType");
        arrayList11.add("type");
        arrayList11.add("data");
        arrayList11.add("direction");
        attributeMap.put("com_tachikoma_core_event_guesture_TKSwipeEvent", arrayList11);
        ArrayList<String> arrayList12 = new ArrayList<>();
        arrayList12.add("motionType");
        arrayList12.add("type");
        arrayList12.add("data");
        arrayList12.add("state");
        arrayList12.add("text");
        attributeMap.put("com_tachikoma_core_event_view_TKInputEvent", arrayList12);
        ArrayList<String> arrayList13 = new ArrayList<>();
        arrayList13.add("lottieRes");
        arrayList13.add("lottiePath");
        arrayList13.add("placeholder");
        arrayList13.add("viewID");
        arrayList13.add("style");
        arrayList13.add("focused");
        arrayList13.add("enabled");
        arrayList13.add("text");
        attributeMap.put("com_tachikoma_core_component_input_TKTextArea", arrayList13);
        ArrayList<String> arrayList14 = new ArrayList<>();
        arrayList14.add("msg");
        arrayList14.add("code");
        attributeMap.put("com_tachikoma_core_component_network_NetworkError", arrayList14);
        ArrayList<String> arrayList15 = new ArrayList<>();
        arrayList15.add("needTranformIdentity");
        arrayList15.add("animValue");
        arrayList15.add("delay");
        arrayList15.add("timingFunction");
        arrayList15.add("duration");
        arrayList15.add("animType");
        arrayList15.add("repeatCount");
        arrayList15.add("autoReverse");
        arrayList15.add("timeFunction");
        attributeMap.put("com_tachikoma_core_component_anim_TKBasicAnimation", arrayList15);
        ArrayList<String> arrayList16 = new ArrayList<>();
        arrayList16.add("motionType");
        arrayList16.add("type");
        arrayList16.add("data");
        arrayList16.add(AnimationProperty.SCALE);
        attributeMap.put("com_tachikoma_core_event_guesture_TKPinchEvent", arrayList16);
        ArrayList<String> arrayList17 = new ArrayList<>();
        arrayList17.add("motionType");
        arrayList17.add("type");
        arrayList17.add("data");
        arrayList17.add("translation");
        attributeMap.put("com_tachikoma_core_event_guesture_TKPanEvent", arrayList17);
        ArrayList<String> arrayList18 = new ArrayList<>();
        arrayList18.add(c.a);
        attributeMap.put("com_tachikoma_core_component_toast_TKToast", arrayList18);
        ArrayList<String> arrayList19 = new ArrayList<>();
        arrayList19.add("lottieRes");
        arrayList19.add("lottiePath");
        arrayList19.add("viewID");
        arrayList19.add("style");
        arrayList19.add("enabled");
        arrayList19.add("formattedText");
        arrayList19.add("text");
        attributeMap.put("com_tachikoma_core_component_text_TKText", arrayList19);
        ArrayList<String> arrayList20 = new ArrayList<>();
        arrayList20.add("motionType");
        arrayList20.add("type");
        arrayList20.add("data");
        attributeMap.put("com_tachikoma_core_event_base_TKBaseEvent", arrayList20);
        ArrayList<String> arrayList21 = new ArrayList<>();
        arrayList21.add("lottieRes");
        arrayList21.add("lottiePath");
        arrayList21.add("viewID");
        arrayList21.add("style");
        arrayList21.add("enabled");
        arrayList21.add("clipChildren");
        attributeMap.put("com_tachikoma_core_component_view_TKView", arrayList21);
        ArrayList<String> arrayList22 = new ArrayList<>();
        arrayList22.add("motionType");
        arrayList22.add("type");
        arrayList22.add("data");
        arrayList22.add("position");
        attributeMap.put("com_tachikoma_core_event_guesture_TKDispatchEvent", arrayList22);
        ArrayList<String> arrayList23 = new ArrayList<>();
        arrayList23.add("lottieRes");
        arrayList23.add("lottiePath");
        arrayList23.add("viewID");
        arrayList23.add("style");
        arrayList23.add("enabled");
        arrayList23.add("disabled");
        arrayList23.add("pressed");
        arrayList23.add("text");
        attributeMap.put("com_tachikoma_core_component_button_TKButton", arrayList23);
        ArrayList<String> arrayList24 = new ArrayList<>();
        arrayList24.add("motionType");
        arrayList24.add("type");
        arrayList24.add("data");
        arrayList24.add("scrollOffsetX");
        arrayList24.add("state");
        arrayList24.add("dy");
        arrayList24.add("scrollOffsetY");
        arrayList24.add("dx");
        attributeMap.put("com_tachikoma_core_event_view_TKScrollEvent", arrayList24);
        ArrayList<String> arrayList25 = new ArrayList<>();
        arrayList25.add("lottieRes");
        arrayList25.add("lottiePath");
        arrayList25.add("viewID");
        arrayList25.add("style");
        arrayList25.add("enabled");
        attributeMap.put("com_tachikoma_core_component_listview_TKViewPager", arrayList25);
        ArrayList<String> arrayList26 = new ArrayList<>();
        arrayList26.add("lottieRes");
        arrayList26.add("lottiePath");
        arrayList26.add("viewID");
        arrayList26.add("style");
        arrayList26.add("enabled");
        arrayList26.add("placeholder");
        arrayList26.add("focused");
        arrayList26.add("text");
        attributeMap.put("com_tachikoma_core_component_input_TKInput", arrayList26);
        ArrayList<String> arrayList27 = new ArrayList<>();
        arrayList27.add("lottieRes");
        arrayList27.add("lottiePath");
        arrayList27.add("viewID");
        arrayList27.add("style");
        arrayList27.add("enabled");
        attributeMap.put("com_tachikoma_core_component_recyclerview_export_TKRefreshAnimatableView", arrayList27);
        ArrayList<String> arrayList28 = new ArrayList<>();
        arrayList28.add("lottieRes");
        arrayList28.add("lottiePath");
        arrayList28.add("viewID");
        arrayList28.add("style");
        arrayList28.add("enabled");
        arrayList28.add("animating");
        arrayList28.add("color");
        arrayList28.add("progress");
        attributeMap.put("com_tachikoma_core_component_progressbar_KTProgressBarView", arrayList28);
        ArrayList<String> arrayList29 = new ArrayList<>();
        arrayList29.add("lottieRes");
        arrayList29.add("lottiePath");
        arrayList29.add("viewID");
        arrayList29.add("style");
        arrayList29.add("enabled");
        arrayList29.add("endReachedThreshold");
        attributeMap.put("com_tachikoma_core_component_listview_TKRecyclerView", arrayList29);
        ArrayList<String> arrayList30 = new ArrayList<>();
        arrayList30.add("needTranformIdentity");
        arrayList30.add("animValue");
        arrayList30.add("delay");
        arrayList30.add("timingFunction");
        arrayList30.add("duration");
        arrayList30.add("animType");
        arrayList30.add("repeatCount");
        arrayList30.add("autoReverse");
        arrayList30.add("timeFunction");
        arrayList30.add("damping");
        arrayList30.add("stiffness");
        attributeMap.put("com_tachikoma_core_component_anim_TKSpringAnimation", arrayList30);
        ArrayList<String> arrayList31 = new ArrayList<>();
        arrayList31.add("lottieRes");
        arrayList31.add("lottiePath");
        arrayList31.add("viewID");
        arrayList31.add("style");
        arrayList31.add("enabled");
        attributeMap.put("com_tachikoma_core_component_recyclerview_export_TKRefreshControl", arrayList31);
        ArrayList<String> arrayList32 = new ArrayList<>();
        arrayList32.add("lottieRes");
        arrayList32.add("lottiePath");
        arrayList32.add("viewID");
        arrayList32.add("style");
        arrayList32.add("enabled");
        arrayList32.add("imageWidth");
        arrayList32.add("spanType");
        arrayList32.add("size");
        arrayList32.add(AnimationProperty.MARGIN_LEFT);
        arrayList32.add("url");
        arrayList32.add("offsetY");
        arrayList32.add("end");
        arrayList32.add("textStyle");
        arrayList32.add("imageHeight");
        arrayList32.add("index");
        arrayList32.add("color");
        arrayList32.add(AnimationProperty.MARGIN_RIGHT);
        arrayList32.add("offsetX");
        arrayList32.add(PointCategory.START);
        arrayList32.add("imageUrl");
        arrayList32.add("fontFamily");
        attributeMap.put("com_tachikoma_core_component_text_SpanItem", arrayList32);
        ArrayList<String> arrayList33 = new ArrayList<>();
        arrayList33.add("lottieRes");
        arrayList33.add("lottiePath");
        arrayList33.add("viewID");
        arrayList33.add("endReachedThreshold");
        arrayList33.add("style");
        arrayList33.add("enabled");
        attributeMap.put("com_tachikoma_core_component_listview_TKWaterListView", arrayList33);
        ArrayList<String> arrayList34 = new ArrayList<>();
        arrayList34.add("lottieRes");
        arrayList34.add("lottiePath");
        arrayList34.add("viewID");
        arrayList34.add("style");
        arrayList34.add("enabled");
        attributeMap.put("com_tachikoma_core_component_listview_TKIndicator", arrayList34);
        ArrayList<String> arrayList35 = new ArrayList<>();
        arrayList35.add("lottieRes");
        arrayList35.add("lottiePath");
        arrayList35.add("viewID");
        arrayList35.add("style");
        arrayList35.add("enabled");
        arrayList35.add("text");
        attributeMap.put("com_tachikoma_core_component_text_TKMarqueeText", arrayList35);
        ArrayList<String> arrayList36 = new ArrayList<>();
        arrayList36.add("lottieRes");
        arrayList36.add("lottiePath");
        arrayList36.add("viewID");
        arrayList36.add("style");
        arrayList36.add("enabled");
        arrayList36.add("checked");
        attributeMap.put("com_tachikoma_core_component_switchview_TKSwitch", arrayList36);
    }

    @Override // com.tachikoma.core.manager.IProvider
    public ArrayList<String> of(String str) {
        return attributeMap.get(str);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:41:0x008c  */
    @Override // com.tachikoma.core.manager.IProvider
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.util.HashMap<java.lang.String, java.lang.Object> retrieveEvent(java.lang.String r3, java.lang.Object r4) {
        /*
            Method dump skipped, instructions count: 676
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tachikoma.core.PropertyProvider.retrieveEvent(java.lang.String, java.lang.Object):java.util.HashMap");
    }
}
