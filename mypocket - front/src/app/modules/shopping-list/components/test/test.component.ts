import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-test',
  templateUrl: './test.component.html',
  styleUrls: ['./test.component.scss']
})
export class TestComponent implements OnInit {

  arr = ['d', 'a', 'b', 'f'];

  constructor() { }

  ngOnInit(): void {
  }

  getDate(event){
    console.log(event.target.value);
  }

}
