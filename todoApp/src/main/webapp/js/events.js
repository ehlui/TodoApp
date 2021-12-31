import { getTasks, deleteTask, createTask, updateTask } from "./fetch.js"
// Globals
const TASK_LIST_CONTAINER_ID = "tasksList"
const BUTTON_SHOW_TASKS_ID = "showTasks"
const CONTAINER_SELECTOR = "div.container"

function sleep(ms) {
    return new Promise(resolve => setTimeout(resolve, ms));
}

async function RetrieveAndBuildTasksList() {
    deleteListIfExists()
    const tasksList = await getTasks()
    buildUlList(tasksList);
}

const btnShowTasks = document.getElementById(BUTTON_SHOW_TASKS_ID);
btnShowTasks.addEventListener("click", async e => {
    RetrieveAndBuildTasksList()
});

function deleteListIfExists() {
    let listContainer = document.getElementById(TASK_LIST_CONTAINER_ID)
    if (listContainer != null)
        listContainer.remove()
}

function AddEventListenerToTaskList() {
    const taskContainer = document.querySelector("#" + TASK_LIST_CONTAINER_ID)
    taskContainer.addEventListener('click', e => {
        const taskOptionClass = JSON.stringify(e.target.className)
        const currentTaskIdClicked = e.target.parentElement.id
        const isOneOptionClicked = taskOptionClass.includes("remove") || taskOptionClass.includes("edit")
        if (isOneOptionClicked) {
            handleTaskOptions(taskOptionClass, currentTaskIdClicked, e)
        }
    })
}


function buildEditForm(taskId) {
    const TASK_EDIT_FORM_SELECTOR = '[id^=item-] div'
    let tasksToEditActived = document.querySelectorAll(TASK_EDIT_FORM_SELECTOR)
    if (tasksToEditActived.length > 0) return

    let itemContainer = document.getElementById(taskId)
    let divEditTask = document.createElement('div');
    let labelName = document.createElement('label');
    let labelDesc = document.createElement('label');
    let inputName = document.createElement('input');
    let inputDesc = document.createElement('input');
    let spanEdit = document.createElement('span');
    let spanRemoveEditForm = document.createElement('span');


    labelName.setAttribute("for", "newTaskName")
    inputName.setAttribute("type", "text")
    inputName.setAttribute("id", "editTaskName")
    inputName.setAttribute("class", "editTaskName")

    labelDesc.setAttribute("for", "newTaskDesc")
    inputDesc.setAttribute("type", "text")
    inputDesc.setAttribute("id", "editTaskDesc")
    inputDesc.setAttribute("class", "editTaskDesc")

    spanEdit.innerHTML = `&#10133;`
    spanRemoveEditForm.innerHTML = `&#10060;`

    itemContainer.appendChild(divEditTask)
    divEditTask.appendChild(labelName)
    divEditTask.appendChild(inputName)
    divEditTask.appendChild(labelDesc)
    divEditTask.appendChild(inputDesc)
    divEditTask.appendChild(spanEdit)
    divEditTask.appendChild(spanRemoveEditForm)


    divEditTask.addEventListener('click', e => {
        console.log("clicked " + e.target)
    })

    spanRemoveEditForm.addEventListener('click', e => {
        e.preventDefault();
        let tasksToEditActived = document.querySelector(TASK_EDIT_FORM_SELECTOR)
        tasksToEditActived.remove()
    })
    spanEdit.addEventListener('click', e => {
        e.preventDefault();
        const idExtracted = taskId.replace(/[^0-9]/g, '');
        let editTaskNameSelector = document.querySelector("#" + taskId + " input.editTaskName")
        let editTaskDescSelector = document.querySelector("#" + taskId + " input.editTaskDesc")

        let newTaskName = editTaskNameSelector.value
        let newTaskDesc = editTaskDescSelector.value

        updateTask(idExtracted, newTaskName, newTaskDesc)
        let tasksToEditActived = document.querySelector(TASK_EDIT_FORM_SELECTOR)
        tasksToEditActived.remove()
        sleep(2000).then(() => { RetrieveAndBuildTasksList(); });
    })

}



function handleTaskOptions(taskOptionClass, currentTaskIdClicked, event) {
    if (taskOptionClass.includes("remove")) {
        const idExtracted = currentTaskIdClicked.replace(/[^0-9]/g, '');
        deleteTask(idExtracted)
        console.log(`Task with id=${idExtracted} has been removed...`)
        sleep(2000).then(() => { RetrieveAndBuildTasksList(); });
    }
    if (taskOptionClass.includes("edit")) {
        const idExtracted = currentTaskIdClicked.replace(/[^0-9]/g, '');
        buildEditForm(currentTaskIdClicked)
        console.log(`Task with id=${idExtracted} has been edited...`)
    }
}

function buildUlList(listOfObjects) {
    if (!Array.isArray(listOfObjects))
        return;

    let container = document.querySelector(CONTAINER_SELECTOR);
    let ul = document.createElement('ul');
    ul.setAttribute('id', TASK_LIST_CONTAINER_ID);

    container.appendChild(ul);
    listOfObjects.forEach(renderProductList);

    function renderProductList(element, index, arr) {
        let li = document.createElement('li');
        let imgRemove = document.createElement('span');
        let imgUpdate = document.createElement('span');

        imgRemove.setAttribute('class', 'remove')
        imgUpdate.setAttribute('class', 'edit')
        li.setAttribute('id', `item-${element.id}`);


        imgRemove.innerHTML = `&#10060;`
        imgUpdate.innerHTML = `&#9999;`
        li.innerHTML = li.innerHTML + `${element.name} :: ${element.description}`;

        ul.appendChild(li);
        li.appendChild(imgRemove)
        li.appendChild(imgUpdate)
    }
    AddEventListenerToTaskList();
}


// Add new task
const addClickable = document.getElementById("addTask")
addClickable.addEventListener('click', e => {
    const addFormContainer = document.getElementById("formAddTask")
    const displayValueToggle = addFormContainer.style.display === 'none' ? '' : 'none';
    addFormContainer.style.display = displayValueToggle
})

const sumbitNewTask = document.getElementById("sumbitNewTask")
sumbitNewTask.addEventListener('click', e => {
    const addFormContainer = document.getElementById("formAddTask")
    let taskNameInput = document.getElementById('taskName')
    let taskDescInput = document.getElementById('taskDesc')

    let taskName = taskNameInput.value
    let taskDesc = taskDescInput.value
    createTask(taskName, taskDesc)

    console.log("New task added=[name : " + taskName + ", description : " + taskDesc + "]")

    taskNameInput.value = ""
    taskDescInput.value = ""

    sleep(2000).then(() => { RetrieveAndBuildTasksList(); });
    addFormContainer.style.display = 'none'
})


RetrieveAndBuildTasksList();