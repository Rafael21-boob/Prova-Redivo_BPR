package escola.service;

import escola.model.Sala;
import escola.repository.SalaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SalaService {

    private final SalaRepository salaRepository;

    public SalaService(SalaRepository salaRepository) {
        this.salaRepository = salaRepository;
    }

    public Sala cadastrar(Sala sala) {
        if (salaRepository.existsByCodigo(sala.getCodigo())) {
            throw new RegraDeNegocioException("Já existe uma sala com o código " + sala.getCodigo());
        }
        // Regra: toda sala cadastrada começa livre, mesmo que o JSON mande "reservada": true.
        sala.setReservada(false);
        // O id é gerado pelo banco; ignorar um id vindo no JSON evita sobrescrever outra sala.
        sala.setId(null);
        return salaRepository.save(sala);
    }

    public List<Sala> listar() {
        return salaRepository.findAll();
    }

    public Sala buscar(Long id) {
        return salaRepository.findById(id)
                .orElseThrow(() -> new SalaNaoEncontradaException(id));
    }

    public Sala reservar(Long id) {
        Sala sala = buscar(id);
        if (sala.isReservada()) {
            throw new RegraDeNegocioException("A sala " + id + " já está reservada");
        }
        sala.setReservada(true);
        return salaRepository.save(sala);
    }

    public Sala liberar(Long id) {
        Sala sala = buscar(id);
        if (!sala.isReservada()) {
            throw new RegraDeNegocioException("A sala " + id + " não está reservada");
        }
        sala.setReservada(false);
        return salaRepository.save(sala);
    }

    public void excluir(Long id) {
        Sala sala = buscar(id);
        if (sala.isReservada()) {
            throw new RegraDeNegocioException("A sala " + id + " está reservada e não pode ser excluída");
        }
        salaRepository.delete(sala);
    }
}
