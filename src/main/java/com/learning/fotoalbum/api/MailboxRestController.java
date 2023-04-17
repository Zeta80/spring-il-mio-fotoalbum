package com.learning.fotoalbum.api;

import com.learning.fotoalbum.model.MailboxMessage;
import com.learning.fotoalbum.service.MailboxMessageService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@CrossOrigin
@RequestMapping("/api/mailbox")
public class MailboxRestController {
    @Autowired
    private MailboxMessageService mailboxMessageService;

    //CREATE
    @PostMapping
    public void create(@Valid @RequestBody MailboxMessage formMailboxMessage, BindingResult bs){
        if(bs.hasErrors()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        mailboxMessageService.send(formMailboxMessage);
    }

}
