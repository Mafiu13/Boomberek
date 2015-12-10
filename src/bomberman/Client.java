/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bomberman;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {

    private static final int PORT = 50000;
    private static final String HOST = "localhost";
    static boolean flaga1 = true;

    int nrp, move, stop;

    public void CreateClient() throws IOException {
        Socket socket = null;

        nrp = 4;
        move = 5;
        stop = 6;

        try {
            socket = new Socket(HOST, PORT);
        } catch (Exception e) {
            System.err.println("Could not connect to " + HOST + ":" + PORT);
            System.exit(1);
        }

//        final PrintWriter out = new PrintWriter(socket.getOutputStream(),true);  
//        final BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));  
        final DataInputStream in = new DataInputStream(socket.getInputStream());
        final DataOutputStream out = new DataOutputStream(socket.getOutputStream());

//        while (flaga1) {
//            try {
//                nrp = in.readInt();
//                System.out.println(nrp);
//            } catch (IOException ioe) {
//
//            }
//            try {
//                move = in.readInt();
//                System.out.println(move);
//            } catch (IOException ioe) {
//
//            }
//
//        }

while(flaga1){
    
    if(in.available()!=0){
        nrp = in.readInt();
               System.out.println(nrp);
        
    }
    else{
        
        System.out.println("nic");
    }
    if(in.available()!=0){
        move = in.readInt();
               System.out.println(move);
        
    }
     else{
        
        System.out.println("nic");
    }
    if(in.available()!=0){
        stop = in.readInt();
               System.out.println(stop);
        
    }
    else{
        
        System.out.println("nic");
    }
    
    
}

for(int i=0; i<10; i++){

        out.writeInt(nrp);
       System.out.println(nrp);


       out.writeInt(move);
       System.out.println(move);

       out.writeInt(stop);
       System.out.println(stop);
       
}



        out.close();
        in.close();
        socket.close();
    }
}