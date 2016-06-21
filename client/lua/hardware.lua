-- file:hardware.lua
local module = {}
function module.set_led(name, state)
    print ("set_led:")
    pin = config.HW_LED_PINS[name]
    level = config.HW_LED_STATES[state]
    print (name .. "=" .. state)
    print (pin .. "=" .. level)
    gpio.write(pin, level)
end

btn_red_state=0
btn_green_state=0
btn_yellow_state=0  

function module.check_buttons()    
    state = gpio.read(config.HW_BUTTON_RED)
    msg = ""
    if (state ~= btn_red_state) then
       btn_red_state = state
       gpio.write(config.HW_LED_BOTTOM_RED, state)
       msg = msg .. "BUTTON_RED=" .. config.BUTTON_STATES[state] .. "\n"
    end
 
    state = gpio.read(config.HW_BUTTON_GREEN)
    if (state ~= btn_green_state) then
       btn_green_state = state
       gpio.write(config.HW_LED_BOTTOM_GREEN, state)
       msg = msg .. "BUTTON_GREEN=" .. config.BUTTON_STATES[state]  ..  "\n"
    end

   state = gpio.read(config.HW_BUTTON_YELLOW)
    if (state ~= btn_yellow_state) then
       btn_yellow_state = state
       gpio.write(config.HW_LED_BOTTOM_BLUE, state)
       msg = msg .. "BUTTON_YELLOW=" .. config.BUTTON_STATES[state]  .. "\n"
    end

    if (string.len(msg) > 0 ) then
        print ("Check_Buttons msg=" .. msg);
        app.send_button_message(msg)
    end    
end

function module.start()  
    print ("Hardware start")
    gpio.mode(config.HW_LED_BOTTOM_GREEN, gpio.OUTPUT)
    gpio.mode(config.HW_LED_BOTTOM_BLUE, gpio.OUTPUT)
    gpio.mode(config.HW_LED_BOTTOM_RED, gpio.OUTPUT)

    gpio.mode(config.HW_LED_TOP_GREEN, gpio.OUTPUT)
    gpio.mode(config.HW_LED_TOP_BLUE, gpio.OUTPUT)
    gpio.mode(config.HW_LED_TOP_RED, gpio.OUTPUT, gpio.PULLUP)
 
    gpio.mode(config.HW_BUTTON_GREEN, gpio.INPUT, gpio.PULLUP)    
    gpio.mode(config.HW_BUTTON_YELLOW, gpio.INPUT, gpio.PULLUP)
    gpio.mode(config.HW_BUTTON_RED, gpio.INPUT, gpio.PULLUP)

    tmr.stop(5);
    tmr.alarm(5, config.BUTTON_POLL_DELAY, tmr.ALARM_AUTO, module.check_buttons);

end 
 

return module   
