package com.huawei.hms.common.a;

import android.database.AbstractWindowedCursor;
import android.database.CrossProcessCursor;
import android.database.Cursor;
import android.database.CursorWindow;
import android.database.CursorWrapper;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class a extends CursorWrapper implements CrossProcessCursor {
    private AbstractWindowedCursor a;

    public a(Cursor cursor) {
        super(cursor);
        if (cursor == null) {
            throw new IllegalArgumentException("cursor cannot be null");
        }
        if (!(cursor instanceof CursorWrapper)) {
            throw new IllegalArgumentException("cursor:" + cursor + " is not a subclass for CursorWrapper");
        }
        Cursor wrappedCursor = ((CursorWrapper) cursor).getWrappedCursor();
        if (wrappedCursor == null) {
            throw new IllegalArgumentException("getWrappedCursor cannot be null");
        }
        if (!(wrappedCursor instanceof AbstractWindowedCursor)) {
            throw new IllegalArgumentException("getWrappedCursor:" + wrappedCursor + " is not a subclass for CursorWrapper");
        }
        this.a = (AbstractWindowedCursor) wrappedCursor;
    }

    @Override // android.database.CrossProcessCursor
    public CursorWindow getWindow() {
        return this.a.getWindow();
    }

    public void a(CursorWindow cursorWindow) {
        this.a.setWindow(cursorWindow);
    }

    @Override // android.database.CrossProcessCursor
    public void fillWindow(int i, CursorWindow cursorWindow) {
        this.a.fillWindow(i, cursorWindow);
    }

    @Override // android.database.CrossProcessCursor
    public boolean onMove(int i, int i2) {
        return this.a.onMove(i, i2);
    }

    @Override // android.database.CursorWrapper
    public Cursor getWrappedCursor() {
        return this.a;
    }
}
