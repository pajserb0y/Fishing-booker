import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Sort } from '@angular/material/sort';
import { Router } from '@angular/router';
import { Boat } from '../model/boat';
import { BoatReservation } from '../model/boat-reservation';
import { Customer } from '../model/customer';
import { BoatOwnerService } from '../service/boat-owner.service';
import { CustomerService } from '../service/customer.service';

@Component({
  selector: 'app-boats',
  templateUrl: './boats.component.html',
  styleUrls: ['./boats.component.css']
})
export class BoatsComponent implements OnInit {

  minDate = new Date();
  range = new FormGroup({
    start: new FormControl(),
    end: new FormControl(),
  });
  boats: Boat[] = []
  displayedColumns: string[] = ['name', 'address', 'description', 'capacity', 'owner', 'price', 'grade'];
  errorMessage : string  = '';
  selectedBoatInfo: Boat = {
    id: 0,
    name: '',
    address: '',
    description: '',
    /* grade: number ;   ovo se dobavlja iz tabele svih ocena*/
    imagePath: '',
    capacity: 0,
    freeTerms: [],
    rules: '',
    price: 0,
    additionalServices: [],
    boatOwner: {
      id: 0,
      firstName: "",
      lastName: "",
      email: "",
      username: "",
      password: "",
      address: "",
      city: "",
      country: "",
      phone: "",
      motive: ""
    },
    boatReservations: [],
    avgGrade: 0,
    type: '',
    length: 0,
    engineNumber: 0,
    horsePower: 0,
    maxSpeed: 0
  }
  boatReservation : BoatReservation = {
    id: 0,
    startDateTime: new Date,
    endDateTime: new Date,
    capacity: 0,
    startSpecialOffer: new Date,
    endSpecialOffer: null,
    services: [],
    price: 0,
    customer: {
      id: 0,
      firstName: "",
      lastName: "",
      email: "",
      username: "",
      password: "",
      address: "",
      city: "",
      country: "",
      phone: "",
      penals: 0,
      subscribedWeekendHouses: [],
      subscribedBoats: [],
      subscribedFishingLessons: [],
      category: '',
      discount: 0,
      points: 0,
      version: 0
    },
    boat: this.selectedBoatInfo,
    cancelled: false
  }
  role : string|null = localStorage.getItem('role');
  searchField: string = '';
  show: boolean = false;
  username: string|null = localStorage.getItem('username');
  currentCustomer! : Customer;

    
  constructor(private _boatOwnerService: BoatOwnerService, private router: Router, private _snackBar: MatSnackBar, public _customerService: CustomerService) { }

  ngOnInit(): void {
    if(this.role == 'ROLE_CUSTOMER')
    {
      this.getCustomer();
      this.getAllBoats();
    }    
    else if(this.role == 'ROLE_BOAT_OWNER')
        this.getAllBoatsForOwner(this.username);  
    else
      this.getAllBoats();
  }


  subscribe() {
    var exists = false;
    for (var boat of this.currentCustomer.subscribedBoats)
      if(boat.id == this.selectedBoatInfo.id) {
        exists = true;
        break;
      }
    if(!exists) {
      this._customerService.subscribeCustomerForBoat(localStorage.getItem('username') || '', this.selectedBoatInfo.id)
                .subscribe(data =>  {
                  this._snackBar.open('You are succesfully subscribed for this boat', 'Close', {duration: 5000})
                    window.location.reload();
                  },
                    error => this.errorMessage = <any>error); 
    } else 
        this._snackBar.open('You are already subscribed for this boat', 'Close', {duration: 5000});
  }

  getAllBoats() {
    this._boatOwnerService.getAllBoats()
        .subscribe(data =>  {
                    this.boats = data
                    //this.dataSource = new MatTableDataSource(this.weekendHouses);
                    },
                   error => this.errorMessage = <any>error);   
  }

  getAllBoatsForOwner(username :String|null)
  {
    this._boatOwnerService.getAllBoatsForOwner(username)
        .subscribe(data =>  this.boats = data,
                   error => this.errorMessage = <any>error); 
  } 

  getAllFreeTerms() {
       this._boatOwnerService.getAllFreeTermsForBoat(this.selectedBoatInfo)
       .subscribe(data =>  this.selectedBoatInfo.freeTerms = data,
                  error => this.errorMessage = <any>error); 
  }

  getCustomer() {
    this._customerService.getCustomerByUsername(localStorage.getItem('username') || '')
              .subscribe(data => {
                this.boatReservation.customer = data
                this.currentCustomer = data
                console.log('Dobio: ', data)},
              error => this.errorMessage = <any>error);  
  }

