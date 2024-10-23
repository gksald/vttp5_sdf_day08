package src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class SocketClient {
    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 8080); // Connect to server on localhost port 8080
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in))) {
             
            String userInputLine;
            System.out.println("Enter an arithmetic operation (e.g., 65 * 65) or 'end' to quit:");
            while ((userInputLine = userInput.readLine()) != null) {
                out.println(userInputLine); // Send input to server
                
                if ("end".equalsIgnoreCase(userInputLine)) {
                    System.out.println("Closing connection.");
                    break; // Exit the loop if "end" is entered
                }
                
                // Read the server's response
                String response = in.readLine();
                System.out.println("Result: " + response); // Print result from server
            }
        } catch (IOException e) {
            System.err.println("Client exception: " + e.getMessage());
        }
    }
}
