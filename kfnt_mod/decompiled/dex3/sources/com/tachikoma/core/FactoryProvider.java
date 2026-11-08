package com.tachikoma.core;

import com.tachikoma.core.component.IFactory;
import com.tachikoma.core.component.anim.TKBasicAnimationFactory;
import com.tachikoma.core.component.anim.TKKeyframeAnimationFactory;
import com.tachikoma.core.component.anim.TKSpringAnimationFactory;
import com.tachikoma.core.component.button.TKButtonFactory;
import com.tachikoma.core.component.imageview.TKImageFactory;
import com.tachikoma.core.component.input.TKInputFactory;
import com.tachikoma.core.component.input.TKTextAreaFactory;
import com.tachikoma.core.component.listview.TKBaseAdapterFactory;
import com.tachikoma.core.component.listview.TKCollectionAdapterFactory;
import com.tachikoma.core.component.listview.TKIndicatorFactory;
import com.tachikoma.core.component.listview.TKListViewFactory;
import com.tachikoma.core.component.listview.TKRecyclerViewFactory;
import com.tachikoma.core.component.listview.TKViewPagerAdapterFactory;
import com.tachikoma.core.component.listview.TKViewPagerFactory;
import com.tachikoma.core.component.listview.TKWaterListViewFactory;
import com.tachikoma.core.component.network.NetworkErrorFactory;
import com.tachikoma.core.component.network.NetworkFactory;
import com.tachikoma.core.component.progressbar.KTProgressBarViewFactory;
import com.tachikoma.core.component.recyclerview.export.TKRefreshAnimatableViewFactory;
import com.tachikoma.core.component.recyclerview.export.TKRefreshControlFactory;
import com.tachikoma.core.component.switchview.TKSwitchFactory;
import com.tachikoma.core.component.text.SpanItemFactory;
import com.tachikoma.core.component.text.TKMarqueeTextFactory;
import com.tachikoma.core.component.text.TKSpanFactory;
import com.tachikoma.core.component.text.TKTextFactory;
import com.tachikoma.core.component.toast.TKToastFactory;
import com.tachikoma.core.component.view.TKViewFactory;
import com.tachikoma.core.event.base.TKBaseEventFactory;
import com.tachikoma.core.event.guesture.TKDispatchEventFactory;
import com.tachikoma.core.event.guesture.TKDownEventFactory;
import com.tachikoma.core.event.guesture.TKLongPressEventFactory;
import com.tachikoma.core.event.guesture.TKPanEventFactory;
import com.tachikoma.core.event.guesture.TKPinchEventFactory;
import com.tachikoma.core.event.guesture.TKSwipeEventFactory;
import com.tachikoma.core.event.guesture.TKTapEventFactory;
import com.tachikoma.core.event.guesture.TKUpEventFactory;
import com.tachikoma.core.event.view.TKInputEventFactory;
import com.tachikoma.core.event.view.TKScrollEventFactory;
import com.tachikoma.core.event.view.TKSwitchEventFactory;
import com.tachikoma.core.manager.IProvider;
import com.tachikoma.core.module.TKBaseBridgeFactory;
import com.tachikoma.core.module.TKBusinessSchoolBridgeFactory;
import com.tachikoma.core.module.TKWebCardBridgeImplFactory;
import com.tachikoma.core.module.handler.TKLifeCycleFactory;
import com.tachikoma.core.router.TKRouterFactory;
import java.util.HashMap;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class FactoryProvider implements IProvider<IFactory> {
    private static HashMap<String, IFactory> factoryMap = new HashMap<>(59);

    public static void registerExternal(String str, IFactory iFactory) {
        factoryMap.put(str, iFactory);
    }

    @Override // com.tachikoma.core.manager.IProvider
    public void apply(String str, Object obj, HashMap<String, Object> map) {
    }

    @Override // com.tachikoma.core.manager.IProvider
    public void clear() {
        factoryMap.clear();
    }

    @Override // com.tachikoma.core.manager.IProvider
    public HashMap<String, String> get() {
        return null;
    }

    @Override // com.tachikoma.core.manager.IProvider
    public void init() {
        factoryMap.put("com.tachikoma.core.event.guesture.TKDownEvent", new TKDownEventFactory());
        factoryMap.put("com.tachikoma.core.component.recyclerview.export.TKRefreshAnimatableView", new TKRefreshAnimatableViewFactory());
        factoryMap.put("com.tachikoma.core.event.guesture.TKSwipeEvent", new TKSwipeEventFactory());
        factoryMap.put("com.tachikoma.core.module.TKBaseBridge", new TKBaseBridgeFactory());
        factoryMap.put("com.tachikoma.core.component.network.NetworkError", new NetworkErrorFactory());
        factoryMap.put("com.tachikoma.core.module.TKWebCardBridgeImpl", new TKWebCardBridgeImplFactory());
        factoryMap.put("com.tachikoma.core.event.guesture.TKUpEvent", new TKUpEventFactory());
        factoryMap.put("com.tachikoma.core.event.guesture.TKPinchEvent", new TKPinchEventFactory());
        factoryMap.put("com.tachikoma.core.component.listview.TKCollectionAdapter", new TKCollectionAdapterFactory());
        factoryMap.put("com.tachikoma.core.component.input.TKTextArea", new TKTextAreaFactory());
        factoryMap.put("com.tachikoma.core.component.recyclerview.export.TKRefreshControl", new TKRefreshControlFactory());
        factoryMap.put("com.tachikoma.core.event.view.TKInputEvent", new TKInputEventFactory());
        factoryMap.put("com.tachikoma.core.component.anim.TKSpringAnimation", new TKSpringAnimationFactory());
        factoryMap.put("com.tachikoma.core.module.handler.TKLifeCycle", new TKLifeCycleFactory());
        factoryMap.put("com.tachikoma.core.component.anim.TKKeyframeAnimation", new TKKeyframeAnimationFactory());
        factoryMap.put("com.tachikoma.core.component.toast.TKToast", new TKToastFactory());
        factoryMap.put("com.tachikoma.core.component.input.TKInput", new TKInputFactory());
        factoryMap.put("com.tachikoma.core.component.listview.TKRecyclerView", new TKRecyclerViewFactory());
        factoryMap.put("com.tachikoma.core.component.imageview.TKImage", new TKImageFactory());
        factoryMap.put("com.tachikoma.core.router.TKRouter", new TKRouterFactory());
        factoryMap.put("com.tachikoma.core.component.listview.TKViewPager", new TKViewPagerFactory());
        factoryMap.put("com.tachikoma.core.component.text.TKMarqueeText", new TKMarqueeTextFactory());
        factoryMap.put("com.tachikoma.core.event.base.TKBaseEvent", new TKBaseEventFactory());
        factoryMap.put("com.tachikoma.core.event.guesture.TKPanEvent", new TKPanEventFactory());
        factoryMap.put("com.tachikoma.core.event.guesture.TKDispatchEvent", new TKDispatchEventFactory());
        factoryMap.put("com.tachikoma.core.component.text.TKText", new TKTextFactory());
        factoryMap.put("com.tachikoma.core.component.view.TKView", new TKViewFactory());
        factoryMap.put("com.tachikoma.core.component.button.TKButton", new TKButtonFactory());
        factoryMap.put("com.tachikoma.core.module.TKBusinessSchoolBridge", new TKBusinessSchoolBridgeFactory());
        factoryMap.put("com.tachikoma.core.event.guesture.TKTapEvent", new TKTapEventFactory());
        factoryMap.put("com.tachikoma.core.component.text.TKSpan", new TKSpanFactory());
        factoryMap.put("com.tachikoma.core.component.listview.TKBaseAdapter", new TKBaseAdapterFactory());
        factoryMap.put("com.tachikoma.core.component.switchview.TKSwitch", new TKSwitchFactory());
        factoryMap.put("com.tachikoma.core.component.listview.TKWaterListView", new TKWaterListViewFactory());
        factoryMap.put("com.tachikoma.core.component.anim.TKBasicAnimation", new TKBasicAnimationFactory());
        factoryMap.put("com.tachikoma.core.component.network.Network", new NetworkFactory());
        factoryMap.put("com.tachikoma.core.component.listview.TKListView", new TKListViewFactory());
        factoryMap.put("com.tachikoma.core.event.view.TKSwitchEvent", new TKSwitchEventFactory());
        factoryMap.put("com.tachikoma.core.event.guesture.TKLongPressEvent", new TKLongPressEventFactory());
        factoryMap.put("com.tachikoma.core.component.listview.TKIndicator", new TKIndicatorFactory());
        factoryMap.put("com.tachikoma.core.component.listview.TKViewPagerAdapter", new TKViewPagerAdapterFactory());
        factoryMap.put("com.tachikoma.core.component.progressbar.KTProgressBarView", new KTProgressBarViewFactory());
        factoryMap.put("com.tachikoma.core.event.view.TKScrollEvent", new TKScrollEventFactory());
        factoryMap.put("com.tachikoma.core.component.text.SpanItem", new SpanItemFactory());
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.tachikoma.core.manager.IProvider
    public IFactory of(String str) {
        return factoryMap.get(str);
    }

    @Override // com.tachikoma.core.manager.IProvider
    public HashMap<String, Object> retrieveEvent(String str, Object obj) {
        return null;
    }
}
