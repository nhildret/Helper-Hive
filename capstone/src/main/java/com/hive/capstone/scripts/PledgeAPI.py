import sys
import requests

results_num = 5
api_key = "a593e05baba4b232d7cab6df192239d0"
headers = {
    'Authorization' : 'Bearer ' + api_key
}

def getOrgs() :
    url = 'https://api.pledge.to/v1/organizations'
    response = requests.get(url, headers = headers)

    if response.status_code == 200:
        data = response.json()
        # print("Now showing " + str(len(data["results"])) + " results out of " + str(data["total_count"]) + "\n\n")
        # for i in range(len(data["results"])) :
        #     print(str(i) + " - " + data["results"][i]["name"])
        #     print("\t" + str(data["results"][i]["lat"]) + ", " + str(data["results"][i]["lon"]) + "\tregion: " + data["results"][i]["region"] + "\n")
    else:
        data["results"] = (response.status_code)
    print(data["results"])


def getOrgsByCoords(lat, lon, cause_id) :
    url = 'https://api.pledge.to/v1/organizations?per_page=' + str(results_num) + '&lat=' + str(lat) + '&lon=' + str(lon) + '&cause_id=' + str(cause_id)
    response = requests.get(url, headers = headers)

    if response.status_code == 200:
        data = response.json()
        ("Now showing " + str(len(data["results"])) + " results out of " + str(data["total_count"]) + "\n\n")
        for i in range(len(data["results"])) :
            print(str(i) + " - " + data["results"][i]["name"])
            print("\t" + str(data["results"][i]["lat"]) + ", " + str(data["results"][i]["lon"]) + "\tregion: " + data["results"][i]["region"] + "\n")
    else:
        data = response.status_code
    sys.stdout.flush()
    return data


def getOrgsByQuery(query) :
    url = 'https://api.pledge.to/v1/organizations?per_page=' + str(results_num) + '&q=' + query
    response = requests.get(url, headers = headers)

    if response.status_code == 200:
        data = response.json()
        print("Now showing " + str(len(data["results"])) + " results out of " + str(data["total_count"]) + "\n\n")
        for i in range(len(data["results"])) :
            print(str(i) + " - " + data["results"][i]["name"])
            print("\t" + str(data["results"][i]["lat"]) + ", " + str(data["results"][i]["lon"]) + "\tregion: " + data["results"][i]["region"] + "\n")
    else:
        data = (response.status_code)
        
    sys.stdout.flush()
    return data


search_id = sys.argv[1]

if search_id == "0" :
    getOrgs()
elif search_id == "1" : 
    lat, lon, cause_id = sys.argv[2].split("; ")
    getOrgsByCoords(lat = lat, lon = lon) #, cause_id = cause_id
elif search_id == "2" :
    getOrgsByQuery(query = sys.argv[2])


#have ids for option buttons as cause_id. Figure out how to do multiple values in a param (not sure if possible)
#only include organizations that were put into the system? Or import ALL organizations? Probably not all

