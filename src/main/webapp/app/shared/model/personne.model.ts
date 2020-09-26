export interface IPersonne {
  id?: number;
  nom?: string;
  prenom?: string;
  pays?: string;
  adresse?: string;
  telephone?: string;
  email?: string;
}

export class Personne implements IPersonne {
  constructor(
    public id?: number,
    public nom?: string,
    public prenom?: string,
    public pays?: string,
    public adresse?: string,
    public telephone?: string,
    public email?: string
  ) {}
}
