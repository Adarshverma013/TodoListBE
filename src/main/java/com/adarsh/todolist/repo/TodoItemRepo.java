package com.adarsh.todolist.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.adarsh.todolist.model.TodoItem;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface TodoItemRepo extends JpaRepository<TodoItem,Integer>{

    @Query(value = "select * from todo_item t where t.user_id = ?1 and t.todo_id = ?2",nativeQuery = true)
    TodoItem findByUserIdAndTodoId(int user_id,int todo_id);

    @Query(value = "select * from todo_item t where t.user_id = ?1",nativeQuery = true)
    List<TodoItem> findByUserId(int user_id);

    @Transactional
    @Modifying
    @Query(value = "delete from todo_item  where user_id = ?1 and todo_id = ?2",nativeQuery = true)
    void deleteByUserIdAndTodoId(int user_id,int todo_id);
}
