package com.javarush.task.task39.task3913;

import com.javarush.task.task39.task3913.query.*;

import java.io.BufferedReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class LogParser implements IPQuery, UserQuery, DateQuery, EventQuery, QLQuery {
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
        private boolean isBetweenStupid(Date after, Date before) {
            return (after == null || this.date.after(after)) &&
                    (before == null || this.date.before(before));
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
                .filter(e -> e.isBetweenStupid(after,before))
                .collect(Collectors.toList());
    }

    @Override
    public Set<Object> execute(String query) {
        if (query != null && query.length() !=0 ) {
            String search = "";
            String key = "";
            String after = "";
            String before = "";
            String value = "";
            Set<Object> set = new HashSet<>();
            if (query.contains("between")) {
                try {
                    String regex = "get (?<search>\\w+) for (?<key>\\w+) = \"(?<value>.*?)\" and date between \"(?<after>.*?)\" and \"(?<before>.*?)\"";
                    Pattern pattern = Pattern.compile(regex);
                    Matcher matcher = pattern.matcher(query);
                    if (matcher.find()) {
                        search = matcher.group("search");
                        key = matcher.group("key");
                        value = matcher.group("value");
                        after = matcher.group("after");
                        before = matcher.group("before");
                    }
                    Date dateAfter = null;
                    Date dateBefore = null;
                    if (!after.equals("[after]"))
                        dateAfter = formatter.parse(after);
                    if (!before.equals("[before]"))
                        dateBefore = formatter.parse(before);
                    for (Entry log : getEntriesByRangeOfDate(dateAfter,dateBefore)) {
                        if (value.contains("any")
                                || key.equals("ip") && value.equals(log.getIp())
                                || key.equals("user") && value.equals(log.getUser())
                                || key.equals("date") && formatter.parse(value).equals(log.getDate())
                                || key.equals("event") && value.equals(String.valueOf(log.event))
                                || key.equals("status") && value.equals(String.valueOf(log.status))
                        ) { switch (search) {
                                case "ip":
                                    set.add(log.ip);
                                    break;
                                case "user":
                                    set.add(log.user);
                                    break;
                                case "date":
                                    set.add(log.date);
                                    break;
                                case "event":
                                    set.add(log.event);
                                    break;
                                case "status":
                                    set.add(log.status);
                                    break;
                            }
                        }
                    }
                } catch (Exception e) {
                    return null;
                }
                return set;
            } else if (query.contains("=")) {
                try {
                    Pattern pattern = Pattern.compile("get (?<search>\\w+) for (?<key>\\w+) = \"(?<value>.*?)\"");
                    Matcher matcher = pattern.matcher(query);
                    matcher.find();
                    search = matcher.group("search");
                    key = matcher.group("key");
                    value = matcher.group("value");
                    for (Entry logEntry : this.logEntries) {
                        Object e = Entry.class.getDeclaredField(key).get(logEntry);
                        if (e instanceof Date && ((Date) e).getTime() == formatter.parse(value).getTime()
                                || e instanceof Event && (Event) e == Event.valueOf(value)
                                || e instanceof Status && (Status) e == Status.valueOf(value)
                                || e instanceof String && e.toString().equals(value)) {
                            set.add(Entry.class.getDeclaredField(search).get(logEntry));
                        }
                    }
                } catch (Exception e) {
                    return null;
                }
                return set;
            } else {
                String regex = "get (?<search>\\w+)";
                Pattern pattern = Pattern.compile(regex);
                Matcher matcher = pattern.matcher(query);
                if (matcher.find()) {
                    search = matcher.group("search");
                }
                switch (search) {
                    case "ip":
                        return new HashSet<>(getUniqueIPs(null, null));
                    case "user":
                        return new HashSet<>(getAllUsers());
                    case "event":
                        return new HashSet<>(getAllEvents(null, null));
                    case "status":
                         return new HashSet<>(getAllStatuses());
                    case "date":
                         return new HashSet<>(getAllDates());
                }
            }
        }
        return null;
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
                .filter(e -> e.isBetween(after,before)
                && e.isUser(user))
                .map(Entry::getIp)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<String> getIPsForEvent(Event event, Date after, Date before) {
        return this.logEntries.stream()
                .filter(e -> e.isBetween(after,before)
                && e.isEvent(event))
                .map(Entry::getIp)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<String> getIPsForStatus(Status status, Date after, Date before) {
        return this.logEntries.stream()
                .filter(e -> e.isBetween(after,before)
                && e.isStatus(status))
                .map(Entry::getIp)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<String> getAllUsers() {
        return this.logEntries.stream()
                .map(Entry::getUser)
                .collect(Collectors.toSet());
    }

    public Set<Status> getAllStatuses() {
        return this.logEntries.stream()
                .map(Entry::getStatus)
                .collect(Collectors.toSet());
    }

    public Set<Date> getAllDates() {
        return this.logEntries.stream()
                .map(Entry::getDate)
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
                .filter(e -> e.isBetween(after,before)
                && e.isUser(user)
                && e.event != null)
                .map(Entry::getEvent)
                .collect(Collectors.toSet())
                .size();
    }

    @Override
    public Set<String> getUsersForIP(String ip, Date after, Date before) {
        return this.logEntries.stream()
                .filter(e -> e.isBetween(after,before)
                && e.isIp(ip))
                .map(Entry::getUser)
                .collect(Collectors.toSet());
    }

    public Set<String> getUsersForEvent(Date after, Date before, Event event) {
        return this.logEntries.stream()
                .filter(e -> e.isBetween(after,before)
                && e.isEvent(event))
                .map(Entry::getUser)
                .collect(Collectors.toSet());
    }

    public Set<String> getUsersForEventAndTask(Date after, Date before, Event event, int task) {
        return this.logEntries.stream()
                .filter(e -> e.isBetween(after,before)
                && e.isEvent(event)
                && e.isTask(task))
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

    @Override
    public Set<Date> getDatesForUserAndEvent(String user, Event event, Date after, Date before) {
        return this.logEntries.stream()
                .filter(e -> e.isBetween(after,before)
                && e.isEvent(event)
                && e.isUser(user))
                .map(Entry::getDate)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<Date> getDatesWhenSomethingFailed(Date after, Date before) {
        return this.logEntries.stream()
                .filter(e -> e.isBetween(after,before)
                && e.isStatus(Status.FAILED))
                .map(Entry::getDate)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<Date> getDatesWhenErrorHappened(Date after, Date before) {
        return this.logEntries.stream()
                .filter(e -> e.isBetween(after,before)
                && e.isStatus(Status.ERROR))
                .map(Entry::getDate)
                .collect(Collectors.toSet());
    }

    @Override
    public Date getDateWhenUserLoggedFirstTime(String user, Date after, Date before) {
        return this.logEntries.stream()
                .filter(Objects::nonNull)
                .filter(e -> e.isBetween(after,before)
                && e.isUser(user)
                && e.isEvent(Event.LOGIN))
                .map(Entry::getDate)
                .sorted()
                .findFirst()
                .orElse(null);
    }

    @Override
    public Date getDateWhenUserSolvedTask(String user, int task, Date after, Date before) {
        return logEntries.stream()
                .filter(Objects::nonNull)
                .filter(e -> e.isUser(user)
                && e.isEvent(Event.SOLVE_TASK)
                && e.isBetween(after, before)
                && e.isTask(task))
                .map(Entry::getDate)
                .sorted()
                .findFirst()
                .orElse(null);
    }

    @Override
    public Date getDateWhenUserDoneTask(String user, int task, Date after, Date before) {
        return logEntries.stream()
                .filter(Objects::nonNull)
                .filter(e -> e.isUser(user)
                && e.isEvent(Event.DONE_TASK)
                && e.isBetween(after, before)
                && e.isTask(task))
                .map(Entry::getDate)
                .sorted()
                .findFirst()
                .orElse(null);
    }

    @Override
    public Set<Date> getDatesWhenUserWroteMessage(String user, Date after, Date before) {
       return logEntries.stream()
                .filter(Objects::nonNull)
                .filter(e -> e.isUser(user)
                && e.isEvent(Event.WRITE_MESSAGE)
                && e.isBetween(after, before))
                .map(Entry::getDate)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<Date> getDatesWhenUserDownloadedPlugin(String user, Date after, Date before) {
        return logEntries.stream()
                .filter(Objects::nonNull)
                .filter(e -> e.isUser(user)
                && e.isEvent(Event.DOWNLOAD_PLUGIN)
                && e.isBetween(after, before))
                .map(Entry::getDate)
                .collect(Collectors.toSet());
    }

    @Override
    public int getNumberOfAllEvents(Date after, Date before) {
        return getAllEvents(after,before).size();
    }

    @Override
    public Set<Event> getAllEvents(Date after, Date before) {
        return logEntries.stream()
                .filter(Objects::nonNull)
                .filter(e -> e.isBetween(after, before))
                .map(Entry::getEvent)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<Event> getEventsForIP(String ip, Date after, Date before) {
        return logEntries.stream()
                .filter(Objects::nonNull)
                .filter(e -> e.isBetween(after, before)
                && e.isIp(ip))
                .map(Entry::getEvent)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<Event> getEventsForUser(String user, Date after, Date before) {
        return logEntries.stream()
                .filter(Objects::nonNull)
                .filter(e -> e.isBetween(after, before)
                && e.isUser(user))
                .map(Entry::getEvent)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<Event> getFailedEvents(Date after, Date before) {
        return logEntries.stream()
                .filter(Objects::nonNull)
                .filter(e -> e.isBetween(after, before)
                && e.isStatus(Status.FAILED))
                .map(Entry::getEvent)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<Event> getErrorEvents(Date after, Date before) {
        return logEntries.stream()
                .filter(Objects::nonNull)
                .filter(e -> e.isBetween(after, before)
                && e.isStatus(Status.ERROR))
                .map(Entry::getEvent)
                .collect(Collectors.toSet());
    }

    @Override
    public int getNumberOfAttemptToSolveTask(int task, Date after, Date before) {
        return (int) logEntries.stream()
                .filter(e -> e.isEvent(Event.SOLVE_TASK)
                        && e.isTask(task)
                        && e.isBetween(after,before))
                .count();
    }

    @Override
    public int getNumberOfSuccessfulAttemptToSolveTask(int task, Date after, Date before) {
        return (int) logEntries.stream()
                .filter(e -> e.isEvent(Event.DONE_TASK)
                        && e.isTask(task)
                        && e.isBetween(after,before))
                .count();
    }

    @Override
    public Map<Integer, Integer> getAllSolvedTasksAndTheirNumber(Date after, Date before) {
        return logEntries.stream()
                .filter(e -> e.isEvent(Event.SOLVE_TASK)
                        && e.isBetween(after, before))
                .collect(Collectors.toMap(
                        Entry::getTaskNumber,
                        log -> 1,
                        Integer::sum));
    }

    @Override
    public Map<Integer, Integer> getAllDoneTasksAndTheirNumber(Date after, Date before) {
        return logEntries.stream()
                .filter(e -> e.isEvent(Event.DONE_TASK)
                        && e.isBetween(after, before))
                .collect(Collectors.toMap(
                        Entry::getTaskNumber,
                        log -> 1,
                        Integer::sum));
    }
}