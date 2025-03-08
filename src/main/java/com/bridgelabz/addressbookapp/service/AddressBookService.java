package com.bridgelabz.addressbookapp.service;

import com.bridgelabz.addressbookapp.dto.AddressBookDTO;
import com.bridgelabz.addressbookapp.model.AddressBook;
import com.bridgelabz.addressbookapp.repository.AddressBookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressBookService {

    private final AddressBookRepository addressBookRepository;
    public AddressBookService(AddressBookRepository addressBookRepository) {
        this.addressBookRepository = addressBookRepository;
    }

    public List<AddressBook> getAllContacts() {
        return addressBookRepository.findAll(); // ✅ Fetch from DB
    }

    public Optional<AddressBook> getContactById(Long id) {
        return addressBookRepository.findById(id); // ✅ Fetch from DB by ID
    }

    public List<AddressBook> getContactsByName(String name) {
        return addressBookRepository.findByNameIgnoreCase(name); // ✅ Fetch from DB by Name
    }

    public AddressBook addContact(AddressBookDTO addressBookDTO) {
        AddressBook contact = new AddressBook(null, addressBookDTO.getName(), addressBookDTO.getPhoneNumber(), addressBookDTO.getEmail());
        return addressBookRepository.save(contact); // ✅ Store in DB
    }

    public Optional<AddressBook> updateContact(Long id, AddressBookDTO addressBookDTO) {
        Optional<AddressBook> existingContact = addressBookRepository.findById(id);
        if (existingContact.isPresent()) {
            AddressBook contact = existingContact.get();
            contact.setName(addressBookDTO.getName());
            contact.setPhoneNumber(addressBookDTO.getPhoneNumber());
            contact.setEmail(addressBookDTO.getEmail());
            return Optional.of(addressBookRepository.save(contact)); // ✅ Update in DB
        }
        return Optional.empty();
    }

    public boolean deleteContact(Long id) {
        if (addressBookRepository.existsById(id)) {
            addressBookRepository.deleteById(id); // ✅ Delete from DB
            return true;
        }
        return false;
    }
}