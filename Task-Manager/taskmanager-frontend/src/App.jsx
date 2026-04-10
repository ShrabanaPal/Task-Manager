import { useEffect, useState } from "react";
import "./App.css";

const API = "http://localhost:8080/tasks";

function App() {
  const [tasks, setTasks] = useState([]);
  const [title, setTitle] = useState("");
  const [filter, setFilter] = useState("all");
  const [editId, setEditId] = useState(null);
  const [editText, setEditText] = useState("");


  const fetchTasks = async () => {
    const res = await fetch(API);
    const data = await res.json();
    setTasks(data);
  };

  useEffect(() => {
    fetchTasks();
  }, []);

  const addTask = async () => {
    if (!title.trim()) return;
    await fetch(API, {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({ title, completed: false }),
    });
    setTitle("");
    fetchTasks();
  };

  const toggleTask = async (id) => {
    await fetch(`http://localhost:8080/tasks/${id}`, {
      method: "PATCH",
    });
    fetchTasks();
  };

  const deleteTask = async (id) => {
    await fetch(`http://localhost:8080/tasks/${id}`, {
      method: "DELETE",
    });
    fetchTasks();
  };

  const updateTaskTitle = async (id) => {
    try{
    const res =await fetch(`http://localhost:8080/tasks/${id}`, {
      method: "PUT",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({ title: editText,
        completed: tasks.find(t=> t.id === id).completed
      }),
    });
    if(!res.ok) {
      throw new Error("Failed to update task");
    }
    setEditId(null);
    setEditText("");
    fetchTasks();
  } catch (error) {
    alert("Failed to update task. Please try again.");
  }
  };

  const filteredTasks = tasks.filter((task) => {
    if (filter === "completed") return task.completed;
    if (filter === "pending") return !task.completed;
    return true;
  });

  return (
    <div className="app">
      <div className="card">
        <h1>Task Manager</h1>

        <div className="filters">
          <button onClick={() => setFilter("all")}>All</button>
          <button onClick={() => setFilter("completed")}>Completed</button>
          <button onClick={() => setFilter("pending")}>Pending</button>
        </div>
        <br/>
        <div className="input-section">
          <input
            value={title}
            onChange={(e) => setTitle(e.target.value)}
            placeholder="Enter a task"/>
          <button onClick={addTask}>Add</button>
        </div>

        <ul>
          {filteredTasks.map((task) => (
            <li key={task.id} className="task-item">
              <input
                type="checkbox"
                checked={task.completed}
                onChange={() => toggleTask(task.id)}/>


              {editId === task.id ? (<>
                  <input
                    value={editText}
                    onChange={(e) => setEditText(e.target.value)}/>
                  <button onClick={() => updateTaskTitle(task.id)}>
                    Save
                  </button></>) : (<>
                 
                  <span className={task.completed ? "completed" : ""}>
                    {task.title}
                  </span>

                 
                  <button
                    onClick={() => {
                      setEditId(task.id);
                      setEditText(task.title);}}>
                    Edit
                  </button></>)}

              <button
                className="delete-btn"
                onClick={() => deleteTask(task.id)}>
                Delete
              </button>
            </li>
          ))}
        </ul>
      </div>
    </div>
  );
}

export default App;