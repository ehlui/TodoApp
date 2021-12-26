const TASK_SERVICE_URL = "http://localhost:8118/todoApp/TaskService"

async function deleteTask(id) {
    await fetch(`${TASK_SERVICE_URL}/${id}`, {
        method: "DELETE"
    });
}

async function updateTask(taskId, taskName, taskDesc) {
    taskToUpdate = { id: taskId, name: taskName, description: taskDesc }
    await fetch(TASK_SERVICE_URL, {
        method: "PUT",
        body: JSON.stringify(taskToUpdate)
    });
}

async function createTask(taskName, taskDesc) {
    newTask = { id: taskId, name: taskName, description: taskDesc }
    await fetch(TASK_SERVICE_URL, {
        method: "POST",
        body: JSON.stringify(newTask)
    });
}