// Globals
const TASK_SERVICE_LOCAL_URL = "http://localhost:8118/todoApp/TaskService"
const TASK_LIST_CONTAINER_ID = "tasksList"
const BUTTON_SHOW_TASKS_ID = "showTasks"
const CONTAINER_SELECTOR = "div.container"


const btnShowTasks = document.getElementById(BUTTON_SHOW_TASKS_ID);
btnShowTasks.addEventListener("click", async e => {
    deleteListIfExists()
    const tasksList = await getTasks()
    buildUlList(tasksList);
});



function buildUlList(listOfObjects) {
    if (!Array.isArray(listOfObjects))
        return;

    let container = document.querySelector(CONTAINER_SELECTOR);
    var ul = document.createElement('ul');
    ul.setAttribute('id', TASK_LIST_CONTAINER_ID);

    container.appendChild(ul);
    listOfObjects.forEach(renderProductList);

    function renderProductList(element, index, arr) {
        var li = document.createElement('li');
        li.setAttribute('id', `item-${element.id}`);

        ul.appendChild(li);

        li.innerHTML = li.innerHTML + `${element.name} :: ${element.description}`;
    }

    // TODO: Remove the belowed log when it is finished
    console.log(listOfObjects)
}

function deleteListIfExists() {
    let listContainer = document.getElementById(TASK_LIST_CONTAINER_ID)
    if (listContainer != null)
        listContainer.remove()
}


async function getTasks() {
    // TODO: Check why from other "localhost" we have no access to the servlet
    const options = {
        mode: 'cors',
        headers: {
            'Access-Control-Allow-Origin': 'origin-list'
        }
    }
    return (await fetch(TASK_SERVICE_LOCAL_URL, options)).json();
}