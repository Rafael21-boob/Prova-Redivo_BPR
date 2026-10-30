package escola.service;

public class SalaNaoEncontradaException extends RuntimeException {

    public SalaNaoEncontradaException(Long id) {
        super("Sala " + id + " não encontrada");
    }
}
