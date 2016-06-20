-- file : config.lua
local module = {}

module.MQTT_HOST = "rhmfGateway"  
module.MQTT_PORT = 1883
module.MQTT_ID = node.chipid()
module.MQTT_USER= "admin"
module.MQTT_PSWD= "admin"

module.MQTT_ENDPOINT = "nodemcu/"  
return module      