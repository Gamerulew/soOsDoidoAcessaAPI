package br.alessio.dbf.web.rest;

import br.alessio.dbf.model.Usuario;
import br.alessio.dbf.service.UsuarioService;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
@RequestMapping("/public/user")
@Api(value = "pessoas", tags = "Entidade que Manipula Pessoas Simples do Sitemas")
public class UsuarioResource {

    private final Logger log = LoggerFactory.getLogger(UsuarioResource.class);

    private final UsuarioService usuarioService;

    public UsuarioResource(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    /**
     * {@code DELETE  /pessoas/:id} : delete pelo "id" pessoa.
     *
     * @param id o id do pessoas que será delete.
     * @return o {@link ResponseEntity} com status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePessoa(@PathVariable Long id) {
        log.debug("REST request to delete Pessoa : {}", id);

        usuarioService.delete(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * {@code GET  /pessoas/:id} : get the "id" pessoa.
     *
     * @param id o id do pessoa que será buscado.
     * @return o {@link ResponseEntity} com status {@code 200 (OK)} e no body o pessoa, ou com status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> getPessoa(@PathVariable Long id) {
        log.debug("REST request to get Pessoa : {}", id);
        Optional<Usuario> pessoa = usuarioService.findOne(id);
        if (pessoa.isPresent()) {
            return ResponseEntity.ok().body(pessoa.get());
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    @GetMapping("/{email}/{password}")
    public ResponseEntity<Usuario> login(@PathVariable String email, @PathVariable String password) {
        Optional<Usuario> pessoa = usuarioService.login(email, password);
        if (pessoa.isPresent()) {
            return ResponseEntity.ok().body(pessoa.get());
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    @GetMapping("/")
    public ResponseEntity<List<Usuario>> getPessoas() {
        List<Usuario> lista = usuarioService.findAllList();
        return ResponseEntity.ok().body(lista);
    }

    /**
     * {@code PUT  /pessoas} : Atualiza um pessoa existenteUpdate.
     *
     * @param usuario o pessoa a ser atulizado.
     * @return o {@link ResponseEntity} com status {@code 200 (OK)} e no corpo o pessoa atualizado,
     * ou com status {@code 400 (Bad Request)} se o pessoa não é válido,
     * ou com status {@code 500 (Internal Server Error)} se o pessoa não pode ser atualizado.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/")
    public ResponseEntity<Usuario> updatePessoa(@Valid @RequestBody Usuario usuario) throws URISyntaxException {
        log.debug("REST request to update Pessoa : {}", usuario);
        if (usuario.getId() == null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Invalid Pessoa id null");
        }
        Usuario result = usuarioService.save(usuario);
        return ResponseEntity.ok()
                .body(result);
    }

    /**
     * {@code POST  /} : Create a new pessoa.
     *
     * @param usuario the pessoa to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new pessoa, or with status {@code 400 (Bad Request)} if the pessoa has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/")
    public ResponseEntity<Usuario> createPessoa(@Valid @RequestBody Usuario usuario) throws URISyntaxException {
        log.debug("REST request to save Pessoa : {}", usuario);
        if (usuario.getId() != null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Um novo pessoa não pode terum ID");
        }
        Usuario result = usuarioService.save(usuario);
        return ResponseEntity.created(new URI("/api/pessoas/" + result.getId()))
                .body(result);
    }

    @GetMapping("/{email}/exist")
    public ResponseEntity<Boolean> verifyExist(@PathVariable String email) {
        log.debug("REST request to get Pessoa : {}", email);
        Optional<Usuario> pessoa = usuarioService.verifyEmail(email);
        if (pessoa.isPresent()) {
            return ResponseEntity.ok(true);
        } else {
            return ResponseEntity.ok(false);
        }

    }

}
