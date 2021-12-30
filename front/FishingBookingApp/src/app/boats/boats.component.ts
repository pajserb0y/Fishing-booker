import { Component, OnInit } from '@angular/core';
import { Boat } from '../model/boat';

@Component({
  selector: 'app-boats',
  templateUrl: './boats.component.html',
  styleUrls: ['./boats.component.css']
})
export class BoatsComponent implements OnInit {

  boats: Boat[] = []
  displayedColumns: string[] = ['name', 'address', 'details', 'freeTerms', 'priceList', 'additionalServes', 'grade'];

  constructor() { }

  ngOnInit(): void {
  }

}
