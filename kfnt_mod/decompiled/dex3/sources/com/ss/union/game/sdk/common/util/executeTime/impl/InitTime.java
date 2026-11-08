package com.ss.union.game.sdk.common.util.executeTime.impl;

import com.ss.union.game.sdk.common.util.executeTime.entity.ExecuteTimeEntity;
import com.ss.union.game.sdk.common.util.executeTime.in.IExecutorTime;
import com.ss.union.game.sdk.common.util.executeTime.in.IExecutorTimeLogger;
import com.ss.union.game.sdk.common.util.executeTime.in.IRecorder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class InitTime implements IExecutorTime, IRecorder {
    public static final String INIT_TIME_TAG = "CoreInit";
    private Map<String, ExecuteTimeEntity> a;
    private IExecutorTimeLogger b;

    public static InitTime getInstance() {
        return a.a;
    }

    private static class a {
        static InitTime a = new InitTime();

        private a() {
        }
    }

    private InitTime() {
        this.a = new HashMap();
    }

    public void setLogger(IExecutorTimeLogger iExecutorTimeLogger) {
        this.b = iExecutorTimeLogger;
    }

    @Override // com.ss.union.game.sdk.common.util.executeTime.in.IExecutorTime
    public long totalTime(String str) {
        ExecuteTimeEntity executeTimeEntity = this.a.get(str);
        if (executeTimeEntity == null) {
            a(str + "---totalTime invoke error,please check...");
            return 0L;
        }
        return executeTimeEntity.getEndTime() - executeTimeEntity.getStartTime();
    }

    @Override // com.ss.union.game.sdk.common.util.executeTime.in.IExecutorTime
    public long ignoreTime(String str) {
        ExecuteTimeEntity executeTimeEntity = this.a.get(str);
        long jLongValue = 0;
        if (executeTimeEntity == null) {
            a(str + "---ignoreTime is 0...");
            return 0L;
        }
        List<Long> ignoreStartTimeList = executeTimeEntity.getIgnoreStartTimeList();
        List<Long> ignoreEndTimeList = executeTimeEntity.getIgnoreEndTimeList();
        if (ignoreStartTimeList.size() != ignoreEndTimeList.size()) {
            a(str + "---ignoreEnd invoke error，ignoreStartTime、ignoreEndTime come in pairs..");
            return 0L;
        }
        for (int i = 0; i < ignoreStartTimeList.size(); i++) {
            jLongValue += ignoreEndTimeList.get(i).longValue() - ignoreStartTimeList.get(i).longValue();
        }
        return jLongValue;
    }

    @Override // com.ss.union.game.sdk.common.util.executeTime.in.IExecutorTime
    public long realTime(String str) {
        long jIgnoreTime = totalTime(str) - ignoreTime(str);
        if (jIgnoreTime < 0) {
            a(str + "---realTime invoke error,please check...");
        }
        return jIgnoreTime;
    }

    @Override // com.ss.union.game.sdk.common.util.executeTime.in.IRecorder
    public void start(String str) {
        ExecuteTimeEntity executeTimeEntity = this.a.get(str);
        if (executeTimeEntity == null) {
            executeTimeEntity = new ExecuteTimeEntity();
        }
        executeTimeEntity.setStartTime(a());
        this.a.put(str, executeTimeEntity);
    }

    @Override // com.ss.union.game.sdk.common.util.executeTime.in.IRecorder
    public void end(String str) {
        ExecuteTimeEntity executeTimeEntity = this.a.get(str);
        if (executeTimeEntity == null) {
            a(str + "---end invoke error,please check...");
            return;
        }
        executeTimeEntity.setEndTime(a());
    }

    @Override // com.ss.union.game.sdk.common.util.executeTime.in.IRecorder
    public void ignoreStart(String str) {
        ExecuteTimeEntity executeTimeEntity = this.a.get(str);
        if (executeTimeEntity == null) {
            executeTimeEntity = new ExecuteTimeEntity();
        }
        executeTimeEntity.insertIgnoreStartTime(a());
        this.a.put(str, executeTimeEntity);
    }

    @Override // com.ss.union.game.sdk.common.util.executeTime.in.IRecorder
    public void ignoreEnd(String str) {
        ExecuteTimeEntity executeTimeEntity = this.a.get(str);
        if (executeTimeEntity == null) {
            a(str + "---ignoreEnd invoke error,please check...");
            return;
        }
        executeTimeEntity.insertIgnoreEndTime(a());
        if (executeTimeEntity.getIgnoreStartTimeList().size() != executeTimeEntity.getIgnoreEndTimeList().size()) {
            a(str + "---ignoreEnd invoke error，ignoreStartTime、ignoreEndTime come in pairs..");
        }
    }

    public long currentInitTotalTime() {
        Iterator<Map.Entry<String, ExecuteTimeEntity>> it = this.a.entrySet().iterator();
        long j = 0;
        while (it.hasNext()) {
            j += totalTime(it.next().getKey());
        }
        return j;
    }

    public long currentInitIgnoreTime() {
        Iterator<Map.Entry<String, ExecuteTimeEntity>> it = this.a.entrySet().iterator();
        long jIgnoreTime = 0;
        while (it.hasNext()) {
            jIgnoreTime += ignoreTime(it.next().getKey());
        }
        return jIgnoreTime;
    }

    public long currentInitRealTime() {
        return currentInitTotalTime() - currentInitIgnoreTime();
    }

    private long a() {
        return System.currentTimeMillis();
    }

    private void a(String str) {
        if (this.b != null) {
            this.b.log(str);
        }
    }
}
