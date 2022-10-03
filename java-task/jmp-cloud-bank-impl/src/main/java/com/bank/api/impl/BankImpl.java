package com.bank.api.impl;

import com.bank.api.Bank;
import com.bank.dto.*;
import org.apache.commons.lang3.RandomStringUtils;

public class BankImpl implements Bank {

    @Override
    public BankCard createBankCard(User user, BankCardType bankCardType) {
        var cardNumber = generateRandomCardNumber();
//        return bankCardType.equals(BankCardType.CREDIT) ?
//                new CreditBankCard(cardNumber, user)
//                : new DebitBankCard(cardNumber, user);


        if (bankCardType.equals(BankCardType.CREDIT)) {
            BankCardInterface creditBankCardInterface = CreditBankCard::new;
            return creditBankCardInterface.getBankCard(cardNumber, user);
        } else {
            BankCardInterface debitBankCardInterface = DebitBankCard::new;
            return debitBankCardInterface.getBankCard(cardNumber, user);
        }
    }

    private String generateRandomCardNumber() {
        var length = 16;
        var useLetters = false;
        var useNumbers = true;
        return RandomStringUtils.random(length, useLetters, useNumbers);
    }
}
