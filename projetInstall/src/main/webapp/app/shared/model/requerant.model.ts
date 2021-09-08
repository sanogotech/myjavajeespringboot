export interface IRequerant {
  id?: number;
  nom?: string;
  prenom?: string;
  numCNI?: string;
}

export class Requerant implements IRequerant {
  constructor(public id?: number, public nom?: string, public prenom?: string, public numCNI?: string) {}
}
