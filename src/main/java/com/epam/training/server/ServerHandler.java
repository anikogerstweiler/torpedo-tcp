package com.epam.training.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.netty.channel.ChannelHandlerContext;

import com.epam.training.handler.MessageHandler;
import com.epam.training.message.Message;
import com.epam.training.message.Size;

public class ServerHandler extends MessageHandler {

    private static final int BOARD_HEIGHT = 30;

    private static final int BOARD_WIDTH = 30;

    private static final Logger LOG = LoggerFactory.getLogger(ServerHandler.class);

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        LOG.info("Client connected");

        createBoardAndEngine(BOARD_WIDTH, BOARD_HEIGHT);

        Message message = new Size(BOARD_WIDTH, BOARD_HEIGHT);
        ctx.write(message);
        ctx.flush();
    }
}
