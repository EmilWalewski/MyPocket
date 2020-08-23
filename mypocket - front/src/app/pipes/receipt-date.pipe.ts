import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'receiptDate'
})
export class ReceiptDatePipe implements PipeTransform {

  transform(value: any, ...args: unknown[]): string {

    const date = (value instanceof Date ? value : new Date(value));


    if (args[0] === 'time') {
      return date.getHours() + ':' + date.getMinutes();
    }

    if (args[0] === 'fullDate') {

      return date.getFullYear() + '-'
        + (date.getMonth() < 10 ? ('0' + date.getMonth()) : date.getMonth().toString()) + '-'
        + (date.getDate() < 10 ? ('0' + date.getDate()) : date.getDate());
    }

    if (args[0] === 'year') { return date.getFullYear().toString(); }

    return date.getMonth() < 10 ? ('0' + date.getMonth()) : date.getMonth().toString();
  }

}
