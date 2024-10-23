package src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServer {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(8080)) { // Start server on port 8080
            System.out.println("Server is listening on port 8080...");
            while (true) {
                Socket clientSocket = serverSocket.accept(); // Accept client connections
                System.out.println("New client connected");
                
                // Create a new thread for each client
                new ClientHandler(clientSocket).start();
            }
        } catch (IOException e) {
            System.err.println("Server exception: " + e.getMessage());
        }
    }
}

// ClientHandler class to handle client requests
class ClientHandler extends Thread {
    private Socket clientSocket;

    public ClientHandler(Socket socket) {
        this.clientSocket = socket;
    }

    public void run() {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
             PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                if ("end".equalsIgnoreCase(inputLine)) {
                    System.out.println("Ending connection with client.");
                    break; // Exit the loop if "end" is received
                }
                
                // Process arithmetic operation
                try {
                    double result = evaluateExpression(inputLine);
                    out.println(result); // Send result back to client
                } catch (Exception e) {
                    out.println("Invalid input");
                }
            }
        } catch (IOException e) {
            System.err.println("Client handler exception: " + e.getMessage());
        } finally {
            try {
                clientSocket.close(); // Close the client socket
            } catch (IOException e) {
                System.err.println("Could not close socket: " + e.getMessage());
            }
        }
    }

    // Method to evaluate arithmetic expression
    private double evaluateExpression(String expression) throws Exception {
        String[] parts = expression.split(" ");
        if (parts.length != 3) {
            throw new Exception("Invalid expression");
        }
        
        double a = Double.parseDouble(parts[0]);
        String operator = parts[1];
        double b = Double.parseDouble(parts[2]);

        switch (operator) {
            case "+":
                return a + b;
            case "-":
                return a - b;
            case "*":
                return a * b;
            case "/":
                if (b == 0) throw new Exception("Division by zero");
                return a / b;
            default:
                throw new Exception("Unknown operator");
        }
    }
}


