export interface CustomerReport {
    id : number;
    reservationId : number;
    comment : string;
    deservesPenalty : boolean;
    didntShowUp : boolean;
}