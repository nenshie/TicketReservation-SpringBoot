package com.nevena.mappers;

import com.nevena.entities.*;
import org.mapstruct.ObjectFactory;
import org.mapstruct.TargetType;
import org.springframework.stereotype.Component;

@Component
public class IdResolvers {

    @ObjectFactory
    public Film resolveFilm(Long id, @TargetType Class<Film> type) {
        if (id == null) return null;
        Film f = new Film();
        f.setFilmId(id);
        return f;
    }

    @ObjectFactory
    public Room resolveRoom(Long id, @TargetType Class<Room> type) {
        if (id == null) return null;
        Room r = new Room();
        r.setRoomId(id);
        return r;
    }

    @ObjectFactory
    public Genre resolveGenre(Long id, @TargetType Class<Genre> type) {
        if (id == null) return null;
        Genre g = new Genre();
        g.setGenreId(id);
        return g;
    }


    @ObjectFactory
    public Ticket resolveTicket(Long id, @TargetType Class<Ticket> type) {
        if (id == null) return null;
        Ticket t = new Ticket();
        t.setTicketId(id);
        return t;
    }

    @ObjectFactory
    public Reservation resolveReservation(Long id, @TargetType Class<Reservation> type) {
        if (id == null) return null;
        Reservation r = new Reservation();
        r.setReservationId(id);
        return r;
    }

    @ObjectFactory
    public User resolveUser(Long id, @TargetType Class<User> type) {
        if (id == null) return null;
        User u = new User();
        u.setUserId(id);
        return u;
    }

    @ObjectFactory
    public Projection resolveProjection(Long id, @TargetType Class<Projection> type) {
        if (id == null) return null;
        Projection p = new Projection();
        p.setProjectionId(id);
        return p;
    }

    @ObjectFactory
    public Seat resolveSeat(Long id, @TargetType Class<Seat> type) {
        if (id == null) return null;
        Seat s = new Seat();
        s.setSeatId(id);
        return s;
    }
}
