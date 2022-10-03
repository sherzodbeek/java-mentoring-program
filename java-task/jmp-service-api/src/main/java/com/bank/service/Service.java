package com.bank.service;

import com.bank.dto.BankCard;
import com.bank.dto.Subscription;
import com.bank.dto.User;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.function.Predicate;

public interface Service {

    void subscribe(BankCard bankCard);

    Subscription getSubscriptionByBankCardNumber(String cardNumber);

    List<User> getAllUsers();

    default double getAverageUserAge() {
        List<User> users = getAllUsers();
        return users.stream().mapToLong(user -> {
            var now = LocalDate.now();
            return ChronoUnit.YEARS.between(user.getBirthday(), now);
        }).average().orElse(0);
    }

    static boolean isPayable(User user) {
        var now = LocalDate.now();
        var birthday = user.getBirthday();
        return ChronoUnit.YEARS.between(birthday, now) > 18;
    }

    List<Subscription> getAllSubscriptionByCondition(Predicate<Subscription> predicate);
}
