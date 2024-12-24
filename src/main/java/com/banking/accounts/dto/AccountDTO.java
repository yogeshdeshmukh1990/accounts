package com.banking.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Schema(
        name="Account",
        description="Schema to hold account information"
)
public class AccountDTO {

    @Schema(
            description="Account number of the bank"
    )
    @NotEmpty(message="Account Number cannot be a null or empty")
    @Pattern(regexp = "(^$[0-9]{10})", message="Account number must be of 10 digits")
    private Long accountNumber;

    @Schema(
            description="Account Type of the bank account",
            example = "Savings"
    )
    @NotEmpty(message="Account Type cannot be a null or empty")
    private String accountType;

    @Schema(
            description="branch Address of the bank account holder",
            example = "City, Country"
    )
    @NotEmpty(message="Branch Address cannot be a null or empty")
    private String branchAddress;
}
