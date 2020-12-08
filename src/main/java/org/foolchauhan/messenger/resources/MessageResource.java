/**
 * 
 */
package org.foolchauhan.messenger.resources;

import java.net.URI;
import java.util.List;

import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.foolchauhan.messenger.model.Message;
import org.foolchauhan.messenger.resources.beans.MessageFilterBean;
import org.foolchauhan.messenger.service.MessageService;

/**
 * @author chchauha
 *
 */

@Path("/messages")
@Consumes(MediaType.APPLICATION_JSON)
@Produces( value = {MediaType.APPLICATION_JSON, MediaType.TEXT_XML})
public class MessageResource {

	MessageService messageService = new MessageService();
	
	@GET
	public List<Message> getAllMessages(@BeanParam MessageFilterBean filterBean) {
		if(filterBean.getYear() > 0) {
			return messageService.getAllMessagesForYear(filterBean.getYear());
		}
		
		return messageService.getAllMessages();
	}
	
	@GET
	@Path("/{messageId}")
	public Message getMessage(@PathParam("messageId") long id, @Context UriInfo uriInfo) {
		Message message = messageService.getMessage(id);
		String selfLink = getUriForSelf(uriInfo, message);
		String profileLink = getUriForProfile(uriInfo, message);
		String commentsLink = getUriForComments(uriInfo, message);
		message.addLink(selfLink, "self");
		message.addLink(profileLink, "profile");
		message.addLink(commentsLink, "comments");
		return message;
	}

	private String getUriForComments(UriInfo uriInfo, Message message) {
		String commentsLink = uriInfo.getBaseUriBuilder()
			     .path(MessageResource.class)
			     .path(MessageResource.class, "getCommentResource")
			     .path(CommentResource.class)
			     .resolveTemplate("messageId", message.getId())
			     .build()
			     .toString();
		return commentsLink;
	}

	private String getUriForProfile(UriInfo uriInfo, Message message) {
		String profileLink = uriInfo.getBaseUriBuilder()
			     .path(ProfileResource.class)
			     .path(message.getAuthor())
			     .build()
			     .toString();
		return profileLink;
	}

	private String getUriForSelf(UriInfo uriInfo, Message message) {
		String selfLink = uriInfo.getBaseUriBuilder()
							     .path(MessageResource.class)
							     .path(Long.toString(message.getId()))
							     .build()
							     .toString();
		return selfLink;
	}
	
	@POST
	public Response addMessage(Message message, @Context UriInfo uriInfo) {
		Message newMessage = messageService.addMessage(message);
		URI location = uriInfo.getAbsolutePathBuilder()
							  .path(String.valueOf(newMessage.getId()))
							  .build();
		return Response.created(location)
					   .entity(newMessage)
					   .build();
	}
	
	@PUT
	@Path("/{messageId}")
	public Message updateMessage(@PathParam("messageId") long id, Message message) {
		message.setId(id);
		return  messageService.updateMessage(message);
	}
	
	@DELETE
	@Path("/{messageId}")
	public void removeMessage(@PathParam("messageId") long id) {
		messageService.removeMessage(id);
		
	}

	@Path("/{messageId}/comments")
	public CommentResource getCommentResource() {
		return new CommentResource();
		
	}
}
