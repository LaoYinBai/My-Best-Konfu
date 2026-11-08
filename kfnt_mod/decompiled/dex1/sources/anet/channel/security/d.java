package anet.channel.security;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
final class d implements ISecurityFactory {
    d() {
    }

    @Override // anet.channel.security.ISecurityFactory
    public ISecurity createSecurity(String str) {
        return new b(str);
    }

    @Override // anet.channel.security.ISecurityFactory
    public ISecurity createNonSecurity(String str) {
        return new a(str);
    }
}
