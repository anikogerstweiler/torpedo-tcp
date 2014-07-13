package com.epam.training.server;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import com.epam.training.domain.Board;
import com.epam.training.domain.Engine;
import com.epam.training.domain.ShipType;
import com.epam.training.domain.ShipTypeReader;
import com.epam.training.message.Message;
import com.epam.training.message.Size;

public class MessageHandler extends ChannelInboundHandlerAdapter {

	private static final String INPUT_FILE = "ships.txt";

	private static final int BOARD_HEIGHT = 30;

	private static final int BOARD_WIDTH = 30;

	@Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("Client connected");

        Message message = new Size(BOARD_WIDTH + " " + BOARD_HEIGHT);
        ctx.write(message);
        ctx.flush();

        Board board = new Board(BOARD_WIDTH, BOARD_HEIGHT);
		Engine engine = new Engine(board);

		try(ShipTypeReader shipTypeReader = new ShipTypeReader(INPUT_FILE)) {
			while (shipTypeReader.hasNext()) {
				ShipType shipType = shipTypeReader.readShipType();
				board.createShips(shipType);
			}

			board.printShips();

			//engine.shoot();
		}
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        Message s = (Message) msg;
        System.out.println(s);
        //ctx.write(s);
        //ctx.flush();
    }
}
