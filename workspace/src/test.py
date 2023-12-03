
s = '''public boolean equals(final Object obj) {
        if (! (obj instanceof FastDateParser) ) {
            return false;
        }
        final FastDateParser other = (FastDateParser) obj;
        return pattern.equals(other.pattern)
            && timeZone.equals(other.timeZone)
            && locale.equals(other.locale);
    }
'''

a = 'pattern.equals(other.pattern) && timeZone.equals(other.timeZone)'
b = 'false'
f = s.split('\n')
f.insert(0,'<start>')

lines = []
line_number = 1

while line_number < len(f):
  if line_number == 6:
    before_stmt = f[line_number]
    while(before_stmt.find(a) == -1):
      print(before_stmt)  
      line_number = line_number+1
      before_stmt += ' '+ f[line_number].strip()
      print(before_stmt)
    print(before_stmt)
    after_stmt = before_stmt.replace(a,b)
    print(after_stmt)
    lines.append(after_stmt)
  else:
    lines.append(f[line_number])
  line_number = line_number+1

print(lines)