package ru.geekbrains.homeworks;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {

    static boolean flag = true;

    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(8000);
        Socket con = server.accept();
        System.out.println("Connected " + con.getInetAddress());
        DataInputStream in = new DataInputStream(con.getInputStream());
        DataOutputStream out = new DataOutputStream(con.getOutputStream());
        Scanner input = new Scanner(System.in);
        Thread inputService = new Thread(() -> {
            while (flag) {

                String command = input.nextLine();
                if (flag) {
                    try {
                       // out.writeUTF("Command from server: " + command);
                       // out.flush();
                        out.writeUTF("Сообщение сервера  " + command);
                        out.flush();
                        String messageFromServer = in.readUTF();
                        System.out.println(messageFromServer);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        inputService.start();

        while (true) {
            String clientMessage = in.readUTF();

                if (clientMessage.equals("quit")) {
                    System.out.println("Shut down");
                    flag = false;
                    return;
                }
                System.out.println("Message from client: " + clientMessage);
                if (clientMessage.contains("Hello")) {
                    out.writeUTF("Hello " + con.getInetAddress() + ", know all about you)");
                    out.flush();
                } else {
                    out.writeUTF("Message from server: " + clientMessage);
                    out.flush();
                }

            }

    }
}