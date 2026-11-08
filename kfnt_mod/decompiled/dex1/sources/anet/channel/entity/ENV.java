package anet.channel.entity;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public enum ENV {
    ONLINE(0),
    PREPARE(1),
    TEST(2);

    private int envMode;

    public int getEnvMode() {
        return this.envMode;
    }

    public void setEnvMode(int i) {
        this.envMode = i;
    }

    public static ENV valueOf(int i) {
        switch (i) {
            case 1:
                return PREPARE;
            case 2:
                return TEST;
            default:
                return ONLINE;
        }
    }

    ENV(int i) {
        this.envMode = i;
    }
}
