do
  -- use pin 5,6,7 as the input pulse width counter
  -- Yellow works perfect!
  local time5, time6, time7 = 0, 0, 0
  local timerdelay = 1000 --milli(timer)
  local suspend = 500000 -- micro(trigger)
  -- local mytimer = tmr.create() 
  btn_red_state=1
  btn_green_state=1
  btn_yellow_state=1 

  gpio.mode(5,gpio.INT)
  gpio.mode(6,gpio.INT)
  gpio.mode(7,gpio.INT)
  
  local function pin5cb(level)
    local pulse = tmr.now()
    if (pulse - time5 > suspend ) then
        state = gpio.read(5)
        if (state ~= btn_red_state) then
           btn_red_state = state
           gpio.write(0, gpio.LOW)
           print ("Red", state)
        end
      --print("Pin ", "Red", level, gpio.read(5), tmr.now())
    time5 = pulse
    end 
    --gpio.write(0, gpio.LOW)
    --gpio.write(1, gpio.HIGH)
    --gpio.write(2, gpio.HIGH)

    tmr.register(5, timerdelay, tmr.ALARM_SINGLE, 
        function() 
            print("Red expired"); 
            gpio.write(0, gpio.HIGH) 
            end)
    tmr.start(5)
   end
  local function pin6cb(level)
    local pulse = tmr.now()
    state = gpio.read(6)
    if ((pulse - time6 > suspend) and (state ~= btn_yellow_state)) then
      print("Yellow", "ON", level, gpio.read(6), tmr.now()) -- pressed
    time6 = pulse
    btn_yellow_state=state
    end
    gpio.write(0, gpio.LOW)
    gpio.write(1, gpio.HIGH)
    gpio.write(2, gpio.LOW)

    tmr.register(6, timerdelay, tmr.ALARM_SINGLE, 
        function() 
            print("Yellow", "OFF", "expired", tmr.now()); -- released
            gpio.write(0, gpio.HIGH);
            gpio.write(2, gpio.HIGH);
            btn_yellow_state=1; 
            end)
    tmr.start(6)
end
  local function pin7cb(level)
    local pulse = tmr.now()
    if (pulse - time7 > suspend ) then
      print("Pin ", "Green", level, gpio.read(7), tmr.now())
    end
    time7 = pulse
    gpio.write(0, gpio.HIGH)
    gpio.write(1, gpio.HIGH)
    gpio.write(2, gpio.LOW)

    tmr.register(4, timerdelay, tmr.ALARM_SINGLE, 
        function() 
            print("Green expired"); 
            gpio.write(2, gpio.HIGH) 
            end)
    tmr.start(4)
  end 
  gpio.trig(5, "low", pin5cb) 
  gpio.trig(6, "low", pin6cb) 
  gpio.trig(7, "down", pin7cb)
  print ("Start demo for pin 5, 6, 7")
end
