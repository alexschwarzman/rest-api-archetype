package com.aschwarzman.api.example.account.controller;

import com.aschwarzman.api.archetype.controllers.BaseDomainController;
import com.aschwarzman.api.example.account.model.Account;
import com.aschwarzman.api.example.account.service.AccountControllerService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;

@Api
@RestController
@RequestMapping("/accounts")
public class AccountController extends BaseDomainController<Long, Account, AccountControllerService> {

}
