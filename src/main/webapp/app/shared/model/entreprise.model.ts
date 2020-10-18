export interface IEntreprise {
  id?: number;
  nom?: string;
  actif?: boolean;
}

export class Entreprise implements IEntreprise {
  constructor(public id?: number, public nom?: string, public actif?: boolean) {
    this.actif = this.actif || false;
  }
}
