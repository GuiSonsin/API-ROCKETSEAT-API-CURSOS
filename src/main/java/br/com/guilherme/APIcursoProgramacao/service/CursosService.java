package br.com.guilherme.APIcursoProgramacao.service;

import br.com.guilherme.APIcursoProgramacao.model.Cursos;
import br.com.guilherme.APIcursoProgramacao.repository.CursosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CursosService {
    CursosRepository cursosRepository;

    @Autowired
    public CursosService(CursosRepository cursosRepository) {
        this.cursosRepository = cursosRepository;
    }

    public List<Cursos> listarCursos(){
        return cursosRepository.findAll();
    }

    public Cursos criarCurso(Cursos curso){
        return cursosRepository.save(curso);
    }

    public Cursos buscarCursoPorID(int id){
        return cursosRepository.findById(id).orElse(null);
    }

    public Cursos atualizarCurso(Cursos curso){
        return cursosRepository.save(curso);
    }

    public void removeCurso(int cursoID){
        cursosRepository.deleteById(cursoID);
    }
}
