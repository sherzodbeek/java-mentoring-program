package com.bank.api;

import com.bank.dto.BankCard;
import com.bank.dto.BankCardType;
import com.bank.dto.User;

public interface Bank {
    BankCard createBankCard(User user, BankCardType bankCardType);
}
