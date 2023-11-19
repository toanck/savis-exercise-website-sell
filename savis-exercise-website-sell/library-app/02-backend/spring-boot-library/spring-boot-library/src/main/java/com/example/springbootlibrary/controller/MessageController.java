package com.example.springbootlibrary.controller;

import com.example.springbootlibrary.entity.Message;
import com.example.springbootlibrary.requestmodels.AdminQuestionRequest;
import com.example.springbootlibrary.service.Impl.MessageService;
import com.example.springbootlibrary.utils.ExtractJWT;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/api/messages")
public class MessageController {

    private MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @PostMapping("/secure/add/message")
    public void postMessage(@RequestHeader(value = "Authorization") String token,
                            @RequestBody Message messageRequest){
        String userEmail = ExtractJWT.payloadJWTExtraction(token,"\"sub\"");
        messageService.postMessage(messageRequest,userEmail);
    };

    @PutMapping("/secure/admin/message")
    public void putMessage(@RequestHeader(value = "Authorization") String token,
                           @RequestBody AdminQuestionRequest adminQuestionRequest) throws Exception{
        String userEmail = ExtractJWT.payloadJWTExtraction(token,"\sub\"");
        String admin = ExtractJWT.payloadJWTExtraction(token,"\"userType\"");
        if (admin == null || !admin.equals("admin")){
            throw new Exception("Adminitration page only");
        }
        messageService.putMessage(adminQuestionRequest,userEmail);
    }

}
