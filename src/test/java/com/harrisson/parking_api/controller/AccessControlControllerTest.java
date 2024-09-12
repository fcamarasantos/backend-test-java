package com.harrisson.parking_api.controller;

import com.harrisson.parking_api.model.AccessControl;
import com.harrisson.parking_api.service.AccessControlService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AccessControlController.class)
public class AccessControlControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AccessControlService accessControlService;

    @Test
    public void testRegisterEntry() throws Exception {
        AccessControl accessControl = new AccessControl();
        Mockito.when(accessControlService.registerEntry(1L, 1L)).thenReturn(accessControl);

        mockMvc.perform(post("/access-controls")
                .param("vehicleId", "1")
                .param("establishmentId", "1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{}"));
    }

    @Test
    public void testRegisterExit() throws Exception {
        AccessControl accessControl = new AccessControl();
        Mockito.when(accessControlService.registerExit(1L, 1L)).thenReturn(accessControl);

        mockMvc.perform(post("/access-controls/exit")
                .param("vehicleId", "1")
                .param("establishmentId", "1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{}"));
    }
}