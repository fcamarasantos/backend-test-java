package com.fcamarasantos.testebackendjava.controller;

import com.fcamarasantos.testebackendjava.domain.veiculo.TipoVeiculo;
import com.fcamarasantos.testebackendjava.domain.veiculo.Veiculo;
import com.fcamarasantos.testebackendjava.domain.veiculo.VeiculoRepository;
import com.fcamarasantos.testebackendjava.domain.veiculo.dto.CreateVeiculoDTO;
import com.fcamarasantos.testebackendjava.domain.veiculo.dto.VeiculoDetailsDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
@ActiveProfiles("test")
class VeiculoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private JacksonTester<CreateVeiculoDTO> createVeiculoJson;

    @Autowired
    private JacksonTester<VeiculoDetailsDTO> veiculoDetailsJson;

    @MockBean
    private VeiculoRepository veiculoRepository;

    @Test
    @DisplayName("Deve retornar 400 quando um dos campos obrigatorios nao forem enviados.")
    public void cadastrarVeiculoInvalido() throws Exception {
        var veiculoInvalidoJson = createVeiculoJson.write(
                new CreateVeiculoDTO("marca-fake", "modelo-fake", "abc-123", "", TipoVeiculo.MOTO)
        );

        var response = mockMvc
                .perform(post("/veiculos/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(veiculoInvalidoJson.getJson()))
                .andReturn();

        assertEquals(response.getResponse().getStatus(), HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("Deve retornar 201 quando um veiculo valido for cadastrado.")
    public void cadastrarVeiculoValido() throws Exception {
        var veiculoValido = new CreateVeiculoDTO("marca-fake", "modelo-fake", "abc-123", "azul", TipoVeiculo.MOTO);
        var veiculoValidoJson = createVeiculoJson.write(veiculoValido);

        var veiculo = new Veiculo(veiculoValido);
        when(veiculoRepository
                .save(any()))
                .thenReturn(veiculo);

        var response = mockMvc
                .perform(post("/veiculos/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(veiculoValidoJson.getJson()))
                .andReturn();

        var veiculoEsperadoJson = veiculoDetailsJson.write(
                new VeiculoDetailsDTO(veiculo)
        );

        assertEquals(response.getResponse().getStatus(), HttpStatus.CREATED.value());
        assertEquals(response.getResponse().getContentAsString(), veiculoEsperadoJson.getJson());
    }

}