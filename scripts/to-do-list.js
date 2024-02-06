const toDo = ['make dinner', 'wash dishes'];

renderTodoList();

function renderTodoList (){

   let toDoHTML = '';

   for (let i = 0; i < toDo.length; i++){
      const makeIt = toDo[i];
      const html = `<p>${makeIt}</p>`;
      toDoHTML += html;
   }
   console.log(toDoHTML)

   document.querySelector('.js-toDo-list').innerHTML = toDoHTML;
}
function addTodo(){
   const inputElement = document.querySelector('.js-name-input');
   const name = inputElement.value;
   toDo.push(name)
   console.log(toDo)


   inputElement.value = '';

   renderTodoList();
};