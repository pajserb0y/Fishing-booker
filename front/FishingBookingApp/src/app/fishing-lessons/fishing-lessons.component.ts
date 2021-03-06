import { Component, OnInit } from '@angular/core';
import { FishingLesson } from '../model/fishing-lesson';
import { FormControl, FormGroup } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Sort } from '@angular/material/sort';
import { Router } from '@angular/router';;
import { CustomerService } from '../service/customer.service';
import { FishingLessonReservation } from '../model/fishing-lesson-reservation';
import { InstructorService } from '../service/instructor.service';
import { Customer } from '../model/customer';

@Component({
  selector: 'app-fishing-lessons',
  templateUrl: './fishing-lessons.component.html',
  styleUrls: ['./fishing-lessons.component.css']
})
export class FishingLessonsComponent implements OnInit {

  minDate = new Date();
  range = new FormGroup({
    start: new FormControl(),
    end: new FormControl(),
  });
  fishingLessons: FishingLesson[] = []
  displayedColumns: string[] = ['name', 'address', 'description', 'capacity', 'owner', 'price', 'grade'];
  terms: string[] = ['09:00', '11:00', '13:00', '15:00', '17:00', '19:00'];
  selectedTerm: string = ''
  errorMessage : string  = '';
  selectedFishingLessonInfo: FishingLesson = {
    id: 0,
    name: '',
    address: '',
    description: '',
    /* grade: number ;   ovo se dobavlja iz tabele svih ocena*/
    imagePaths: '',
    maxNumberOfPeople: 0,
    freeTerms: [],
    rules: '',
    price: 0,
    additionalServices: [],
    instructor: {
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
    fishingReservations: [],
    avgGrade: 0,
    fishingEquipment: '',
    cancelConditions: ''
  }
  fishingReservation : FishingLessonReservation = {
    id: 0,
    startDateTime: new Date,
    endDateTime: new Date,
    maxPeopleNumber: 0,
    startSpecialOffer: new Date,
    endSpecialOffer: null,
    additionalServices: [],
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
    fishingLesson: this.selectedFishingLessonInfo,
    cancelled: false
  }
  role : string|null = localStorage.getItem('role');
  searchField: string = '';
  show: boolean = false;
  username: string|null = localStorage.getItem('username');
  currentCustomer! : Customer;


  constructor(private _instructorService: InstructorService, private router: Router, private _snackBar: MatSnackBar, public _customerService: CustomerService) { }

  ngOnInit(): void {
    if(this.role == 'ROLE_CUSTOMER')
    {
      this.getCustomer();
      this.getAllFishingLessons();
    }    
    /* else if(this.role == 'ROLE_INSTRUCTOR')
        this.getAllWeekendHousesForOwner(this.username) */   
    else
      this.getAllFishingLessons();
  }

  subscribe() {
    var exists = false;
    for (var lesson of this.currentCustomer.subscribedFishingLessons)
      if(lesson.id == this.selectedFishingLessonInfo.id) {
        exists = true;
        break;
      }
    if(!exists) {
      this._customerService.subscribeCustomerForFishingLesson(localStorage.getItem('username') || '', this.selectedFishingLessonInfo.id)
                .subscribe(data =>  {
                  this._snackBar.open('You are succesfully subscribed for this fishing lesson', 'Close', {duration: 5000})
                    window.location.reload();
                  },
                    error => this.errorMessage = <any>error); 
    } else 
        this._snackBar.open('You are already subscribed for this fishing lesson', 'Close', {duration: 5000});
  }

  getAllFishingLessons() {
    this._instructorService.getAllFishingLessons()
        .subscribe(data =>  {
                    this.fishingLessons = data
                    //this.dataSource = new MatTableDataSource(this.weekendHouses);
                    },
                   error => this.errorMessage = <any>error);   
  }

  getAllFreeTerms() {
       this._instructorService.getAllFreeTermsForFishingLesson(this.selectedFishingLessonInfo)
       .subscribe(data =>  this.selectedFishingLessonInfo.freeTerms = data,
                  error => this.errorMessage = <any>error); 
  }

  getCustomer() {
    this._customerService.getCustomerByUsername(localStorage.getItem('username') || '')
              .subscribe(data => {
                this.fishingReservation.customer = data
                this.currentCustomer = data
                console.log('Dobio: ', data)},
              error => this.errorMessage = <any>error);  
  }

  showInfo(fishingLesson: FishingLesson) {
    /* if(this.role == 'ROLE_WEEKEND_HOUSE_OWNER')
    {
      this._weekendHouseOwnerService.weekendHouse = boat;
      this.router.navigateByUrl("weekend-house-profile")
    } */
    if(this.role != 'ROLE_INSTRUCTOR')
    {      
      this.selectedFishingLessonInfo = fishingLesson
      if(this.role == 'ROLE_CUSTOMER') {
        this.fishingReservation.price = this.selectedFishingLessonInfo.price * (100 - this.currentCustomer.discount)/100
        this.fishingReservation.maxPeopleNumber = 1
        this.getAllFreeTerms();
      }
    }    
    this.show = true;
  }

  findAvailableFishingLessonsForSelectedTerm() {
    this._instructorService.findAvailableFishingLessonsForSelectedTerm(this.range.value.start, this.range.value.end)
          .subscribe(data =>  this.fishingLessons = data,
               error => this.errorMessage = <any>error); 
    this.show = false;
  }

  reserve() {
    if (this.fishingReservation.customer.penals > 2)
        this._snackBar.open('You can not make any reservations because you are banned for the end of the month!', 'Close', {duration: 5000});
    else {
        this.fishingReservation.fishingLesson = this.selectedFishingLessonInfo
        this.fishingReservation.endDateTime = this.getDateFromDatePickerRange(this.range.value.end)
        this.fishingReservation.startDateTime = this.getDateFromDatePickerRange(this.range.value.start)
        this.fishingReservation.startDateTime.setHours(Number(this.selectedTerm.split(':')[0]), 0) 
        this.fishingReservation.endSpecialOffer = null
        this.fishingReservation.price = this.selectedFishingLessonInfo.price * (100 - this.currentCustomer.discount)/100
        for (let service of this.fishingReservation.additionalServices) {
          this.fishingReservation.price += service.price * (100 - this.currentCustomer.discount)/100
        }
        this._instructorService.reserve(this.fishingReservation)
              .subscribe(data => {
                if(data == null)
                  this._snackBar.open('Someone has reserved fishing lesson in selected term before you. Please select other term.', 'Close', {duration: 5000});
                else {
                  this.router.navigateByUrl('customer-future-fishing-lesson-reservations').then(() => {
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
      this.fishingReservation.price += selectedService.price * (100 - this.currentCustomer.discount)/100
    else
      this.fishingReservation.price -= selectedService.price * (100 - this.currentCustomer.discount)/100
  }

  getDateFromDatePickerRange(start: Date) {
    return new Date(Date.UTC(start.getFullYear(), start.getMonth(), start.getDate(), start.getHours(), start.getMinutes()));
  }

  sortData(sort: Sort) {
    const data = this.fishingLessons.slice();
    if (!sort.active || sort.direction === '') {
      this.fishingLessons = data;
      return;
    }

    this.fishingLessons = data.sort((a, b) => {
      const isAsc = sort.direction === 'asc';
      switch (sort.active) {
        case 'name':
          return compare(a.name, b.name, isAsc);
        case 'address':
          return compare(a.address, b.address, isAsc);
        case 'description':
          return compare(a.description, b.description, isAsc);
        case 'capacity':
          return compare(a.maxNumberOfPeople, b.maxNumberOfPeople, isAsc);
        case 'owner':
          return compare(a.instructor.firstName, b.instructor.firstName, isAsc);
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
      if (this.fishingLessons[i].avgGrade == null)
        grade = ''
      else
        grade =  this.fishingLessons[i].avgGrade.toString()
      let allText = this.fishingLessons[i].name.toString() + ' ' + this.fishingLessons[i].address.toString() + ' ' + this.fishingLessons[i].description.toString() + ' ' + 
                      this.fishingLessons[i].maxNumberOfPeople.toString() + ' ' + this.fishingLessons[i].price.toString() + ' ' + this.fishingLessons[i].instructor.firstName.toString() + ' ' +
                      this.fishingLessons[i].instructor.lastName.toString() + ' ' + grade
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
