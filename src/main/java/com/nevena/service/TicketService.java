package com.nevena.service;

import com.nevena.entities.Ticket;

import java.util.List;

public interface TicketService {

    List<Ticket> getAllTickets();
    Ticket getTicketById(Long id);
    Ticket saveTicket(Ticket ticket);
    void deleteTicket(Long id);
}
