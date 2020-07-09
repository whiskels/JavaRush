package com.javarush.task.task30.task3008;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Server {
    private static Map<String, Connection> connectionMap = new ConcurrentHashMap<>();

    public static void main(String... args) {
        ConsoleHelper.writeMessage("Please enter server's port");

        try (ServerSocket serverSocket = new ServerSocket(ConsoleHelper.readInt())) {
            ConsoleHelper.writeMessage("Server started");

            while (true) {
                new Handler(serverSocket.accept()).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void sendBroadcastMessage(Message message) {
        connectionMap.forEach((s, connection) -> {
            try {
                connection.send(message);
            } catch (IOException e) {
                ConsoleHelper.writeMessage(String.format("Couldn't deliver message: {}",
                        connection.getRemoteSocketAddress()));
                e.printStackTrace();
            }
        });
    }

    private static class Handler extends Thread {
        private Socket socket;

        public Handler(Socket socket) {
            this.socket = socket;
        }

        public void run() {
            ConsoleHelper.writeMessage(String.format("Connection established with %s", socket.getRemoteSocketAddress()));
            String userName = null;
            
            try (Connection connection = new Connection(socket)) {
                userName = serverHandshake(connection);
                sendBroadcastMessage(new Message(MessageType.USER_ADDED, userName));
                notifyUsers(connection, userName);
                serverMainLoop(connection, userName);
            } catch (IOException | ClassNotFoundException e) {
                ConsoleHelper.writeMessage("Exception while broadcasting data to remote address");
            }

            if (userName != null && !userName.isEmpty()) {
                connectionMap.remove(userName);
                sendBroadcastMessage(new Message(MessageType.USER_REMOVED, userName));
            }
            ConsoleHelper.writeMessage(String.format("Connection closed with %s", socket.getRemoteSocketAddress()));
        }

        private String serverHandshake(Connection connection) throws IOException, ClassNotFoundException {
            Message userName;
            do {
                connection.send(new Message(MessageType.NAME_REQUEST));
                userName = connection.receive();
            } while (userName.getType() != MessageType.USER_NAME
                    || userName.getData().isEmpty()
                    || connectionMap.containsKey(userName.getData()));

            connectionMap.put(userName.getData(), connection);
            connection.send(new Message(MessageType.NAME_ACCEPTED));
            return userName.getData();
        }

        private void notifyUsers(Connection connection, String userName) throws IOException {
            connectionMap.forEach((k, v) -> {
                try {
                    if (!k.equals(userName)) connection.send(new Message(MessageType.USER_ADDED, k));
                } catch (IOException e) {
                }
            });
        }

        private void serverMainLoop(Connection connection, String userName) throws IOException, ClassNotFoundException {
            while (true) {
                Message input = connection.receive();
                if (input.getType() == MessageType.TEXT) {
                    Message output = new Message(MessageType.TEXT, String.format("%s: %s", userName, input.getData()));
                    sendBroadcastMessage(output);
                } else {
                    ConsoleHelper.writeMessage("Error - wrong message type");
                }
            }
        }
    }
}

