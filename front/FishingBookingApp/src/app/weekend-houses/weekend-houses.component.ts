import { Component, OnInit } from '@angular/core';
import { WeekendHouse } from '../model/weekend-house';

@Component({
  selector: 'app-weekend-houses',
  templateUrl: './weekend-houses.component.html',
  styleUrls: ['./weekend-houses.component.css']
})
export class WeekendHousesComponent implements OnInit {

  weekendHouses: WeekendHouse[] = []
  displayedColumns: string[] = ['name', 'address', 'details', 'grade'];

  constructor() { }

  ngOnInit(): void {
  }

}
