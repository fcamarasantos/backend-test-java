import os
import sys
import requests
import json

if len(sys.argv) != 2:
    print("Usage: python post_all_jsonl.py <base_endpoint>")
    sys.exit(1)

def postAll():
    entities = []
    for filename in os.listdir():
        if filename.endswith(".jsonl"):
            entities.append(filename.split(".")[0])


    print(f"Entities found: {entities}")
    print("Posting data...")

    for entity in entities:
        print(f"Posting data for {entity}...")

        with open(f"{entity}.jsonl", 'r') as file:
            for line in file:
                data = json.loads(line)
                response = requests.post(f"{base_endpoint}/{entity}/", json=data)
                if response.status_code >= 200 and response.status_code < 300:
                    print(f"Successfully posted: {data}")
                else:
                    print(f"Failed to post: {data}, Status Code: {response.status_code}")

        print("Data posted.")


if __name__ == "__main__":
    base_endpoint = sys.argv[1]
    postAll()
