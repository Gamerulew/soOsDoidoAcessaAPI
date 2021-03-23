package br.alessio.dbf.repository;

import br.alessio.dbf.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    // @Query(value = "select * from tbl_aluno a", nativeQuery = true)
    // List<Pessoa> findTodoMundoSql();

    //@Query("select a from Pessoa a")
    //List<Pessoa> findTodoMundo();
    Optional<Usuario> findByEmail(String email);

    Optional<Usuario> findUsuarioByEmailAndPassword(String email, String password);
}
