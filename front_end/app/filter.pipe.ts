import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
    name: 'filter'
})

export class FilterPipe implements PipeTransform {
    transform(todos: any, term: any): any {
        //return null;
        //Check if search term is undefined
        if(term == undefined) return todos;
        //return updated todos array
        return todos.filter(function(todo){
            return todo.toLowerCase().includes(term.toLowerCase());
        })
    }
    
}