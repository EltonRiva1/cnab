package com.desafio.dev.cnab.service;

import com.desafio.dev.cnab.repository.CNABRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.BufferedReader;
import java.io.StringReader;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CNABServiceTest {
    @Mock
    private CNABRepository cnabRepository;

    @InjectMocks
    private CNABService cnabService;

    @Test
    void testProcessCNABFile() throws Exception {
        // Dado um arquivo CNAB fictício em formato de string
        String cnabData = "3201903010000014200096206760174753****3153153453JOÃO MACEDO   BAR DO JOÃO\n" +
                "5201903010000013200556418150633123****7687145607MARIA JOSEFINALOJA DO Ó - MATRIZ\n" +
                "3201903010000012200845152540736777****1313172712MARCOS PEREIRAMERCADO DA AVENIDA\n";

        // Cria um BufferedReader com o conteúdo do arquivo CNAB fictício
        BufferedReader br = new BufferedReader(new StringReader(cnabData));

        // Quando o cnabRepository.saveAll() for chamado, retorne uma lista vazia
        when(this.cnabRepository.saveAll(any(List.class))).thenReturn(List.of());

        // Realiza o processamento do arquivo CNAB fictício
        this.cnabService.processCNABFile(br);

        // Verifica se o cnabRepository.saveAll() foi chamado corretamente
        assertEquals(1, this.cnabRepository.saveAll(List.of()).size());
    }
}
