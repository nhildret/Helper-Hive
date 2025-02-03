import sys
import requests

lat = 36.098104
lon = -79.784872
cause_id = 91
api_key = ""


url = 'https://api.pledge.to/v1/organizations?per_page=100&lat=' + str(lat) + '&lon=' + str(lon) + '&cause_id=' + str(cause_id)


headers = {
    'Authorization' : 'Bearer ' + api_key
}
response = requests.get(url, headers = headers)

if response.status_code == 200:
    data = response.json()
    print("Now showing " + str(len(data["results"])) + " results out of " + str(data["total_count"]) + "\n\n")
    for i in range(len(data["results"])) :
        print(str(i) + " - " + data["results"][i]["name"])
        print("\t" + str(data["results"][i]["lat"]) + ", " + str(data["results"][i]["lon"]) + "\tregion: " + data["results"][i]["region"] + "\n")

else:
    print(response.status_code)
    
sys.stdout.flush()


# In[ ]:


#have ids for option buttons as cause_id. Figure out how to do multiple values in a param (not sure if possible)
#only include organizations that were put into the system? Or import ALL organizations? Probably not all

