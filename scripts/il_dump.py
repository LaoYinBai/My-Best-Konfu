"""按 类型名.方法名 反汇编 IL, 解析字符串token/成员引用"""
import sys
sys.path.insert(0, r'D:\My-Best-Konfu\scripts\pylibs')
import dnfile
from dncil.cil.body import CilMethodBody
from dncil.cil.error import MethodBodyFormatError
from dncil.clr.token import Token, StringToken, InvalidToken
from dncil.cil.body.reader import CilMethodBodyReaderBase

class Reader(CilMethodBodyReaderBase):
    def __init__(self, pe, row):
        self.pe = pe
        self.offset = self.pe.get_offset_from_rva(row.Rva)
    def read(self, n):
        d = self.pe.get_data(self.pe.get_rva_from_offset(self.offset), n)
        self.offset += n
        return d
    def tell(self):
        return self.offset
    def seek(self, o):
        self.offset = o
        return self.offset

def resolve(pe, tok):
    if isinstance(tok, StringToken):
        try:
            return '"%s"' % pe.net.user_strings.get_us(tok.rid).value
        except Exception:
            return '<str 0x%x>' % tok.rid
    t = pe.net.mdtables.tables_by_index.get(tok.table) if hasattr(pe.net.mdtables, 'tables_by_index') else None
    # 通用解析
    try:
        table = pe.net.mdtables.tables[tok.table] if hasattr(pe.net.mdtables, 'tables') else None
    except Exception:
        table = None
    try:
        if tok.table == 0x0A:  # MemberRef
            row = pe.net.mdtables.MemberRef.rows[tok.rid - 1]
            cls = row.Class.row
            clsname = getattr(cls, 'TypeName', getattr(cls, 'Name', '?'))
            return '%s::%s' % (clsname, row.Name)
        if tok.table == 0x06:  # MethodDef
            row = pe.net.mdtables.MethodDef.rows[tok.rid - 1]
            return 'M:%s' % row.Name
        if tok.table == 0x01:  # TypeRef
            row = pe.net.mdtables.TypeRef.rows[tok.rid - 1]
            return 'T:%s.%s' % (row.TypeNamespace, row.TypeName)
        if tok.table == 0x02:  # TypeDef
            row = pe.net.mdtables.TypeDef.rows[tok.rid - 1]
            return 'T:%s.%s' % (row.TypeNamespace, row.TypeName)
        if tok.table == 0x04:  # Field
            row = pe.net.mdtables.Field.rows[tok.rid - 1]
            return 'F:%s' % row.Name
        if tok.table == 0x2B:  # MethodSpec
            row = pe.net.mdtables.MethodSpec.rows[tok.rid - 1]
            try:
                m = row.Method.row
                return 'MS:%s' % getattr(m, 'Name', '?')
            except Exception:
                return 'MS:?'
    except Exception:
        pass
    return str(tok)

def dump(path, type_name, method_name=None):
    pe = dnfile.dnPE(path)
    md = pe.net.mdtables
    for t in md.TypeDef.rows:
        if t.TypeName != type_name:
            continue
        for m in t.MethodList:
            r = m.row
            if method_name and r.Name != method_name:
                continue
            if r.Rva == 0:
                print('%s.%s: (无IL, abstract/pinvoke)' % (t.TypeName, r.Name))
                continue
            print('===== %s.%s (RVA 0x%x) =====' % (t.TypeName, r.Name, r.Rva))
            try:
                body = CilMethodBody(Reader(pe, r))
                for insn in body.instructions:
                    op = str(insn.opcode)
                    operand = ''
                    if insn.operand is not None:
                        if isinstance(insn.operand, Token):
                            operand = resolve(pe, insn.operand)
                        else:
                            operand = str(insn.operand)
                    print('  IL_%04x: %-12s %s' % (insn.offset - body.offset, op, operand))
            except MethodBodyFormatError as e:
                print('  IL解析失败:', e)

if __name__ == '__main__':
    dump(sys.argv[1], sys.argv[2], sys.argv[3] if len(sys.argv) > 3 else None)
