package com.bank.service.impl;

import com.bank.dto.BankCard;
import com.bank.dto.Subscription;
import com.bank.dto.User;
import com.bank.service.Service;
import com.bank.service.excpetion.SubscriptionNotFoundException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ServiceImpl implements Service {

    public static List<User> users = new ArrayList<>();
    public static List<BankCard> bankCards = new ArrayList<>();
    public static List<Subscription> subscriptions = new ArrayList<>();

    @Override
    public void subscribe(BankCard bankCard) {
        Optional<BankCard> optionalBankCard = bankCards.parallelStream().
                filter(bc -> bc.getNumber().equals(bankCard.getNumber()))
                .findFirst();
        if (optionalBankCard.isEmpty()) {
            System.out.println("Bank card not found with " + bankCard.getNumber() + " number");
        } else {
            subscriptions.add(new Subscription(bankCard.getNumber(), LocalDate.now()));
        }
    }

    @Override
    public Subscription getSubscriptionByBankCardNumber(String cardNumber) {
        return subscriptions.parallelStream()
                .filter(s -> s.getBankcard().equals(cardNumber))
                .findFirst()
                .orElseThrow(() -> new SubscriptionNotFoundException("With " + cardNumber + " card number subscription not found"));
    }

    @Override
    public List<User> getAllUsers() {
        return users;
    }

    @Override
    public List<Subscription> getAllSubscriptionByCondition(Predicate<Subscription> predicate) {
       return subscriptions.stream().
               filter(predicate).
               collect(Collectors.toUnmodifiableList());
    }
}
