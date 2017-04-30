local socket = require("socket")
host = host or "192.168.114.1"
port = port or 8080
if arg then
	host = arg[1] or host
	port = arg[2] or port
end

print("Binding to host '" ..host.. "' and port " ..port.. "...")
sock = assert(socket.bind(host, port))
h, p   = sock:getsockname()
assert(h, p)
print("Waiting connection from " .. h .. ":" .. p .. "...")
connect = assert(sock:accept())
print("Connected!")
recv, err = connect:receive()
while not err do
	reversedString = recv.reverse(recv)
	connect:send(reversedString .. "\n")
	recv, err = connect:receive()
end
print(err)
