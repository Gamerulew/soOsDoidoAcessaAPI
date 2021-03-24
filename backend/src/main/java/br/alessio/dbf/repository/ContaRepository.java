package br.alessio.dbf.repository;

import br.alessio.dbf.model.Conta;
import br.alessio.dbf.model.ContaType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ContaRepository extends JpaRepository<Conta, Long> {

    // @Query(value = "select * from tbl_aluno a", nativeQuery = true)
    // List<Pessoa> findTodoMundoSql();

    //@Query("select a from Pessoa a")
    //List<Pessoa> findTodoMundo();
    List<Conta> findContasByType(ContaType type);

    @Query(value = "SELECT sum(value) from tbl_conta where type = :typeR", nativeQuery = true)
    Float sumValueByType(@Param("typeR") String type);

    @Query(value = "SELECT sum(value),COUNT(id) from tbl_conta where category = :categoryR and type = :typeR and MONTH(date) = :dateR", nativeQuery = true)
    Object sumValueByCategory(@Param("categoryR") String category, @Param("typeR") String typeR, @Param("dateR") Integer dateR);

    @Query(value = "SELECT sum(value) as valorTotal, MONTH(date) as mes from tbl_conta where type = :typeR GROUP BY MONTH(date) ORDER BY MONTH(date)", nativeQuery = true)
    List<Object> sumValueByTypeAndMonths(@Param("typeR") String typeR);
}