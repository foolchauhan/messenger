package org.foolchauhan.messenger.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.foolchauhan.messenger.database.DatabaseClass;
import org.foolchauhan.messenger.exception.DataNotFoundException;
import org.foolchauhan.messenger.model.Message;
import org.foolchauhan.messenger.model.Profile;

public class MessageService {
	
	private Map<Long, Message> messages = DatabaseClass.getMessages();
	private Map<String, Profile> profiles = DatabaseClass.getProfiles();
	
	public MessageService() {
		messages.put(1L, new Message(1L, "This is a message.", new Date(), new Date(), "chchauha"));
		messages.put(2L, new Message(2L, "This is second message", new Date(), new Date(), "chchauha"));
	}

	public List<Message> getAllMessages(){
		return new ArrayList<Message>(messages.values());
	}
	
	public Message getMessage(long id) {
		Message newMessage =  messages.get(id);
		if(newMessage == null) {
			throw new DataNotFoundException("Message with message id " + id + " not found.");
		}
		return newMessage;
	}
	
	public Message addMessage(Message message) {
		if(message.getAuthor().isEmpty() || message.getMessage().isEmpty() || !profiles.containsKey(message.getAuthor())) {
			return null;
		}
		Message msg = new Message();
		msg.setAuthor(message.getAuthor());
		msg.setMessage(message.getMessage());
		msg.setCreation_date(new Date());
		msg.setLast_update_date(new Date());
		msg.setId(messages.size()+1);
		messages.put(msg.getId(), msg);
		return msg;
	}
	
	public Message updateMessage(Message message) {
		if(message.getId() <=0) {
			return null;
		}
		Message msg = messages.get(message.getId());
		msg.setLast_update_date(new Date());
		msg.setMessage(message.getMessage());
		messages.put(message.getId(), msg);
		return messages.get(msg.getId());
	}
	
	public Message removeMessage(long id) {
		return messages.remove(id);
	}
	
	public List<Message> getAllMessagesForYear(int year) {
		List<Message> messageForYear = new ArrayList<Message>();
		Calendar cal = Calendar.getInstance();
		for (Message msg : messages.values()) {
			cal.setTime(msg.getCreation_date());
			if (cal.get(Calendar.YEAR) == year) {
				messageForYear.add(msg);
			}
		}
		return messageForYear;
	}
	
	public List<Message> getAllMessagesPaginated(int start, int size){
		List<Message> messageList = new ArrayList<Message>(messages.values());
		if(start+size > messageList.size()) {
			return null;
		}
		return messageList.subList(start, start+size);
	}
}

