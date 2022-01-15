import { Customer } from "./customer";

export interface CustomerLoyalty {
    customer: Customer;
    points: number ;
    category: string ;
  }