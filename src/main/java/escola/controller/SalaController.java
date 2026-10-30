package escola.controller;

import escola.model.Sala;
import escola.service.SalaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/salas")
public class SalaController {

    private final SalaService salaService;

    public SalaController(SalaService salaService) {
        this.salaService = salaService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Sala cadastrar(@Valid @RequestBody Sala sala) {
        return salaService.cadastrar(sala);
    }

    @GetMapping
    public List<Sala> listar() {
        return salaService.listar();
    }

    @GetMapping("/{id}")
    public Sala buscar(@PathVariable Long id) {
        return salaService.buscar(id);
    }

    @PostMapping("/{id}/reservar")
    public Sala reservar(@PathVariable Long id) {
        return salaService.reservar(id);
    }

    @PostMapping("/{id}/liberar")
    public Sala liberar(@PathVariable Long id) {
        return salaService.liberar(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long id) {
        salaService.excluir(id);
    }
}
