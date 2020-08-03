package com.javarush.task.task25.task2506;

public class LoggingStateThread extends Thread {
    private Thread thread;
    private State state;

    public LoggingStateThread(Thread thread) {
        this.thread = thread;
        state = thread.getState();
        System.out.println(state);
    }

    @Override
    public void run() {
        while (true) {
            if (thread.getState() != state) {
                state = thread.getState();
                System.out.println(state);
            }

            if (state == State.TERMINATED) {
                return;
            }
        }
    }
}
