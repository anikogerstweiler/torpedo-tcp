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
	@Override
    protected void encode(ChannelHandlerContext ctx, Message msg, ByteBuf out) throws Exception {
        out.writeBytes(msg.toString().getBytes("UTF8"));
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
    	int size = in.readableBytes();
        byte[] msg = new byte[size];
        in.readBytes(msg);
        String input = new String(msg);
        int delimiter = input.indexOf(" ");
        System.out.println(delimiter);
        String command;
        if (delimiter == -1) {
        	command = input;
        } else {
        	command = input.substring(0, delimiter);
        }
        Message message = null;
        switch (command) {
			case "SIZE":
				message = new Size(input.substring(delimiter + 1, size));
				break;
			case "FIRE":
				message = new Fire(input.substring(delimiter + 1, size));
				break;
			case "HIT":
				message = new Hit();
				break;
			case "SUNK":
				message = new Sunk();
				break;
			case "MISS":
				message = new Miss();
				break;
			case "LOST":
				message = new Lost();
				break;
			case "WON":
				message = new Won();
				break;
			default:
				message = new Error(input.substring(delimiter + 1, size));
				break;
		}
        out.add(message.toString());
    }
}
