package com.epam.training.handler;

import static com.epam.training.handler.MessageCodec.CHARSET;
import static com.epam.training.handler.MessageCodec.FIRE;
import static com.epam.training.handler.MessageCodec.HIT;
import static com.epam.training.handler.MessageCodec.LOST;
import static com.epam.training.handler.MessageCodec.MISS;
import static com.epam.training.handler.MessageCodec.SIZE;
import static com.epam.training.handler.MessageCodec.SUNK;
import static com.epam.training.handler.MessageCodec.WON;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import com.epam.training.message.Fire;
import com.epam.training.message.Hit;
import com.epam.training.message.Lost;
import com.epam.training.message.Miss;
import com.epam.training.message.Size;
import com.epam.training.message.Sunk;
import com.epam.training.message.Won;

public class MessageCodecTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testDecodeWhenInputIsSizeReturnsSizeMessage() throws Exception {
		//given
		MessageCodec codec = new MessageCodec();
		final byte[] testInputMessage = (SIZE + " 1 2").getBytes(CHARSET);

		//when
		ChannelHandlerContext ctx = Mockito.mock(ChannelHandlerContext.class);
		ByteBuf in = mockByteBuf(testInputMessage);

		List<Object> out = new ArrayList<>();
		codec.decode(ctx, in, out);

		//then
		assertEquals(1, out.size());
		assertEquals(Size.class, out.get(0).getClass());
	}

	@Test
	public void testDecodeWhenInputIsFireReturnsFireMessage() throws Exception {
		//given
		MessageCodec codec = new MessageCodec();
		final byte[] testInputMessage = (FIRE + " 1 2").getBytes(CHARSET);

		//when
		ChannelHandlerContext ctx = Mockito.mock(ChannelHandlerContext.class);
		ByteBuf in = mockByteBuf(testInputMessage);

		List<Object> out = new ArrayList<>();
		codec.decode(ctx, in, out);

		//then
		assertEquals(1, out.size());
		assertEquals(Fire.class, out.get(0).getClass());
	}

	@Test
	public void testDecodeWhenInputIsHitReturnsHitMessage() throws Exception {
		//given
		MessageCodec codec = new MessageCodec();
		final byte[] testInputMessage = HIT.getBytes(CHARSET);

		//when
		ChannelHandlerContext ctx = Mockito.mock(ChannelHandlerContext.class);
		ByteBuf in = mockByteBuf(testInputMessage);

		List<Object> out = new ArrayList<>();
		codec.decode(ctx, in, out);

		//then
		assertEquals(1, out.size());
		assertEquals(Hit.class, out.get(0).getClass());
	}

	@Test
	public void testDecodeWhenInputIsSunkReturnsSunkMessage() throws Exception {
		//given
		MessageCodec codec = new MessageCodec();
		final byte[] testInputMessage = SUNK.getBytes(CHARSET);

		//when
		ChannelHandlerContext ctx = Mockito.mock(ChannelHandlerContext.class);
		ByteBuf in = mockByteBuf(testInputMessage);

		List<Object> out = new ArrayList<>();
		codec.decode(ctx, in, out);

		//then
		assertEquals(1, out.size());
		assertEquals(Sunk.class, out.get(0).getClass());
	}

	@Test
	public void testDecodeWhenInputIsMissReturnsMissMessage() throws Exception {
		//given
		MessageCodec codec = new MessageCodec();
		final byte[] testInputMessage = MISS.getBytes(CHARSET);

		//when
		ChannelHandlerContext ctx = Mockito.mock(ChannelHandlerContext.class);
		ByteBuf in = mockByteBuf(testInputMessage);

		List<Object> out = new ArrayList<>();
		codec.decode(ctx, in, out);

		//then
		assertEquals(1, out.size());
		assertEquals(Miss.class, out.get(0).getClass());
	}

	@Test
	public void testDecodeWhenInputIsLostReturnsLostMessage() throws Exception {
		//given
		MessageCodec codec = new MessageCodec();
		final byte[] testInputMessage = LOST.getBytes(CHARSET);

		//when
		ChannelHandlerContext ctx = Mockito.mock(ChannelHandlerContext.class);
		ByteBuf in = mockByteBuf(testInputMessage);

		List<Object> out = new ArrayList<>();
		codec.decode(ctx, in, out);

		//then
		assertEquals(1, out.size());
		assertEquals(Lost.class, out.get(0).getClass());
	}

	@Test
	public void testDecodeWhenInputIsWonReturnsWonMessage() throws Exception {
		//given
		MessageCodec codec = new MessageCodec();
		final byte[] testInputMessage = WON.getBytes(CHARSET);

		//when
		ChannelHandlerContext ctx = Mockito.mock(ChannelHandlerContext.class);
		ByteBuf in = mockByteBuf(testInputMessage);

		List<Object> out = new ArrayList<>();
		codec.decode(ctx, in, out);

		//then
		assertEquals(1, out.size());
		assertEquals(Won.class, out.get(0).getClass());
	}

	//TODO debug why not works
	//@Test
	public void testDecodeWhenInputIsErrorReturnsErrorMessage() throws Exception {
		//given
		MessageCodec codec = new MessageCodec();
		final byte[] testInputMessage = "ERROR error".getBytes(CHARSET);

		//when
		ChannelHandlerContext ctx = Mockito.mock(ChannelHandlerContext.class);
		ByteBuf in = mockByteBuf(testInputMessage);

		List<Object> out = new ArrayList<>();
		codec.decode(ctx, in, out);

		//then
		assertEquals(1, out.size());
		assertEquals(com.epam.training.message.Error.class, out.get(0).getClass());
	}

	private ByteBuf mockByteBuf(final byte[] inputMessage) {
		ByteBuf in = Mockito.mock(ByteBuf.class);
		when(in.readableBytes()).thenReturn(inputMessage.length);
		when(in.readBytes(Mockito.any(byte[].class))).then(new Answer<ByteBuf>() {
			@Override
			public ByteBuf answer(InvocationOnMock invocation) throws Throwable {
				Object[] args = invocation.getArguments();
				byte[] msg = (byte[]) args[0];
				System.arraycopy(inputMessage , 0, msg, 0, inputMessage.length);
				return null;
			}
		});

		return in;
	}

}