  showInfo(boat: Boat) {
    if(this.role == 'ROLE_BOAT_OWNER')
    {
      this._boatOwnerService.boat = boat;
      this.router.navigateByUrl("boat-profile")
    } 
    if(this.role != 'ROLE_BOAT_OWNER')
    {
      this.selectedBoatInfo = boat
      this.boatReservation.price = this.selectedBoatInfo.price * (100 - this.currentCustomer.discount)/100
      this.boatReservation.capacity = 1
      if(this.role == 'ROLE_CUSTOMER')
        this.getAllFreeTerms();
    }    
    this.show = true;
  }

  findAvailableBoatsForSelectedTerm() {
    this._boatOwnerService.findAvailableBoatsForSelectedTerm(this.range.value.start, this.range.value.end)
          .subscribe(data =>  this.boats = data,
               error => this.errorMessage = <any>error); 
    this.show = false;
  }

  reserve() {
    if (this.boatReservation.customer != null && this.boatReservation.customer.penals > 2)
        this._snackBar.open('You can not make any reservations because you are banned for the end of the month!', 'Close', {duration: 5000});
    else {
        this.boatReservation.boat = this.selectedBoatInfo
        this.boatReservation.endDateTime = this.getDateFromDatePickerRange(this.range.value.end)
        this.boatReservation.startDateTime = this.getDateFromDatePickerRange(this.range.value.start)
        this.boatReservation.endSpecialOffer = null
        this.boatReservation.price = this.selectedBoatInfo.price * (100 - this.currentCustomer.discount)/100
        for (let service of this.boatReservation.services) {
          this.boatReservation.price += service.price * (100 - this.currentCustomer.discount)/100
        }
        this._boatOwnerService.reserve(this.boatReservation)
              .subscribe(data => {
                if(data == null)
                  this._snackBar.open('Someone has reserved boat in selected term before you. Please select other term.', 'Close', {duration: 5000});
                else {
                  this.router.navigateByUrl('customer-future-boat-reservations').then(() => {
                  this._snackBar.open('Reservation successful', 'Close', {duration: 5000});
                  }); 
                }  
              }, error => this.errorMessage = <any>error);   
    }            
  }

  calculateTotalPrice(ob: any) {
    let selectedService = ob.source.value;
    console.log(selectedService);
    if (ob.source._selected)
      this.boatReservation.price += selectedService.price * (100 - this.currentCustomer.discount)/100
    else
      this.boatReservation.price -= selectedService.price * (100 - this.currentCustomer.discount)/100
  }

  getDateFromDatePickerRange(start: Date) {
    return new Date(Date.UTC(start.getFullYear(), start.getMonth(), start.getDate(), start.getHours(), start.getMinutes()));
  }

  sortData(sort: Sort) {
    const data = this.boats.slice();
    if (!sort.active || sort.direction === '') {
      this.boats = data;
      return;
    }

    this.boats = data.sort((a, b) => {
      const isAsc = sort.direction === 'asc';
      switch (sort.active) {
        case 'name':
          return compare(a.name, b.name, isAsc);
        case 'address':
          return compare(a.address, b.address, isAsc);
        case 'description':
          return compare(a.description, b.description, isAsc);
        case 'capacity':
          return compare(a.capacity, b.capacity, isAsc);
        case 'owner':
          return compare(a.boatOwner.firstName, b.boatOwner.firstName, isAsc);
        case 'price':
          return compare(a.price, b.price, isAsc);
        case 'grade':
          return compare(a.avgGrade, b.avgGrade, isAsc);
        default:
          return 0;
      }
    });
  }

  searchTable() {
    var table, tr, i;

    table = <HTMLTableElement>document.getElementById("myTable");
    tr = table.getElementsByTagName("mat-row") as HTMLCollectionOf<HTMLElement>;        

    var hideList = [];
    var grade;

    for (i = 0; i < tr.length; i++) {
      if (this.boats[i].avgGrade == null)
        grade = ''
      else
        grade =  this.boats[i].avgGrade.toString()
      let allText = this.boats[i].name.toString() + ' ' + this.boats[i].address.toString() + ' ' + this.boats[i].description.toString() + ' ' + 
                      this.boats[i].capacity.toString() + ' ' + this.boats[i].price.toString() + ' ' + this.boats[i].boatOwner.firstName.toString() + ' ' +
                      this.boats[i].boatOwner.lastName.toString() + ' ' + grade
      if (!(allText.toUpperCase().indexOf(this.searchField.toUpperCase()) > -1)) 
                hideList.push(i);
    }    
    
    for (i = 0; i < tr.length; i++)
    {
        if (hideList.includes(i))
            tr[i].style.display = "none";
        else 
            tr[i].style.display = "";
    }
    hideList.length = 0;  //clear the map
  }

}

function compare(a: number | string, b: number | string, isAsc: boolean) {
  return (a < b ? -1 : 1) * (isAsc ? 1 : -1);
}
