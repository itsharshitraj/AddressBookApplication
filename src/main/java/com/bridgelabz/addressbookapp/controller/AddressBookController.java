package com.bridgelabz.addressbookapp.controller;

import com.bridgelabz.addressbookapp.dto.AddressBookDTO;
import com.bridgelabz.addressbookapp.model.AddressBook;
import org.springframework.http.ResponseEntity;
import com.bridgelabz.addressbookapp.service.AddressBookService;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Slf4j  // Enables Lombok logging
@RestController
@RequestMapping("/contacts")
public class AddressBookController {

    private final AddressBookService addressBookService;

    public AddressBookController(AddressBookService addressBookService) {
        this.addressBookService = addressBookService;
    }
    @GetMapping
    public ResponseEntity<List<AddressBook>> getAllContacts() {
        log.info("Fetching all contacts");
        return ResponseEntity.ok(addressBookService.getAllContacts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AddressBook> getContactById(@PathVariable Long id) {
        log.info("Fetching contact with ID: {}", id);
        AddressBook contact = addressBookService.getContactById(id); // No Optional
        return ResponseEntity.ok(contact);
    }
    @GetMapping("/name/{name}")
    public ResponseEntity<List<AddressBook>> getContactsByName(@PathVariable String name) {
        log.info("Fetching contacts with name: {}", name);
        List<AddressBook> contacts = addressBookService.getContactsByName(name);
        return contacts.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(contacts);
    }

    @PostMapping
    public ResponseEntity<AddressBook> addContact(@Valid @RequestBody AddressBookDTO addressBookDTO) {
        log.info("Adding new contact: {}", addressBookDTO);
        AddressBook contact = addressBookService.addContact(addressBookDTO);
        return ResponseEntity.ok(contact);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AddressBook> updateContact(@PathVariable Long id,@Valid @RequestBody AddressBookDTO addressBookDTO) {
        log.info("Updating contact with ID: {}", id);
        AddressBook updatedContact = addressBookService.updateContact(id, addressBookDTO); // No Optional
        return ResponseEntity.ok(updatedContact);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteContact(@PathVariable Long id) {
        log.info("Deleting contact with ID: {}", id);
        addressBookService.deleteContact(id);

        Map<String, String> response = new HashMap<>();
        response.put("message", "Contact with ID " + id + " deleted successfully");
        return ResponseEntity.ok(response);
    }

}
