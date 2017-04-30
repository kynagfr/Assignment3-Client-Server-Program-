/*Nursakinah Abdul Ghafar
2016718153*/

#include<stdio.h>	
#include<string.h>	
#include<sys/socket.h>	
#include<arpa/inet.h>	

int main(int argc , char *argv[])
{
	int sock;
	struct sockaddr_in server_in;
	char message[1000] , server_reply[1000];
	
	//Create socket
	sock = socket(AF_INET , SOCK_STREAM , 0);
	if (sock == -1)
	{
		printf("Could not create socket");
	}
	puts("Socket created");
	
	server_in.sin_addr.s_addr = inet_addr("192.168.242.130");
	server_in.sin_family = AF_INET;
	server_in.sin_port = htons( 8080 );

	//Connect to remote server
	if (connect(sock , (struct sockaddr *)&server_in , sizeof(server_in)) < 0)
	{
		perror("connect failed. Error");
		return 1;
	}
	
	puts("Connected\n");
	
	//communication with server keep on going
	while(1)
	{
		printf("Enter message : ");
		scanf("%s" , message);
		
		//Sending data
		if( send(sock , message , strlen(message) , 0) < 0)
		{
			puts("Send failed");
			return 1;
		}
		
		//Receive reply message from the server
		if( recv(sock , server_reply , 1000 , 0) < 0)
		{
			puts("recv failed");
			break;
		}
		
		puts("Server reply :");
		puts(server_reply);
	}
	
	//close(sock);
	return 0;
}
