package br.com.guilherme.APIcursoProgramacao.repository;

import br.com.guilherme.APIcursoProgramacao.model.Cursos;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CursosRepository extends JpaRepository<Cursos, Integer> {
}
