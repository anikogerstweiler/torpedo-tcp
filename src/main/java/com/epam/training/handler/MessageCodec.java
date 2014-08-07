package com.epam.training.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageCodec;

import java.util.List;

import com.epam.training.message.Message;
import com.epam.training.message.MessageEnum;

public class MessageCodec extends ByteToMessageCodec<Message> {

    static final String CHARSET = "UTF8";
    private static final int NEW_LINE = 10;

    @Override
    protected void encode(ChannelHandlerContext ctx, Message msg, ByteBuf out) throws Exception {
        out.writeBytes(msg.toString().getBytes(CHARSET));
        out.writeByte(NEW_LINE);
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        int size = in.readableBytes();
        byte[] msg = new byte[size];
        in.readBytes(msg);
        String input = new String(msg, CHARSET);

        int delimiter = input.indexOf(' ');
        String command = getCommand(input, delimiter);

        Message message = null;
        try {
            message = MessageEnum.valueOf(command).createMessage(input);

            if (message instanceof Error) {
                ctx.disconnect().get();
            }

            out.add(message);

        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(" Unrecognized command: " + input + e);
        }
    }

    private String getCommand(String input, int delimiter) {
        String command;
        if (commandHasOneArgument(delimiter)) {
            command = input;
        } else {
            command = input.substring(0, delimiter);
        }
        return command;
    }

    private boolean commandHasOneArgument(int delimiter) {
        return delimiter == -1;
    }
}
