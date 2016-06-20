-- file : application.lua
local module = {}  
m = nil

-- Sends a simple ping to the broker
local function send_ping()  
    m:publish(config.MQTT_ENDPOINT .. "ping","id=" .. config.MQTT_ID,0,0)
end

-- Sends my id to the broker for registration
local function register_myself()  
    m:subscribe(config.MQTT_ENDPOINT .. config.MQTT_ID,0,function(conn)
        print("Successfully subscribed to data endpoint")
    end)
end

local function mqtt_start()  
    print ("mqtt start")
    m = mqtt.Client(config.MQTT_ID, 120)
    -- register message callback beforehand
    m:on("message", function(conn, topic, data) 
      if data ~= nil then
        print(topic .. ": " .. data)
        -- do something, we have received a message
      end
    end)
    -- Connect to broker
    print ("Connecting to MQTT Broker...")
    m:connect(config.MQTT_HOST, config.MQTT_PORT, 0, 1, function(con) 
        print ("Connected to mqtt broker")
        register_myself()
        -- And then pings each 1000 milliseconds
        tmr.stop(6)
        tmr.alarm(6, 1000, 1, send_ping)
    end) 

end

function module.start()   
  print ("Application starting")
  mqtt_start() 
end 
 
return module  