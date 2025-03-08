package com.bridgelabz.addressbookapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.bridgelabz.addressbookapp.model.AddressBook;

import java.util.List;

public interface AddressBookRepository extends  JpaRepository<AddressBook,Long>{
    List<AddressBook> findByNameIgnoreCase(String name);
}
