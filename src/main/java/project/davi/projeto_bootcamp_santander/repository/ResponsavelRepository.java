package project.davi.projeto_bootcamp_santander.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.davi.projeto_bootcamp_santander.model.Responsavel;

@Repository
public interface ResponsavelRepository extends JpaRepository<Responsavel, Integer> {
    Responsavel findByNome(String nome);
}
