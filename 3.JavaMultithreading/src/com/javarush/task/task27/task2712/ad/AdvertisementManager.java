package com.javarush.task.task27.task2712.ad;


import com.javarush.task.task27.task2712.ConsoleHelper;
import com.javarush.task.task27.task2712.statistic.StatisticManager;
import com.javarush.task.task27.task2712.statistic.event.VideoSelectedEventDataRow;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class AdvertisementManager {
    private final AdvertisementStorage storage = AdvertisementStorage.getInstance();
    private int timeSeconds;

    public AdvertisementManager(int timeSeconds) {
        this.timeSeconds = timeSeconds;
    }

    public void processVideos() {
        if (storage.list().isEmpty()) {
            throw new NoVideoAvailableException();
        }

        List<Advertisement> adList = storage.list().
                stream().
                filter(e -> e.getHits() > 0).
                collect(Collectors.toList());

        List<AdvertisementSeq> compilation = getCombinations(adList);

        adList = compilation.stream().max(
                Comparator.comparingLong(
                        AdvertisementSeq::getAmountPerOneDisplaying
                ).thenComparing(
                        AdvertisementSeq::getDuration
                ).thenComparing(
                        Comparator.comparingInt(AdvertisementSeq::getSize).reversed()
                ))
                .get().getAdvertisements();


        Collections.sort(adList,
                Comparator.
                        comparingLong(
                                Advertisement::getAmountPerOneDisplaying)
                        .reversed()
                        .thenComparing(Advertisement::getAmountPerSecond)
        );

        int totalDuration = 0;
        int amount = 0;

        for (Advertisement ad : adList) {
            totalDuration += ad.getDuration();
            amount += ad.getAmountPerOneDisplaying();
        }

        StatisticManager.getInstance().register(new VideoSelectedEventDataRow(adList, amount, totalDuration));

        for (Advertisement a : adList) {
            a.revalidate();
            String string = String.format("%s is displaying... %d, %d",
                    a.getName(),
                    a.getAmountPerOneDisplaying(),
                    a.getAmountPerSecond()
            );
            ConsoleHelper.writeMessage(string);
        }
    }

    private List<AdvertisementSeq> getCombinations(List<Advertisement> stor) {
        List<AdvertisementSeq> combinations = new LinkedList<>();
        getCombinations(0, 0, new LinkedList<>(), combinations, stor);
        return combinations;
    }

    private void getCombinations(int start, int range, List<Advertisement> currentComb, List<AdvertisementSeq> combinations, List<Advertisement> storage) {
        for (int i = start + range; i < storage.size(); i++) {
            List<Advertisement> tmp = new LinkedList<>(currentComb);
            tmp.add(storage.get(i));
            AdvertisementSeq seq = new AdvertisementSeq(tmp);
            if (timeSeconds >= seq.getDuration()) {
                combinations.add(seq); // добавить список
                getCombinations(start, i + 1, tmp, combinations, storage);
            }
        }
    }

    private class AdvertisementSeq {
        private List<Advertisement> advertisements = null;
        private int duration = -1;
        private long amountPerOneDisplaying = -1;
        private int size;

        public AdvertisementSeq(List<Advertisement> list) {
            this.advertisements = list;
        }

        public List<Advertisement> getAdvertisements() {
            return advertisements;
        }

        public int getDuration() {
            if (duration < 0) {
                this.duration = advertisements.stream()
                        .mapToInt(Advertisement::getDuration)
                        .sum();
            }
            return duration;
        }

        public long getAmountPerOneDisplaying() {
            if (amountPerOneDisplaying < 0) {
                this.amountPerOneDisplaying = advertisements.stream()
                        .mapToLong(Advertisement::getAmountPerOneDisplaying)
                        .sum();
            }
            return amountPerOneDisplaying;
        }

        public int getSize() {
            return advertisements.size();
        }
    }
}
