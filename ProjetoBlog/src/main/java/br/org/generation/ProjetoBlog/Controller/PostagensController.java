package br.org.generation.ProjetoBlog.Controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import br.org.generation.ProjetoBlog.Repository.PostagemRepository;
import br.org.generation.ProjetoBlog.model.Postagens;


@RestController
@RequestMapping("/postagens")
@CrossOrigin("*")
public class PostagensController  {
	
	      @Autowired
          private PostagemRepository repository;
	      
	      public ResponseEntity<List<Postagens>> GetAll(){
	    	  
	    	  return ResponseEntity.ok(repository.findAll());
	      }
	    	  
	      }

