/**Nursakinah Abdul Ghafar
2016718153*/
import java.io.*;
import java.net.*;

public class Server
{
	private static Socket socket;
	public static void main(String[] args)
	{
		try
		{
			int port = 8080;
			ServerSocket svrSock = new ServerSocket(port);
			System.out.println("Server listening to port 8080");

			//Server will always running and will stop by using while
			while(true)
			{
				//Reading the message from the client
				socket = svrSock.accept();
				InputStream is = socket.getInputStream();
				InputStreamReader isr = new InputStreamReader(is);
				BufferedReader br = new BufferedReader(isr);
				String msg = br.readLine();
				System.out.println("Message received " + msg );

				//Reversing the message and forming the return message
				String returnMessage;
				try
				{
					char temporary;
					char [] arr = msg.toCharArray();
					int len = arr.length;
					for ( int k=0; k<(msg.length())/2; k++,len--)
					{
						temporary = arr[k];
						arr[k] = arr[len-1];
						arr[len-1] = temporary;
					}

					returnMessage = String.valueOf(arr) + "\n";
				}
				catch(Exception e)
				{
					returnMessage = "Please enter a proper message\n";
				}

				//sending back the message to the client
				OutputStream os = socket.getOutputStream();
				OutputStreamWriter osw = new OutputStreamWriter(os);
				BufferedWriter bw = new BufferedWriter(osw);
				bw.write(returnMessage);
				System.out.println("Message sent is" + returnMessage);
				bw.flush();
			}	
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
		finally
		{
			try
			{
				socket.close();
			}
			catch(Exception ex){}
		}
	}
}
