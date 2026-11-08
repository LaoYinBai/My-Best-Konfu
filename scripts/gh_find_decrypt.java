import ghidra.app.script.GhidraScript;
import ghidra.app.decompiler.*;
import ghidra.program.model.listing.*;

public class gh_find_decrypt extends GhidraScript {
    public void run() throws Exception {
        FunctionIterator it = currentProgram.getFunctionManager().getFunctions(true);
        DecompInterface ifc = new DecompInterface();
        DecompileOptions opts = new DecompileOptions();
        ifc.setOptions(opts);
        ifc.openProgram(currentProgram);
        int found = 0;
        while (it.hasNext()) {
            Function f = it.next();
            String n = f.getName().toLowerCase();
            if (n.contains("open_from_data") || n.contains("mono_image_open")) {
                found++;
                println("FOUND " + f.getName() + " @ " + f.getEntryPoint());
                DecompileResults r = ifc.decompileFunction(f, 180, monitor);
                if (r.decompileCompleted()) {
                    println(r.getDecompiledFunction().getC());
                } else {
                    println("DECOMP FAILED: " + r.getErrorMessage());
                }
            }
        }
        println("TOTAL FOUND: " + found);
    }
}
