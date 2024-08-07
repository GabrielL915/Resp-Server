import socket

def send_command(command):
    host = 'localhost'
    port = 8081

    sock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)

    try:
        sock.connect((host, port))
        sock.sendall(command)
        response = sock.recv(4096)
        print('Received:', response.decode('utf-8'))

    finally:
        sock.close()

# Chamando a função
send_command(b'+OKokokokooko\r\n')
send_command(b':1\r\n')
send_command(b'$13\r\nHello, World!\r\n')
