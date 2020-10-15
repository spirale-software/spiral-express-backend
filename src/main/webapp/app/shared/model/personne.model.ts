import { IAdresse } from 'app/shared/model/adresse.model';

export interface IPersonne {
  id?: number;
  nom?: string;
  prenom?: string;
  telephone?: string;
  email?: string;
  adresse?: IAdresse;
}

export class Personne implements IPersonne {
  constructor(
    public id?: number,
    public nom?: string,
    public prenom?: string,
    public telephone?: string,
    public email?: string,
    public adresse?: IAdresse
  ) {}
}
