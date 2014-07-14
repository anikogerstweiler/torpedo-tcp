package com.epam.training.client;

import com.epam.training.handler.MessageValidator;
import com.epam.training.message.Fire;
import com.epam.training.message.Hit;
import com.epam.training.message.Miss;
import com.epam.training.message.Size;
import com.epam.training.message.Sunk;

public class ClientValidator extends MessageValidator {

	@Override
	protected void verifySizeMessage(Object actualMessage) {
		if (actualMessage instanceof Size &&
				(lastMessageGet != NO_MESSAGE || lastMessageSent != NO_MESSAGE)) {
			signalError(actualMessage);
		}
	}

	@Override
	protected void verifyFireMessage(Object actualMessage) {
		if (actualMessage instanceof Fire &&
    			!(lastMessageGet instanceof Hit
    				|| lastMessageGet instanceof Sunk
    				|| lastMessageGet instanceof Miss)) {
    		signalError(actualMessage);
    	}
	}

}
