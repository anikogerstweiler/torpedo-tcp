package com.epam.training.handler;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ErrorHandler extends ChannelHandlerAdapter {

	private static final Logger log = LoggerFactory.getLogger(ErrorHandler.class);

	public ErrorHandler() {}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
			throws Exception {

		ctx.writeAndFlush(new Error(cause.getMessage()));
		ctx.disconnect().get();

		log.error("Exception cought", cause);
	}


}
