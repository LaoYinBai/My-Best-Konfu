package com.sigmob.sdk.mraid;

import com.ss.android.pushmanager.PushCommonConstants;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public enum e {
    CLOSE("close"),
    EXPAND("expand") { // from class: com.sigmob.sdk.mraid.e.1
    },
    USE_CUSTOM_CLOSE("usecustomclose"),
    OPEN(PushCommonConstants.VALUE_OPEN) { // from class: com.sigmob.sdk.mraid.e.2
    },
    UNLOAD("unload"),
    OPENFOURELEMENTS("openFourElements"),
    RESIZE("resize") { // from class: com.sigmob.sdk.mraid.e.3
    },
    SET_ORIENTATION_PROPERTIES("setOrientationProperties"),
    PLAY_VIDEO("playVideo") { // from class: com.sigmob.sdk.mraid.e.4
    },
    STORE_PICTURE("storePicture") { // from class: com.sigmob.sdk.mraid.e.5
    },
    CREATE_CALENDAR_EVENT("createCalendarEvent") { // from class: com.sigmob.sdk.mraid.e.6
    },
    VPAID("vpaid") { // from class: com.sigmob.sdk.mraid.e.7
    },
    EXTENSION("extension") { // from class: com.sigmob.sdk.mraid.e.8
    },
    UNSPECIFIED("");

    private final String o;

    e(String str) {
        this.o = str;
    }

    static e a(String str) {
        for (e eVar : values()) {
            if (eVar.o.equals(str)) {
                return eVar;
            }
        }
        return UNSPECIFIED;
    }

    String a() {
        return this.o;
    }
}
