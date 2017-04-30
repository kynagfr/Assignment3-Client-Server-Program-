/**Nursakinah Abdul Ghafar
2016718153*/

import java.net.*;
import java.io.*;

public class Client
{
	private static Socket socket;
	public static void main(String args[])
	{
		try
		{
			String host = "192.168.114.1";
			int port = 8080;
			InetAddress addr = InetAddress.getByName(host);
			socket = new Socket(addr,port);

			//send the message to the server
			OutputStream os = socket.getOutputStream();
			OutputStreamWriter osw = new OutputStreamWriter(os);
			BufferedWriter bw = new BufferedWriter(osw);

			String msg = "Hello Kyna";
			String sendMessage = msg + "\n";
			bw.write(sendMessage);
			bw.flush();
			System.out.println("Message sent: " + sendMessage);

			//Get the return message from the server
			InputStream is = socket.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);
			String message = br.readLine();
			System.out.println("Message received: " + message);
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
		finally
		{
			//closing the socket
			try
			{
				socket.close();
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
	}
}
