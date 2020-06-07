package pl.uj.io.cuteanimals.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import pl.uj.io.cuteanimals.exception.InvalidCommandException;
import pl.uj.io.cuteanimals.model.ItemType;
import pl.uj.io.cuteanimals.model.entity.Item;
import pl.uj.io.cuteanimals.service.GameService;
import pl.uj.io.cuteanimals.service.ItemService;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@ExtendWith(MockitoExtension.class)
public class GameControllerTest {

    private MockMvc mockMvc;

    @Mock
    private GameService gameService;

    @InjectMocks
    private GameController gameController;

    @BeforeEach
    private void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(gameController)
                .build();
    }

    @Test
    public void receiveOrderAndReturnResultSucceedWhenCommandIsEqualToStart() throws Exception {
        given(gameService.getLocationInfo()).willReturn("Info 1");

        var response = mockMvc.perform(post("/game/1/msg")
                .accept(MediaType.TEXT_PLAIN)
                .content("start")
                .contentType(MediaType.TEXT_PLAIN))
                .andReturn()
                .getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("Info 1");
    }

    @Test
    public void receiveOrderAndReturnResultSucceedWhenCommandIsInvalid() throws Exception {
        given(gameService.execute(1, "command"))
                .willThrow(new InvalidCommandException("error"));

        var response = mockMvc.perform(post("/game/1/msg")
                .accept(MediaType.TEXT_PLAIN)
                .content("command")
                .contentType(MediaType.TEXT_PLAIN))
                .andReturn()
                .getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("error");
    }
    @Test
    public void receiveOrderAndReturnResultSucceedWhenCommandIsValidAndNotEqualToStart()
            throws Exception {
        given(gameService.execute(1, "command")).willReturn("result 1");

        var response = mockMvc.perform(post("/game/1/msg")
                .accept(MediaType.TEXT_PLAIN)
                .content("command")
                .contentType(MediaType.TEXT_PLAIN))
                .andReturn()
                .getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("result 1");
    }


}