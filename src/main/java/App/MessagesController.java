package App;

import org.apache.commons.collections4.iterators.ReverseListIterator;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.security.InvalidParameterException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
public class MessagesController {

    private Map<String, List<Message>> messagesPerRecipient = new HashMap<>();

    @RequestMapping("/")
    public String index() {
        return "Congratulations from MessagesController.java";
    }

    @PostMapping("/messages/add")
    @ResponseStatus(HttpStatus.OK)
    public String add(@RequestBody Map<String, String> body){
        String recipient = body.get("recipient");
        String content = body.get("content");
        String sender = body.get("sender");

        Message message = new Message(sender, content, new MyDate());

        List<Message> messages = messagesPerRecipient.get(recipient);
        if (messages==null){
            messages= new ArrayList<>();
        }
        messages.add(message);
        messagesPerRecipient.put(recipient, messages);

        return "message was successfully added.";
    }

    @PostMapping("/messages/get/{recipient:.+}")
    public List<Message> getMessages(@PathVariable String recipient, @RequestBody Map<String, String> body){

        SimpleDateFormat formatter = new SimpleDateFormat(MyDate.datePattern);

        Date date;
        try {
            String dateStr = body.get("date");
            date = formatter.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
            throw new InvalidParameterException("date format is invalid");
        }


        List<Message> messages = messagesPerRecipient.get(recipient);
        if (messages == null){
            return new ArrayList<>();
        }else{
            List<Message> messagesInTimeFrame = new ArrayList<>();

            ReverseListIterator reverseListIterator = new ReverseListIterator(messages);
            while (reverseListIterator.hasNext()){
                Message m = (Message) reverseListIterator.next();
                if (m.getDate().after(date)){
                    messagesInTimeFrame.add(m);
                }else{
                    break;
                }
            }
            return messagesInTimeFrame;
        }
    }


}
