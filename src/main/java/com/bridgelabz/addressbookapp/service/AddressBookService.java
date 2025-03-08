package com.bridgelabz.addressbookapp.service;

import com.bridgelabz.addressbookapp.dto.AddressBookDTO;
import com.bridgelabz.addressbookapp.exception.AddressBookNotFoundException;
import com.bridgelabz.addressbookapp.model.AddressBook;
import com.bridgelabz.addressbookapp.repository.AddressBookRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class AddressBookService {

    private final AddressBookRepository addressBookRepository;

    public AddressBookService(AddressBookRepository addressBookRepository) {
        this.addressBookRepository = addressBookRepository;
    }

    public List<AddressBook> getAllContacts() {
        return addressBookRepository.findAll(); // ✅ Fetch from DB
    }

    public AddressBook getContactById(Long id) {
        return addressBookRepository.findById(id)
                .orElseThrow(() -> new AddressBookNotFoundException("Address Book ID " + id + " not found!"));
    }

    public List<AddressBook> getContactsByName(String name) {
        return addressBookRepository.findByNameIgnoreCase(name); // ✅ Fetch from DB by Name
    }

    public AddressBook addContact(AddressBookDTO addressBookDTO) {
        AddressBook contact = new AddressBook(null, addressBookDTO.getName(), addressBookDTO.getPhoneNumber(), addressBookDTO.getEmail());
        return addressBookRepository.save(contact); // ✅ Store in DB
    }

    public AddressBook updateContact(Long id, AddressBookDTO addressBookDTO) {
        AddressBook contact = addressBookRepository.findById(id)
                .orElseThrow(() -> new AddressBookNotFoundException("Address Book ID " + id + " not found!"));

        contact.setName(addressBookDTO.getName());
        contact.setPhoneNumber(addressBookDTO.getPhoneNumber());
        contact.setEmail(addressBookDTO.getEmail());

        return addressBookRepository.save(contact);
    }

    public boolean deleteContact(Long id) {
        AddressBook contact = addressBookRepository.findById(id)
                .orElseThrow(() -> new AddressBookNotFoundException("Address Book ID " + id + " not found!"));

        addressBookRepository.delete(contact);
        return true;
    }
}