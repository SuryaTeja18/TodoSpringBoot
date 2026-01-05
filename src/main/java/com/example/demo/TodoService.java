package com.example.demo;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TodoService {
    TodoRepo repoDb = new  TodoRepo();

    @GetMapping(value = "/todos")
    public List<Todo> getAllTodos(){
        return repoDb.getAllTodos();
    }

    @GetMapping(value = "/todo/{id}")
    public Todo getTodoById(@PathVariable("id") int id){
        return repoDb.getTodo(id);
    }

    @PutMapping(value = "/todos/")
    public void addTodo(@RequestBody Todo todo){
        repoDb.save(todo.getId(), todo.getDescription(), todo.getStatus(), todo.getTitle());
    }

    @PatchMapping(value = "/todo/{id}")
    public void updateTodoById(@PathVariable("id") String id, @RequestBody Todo todo){
        repoDb.updateTodo(todo.getId(), todo.getDescription(), todo.getStatus(), todo.getTitle());
    }

    @DeleteMapping(value ="/todo/{id}")
    public void deleteTodo(@PathVariable("id") int id){
        repoDb.deleteTodo(id);
    }
}
