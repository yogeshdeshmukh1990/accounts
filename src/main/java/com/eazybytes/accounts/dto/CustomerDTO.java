package com.eazybytes.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(
        name="Customer",
        description="Schema to hold customer and account details"
)
public class CustomerDTO {

    @Schema(
            description="Name of the Customer",
            example = "Yogesh Deshmukh"
    )
    @NotEmpty(message = "Name cannot be null or empty")
    @Size(min = 5, max = 30, message = "The length of the customer name should be in between 5 and 30.")
    private String name;

    @Schema(
            description="Email of the Customer",
            example = "yogesh@gamil.com"
    )
    @NotEmpty(message = "Email cannot be null or empty")
    @Email(message = "Email addresss should be valid value")
    private String email;

    @Schema(
            description="Mobile Number of the Customer",
            example = "9988772210"
    )
    @Pattern(regexp="(^$|[0-9]{10})", message="Mobile number must be of 10 digits")
    private String mobileNumber;

    @Schema(
            description="Account details of the customer"
    )
    private AccountDTO accountDTO;
}
