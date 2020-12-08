package org.foolchauhan.messenger.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.foolchauhan.messenger.database.DatabaseClass;
import org.foolchauhan.messenger.model.Comment;
import org.foolchauhan.messenger.model.ErrorMessage;
import org.foolchauhan.messenger.model.Message;

public class CommentService {
	
	private Map<Long, Message> messages = DatabaseClass.getMessages();
	
	public List<Comment> getAllComments(long messageId) {
		Map<Long, Comment> comments = messages.get(messageId).getComments();
		return new ArrayList<Comment>(comments.values());
	}
	
	public Comment getComment(long messageId, long commentId) {
		ErrorMessage errorMessage = new ErrorMessage("MessageId or CommentId not found", 
				 "No Data : 004", 
				 "foolchauhan.github.io");
		Response response =  Response.status(Status.NOT_FOUND)
									 .type(MediaType.APPLICATION_JSON)
									 .entity(errorMessage)
									 .build();
		Message message = messages.get(messageId);
		if (message == null) {
			throw new WebApplicationException(response);
		}
		Map<Long, Comment> comments = message.getComments();
		Comment comment = comments.get(commentId);
		if (comment == null) {
			throw new WebApplicationException(response);
		}
		return comment;
	}
	
	public Comment addComment(long messageId, Comment comment) {
		if (comment.getMessage().isEmpty()) {
			return null;
		}
		Map<Long, Comment> comments = messages.get(messageId).getComments();
		comment.setId(comments.size()+1);
		comment.setAuthor(messages.get(messageId).getAuthor());
		comment.setCreationDate(new Date());
		comments.put(comment.getId(), comment);
		return comment;
	}
	
	public Comment updateComment(long messageId, Comment comment) {
		Map<Long, Comment> comments = messages.get(messageId).getComments();
		if(comment.getId() <= 0) {
			return null;
		}
		Comment cmt = comments.get(comment.getId());
		cmt.setMessage(comment.getMessage());
		comments.put(cmt.getId(), cmt);
		return cmt;		
	}
	
	public Comment removeComment(long messageId, long commentId) {
		Map<Long, Comment> comments = messages.get(messageId).getComments();
		return comments.remove(commentId);
	}
}
