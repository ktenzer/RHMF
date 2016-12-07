-- file : config.lua
local module = {}

module.MQTT_HOST = "rhmf.dynet.com"  
module.MQTT_PORT = 1883
module.MQTT_ID = node.chipid()
module.MQTT_USER= "admin"
module.MQTT_PSWD= "admin"
module.MQTT_ENDPOINT = "rhmfd/"  
module.MQTT_PING_DELAY = 5000

module.HW_BUTTON_GREEN = 7  
module.HW_BUTTON_YELLOW = 6
module.HW_BUTTON_RED = 5

module.HW_BUZZER = 4

module.HW_LED_BOTTOM_RED = 3
  
module.HW_LED_TOP_RED = 0 
module.HW_LED_TOP_GREEN = 2 
module.HW_LED_TOP_BLUE = 1 
 
module.HW_LED_PINS = {
    ["LED_BOTTOM_RED"] = module.HW_LED_BOTTOM_RED,   
    ["LED_TOP_GREEN"] = module.HW_LED_TOP_GREEN,
    ["LED_TOP_BLUE"] = module.HW_LED_TOP_BLUE,
    ["LED_TOP_RED"] = module.HW_LED_TOP_RED,   
}

module.HW_BUZZERS = {
    ["BUZZER"] = module.HW_BUZZER,   
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
