package com.example.demo;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
public class TodoService {
    private final TodoRepo repoDb;

    public TodoService(TodoRepo repoDb) {
        this.repoDb = repoDb;
    }

    @GetMapping(value = "/todos")
    public List<Todo> getAllTodos(){
        return repoDb.getAllTodos();
    }

    @GetMapping(value = "/todo/{id}")
    public Todo getTodoById(@PathVariable("id") int id){
        return repoDb.getTodo(id);
    }

    @PostMapping(value = "/todos")
    public void addTodo(@RequestBody Todo todo){
        System.out.println(todo);
        repoDb.save(todo);
    }

    @PatchMapping(value = "/todo/{id}")
    public void updateTodoById(@PathVariable("id") String id, @RequestBody Todo todo){
        repoDb.updateTodo(todo);
    }

    @DeleteMapping(value ="/todo/{id}")
    public void deleteTodo(@PathVariable("id") int id){
        repoDb.deleteTodo(id);
    }
}
