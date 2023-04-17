package com.learning.fotoalbum.repository;

import com.learning.fotoalbum.model.MailboxMessage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MailboxMessageRepository extends JpaRepository<MailboxMessage, Integer>{
}
