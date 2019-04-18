package com.eventservice.controllers;

import com.eventservice.exceptions.EntityNotFoundException;
import com.eventservice.models.Event;
import com.eventservice.services.EventService;
import com.google.gson.Gson;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@SpringBootTest
public class EventControllerTest {

    @Autowired
    private MockMvc mockMvc;
    private Gson gson = new Gson();

    @Mock
    EventService eventService;

    @InjectMocks
    EventController controllerUnderTest;

    @Before
    public void setup() {

        MockitoAnnotations.initMocks(this);
        controllerUnderTest = new EventController(eventService);
        this.mockMvc = MockMvcBuilders.standaloneSetup(controllerUnderTest).build();

    }

    @Test
    @Ignore
    public void getEventSuccess() throws Exception {
        List evenList = Arrays.asList(new Event().setEntityId("id_one"),
                new Event().setEntityId("id_two"));
        when(eventService.getEvents("id")).thenReturn(evenList);
        mockMvc
                .perform(get("/event/id"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(gson.toJson(evenList)));

    }

    @Test
    @Ignore
    public void getEventNotFoundException() throws Exception {
        when(eventService.getEvents("id")).thenThrow(new EntityNotFoundException(""));
        mockMvc
                .perform(get("/event/id"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }
}