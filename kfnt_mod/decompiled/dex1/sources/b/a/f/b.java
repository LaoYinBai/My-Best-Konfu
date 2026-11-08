package b.a.f;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public enum b {
    NO_ERROR(0),
    PROTOCOL_ERROR(1),
    INTERNAL_ERROR(2),
    FLOW_CONTROL_ERROR(3),
    REFUSED_STREAM(7),
    CANCEL(8),
    COMPRESSION_ERROR(9),
    CONNECT_ERROR(10),
    ENHANCE_YOUR_CALM(11),
    INADEQUATE_SECURITY(12),
    HTTP_1_1_REQUIRED(13);

    public final int l;

    b(int i) {
        this.l = i;
    }

    public static b a(int i) {
        for (b bVar : values()) {
            if (bVar.l == i) {
                return bVar;
            }
        }
        return null;
    }
}
