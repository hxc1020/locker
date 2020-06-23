package com.thoughtworks.tdd;

public class Ticket {
    private TicketType type = TicketType.GIVEN_BY_LOCKER;

    public void setType(TicketType type) {
        this.type = type;
    }

    public TicketType getType() {
        return type;
    }
}
