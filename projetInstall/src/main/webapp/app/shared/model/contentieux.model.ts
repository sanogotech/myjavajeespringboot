import { IRequerant } from '@/shared/model/requerant.model';
import { IAvocat } from '@/shared/model/avocat.model';
import { IJuridiction } from '@/shared/model/juridiction.model';

export const enum Entite {
  CIE = 'CIE',
  SODECI = 'SODECI',
  AWALE = 'AWALE',
}

export interface IContentieux {
  id?: number;
  entite?: Entite;
  refContentieux?: string;
  datePremiereAudience?: Date;
  requerant?: IRequerant;
  avocat?: IAvocat;
  juridiction?: IJuridiction;
}

export class Contentieux implements IContentieux {
  constructor(
    public id?: number,
    public entite?: Entite,
    public refContentieux?: string,
    public datePremiereAudience?: Date,
    public requerant?: IRequerant,
    public avocat?: IAvocat,
    public juridiction?: IJuridiction
  ) {}
}
