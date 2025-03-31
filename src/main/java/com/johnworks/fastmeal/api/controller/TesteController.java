package com.johnworks.fastmeal.api.controller;

import com.johnworks.fastmeal.domain.model.Cozinha;
import com.johnworks.fastmeal.domain.model.Restaurante;
import com.johnworks.fastmeal.domain.repository.CozinhaRepository;
import com.johnworks.fastmeal.domain.repository.RestauranteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/testes")
public class TesteController {

    @Autowired
    CozinhaRepository cozinhaRepository;

    @Autowired
    RestauranteRepository restauranteRepository;

    @GetMapping
    public List<Cozinha> consultaPorNome(String nome){
        return cozinhaRepository.findCozinhaByNome(nome);
    }

    @GetMapping("/cozinhaExists")
    public boolean cozinhaExists(String nome){
        return cozinhaRepository.existsByNome(nome);
    }
    @GetMapping("/consultaContendoNome")
    public List<Cozinha> consultaContendoNome(String nome){
        return cozinhaRepository.findAllByNomeContains(nome);
    }
    @GetMapping("/restaurantePorTaxaFrete")
    public List<Restaurante> restaurantePorTaxaFrete(BigDecimal taxaInicial, BigDecimal taxaFinal){
        return restauranteRepository.findByTaxaFreteBetween(taxaInicial, taxaFinal);
    }

    @GetMapping("/restaurantePorNomeECozinhaId")
    public List<Restaurante> restaurantePorNomeECozinhaId(String nome, Long cozinhaId){
        return restauranteRepository.findByNomeContainingAndCozinhaId(nome, cozinhaId);
    }
    @GetMapping("/restaurantePorNomePrimeiro")
    public Optional<Restaurante> restaurantePorNomePrimeiro(String nome){
        return restauranteRepository.findFirstRestauranteByNomeContaining(nome);
    }

    @GetMapping("/restaurantePorNomeTop2")
    public List<Restaurante> restaurantePorNomeTop2(String nome){
        return restauranteRepository.findTop2ByNomeContaining(nome);
    }
    @GetMapping("/restaurantePorNomeTop23")
    public int countPorCozinha(Long cozinhaId){
        return restauranteRepository.countByCozinhaId(cozinhaId);
    }

   // @GetMapping("/restaurantes/com-frete-gratis")
   // public List<Restaurante> restaurantesComFreteGratis(String nome){
        //var comFreteGratis = new ResturanteComFreteGratisSpec();
        //var comNomeSemelhante = new RestauranteComNomeSemelhanteSpec(nome);
        //return restauranteRepository.findAll(comFreteFratis().and(comNomeSemelhante(nome)));

    //    return restauranteRepository.findComFreteGratis(nome);
    //}

    @GetMapping("/restaurantes/buscar-primeirp")
    public Optional<Restaurante> restaurantesBuscarPrimeiro(){
        return restauranteRepository.buscarPrimeiro();
    }
}
