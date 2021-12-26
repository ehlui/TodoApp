import { getTasks } from "./fetch.js"
// Globals
const TASK_LIST_CONTAINER_ID = "tasksList"
const BUTTON_SHOW_TASKS_ID = "showTasks"
const CONTAINER_SELECTOR = "div.container"


async function RetrieveAndBuildTasksList() {
    deleteListIfExists()
    const tasksList = await getTasks()
    buildUlList(tasksList);
}


const btnShowTasks = document.getElementById(BUTTON_SHOW_TASKS_ID);
btnShowTasks.addEventListener("click", async e => {
    RetrieveAndBuildTasksList();
});


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

    // TODO: Remove the belowed log when it is finished
    console.log(listOfObjects)
}

function deleteListIfExists() {
    let listContainer = document.getElementById(TASK_LIST_CONTAINER_ID)
    if (listContainer != null)
        listContainer.remove()
}


RetrieveAndBuildTasksList()