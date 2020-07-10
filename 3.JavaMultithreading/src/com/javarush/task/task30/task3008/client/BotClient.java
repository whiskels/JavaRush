package com.javarush.task.task30.task3008.client;

import com.javarush.task.task30.task3008.ConsoleHelper;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class BotClient extends Client {

    public static void main(String... args) {
        new BotClient().run();
    }

    @Override
    protected SocketThread getSocketThread() {
        return new BotSocketThread();
    }

    @Override
    protected boolean shouldSendTextFromConsole() {
        return false;
    }

    @Override
    protected String getUserName() {
        return String.format("date_bot_%d", (int) (Math.random() * 100));
    }

    public class BotSocketThread extends SocketThread {
        @Override
        protected void clientMainLoop() throws IOException, ClassNotFoundException {
            sendTextMessage("Привет чатику. Я бот. Понимаю команды: дата, день, месяц, год, время, час, минуты, секунды.");
            super.clientMainLoop();
        }

        @Override
        protected void processIncomingMessage(String message) {
            if (message == null || message.isEmpty()) {
                return;
            }

            ConsoleHelper.writeMessage(message);
            if (message.contains(":")) {
                String[] content = message.split(":");
                if (content.length != 2) {
                    return;
                }

                SimpleDateFormat sdf;
                switch (content[1].trim()) {
                    case "дата":
                        sdf = new SimpleDateFormat("d.MM.YYYY");
                        break;
                    case "день":
                        sdf = new SimpleDateFormat("d");
                        break;
                    case "месяц":
                        sdf = new SimpleDateFormat("MMMM");
                        break;
                    case "год":
                        sdf = new SimpleDateFormat("YYYY");
                        break;
                    case "время":
                        sdf = new SimpleDateFormat("H:mm:ss");
                        break;
                    case "час":
                        sdf = new SimpleDateFormat("H");
                        break;
                    case "минуты":
                        sdf = new SimpleDateFormat("m");
                        break;
                    case "секунды":
                        sdf = new SimpleDateFormat("s");
                        break;
                    default:
                        return;
                }
                Date date = Calendar.getInstance().getTime();
                sendTextMessage(String.format("Информация для %s: %s", content[0], sdf.format(date)));
            }
        }
    }
}
