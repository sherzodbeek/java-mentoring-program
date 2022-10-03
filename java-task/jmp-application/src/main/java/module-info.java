module com.bank.application {
    requires com.bank.api.impl;
    requires com.bank.service.impl;
    requires com.bank.dto;
    uses com.bank.api.Bank;
    uses com.bank.service.Service;
}