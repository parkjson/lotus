package Controller;

import io.javalin.Javalin;
import io.javalin.http.Context;
import java.util.*;

import Model.Account;
import Model.Message;
import Service.AccountService;
import Service.MessageService;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;




public class SocialMediaController {
    MessageService messageService;
    AccountService accountService;
    public SocialMediaController(){
        this.messageService = new MessageService();
        this.accountService = new AccountService();
    }
    
    public Javalin startAPI() {
        Javalin app = Javalin.create();

        app.post("/register", this::postRegisterHandler);
        app.post("/login", this::postLoginHandler);
        
        app.post("/messages", this::postMessageHandler);
        app.get("/messages", this::getAllMessagesHandler);
        app.get("/messages/{message_id}", this::getOneMessageHandler);
        app.delete("/messages/{message_id}", this::deleteOneMessageHandler);
        app.patch("/messages/{message_id}", this::patchOneMessageHandler);

        app.get("/accounts/{account_id}/messages", this::getAccountMessagesHandler);

        return app;
    }

    private void postRegisterHandler(Context ctx) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Account account = mapper.readValue(ctx.body(), Account.class);
        Account registerAccount = accountService.userRegistration(account);
        if(registerAccount!=null){
            ctx.json(mapper.writeValueAsString(registerAccount));
        }else{
            ctx.status(400);
        }
    }

    private void postLoginHandler(Context ctx) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Account account = mapper.readValue(ctx.body(), Account.class);
        
        System.out.println("About to activate");
        System.out.println(account.toString());
        
        Account loginAccount = accountService.loginAccount(account);
        
        if(loginAccount!=null){
            ctx.json(mapper.writeValueAsString(loginAccount));
        }else{
            ctx.status(401);
        }
    }

    private void postMessageHandler(Context ctx) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Message message = mapper.readValue(ctx.body(), Message.class);
        Message postMessage = messageService.createNewMessage(message);
        if(postMessage!=null){
            ctx.json(mapper.writeValueAsString(postMessage));
        }else{
            ctx.status(400);
        }
    }

    private void getAllMessagesHandler(Context ctx) {
        List<Message> messages = messageService.getAllMessages();
        ctx.json(messages);
    }

    private void getOneMessageHandler(Context ctx) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        int message_id = Integer.parseInt(ctx.pathParam("message_id"));
        Message postMessage = messageService.getOneMessageGivenMessageId(message_id);
        if(postMessage!=null){
            ctx.json(mapper.writeValueAsString(postMessage));
        }else{
            ctx.status(200);
        }
    }


    private void deleteOneMessageHandler(Context ctx) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        int message_id = Integer.parseInt(ctx.pathParam("message_id"));
        Message postMessage = messageService.deleteAMessageGivenMessageId(message_id);
        if(postMessage!=null){
            ctx.json(mapper.writeValueAsString(postMessage));
        }else{
            ctx.status(200); //200 anyways, should be empty response
        }
    }

    private void patchOneMessageHandler(Context ctx) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Message message = mapper.readValue(ctx.body(), Message.class);
        int message_id = Integer.parseInt(ctx.pathParam("message_id"));
        Message updateMessage = messageService.updateMessageGivenMessageId(message_id, message);
        System.out.println(updateMessage);
        if(updateMessage == null){
            ctx.status(400);
        }else{
            ctx.json(mapper.writeValueAsString(updateMessage));
        }
    }

    private void getAccountMessagesHandler(Context ctx) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        int account_id = Integer.parseInt(ctx.pathParam("account_id"));
        System.out.println("account id");
        System.out.println(account_id);
        List<Message> postMessages = messageService.getAllMessagesFromUserGivenAccountId(account_id);
        if(postMessages!=null){
            ctx.json(mapper.writeValueAsString(postMessages));
        }else{
            ctx.status(200); //no 400 regardless
        }
    }

}