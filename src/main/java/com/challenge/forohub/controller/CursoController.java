package com.challenge.forohub.controller;

import com.challenge.forohub.domain.cursos.*;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@ResponseBody
@RequestMapping("/cursos")
@SecurityRequirement(name = "bearer-key")
public class CursoController {

    @Autowired
    private CursoRespository cursoRespository;

    @PostMapping
    public ResponseEntity<DtoListadoCurso> agregarCurso(@RequestBody @Valid DtoRegistroCurso datosRegistroCurso, UriComponentsBuilder uriComponentsBuilder) {
        System.out.println(datosRegistroCurso);
        Curso curso = cursoRespository.save(new Curso(datosRegistroCurso));
        DtoListadoCurso datosListadoCurso = new DtoListadoCurso(
                curso.getId(),
                curso.getNombre(),
                curso.getCategoria()
        );
        URI url = uriComponentsBuilder.path("cursos/{id}").buildAndExpand(curso.getId()).toUri();
        return ResponseEntity.created(url).body(datosListadoCurso);
    }

    @GetMapping
    public ResponseEntity<Page<DtoListadoCurso>> mostrarCursos(@PageableDefault(size = 10) Pageable pageable) {
        Page<DtoListadoCurso> listadoCursos = cursoRespository.findAll(pageable).map(DtoListadoCurso::new);
        return ResponseEntity.ok(listadoCursos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DtoListadoCurso> muestraCurso(@PathVariable Long id) {
        Curso curso = cursoRespository.getReferenceById(id);
        DtoListadoCurso datosListadoCurso = new DtoListadoCurso(
                curso.getId(),
                curso.getNombre(),
                curso.getCategoria()
        );
        return ResponseEntity.ok(datosListadoCurso);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DtoListadoCurso> actualizaCurso(@RequestBody @Valid DtoActualizarCurso datosActualizarCurso) {
        Curso curso = cursoRespository.getReferenceById(datosActualizarCurso.id());
        curso.actualizarCurso(datosActualizarCurso);
        return ResponseEntity.ok(new DtoListadoCurso(curso));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity borrarCurso(@PathVariable Long id) {
        cursoRespository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
