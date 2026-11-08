package com.ss.union.game.sdk.common.util.flow;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public abstract class FlowItem {
    private FlowList a;
    private boolean b = false;

    public abstract void doIt();

    protected void finish() {
        if (this.b) {
            return;
        }
        this.b = true;
        this.a.a(this);
    }

    void a(FlowList flowList) {
        this.a = flowList;
    }

    public String toString() {
        return getClass().getSimpleName();
    }
}
