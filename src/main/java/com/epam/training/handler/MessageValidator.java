package com.epam.training.handler;

import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;

import com.epam.training.message.Fire;
import com.epam.training.message.Hit;
import com.epam.training.message.Lost;
import com.epam.training.message.Message;
import com.epam.training.message.Miss;
import com.epam.training.message.Size;
import com.epam.training.message.Sunk;
import com.epam.training.message.Won;


public class MessageValidator extends ChannelDuplexHandler {

	public static final Message NO_MESSAGE = new NoMessage();

	protected Message lastMessageGet;

	protected Message lastMessageSent;

	public MessageValidator() {
		lastMessageGet = NO_MESSAGE;
		lastMessageSent = NO_MESSAGE;
	}

	MessageValidator(Message lastMessageGet, Message lastMessageSent) {
		this.lastMessageGet = lastMessageGet;
		this.lastMessageSent = lastMessageSent;
	}

	//write to client
    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
        lastMessageSent = (Message) msg;
    	ctx.write(msg, promise);
    }

    //read from client
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object actualMessage) throws Exception {
    	verifySizeMessage(actualMessage);

    	verifyFireMessage(actualMessage);

    	if (((actualMessage instanceof Sunk || actualMessage instanceof Hit || actualMessage instanceof Miss)
    			&& !(lastMessageSent instanceof Fire))) {
    		signalError(actualMessage);
    	}

    	if ((actualMessage instanceof Lost && !(lastMessageGet instanceof Sunk))) {
    		signalError(actualMessage);
    	}

    	if ((actualMessage instanceof Won && !(lastMessageSent instanceof Lost))) {
    		signalError(actualMessage);
    	}

    	lastMessageGet = (Message) actualMessage;

    	super.channelRead(ctx, actualMessage);
    }

	protected void verifyFireMessage(Object actualMessage) {
		if (actualMessage instanceof Fire &&
    			!(lastMessageSent instanceof Size
    				|| lastMessageGet instanceof Hit
    				|| lastMessageGet instanceof Sunk
    				|| lastMessageGet instanceof Miss)) {
    		signalError(actualMessage);
    	}
	}

	protected void verifySizeMessage(Object actualMessage) {
		if (actualMessage instanceof Size) {
    		signalError(actualMessage);
    	}
	}

    protected void signalError(Object actualMessage) {
    	throw new IllegalArgumentException(actualMessage
    			+  " message received, when last message got: " + lastMessageGet
    			+ ", last message sent: " + lastMessageSent);
	}

	private static class NoMessage implements Message {

		@Override
		public String toString() {
			return "No_message";
		}
    }

}
