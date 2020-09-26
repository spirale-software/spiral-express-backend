import { IPersonne } from 'app/shared/model/personne.model';
import { IDestinataire } from 'app/shared/model/destinataire.model';

export interface IClient {
  id?: number;
  numero?: number;
  personne?: IPersonne;
  destinataires?: IDestinataire[];
}

export class Client implements IClient {
  constructor(public id?: number, public numero?: number, public personne?: IPersonne, public destinataires?: IDestinataire[]) {}
}
