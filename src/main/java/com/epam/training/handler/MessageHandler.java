package com.epam.training.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.concurrent.ExecutionException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.epam.training.domain.Board;
import com.epam.training.domain.BoardFactory;
import com.epam.training.domain.Engine;
import com.epam.training.message.Fire;
import com.epam.training.message.FireAnswer;
import com.epam.training.message.Lost;
import com.epam.training.message.Message;
import com.epam.training.message.Size;
import com.epam.training.message.Won;

public class MessageHandler extends ChannelInboundHandlerAdapter {

	private static final String INPUT_FILE = "ships.txt";
	
	private static final Logger log = LoggerFactory.getLogger(MessageHandler.class);

	protected Board board;

	private Engine engine;

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        log.info("Message got " + msg);
        if (msg instanceof Size) {
        	Size size = (Size)msg;
        	System.out.println("size " + size);
        	createBoardAndEngine(size.getWidth(), size.getHeight());
        	board.printShips();

        	sendMessage(ctx, engine.shoot());
        	return;
        }

        if (msg instanceof Fire) {
        	Fire message = (Fire)msg;
        	FireAnswer answer = board.process(message);
        	board.printShips();
        	sendMessage(ctx, answer);

        	if (board.isLost()) {
        		sendMessage(ctx, new Lost());
        	} else {
        		sendMessage(ctx, engine.shoot());
        	}
        	return;
        }

        if (msg instanceof FireAnswer) {
        	engine.process((FireAnswer) msg);
        	//board.printShips();

        	if (engine.isWon()) {
        		sendMessage(ctx, new Won());
        	}
        	return;
        }

        if (msg instanceof Won) {
        	disconnect(ctx);
        }
        
        if (msg instanceof Lost) {
        	disconnect(ctx);
        }
    }

	private void disconnect(ChannelHandlerContext ctx)
			throws InterruptedException, ExecutionException {
		ctx.disconnect().get();
	}

    protected void createBoardAndEngine(int width, int height) {
    	BoardFactory boardFactory = new BoardFactory(INPUT_FILE);
    	board = boardFactory.create(width, height);
    	
    	engine = new Engine(width, height, board.getShipCount());
    }

    private void sendMessage(ChannelHandlerContext ctx, Message message) {
    	log.info("Message sent " + message);
        ctx.write(message);
        ctx.flush();
    }
}
