package com.bridgelabz.addressbookapp.dto;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
@Data
public class AddressBookDTO {

    @NotNull(message = "Name cannot be empty")
    @Pattern(regexp = "^[A-Z][a-zA-Z]{2,}$", message = "Name must start with a capital letter and have at least 3 characters")
    private String name;

    @NotNull(message = "Phone number cannot be empty")
    private String phoneNumber;

    @NotNull(message = "Email cannot be empty")
    private String email;

    public AddressBookDTO() {}

    public AddressBookDTO(String name, String phoneNumber, String email) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
