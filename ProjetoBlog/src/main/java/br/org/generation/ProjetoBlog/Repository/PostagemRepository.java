package br.org.generation.ProjetoBlog.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import br.org.generation.ProjetoBlog.model.Postagens;

@Repository
public interface PostagemRepository extends JpaRepository<Postagens,Long>{
      public List<Postagens> findAllByTituloContainingIgnoreCase (String titulo);
      
      
}
