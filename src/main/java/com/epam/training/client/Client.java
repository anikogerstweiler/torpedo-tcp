package com.epam.training.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

import com.epam.training.handler.ErrorHandler;
import com.epam.training.handler.MessageCodec;
import com.epam.training.handler.MessageHandler;

public class Client {

	private static final int ONE_THREAD = 1;

	private String host;
	private int port;

	public Client(String host, int port) {
		this.host = host;
		this.port = port;
	}

	public void run() throws Exception {
        EventLoopGroup workerGroup = new NioEventLoopGroup(ONE_THREAD);

        try {
            Bootstrap b = new Bootstrap();
            b.group(workerGroup);
            b.channel(NioSocketChannel.class);
            b.option(ChannelOption.SO_KEEPALIVE, true);
            b.option(ChannelOption.TCP_NODELAY, true);
            b.handler(new ChannelInitializer<SocketChannel>() {
                @Override
                public void initChannel(SocketChannel ch) throws Exception {
                	ch.pipeline().addLast(new LoggingHandler(LogLevel.TRACE))
        			.addLast(new LineBasedFrameDecoder(1024))
        			.addLast(new MessageCodec())
        			.addLast(new ClientValidator())
        			.addLast(new MessageHandler())
        			.addLast(new ErrorHandler());
                }
            });

            // Start the client.
            ChannelFuture f = b.connect(host, port).sync(); // (5)

            // Wait until the connection is closed.
            f.channel().closeFuture().sync();
        } finally {
            workerGroup.shutdownGracefully();
        }
	}
}
