package br.alessio.dbf.service;

import br.alessio.dbf.model.Category;
import br.alessio.dbf.model.Conta;
import br.alessio.dbf.model.ContaType;
import br.alessio.dbf.repository.ContaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContaService {
    private final ContaRepository contaRepository;

    public ContaService(ContaRepository contaRepository) {
        this.contaRepository = contaRepository;
    }

    public List<Conta> findAllList() {
        return contaRepository.findAll();
    }

    public Optional<Conta> findOne(Long id) {
        return contaRepository.findById(id);
    }

    public void delete(Long id) {
        contaRepository.deleteById(id);
    }

    public Conta save(Conta conta) {
        conta = contaRepository.save(conta);
        return conta;
    }

    public List<Conta> findByType(ContaType type) {
        return contaRepository.findContasByType(type);
    }

    public Float sumValueByType(ContaType type) {
        String typeR = type.toString();
        return contaRepository.sumValueByType(typeR);
    }

    public Object sumValueByCategory(Category category, ContaType type, Integer month) {
        String categoryR = category.toString();
        String typeR = type.toString();
        return contaRepository.sumValueByCategory(categoryR, typeR, month);
    }

    public Object sumValueByTypeAndMonths(ContaType type) {
        String typeR = type.toString();
        return contaRepository.sumValueByTypeAndMonths(typeR);
    }


}
