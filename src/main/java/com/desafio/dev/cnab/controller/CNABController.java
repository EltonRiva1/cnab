package com.desafio.dev.cnab.controller;

import com.desafio.dev.cnab.model.CNAB;
import com.desafio.dev.cnab.service.CNABService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

@RestController
@RequestMapping("/cnab")
public class CNABController {
    private final CNABService cnabService;

    public CNABController(CNABService cnabService) {
        this.cnabService = cnabService;
    }

    @PostMapping("/upload")
    public String uploadCNAB(@RequestParam("file") MultipartFile file) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream()));
            this.cnabService.processCNABFile(br);
            return "Upload do arquivo CNAB realizado com sucesso!";
        } catch (IOException e) {
            e.printStackTrace();
            return "Erro ao processar o arquivo CNAB.";
        }
    }

    @GetMapping("/list")
    public List<CNAB> listCNAB() {
        return this.cnabService.getAllCNAB();
    }
}
