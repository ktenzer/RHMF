-- file: setup.lua
local module = {}

function module.start()  
    print("Starting end user setup")
    enduser_setup.start(
      function()
        print("Connected to wifi as:" .. wifi.sta.getip())
        print("Before App Start")
        app.start()
      end,
      function(err, str)
        print("enduser_setup: Err #" .. err .. ": " .. str)
      end 
    ); 
end
 
return module   
