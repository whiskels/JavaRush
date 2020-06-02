package com.javarush.task.task39.task3913;

import com.javarush.task.task39.task3913.query.IPQuery;
import com.javarush.task.task39.task3913.query.UserQuery;

import java.io.BufferedReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

public class LogParser implements IPQuery, UserQuery {
    private Path logDir;
    private List<Path> logFiles;
    private List<Entry> logEntries;
    private SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");

    private class Entry {
        String ip;
        String user;
        Date date;
        Event event;
        Integer taskNumber;
        Status status;

        public String getIp() {
            return ip;
        }

        public String getUser() {
            return user;
        }

        public Date getDate() {
            return date;
        }

        public Event getEvent() {
            return event;
        }

        public Integer getTaskNumber() {
            return taskNumber;
        }

        public Status getStatus() {
            return status;
        }

        @Override
        public String toString() {
            return "Entry{" +
                    "ip='" + ip + '\'' +
                    ", user='" + user + '\'' +
                    ", date=" + date +
                    ", event=" + event +
                    ", taskNumber=" + taskNumber +
                    ", status=" + status +
                    '}';
        }

        public void setFields(String[] args) throws ParseException {
            if (args != null && args.length !=0) {
                this.ip = args[0];
                this.user = args[1];
                this.date = formatter.parse(args[2]);
                if (args[3].indexOf(' ') == -1) {
                    this.event = Event.valueOf(args[3]);
                    this.taskNumber = null;
                } else {
                    String[] event = args[3].split(" ");
                    this.event = Event.valueOf(event[0]);
                    this.taskNumber = Integer.parseInt(event[1]);
                }
                this.status = Status.valueOf(args[4]);
            }
        }

        private boolean isBetween(Date after, Date before) {
            return (after == null || this.date.after(after) || this.date.equals(after)) &&
                    (before == null || this.date.before(before) || this.date.equals(before));
        }

        private boolean isEvent(Event event) {
            return this.event.equals(event);
        }

        private boolean isStatus(Status status) {
            return this.status.equals(status);
        }

        private boolean isTask(int task) {
            return this.taskNumber == task;
        }

        private boolean isIp(String ip) {
            return this.ip.equals(ip);
        }

        private boolean isUser(String user) {
            return this.user.equals(user);
        }
    }

    public LogParser(Path logDir) {
        this.logDir = logDir;
        this.logFiles = setLogFiles();
        this.logEntries = getAllEntries();
    }

    private List<Path> setLogFiles() {
        if (this.logDir != null && Files.exists(this.logDir)) {
            List<Path> logFiles = new ArrayList<Path>();
            if (Files.isDirectory(this.logDir)) {
                try {
                    logFiles = Files.walk(this.logDir)
                            .filter(s -> s.toString().endsWith(".log"))
                            .map(Path::toAbsolutePath).sorted().collect(Collectors.toList());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                logFiles.add(this.logDir);
            }
            return logFiles;
        } else {
            return null;
        }
    }

    private List<Entry> getAllEntries() {
        List<Entry> result = new ArrayList<>();
        if (this.logFiles != null && this.logFiles.size() != 0) {

            for (Path logFile : this.logFiles) {
                try (BufferedReader r = Files.newBufferedReader(logFile)) {
                    while (r.ready()) {
                        Entry currentEntry = new Entry();
                        String[] lineContent = r.readLine().split("\t");
                        currentEntry.setFields(lineContent);
                        result.add(currentEntry);
                    }
                } catch (Exception e) {
                    System.out.println(String.format("Some exception occurred %s", e.getMessage()));
                }
            }
        }
        return result;
    }

    private List<Entry> getEntriesByRangeOfDate(Date after, Date before) {
        return this.logEntries.stream()
                .filter(e -> e.isBetween(after,before))
                .collect(Collectors.toList());
    }

    @Override
    public int getNumberOfUniqueIPs(Date after, Date before) {
        return getUniqueIPs(after,before).size();
    }

    @Override
    public Set<String> getUniqueIPs(Date after, Date before) {
        return this.logEntries.stream()
                .filter(e -> e.isBetween(after,before))
                .map(Entry::getIp)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<String> getIPsForUser(String user, Date after, Date before) {
        return this.logEntries.stream()
                .filter(e -> e.isBetween(after,before))
                .filter(e -> e.isUser(user))
                .map(Entry::getIp)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<String> getIPsForEvent(Event event, Date after, Date before) {
        return this.logEntries.stream()
                .filter(e -> e.isBetween(after,before))
                .filter(e -> e.isEvent(event))
                .map(Entry::getIp)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<String> getIPsForStatus(Status status, Date after, Date before) {
        return this.logEntries.stream()
                .filter(e -> e.isBetween(after,before))
                .filter(e -> e.isStatus(status))
                .map(Entry::getIp)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<String> getAllUsers() {
        return this.logEntries.stream()
                .map(Entry::getUser)
                .collect(Collectors.toSet());
    }

    @Override
    public int getNumberOfUsers(Date after, Date before) {
        return this.logEntries.stream()
                .filter(e -> e.isBetween(after,before))
                .map(Entry::getUser)
                .collect(Collectors.toSet())
                .size();
    }

    @Override
    public int getNumberOfUserEvents(String user, Date after, Date before) {
        return this.logEntries.stream()
                .filter(e -> e.isBetween(after,before))
                .filter(e -> e.isUser(user))
                .filter(e -> e.event != null)
                .map(Entry::getEvent)
                .collect(Collectors.toSet())
                .size();
    }

    @Override
    public Set<String> getUsersForIP(String ip, Date after, Date before) {
        return this.logEntries.stream()
                .filter(e -> e.isBetween(after,before))
                .filter(e -> e.isIp(ip))
                .map(Entry::getUser)
                .collect(Collectors.toSet());
    }

    public Set<String> getUsersForEvent(Date after, Date before, Event event) {
        return this.logEntries.stream()
                .filter(e -> e.isBetween(after,before))
                .filter(e -> e.isEvent(event))
                .map(Entry::getUser)
                .collect(Collectors.toSet());
    }

    public Set<String> getUsersForEventAndTask(Date after, Date before, Event event, int task) {
        return this.logEntries.stream()
                .filter(e -> e.isBetween(after,before))
                .filter(e -> e.isEvent(event))
                .filter(e -> e.isTask(task))
                .map(Entry::getUser)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<String> getLoggedUsers(Date after, Date before) {
        return getUsersForEvent(after,before,Event.LOGIN);
    }

    @Override
    public Set<String> getDownloadedPluginUsers(Date after, Date before) {
        return getUsersForEvent(after,before,Event.DOWNLOAD_PLUGIN);
    }

    @Override
    public Set<String> getWroteMessageUsers(Date after, Date before) {
        return getUsersForEvent(after,before,Event.WRITE_MESSAGE);
    }

    @Override
    public Set<String> getSolvedTaskUsers(Date after, Date before) {
        return getUsersForEvent(after,before,Event.SOLVE_TASK);
    }

    @Override
    public Set<String> getSolvedTaskUsers(Date after, Date before, int task) {
        return getUsersForEventAndTask(after,before,Event.SOLVE_TASK,task);
    }

    @Override
    public Set<String> getDoneTaskUsers(Date after, Date before) {
        return getUsersForEvent(after,before,Event.DONE_TASK);
    }

    @Override
    public Set<String> getDoneTaskUsers(Date after, Date before, int task) {
        return getUsersForEventAndTask(after,before,Event.DONE_TASK,task);
    }
}