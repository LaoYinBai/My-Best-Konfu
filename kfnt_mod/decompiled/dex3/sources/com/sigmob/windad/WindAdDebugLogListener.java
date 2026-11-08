package com.sigmob.windad;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public interface WindAdDebugLogListener {

    public enum WindAdLogLevel {
        WindLogLevelError,
        WindLogLevelWarning,
        WindLogLevelInformation,
        WindLogLevelDebug
    }

    void windAdDebugLog(String str, WindAdLogLevel windAdLogLevel);
}
