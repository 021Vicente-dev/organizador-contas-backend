package com.organizador.financas.controller;

import com.organizador.financas.model.ContaFixa;
import com.organizador.financas.model.GastoAdicional;
import com.organizador.financas.model.HistoricoPagamento;
import com.organizador.financas.service.FinancasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/financas")
@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class FinancasController {

    @Autowired
    private FinancasService financasService;


    @GetMapping("/contas-fixas")
    public List<ContaFixa> listarContasFixas() {
        return financasService.listarContasFixas();
    }

    @PostMapping("/contas-fixas")
    public ContaFixa salvarContaFixa(@RequestBody ContaFixa conta) {
        return financasService.salvarContaFixa(conta);
    }

    @PutMapping("/contas-fixas/{id}/pagar")
    public ContaFixa pagarConta(@PathVariable Long id) {
        return financasService.pagarContaFixa(id);
    }


    @GetMapping("/gastos-adicionais")
    public List<GastoAdicional> listarGastosAdicionais() {
        return financasService.listarGastosAdicionais();
    }

    @PostMapping("/gastos-adicionais")
    public GastoAdicional salvarGastoAdicional(@RequestBody GastoAdicional gasto) {
        return financasService.salvarGastoAdicional(gasto);
    }


    @GetMapping("/historico")
    public List<HistoricoPagamento> listarHistorico() {
        return financasService.listarHistorico();
    }
    @DeleteMapping("/contas-fixas/{id}")
    public void deletarContaFixa(@PathVariable Long id) {
        financasService.deletarContaFixa(id);
    }

    @DeleteMapping("/gastos-adicionais/{id}")
    public void deletarGastoAdicional(@PathVariable Long id) {
        financasService.deletarGastoAdicional(id);
    }

    @DeleteMapping("/historico")
    public void limparHistorico() {
        financasService.limparHistorico();
    }
    @PutMapping("/gastos-adicionais/{id}/pagar")
    public void pagarGastoAdicional(@PathVariable Long id) {
        financasService.pagarGastoAdicional(id);
    }


}
