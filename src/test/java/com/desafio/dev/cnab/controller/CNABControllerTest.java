package com.desafio.dev.cnab.controller;

import com.desafio.dev.cnab.service.CNABService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.io.BufferedReader;
import java.nio.charset.StandardCharsets;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class CNABControllerTest {
    @Mock
    private CNABService cnabService;

    @InjectMocks
    private CNABController cnabController;

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(this.cnabController).build();
    }

    @Test
    public void testUploadCNABFile() throws Exception {
        // Dado um arquivo CNAB fictício em formato de string
        String cnabData = "3201903010000014200096206760174753****3153153453JOÃO MACEDO   BAR DO JOÃO\n" +
                "5201903010000013200556418150633123****7687145607MARIA JOSEFINALOJA DO Ó - MATRIZ\n" +
                "3201903010000012200845152540736777****1313172712MARCOS PEREIRAMERCADO DA AVENIDA\n";

        // Cria um MockMultipartFile com o conteúdo do arquivo CNAB fictício
        MockMultipartFile file = new MockMultipartFile("file", "CNAB.txt",
                "text/plain", cnabData.getBytes(StandardCharsets.UTF_8));

        // Quando o cnabService.processCNABFile() for chamado, não faça nada
        doNothing().when(this.cnabService).processCNABFile(any(BufferedReader.class));

        // Realiza a requisição POST para o endpoint /cnab/upload com o arquivo CNAB fictício
        this.mockMvc.perform(multipart("/cnab/upload").file(file))
                .andExpect(status().isOk());
    }
}
