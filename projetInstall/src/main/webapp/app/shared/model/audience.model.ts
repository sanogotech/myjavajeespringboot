import { IContentieux } from '@/shared/model/contentieux.model';

export const enum Mois {
  Janvier = 'Janvier',
  Fevrier = 'Fevrier',
  Mars = 'Mars',
  Avril = 'Avril',
  Mai = 'Mai',
  Juin = 'Juin',
  Juillet = 'Juillet',
  Aout = 'Aout',
  Septembre = 'Septembre',
  Octobre = 'Octobre',
  Novembre = 'Novembre',
  Decembre = 'Decembre',
}

export interface IAudience {
  id?: number;
  mois?: Mois;
  stade?: any;
  dateAudience?: Date;
  contentieux?: IContentieux;
}

export class Audience implements IAudience {
  constructor(public id?: number, public mois?: Mois, public stade?: any, public dateAudience?: Date, public contentieux?: IContentieux) {}
}
