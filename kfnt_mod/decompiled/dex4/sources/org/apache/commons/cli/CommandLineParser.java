package org.apache.commons.cli;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes4.dex */
public interface CommandLineParser {
    CommandLine parse(Options options, String[] strArr) throws ParseException;

    CommandLine parse(Options options, String[] strArr, boolean z) throws ParseException;
}
