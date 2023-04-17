package com.learning.fotoalbum.service;

import com.learning.fotoalbum.model.MailboxMessage;
import com.learning.fotoalbum.repository.MailboxMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class MailboxMessageService {
    @Autowired
    private MailboxMessageRepository mailboxMessageRepository;

    //SELECTION
    public MailboxMessage getById(Integer id) throws ResponseStatusException {
        return mailboxMessageRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    //INDEX
    public List<MailboxMessage> getAllMessages(){
        return mailboxMessageRepository.findAll();
    }

    //DELETE
    public void deleteMessage(Integer id){
        MailboxMessage messageToDelete = getById(id);
        mailboxMessageRepository.delete(messageToDelete);
    }

    //SEND MESSAGE FROM FRONT-END FORM
    public void send(MailboxMessage formMailboxMessage){
        MailboxMessage messageToSave = new MailboxMessage();
        messageToSave.setContent(formMailboxMessage.getContent());
        messageToSave.setEmail(formMailboxMessage.getEmail());
        mailboxMessageRepository.save(messageToSave);
    }

}
