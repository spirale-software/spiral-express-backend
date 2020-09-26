import { IPersonne } from 'app/shared/model/personne.model';
import { IClient } from 'app/shared/model/client.model';

export interface IDestinataire {
  id?: number;
  personne?: IPersonne;
  client?: IClient;
}

export class Destinataire implements IDestinataire {
  constructor(public id?: number, public personne?: IPersonne, public client?: IClient) {}
}
