package com.ss.union.game.sdk.common.util.executeTime.entity;

import java.util.ArrayList;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class ExecuteTimeEntity {
    private long a;
    private long b;
    private List<Long> c = new ArrayList();
    private List<Long> d = new ArrayList();

    public long getStartTime() {
        return this.a;
    }

    public void setStartTime(long j) {
        this.a = j;
    }

    public long getEndTime() {
        return this.b;
    }

    public void setEndTime(long j) {
        this.b = j;
    }

    public void insertIgnoreStartTime(long j) {
        this.c.add(Long.valueOf(j));
    }

    public void insertIgnoreEndTime(long j) {
        this.d.add(Long.valueOf(j));
    }

    public List<Long> getIgnoreStartTimeList() {
        return this.c;
    }

    public List<Long> getIgnoreEndTimeList() {
        return this.d;
    }
}
