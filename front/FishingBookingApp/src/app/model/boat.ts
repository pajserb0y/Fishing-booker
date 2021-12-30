export interface Boat {
    id: number ;
    name: string ;
    address: string ;
    details: string ;
    freeTerms: Date[];
    priceList: string;  //treba da bude neki item koji ce sadrzati opis i cenu kao dva polja
    additionalServes: string;  //i ovo treba menjati
    grade: number ;
  }