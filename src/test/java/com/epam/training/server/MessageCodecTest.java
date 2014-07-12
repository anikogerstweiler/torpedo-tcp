package com.epam.training.server;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import com.epam.training.message.Size;

public class MessageCodecTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testDecodeWhenInputIsSizeReturnsSizeMessage() throws Exception {
		//given
		MessageCodec codec = new MessageCodec();
		final byte[] src = "SIZE 1 1".getBytes("UTF8");
		//when
		ChannelHandlerContext ctx = Mockito.mock(ChannelHandlerContext.class);
		ByteBuf in = Mockito.mock(ByteBuf.class);
		when(in.readableBytes()).thenReturn(src.length);
		when(in.readBytes(Mockito.any(byte[].class))).then(new Answer<ByteBuf>() {
			@Override
			public ByteBuf answer(InvocationOnMock invocation) throws Throwable {
				Object[] args = invocation.getArguments();
				byte[] msg = (byte[]) args[0];
				System.arraycopy(src , 0, msg, 0, src.length - 1);
				return null;
			}
		});
		List<Object> out = new ArrayList<>();
		codec.decode(ctx, in, out);
		assertEquals(1, out.size());
		Assert.assertEquals(Size.class, out.get(0).getClass());
	}

}
