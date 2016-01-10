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

public class Client extends ServClie {

    private static final int PORT = 50000;
    private static final String HOST = "localhost";
    static boolean flaga1 = true, flaga = true;
    private static Socket socket;

    int nrp, move, stop, action;

    public boolean CreateClient() throws IOException {
        socket = null;
nrp =0;
        while (flaga) {
            
            try {
                socket = new Socket(HOST, PORT);
            } catch (Exception e) {
                System.err.println("Could not connect to " + HOST + ":" + PORT);
                nrp++;
                if(nrp==5){
                    return false;
                }
                //System.exit(1);
            }
            if (socket != null) {
                flaga = false;
                
            }

        }
        return true;
//   
//            Thread t2 = new Thread() {
//            public void run(){
//                int y =1;
//                int x=2;
//                boolean fl = true;
//
//                
//                    while(fl){try{
//                        
//            
//
//                ReceiveMessage(y,x);}
//
//                
//                catch(Exception e){
//            }}
//            }     };
//           t2.start();

        //socket.close();
    }
    
    public DataInputStream getDataInputStream ()throws IOException{
        
        final DataInputStream in = new DataInputStream(socket.getInputStream());
        
        return in;
    }
    

    public void SendMessage(int move, int action) throws IOException {

        final DataOutputStream out = new DataOutputStream(socket.getOutputStream());

        System.out.println("Send: ");

        out.writeInt(move);
        System.out.println(move);

        out.writeInt(action);
        System.out.println(action);
    }

    public void SendMessageI(int move) throws IOException {

        final DataOutputStream out = new DataOutputStream(socket.getOutputStream());

        System.out.println("Send: ");

        out.writeInt(move);
        System.out.println(move);

    }

    public void ReceiveMessage(int move, int action) throws IOException {

        final DataInputStream in = new DataInputStream(socket.getInputStream());

        if (in.available() != 0) {

            System.out.println("Received: ");

            move = in.readInt();
            System.out.println(move);

            action = in.readInt();
            System.out.println(action);
        }

    }

    public int ReceiveMessageI(int move, DataInputStream in) throws IOException {


        if (in.available() != 0) {

            System.out.println("Received: ");

            move = in.readInt();
            System.out.println(move);
        }
        return move;

    }

    public void ClientClose() throws IOException {

        socket.close();

    }

}
