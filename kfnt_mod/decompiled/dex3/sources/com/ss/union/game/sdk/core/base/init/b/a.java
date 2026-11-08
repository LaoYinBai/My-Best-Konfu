package com.ss.union.game.sdk.core.base.init.b;

import com.ss.union.game.sdk.common.util.executeTime.impl.InitTime;
import com.ss.union.game.sdk.common.util.flow.FlowItem;
import com.ss.union.game.sdk.common.util.flow.FlowList;
import com.ss.union.game.sdk.common.util.logger.LogConstant;
import com.ss.union.game.sdk.common.util.logger.LogUtils;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class a extends FlowList.FlowListenerAdapter {
    @Override // com.ss.union.game.sdk.common.util.flow.FlowList.FlowListenerAdapter, com.ss.union.game.sdk.common.util.flow.FlowList.IFlowListener
    public void onItemStart(FlowItem flowItem) {
        InitTime.getInstance().start(flowItem.toString());
    }

    @Override // com.ss.union.game.sdk.common.util.flow.FlowList.FlowListenerAdapter, com.ss.union.game.sdk.common.util.flow.FlowList.IFlowListener
    public void onItemFinish(FlowItem flowItem) {
        InitTime.getInstance().end(flowItem.toString());
        long j = InitTime.getInstance().totalTime(flowItem.toString());
        long jIgnoreTime = InitTime.getInstance().ignoreTime(flowItem.toString());
        LogUtils.log(LogConstant.TAG_INIT, flowItem + "模块初始化完成---realTime:" + a(InitTime.getInstance().realTime(flowItem.toString())) + "---ignoreTime=" + a(jIgnoreTime) + "---totalTime=" + a(j));
    }

    @Override // com.ss.union.game.sdk.common.util.flow.FlowList.FlowListenerAdapter, com.ss.union.game.sdk.common.util.flow.FlowList.IFlowListener
    public void onFinish() {
        long jCurrentInitTotalTime = InitTime.getInstance().currentInitTotalTime();
        long jCurrentInitIgnoreTime = InitTime.getInstance().currentInitIgnoreTime();
        LogUtils.log(LogConstant.TAG_INIT, "初始化完成---SDK真正初始化时间:" + a(InitTime.getInstance().currentInitRealTime()) + "---ignoreTime=" + a(jCurrentInitIgnoreTime) + "---totalTime=" + a(jCurrentInitTotalTime));
    }

    private static String a(long j) {
        if (j < 1000) {
            return j + "ms（毫秒）";
        }
        if (j / 1000 < 60) {
            return (j / 1000.0f) + "s（秒）";
        }
        return (j / 60000.0f) + "m (分钟)";
    }
}
