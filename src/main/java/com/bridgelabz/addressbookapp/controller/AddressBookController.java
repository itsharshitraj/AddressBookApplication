package com.bridgelabz.addressbookapp.controller;

import com.bridgelabz.addressbookapp.dto.AddressBookDTO;
import com.bridgelabz.addressbookapp.model.AddressBook;
import org.springframework.http.ResponseEntity;
import com.bridgelabz.addressbookapp.service.AddressBookService;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;

import java.util.List;


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
        return addressBookService.getContactById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @GetMapping("/name/{name}")
    public ResponseEntity<List<AddressBook>> getContactsByName(@PathVariable String name) {
        log.info("Fetching contacts with name: {}", name);
        List<AddressBook> contacts = addressBookService.getContactsByName(name);
        return contacts.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(contacts);
    }

    @PostMapping
    public ResponseEntity<AddressBook> addContact(@RequestBody AddressBookDTO addressBookDTO) {
        log.info("Adding new contact: {}", addressBookDTO);
        return ResponseEntity.ok(addressBookService.addContact(addressBookDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AddressBook> updateContact(@PathVariable Long id, @RequestBody AddressBookDTO addressBookDTO) {
        log.info("Updating contact with ID: {}", id);
        return addressBookService.updateContact(id, addressBookDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteContact(@PathVariable Long id) {
        log.info("Deleting contact with ID: {}", id);
        return addressBookService.deleteContact(id) ?
                ResponseEntity.noContent().build() :
                ResponseEntity.notFound().build();
    }

}
