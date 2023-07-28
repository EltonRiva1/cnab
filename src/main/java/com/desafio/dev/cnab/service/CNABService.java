package com.desafio.dev.cnab.service;

import com.desafio.dev.cnab.model.CNAB;
import com.desafio.dev.cnab.repository.CNABRepository;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class CNABService {
    private final CNABRepository cnabRepository;

    public CNABService(CNABRepository cnabRepository) {
        this.cnabRepository = cnabRepository;
    }

    public void processCNABFile(BufferedReader br) throws IOException {
        String line;
        List<CNAB> cnabs = new ArrayList<>();
        while ((line = br.readLine()) != null) {
            if (line.length() >= 80) { // Verifica se a linha tem pelo menos 80 caracteres
                try {
                    // Faz o parsing do arquivo CNAB
                    String tipoRegistro = line.substring(0, 1);
                    if ("3".equals(tipoRegistro)) {
                        String dataString = line.substring(1, 9) + line.substring(42, 48);
                        LocalDateTime data = LocalDateTime.parse(dataString, DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
                        String descricao = line.substring(62, 81).trim();
                        String valorString = line.substring(9, 19) + "." + line.substring(29, 31);
                        BigDecimal valor = new BigDecimal(valorString);

                        CNAB cnab = new CNAB();
                        cnab.setData(data);
                        cnab.setDescricao(descricao);
                        cnab.setValor(valor);

                        cnabs.add(cnab);
                    }
                } catch (Exception e) {
                    // Ignora linhas com formato inválido
                }
            }
        }

        this.cnabRepository.saveAll(cnabs);
    }

    public List<CNAB> getAllCNAB() {
        return this.cnabRepository.findAll();
    }
}
