package br.com.guilherme.APIcursoProgramacao.controller;

import br.com.guilherme.APIcursoProgramacao.model.Cursos;
import br.com.guilherme.APIcursoProgramacao.service.CursosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cursos")
public class CursosController {
    CursosService cursosService;

    @Autowired
    public CursosController(CursosService cursosService) {

        this.cursosService = cursosService;
    }

    @GetMapping
    public List<Cursos> listarCursos(){
        return cursosService.listarCursos();
    }

    @PostMapping
    public ResponseEntity<Cursos> criarCurso(@RequestBody Cursos curso){
        Cursos cursoCriado = cursosService.criarCurso(curso);
        return ResponseEntity.ok(cursoCriado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cursos> atualizarCurso(@PathVariable int id,
           @RequestBody Cursos cursoAtualizado){
        Cursos cursoExiste = cursosService.buscarCursoPorID(id);

        if(cursoExiste == null){
            return ResponseEntity.notFound().build();
        }

        cursoExiste.setName(cursoAtualizado.getName());
        cursoExiste.setActive(cursoAtualizado.isActive());
        cursoExiste.setCategory(cursoAtualizado.getCategory());
        cursoExiste.setUpdatedAt(cursoAtualizado.getUpdatedAt());

        Cursos cursoAtt = cursosService.atualizarCurso(cursoExiste);
        return ResponseEntity.ok(cursoAtt);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Cursos> removeCurso(@PathVariable int id){
        Cursos cursoExiste = cursosService.buscarCursoPorID(id);

        if(cursoExiste == null){
            return ResponseEntity.notFound().build();
        }

        cursosService.removeCurso(cursoExiste.getId());
        return ResponseEntity.ok(cursoExiste);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<String> attPorPatch(@PathVariable int id, @RequestBody Cursos curso){
        Cursos cursoExiste = cursosService.buscarCursoPorID(id);

        if (cursoExiste == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Curso com id " + id + " nao foi encontrado");
        }

        if (curso.isActive()){
            cursoExiste.setActive(true);
            cursoExiste.setCreateAt(cursosService.buscarCursoPorID(id).getCreateAt());
            cursoExiste.setUpdatedAt(cursosService.buscarCursoPorID(id).getUpdatedAt());
            cursoExiste.setName(cursosService.buscarCursoPorID(id).getName());
            cursoExiste.setCategory(cursosService.buscarCursoPorID(id).getCategory());
        }else{
            cursoExiste.setActive(false);
            cursoExiste.setCreateAt(cursosService.buscarCursoPorID(id).getCreateAt());
            cursoExiste.setUpdatedAt(cursosService.buscarCursoPorID(id).getUpdatedAt());
            cursoExiste.setName(cursosService.buscarCursoPorID(id).getName());
            cursoExiste.setCategory(cursosService.buscarCursoPorID(id).getCategory());
        }
        cursosService.atualizarCurso(cursoExiste);
        return ResponseEntity.ok("Curso atualizado!");
    }
}
