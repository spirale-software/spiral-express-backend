import { Moment } from 'moment';

export interface IEntreprise {
  id?: number;
  dateCreation?: Moment;
  dateModification?: Moment;
  nom?: string;
  numero?: number;
  actif?: boolean;
}

export class Entreprise implements IEntreprise {
  constructor(
    public id?: number,
    public dateCreation?: Moment,
    public dateModification?: Moment,
    public nom?: string,
    public numero?: number,
    public actif?: boolean
  ) {
    this.actif = this.actif || false;
  }
}
