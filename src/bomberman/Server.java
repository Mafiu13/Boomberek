/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bomberman;

import static bomberman.Client.flaga1;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.DataInputStream;
import java.io.DataOutputStream;

public class Server {

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

        while (flaga) {
            System.out.print(".");
            clientSocket = serverSocket.accept();
            if (clientSocket != null) {
                flaga = false;
            }

        }
        System.out.println("\nClient connected on port " + PORT);

//        final PrintWriter out = new PrintWriter(clientSocket.getOutputStream(),true);  
//        final BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));  
        //final DataInputStream in = new DataInputStream(clientSocket.getInputStream());
        //final DataOutputStream out = new DataOutputStream(clientSocket.getOutputStream());
        
        
        
        Thread t1 = new Thread() {
            public void run(){
                int y =1;
                int x=2;
                
                try{
                
                SendMessageS(y,2);
                }
                catch(IOException e){
            }
            }
                
                
                
            };
            t1.start();
            
            
            Thread t2 = new Thread() {
            public void run(){
                int y =1;
                int x=2;
                try{
                
                final DataInputStream in = new DataInputStream(clientSocket.getInputStream());
                
                if(in.available()!=0){
                

                ReceiveMessageS(in,y,2);
                }
                }
                catch(IOException e){
            }
            }
            
                
                
                
            };
            t2.start();
            
            
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        

//        nrp = 2;
//        move = 1;
//        stop = 3;
        

        //       out.println(nrp); 
//while(flaga1) 
//{
// out.writeInt(nrp);
//        System.out.println(nrp);
//        
//        out.writeInt(move);
//        System.out.println(move);
//        
//}


//for(int i=0; i<10; i++){
//
//        out.writeInt(nrp);
//       System.out.println(nrp);
//
//
//       out.writeInt(move);
//       System.out.println(move);
//
//       out.writeInt(stop);
//       System.out.println(stop);
//       
//}
//while(flaga1){
//    
//    if(in.available()!=0){
//        nrp = in.readInt();
//               System.out.println(nrp);
//        
//    }
//    else{
//        
//        System.out.println("nic");
//    }
//    if(in.available()!=0){
//        move = in.readInt();
//               System.out.println(move);
//        
//    }
//     else{
//        
//        System.out.println("nic");
//    }
//    if(in.available()!=0){
//        stop = in.readInt();
//               System.out.println(stop);
//        
//    }
//    else{
//        
//        System.out.println("nic");
//    }
    
    
    //}

 
    }
    
    
    public void SendMessageS (int move, int action ) throws IOException{
        
        final DataOutputStream out = new DataOutputStream(clientSocket.getOutputStream());
        
        out.writeInt(move);
        System.out.println(move);
        
        out.writeInt(action);
        System.out.println(action);
    }
    
    public void ReceiveMessageS(DataInputStream in, int move, int action) throws IOException{
        
        
        move = in.readInt(); 
        System.out.println(move);
        
        action = in.readInt();
        System.out.println(action);
        
        
    }
    
    
    
    
}
