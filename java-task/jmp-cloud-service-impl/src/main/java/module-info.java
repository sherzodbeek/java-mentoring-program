module com.bank.service.impl {
    requires transitive com.bank.service;
    requires com.bank.dto;
    provides com.bank.service.Service with com.bank.service.impl.ServiceImpl;
    exports com.bank.service.impl;
    exports com.bank.service.excpetion;
}