import { Moment } from 'moment';
import { IColi } from 'app/shared/model/coli.model';
import { IClient } from 'app/shared/model/client.model';
import { IDestinataire } from 'app/shared/model/destinataire.model';
import { StatutEnvoi } from 'app/shared/model/enumerations/statut-envoi.model';

export interface IEnvoi {
  id?: number;
  dateCreation?: Moment;
  statut?: StatutEnvoi;
  reference?: string;
  rapportQuai?: string;
  rapportLivraisaon?: string;
  coli?: IColi;
  client?: IClient;
  destinataire?: IDestinataire;
}

export class Envoi implements IEnvoi {
  constructor(
    public id?: number,
    public dateCreation?: Moment,
    public statut?: StatutEnvoi,
    public reference?: string,
    public rapportQuai?: string,
    public rapportLivraisaon?: string,
    public coli?: IColi,
    public client?: IClient,
    public destinataire?: IDestinataire
  ) {}
}
