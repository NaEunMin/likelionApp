package com.example.likelionapp.controller;

import com.example.likelionapp.entity.Todo;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/todos")

public class TodoController {

    private long todoListId;
    private List<Todo> todos;

    public TodoController() {
        todos = new ArrayList<>();
    }

    @GetMapping("/add")
    public Todo add(@RequestParam String content) {
        Todo todo = Todo.builder()
                .id(++todoListId)
                .content(content)
                .build();
        todos.add(todo);
        return todo;
    }

    @GetMapping("")
    public List<Todo> getTodos() {
        return todos;
    }

    @GetMapping("/{id}")
    public Todo getTodo(@PathVariable long id) {

        return todos
                .stream().filter(
                        todo -> todo.getId() == id
                )
                .findFirst()
                .orElse(null);

    }

    @GetMapping("/delete/{id}")
    public Boolean delete(@PathVariable long id) {
        Boolean deleted = todos.removeIf((todo -> todo.getId() == id));

        return deleted;
    }

    @GetMapping("/update/{id}")
    public Boolean update(@PathVariable long id, String content) {
        Todo todo = todos
                .stream().filter(
                        _todo -> _todo.getId() == id
                )
                .findFirst()
                .orElse(null);
        if (todo == null) return false;
        todo.setContent(content);
        return true;
    }
}
