package com.epam.training.message;

public enum MessageEnum {
    FIRE {
        @Override
        public Message createMessage(String input) {
            int delimiter = input.indexOf(' ');

            return new Fire(input.substring(delimiter + 1, input.length()));
        }
    },

    HIT {
        @Override
        public Message createMessage(String input) {
            return new Hit();
        }
    },

    LOST {
        @Override
        public Message createMessage(String input) {
            return new Lost();
        }
    },

    MISS {
        @Override
        public Message createMessage(String input) {
            return new Miss();
        }
    },

    SIZE {
        @Override
        public Message createMessage(String input) {
            int delimiter = input.indexOf(' ');

            return new Size(input.substring(delimiter + 1, input.length()));
        }
    },

    SUNK {
        @Override
        public Message createMessage(String input) {
            return new Sunk();
        }
    },

    WON {
        @Override
        public Message createMessage(String input) {
            return new Won();
        }
    },

    ERROR {
        @Override
        public Message createMessage(String input) {
            return new Error("Unrecognized command: " + input);
        }
    };

    public abstract Message createMessage(String input);
}
