package br.alessio.dbf.web.rest;

import br.alessio.dbf.model.Conta;
import br.alessio.dbf.model.ContaType;
import br.alessio.dbf.service.ContaService;
import io.swagger.annotations.Api;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/public/conta")
@Api(value = "contas", tags = "Contas")
public class ContaResource {
    private final ContaService contaService;

    public ContaResource(ContaService contaService) {
        this.contaService = contaService;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteConta(@PathVariable Long id) {
        contaService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Conta> getConta(@PathVariable Long id) {
        Optional<Conta> conta = contaService.findOne(id);
        if (conta.isPresent()) {
            return ResponseEntity.ok().body(conta.get());
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    @GetMapping("/")
    public ResponseEntity<List<Conta>> getContas() {
        List<Conta> lista = contaService.findAllList();
        return ResponseEntity.ok().body(lista);
    }

    @GetMapping("/type/{type}")
    public ResponseEntity<List<Conta>> getByType(@PathVariable ContaType type) {
        List<Conta> contas = contaService.findByType(type);
        return ResponseEntity.ok().body(contas);
    }

    @GetMapping("/type/{type}/sum/value/")
    public ResponseEntity<Float> sumValueByType(@PathVariable ContaType type) {
        Float total = contaService.sumValueByType(type);
        System.out.println(total);
        if (total != null) {
            return ResponseEntity.ok().body(total);
        } else {
            return ResponseEntity.ok().body((float) 0);
        }
    }


    @PutMapping("/")
    public ResponseEntity<Conta> updateConta(@Valid @RequestBody Conta conta) throws URISyntaxException {
        if (conta.getId() == null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Id nulo para essa conta");
        }
        Conta result = contaService.save(conta);
        return ResponseEntity.ok()
                .body(result);
    }

    @PostMapping("/")
    public ResponseEntity<Conta> createConta(@Valid @RequestBody Conta conta) throws URISyntaxException {
        if (conta.getId() != null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Uma nova conta nn pode ter id pre definido");
        }
        Conta result = contaService.save(conta);
        return ResponseEntity.created(new URI("/api/pessoas/" + result.getId()))
                .body(result);
    }
}
