package com.example.postech_tc4_pedido.controller;

import com.example.postech_tc4_pedido.dto.AtualizacaoPagamentoDTO;
import com.example.postech_tc4_pedido.usecases.AtualizarPagamentoUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AtualizacaoPagamentoController {

    private final AtualizarPagamentoUseCase atualizarPagamentoUseCase;

    public AtualizacaoPagamentoController(AtualizarPagamentoUseCase atualizarPagamentoUseCase) {
        this.atualizarPagamentoUseCase = atualizarPagamentoUseCase;
    }

    @PostMapping("/atualizacao-pagamento")
    public ResponseEntity<Void> atualizarPagamento(@RequestBody AtualizacaoPagamentoDTO atualizacaoPagamentoDTO){
        atualizarPagamentoUseCase.atualizar(atualizacaoPagamentoDTO);
        return ResponseEntity.ok().build();
    }
}
