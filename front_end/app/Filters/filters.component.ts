//our root app component
import {Component, Pipe, PipeTransform} from '@angular/core';

@Pipe({name: 'capitalize'})
export class CapitalizePipe implements PipeTransform {
  transform(value: string, args: string[]): any {
    if (!value) return value;

    return value.replace(/\w\S*/g, function(txt) {
        return txt.charAt(0).toUpperCase() + txt.substr(1).toLowerCase();
    });
  }
}

@Component({
  selector: 'my-app',
  template: '<p>My name is <strong>{{ name | capitalize }}</strong>.</p>',
})

export class FiltersComponent {
  name = 'john doe';
}


