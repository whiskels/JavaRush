package com.javarush.task.task30.task3008.client;

import com.javarush.task.task30.task3008.Connection;
import com.javarush.task.task30.task3008.ConsoleHelper;
import com.javarush.task.task30.task3008.Message;
import com.javarush.task.task30.task3008.MessageType;

import java.io.IOException;

public class Client {
    protected Connection connection;
    private volatile boolean clientConnected = false;

    public static void main(String... args) {
        new Client().run();
    }

    public void run() {
        SocketThread socketThread = this.getSocketThread();
        socketThread.setDaemon(true);
        socketThread.start();
        synchronized (this) {
            try {
                 this.wait();
            } catch (InterruptedException e) {
                ConsoleHelper.writeMessage("Exception while waiting for thread notification");
            }
        }

        if (clientConnected) {
            ConsoleHelper.writeMessage("Connection established.\nEnter 'exit' command to exit.");
        } else {
            ConsoleHelper.writeMessage("Exception happened while client was working");
            return;
        }

        while (clientConnected) {
            String messageText = ConsoleHelper.readString();
            if (messageText.equals("exit")) {
                break;
            }

            if (shouldSendTextFromConsole()) {
                sendTextMessage(messageText);
            }
        }
    }

    protected String getServerAddress() {
        ConsoleHelper.writeMessage("Please enter server's address");
        return ConsoleHelper.readString();
    }

    protected int getServerPort() {
        ConsoleHelper.writeMessage("Please enter server's port");
        return ConsoleHelper.readInt();
    }

    protected String getUserName() {
        ConsoleHelper.writeMessage("Please enter user name");
        return ConsoleHelper.readString();
    }

    protected boolean shouldSendTextFromConsole() {
        return true;
    }

    protected SocketThread getSocketThread() {
        return new SocketThread();
    }

    protected void sendTextMessage(String text) {
        try {
            connection.send(new Message(MessageType.TEXT, text));
        } catch (IOException e) {
            ConsoleHelper.writeMessage("Exception caught while trying to send a message");
            clientConnected = false;
        }
    }

    public class SocketThread extends Thread {
        @Override
        public void run() {
            super.run();
        }
    }
}
