import requests

url = "http://127.0.0.1:8000/parse-receipt/"
files = {'file': open('/Users/joar/Documents/GitHub/project-dollarstore/backend/services/kvitto2.jpg', 'rb')}
response = requests.post(url, files=files)

print(response.json())
