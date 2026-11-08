package com.ss.union.game.sdk.core.glide.load.data;

import android.content.ContentResolver;
import android.content.UriMatcher;
import android.net.Uri;
import android.provider.ContactsContract;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class StreamLocalUriFetcher extends LocalUriFetcher<InputStream> {
    private static final int a = 1;
    private static final int b = 2;
    private static final int c = 3;
    private static final int d = 4;
    private static final int e = 5;
    private static final UriMatcher f = new UriMatcher(-1);

    static {
        f.addURI("com.android.contacts", "contacts/lookup/*/#", 1);
        f.addURI("com.android.contacts", "contacts/lookup/*", 1);
        f.addURI("com.android.contacts", "contacts/#/photo", 2);
        f.addURI("com.android.contacts", "contacts/#", 3);
        f.addURI("com.android.contacts", "contacts/#/display_photo", 4);
        f.addURI("com.android.contacts", "phone_lookup/*", 5);
    }

    public StreamLocalUriFetcher(ContentResolver contentResolver, Uri uri) {
        super(contentResolver, uri);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.ss.union.game.sdk.core.glide.load.data.LocalUriFetcher
    public InputStream loadResource(Uri uri, ContentResolver contentResolver) throws FileNotFoundException {
        InputStream inputStreamA = a(uri, contentResolver);
        if (inputStreamA != null) {
            return inputStreamA;
        }
        throw new FileNotFoundException("InputStream is null for " + uri);
    }

    private InputStream a(Uri uri, ContentResolver contentResolver) throws FileNotFoundException {
        int iMatch = f.match(uri);
        if (iMatch != 1) {
            if (iMatch == 3) {
                return a(contentResolver, uri);
            }
            if (iMatch != 5) {
                return contentResolver.openInputStream(uri);
            }
        }
        Uri uriLookupContact = ContactsContract.Contacts.lookupContact(contentResolver, uri);
        if (uriLookupContact == null) {
            throw new FileNotFoundException("Contact cannot be found");
        }
        return a(contentResolver, uriLookupContact);
    }

    private InputStream a(ContentResolver contentResolver, Uri uri) {
        return ContactsContract.Contacts.openContactPhotoInputStream(contentResolver, uri, true);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.ss.union.game.sdk.core.glide.load.data.LocalUriFetcher
    public void close(InputStream inputStream) throws IOException {
        inputStream.close();
    }

    @Override // com.ss.union.game.sdk.core.glide.load.data.DataFetcher
    public Class<InputStream> getDataClass() {
        return InputStream.class;
    }
}
