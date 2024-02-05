const toDo = [];



function addTodo(){
   const inputElement = document.querySelector('.js-name-input');
   const name = inputElement.value;
   toDo.push(name)
   console.log(toDo)


   inputElement.value = '';

   
};