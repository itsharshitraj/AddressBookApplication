package com.bridgelabz.addressbookapp.service;

import com.bridgelabz.addressbookapp.dto.AddressBookDTO;
import com.bridgelabz.addressbookapp.model.AddressBook;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;
@Service
public class AddressBookService {

    private final List<AddressBook> contacts = new ArrayList<>();
    private final AtomicLong idCounter = new AtomicLong(1);

    public List<AddressBook> getAllContacts() {
        return contacts;
    }

    public Optional<AddressBook> getContactById(Long id) {
        return contacts.stream()
                .filter(contact -> contact.getId().equals(id))
                .findFirst();
    }
    public List<AddressBook> getContactsByName(String name) {
        return contacts.stream()
                .filter(contact -> contact.getName().equalsIgnoreCase(name))
                .collect(Collectors.toList());
    }

        public AddressBook addContact(AddressBookDTO addressBookDTO) {
        AddressBook contact = new AddressBook(
                idCounter.getAndIncrement(),
                addressBookDTO.getName(),
                addressBookDTO.getPhoneNumber(),
                addressBookDTO.getEmail()
        );
        contacts.add(contact);
        return contact;
    }

    public Optional<AddressBook> updateContact(Long id, AddressBookDTO addressBookDTO) {
        for (AddressBook contact : contacts) {
            if (contact.getId().equals(id)) {
                contact.setName(addressBookDTO.getName());
                contact.setPhoneNumber(addressBookDTO.getPhoneNumber());
                contact.setEmail(addressBookDTO.getEmail());
                return Optional.of(contact);
            }
        }
        return Optional.empty();
    }

    public boolean deleteContact(Long id) {
        return contacts.removeIf(contact -> contact.getId().equals(id));
    }
}
