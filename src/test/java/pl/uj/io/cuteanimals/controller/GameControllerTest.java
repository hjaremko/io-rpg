package pl.uj.io.cuteanimals.controller;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.util.List;
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
import pl.uj.io.cuteanimals.model.Color;
import pl.uj.io.cuteanimals.model.CompoundResult;
import pl.uj.io.cuteanimals.model.Result;
import pl.uj.io.cuteanimals.service.GameService;

@ExtendWith(MockitoExtension.class)
public class GameControllerTest {

    private MockMvc mockMvc;

    @Mock private GameService gameService;

    @InjectMocks private GameController gameController;

    @BeforeEach
    private void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(gameController).build();
    }

    @Test
    public void receiveOrderAndReturnResultSucceedWhenCommandIsEqualToStart() throws Exception {
        given(gameService.getLocationInfo()).willReturn("Info 1");

        var response =
                mockMvc.perform(
                                post("/game/1/msg")
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
        given(gameService.execute(1, "command")).willThrow(new InvalidCommandException("error"));

        var response =
                mockMvc.perform(
                                post("/game/1/msg")
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
        given(gameService.execute(1, "command")).willReturn(new Result("result 1"));

        var response =
                mockMvc.perform(
                                post("/game/1/msg")
                                        .accept(MediaType.TEXT_PLAIN)
                                        .content("command")
                                        .contentType(MediaType.TEXT_PLAIN))
                        .andReturn()
                        .getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("result 1");
    }

    @Test
    public void receiveOrderAndReturnResultSucceedWhenExecutionResultIsCompoundResult()
            throws Exception {
        given(gameService.execute(1, "command"))
                .willReturn(
                        new CompoundResult(
                                List.of(
                                        new Result("result 1", Color.RED),
                                        new Result("result 2", Color.GREEN),
                                        new Result("result 3", Color.YELLOW))));

        var response =
                mockMvc.perform(
                                post("/game/1/msg")
                                        .accept(MediaType.TEXT_PLAIN)
                                        .content("command")
                                        .contentType(MediaType.TEXT_PLAIN))
                        .andReturn()
                        .getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).contains("result 1");
        assertThat(response.getContentAsString()).contains("result 2");
        assertThat(response.getContentAsString()).contains("result 3");
    }
}
