package com.ss.union.game.sdk.ad.ad_mediation.type;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public interface LGMediationAdBaseAd {

    public enum InteractionType {
        BROWSER(2),
        LANDING_PAGE(3),
        DOWNLOAD(4),
        DIAL(5),
        UNKNOWN(-1);

        int index;

        InteractionType(int i) {
            this.index = i;
        }

        public int getIndex() {
            return this.index;
        }

        public static InteractionType getType(int i) {
            switch (i) {
                case 2:
                    return BROWSER;
                case 3:
                    return LANDING_PAGE;
                case 4:
                    return DOWNLOAD;
                case 5:
                    return DIAL;
                default:
                    return UNKNOWN;
            }
        }
    }
}
