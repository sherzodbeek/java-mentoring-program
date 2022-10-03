package com.bank.application;

import com.bank.api.Bank;
import com.bank.api.impl.BankImpl;
import com.bank.dto.BankCardType;
import com.bank.dto.Subscription;
import com.bank.dto.User;
import com.bank.service.Service;
import com.bank.service.excpetion.SubscriptionNotFoundException;
import com.bank.service.impl.ServiceImpl;

import java.time.LocalDate;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Application {

    public static void main(String[] args) {
        // create users
        var user1 = new User("UserName1", "Surname1", LocalDate.of(1998, 5, 10));
        var user2 = new User("UserName2", "Surname2", LocalDate.of(1999, 4, 12));
        var user3 = new User("UserName3", "Surname3", LocalDate.of(1995, 10, 4));
        ServiceImpl.users.add(user1);
        ServiceImpl.users.add(user2);
        ServiceImpl.users.add(user3);

        // create bank card
        Bank bank = new BankImpl();
        var bankCard1 = bank.createBankCard(user1, BankCardType.CREDIT);
        var bankCard2 = bank.createBankCard(user2, BankCardType.DEBIT);
        var bankCard3 = bank.createBankCard(user3, BankCardType.CREDIT);
        ServiceImpl.bankCards.add(bankCard1);
        ServiceImpl.bankCards.add(bankCard2);
        ServiceImpl.bankCards.add(bankCard3);


        Service service = new ServiceImpl();

        // create subscribe
        service.subscribe(bankCard1);
        service.subscribe(bankCard2);

        // get subscribes
        try {
            var subscription = service.getSubscriptionByBankCardNumber(bankCard1.getNumber());
            System.out.println(subscription);
        } catch (SubscriptionNotFoundException ex) {
            System.out.println(ex.getMessage());
        }


        // get all users
        List<User> unmodifiableUserList = service.getAllUsers().stream().collect(Collectors.toUnmodifiableList());
        unmodifiableUserList.forEach(System.out::println);

        // get average age
        double averageUserAge = service.getAverageUserAge();
        System.out.println(averageUserAge);

        // isPayable
        boolean payable = Service.isPayable(user3);
        System.out.println(payable);

        // get all subscription by condition
        Predicate<Subscription> predicateWithContainsCardNumber = getPredicateWithContainsCardNumber("3");

        List<Subscription> allSubscriptionByCondition = service.getAllSubscriptionByCondition(predicateWithContainsCardNumber);
        System.out.println(allSubscriptionByCondition);
    }

    private static Predicate<Subscription> getPredicateWithContainsCardNumber(String cardNumber) {
        return subscription -> subscription.getBankcard().contains(cardNumber);
    }
}
