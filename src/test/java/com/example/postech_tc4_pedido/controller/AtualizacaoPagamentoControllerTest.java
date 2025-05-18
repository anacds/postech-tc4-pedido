package com.example.postech_tc4_pedido.controller;

import com.example.postech_tc4_pedido.dto.AtualizacaoPagamentoDTO;
import com.example.postech_tc4_pedido.usecases.AtualizarPagamentoUseCase;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AtualizacaoPagamentoController.class)
class AtualizacaoPagamentoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AtualizarPagamentoUseCase atualizarPagamentoUseCase;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void deveAtualizarPagamentoComSucesso() throws Exception {
        AtualizacaoPagamentoDTO dto = new AtualizacaoPagamentoDTO(
                "123456",
                "PROCESSADO_SUCESSO"
        );

        doNothing().when(atualizarPagamentoUseCase).atualizar(dto);

        mockMvc.perform(post("/api/atualizacao-pagamento")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk());
    }
}