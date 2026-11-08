package com.facebook.stetho.dumpapp;

import com.baidu.mobads.sdk.api.IAdInterListener;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class GlobalOptions {
    public final Option optionHelp = new Option(IAdInterListener.AdReqParam.HEIGHT, "help", false, "Print this help");
    public final Option optionListPlugins = new Option("l", "list", false, "List available plugins");
    public final Option optionProcess = new Option("p", "process", true, "Specify target process");
    public final Options options = new Options();

    public GlobalOptions() {
        this.options.addOption(this.optionHelp);
        this.options.addOption(this.optionListPlugins);
        this.options.addOption(this.optionProcess);
    }
}
