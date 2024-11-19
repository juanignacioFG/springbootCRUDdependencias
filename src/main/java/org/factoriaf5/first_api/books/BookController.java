package org.factoriaf5.first_api.books;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/books")
public class BookController {

    private final InMemoryBookRepository bookRepository;

    public BookController() {
        this.bookRepository = new InMemoryBookRepository();
    }

    @GetMapping
    public List<Book> getAllBooks() {
        return this.bookRepository.findAll();
    }

    @GetMapping("/{isbn}")
    public ResponseEntity<Book> getBookByIsbn(@PathVariable String isbn) {
        Optional<Book> optionalBook = bookRepository.findByIsbn(isbn);

        if (optionalBook.isPresent()) {
            return new ResponseEntity<>(optionalBook.get(), HttpStatus.OK); // 200 OK
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND); // 404 Not Found
    }

    @PostMapping
    public Book createBook(@RequestBody Book book) {
        Optional<Book> optionalBook = bookRepository.findByIsbn(book.getIsbn());
        if (optionalBook.isPresent()) {
           throw new ResponseStatusException( HttpStatus.BAD_REQUEST);  //NO EXISTE 400
        }
        // comprobar que no existe el isbn si existe return (bad_request)

        bookRepository.save(book);
        return book; // OK (200) or Created (201)
    }

    @DeleteMapping("/{isbn}")
    public void deleteBookByIsbn(@PathVariable String isbn) {
        // si no existe retornar un 404
        // si se ha borrado retornar ok
        bookRepository.deleteByIsbn(isbn);
    }

    // Update -> modificar un libro por su isbn (PUT)

    @PutMapping("/{isbn}")
    public ResponseEntity <String> updateBook (@PathVariable String isbn , @RequestBody Book book) {

        //buscar libro existente por su isbn

        Optional<Book> optionalBook = bookRepository.findByIsbn(isbn);

        if (optionalBook.isPresent()) {
            Book existingBook = optionalBook.get();

            //actualizar los campos del libro existente con los valores del libro actualizado
            existingBook.setTittle(book.getTittle());
            existingBook.setAuthor(book.getAuthor());
            existingBook.setIsbn(book.getIsbn());
            //guardar los cambios en el repositorio
            bookRepository.update(existingBook);
            // respuesta con exito
            return new ResponseEntity<>("Libro actualizado correctamente", HttpStatus.OK);
        }
            //SI NO SE ENCUENTRA EL LIBRO , RETORNAR UN ERROR 404
            return new ResponseEntity<>("libro no encontrado", HttpStatus.NOT_FOUND);



    }


}