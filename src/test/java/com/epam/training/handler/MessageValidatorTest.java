package com.epam.training.handler;

import static com.epam.training.handler.MessageValidator.NO_MESSAGE;
import static org.mockito.Mockito.mock;
import io.netty.channel.ChannelHandlerContext;

import org.junit.Before;
import org.junit.Test;

import com.epam.training.handler.MessageValidator;
import com.epam.training.message.Fire;
import com.epam.training.message.Hit;
import com.epam.training.message.Lost;
import com.epam.training.message.Size;
import com.epam.training.message.Sunk;
import com.epam.training.message.Won;

public class MessageValidatorTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testChannelReadWhenInputFireNoExceptionThrown() throws Exception {
		//given
		MessageValidator validator = new MessageValidator(NO_MESSAGE, new Size("1 2"));
		ChannelHandlerContext ctx = mock(ChannelHandlerContext.class);
		Object fire = new Fire("1 1");

		//when
		validator.channelRead(ctx, fire);

		//then no exception thrown
	}

	@Test
	public void testChannelReadWhenInputHitNoExceptionThrown() throws Exception {
		//given
		MessageValidator validator = new MessageValidator(new Hit(), NO_MESSAGE);
		ChannelHandlerContext ctx = mock(ChannelHandlerContext.class);
		Object fire = new Fire("1 1");

		//when
		validator.channelRead(ctx, fire);

		//then no exception thrown
	}

	@Test(expected = IllegalArgumentException.class)
	public void testChannelReadWhenInputSizeExceptionThrown() throws Exception {
		//given
		MessageValidator validator = new MessageValidator(NO_MESSAGE, NO_MESSAGE);
		ChannelHandlerContext ctx = mock(ChannelHandlerContext.class);
		Object size = new Size("1 1");

		//when
		validator.channelRead(ctx, size);

		//then exception thrown
	}

	@Test
	public void testChannelReadWhenInputSunkNoExceptionThrown() throws Exception {
		//given
		MessageValidator validator = new MessageValidator(NO_MESSAGE, new Fire("1 1"));
		ChannelHandlerContext ctx = mock(ChannelHandlerContext.class);
		Object sunk = new Sunk();

		//when
		validator.channelRead(ctx, sunk);

		//then no exception thrown
	}

	@Test
	public void testChannelReadWhenInputLostNoExceptionThrown() throws Exception {
		//given
		MessageValidator validator = new MessageValidator(new Sunk(), NO_MESSAGE);
		ChannelHandlerContext ctx = mock(ChannelHandlerContext.class);
		Object lost = new Lost();

		//when
		validator.channelRead(ctx, lost);

		//then no exception thrown
	}

	@Test
	public void testChannelReadWhenInputWonNoExceptionThrown() throws Exception {
		//given
		MessageValidator validator = new MessageValidator(NO_MESSAGE, new Sunk());
		ChannelHandlerContext ctx = mock(ChannelHandlerContext.class);
		Object won = new Won();

		//when
		validator.channelRead(ctx, won);

		//then no exception thrown
	}


}
