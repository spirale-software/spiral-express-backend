import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IEntreprise } from 'app/shared/model/entreprise.model';

type EntityResponseType = HttpResponse<IEntreprise>;
type EntityArrayResponseType = HttpResponse<IEntreprise[]>;

@Injectable({ providedIn: 'root' })
export class EntrepriseService {
  public resourceUrl = SERVER_API_URL + 'api/entreprises';

  constructor(protected http: HttpClient) {}

  create(entreprise: IEntreprise): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(entreprise);
    return this.http
      .post<IEntreprise>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(entreprise: IEntreprise): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(entreprise);
    return this.http
      .put<IEntreprise>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IEntreprise>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IEntreprise[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(entreprise: IEntreprise): IEntreprise {
    const copy: IEntreprise = Object.assign({}, entreprise, {
      dateCreation: entreprise.dateCreation && entreprise.dateCreation.isValid() ? entreprise.dateCreation.toJSON() : undefined,
      dateModification:
        entreprise.dateModification && entreprise.dateModification.isValid() ? entreprise.dateModification.toJSON() : undefined,
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.dateCreation = res.body.dateCreation ? moment(res.body.dateCreation) : undefined;
      res.body.dateModification = res.body.dateModification ? moment(res.body.dateModification) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((entreprise: IEntreprise) => {
        entreprise.dateCreation = entreprise.dateCreation ? moment(entreprise.dateCreation) : undefined;
        entreprise.dateModification = entreprise.dateModification ? moment(entreprise.dateModification) : undefined;
      });
    }
    return res;
  }
}
