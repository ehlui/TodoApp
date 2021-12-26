export const TASK_SERVICE_URL = "http://localhost:8118/todoApp/TaskService"

export async function deleteTask(id) {
    await fetch(`${TASK_SERVICE_URL}/${id}`, {
        method: "DELETE"
    });
}

export async function updateTask(taskId, taskName, taskDesc) {
    const taskToUpdate = { id: taskId, name: taskName, description: taskDesc }
    await fetch(TASK_SERVICE_URL, {
        method: "PUT",
        body: JSON.stringify(taskToUpdate)
    });
}

export async function createTask(taskName, taskDesc) {
    const newTask = { id: taskId, name: taskName, description: taskDesc }
    await fetch(TASK_SERVICE_URL, {
        method: "POST",
        body: JSON.stringify(newTask)
    });
}

export async function getTasks() {
    // TODO: Check why from other "localhost" we have no access to the servlets
    return (await fetch(TASK_SERVICE_URL)).json();
}