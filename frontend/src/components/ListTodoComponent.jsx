import React, { useEffect } from 'react'
import { useState } from 'react'
import { deleteTodo, getAllTodos, updateTodo, completeTodo, inCompleteTodo } from '../services/TodoService';
import { useNavigate } from 'react-router-dom';

const ListTodoComponent = () => {

  // const dummyData =[
  //   {
  //     "id":1,
  //     "title":"Learn",
  //     "description":"Learn2",
  //     "completed":false
  //   },
  //   {
  //     "id":2,
  //     "title":"Learn",
  //     "description":"Learn2",
  //     "completed":false
  //   },
  //   {
  //     "id":3,
  //     "title":"Learn",
  //     "description":"Learn2",
  //     "completed":false
  //   }
  // ]

  const [todos, setTodos] = useState([]);
  useEffect(() =>{
    listTodos();
  }, []);

  function listTodos(){
    getAllTodos().then((response) =>{
      setTodos(response.data);
    }).catch(error =>{
      console.error(error);
    });
  }

  const navigator = useNavigate();

  function addNewTodo(){
    navigator('/add-todo');
  }

  function updateTodo(id){
    navigator(`/update-todo/${id}`);
  }

  function removeTodo(id){
    deleteTodo(id).then((response) => {
        listTodos();
    }).catch(error => {
        console.error(error)
    })
}

function markCompleteTodo(id){
    completeTodo(id).then((response) => {
        listTodos()
    }).catch(error => {
        console.error(error)
    })
}

function markInCompleteTodo(id){
    inCompleteTodo(id).then((response) => {
        listTodos();
    }).catch(error => {
        console.error(error)
    })
} 

  return (
    <div className='container'>
      <h2 className='text-center'>List of Todos</h2>
      <button className='btn btn-primary md-2' onClick={addNewTodo}>Add Todo</button>
      <table className='table table-bordered table-striped'>
        <thead>
          <tr>
            <th>Todo Title</th>
            <th>Todo Description</th>
            <th>Todo Completed</th>
            <th>Actions</th>
          </tr>
        </thead>
        <tbody>
          {
            todos.map(todo => 
              <tr key={todo.id}>
                <td>{todo.title}</td>
                <td>{todo.description}</td>
                <td>{todo.completed ? 'YES': 'NO'}</td>
                <td>
                  <button className='btn btn-info' onClick={() => updateTodo(todo.id)}>Update</button>
                  <button className='btn btn-danger' onClick={() => removeTodo(todo.id)} style={ { marginLeft: "10px" }} >Delete</button>
                  <button className='btn btn-success' onClick={() => markCompleteTodo(todo.id)} style={ { marginLeft: "10px" }} >Complete</button>
                  <button className='btn btn-info' onClick={() => markInCompleteTodo(todo.id)} style={ { marginLeft: "10px" }} >In Complete</button>
                </td>
              </tr>
            )
          }
          
        </tbody>
      </table>
    </div>
  )
}

export default ListTodoComponent