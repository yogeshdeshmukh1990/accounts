package com.banking.accounts.controller;

import com.banking.accounts.dto.CustomerDTO;
import com.banking.accounts.dto.ErrorResponseDTO;
import com.banking.accounts.dto.ResponseDTO;
import com.banking.accounts.utils.AccountsConstants;
import com.banking.accounts.service.IAccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@Tag(
        name= "CRUD REST api for account details in the bank",
        description = "CRUD REST api for the accounts details in the bank"
)
@AllArgsConstructor
@Validated
@RequestMapping(path="/api")
public class AccountsController {

    private IAccountService iAccountService;

    @Operation(summary = "Health check rest api for the service",
            description = "REST API to health check rest api for the service")
    @GetMapping("/test")
    public String test(){
        return "Application is up and running!";
    }

    @Operation(summary = "Create account rest api",
            description = "REST API to create a new Customer for the bank")
    @ApiResponse(responseCode = "201",
            description = "HTTP Status created"
    )
    @PostMapping("/create")
    public ResponseEntity<ResponseDTO> createAccount(@Valid @RequestBody CustomerDTO customerDTO){
        iAccountService.createAccount(customerDTO);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDTO(AccountsConstants.STATUS_201, AccountsConstants.MESSAGE_201));
    }

    @Operation(summary = "Fetch account details rest api",
            description = "REST API to fetch Customer details for the bank")
    @ApiResponse(responseCode = "200",
            description = "HTTP Status OK"
    )
    @GetMapping("/fetch")
    public ResponseEntity<CustomerDTO> fetchAccountDetails(@RequestParam @Pattern(regexp="(^$|[0-9]{10})",message = "Mobile number must be 10 digits")
                                                               String mobileNumber){
        CustomerDTO customerDTO = iAccountService.fetchAccount(mobileNumber);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(customerDTO);
    }

    @Operation(summary = "Update account details rest api",
            description = "REST API to update Customer details for the bank")
    @ApiResponses({
            @ApiResponse(responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(responseCode = "417",
                    description = "HTTP Status Update failed Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDTO.class)
                    )
            )
    })
    @PutMapping("/update")
    public ResponseEntity<ResponseDTO> updateAccountDetails(@Valid @RequestBody CustomerDTO customerDTO){
        boolean isUpdated = iAccountService.updateAccount(customerDTO);
        if(isUpdated){
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDTO(AccountsConstants.STATUS_200, AccountsConstants.MESSAGE_200));
        } else {
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDTO(AccountsConstants.STATUS_417, AccountsConstants.MESSAGE_417_UPDATE));
        }
    }

    @Operation(summary = "Delete account details rest api",
            description = "REST API to delete Customer details for the bank")
    @ApiResponses({
            @ApiResponse(responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(responseCode = "417",
            description = "HTTP Status Delete operation Failed",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDTO.class)
                    )
    )
    })
    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDTO> deleteAccountDetails(@RequestParam
                                                                @Pattern(regexp="(^$|[0-9]{10})",message = "Mobile number must be 10 digits")
                                                                String mobileNumber){
        boolean isDeleted = iAccountService.deleteAccount(mobileNumber);
        if(isDeleted){
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDTO(AccountsConstants.STATUS_200, AccountsConstants.MESSAGE_200));
        } else {
          return ResponseEntity
                  .status(HttpStatus.EXPECTATION_FAILED)
                  .body(new ResponseDTO(AccountsConstants.STATUS_417, AccountsConstants.MESSAGE_417_DELETE));
        }
    }
}
