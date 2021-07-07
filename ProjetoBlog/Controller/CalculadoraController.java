package br.org.generation.ProjetoBlog.Controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/calculadora")
public class CalculadoraController {

	 @GetMapping("/soma/{n1}/{n2}")
	 public int GetByCalcularSoma(@PathVariable int n1 , @PathVariable int n2) {
		 return n1 + n2;
		 
	 }
	

	 @GetMapping("/subtracao/{n1}/{n2}")
	 public int GetByCalcularSubtracao(@PathVariable int n1 , @PathVariable int n2) {
		 return n1 - n2;
		 
	 }
	 
	 @GetMapping("/multiplicacao/{n1}/{n2}")
	 public int GetByCalcularMultiplicacao(@PathVariable int n1 , @PathVariable int n2) {
		 return n1 * n2;
		 
		 
	 }
	 @GetMapping("/divisao/{n1}/{n2}")
	 public int GetByCalcularDivisao(@PathVariable int n1 , @PathVariable int n2) {
		  return n1 / n2;
		 
		 
	 }
}
