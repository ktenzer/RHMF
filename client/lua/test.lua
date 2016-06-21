-- This is goint to be init.lua
-- until this is save and stable,
-- we use it as test.lua
--  Run it with dofile('test.lua');
  
app = require("application")  
config = require("config")  
setup = require("setup")
hardware = require("hardware")
  
setup.start()     