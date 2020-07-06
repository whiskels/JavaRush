package com.javarush.task.task26.task2613;

import com.javarush.task.task26.task2613.exception.NotEnoughMoneyException;

import java.util.*;

public class CurrencyManipulator {
    private String currencyCode;
    private Map<Integer, Integer> denominations = new TreeMap<>(Collections.reverseOrder());
    ;

    public CurrencyManipulator(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void addAmount(int denomination, int count) {
        if (denominations.containsKey(denomination)) {
            denominations.put(denomination, denominations.get(denomination) + count);
        } else denominations.put(denomination, count);
    }

    public int getTotalAmount() {
        return denominations.entrySet().stream()
                .mapToInt(e -> e.getKey() * e.getValue())
                .sum();
    }

    public String printState() {
        return String.format("%s - %d", currencyCode, getTotalAmount());
    }

    public boolean hasMoney() {
        return denominations.size() != 0;
    }

    public boolean isAmountAvailable(final int expectedAmount) {
        return getTotalAmount() >= expectedAmount;
    }

    public Map<Integer, Integer> withdrawAmount(final int expectedAmount) throws NotEnoughMoneyException {
        Map<Integer, Integer> updatedDenominations = new HashMap<>();

        List<Integer> nominalAvailable = new ArrayList<>(denominations.keySet());

        int expectedRemainder = expectedAmount;
        for (int i = 0; i < nominalAvailable.size(); i++) {
            final int nominalCurrent = nominalAvailable.get(i);
            int amountToWithdraw = 0;
            int remainingAmount = denominations.get(nominalCurrent) - 1;
            expectedRemainder -= nominalCurrent;

            while (expectedRemainder >= 0 && remainingAmount >= 0) {
                expectedRemainder -= nominalCurrent;
                amountToWithdraw++;
                remainingAmount--;
            }

            expectedRemainder += nominalCurrent;
            if (amountToWithdraw > 0) {
                updatedDenominations.put(nominalCurrent, amountToWithdraw);
            }
            if (expectedRemainder == 0) {
                break;
            }
        }

        if (expectedRemainder != 0) {
            throw new NotEnoughMoneyException();
        }

        for (Map.Entry<Integer, Integer> item : updatedDenominations.entrySet()) {
            denominations.put(item.getKey(), denominations.get(item.getKey()) - item.getValue());
        }

        return updatedDenominations;
    }

}
