package com.learning.fotoalbum.controller;

import com.learning.fotoalbum.exceptions.PhotoNotFoundException;
import com.learning.fotoalbum.model.MailboxMessage;
import com.learning.fotoalbum.model.Photo;
import com.learning.fotoalbum.service.MailboxMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;

@Controller
@RequestMapping("/mailbox")
public class MailboxMessageController {
    @Autowired
    private MailboxMessageService mailboxMessageService;

    //INDEX
    @GetMapping
    public String index(Model model){
        model.addAttribute("messages", mailboxMessageService.getAllMessages());
        return "mailbox/index";
    }

    //SHOW
    @GetMapping("/{messageId}")
    public String show(@PathVariable("messageId") Integer id, Model model) {
        try {
            MailboxMessage mailboxMessage = mailboxMessageService.getById(id);
            model.addAttribute("mailboxMessage", mailboxMessage);
            return "mailbox/show";
        } catch (PhotoNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Message with id '" + id + "' not found");
        }
    }

    //DELETE
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id){
        mailboxMessageService.deleteMessage(id);
        return "redirect:/mailbox";
    }

}
