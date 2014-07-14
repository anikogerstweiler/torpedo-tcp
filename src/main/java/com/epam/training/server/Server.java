package com.epam.training.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

import com.epam.training.handler.ErrorHandler;
import com.epam.training.handler.MessageCodec;
import com.epam.training.handler.MessageValidator;

public class Server {

	private static final int ONE_THREAD = 1;
    private final int port;

    public Server(int port) {
        this.port = port;
    }

    public void run() throws Exception {
        EventLoopGroup group = new NioEventLoopGroup(ONE_THREAD);

        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap
                .group(group)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                    	ch.pipeline().addLast(new LoggingHandler(LogLevel.TRACE))
                    			.addLast(new LineBasedFrameDecoder(1024))
                    			.addLast(new MessageCodec())
                    			.addLast(new MessageValidator())
                    			.addLast(new ServerHandler())
                    			.addLast(new ErrorHandler());
                    }
                })
                .option(ChannelOption.SO_BACKLOG, 128)
                .option(ChannelOption.TCP_NODELAY, true)
                .childOption(ChannelOption.SO_KEEPALIVE, true);

            // Bind and start to accept incoming connections.
            ChannelFuture f = bootstrap.bind(port).sync(); // (7)


            // Wait until the server socket is closed.
            // In this example, this does not happen, but you can do that to gracefully
            // shut down your server.
            f.channel().closeFuture().sync();

        } finally {
            group.shutdownGracefully();
        }
    }

    public static void main(String[] args) throws Exception {
        int port;
        if ( args . length > 0 ) {
            port = Integer.parseInt(args[0]);
        } else {
            port = 5000;
        }

        new Server(port).run();
    }

}
