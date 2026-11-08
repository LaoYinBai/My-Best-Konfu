"""扫 MemberRef token 的原始字节交叉引用 -> 定位调用者方法"""
import sys, struct
sys.path.insert(0, r'D:\My-Best-Konfu\scripts\pylibs')
import dnfile

def find_callers(path, class_name, method_names):
    pe = dnfile.dnPE(path)
    md = pe.net.mdtables
    # 1) 找目标 token (MemberRef 表: Class->TypeDef/TypeRef, Name)
    toks = {}
    for i, r in enumerate(md.MemberRef.rows):
        nm = str(r.Name)
        if nm in method_names:
            try:
                cls = r.Class.row
                cn = getattr(cls, 'TypeName', None) or getattr(cls, 'Name', '?')
            except Exception:
                cn = '?'
            if cn == class_name:
                toks[0x0A000000 | (i + 1)] = nm
    # MethodDef 里的同名方法 (call 直接用 MethodDef token)
    if md.MethodDef:
        for i, r in enumerate(md.MethodDef.rows):
            if str(r.Name) in method_names:
                # 找所属类型
                for t in md.TypeDef.rows:
                    for m in t.MethodList:
                        if m.row.Rva == r.Rva and str(m.row.Name) == str(r.Name):
                            if t.TypeName == class_name:
                                toks[0x06000000 | (i + 1)] = str(r.Name)
    print('目标token:', {hex(k): v for k, v in toks.items()})
    if not toks:
        print('未找到token'); return
    data = open(path, 'rb').read()
    # 2) 收集 MethodDef (RVA, size) 排序 -> 定位包含地址的方法
    meths = []
    for t in md.TypeDef.rows:
        for m in t.MethodList:
            r = m.row
            if r.Rva:
                meths.append((r.Rva, t.TypeName, str(r.Name)))
    meths.sort()
    def owner(rva):
        lo, hi = 0, len(meths) - 1
        best = None
        while lo <= hi:
            mid = (lo + hi) // 2
            if meths[mid][0] <= rva:
                best = meths[mid]; lo = mid + 1
            else:
                hi = mid - 1
        return best
    # 3) 扫描 body 内的 token 引用 (4字节对齐近似)
    for tokval, nm in sorted(toks.items()):
        pat = struct.pack('<I', tokval)
        pos = 0
        hits = []
        while True:
            idx = data.find(pat, pos)
            if idx < 0: break
            pos = idx + 1
            rva = 0x2000 + (idx - 0x200)  # 文件offset->RVA (粗略, .text线性)
            o = owner(rva)
            hits.append((idx, o))
        print('token 0x%08x (%s::%s) 引用位置:' % (tokval, class_name, nm))
        seen = set()
        for idx, o in hits:
            if o and o[1:] not in seen:
                seen.add(o[1:])
                print('   file 0x%x  ->  %s.%s' % (idx, o[1], o[2]))

if __name__ == '__main__':
    find_callers(sys.argv[1], sys.argv[2], sys.argv[3].split(','))
