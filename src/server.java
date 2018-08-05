import java.io.*;
import java.net.*;
import java.net.DatagramSocket;


public class server  {

	static int PORT = 1010;
	static String IPADD = "127.0.0.1";
	
	public static void main(String argv[]) throws Exception {
		String clientSentence;
		String capitalizedSentence;
		
		// Enable server socket, Port in defined
		ServerSocket welcomeSocket = new ServerSocket(PORT);
		System.out.println("Server ready..");
		
		// get current IP address
		try(final DatagramSocket socket = new DatagramSocket()){
			socket.connect(InetAddress.getByName("8.8.8.8"), 10002);
			IPADD = socket.getLocalAddress().getHostAddress();
		}
		System.out.println("Server Server IP: "+IPADD+" PORT: "+PORT);

		while (true) {
			Socket connectionSocket = welcomeSocket.accept();
			
			BufferedReader inFromClient =
					new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
			DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());
			clientSentence = inFromClient.readLine();
			System.out.println("Received: " + clientSentence);
			capitalizedSentence = clientSentence.toUpperCase() + 'n';
			outToClient.writeBytes(capitalizedSentence);
		}
	}
}
