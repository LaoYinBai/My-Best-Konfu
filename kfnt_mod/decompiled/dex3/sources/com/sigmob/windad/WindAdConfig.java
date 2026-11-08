package com.sigmob.windad;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public final class WindAdConfig {
    private WindCustomController a;

    public static class Builder {
        private WindCustomController a;

        public WindAdConfig build() {
            WindAdConfig windAdConfig = new WindAdConfig();
            windAdConfig.setCustomController(this.a);
            return windAdConfig;
        }

        public Builder customController(WindCustomController windCustomController) {
            this.a = windCustomController;
            return this;
        }
    }

    private WindAdConfig() {
    }

    public WindCustomController getCustomController() {
        return this.a;
    }

    public void setCustomController(WindCustomController windCustomController) {
        this.a = windCustomController;
    }
}
