import requests
import json
import sys

if len(sys.argv) != 3:
    print("Usage: python post_local_data.py <endpoint> <filepath>")
    sys.exit(1)

endpoint = sys.argv[1]
filepath = sys.argv[2]

with open(filepath, 'r') as file:
    for line in file:
        data = json.loads(line)
        response = requests.post(endpoint, json=data)
        if response.status_code >= 200 and response.status_code < 300:
            print(f"Successfully posted: {data}")
        else:
            print(f"Failed to post: {data}, Status Code: {response.status_code}")