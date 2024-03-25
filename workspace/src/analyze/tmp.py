import csv
projects = ["Chart", "Closure", "Lang", "Math",  "Time"]
projects = ["total"]
for project in projects:
  f = open(f'{project}_8_row.csv', 'r', encoding='utf-8')

  reader= csv.reader(f)
  score_dict={}
  score_org_dict={}
  score_trs_dict={}
  for i in ['-1.00','-0.90','-0.80','-0.70', '-0.60', '-0.50', '-0.40', '-0.30', '-0.20', '-0.10','0.00','0.10','0.20','0.30','0.40','0.50','0.60','0.70','0.80','0.90','1.00']:
    score_dict[i]=0
    score_org_dict[i]=0
    score_trs_dict[i]=0

  for line in reader:
    if line[0].split()[1] == 'score':
      score_dict[line[1].split()[2]] = line[3].split()[1]
      score_org_dict[line[1].split()[2]] = 0
      score_trs_dict[line[1].split()[2]] = 0
    elif line[0].split()[1] == 'score_org':
      score_org_dict[line[1].split()[2]] = line[3].split()[1]
    elif line[0].split()[1] == 'score_trs':
      score_trs_dict[line[1].split()[2]] = line[3].split()[1]

  with open(f'{project}_8.csv','w') as f:
      w = csv.writer(f)
      w.writerow(score_dict.keys())
      w.writerow(score_dict.values())
      w.writerow(score_org_dict.values())
      w.writerow(score_trs_dict.values())

    

