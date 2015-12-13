/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bomberman;

/**
 *
 * @author YapYap
 */
import static bomberman.Client.flaga1;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.DataInputStream;
import java.io.DataOutputStream;

public abstract class ServClie {

    public abstract void SendMessage(int move, int action) throws IOException;

    public abstract void ReceiveMessage(int move, int action) throws IOException;

    public abstract int ReceiveMessageI(int move) throws IOException;

    public abstract void SendMessageI(int move) throws IOException;

}
