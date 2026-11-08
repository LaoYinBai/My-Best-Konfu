import ghidra.app.script.GhidraScript;
import ghidra.program.model.symbol.*;

public class gh_dump extends GhidraScript {
    public void run() throws Exception {
        SymbolTable st = currentProgram.getSymbolTable();
        SymbolIterator si = st.getAllSymbols(true);
        int c = 0, mono = 0, sample = 0;
        StringBuilder sb = new StringBuilder();
        while (si.hasNext()) {
            Symbol s = si.next();
            String n = s.getName();
            c++;
            if (n.toLowerCase().startsWith("mono")) mono++;
            if (sample < 80 && c % 20 == 0) { sb.append(n).append(" | "); sample++; }
        }
        println("总符号: " + c + "  mono*: " + mono);
        println("抽样: " + sb.toString());
    }
}
