    package io.github.maikiller.clientes.rest;
    import io.github.maikiller.clientes.model.model.entity.Cliente;
    import io.github.maikiller.clientes.model.repository.ClienteRepository;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.http.HttpStatus;
    import org.springframework.web.bind.annotation.*;
    import org.springframework.web.server.ResponseStatusException;
    import javax.validation.Valid;
    import java.util.List;

    @RestController
    @RequestMapping("/api/clientes")
    //@CrossOrigin("*") // IP e Porta  APP Angular -> @CrossOrigin("*") todas as requisição
    public class ClienteController {
    private final ClienteRepository repository;

    @Autowired
    public ClienteController(ClienteRepository repository){
        this.repository = repository;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente save(@RequestBody @Valid Cliente cliente){
        return repository.save(cliente);
    }

    @GetMapping("{id}")
    public Cliente findId(@PathVariable Integer id){
        return repository
                .findById(id)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não Encontrado"));
    }

     @DeleteMapping("{id}")
     @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id){
         repository
                 .findById(id)
                 .map(cliente -> {
                     repository.delete(cliente);
                     return Void.TYPE;
                 })
                 .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND , "Cliente não Encontrado"));
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void edit(@PathVariable Integer id,@RequestBody @Valid Cliente clienteAtualizado){
        repository
                .findById(id)
                .map(cliente -> {
                   clienteAtualizado.setId(cliente.getId());
                   return repository.save(clienteAtualizado);
                })
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public List<Cliente> ObterTodos(){
        return repository.findAll();
    }

}
