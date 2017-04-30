local socket = require("socket")
host = host or "192.168.114.1"
port = port or 8080
if arg then
	host = arg[1] or host
	port = arg[2] or port
end
print("Attemp connection to host '" ..host.. "' and port " ..port.. "...")
conn = assert(socket.connect(host, port))
print("Connected! Your Message:")
rdMsg = io.read()
while rdMsg and rdMsg ~= "" and not e do
	assert(conn:send(rdMsg .. "\n"))

	msg, err = conn:receive()
	if not err then
		print("Reversed Message: " .. msg)

	end

	rdMsg = io.read()
end
