package com.organizador.financas.service;

import com.organizador.financas.model.ContaFixa;
import com.organizador.financas.model.GastoAdicional;
import com.organizador.financas.model.HistoricoPagamento;
import com.organizador.financas.repository.ContaFixaRepository;
import com.organizador.financas.repository.GastoAdicionalRepository;
import com.organizador.financas.repository.HistoricoPagamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.List;
import java.util.Locale;

@Service
public class FinancasService {

    @Autowired
    private ContaFixaRepository contaFixaRepository;

    @Autowired
    private GastoAdicionalRepository gastoAdicionalRepository;

    @Autowired
    private HistoricoPagamentoRepository historicoPagamentoRepository;

    public List<ContaFixa> listarContasFixas() {
        return contaFixaRepository.findAll();
    }

    public ContaFixa salvarContaFixa(ContaFixa conta) {
        conta.setPaga(false);
        return contaFixaRepository.save(conta);
    }

    public ContaFixa pagarContaFixa(Long id) {
        ContaFixa conta = contaFixaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Conta não encontrada"));

        HistoricoPagamento historico = new HistoricoPagamento();
        historico.setDescricao(conta.getDescricao());
        historico.setValorPago(conta.getValor());
        historico.setDataPagamentoRealizado(LocalDate.now());

        String mesAno = conta.getDataVencimento().getMonth().getDisplayName(TextStyle.FULL, new Locale("pt", "BR"))
                + "/" + conta.getDataVencimento().getYear();
        historico.setMesReferencia(mesAno);

        historicoPagamentoRepository.save(historico);

        conta.setDataVencimento(conta.getDataVencimento().plusMonths(1));
        conta.setPaga(false);

        return contaFixaRepository.save(conta);
    }

    public List<GastoAdicional> listarGastosAdicionais() {
        return gastoAdicionalRepository.findAll();
    }

    public GastoAdicional salvarGastoAdicional(GastoAdicional gasto) {
        if (gasto.getDataGasto() == null) {
            gasto.setDataGasto(LocalDate.now());
        }
        return gastoAdicionalRepository.save(gasto);
    }

    public List<HistoricoPagamento> listarHistorico() {
        return historicoPagamentoRepository.findAll();
    }
    // Método para deletar uma conta fixa
    public void deletarContaFixa(Long id) {
        contaFixaRepository.deleteById(id);
    }

    // Método para deletar um gasto adicional
    public void deletarGastoAdicional(Long id) {
        gastoAdicionalRepository.deleteById(id);
    }

    // Método para limpar TODO o histórico de logs
    public void limparHistorico() {
        historicoPagamentoRepository.deleteAll();
    }
    public void pagarGastoAdicional(Long id) {
        GastoAdicional gasto = gastoAdicionalRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Gasto não encontrado"));
        HistoricoPagamento historico = new HistoricoPagamento();
        historico.setDescricao(gasto.getLocal() + (gasto.getDescricao() != null && !gasto.getDescricao().isEmpty() ? " - " + gasto.getDescricao() : ""));
        historico.setValorPago(gasto.getValor());
        historico.setDataPagamentoRealizado(LocalDate.now());

        String mesAno = gasto.getDataGasto().getMonth().getDisplayName(java.time.format.TextStyle.FULL, new java.util.Locale("pt", "BR"))
                + "/" + gasto.getDataGasto().getYear();
        historico.setMesReferencia(mesAno);

        historicoPagamentoRepository.save(historico);

        gastoAdicionalRepository.deleteById(id);
    }


}
