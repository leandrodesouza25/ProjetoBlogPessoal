package br.org.generation.ProjetoBlog.Controller;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.org.generation.ProjetoBlog.Repository.PostagemRepository;
import br.org.generation.ProjetoBlog.model.Postagens;


@RestController
@RequestMapping("/postagens")
@CrossOrigin("*")
public class PostagensController  {
	

	@Autowired 
	private PostagemRepository postagemRepository;
	
	@GetMapping
	public ResponseEntity<List<Postagens>> getAll (){
		return ResponseEntity.ok(postagemRepository.findAll()); // OK = 200
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Postagens> getById(@PathVariable long id) {
		return postagemRepository.findById(id)
			.map(resp -> ResponseEntity.ok(resp))
			.orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("/titulo/{titulo}")
	public ResponseEntity<List<Postagens>> getByTitulo(@PathVariable String titulo){
		return ResponseEntity.ok(postagemRepository.findAllByTituloContainingIgnoreCase(titulo));
	}

	@PostMapping
	public ResponseEntity<Postagens> postPostagem (@RequestBody Postagens postagens){
		return ResponseEntity.status(HttpStatus.CREATED).body(postagemRepository.save(postagens));
	}
	
	@PutMapping
	public ResponseEntity<Postagens> putPostagem (@RequestBody Postagens postagens){
		
		Optional<Postagens> postagemUpdate = postagemRepository.findById(postagens.getId());
		
		if (postagemUpdate.isPresent()) {
			return ResponseEntity.status(HttpStatus.OK).body(postagemRepository.save(postagens));
		}else{
			throw new ResponseStatusException(
		          	HttpStatus.NOT_FOUND, "A postagem não foi encontrada...", null);
		}
		
	}
			
	@DeleteMapping("/{id}")
	public void deletePostagem(@PathVariable long id) {
		
		Optional<Postagens> postagem = postagemRepository.findById(id);
		
		if (postagem.isPresent()) {
			postagemRepository.deleteById(id);
		}else{
			throw new ResponseStatusException(
		          	HttpStatus.NOT_FOUND, "A postagem não foi encontrada...", null);
		}
	}	
	
}