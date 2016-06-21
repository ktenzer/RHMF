-- file : application.lua
local module = {}  
m = nil

-- Sends a simple ping to the broker
local function send_ping()   
    print ("send ping")
    m:publish(config.MQTT_ENDPOINT .. "ping","id=" .. config.MQTT_ID,0,0)
end


function module.send_button_message(payload)
    print ("send_button_message")
    message = ""
    message = message .. "id=" .. config.MQTT_ID .. "\n"
    message = message .. "ts=" .. tmr.now() .. "\n"
    message = message .. payload
    
    m:publish(config.MQTT_ENDPOINT .. "button", message, 0,0)
end  
-- Sends my id to the broker for registration
local function register_myself()  
    print ("register_myself")
    m:subscribe(config.MQTT_ENDPOINT .. config.MQTT_ID,0,function(conn)
        print("Successfully subscribed to data endpoint")
    end)
end

local function handle_command(command, value)
    print ("handle_command " .. command .. "=" .. value)
    hardware.set_led(command, value)
end

local function handle_message(topic, message)
    for line in message:gmatch("[^\r\n]+") do 
        print ("handle_message: line=" .. line)
        for key, value in string.gmatch(line, "(.+)=(.+)") do
            handle_command(key, value) 
        end
    end
end

local function mqtt_start()  
    print ("mqtt start")
    m = mqtt.Client(config.MQTT_ID, 120)
    -- register message callback beforehand
    m:on("message", function(conn, topic, data) 
      if data ~= nil then
        print(topic .. ": " .. data)
        handle_message(topic, data)
      end
    end)
    -- Connect to broker
    print ("Connecting to MQTT Broker...")
    m:connect(config.MQTT_HOST, config.MQTT_PORT, 0, 1, function(con) 
        print ("Connected to mqtt broker")
        register_myself()
        -- And then pings each 2000 milliseconds
        tmr.stop(6) 
        tmr.alarm(6, config.MQTT_PING_DELAY, 1, send_ping)

        hardware.start()
    end)    
end
 
function module.start()   
  print ("Application starting")
  mqtt_start() 
end 
 
return module  