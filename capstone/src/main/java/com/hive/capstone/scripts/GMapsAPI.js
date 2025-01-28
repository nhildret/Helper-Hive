const proc = require("child_process");
const pledge_key = '';
const maps_key = '';


// Do google maps things


// Calling Pledge API
// Args: [lat, lon, cause_id, api_key]
pledgeProc = proc.spawn('python', ['PledgeAPI.py', 36.098104, -79.784872, 91, pledge_key]);

pledgeProc.stdout.on('data', (data) => {
    console.log("Data is " + data);
});