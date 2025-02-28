package com.addressbook.addressbookapp.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequestMapping("/contacts")
public class AddressBookController {

    private Map<Long, Map<String, String>> contacts = new ConcurrentHashMap<>();
    private long idCounter = 1;

    @GetMapping
    public ResponseEntity<List<Map<String, String>>> getAllContacts() {
        return ResponseEntity.ok(new ArrayList<>(contacts.values()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, String>> getContactById(@PathVariable Long id) {
        return contacts.containsKey(id) ? ResponseEntity.ok(contacts.get(id)) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Map<String, String>> addContact(@RequestBody Map<String, String> contact) {
        contact.put("id", String.valueOf(idCounter));
        contacts.put(idCounter++, contact);
        return ResponseEntity.ok(contact);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, String>> updateContact(@PathVariable Long id, @RequestBody Map<String, String> contactDetails) {
        if (!contacts.containsKey(id)) return ResponseEntity.notFound().build();
        contactDetails.put("id", String.valueOf(id));
        contacts.put(id, contactDetails);
        return ResponseEntity.ok(contactDetails);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteContact(@PathVariable Long id) {
        if (!contacts.containsKey(id)) return ResponseEntity.notFound().build();
        contacts.remove(id);
        return ResponseEntity.noContent().build();
    }
}
