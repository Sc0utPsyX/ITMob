package ru.geekbrains.spring.ITMob;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.geekbrains.spring.ITMob.entities.Event;
import ru.geekbrains.spring.ITMob.entities.EventMembers;
import ru.geekbrains.spring.ITMob.repositories.EventMembersRepository;
import ru.geekbrains.spring.ITMob.services.EventMembersService;
import ru.geekbrains.spring.ITMob.services.EventService;

import java.util.Optional;

@SpringBootTest
public class EventMembersServiceTest {
    @Autowired
    private EventMembersService eventMembersService;

    @MockBean
    private EventService eventService;

    @MockBean
    private EventMembersRepository eventMembersRepository;

    @Test
    public void createEventMembersTest() {

        Event event = new Event();
        event.setId(19224L);
        event.setTitle("Juice");
        event.setDescription("Juice is the best");
        //event.setEvent_date('1999-01-08 04:05:06');
        event.setEvent_place("AT&T Park, Москва");

        Mockito.doReturn(Optional.of(event)).when(eventService).findById(19224L);

        EventMembers eventMembers = eventMembersService.createEventMembers(19224L, "Bob");
        Assertions.assertEquals(eventMembers.getUsername(), "Bob");
        Mockito.verify(eventMembersRepository, Mockito.times(1)).save(ArgumentMatchers.any());

    }
}
