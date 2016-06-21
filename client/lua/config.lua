-- file : config.lua
local module = {}

module.MQTT_HOST = "rhmfGateway"  
module.MQTT_PORT = 1883
module.MQTT_ID = node.chipid()
module.MQTT_USER= "admin"
module.MQTT_PSWD= "admin"
module.MQTT_ENDPOINT = "rhmfd/"  
module.MQTT_PING_DELAY = 5000

module.HW_BUTTON_GREEN = 0 
module.HW_BUTTON_YELLOW = 1
module.HW_BUTTON_RED = 2

module.HW_LED_BOTTOM_GREEN = 3
module.HW_LED_BOTTOM_BLUE = 4
module.HW_LED_BOTTOM_RED = 5

module.HW_LED_TOP_RED = 6 
module.HW_LED_TOP_GREEN = 7 
module.HW_LED_TOP_BLUE = 8
 
module.HW_LED_PINS = {
    ["LED_BOTTOM_GREEN"] = 3,
    ["LED_BOTTOM_BLUE"] = 4,
    ["LED_BOTTOM_RED"] = 5,   
    ["LED_TOP_GREEN"] = 6,
    ["LED_TOP_BLUE"] = 7,
    ["LED_TOP_RED"] = 8,   
}

module.HW_LED_STATES = {
    ["ON"] = gpio.LOW,
    ["OFF"] = gpio.HIGH,
}

module.BUTTON_STATES = {
    [gpio.LOW] = "ON",
    [gpio.HIGH] = "OFF",
}
  
module.BUTTON_POLL_DELAY = 100
 
return module      