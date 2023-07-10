package com.hanaonestock.transaction.controller;

import com.hanaonestock.transaction.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TransactionController {

    private final TransactionService transactionService;

    @Autowired
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @RequestMapping("/trading")
    public ModelAndView trading() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("trading");
        return mav;
    }
}
