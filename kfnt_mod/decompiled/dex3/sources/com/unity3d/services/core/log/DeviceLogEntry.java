package com.unity3d.services.core.log;

import com.umeng.message.proguard.l;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
class DeviceLogEntry {
    private DeviceLogLevel _logLevel;
    private String _originalMessage;
    private StackTraceElement _stackTraceElement;

    public DeviceLogEntry(DeviceLogLevel deviceLogLevel, String str, StackTraceElement stackTraceElement) {
        this._logLevel = null;
        this._originalMessage = null;
        this._stackTraceElement = null;
        this._logLevel = deviceLogLevel;
        this._originalMessage = str;
        this._stackTraceElement = stackTraceElement;
    }

    public DeviceLogLevel getLogLevel() {
        return this._logLevel;
    }

    public String getParsedMessage() {
        int lineNumber;
        String str = this._originalMessage;
        String className = "UnknownClass";
        String methodName = "unknownMethod";
        if (this._stackTraceElement != null) {
            className = this._stackTraceElement.getClassName();
            methodName = this._stackTraceElement.getMethodName();
            lineNumber = this._stackTraceElement.getLineNumber();
        } else {
            lineNumber = -1;
        }
        if (str != null && !str.isEmpty()) {
            str = " :: " + str;
        }
        if (str == null) {
            str = "";
        }
        return className + "." + methodName + "()" + (" (line:" + lineNumber + l.t) + str;
    }
}
