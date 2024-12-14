package com.johnworks.fastmeal.infra.repository;

import static com.johnworks.fastmeal.infra.repository.spec.RestauranteSpecs.comFreteFratis;
import static com.johnworks.fastmeal.infra.repository.spec.RestauranteSpecs.comNomeSemelhante;

import com.johnworks.fastmeal.domain.model.Restaurante;
import com.johnworks.fastmeal.domain.repository.RestauranteRepository;
import com.johnworks.fastmeal.domain.repository.RestauranteRepositoryQueries;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.util.Predicates;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Repository
public class RestauranteRespositoryImpl implements RestauranteRepositoryQueries {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired @Lazy
    private RestauranteRepository restauranteRepository;

//
//    @Override
//    public List<Restaurante> findNomeETaxaFrete(String nome, BigDecimal taxaFreteInicial, BigDecimal taxaFreteFinal) {
//
//        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
//
//
//        CriteriaQuery<Restaurante> criteriaQuery = criteriaBuilder.createQuery(Restaurante.class);
//        Root<Restaurante> root = criteriaQuery.from(Restaurante.class); // from Restaurante
//
//        var predicates = new ArrayList<Predicate>();
//
//        if(StringUtils.hasText(nome)){
//            predicates.add(criteriaBuilder.like(root.get("nome"), "%" + nome + "%"));
//        }
//
//        if(taxaFreteFinal != null){
//            predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("taxaFrete"), taxaFreteInicial));
//        }
//
//        if(taxaFreteFinal != null){
//            predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("taxaFrete"), taxaFreteFinal));
//        }
//
//        criteriaQuery.where(predicates.toArray(new Predicate[0]));
//        TypedQuery<Restaurante> query = entityManager.createQuery(criteriaQuery);
//
//        return query.getResultList();
//    }

    @Override
    public List<Restaurante> findComFreteGratis(String nome) {
        return restauranteRepository.findAll(comFreteFratis().and(comNomeSemelhante(nome)));
    }
}
