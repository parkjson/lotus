package Service;

import Model.Message;
import DAO.MessageDAO;
import java.util.*;

public class MessageService {
    MessageDAO messageDAO;
    
    public MessageService(){
        messageDAO = new MessageDAO();
    }

    public MessageService(MessageDAO messageDAO){
        this.messageDAO = messageDAO;
    }

    public Message createNewMessage(Message message){
        if (message.getMessage_text() == null || message.getMessage_text().trim().isEmpty()){
            return null;
        }
        if (message.getMessage_text().length() >= 255){
            return null;
        }
        return messageDAO.createNewMessage(message);
    }

    public List<Message> getAllMessages(){
        return messageDAO.getAllMessages();
    }
  
    public Message getOneMessageGivenMessageId(int id){
        return messageDAO.getOneMessageGivenMessageId(id);
    }

    public Message deleteAMessageGivenMessageId(int id){
        Message m = messageDAO.getOneMessageGivenMessageId(id);
        messageDAO.deleteAMessageGivenMessageId(id);
        return m;
    }

    public Message updateMessageGivenMessageId(int id, Message message){
        
        if (messageDAO.getOneMessageGivenMessageId(id) == null){
            return null;
        }
        if (message.getMessage_text() == null || message.getMessage_text().trim().isEmpty()){
            return null;
        }
        if (message.getMessage_text().length() >= 255){
            return null;
        }
        messageDAO.updateMessageGivenMessageId(id, message);
        Message m = messageDAO.getOneMessageGivenMessageId(id);
        return m;    
    }

    public List<Message> getAllMessagesFromUserGivenAccountId(int id){
        return messageDAO.getAllMessagesFromUserGivenAccountId(id);
    }
}
