package com.eazybytes.accounts.controller;

import com.eazybytes.accounts.dto.CustomerDTO;
import com.eazybytes.accounts.dto.ResponseDTO;
import com.eazybytes.accounts.service.IAccountService;
import com.eazybytes.accounts.utils.AccountsConstants;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping(path="/accounts", produces= MediaType.APPLICATION_JSON_VALUE)
public class AccountsController {

    private IAccountService iAccountService;

    @GetMapping("/test")
    public String test(){
        return "Application is running!";
    }

    @PostMapping("/add")
    public ResponseEntity<ResponseDTO> createAccount(@RequestBody CustomerDTO customer){
        iAccountService.createAccount(customer);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDTO(AccountsConstants.STATUS_201, AccountsConstants.MESSAGE_201));
    }
}
