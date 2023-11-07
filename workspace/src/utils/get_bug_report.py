import os
import json
import requests
import time
import datetime
from urllib.parse import urlparse
from env import EvoD4jEnv

def path_to_metadata(pid):
    return f"/tmp/project-metadata/{pid}.csv"

def get_metadata(pid):
    metadata = {}
    savepath = path_to_metadata(pid)
    if not os.path.exists(savepath):
        os.system(f'defects4j query -p {pid} -q "report.id,report.url" -o {savepath}')
    with open(savepath, 'r') as f:
        for l in f:
            vid, report_id, report_url = tuple(l.strip().split(','))
            metadata[(pid, vid)] = {
                'report_id': report_id,
                'report_url': report_url
            }
    return metadata

def get_json_from_url(url, **kwargs):
    res = requests.get(url, allow_redirects=True, **kwargs)
    res.raise_for_status()
    return res.json()

def get_json_from_file(path):
    with open(path, 'r') as f:
        return json.load(f)

def save_json_to_file(data, path):
    with open(path, 'w') as f:
        json.dump(data, f, indent=4)

def get_bug_report_from_apache_issue(report_url):
    if "rest/api" in report_url:
        issue_url = report_url
    else:
        report_id = report_url.split('/')[-1]
    issue_url = f'https://issues.apache.org/jira/rest/api/latest/issue/{report_id}'
    return get_json_from_url(issue_url)

def get_bug_report_from_sourceforge(report_url):
    url_path = urlparse(report_url).path
    issue_url = "https://sourceforge.net/rest/" + url_path
    return get_json_from_url(issue_url)

def get_bug_report_from_github(report_url):
    url_path = urlparse(report_url).path
    issue_url = "https://api.github.com/repos" + url_path
    return get_json_from_url(issue_url)

def get_bug_report_from_googleapi(report_url):
    return get_json_from_url(report_url) 

def get_bug_report(report_url):
    if report_url.startswith("https://issues.apache.org/jira"):
        return get_bug_report_from_apache_issue(report_url)
    elif report_url.startswith("https://sourceforge.net/"):
        return get_bug_report_from_sourceforge(report_url)
    elif report_url.startswith("https://github.com/"):
        return get_bug_report_from_github(report_url)
    elif report_url.startswith("https://storage.googleapis.com"):
        return get_bug_report_from_googleapi(report_url)
    else:
        return None

def parse_bug_report(data, source_url):
    if report_url.startswith("https://issues.apache.org/jira"):
        short_br = data["fields"]["summary"]
        long_br = data["fields"]["description"]
        ts = datetime.datetime.strptime(
            data["fields"]["created"], "%Y-%m-%dT%H:%M:%S.%f%z").timestamp()
        return short_br, long_br, ts
    elif report_url.startswith("https://sourceforge.net/"):
        short_br = data["ticket"]["summary"]
        long_br = data["ticket"]["description"]
        ts = datetime.datetime.strptime(
            data["ticket"]["created_date"], "%Y-%m-%d %H:%M:%S").timestamp()
        return short_br, long_br, ts
    elif report_url.startswith("https://github.com/"):
        short_br = data["title"]
        long_br = data["body"]
        ts = datetime.datetime.strptime(
            data["created_at"], "%Y-%m-%dT%H:%M:%SZ").timestamp()
        return short_br, long_br, ts
    elif report_url.startswith("https://storage.googleapis.com"):
        return data["summary"], data["comments"][0]["content"], data["comments"][0]["timestamp"]
    else:
        return None

 
if __name__ == "__main__":
    bugs = []

    with open("./PROJECT_VERSION.csv", "r") as f:
        f.readline()
        for l in f:
            pid = l.split(',')[0]
            vid = l.split(',')[1].strip()
            env = EvoD4jEnv(pid, vid)
            project_dpath = env.result_dir
            #collect_commits_for_D4J_bug(pid, vid, project_dpath)
            bugs.append((pid, vid))

    projects = set([pid for pid, _ in bugs])
    metadata = {}
    for pid in projects:
        metadata.update(get_metadata(pid))

    for bug in bugs:
        pid, vid = bug
        report_url = metadata[bug]['report_url']
        env = EvoD4jEnv(pid, vid)
        path_to_br = os.path.join(env.result_dir,"raw_br.json")
        if not os.path.exists(path_to_br):
            br = get_bug_report(report_url)
            if br is None: continue
            save_json_to_file(br, path_to_br)
        br = get_json_from_file(path_to_br)
        short_br, long_br, timestamp = parse_bug_report(br, report_url)
        
        project_dpath = env.result_dir
        print(project_dpath)
        os.makedirs(os.path.join(project_dpath, "br", "short"), exist_ok=True)
        os.makedirs(os.path.join(project_dpath, "br", "long"), exist_ok=True)
        with open(os.path.join(project_dpath, "br", "short", vid + ".txt"), 'w') as f:
            print(os.path.join(project_dpath, "br", "short", vid + ".txt"))
            f.write(short_br)
        with open(os.path.join(project_dpath, "br", "long", vid + ".txt"), 'w') as f:
            f.write(long_br)
        with open(os.path.join(project_dpath, "open_ts.txt"), 'w') as f:
            f.write(f"{vid},{float(timestamp)}")