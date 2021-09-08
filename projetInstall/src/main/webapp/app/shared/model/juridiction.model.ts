export interface IJuridiction {
  id?: number;
  nom?: string;
  ville?: string;
}

export class Juridiction implements IJuridiction {
  constructor(public id?: number, public nom?: string, public ville?: string) {}
}
