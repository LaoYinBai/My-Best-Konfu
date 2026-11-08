package com.ss.union.game.sdk.common.util.flow;

import com.ss.union.game.sdk.common.util.logger.LogUtils;
import java.util.LinkedList;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class FlowList {
    private IFlowListener a;
    private List<FlowItem> b = new LinkedList();
    private boolean c = false;

    public static abstract class FlowListenerAdapter implements IFlowListener {
        @Override // com.ss.union.game.sdk.common.util.flow.FlowList.IFlowListener
        public void onFinish() {
        }

        @Override // com.ss.union.game.sdk.common.util.flow.FlowList.IFlowListener
        public void onItemFinish(FlowItem flowItem) {
        }

        @Override // com.ss.union.game.sdk.common.util.flow.FlowList.IFlowListener
        public void onItemStart(FlowItem flowItem) {
        }

        @Override // com.ss.union.game.sdk.common.util.flow.FlowList.IFlowListener
        public void onStart() {
        }
    }

    public interface IFlowListener {
        void onFinish();

        void onItemFinish(FlowItem flowItem);

        void onItemStart(FlowItem flowItem);

        void onStart();
    }

    public FlowList then(FlowItem flowItem) {
        if (flowItem != null) {
            flowItem.a(this);
            this.b.add(flowItem);
        }
        return this;
    }

    public void start() {
        if (this.c) {
            return;
        }
        this.c = true;
        if (this.a != null) {
            this.a.onStart();
        }
        a();
    }

    private void a() {
        if (this.b.size() > 0) {
            FlowItem flowItemRemove = this.b.remove(0);
            LogUtils.log("LG_LOGGER Core_Common_FlowList", "Flow Next " + flowItemRemove.toString());
            if (this.a != null) {
                this.a.onItemStart(flowItemRemove);
            }
            flowItemRemove.doIt();
            return;
        }
        if (this.a != null) {
            this.a.onFinish();
        }
    }

    void a(FlowItem flowItem) {
        if (this.a != null) {
            this.a.onItemFinish(flowItem);
        }
        a();
    }

    public boolean isRunning() {
        return this.c;
    }

    public FlowList register(IFlowListener iFlowListener) {
        this.a = iFlowListener;
        return this;
    }
}
