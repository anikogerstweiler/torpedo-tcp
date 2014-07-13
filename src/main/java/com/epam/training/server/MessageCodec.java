package com.epam.training.server;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageCodec;

import java.util.List;

import com.epam.training.message.Error;
import com.epam.training.message.Fire;
import com.epam.training.message.Hit;
import com.epam.training.message.Lost;
import com.epam.training.message.Message;
import com.epam.training.message.Miss;
import com.epam.training.message.Size;
import com.epam.training.message.Sunk;
import com.epam.training.message.Won;

public class MessageCodec extends ByteToMessageCodec<Message> {

	private static final String CHARSET = "UTF8";
	static final String WON = "WON";
	static final String LOST = "LOST";
	static final String MISS = "MISS";
	static final String SUNK = "SUNK";
	static final String HIT = "HIT";
	static final String FIRE = "FIRE";
	static final String SIZE = "SIZE";

	@Override
    protected void encode(ChannelHandlerContext ctx, Message msg, ByteBuf out) throws Exception {
        out.writeBytes(msg.toString().getBytes(CHARSET));
        out.writeChar('\n');
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
    	int size = in.readableBytes();
        byte[] msg = new byte[size];

        in.readBytes(msg);
        String input = new String(msg,CHARSET);

        int delimiter = input.indexOf(" ");
        String command = getCommand(input, delimiter);

        Message message = null;
        switch (command) {
			case SIZE:
				message = new Size(input.substring(delimiter + 1));
				break;
			case FIRE:
				message = new Fire(input.substring(delimiter + 1));
				break;
			case HIT:
				message = new Hit();
				break;
			case SUNK:
				message = new Sunk();
				break;
			case MISS:
				message = new Miss();
				break;
			case LOST:
				message = new Lost();
				break;
			case WON:
				message = new Won();
				break;
			default:
				message = new Error(input.substring(delimiter + 1, size));
				break;
		}
        out.add(message);
    }

	private String getCommand(String input, int delimiter) {
		String command;
		if (delimiter == -1) {
        	command = input;
        } else {
        	command = input.substring(0, delimiter);
        }
		return command;
	}
}
