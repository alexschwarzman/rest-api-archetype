package com.aschwarzman.api.example.user.controller;

import com.aschwarzman.api.archetype.controllers.BaseDomainController;
import com.aschwarzman.api.example.user.model.User;
import com.aschwarzman.api.example.user.service.UserControllerService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;

@Api
@RestController
@RequestMapping("/users")
public class UserController extends BaseDomainController<Long, User, UserControllerService> {

}
