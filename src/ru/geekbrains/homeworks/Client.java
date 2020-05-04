package ru.geekbrains.homeworks;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    volatile int lol;

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket(InetAddress.getLocalHost(), 8000);
        DataInputStream in = new DataInputStream(socket.getInputStream());
        DataOutputStream out = new DataOutputStream(socket.getOutputStream());
        Scanner input = new Scanner(System.in);

//        Thread serverMessage = new Thread(() -> {
//            while (true){
//                try {
//                    String clientMessage = in.readUTF();
//                    System.out.println("Message from client1: " + clientMessage);
//                    out.writeUTF("Message from clientt: " + clientMessage);
//                    out.flush();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//
//        });
//        serverMessage.start();
        while (true) {
            String message = input.nextLine();

                if (message.equals("quit")) {
                    System.out.println("Finished!");
                    out.writeUTF(message);
                    out.flush();
                    break;
                }
                out.writeUTF(message);
                out.flush();
                String messageFromServer = in.readUTF();
                System.out.println(messageFromServer);
            }
        }


}