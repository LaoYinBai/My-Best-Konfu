package com.ss.android.socialbase.downloader.db;

import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import com.ss.android.socialbase.downloader.utils.SqlUtils;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class TableStatements {
    private final String[] allColumns;
    private final SQLiteDatabase database;
    private SQLiteStatement deleteStatement;
    private SQLiteStatement insertOrReplaceStatement;
    private SQLiteStatement insertStatement;
    private final String[] pkColumns;
    private final String tableName;
    private SQLiteStatement updateStatement;

    public TableStatements(SQLiteDatabase sQLiteDatabase, String str, String[] strArr, String[] strArr2) {
        this.database = sQLiteDatabase;
        this.tableName = str;
        this.allColumns = strArr;
        this.pkColumns = strArr2;
    }

    public SQLiteStatement getInsertStatement() throws SQLException {
        if (this.insertStatement == null) {
            SQLiteStatement sQLiteStatementCompileStatement = this.database.compileStatement(SqlUtils.createSqlInsert("INSERT INTO ", this.tableName, this.allColumns));
            synchronized (this) {
                if (this.insertStatement == null) {
                    this.insertStatement = sQLiteStatementCompileStatement;
                }
            }
            if (this.insertStatement != sQLiteStatementCompileStatement) {
                sQLiteStatementCompileStatement.close();
            }
        }
        return this.insertStatement;
    }

    public SQLiteStatement getDeleteStatement() throws SQLException {
        if (this.deleteStatement == null) {
            SQLiteStatement sQLiteStatementCompileStatement = this.database.compileStatement(SqlUtils.createSqlDelete(this.tableName, this.pkColumns));
            synchronized (this) {
                if (this.deleteStatement == null) {
                    this.deleteStatement = sQLiteStatementCompileStatement;
                }
            }
            if (this.deleteStatement != sQLiteStatementCompileStatement) {
                sQLiteStatementCompileStatement.close();
            }
        }
        return this.deleteStatement;
    }

    public SQLiteStatement getUpdateStatement() throws SQLException {
        if (this.updateStatement == null) {
            SQLiteStatement sQLiteStatementCompileStatement = this.database.compileStatement(SqlUtils.createSqlUpdate(this.tableName, this.allColumns, this.pkColumns));
            synchronized (this) {
                if (this.updateStatement == null) {
                    this.updateStatement = sQLiteStatementCompileStatement;
                }
            }
            if (this.updateStatement != sQLiteStatementCompileStatement) {
                sQLiteStatementCompileStatement.close();
            }
        }
        return this.updateStatement;
    }

    public SQLiteStatement getInsertOrReplaceStatement() throws SQLException {
        if (this.insertOrReplaceStatement == null) {
            SQLiteStatement sQLiteStatementCompileStatement = this.database.compileStatement(SqlUtils.createSqlInsertOrReplace(this.tableName, this.allColumns, this.pkColumns));
            synchronized (this) {
                if (this.insertOrReplaceStatement == null) {
                    this.insertOrReplaceStatement = sQLiteStatementCompileStatement;
                }
            }
            if (this.insertOrReplaceStatement != sQLiteStatementCompileStatement) {
                sQLiteStatementCompileStatement.close();
            }
        }
        return this.insertOrReplaceStatement;
    }
}
