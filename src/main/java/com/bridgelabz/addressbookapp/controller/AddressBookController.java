package com.bridgelabz.addressbookapp.controller;

import com.bridgelabz.addressbookapp.dto.AddressBookDTO;
import com.bridgelabz.addressbookapp.model.AddressBook;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;


@RestController
@RequestMapping("/contacts")
public class AddressBookController {

    private List<AddressBook> contacts = new ArrayList<>();
    private AtomicLong idCounter = new AtomicLong(1);

    @GetMapping
    public ResponseEntity<List<AddressBook>> getAllContacts() {
        return ResponseEntity.ok(contacts);
    }
    @GetMapping("/{id}")
    public ResponseEntity<AddressBook> getContactById(@PathVariable Long id) {
        return contacts.stream()
                .filter(contact -> contact.getId().equals(id))
                .findFirst()
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<AddressBook> addContact(@RequestBody AddressBookDTO addressBookDTO) {
        AddressBook contact = new AddressBook(
                idCounter.getAndIncrement(),
                addressBookDTO.getName(),
                addressBookDTO.getPhoneNumber(),
                addressBookDTO.getEmail()
        );
        contacts.add(contact);
        return ResponseEntity.ok(contact);
    }


    @PutMapping("/{id}")
    public ResponseEntity<AddressBook> updateContact(@PathVariable Long id, @RequestBody AddressBookDTO addressBookDTO) {
        for (AddressBook contact : contacts) {
            if (contact.getId().equals(id)) {
                contact.setName(addressBookDTO.getName());
                contact.setPhoneNumber(addressBookDTO.getPhoneNumber());
                contact.setEmail(addressBookDTO.getEmail());
                return ResponseEntity.ok(contact);
            }
        }
        return ResponseEntity.notFound().build();
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteContact(@PathVariable Long id) {
        boolean removed = contacts.removeIf(contact -> contact.getId().equals(id));
        return removed ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
