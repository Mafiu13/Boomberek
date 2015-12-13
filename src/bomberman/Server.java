/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bomberman;

//import static bomberman.Client.flaga1;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.DataInputStream;
import java.io.DataOutputStream;

public class Server extends ServClie {

    private static final int PORT = 50000;
    static boolean flaga = true;
    static boolean flaga1 = true;

    private static ServerSocket serverSocket;
    private static Socket clientSocket;

    public int nrp, move, stop;

    public void CreateServer() throws IOException {
        clientSocket = null;
        serverSocket = null;
        try {
            serverSocket = new ServerSocket(PORT);
        } catch (IOException e) {
            System.err.println("Could not listen on port: " + PORT);
            System.exit(1);
        }

        System.out.print("Wating for connection...");

//                Thread t2 = new Thread() {
//            public void run(){
//                int y =1;
//                int x=2;
//                boolean fl = true;
//                
//                try{
//
//                    while(fl){
//
//                
//
//                ReceiveMessageS(y,x);}
//
//                }
//                catch(Exception e){
//            }
//            }            };
//           t2.start();
    }

    public void SendMessage(int move, int action) throws IOException {

        final DataOutputStream out = new DataOutputStream(clientSocket.getOutputStream());

        System.out.println("Send: ");

        out.writeInt(move);
        System.out.println(move);

        out.writeInt(action);
        System.out.println(action);;
    }

    public void ReceiveMessage(int move, int action) throws IOException {

        final DataInputStream in = new DataInputStream(clientSocket.getInputStream());

        if (in.available() != 0) {

            System.out.println("Received: ");

            move = in.readInt();
            System.out.println(move);

            action = in.readInt();
            System.out.println(action);
        }

    }

    public void SendMessageI(int move) throws IOException {

        final DataOutputStream out = new DataOutputStream(clientSocket.getOutputStream());

        System.out.println("Send: ");

        out.writeInt(move);
        System.out.println(move);

    }

    public boolean Connected() throws IOException {
//        
//        for (int i=0;i<5;i++) {
//            System.out.print(".");

        clientSocket = serverSocket.accept();
//            if ((clientSocket != null) || (i==4)) {
//                serverSocket.close();
//                flaga = false;
//            }

//        }
        System.out.println("\nClient connected on port " + PORT);

        return true;
    }

    public int ReceiveMessageI(int move) throws IOException {

        final DataInputStream in = new DataInputStream(clientSocket.getInputStream());

        if (in.available() != 0) {

            System.out.println("Received: ");

            move = in.readInt();
            System.out.println(move);
        }
        return move;
    }

    public void ServerClose() throws IOException {

        clientSocket.close();
        serverSocket.close();

    }

//    public int SMove (int move){
//        
//        return move;
//        
//    }
//    
//    public int SAction (int action){
//        
//        return action;
//        
//    }
}
