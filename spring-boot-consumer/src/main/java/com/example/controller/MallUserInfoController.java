package com.example.controller;

import com.example.service.MallUserInfoService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping(produces = {"application/json;charset=UTF-8"})
public class MallUserInfoController extends BaseController{

    @Resource
    private MallUserInfoService mallUserInfoService;



}