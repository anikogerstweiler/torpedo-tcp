package com.epam.training.server;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

public class MessageCodecTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testDecodeWhenInputIsSizeReturnsSizeMessage() {
		//given
		MessageCodec codec = new MessageCodec();

		//when
		ChannelHandlerContext ctx = Mockito.mock(ChannelHandlerContext.class);
		ByteBuf in = Mockito.mock(ByteBuf.class);
		Mockito.when(in.readBytes(Mockito.any(byte[].class))).then(new Answer<ByteBuf>() {
			@Override
			public ByteBuf answer(InvocationOnMock invocation) throws Throwable {
				Object[] args = invocation.getArguments();
				return null;
			}
		});
		List<Object> out;
		//codec.decode(ctx, in, out);
	}

}
