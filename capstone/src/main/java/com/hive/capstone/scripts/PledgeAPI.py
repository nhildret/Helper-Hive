import sys
import requests

api_key = "a593e05baba4b232d7cab6df192239d0"
headers = {
    'Authorization' : 'Bearer ' + api_key
}
argstring = sys.argv[1]

url = 'https://api.pledge.to/v1/organizations?' + argstring
response = requests.get(url, headers = headers)

if response.status_code == 200:
    data = response.json()
    data = data["results"]
    # print("Now showing " + str(len(data["results"])) + " results out of " + str(data["total_count"]) + "\n\n")
    # for i in range(len(data["results"])) :
    #     print(str(i) + " - " + data["results"][i]["name"])
    #     print("\t" + str(data["results"][i]["lat"]) + ", " + str(data["results"][i]["lon"]) + "\tregion: " + data["results"][i]["region"] + "\n")
else:
    data = (response.status_code)
print(data)