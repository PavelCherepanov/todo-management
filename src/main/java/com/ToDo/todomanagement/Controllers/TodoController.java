package com.ToDo.todomanagement.Controllers;

import com.ToDo.todomanagement.Dto.TodoDto;
import com.ToDo.todomanagement.Services.TodoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@Controller
@RequestMapping(path = "/api/v1/todos")
@AllArgsConstructor
public class TodoController {
    private TodoService todoService;

//    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<TodoDto> addTodo(@RequestBody TodoDto todoDto){
        TodoDto savedTodoDto = todoService.addTodo(todoDto);
        return new ResponseEntity<>(savedTodoDto, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<TodoDto> getTodo(@PathVariable Long id){
        TodoDto todoDto = todoService.getTodo(id);
        return new ResponseEntity<>(todoDto, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<TodoDto>> getAllTodo(){
        List<TodoDto> todoDtos = todoService.getAllTodo();
        return new ResponseEntity<>(todoDtos, HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<TodoDto> updateTodo(@RequestBody TodoDto todoDto,
                                              @PathVariable Long id){

        TodoDto updateTodo = todoService.updateTodo(todoDto, id);
        return new ResponseEntity<>(updateTodo, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<TodoDto> deleteTodo(@PathVariable Long id){
        TodoDto todoDto = todoService.getTodo(id);
        todoService.deleteTodo(id);
        return new ResponseEntity<>(todoDto, HttpStatus.OK);
    }

    @PatchMapping("{id}/complete")
    public ResponseEntity<TodoDto> completeTodo(@PathVariable Long id){
        TodoDto todoDto = todoService.completeTodo(id);
        return new ResponseEntity<>(todoDto, HttpStatus.OK);
    }

    @PatchMapping("{id}/in-complete")
    public ResponseEntity<TodoDto> inCompleteTodo(@PathVariable Long id){
        TodoDto todoDto = todoService.inCompleteTodo(id);
        return new ResponseEntity<>(todoDto, HttpStatus.OK);
    }
}
