package com.epam.training;

import com.epam.training.client.Client;
import com.epam.training.server.Server;

public class Main {

	private static final String DEFAULT_HOST = "localhost";
	private static final int DEFAULT_PORT = 5000;

	public static void main(String[] args) throws Exception {
		String mode = "server";
		if (args.length > 0) {
			mode = args[0];
		}

		if ("server".equals(mode)) {
			startServer(args);
		} else {
			startClient(args);
		}
	}

	private static void startServer(String[] args) throws Exception {
		int port = DEFAULT_PORT;
        if (args.length > 1) {
            port = Integer.parseInt(args[1]);
        }

        new Server(port).run();
	}

	private static void startClient(String[] args) throws Exception {
		//Elemer
		//String host = "ephubudw0543";
		//Peti
		//String host = "ephubudw0080";
		//Zsolti
		//String host = "ephubudw0544";
		String host = DEFAULT_HOST;
		if (args.length > 1) {
			host = args[1];
		}

		int port = DEFAULT_PORT;
        if (args.length > 2) {
            port = Integer.parseInt(args[2]);
        }

        new Client(host, port).run();
	}

}
