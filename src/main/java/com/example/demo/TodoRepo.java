package com.example.demo;

import org.apache.catalina.User;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.Key;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class TodoRepo {

    private final DynamoDbTable<Todo> todoTable;

    public TodoRepo(DynamoDbEnhancedClient enhancedClient) {
        this.todoTable = enhancedClient.table(
                "Todo",
                TableSchema.fromBean(Todo.class)
        );
    }

    public void save(Todo todo){
        todoTable.putItem(todo);
    }

    public List<Todo> getAllTodos(){
        return todoTable.scan().items().stream().collect(Collectors.toList());
    }

    public Todo getTodo(int id){
        return todoTable.getItem(
                Key.builder().partitionValue(id).build()
        );
    }

    public void updateTodo(Todo todo){
        todoTable.updateItem(todo);
    }

    public void deleteTodo(int id){
        todoTable.deleteItem(Key.builder().partitionValue(id).build());
    }
}