package universal.tools.notifications;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes4.dex */
interface IPushNotificationsProvider {
    void disable();

    void enable();

    void subscribeToTopic(String str);

    void unsubscribeFromTopic(String str);
}
