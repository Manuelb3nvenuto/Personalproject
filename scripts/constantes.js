export const PI = Math.PI;

export  let usuario = "MAnuel";
export  let password = "QWERTY";


//funcion expresada aquella que se guarda en variable , en este caso no se logra ejecutar porque se llama antes de la expresion
const hello = () => console.log("Hola")

//default hace que se mande a llamar de manera automatica 
//expresion declarada always better
export default function saludar(){
console.log("Hola modulos")
}

export class despedida{
    constructor(){
    console.log("Adios modulos")
    }
}



/*
//Resumen Clases: Se instancian con new. No se pueden llamar sin new. Funciones Normales: No requieren new para ser llamadas.

export function despedida(){
    console.log("Adios modulos")
    }
//decarar y despues llamar , es una mejor practica
*/