import { IPersonne } from 'app/shared/model/personne.model';

export interface IPartenaire {
  id?: number;
  numero?: number;
  personne?: IPersonne;
}

export class Partenaire implements IPartenaire {
  constructor(public id?: number, public numero?: number, public personne?: IPersonne) {}
}
