module com.bank.api.impl {
   requires transitive com.bank.api;
   requires com.bank.dto;
   requires org.apache.commons.lang3;
   provides com.bank.api.Bank with com.bank.api.impl.BankImpl;
   exports com.bank.api.impl;
}