import { Pays } from 'app/shared/model/enumerations/pays.model';

export interface IAdresse {
  id?: number;
  pays?: Pays;
  ville?: string;
  codePostal?: string;
  rue?: string;
}

export class Adresse implements IAdresse {
  constructor(public id?: number, public pays?: Pays, public ville?: string, public codePostal?: string, public rue?: string) {}
}
