const toDo = [{
   name: 'make dinner', 
   dueDate: '2022-12-22'
   },{
      name: "wash the dishes",
      dueDate: '2022-12-22'
   }
];

renderTodoList();

function renderTodoList (){

   let toDoHTML = '';

   for (let i = 0; i < toDo.length; i++){
      const makeItObject = toDo[i];
      //const name = makeItObject.name;
      //const dueDate = makeItObject.dueDate;

      const {name , dueDate} = makeItObject;
      
      const html = 
      `<div>${name}</div>
      <div>${dueDate}</div>
      <button onclick = "
      toDo.splice(${i}, 1);
      renderTodoList();
      " class="delete-todo-button" >Delete</button>
      `;
      toDoHTML += html;
   }

   document.querySelector('.js-toDo-list').innerHTML = toDoHTML;
}
function addTodo(){
   const inputElement = document.querySelector('.js-name-input');
   const name = inputElement.value;
   const dataInputElelment = document.querySelector('.js-due-date-input');
   const dueDate = dataInputElelment.value;

   toDo.push({
      //name: name ,
      //dueDate: dueDate
      name,
      dueDate
   });


   inputElement.value = '';

   renderTodoList();
};