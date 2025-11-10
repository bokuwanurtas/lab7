package com.example.demo.repositories;


import com.example.demo.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
}