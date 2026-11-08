package anet.channel;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public interface IAuth {

    public interface AuthCallback {
        void onAuthFail(int i, String str);

        void onAuthSuccess();
    }

    void auth(Session session, AuthCallback authCallback);
}
