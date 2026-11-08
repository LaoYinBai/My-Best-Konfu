package anet.channel.a;

import anet.channel.GlobalAppRuntimeInfo;
import anet.channel.flow.FlowStat;
import anet.channel.flow.INetworkAnalysis;
import anet.channel.util.ALog;
import com.taobao.analysis.FlowCenter;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class a implements INetworkAnalysis {

    /* renamed from: a, reason: collision with root package name */
    private boolean f395a;

    public a() throws ClassNotFoundException {
        try {
            Class.forName("com.taobao.analysis.FlowCenter");
            this.f395a = true;
        } catch (Exception unused) {
            this.f395a = false;
            ALog.e("DefaultNetworkAnalysis", "no NWNetworkAnalysisSDK sdk", null, new Object[0]);
        }
    }

    @Override // anet.channel.flow.INetworkAnalysis
    public void commitFlow(FlowStat flowStat) {
        if (this.f395a) {
            FlowCenter.getInstance().commitFlow(GlobalAppRuntimeInfo.getContext(), flowStat.refer, flowStat.protocoltype, flowStat.req_identifier, flowStat.upstream, flowStat.downstream);
        }
    }
}
