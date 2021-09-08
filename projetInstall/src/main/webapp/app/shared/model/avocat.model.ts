export interface IAvocat {
  id?: number;
  nom?: string;
  prenom?: string;
  email?: string;
  phone?: string;
}

export class Avocat implements IAvocat {
  constructor(public id?: number, public nom?: string, public prenom?: string, public email?: string, public phone?: string) {}
}
